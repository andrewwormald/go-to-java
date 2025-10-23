// Stage 5.3: Flyweight Pattern for Memory Optimization
// Coming from Go: Similar to string interning, but more explicit object sharing

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Flyweight interface
interface TreeType {
    void render(int x, int y, String season);
    String getSpecies();
    String getColor();
    String getTexture();
}

// Concrete Flyweight - stores intrinsic state (shared data)
class ConcreteTreeType implements TreeType {
    private final String species;    // intrinsic state - shared
    private final String color;      // intrinsic state - shared
    private final String texture;    // intrinsic state - shared

    public ConcreteTreeType(String species, String color, String texture) {
        this.species = species;
        this.color = color;
        this.texture = texture;

        // Simulate expensive initialization
        simulateExpensiveInitialization();
    }

    private void simulateExpensiveInitialization() {
        try {
            Thread.sleep(10); // Simulate loading textures, models, etc.
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Created expensive TreeType: " + species + " (" + color + ")");
    }

    @Override
    public void render(int x, int y, String season) {
        // Use intrinsic state (this object's data) + extrinsic state (parameters)
        String seasonalColor = adjustColorForSeason(season);
        System.out.printf("Rendering %s tree at (%d,%d) with %s %s texture in %s%n",
                species, x, y, seasonalColor, texture, season);
    }

    private String adjustColorForSeason(String season) {
        switch (season.toLowerCase()) {
            case "spring": return "bright " + color;
            case "summer": return "lush " + color;
            case "autumn": return "golden-" + color;
            case "winter": return "bare " + color;
            default: return color;
        }
    }

    @Override
    public String getSpecies() { return species; }

    @Override
    public String getColor() { return color; }

    @Override
    public String getTexture() { return texture; }

    @Override
    public String toString() {
        return String.format("TreeType{species='%s', color='%s', texture='%s'}",
                species, color, texture);
    }
}

// Flyweight Factory - manages flyweight instances
class TreeTypeFactory {
    private static final Map<String, TreeType> treeTypes = new ConcurrentHashMap<>();
    private static int createdInstances = 0;

    public static TreeType getTreeType(String species, String color, String texture) {
        String key = species + "_" + color + "_" + texture;

        return treeTypes.computeIfAbsent(key, k -> {
            createdInstances++;
            return new ConcreteTreeType(species, color, texture);
        });
    }

    public static int getCreatedInstancesCount() {
        return createdInstances;
    }

    public static int getCachedInstancesCount() {
        return treeTypes.size();
    }

    public static void printCacheStatistics() {
        System.out.println("\n=== Flyweight Cache Statistics ===");
        System.out.println("Total flyweight instances created: " + createdInstances);
        System.out.println("Cached flyweight instances: " + treeTypes.size());
        System.out.println("Memory saved by sharing: " +
                ((float)(getTotalRequestedInstances() - createdInstances) / getTotalRequestedInstances() * 100) + "%");
    }

    private static int totalRequestedInstances = 0;

    public static void incrementRequestCount() {
        totalRequestedInstances++;
    }

    public static int getTotalRequestedInstances() {
        return totalRequestedInstances;
    }
}

// Context class - stores extrinsic state and uses flyweights
class Tree {
    private final int x, y;              // extrinsic state - unique per tree
    private final int age;               // extrinsic state
    private final TreeType treeType;     // reference to flyweight (intrinsic state)

    public Tree(int x, int y, int age, String species, String color, String texture) {
        this.x = x;
        this.y = y;
        this.age = age;

        TreeTypeFactory.incrementRequestCount();
        this.treeType = TreeTypeFactory.getTreeType(species, color, texture);
    }

    public void render(String season) {
        treeType.render(x, y, season);
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getAge() { return age; }
    public TreeType getTreeType() { return treeType; }

    @Override
    public String toString() {
        return String.format("Tree at (%d,%d), age %d, type: %s",
                x, y, age, treeType.getSpecies());
    }
}

// Forest class - manages many trees (context objects)
class Forest {
    private final List<Tree> trees = new ArrayList<>();
    private final Random random = new Random();

    public void plantTree(int x, int y, int age, String species, String color, String texture) {
        Tree tree = new Tree(x, y, age, species, color, texture);
        trees.add(tree);
    }

    public void generateRandomForest(int numberOfTrees) {
        String[] species = {"Oak", "Pine", "Birch", "Maple", "Spruce"};
        String[] colors = {"green", "dark-green", "brown", "light-green"};
        String[] textures = {"rough", "smooth", "bark-heavy", "leafy"};

        System.out.println("Generating forest with " + numberOfTrees + " trees...");

        for (int i = 0; i < numberOfTrees; i++) {
            int x = random.nextInt(1000);
            int y = random.nextInt(1000);
            int age = random.nextInt(100) + 1;
            String speciesChoice = species[random.nextInt(species.length)];
            String colorChoice = colors[random.nextInt(colors.length)];
            String textureChoice = textures[random.nextInt(textures.length)];

            plantTree(x, y, age, speciesChoice, colorChoice, textureChoice);
        }

        System.out.println("Forest generation complete!");
    }

    public void renderForest(String season) {
        System.out.println("\n=== Rendering Forest in " + season + " ===");

        // Group trees by type for efficient rendering
        Map<TreeType, List<Tree>> treesByType = new HashMap<>();
        for (Tree tree : trees) {
            treesByType.computeIfAbsent(tree.getTreeType(), k -> new ArrayList<>()).add(tree);
        }

        // Render by type (demonstrates flyweight efficiency)
        for (Map.Entry<TreeType, List<Tree>> entry : treesByType.entrySet()) {
            TreeType type = entry.getKey();
            List<Tree> treesOfType = entry.getValue();

            System.out.printf("Rendering %d %s trees:%n", treesOfType.size(), type.getSpecies());
            for (Tree tree : treesOfType.subList(0, Math.min(3, treesOfType.size()))) { // Show first 3
                tree.render(season);
            }
            if (treesOfType.size() > 3) {
                System.out.printf("... and %d more %s trees%n",
                        treesOfType.size() - 3, type.getSpecies());
            }
        }
    }

    public void printStatistics() {
        System.out.println("\n=== Forest Statistics ===");
        System.out.println("Total trees in forest: " + trees.size());

        // Count by species
        Map<String, Integer> speciesCount = new HashMap<>();
        for (Tree tree : trees) {
            String species = tree.getTreeType().getSpecies();
            speciesCount.put(species, speciesCount.getOrDefault(species, 0) + 1);
        }

        System.out.println("Trees by species:");
        speciesCount.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> System.out.printf("  %s: %d trees%n",
                        entry.getKey(), entry.getValue()));
    }

    public int getTreeCount() {
        return trees.size();
    }
}

// Demonstration of memory usage without flyweight (for comparison)
class NaiveTerrain {
    static class NaiveTree {
        private final int x, y, age;
        private final String species, color, texture;

        public NaiveTree(int x, int y, int age, String species, String color, String texture) {
            this.x = x;
            this.y = y;
            this.age = age;
            this.species = species;
            this.color = color;
            this.texture = texture;

            // Simulate expensive initialization for each tree
            simulateExpensiveInitialization();
        }

        private void simulateExpensiveInitialization() {
            try {
                Thread.sleep(1); // Much less than flyweight to show the difference
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        public void render(String season) {
            System.out.printf("Naive rendering %s tree at (%d,%d) in %s%n",
                    species, x, y, season);
        }
    }

    private final List<NaiveTree> trees = new ArrayList<>();

    public void addTree(int x, int y, int age, String species, String color, String texture) {
        trees.add(new NaiveTree(x, y, age, species, color, texture));
    }

    public int getTreeCount() {
        return trees.size();
    }
}

public class FlyweightPattern {
    public static void main(String[] args) {
        System.out.println("=== Flyweight Pattern Demo ===");

        // Create a forest using flyweight pattern
        Forest forest = new Forest();

        // Manually plant some trees
        System.out.println("\n--- Manual Tree Planting ---");
        forest.plantTree(10, 20, 5, "Oak", "green", "rough");
        forest.plantTree(30, 40, 8, "Oak", "green", "rough");    // Reuses flyweight
        forest.plantTree(50, 60, 3, "Pine", "dark-green", "smooth");
        forest.plantTree(70, 80, 12, "Oak", "green", "rough");   // Reuses flyweight again

        TreeTypeFactory.printCacheStatistics();

        // Generate a large forest
        System.out.println("\n--- Large Forest Generation ---");
        long startTime = System.currentTimeMillis();
        forest.generateRandomForest(10000);
        long endTime = System.currentTimeMillis();

        System.out.printf("Forest generation took %d ms%n", endTime - startTime);
        forest.printStatistics();
        TreeTypeFactory.printCacheStatistics();

        // Render the forest in different seasons
        forest.renderForest("spring");

        // Compare with naive implementation
        System.out.println("\n--- Comparison with Naive Implementation ---");
        NaiveTerrain naiveTerrain = new NaiveTerrain();

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {  // Smaller number to avoid long wait
            naiveTerrain.addTree(i, i, 5, "Oak", "green", "rough");
        }
        endTime = System.currentTimeMillis();

        System.out.printf("Naive implementation (1000 trees) took %d ms%n", endTime - startTime);
        System.out.printf("Each tree creates its own object with duplicate data%n");

        // Memory efficiency demonstration
        System.out.println("\n--- Memory Efficiency Analysis ---");
        System.out.println("Flyweight Pattern Benefits:");
        System.out.println("1. Shared intrinsic state (species, color, texture)");
        System.out.println("2. Unique extrinsic state (x, y, age) per tree");
        System.out.println("3. Significant memory savings for large numbers of similar objects");

        Runtime runtime = Runtime.getRuntime();
        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
        System.out.printf("Current memory usage: %.2f MB%n", usedMemory / (1024.0 * 1024.0));

        // Real-world applications
        System.out.println("\n--- Real-world Flyweight Applications ---");
        System.out.println("1. Game engines: Particles, terrain tiles, sprites");
        System.out.println("2. Text editors: Character formatting, font rendering");
        System.out.println("3. GUI frameworks: Icons, themes, styling");
        System.out.println("4. Databases: Connection pooling, cached queries");
        System.out.println("5. Web applications: Template caching, resource pooling");

        System.out.println("\n=== Flyweight Pattern Demo Complete ===");
    }
}