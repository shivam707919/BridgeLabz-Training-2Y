package com.gla.LinkedList;

import java.util.Comparator;

/**
 * Problem 4: Singly Linked List - Inventory Management System
 */
public class InventoryManagementSystem {

    static class Item {
        String name;
        int id;
        int quantity;
        double price;
        Item next;

        Item(String name, int id, int quantity, double price) {
            this.name = name;
            this.id = id;
            this.quantity = quantity;
            this.price = price;
        }

        @Override
        public String toString() {
            return "ID: " + id + " | " + name + " | Qty: " + quantity + " | Price: " + price;
        }
    }

    private Item head;

    public void addAtBeginning(String name, int id, int qty, double price) {
        Item n = new Item(name, id, qty, price);
        n.next = head;
        head = n;
    }

    public void addAtEnd(String name, int id, int qty, double price) {
        Item n = new Item(name, id, qty, price);
        if (head == null) {
            head = n;
            return;
        }
        Item temp = head;
        while (temp.next != null) temp = temp.next;
        temp.next = n;
    }

    /** position is 1-based */
    public void addAtPosition(int pos, String name, int id, int qty, double price) {
        if (pos <= 1 || head == null) {
            addAtBeginning(name, id, qty, price);
            return;
        }
        Item temp = head;
        for (int i = 1; i < pos - 1 && temp.next != null; i++) temp = temp.next;
        Item n = new Item(name, id, qty, price);
        n.next = temp.next;
        temp.next = n;
    }

    public boolean removeById(int id) {
        if (head == null) return false;
        if (head.id == id) {
            Item old = head;
            head = head.next;
            old.next = null;
            return true;
        }
        Item temp = head;
        while (temp.next != null && temp.next.id != id) temp = temp.next;
        if (temp.next == null) return false;
        Item old = temp.next;
        temp.next = old.next;
        old.next = null;
        return true;
    }

    public boolean updateQuantity(int id, int newQty) {
        for (Item i = head; i != null; i = i.next) {
            if (i.id == id) {
                i.quantity = newQty;
                return true;
            }
        }
        return false;
    }

    public Item searchById(int id) {
        for (Item i = head; i != null; i = i.next) if (i.id == id) return i;
        return null;
    }

    public Item searchByName(String name) {
        for (Item i = head; i != null; i = i.next) if (i.name.equalsIgnoreCase(name)) return i;
        return null;
    }

    public double totalValue() {
        double total = 0;
        for (Item i = head; i != null; i = i.next) total += i.price * i.quantity;
        return total;
    }

    public void display() {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }
        for (Item i = head; i != null; i = i.next) System.out.println(i);
    }

    // ---------- Merge sort on a linked list ----------

    /** sortBy: "name" or "price". */
    public void sort(String sortBy, boolean ascending) {
        Comparator<Item> cmp;
        if (sortBy.equalsIgnoreCase("name")) {
            cmp = (a, b) -> a.name.compareToIgnoreCase(b.name);
        } else {
            cmp = (a, b) -> Double.compare(a.price, b.price);
        }
        if (!ascending) cmp = cmp.reversed();
        head = mergeSort(head, cmp);
    }

    private Item mergeSort(Item h, Comparator<Item> cmp) {
        if (h == null || h.next == null) return h;
        Item mid = getMiddle(h);
        Item right = mid.next;
        mid.next = null;
        return merge(mergeSort(h, cmp), mergeSort(right, cmp), cmp);
    }

    private Item getMiddle(Item h) {
        Item slow = h, fast = h.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private Item merge(Item a, Item b, Comparator<Item> cmp) {
        Item dummy = new Item("", 0, 0, 0);
        Item tail = dummy;
        while (a != null && b != null) {
            if (cmp.compare(a, b) <= 0) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }
        tail.next = (a != null) ? a : b;
        return dummy.next;
    }

    public static void main(String[] args) {
        InventoryManagementSystem inv = new InventoryManagementSystem();
        inv.addAtEnd("Keyboard", 1, 10, 750.0);
        inv.addAtEnd("Mouse", 2, 25, 350.0);
        inv.addAtBeginning("Monitor", 3, 5, 8500.0);
        inv.addAtPosition(2, "Cable", 4, 40, 120.0);

        System.out.println("--- Inventory ---");
        inv.display();
        System.out.println("\nTotal value: " + inv.totalValue());

        System.out.println("\n--- Update quantity of ID 2 to 30 ---");
        System.out.println(inv.updateQuantity(2, 30) ? "Updated" : "Not found");

        System.out.println("\n--- Search ---");
        System.out.println("By ID 3: " + inv.searchById(3));
        System.out.println("By name 'mouse': " + inv.searchByName("mouse"));

        System.out.println("\n--- Sorted by name (ascending) ---");
        inv.sort("name", true);
        inv.display();

        System.out.println("\n--- Sorted by price (descending) ---");
        inv.sort("price", false);
        inv.display();

        System.out.println("\n--- Remove ID 4 ---");
        System.out.println(inv.removeById(4) ? "Removed" : "Not found");
        inv.display();
        System.out.println("Total value: " + inv.totalValue());
    }
}
