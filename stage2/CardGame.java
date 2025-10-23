// Stage 2.3: Card Game - Putting It All Together
// Fun project demonstrating interfaces, abstract classes, and polymorphism

// Interface for things that can be played
interface Playable {
    void play();
    int getCost();
}

// Abstract base class for all cards
abstract class Card implements Playable {
    protected String name;
    protected int cost;
    protected String description;

    public Card(String name, int cost, String description) {
        this.name = name;
        this.cost = cost;
        this.description = description;
    }

    @Override
    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Abstract method - each card type implements differently
    public abstract String getType();

    @Override
    public String toString() {
        return String.format("[%s] %s (%d) - %s", getType(), name, cost, description);
    }
}

// Specific card types
class CreatureCard extends Card {
    private int attack;
    private int health;

    public CreatureCard(String name, int cost, String description, int attack, int health) {
        super(name, cost, description);
        this.attack = attack;
        this.health = health;
    }

    @Override
    public String getType() {
        return "CREATURE";
    }

    @Override
    public void play() {
        System.out.println("Summoning " + name + " (" + attack + "/" + health + ")!");
    }

    public int getAttack() { return attack; }
    public int getHealth() { return health; }

    @Override
    public String toString() {
        return super.toString() + String.format(" [%d/%d]", attack, health);
    }
}

class SpellCard extends Card {
    private int damage;

    public SpellCard(String name, int cost, String description, int damage) {
        super(name, cost, description);
        this.damage = damage;
    }

    @Override
    public String getType() {
        return "SPELL";
    }

    @Override
    public void play() {
        System.out.println("Casting " + name + " for " + damage + " damage!");
    }

    public int getDamage() { return damage; }
}

class ArtifactCard extends Card {
    private String effect;

    public ArtifactCard(String name, int cost, String description, String effect) {
        super(name, cost, description);
        this.effect = effect;
    }

    @Override
    public String getType() {
        return "ARTIFACT";
    }

    @Override
    public void play() {
        System.out.println("Playing artifact " + name + " - " + effect);
    }

    public String getEffect() { return effect; }
}

// Interface for interactive game elements
interface Interactive {
    void onSelect();
    boolean canInteract();
}

// Player class that can interact with cards
class Player implements Interactive {
    private String name;
    private int mana;
    private Card[] hand;
    private int handSize;

    public Player(String name, int mana) {
        this.name = name;
        this.mana = mana;
        this.hand = new Card[7];  // max hand size
        this.handSize = 0;
    }

    public void addCard(Card card) {
        if (handSize < hand.length) {
            hand[handSize++] = card;
        }
    }

    public void playCard(int index) {
        if (index >= 0 && index < handSize) {
            Card card = hand[index];
            if (mana >= card.getCost()) {
                mana -= card.getCost();
                card.play();
                removeCardFromHand(index);
                System.out.println(name + " has " + mana + " mana remaining");
            } else {
                System.out.println("Not enough mana to play " + card.getName());
            }
        }
    }

    private void removeCardFromHand(int index) {
        for (int i = index; i < handSize - 1; i++) {
            hand[i] = hand[i + 1];
        }
        handSize--;
    }

    public void showHand() {
        System.out.println("\n" + name + "'s Hand (Mana: " + mana + "):");
        for (int i = 0; i < handSize; i++) {
            System.out.println(i + ": " + hand[i]);
        }
    }

    @Override
    public void onSelect() {
        System.out.println(name + " is selected and ready to play!");
    }

    @Override
    public boolean canInteract() {
        return handSize > 0 && mana > 0;
    }

    public String getName() { return name; }
    public int getMana() { return mana; }
}

public class CardGame {
    public static void main(String[] args) {
        System.out.println("=== Card Game Demo ===");

        // Create player
        Player player = new Player("Merlin", 10);

        // Create different types of cards
        Card[] deck = {
            new CreatureCard("Fire Dragon", 5, "Powerful flying creature", 6, 4),
            new CreatureCard("Knight", 3, "Brave warrior", 3, 5),
            new SpellCard("Lightning Bolt", 2, "Quick damage spell", 4),
            new SpellCard("Heal", 1, "Restore health", 3),
            new ArtifactCard("Magic Sword", 4, "Enchanted weapon", "+2 Attack to creatures")
        };

        // Add cards to player's hand
        System.out.println("\n=== Building Deck ===");
        for (Card card : deck) {
            player.addCard(card);
            System.out.println("Added: " + card);
        }

        // Show polymorphism - all cards implement Playable
        System.out.println("\n=== Playable Interface Demo ===");
        Playable[] playableCards = {deck[0], deck[1], deck[2]};
        for (Playable card : playableCards) {
            System.out.println("Cost: " + card.getCost());
        }

        // Interactive demo
        System.out.println("\n=== Interactive Demo ===");
        if (player.canInteract()) {
            player.onSelect();
        }

        // Game simulation
        System.out.println("\n=== Playing Cards ===");
        player.showHand();

        System.out.println("\nPlaying some cards...");
        player.playCard(2);  // Lightning Bolt
        player.playCard(1);  // Knight (was index 1, now 0 after removal)
        player.playCard(0);  // Fire Dragon

        player.showHand();

        // Try to play expensive card with insufficient mana
        System.out.println("\nTrying to play expensive card...");
        player.playCard(0);

        System.out.println("\n=== Game Over ===");
    }
}