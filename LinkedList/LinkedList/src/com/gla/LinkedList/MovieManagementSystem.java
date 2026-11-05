package com.gla.LinkedList;

/**
 * Problem 2: Doubly Linked List - Movie Management System
 */
public class MovieManagementSystem {

    static class Movie {
        String title;
        String director;
        int year;
        double rating;
        Movie next, prev;

        Movie(String title, String director, int year, double rating) {
            this.title = title;
            this.director = director;
            this.year = year;
            this.rating = rating;
        }

        @Override
        public String toString() {
            return title + " | Director: " + director + " | Year: " + year + " | Rating: " + rating;
        }
    }

    private Movie head, tail;

    public void addAtBeginning(String t, String d, int y, double r) {
        Movie m = new Movie(t, d, y, r);
        if (head == null) {
            head = tail = m;
        } else {
            m.next = head;
            head.prev = m;
            head = m;
        }
    }

    public void addAtEnd(String t, String d, int y, double r) {
        Movie m = new Movie(t, d, y, r);
        if (tail == null) {
            head = tail = m;
        } else {
            tail.next = m;
            m.prev = tail;
            tail = m;
        }
    }

    /** position is 1-based */
    public void addAtPosition(int pos, String t, String d, int y, double r) {
        if (pos <= 1 || head == null) {
            addAtBeginning(t, d, y, r);
            return;
        }
        Movie temp = head;
        for (int i = 1; i < pos - 1 && temp.next != null; i++) temp = temp.next;
        if (temp == tail) {
            addAtEnd(t, d, y, r);
            return;
        }
        Movie m = new Movie(t, d, y, r);
        m.next = temp.next;
        m.prev = temp;
        temp.next.prev = m;
        temp.next = m;
    }

    public boolean removeByTitle(String title) {
        Movie temp = head;
        while (temp != null && !temp.title.equalsIgnoreCase(title)) temp = temp.next;
        if (temp == null) return false;

        if (temp == head) head = temp.next;
        else temp.prev.next = temp.next;

        if (temp == tail) tail = temp.prev;
        else temp.next.prev = temp.prev;

        temp.next = temp.prev = null;
        return true;
    }

    public void searchByDirector(String director) {
        boolean found = false;
        for (Movie m = head; m != null; m = m.next) {
            if (m.director.equalsIgnoreCase(director)) {
                System.out.println(m);
                found = true;
            }
        }
        if (!found) System.out.println("No movie found for director: " + director);
    }

    public void searchByRating(double rating) {
        boolean found = false;
        for (Movie m = head; m != null; m = m.next) {
            if (m.rating == rating) {
                System.out.println(m);
                found = true;
            }
        }
        if (!found) System.out.println("No movie found with rating: " + rating);
    }

    public void displayForward() {
        if (head == null) {
            System.out.println("No movies.");
            return;
        }
        for (Movie m = head; m != null; m = m.next) System.out.println(m);
    }

    public void displayReverse() {
        if (tail == null) {
            System.out.println("No movies.");
            return;
        }
        for (Movie m = tail; m != null; m = m.prev) System.out.println(m);
    }

    public boolean updateRating(String title, double newRating) {
        for (Movie m = head; m != null; m = m.next) {
            if (m.title.equalsIgnoreCase(title)) {
                m.rating = newRating;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        MovieManagementSystem list = new MovieManagementSystem();
        list.addAtEnd("Inception", "Christopher Nolan", 2010, 8.8);
        list.addAtEnd("Interstellar", "Christopher Nolan", 2014, 8.7);
        list.addAtBeginning("3 Idiots", "Rajkumar Hirani", 2009, 8.4);
        list.addAtPosition(2, "Dangal", "Nitesh Tiwari", 2016, 8.3);

        System.out.println("--- Forward ---");
        list.displayForward();
        System.out.println("\n--- Reverse ---");
        list.displayReverse();

        System.out.println("\n--- Search by director: Christopher Nolan ---");
        list.searchByDirector("Christopher Nolan");
        System.out.println("\n--- Search by rating 8.3 ---");
        list.searchByRating(8.3);

        System.out.println("\n--- Update rating of Dangal to 8.9 ---");
        System.out.println(list.updateRating("Dangal", 8.9) ? "Updated" : "Not found");

        System.out.println("\n--- Remove Inception ---");
        System.out.println(list.removeByTitle("Inception") ? "Removed" : "Not found");
        list.displayForward();
    }
}
