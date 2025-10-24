public class ExampleTest {
    public static void main(String[] args) {
        System.out.println("=== Library System Test ===");

        StarterCode library = new StarterCode();

        // Test data
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "978-0-7432-7356-5", "Classic", 1925);
        Book book2 = new Book("1984", "George Orwell", "978-0-452-28423-4", "Dystopian", 1949);
        Book book3 = new Book("To Kill a Mockingbird", "Harper Lee", "978-0-06-112008-4", "Classic", 1960);

        // Add books
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        System.out.println();

        // Test functionality
        boolean allPassed = true;

        // Test 1: Total books
        if (library.getTotalBooks() == 3) {
            System.out.println("✅ Book count test passed");
        } else {
            System.out.println("❌ Book count test failed: expected 3, got " + library.getTotalBooks());
            allPassed = false;
        }

        // Test 2: ISBN lookup
        Book found = library.findByIsbn("978-0-452-28423-4");
        if (found != null && found.getTitle().equals("1984")) {
            System.out.println("✅ ISBN lookup test passed");
        } else {
            System.out.println("❌ ISBN lookup test failed");
            allPassed = false;
        }

        // Test 3: Books by author
        var orwellBooks = library.findBooksByAuthor("George Orwell");
        if (orwellBooks.size() == 1 && orwellBooks.get(0).getTitle().equals("1984")) {
            System.out.println("✅ Author search test passed");
            System.out.println("Books by Orwell: " + orwellBooks);
        } else {
            System.out.println("❌ Author search test failed");
            allPassed = false;
        }

        // Test 4: Unique authors
        var authors = library.getUniqueAuthors();
        if (authors.size() == 3 && authors.contains("George Orwell")) {
            System.out.println("✅ Unique authors test passed");
            System.out.println("Unique authors: " + authors);
        } else {
            System.out.println("❌ Unique authors test failed");
            allPassed = false;
        }

        // Test 5: Genre counts
        var genres = library.getGenreCounts();
        if (genres.get("Classic") != null && genres.get("Classic") == 2) {
            System.out.println("✅ Genre counts test passed");
            System.out.println("Books by genre: " + genres);
        } else {
            System.out.println("❌ Genre counts test failed");
            allPassed = false;
        }

        System.out.println();
        if (allPassed) {
            System.out.println("🎉 All tests passed!");
        } else {
            System.out.println("❌ Some tests failed. Check your implementation.");
        }
    }
}