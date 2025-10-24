// Go to Java - Stage 5.2: Lambda Expressions, Streams, and Optional
// Coming from Go: Similar to anonymous functions and pipelines

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

// Data class for demonstrations
class Player {
    private final String name;
    private final int level;
    private final double score;
    private final String guild;
    private final boolean active;

    public Player(String name, int level, double score, String guild, boolean active) {
        this.name = name;
        this.level = level;
        this.score = score;
        this.guild = guild;
        this.active = active;
    }

    // Getters
    public String getName() { return name; }
    public int getLevel() { return level; }
    public double getScore() { return score; }
    public String getGuild() { return guild; }
    public boolean isActive() { return active; }

    @Override
    public String toString() {
        return String.format("%s (L%d, %.1f, %s)%s",
                name, level, score, guild, active ? "" : " [INACTIVE]");
    }
}

public class FunctionalFeatures {

    // Method demonstrating different functional interfaces
    public static void demonstrateFunctionalInterfaces() {
        System.out.println("\n--- Functional Interfaces ---");

        // Predicate<T> - takes T, returns boolean
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<String> isLongName = name -> name.length() > 5;

        System.out.println("Is 4 even? " + isEven.test(4));
        System.out.println("Is 'Alexander' a long name? " + isLongName.test("Alexander"));

        // Function<T, R> - takes T, returns R
        Function<String, Integer> stringLength = s -> s.length();
        Function<Integer, String> intToHex = n -> Integer.toHexString(n);

        System.out.println("Length of 'Hello': " + stringLength.apply("Hello"));
        System.out.println("255 in hex: " + intToHex.apply(255));

        // Consumer<T> - takes T, returns void
        Consumer<String> printer = s -> System.out.println("Processing: " + s);
        List<String> items = Arrays.asList("apple", "banana", "cherry");
        items.forEach(printer);

        // Supplier<T> - takes nothing, returns T
        Supplier<Double> randomScore = () -> Math.random() * 100;
        System.out.println("Random score: " + randomScore.get());

        // Combining predicates
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<Integer> isSmall = n -> n < 100;
        Predicate<Integer> isPositiveAndSmall = isPositive.and(isSmall);

        System.out.println("Is 50 positive and small? " + isPositiveAndSmall.test(50));
        System.out.println("Is 150 positive and small? " + isPositiveAndSmall.test(150));
    }

    public static void demonstrateStreams() {
        System.out.println("\n--- Streams API ---");

        // Sample data
        List<Player> players = Arrays.asList(
            new Player("Alice", 25, 1500.5, "Dragons", true),
            new Player("Bob", 30, 2100.0, "Phoenix", true),
            new Player("Charlie", 15, 800.0, "Dragons", false),
            new Player("Diana", 40, 3200.5, "Phoenix", true),
            new Player("Eve", 20, 1200.0, "Wolves", true),
            new Player("Frank", 35, 2800.0, "Wolves", false),
            new Player("Grace", 28, 1800.5, "Dragons", true)
        );

        // Basic stream operations
        System.out.println("All players:");
        players.stream()
                .forEach(System.out::println);  // method reference

        // Filtering
        System.out.println("\nActive players only:");
        players.stream()
                .filter(Player::isActive)  // method reference
                .forEach(System.out::println);

        // Mapping (transformation)
        System.out.println("\nPlayer names and levels:");
        players.stream()
                .map(p -> p.getName() + " (L" + p.getLevel() + ")")
                .forEach(System.out::println);

        // Filtering and mapping combined
        System.out.println("\nHigh-level active players:");
        List<String> highLevelPlayers = players.stream()
                .filter(Player::isActive)
                .filter(p -> p.getLevel() >= 25)
                .map(Player::getName)
                .collect(Collectors.toList());
        System.out.println(highLevelPlayers);

        // Sorting
        System.out.println("\nPlayers sorted by score (descending):");
        players.stream()
                .sorted((p1, p2) -> Double.compare(p2.getScore(), p1.getScore()))
                // Alternative: .sorted(Comparator.comparing(Player::getScore).reversed())
                .limit(3)  // top 3
                .forEach(System.out::println);

        // Grouping
        System.out.println("\nPlayers by guild:");
        Map<String, List<Player>> playersByGuild = players.stream()
                .collect(Collectors.groupingBy(Player::getGuild));
        playersByGuild.forEach((guild, guildPlayers) -> {
            System.out.println(guild + ": " + guildPlayers.size() + " players");
        });

        // Aggregations
        System.out.println("\nStatistics:");
        double averageScore = players.stream()
                .filter(Player::isActive)
                .mapToDouble(Player::getScore)
                .average()
                .orElse(0.0);
        System.out.println("Average score of active players: " + averageScore);

        int totalLevel = players.stream()
                .filter(Player::isActive)
                .mapToInt(Player::getLevel)
                .sum();
        System.out.println("Total level of active players: " + totalLevel);

        // Finding elements
        Optional<Player> topPlayer = players.stream()
                .filter(Player::isActive)
                .max(Comparator.comparing(Player::getScore));
        topPlayer.ifPresent(p -> System.out.println("Top active player: " + p));

        // Complex pipeline
        System.out.println("\nComplex pipeline - Active Dragons with high scores:");
        Map<String, Double> result = players.stream()
                .filter(Player::isActive)
                .filter(p -> "Dragons".equals(p.getGuild()))
                .filter(p -> p.getScore() > 1000)
                .collect(Collectors.toMap(
                    Player::getName,
                    Player::getScore
                ));
        System.out.println(result);

        // Parallel streams for large datasets
        System.out.println("\nParallel processing example:");
        long startTime = System.currentTimeMillis();
        long count = players.parallelStream()  // parallel processing
                .filter(Player::isActive)
                .filter(p -> p.getScore() > 1000)
                .count();
        long endTime = System.currentTimeMillis();
        System.out.println("Found " + count + " players in " + (endTime - startTime) + "ms");
    }

    public static void demonstrateOptional() {
        System.out.println("\n--- Optional Class ---");

        // Creating Optional instances
        Optional<String> present = Optional.of("Hello");
        Optional<String> empty = Optional.empty();
        Optional<String> nullable = Optional.ofNullable(null);

        System.out.println("Present: " + present.isPresent());
        System.out.println("Empty: " + empty.isPresent());
        System.out.println("Nullable: " + nullable.isPresent());

        // Safe value extraction
        System.out.println("Value if present: " + present.orElse("default"));
        System.out.println("Value if empty: " + empty.orElse("default"));

        // Using ifPresent instead of null checks
        present.ifPresent(value -> System.out.println("Processing: " + value));
        empty.ifPresent(value -> System.out.println("This won't print"));

        // Chaining operations
        Optional<String> result = present
                .filter(s -> s.length() > 3)
                .map(String::toUpperCase)
                .map(s -> s + "!");

        result.ifPresent(s -> System.out.println("Transformed: " + s));

        // Optional with custom objects
        List<Player> players = Arrays.asList(
            new Player("Alice", 25, 1500.5, "Dragons", true),
            new Player("Bob", 30, 2100.0, "Phoenix", true)
        );

        // Finding a player (returns Optional)
        Optional<Player> foundPlayer = players.stream()
                .filter(p -> p.getName().equals("Alice"))
                .findFirst();

        foundPlayer.ifPresentOrElse(
            player -> System.out.println("Found player: " + player),
            () -> System.out.println("Player not found")
        );

        // Optional chaining to avoid null pointer exceptions
        String playerGuild = foundPlayer
                .map(Player::getGuild)
                .orElse("No Guild");
        System.out.println("Player guild: " + playerGuild);

        // Optional with exceptions
        Optional<Player> notFound = players.stream()
                .filter(p -> p.getName().equals("NonExistent"))
                .findFirst();

        try {
            Player player = notFound.orElseThrow(() ->
                new IllegalStateException("Player not found"));
        } catch (IllegalStateException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }

    // Method demonstrating method references
    public static void demonstrateMethodReferences() {
        System.out.println("\n--- Method References ---");

        List<String> names = Arrays.asList("alice", "bob", "charlie", "diana");

        // Lambda vs method reference
        System.out.println("Using lambda:");
        names.stream()
                .map(s -> s.toUpperCase())
                .forEach(s -> System.out.println(s));

        System.out.println("Using method references:");
        names.stream()
                .map(String::toUpperCase)     // instance method reference
                .forEach(System.out::println); // static method reference

        // Constructor reference
        List<Integer> lengths = names.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println("Name lengths: " + lengths);

        // Static method reference
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> max = numbers.stream()
                .max(Integer::compareTo);  // static method reference
        System.out.println("Max number: " + max.orElse(0));
    }

    public static void main(String[] args) {
        System.out.println("=== Java Functional Features ===");

        demonstrateFunctionalInterfaces();
        demonstrateStreams();
        demonstrateOptional();
        demonstrateMethodReferences();

        // Real-world example: Data processing pipeline
        System.out.println("\n--- Real-world Data Processing Pipeline ---");

        List<Player> allPlayers = Arrays.asList(
            new Player("Alice", 25, 1500.5, "Dragons", true),
            new Player("Bob", 30, 2100.0, "Phoenix", true),
            new Player("Charlie", 15, 800.0, "Dragons", false),
            new Player("Diana", 40, 3200.5, "Phoenix", true),
            new Player("Eve", 20, 1200.0, "Wolves", true),
            new Player("Frank", 35, 2800.0, "Wolves", false),
            new Player("Grace", 28, 1800.5, "Dragons", true),
            new Player("Henry", 22, 1100.0, "Phoenix", true),
            new Player("Iris", 33, 2400.0, "Wolves", true),
            new Player("Jack", 18, 950.0, "Dragons", false)
        );

        // Complex business logic using functional programming
        Map<String, Double> guildAverageScores = allPlayers.stream()
                .filter(Player::isActive)                    // only active players
                .filter(p -> p.getLevel() >= 20)             // experienced players only
                .collect(Collectors.groupingBy(               // group by guild
                    Player::getGuild,
                    Collectors.averagingDouble(Player::getScore) // average score per guild
                ));

        System.out.println("Average scores by guild (active players L20+):");
        guildAverageScores.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(entry -> System.out.printf("%s: %.1f%n",
                    entry.getKey(), entry.getValue()));

        System.out.println("\n=== Functional Features Demo Complete ===");
    }
}