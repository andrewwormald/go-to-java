import java.util.*;

class Book {
    private final String title;
    private final String author;
    private final String isbn;
    private final String genre;
    private final int year;

    public Book(String title, String author, String isbn, String genre, int year) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.year = year;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public String getGenre() { return genre; }
    public int getYear() { return year; }

    @Override
    public String toString() {
        return title + " by " + author;
    }
}

public class Solution {
    private List<Book> books;
    private Map<String, Book> isbnLookup;
    private Set<String> uniqueAuthors;
    private Map<String, Integer> genreCounts;

    public Solution() {
        this.books = new ArrayList<>();
        this.isbnLookup = new HashMap<>();
        this.uniqueAuthors = new HashSet<>();
        this.genreCounts = new TreeMap<>(); // TreeMap for sorted keys
    }

    public void addBook(Book book) {
        // Add to list
        books.add(book);

        // Add to ISBN lookup
        isbnLookup.put(book.getIsbn(), book);

        // Add author to unique set
        uniqueAuthors.add(book.getAuthor());

        // Update genre count
        genreCounts.put(book.getGenre(),
            genreCounts.getOrDefault(book.getGenre(), 0) + 1);

        System.out.println("Added: " + book);
    }

    public Book findByIsbn(String isbn) {
        return isbnLookup.get(isbn);
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public Set<String> getUniqueAuthors() {
        return new HashSet<>(uniqueAuthors); // Return defensive copy
    }

    public Map<String, Integer> getGenreCounts() {
        return new TreeMap<>(genreCounts); // Return defensive copy
    }

    public int getTotalBooks() {
        return books.size();
    }
}