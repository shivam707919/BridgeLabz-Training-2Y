package com.gla.LinkedList;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem 7: Singly Linked List - Social Media Friend Connections
 */
public class SocialMediaFriendConnections {

    static class User {
        int id;
        String name;
        int age;
        List<Integer> friendIds = new ArrayList<>();
        User next;

        User(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }
    }

    private User head;

    public void addUser(int id, String name, int age) {
        User u = new User(id, name, age);
        if (head == null) {
            head = u;
            return;
        }
        User temp = head;
        while (temp.next != null) temp = temp.next;
        temp.next = u;
    }

    public User searchById(int id) {
        for (User u = head; u != null; u = u.next) if (u.id == id) return u;
        return null;
    }

    public User searchByName(String name) {
        for (User u = head; u != null; u = u.next) if (u.name.equalsIgnoreCase(name)) return u;
        return null;
    }

    public boolean addFriend(int id1, int id2) {
        User a = searchById(id1), b = searchById(id2);
        if (a == null || b == null || id1 == id2) return false;
        if (!a.friendIds.contains(id2)) a.friendIds.add(id2);
        if (!b.friendIds.contains(id1)) b.friendIds.add(id1);
        return true;
    }

    public boolean removeFriend(int id1, int id2) {
        User a = searchById(id1), b = searchById(id2);
        if (a == null || b == null) return false;
        boolean r1 = a.friendIds.remove(Integer.valueOf(id2));
        boolean r2 = b.friendIds.remove(Integer.valueOf(id1));
        return r1 || r2;
    }

    public List<Integer> mutualFriends(int id1, int id2) {
        List<Integer> mutual = new ArrayList<>();
        User a = searchById(id1), b = searchById(id2);
        if (a == null || b == null) return mutual;
        for (int f : a.friendIds) if (b.friendIds.contains(f)) mutual.add(f);
        return mutual;
    }

    public void displayFriends(int id) {
        User u = searchById(id);
        if (u == null) {
            System.out.println("User not found.");
            return;
        }
        System.out.print("Friends of " + u.name + ": ");
        if (u.friendIds.isEmpty()) {
            System.out.println("none");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int fid : u.friendIds) {
            User f = searchById(fid);
            sb.append(f != null ? f.name : "?").append("(").append(fid).append(") ");
        }
        System.out.println(sb.toString().trim());
    }

    public void countFriendsForAll() {
        for (User u = head; u != null; u = u.next) {
            System.out.println(u.name + " (ID " + u.id + ") has " + u.friendIds.size() + " friend(s)");
        }
    }

    public static void main(String[] args) {
        SocialMediaFriendConnections net = new SocialMediaFriendConnections();
        net.addUser(1, "Aman", 20);
        net.addUser(2, "Riya", 21);
        net.addUser(3, "Karan", 19);
        net.addUser(4, "Neha", 22);

        net.addFriend(1, 2);
        net.addFriend(1, 3);
        net.addFriend(2, 3);
        net.addFriend(2, 4);

        System.out.println("--- Friends ---");
        net.displayFriends(1);
        net.displayFriends(2);

        System.out.println("\n--- Mutual friends of Aman(1) and Riya(2) ---");
        System.out.println(net.mutualFriends(1, 2));

        System.out.println("\n--- Search ---");
        User u = net.searchByName("neha");
        System.out.println(u != null ? "Found: " + u.name + ", ID " + u.id + ", age " + u.age : "Not found");
        u = net.searchById(9);
        System.out.println(u != null ? "Found: " + u.name : "User ID 9 not found");

        System.out.println("\n--- Remove friendship 1-3 ---");
        System.out.println(net.removeFriend(1, 3) ? "Removed" : "Not friends");
        net.displayFriends(1);

        System.out.println("\n--- Friend counts ---");
        net.countFriendsForAll();
    }
}
