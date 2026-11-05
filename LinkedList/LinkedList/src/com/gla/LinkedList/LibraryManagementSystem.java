package com.gla.LinkedList;

/**
 * Problem 5: Doubly Linked List - Library Management System
 */
public class LibraryManagementSystem {

    static class Book {
        String title;
        String author;
        String genre;
        int id;
        boolean available;
        Book next, prev;

        Book(String title, String author, String genre, int id, boolean available) {
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.id = id;
            this.available = available;
        }

        @Override
        public String toString() {
            return "ID: " + id + " | " + title + " | " + author + " | " + genre
                    + " | " + (available ? "Available" : "Issued");
        }
    }

    private Book head, tail;
    private int count;

    public void addAtBeginning(String t, String a, String g, int id, boolean av) {
        Book b = new Book(t, a, g, id, av);
        if (head == null) {
            head = tail = b;
        } else {
            b.next = head;
            head.prev = b;
            head = b;
        }
        count++;
    }

    public void addAtEnd(String t, String a, String g, int id, boolean av) {
        Book b = new Book(t, a, g, id, av);
        if (tail == null) {
            head = tail = b;
        } else {
            tail.next = b;
            b.prev = tail;
            tail = b;
        }
        count++;
    }

    /** position is 1-based */
    public void addAtPosition(int pos, String t, String a, String g, int id, boolean av) {
        if (pos <= 1 || head == null) {
            addAtBeginning(t, a, g, id, av);
            return;
        }
        if (pos > count) {
            addAtEnd(t, a, g, id, av);
            return;
        }
        Book temp = head;
        for (int i = 1; i < pos - 1; i++) temp = temp.next;
        Book b = new Book(t, a, g, id, av);
        b.next = temp.next;
        b.prev = temp;
        temp.next.prev = b;
        temp.next = b;
        count++;
    }

    public boolean removeById(int id) {
        Book temp = head;
        while (temp != null && temp.id != id) temp = temp.next;
        if (temp == null) return false;

        if (temp == head) head = temp.next;
        else temp.prev.next = temp.next;

        if (temp == tail) tail = temp.prev;
        else temp.next.prev = temp.prev;

        temp.next = temp.prev = null;
        count--;
        return true;
    }

    public void searchByTitle(String title) {
        boolean found = false;
        for (Book b = head; b != null; b = b.next) {
            if (b.title.equalsIgnoreCase(title)) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) System.out.println("No book titled '" + title + "'");
    }

    public void searchByAuthor(String author) {
        boolean found = false;
        for (Book b = head; b != null; b = b.next) {
            if (b.author.equalsIgnoreCase(author)) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) System.out.println("No book by author '" + author + "'");
    }

    public boolean updateAvailability(int id, boolean available) {
        for (Book b = head; b != null; b = b.next) {
            if (b.id == id) {
                b.available = available;
                return true;
            }
        }
        return false;
    }

    public void displayForward() {
        if (head == null) {
            System.out.println("Library is empty.");
            return;
        }
        for (Book b = head; b != null; b = b.next) System.out.println(b);
    }

    public void displayReverse() {
        if (tail == null) {
            System.out.println("Library is empty.");
            return;
        }
        for (Book b = tail; b != null; b = b.prev) System.out.println(b);
    }

    public int countBooks() {
        return count;
    }

    public static void main(String[] args) {
        LibraryManagementSystem lib = new LibraryManagementSystem();
        lib.addAtEnd("Clean Code", "Robert Martin", "Programming", 1, true);
        lib.addAtEnd("The Pragmatic Programmer", "Andrew Hunt", "Programming", 2, true);
        lib.addAtBeginning("Wings of Fire", "A.P.J. Abdul Kalam", "Autobiography", 3, true);
        lib.addAtPosition(2, "Effective Java", "Joshua Bloch", "Programming", 4, false);

        System.out.println("--- Forward ---");
        lib.displayForward();
        System.out.println("\n--- Reverse ---");
        lib.displayReverse();

        System.out.println("\n--- Search by title: Clean Code ---");
        lib.searchByTitle("Clean Code");
        System.out.println("\n--- Search by author: Joshua Bloch ---");
        lib.searchByAuthor("Joshua Bloch");

        System.out.println("\n--- Update availability of ID 4 -> available ---");
        System.out.println(lib.updateAvailability(4, true) ? "Updated" : "Not found");

        System.out.println("\n--- Remove ID 2 ---");
        System.out.println(lib.removeById(2) ? "Removed" : "Not found");
        lib.displayForward();

        System.out.println("\nTotal books: " + lib.countBooks());
    }
}
