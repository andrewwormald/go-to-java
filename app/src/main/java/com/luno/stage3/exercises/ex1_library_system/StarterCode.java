package com.luno.stage3.exercises.ex1_library_system;

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

    // Getters
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

public class StarterCode {
    // TODO: Add these fields
    // private List<Book> books;
    // private Map<String, Book> isbnLookup;
    // private Set<String> uniqueAuthors;
    // private Map<String, Integer> genreCounts;

    public StarterCode() {
        // TODO: Initialize all collections
    }

    public void addBook(Book book) {
        // TODO: Add book to all collections
        // - Add to books list
        // - Add to ISBN lookup map
        // - Add author to unique authors set
        // - Update genre count in TreeMap
        System.out.println("Added: " + book);
    }

    public Book findByIsbn(String isbn) {
        // TODO: Return book by ISBN or null if not found
        return null;
    }

    public List<Book> findBooksByAuthor(String author) {
        // TODO: Return all books by given author
        return new ArrayList<>();
    }

    public Set<String> getUniqueAuthors() {
        // TODO: Return set of all unique authors
        return new HashSet<>();
    }

    public Map<String, Integer> getGenreCounts() {
        // TODO: Return map of genre -> count (sorted by genre name)
        return new TreeMap<>();
    }

    // Helper method for testing
    public int getTotalBooks() {
        // TODO: Return total number of books
        return 0;
    }
}