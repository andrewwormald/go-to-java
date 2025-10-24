// Go to Java - Stage 4.2: Common Design Patterns
// Coming from Go: Similar patterns but Java's OOP makes them more explicit

import java.util.*;

// Observer Pattern - similar to Go channels but object-oriented
interface GameEventListener {
    void onPlayerLevelUp(String playerName, int newLevel);
    void onPlayerDied(String playerName);
    void onItemFound(String playerName, String itemName);
}

class GameEventManager {
    private List<GameEventListener> listeners = new ArrayList<>();

    public void addListener(GameEventListener listener) {
        listeners.add(listener);
    }

    public void removeListener(GameEventListener listener) {
        listeners.remove(listener);
    }

    public void notifyPlayerLevelUp(String playerName, int newLevel) {
        for (GameEventListener listener : listeners) {
            listener.onPlayerLevelUp(playerName, newLevel);
        }
    }

    public void notifyPlayerDied(String playerName) {
        for (GameEventListener listener : listeners) {
            listener.onPlayerDied(playerName);
        }
    }

    public void notifyItemFound(String playerName, String itemName) {
        for (GameEventListener listener : listeners) {
            listener.onItemFound(playerName, itemName);
        }
    }
}

// Concrete observers
class AchievementSystem implements GameEventListener {
    private Map<String, Integer> playerLevels = new HashMap<>();

    @Override
    public void onPlayerLevelUp(String playerName, int newLevel) {
        playerLevels.put(playerName, newLevel);
        if (newLevel == 10) {
            System.out.println("🏆 Achievement Unlocked: " + playerName + " reached level 10!");
        }
        if (newLevel == 25) {
            System.out.println("🏆 Achievement Unlocked: " + playerName + " is a veteran!");
        }
    }

    @Override
    public void onPlayerDied(String playerName) {
        System.out.println("💀 Death recorded for " + playerName);
    }

    @Override
    public void onItemFound(String playerName, String itemName) {
        if (itemName.contains("Legendary")) {
            System.out.println("🏆 Achievement Unlocked: " + playerName + " found a legendary item!");
        }
    }
}

class StatisticsTracker implements GameEventListener {
    private Map<String, Integer> levelUpCounts = new HashMap<>();
    private Map<String, Integer> deathCounts = new HashMap<>();
    private Map<String, Integer> itemCounts = new HashMap<>();

    @Override
    public void onPlayerLevelUp(String playerName, int newLevel) {
        levelUpCounts.put(playerName, levelUpCounts.getOrDefault(playerName, 0) + 1);
    }

    @Override
    public void onPlayerDied(String playerName) {
        deathCounts.put(playerName, deathCounts.getOrDefault(playerName, 0) + 1);
    }

    @Override
    public void onItemFound(String playerName, String itemName) {
        itemCounts.put(playerName, itemCounts.getOrDefault(playerName, 0) + 1);
    }

    public void printStatistics() {
        System.out.println("\n=== Game Statistics ===");
        System.out.println("Level ups: " + levelUpCounts);
        System.out.println("Deaths: " + deathCounts);
        System.out.println("Items found: " + itemCounts);
    }
}

// Strategy Pattern - different algorithms for same task
interface CombatStrategy {
    int calculateDamage(int baseDamage, int level);
    String getStrategyName();
}

class AggressiveStrategy implements CombatStrategy {
    @Override
    public int calculateDamage(int baseDamage, int level) {
        return baseDamage + (level * 2);  // high damage scaling
    }

    @Override
    public String getStrategyName() {
        return "Aggressive";
    }
}

class DefensiveStrategy implements CombatStrategy {
    @Override
    public int calculateDamage(int baseDamage, int level) {
        return baseDamage + (level / 2);  // low damage, presumably tankier
    }

    @Override
    public String getStrategyName() {
        return "Defensive";
    }
}

class BalancedStrategy implements CombatStrategy {
    @Override
    public int calculateDamage(int baseDamage, int level) {
        return baseDamage + level;  // medium scaling
    }

    @Override
    public String getStrategyName() {
        return "Balanced";
    }
}

// Factory Pattern - creating objects without specifying exact class
interface Enemy {
    void attack();
    int getHealth();
    String getType();
}

class Goblin implements Enemy {
    @Override
    public void attack() {
        System.out.println("Goblin swipes with claws!");
    }

    @Override
    public int getHealth() {
        return 30;
    }

    @Override
    public String getType() {
        return "Goblin";
    }
}

class Dragon implements Enemy {
    @Override
    public void attack() {
        System.out.println("Dragon breathes fire!");
    }

    @Override
    public int getHealth() {
        return 200;
    }

    @Override
    public String getType() {
        return "Dragon";
    }
}

class Skeleton implements Enemy {
    @Override
    public void attack() {
        System.out.println("Skeleton shoots arrows!");
    }

    @Override
    public int getHealth() {
        return 50;
    }

    @Override
    public String getType() {
        return "Skeleton";
    }
}

// Factory class
class EnemyFactory {
    public static Enemy createEnemy(String type, int playerLevel) {
        switch (type.toLowerCase()) {
            case "goblin":
                return new Goblin();
            case "dragon":
                return new Dragon();
            case "skeleton":
                return new Skeleton();
            default:
                // Default based on player level
                if (playerLevel < 5) {
                    return new Goblin();
                } else if (playerLevel < 15) {
                    return new Skeleton();
                } else {
                    return new Dragon();
                }
        }
    }

    public static Enemy createRandomEnemy(int playerLevel) {
        String[] types = {"goblin", "skeleton", "dragon"};
        String randomType = types[new Random().nextInt(types.length)];
        return createEnemy(randomType, playerLevel);
    }
}

// Context class using Strategy pattern
class GameCharacter {
    private String name;
    private int level;
    private int baseDamage;
    private CombatStrategy strategy;
    private GameEventManager eventManager;

    public GameCharacter(String name, GameEventManager eventManager) {
        this.name = name;
        this.level = 1;
        this.baseDamage = 10;
        this.strategy = new BalancedStrategy();  // default
        this.eventManager = eventManager;
    }

    public void setStrategy(CombatStrategy strategy) {
        this.strategy = strategy;
        System.out.println(name + " switched to " + strategy.getStrategyName() + " combat style");
    }

    public void levelUp() {
        level++;
        eventManager.notifyPlayerLevelUp(name, level);
    }

    public void die() {
        eventManager.notifyPlayerDied(name);
    }

    public void findItem(String itemName) {
        eventManager.notifyItemFound(name, itemName);
    }

    public int attack() {
        int damage = strategy.calculateDamage(baseDamage, level);
        System.out.println(name + " attacks for " + damage + " damage using " +
                strategy.getStrategyName() + " strategy");
        return damage;
    }

    // Getters
    public String getName() { return name; }
    public int getLevel() { return level; }
}

public class DesignPatterns {
    public static void main(String[] args) {
        System.out.println("=== Design Patterns in Java ===");

        // Observer Pattern Demo
        System.out.println("\n--- Observer Pattern ---");
        GameEventManager eventManager = new GameEventManager();
        AchievementSystem achievements = new AchievementSystem();
        StatisticsTracker stats = new StatisticsTracker();

        eventManager.addListener(achievements);
        eventManager.addListener(stats);

        GameCharacter player = new GameCharacter("Hero", eventManager);

        // Strategy Pattern Demo
        System.out.println("\n--- Strategy Pattern ---");
        System.out.println("Initial attack:");
        player.attack();

        player.setStrategy(new AggressiveStrategy());
        player.attack();

        player.setStrategy(new DefensiveStrategy());
        player.attack();

        // Observer Pattern in action
        System.out.println("\n--- Observer Pattern in Action ---");
        for (int i = 0; i < 12; i++) {
            player.levelUp();
        }

        player.findItem("Iron Sword");
        player.findItem("Legendary Excalibur");
        player.die();

        stats.printStatistics();

        // Factory Pattern Demo
        System.out.println("\n--- Factory Pattern ---");
        System.out.println("Creating enemies for level " + player.getLevel() + " player:");

        Enemy enemy1 = EnemyFactory.createEnemy("goblin", player.getLevel());
        Enemy enemy2 = EnemyFactory.createEnemy("dragon", player.getLevel());
        Enemy enemy3 = EnemyFactory.createEnemy("unknown", player.getLevel());  // default

        Enemy[] enemies = {enemy1, enemy2, enemy3};
        for (Enemy enemy : enemies) {
            System.out.println(enemy.getType() + " (HP: " + enemy.getHealth() + ")");
            enemy.attack();
        }

        System.out.println("\nRandom enemies:");
        for (int i = 0; i < 5; i++) {
            Enemy randomEnemy = EnemyFactory.createRandomEnemy(player.getLevel());
            System.out.println("  " + randomEnemy.getType());
        }

        // Demonstrate pattern combinations
        System.out.println("\n--- Combining Patterns ---");
        GameCharacter player2 = new GameCharacter("Warrior", eventManager);
        player2.setStrategy(new AggressiveStrategy());

        // Simulate a battle
        Enemy boss = EnemyFactory.createEnemy("dragon", 20);
        System.out.println("\nBattle begins!");
        System.out.println(boss.getType() + " appears with " + boss.getHealth() + " HP!");

        boss.attack();
        int playerDamage = player2.attack();

        if (playerDamage > 50) {
            player2.findItem("Victory Trophy");
        }

        player2.levelUp();
        stats.printStatistics();

        System.out.println("\n=== Design Patterns Demo Complete ===");
    }
}