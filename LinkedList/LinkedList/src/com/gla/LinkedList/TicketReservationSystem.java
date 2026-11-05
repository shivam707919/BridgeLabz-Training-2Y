package com.gla.LinkedList;

/**
 * Problem 9: Circular Linked List - Online Ticket Reservation System
 */
public class TicketReservationSystem {

    static class Ticket {
        int ticketId;
        String customerName;
        String movieName;
        String seatNumber;
        String bookingTime;
        Ticket next;

        Ticket(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
            this.ticketId = ticketId;
            this.customerName = customerName;
            this.movieName = movieName;
            this.seatNumber = seatNumber;
            this.bookingTime = bookingTime;
        }

        @Override
        public String toString() {
            return "Ticket #" + ticketId + " | " + customerName + " | " + movieName
                    + " | Seat: " + seatNumber + " | Booked at: " + bookingTime;
        }
    }

    private Ticket head, tail;
    private int count;

    public void addTicket(int id, String customer, String movie, String seat, String time) {
        Ticket t = new Ticket(id, customer, movie, seat, time);
        if (head == null) {
            head = tail = t;
            t.next = t;
        } else {
            tail.next = t;
            t.next = head;
            tail = t;
        }
        count++;
    }

    public boolean removeTicket(int id) {
        if (head == null) return false;

        if (count == 1) {
            if (head.ticketId != id) return false;
            head = tail = null;
            count = 0;
            return true;
        }

        Ticket prev = tail, cur = head;
        do {
            if (cur.ticketId == id) {
                prev.next = cur.next;
                if (cur == head) head = cur.next;
                if (cur == tail) tail = prev;
                cur.next = null;
                count--;
                return true;
            }
            prev = cur;
            cur = cur.next;
        } while (cur != head);
        return false;
    }

    public void display() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }
        Ticket temp = head;
        do {
            System.out.println(temp);
            temp = temp.next;
        } while (temp != head);
    }

    public void searchByCustomer(String name) {
        search(name, true);
    }

    public void searchByMovie(String movie) {
        search(movie, false);
    }

    private void search(String key, boolean byCustomer) {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }
        boolean found = false;
        Ticket temp = head;
        do {
            String field = byCustomer ? temp.customerName : temp.movieName;
            if (field.equalsIgnoreCase(key)) {
                System.out.println(temp);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);
        if (!found) System.out.println("No ticket found for: " + key);
    }

    public int totalTickets() {
        return count;
    }

    public static void main(String[] args) {
        TicketReservationSystem sys = new TicketReservationSystem();
        sys.addTicket(1001, "Aman", "Inception", "A1", "10:00");
        sys.addTicket(1002, "Riya", "Interstellar", "B4", "10:15");
        sys.addTicket(1003, "Karan", "Inception", "A2", "10:30");
        sys.addTicket(1004, "Neha", "Dangal", "C7", "11:00");

        System.out.println("--- All tickets ---");
        sys.display();
        System.out.println("Total booked: " + sys.totalTickets());

        System.out.println("\n--- Search by customer: Riya ---");
        sys.searchByCustomer("Riya");
        System.out.println("\n--- Search by movie: Inception ---");
        sys.searchByMovie("Inception");

        System.out.println("\n--- Remove 1001 (head) and 1004 (tail) ---");
        System.out.println(sys.removeTicket(1001) + " " + sys.removeTicket(1004));
        sys.display();
        System.out.println("Total booked: " + sys.totalTickets());
    }
}
