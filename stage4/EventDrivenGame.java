// Stage 4.3: Event-Driven Game System
// Comprehensive example combining all Stage 4 concepts

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

// Event system interfaces
interface GameEvent {
    String getEventType();
    long getTimestamp();
    Map<String, Object> getEventData();
}

interface EventHandler<T extends GameEvent> {
    void handle(T event);
    Class<T> getEventType();
}

// Concrete events
class PlayerActionEvent implements GameEvent {
    private final String eventType;
    private final long timestamp;
    private final Map<String, Object> eventData;

    public PlayerActionEvent(String action, String playerName, Object... params) {
        this.eventType = "PLAYER_ACTION";
        this.timestamp = System.currentTimeMillis();
        this.eventData = new HashMap<>();
        this.eventData.put("action", action);
        this.eventData.put("playerName", playerName);

        // Add additional parameters
        for (int i = 0; i < params.length; i += 2) {
            if (i + 1 < params.length) {
                eventData.put(params[i].toString(), params[i + 1]);
            }
        }
    }

    @Override
    public String getEventType() { return eventType; }

    @Override
    public long getTimestamp() { return timestamp; }

    @Override
    public Map<String, Object> getEventData() { return new HashMap<>(eventData); }
}

class CombatEvent implements GameEvent {
    private final String eventType;
    private final long timestamp;
    private final Map<String, Object> eventData;

    public CombatEvent(String attacker, String target, int damage, boolean critical) {
        this.eventType = "COMBAT";
        this.timestamp = System.currentTimeMillis();
        this.eventData = new HashMap<>();
        this.eventData.put("attacker", attacker);
        this.eventData.put("target", target);
        this.eventData.put("damage", damage);
        this.eventData.put("critical", critical);
    }

    @Override
    public String getEventType() { return eventType; }

    @Override
    public long getTimestamp() { return timestamp; }

    @Override
    public Map<String, Object> getEventData() { return new HashMap<>(eventData); }
}

class ItemEvent implements GameEvent {
    private final String eventType;
    private final long timestamp;
    private final Map<String, Object> eventData;

    public ItemEvent(String action, String playerName, String itemName, String rarity) {
        this.eventType = "ITEM";
        this.timestamp = System.currentTimeMillis();
        this.eventData = new HashMap<>();
        this.eventData.put("action", action);
        this.eventData.put("playerName", playerName);
        this.eventData.put("itemName", itemName);
        this.eventData.put("rarity", rarity);
    }

    @Override
    public String getEventType() { return eventType; }

    @Override
    public long getTimestamp() { return timestamp; }

    @Override
    public Map<String, Object> getEventData() { return new HashMap<>(eventData); }
}

// Event bus for managing events
class EventBus {
    private final Map<Class<? extends GameEvent>, List<EventHandler<? extends GameEvent>>> handlers;
    private final List<GameEvent> eventHistory;

    public EventBus() {
        this.handlers = new HashMap<>();
        this.eventHistory = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public <T extends GameEvent> void registerHandler(EventHandler<T> handler) {
        Class<T> eventType = handler.getEventType();
        handlers.computeIfAbsent(eventType, k -> new ArrayList<>()).add(handler);
    }

    @SuppressWarnings("unchecked")
    public <T extends GameEvent> void publishEvent(T event) {
        eventHistory.add(event);

        List<EventHandler<? extends GameEvent>> eventHandlers = handlers.get(event.getClass());
        if (eventHandlers != null) {
            for (EventHandler<? extends GameEvent> handler : eventHandlers) {
                try {
                    ((EventHandler<T>) handler).handle(event);
                } catch (Exception e) {
                    System.err.println("Error handling event: " + e.getMessage());
                }
            }
        }
    }

    public List<GameEvent> getEventHistory() {
        return new ArrayList<>(eventHistory);
    }

    public void clearHistory() {
        eventHistory.clear();
    }
}

// Game components using different patterns

// Immutable game item (encapsulation)
final class GameItem {
    private final String name;
    private final String rarity;
    private final int value;
    private final Map<String, Integer> stats;

    public GameItem(String name, String rarity, int value, Map<String, Integer> stats) {
        this.name = name;
        this.rarity = rarity;
        this.value = value;
        this.stats = new HashMap<>(stats);  // defensive copy
    }

    public String getName() { return name; }
    public String getRarity() { return rarity; }
    public int getValue() { return value; }
    public Map<String, Integer> getStats() { return new HashMap<>(stats); }

    @Override
    public String toString() {
        return String.format("%s (%s) - Value: %d, Stats: %s", name, rarity, value, stats);
    }
}

// Factory for creating game items
class ItemFactory {
    private static final Map<String, Map<String, Integer>> ITEM_TEMPLATES = new HashMap<>();
    private static final String[] RARITIES = {"Common", "Rare", "Epic", "Legendary"};

    static {
        ITEM_TEMPLATES.put("Sword", Map.of("attack", 10, "durability", 100));
        ITEM_TEMPLATES.put("Shield", Map.of("defense", 8, "durability", 120));
        ITEM_TEMPLATES.put("Potion", Map.of("healing", 50));
        ITEM_TEMPLATES.put("Bow", Map.of("attack", 8, "range", 15, "durability", 80));
    }

    public static GameItem createItem(String itemType, String rarity) {
        Map<String, Integer> baseStats = ITEM_TEMPLATES.get(itemType);
        if (baseStats == null) {
            throw new IllegalArgumentException("Unknown item type: " + itemType);
        }

        // Scale stats based on rarity
        int rarityMultiplier = getRarityMultiplier(rarity);
        Map<String, Integer> scaledStats = new HashMap<>();
        for (Map.Entry<String, Integer> entry : baseStats.entrySet()) {
            scaledStats.put(entry.getKey(), entry.getValue() * rarityMultiplier);
        }

        int value = 100 * rarityMultiplier;
        return new GameItem(rarity + " " + itemType, rarity, value, scaledStats);
    }

    public static GameItem createRandomItem() {
        String[] itemTypes = ITEM_TEMPLATES.keySet().toArray(new String[0]);
        String randomType = itemTypes[ThreadLocalRandom.current().nextInt(itemTypes.length)];
        String randomRarity = RARITIES[ThreadLocalRandom.current().nextInt(RARITIES.length)];
        return createItem(randomType, randomRarity);
    }

    private static int getRarityMultiplier(String rarity) {
        switch (rarity) {
            case "Common": return 1;
            case "Rare": return 2;
            case "Epic": return 3;
            case "Legendary": return 5;
            default: return 1;
        }
    }
}

// Event handlers implementing different concerns

class QuestHandler implements EventHandler<PlayerActionEvent> {
    private final Map<String, Integer> questProgress = new HashMap<>();
    private final EventBus eventBus;

    public QuestHandler(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void handle(PlayerActionEvent event) {
        String action = (String) event.getEventData().get("action");
        String playerName = (String) event.getEventData().get("playerName");

        if ("KILL_ENEMY".equals(action)) {
            String enemyType = (String) event.getEventData().get("enemyType");
            String questKey = playerName + "_kill_" + enemyType;
            int kills = questProgress.getOrDefault(questKey, 0) + 1;
            questProgress.put(questKey, kills);

            if (kills == 10) {
                System.out.println("🎯 Quest Complete: " + playerName + " killed 10 " + enemyType + "s!");
                // Award quest item
                GameItem questReward = ItemFactory.createItem("Sword", "Epic");
                eventBus.publishEvent(new ItemEvent("FOUND", playerName, questReward.getName(), questReward.getRarity()));
            }
        }
    }

    @Override
    public Class<PlayerActionEvent> getEventType() {
        return PlayerActionEvent.class;
    }
}

class CombatLogHandler implements EventHandler<CombatEvent> {
    private final List<String> combatLog = new ArrayList<>();

    @Override
    public void handle(CombatEvent event) {
        Map<String, Object> data = event.getEventData();
        String attacker = (String) data.get("attacker");
        String target = (String) data.get("target");
        int damage = (Integer) data.get("damage");
        boolean critical = (Boolean) data.get("critical");

        String logEntry = String.format("%s attacks %s for %d damage%s",
                attacker, target, damage, critical ? " (CRITICAL!)" : "");
        combatLog.add(logEntry);
        System.out.println("⚔️ " + logEntry);
    }

    @Override
    public Class<CombatEvent> getEventType() {
        return CombatEvent.class;
    }

    public List<String> getCombatLog() {
        return new ArrayList<>(combatLog);
    }
}

class LootHandler implements EventHandler<ItemEvent> {
    @Override
    public void handle(ItemEvent event) {
        Map<String, Object> data = event.getEventData();
        String action = (String) data.get("action");
        String playerName = (String) data.get("playerName");
        String itemName = (String) data.get("itemName");
        String rarity = (String) data.get("rarity");

        if ("FOUND".equals(action)) {
            String emoji = getRarityEmoji(rarity);
            System.out.println(emoji + " " + playerName + " found: " + itemName);
        }
    }

    @Override
    public Class<ItemEvent> getEventType() {
        return ItemEvent.class;
    }

    private String getRarityEmoji(String rarity) {
        switch (rarity) {
            case "Common": return "⚪";
            case "Rare": return "🔵";
            case "Epic": return "🟣";
            case "Legendary": return "🟡";
            default: return "⚫";
        }
    }
}

// Main game player class
class GamePlayer {
    private final String name;
    private final EventBus eventBus;
    private int health = 100;
    private int level = 1;

    public GamePlayer(String name, EventBus eventBus) {
        this.name = name;
        this.eventBus = eventBus;
    }

    public void attackEnemy(String enemyType) {
        int damage = ThreadLocalRandom.current().nextInt(15, 31);
        boolean critical = ThreadLocalRandom.current().nextDouble() < 0.2;
        if (critical) damage *= 2;

        eventBus.publishEvent(new CombatEvent(name, enemyType, damage, critical));
        eventBus.publishEvent(new PlayerActionEvent("KILL_ENEMY", name, "enemyType", enemyType));

        // Chance to find loot
        if (ThreadLocalRandom.current().nextDouble() < 0.3) {
            GameItem loot = ItemFactory.createRandomItem();
            eventBus.publishEvent(new ItemEvent("FOUND", name, loot.getName(), loot.getRarity()));
        }
    }

    public void takeDamage(int damage) {
        health -= damage;
        System.out.println(name + " takes " + damage + " damage. Health: " + health);
        if (health <= 0) {
            System.out.println("💀 " + name + " has died!");
        }
    }

    public String getName() { return name; }
    public int getHealth() { return health; }
}

public class EventDrivenGame {
    public static void main(String[] args) {
        System.out.println("=== Event-Driven Game System ===");

        // Create event bus and register handlers
        EventBus eventBus = new EventBus();
        QuestHandler questHandler = new QuestHandler(eventBus);
        CombatLogHandler combatLogHandler = new CombatLogHandler();
        LootHandler lootHandler = new LootHandler();

        eventBus.registerHandler(questHandler);
        eventBus.registerHandler(combatLogHandler);
        eventBus.registerHandler(lootHandler);

        // Create players
        GamePlayer player1 = new GamePlayer("Conan", eventBus);
        GamePlayer player2 = new GamePlayer("Xena", eventBus);

        System.out.println("\n--- Game Simulation ---");

        // Simulate game events
        String[] enemies = {"Goblin", "Orc", "Skeleton", "Dragon"};

        for (int round = 1; round <= 5; round++) {
            System.out.println("\n=== Round " + round + " ===");

            // Players attack random enemies
            String enemy1 = enemies[ThreadLocalRandom.current().nextInt(enemies.length)];
            String enemy2 = enemies[ThreadLocalRandom.current().nextInt(enemies.length)];

            player1.attackEnemy(enemy1);
            player2.attackEnemy(enemy2);

            // Enemies might attack back
            if (ThreadLocalRandom.current().nextBoolean()) {
                int damage = ThreadLocalRandom.current().nextInt(5, 16);
                eventBus.publishEvent(new CombatEvent(enemy1, player1.getName(), damage, false));
                player1.takeDamage(damage);
            }

            if (ThreadLocalRandom.current().nextBoolean()) {
                int damage = ThreadLocalRandom.current().nextInt(5, 16);
                eventBus.publishEvent(new CombatEvent(enemy2, player2.getName(), damage, false));
                player2.takeDamage(damage);
            }
        }

        // Manual item discovery
        System.out.println("\n--- Manual Item Discovery ---");
        GameItem legendaryItem = ItemFactory.createItem("Sword", "Legendary");
        eventBus.publishEvent(new ItemEvent("FOUND", "Conan", legendaryItem.getName(), legendaryItem.getRarity()));

        // Event history analysis
        System.out.println("\n--- Event History Analysis ---");
        List<GameEvent> history = eventBus.getEventHistory();
        System.out.println("Total events: " + history.size());

        Map<String, Integer> eventTypeCounts = new HashMap<>();
        for (GameEvent event : history) {
            eventTypeCounts.put(event.getEventType(),
                    eventTypeCounts.getOrDefault(event.getEventType(), 0) + 1);
        }
        System.out.println("Event type breakdown: " + eventTypeCounts);

        // Show combat log
        System.out.println("\n--- Combat Log Summary ---");
        List<String> combatLog = combatLogHandler.getCombatLog();
        System.out.println("Combat actions recorded: " + combatLog.size());

        System.out.println("\n=== Event-Driven Game Demo Complete ===");
    }
}