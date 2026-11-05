package com.gla.LinkedList;

/**
 * Problem 8: Doubly Linked List - Undo/Redo Functionality for Text Editor
 * prev = undo history, next = redo history. History is capped at MAX_HISTORY states.
 */
public class UndoRedoTextEditor {

    static class State {
        String text;
        State next, prev;

        State(String text) {
            this.text = text;
        }
    }

    private final int maxHistory;
    private State head, tail, current;
    private int size;

    public UndoRedoTextEditor(int maxHistory) {
        this.maxHistory = maxHistory;
    }

    /** Called every time the user types or performs an action. */
    public void addState(String text) {
        State s = new State(text);
        if (current == null) {
            head = tail = current = s;
            size = 1;
            return;
        }
        // A new action invalidates any redo history after the current state
        current.next = null;
        tail = current;

        tail.next = s;
        s.prev = tail;
        tail = s;
        current = s;
        recount();

        // Keep only the last maxHistory states
        while (size > maxHistory) {
            State old = head;
            head = head.next;
            head.prev = null;
            old.next = null;
            size--;
        }
    }

    public void undo() {
        if (current == null || current.prev == null) {
            System.out.println("Nothing to undo.");
            return;
        }
        current = current.prev;
    }

    public void redo() {
        if (current == null || current.next == null) {
            System.out.println("Nothing to redo.");
            return;
        }
        current = current.next;
    }

    public void displayCurrent() {
        System.out.println("Current text: \"" + (current == null ? "" : current.text) + "\"");
    }

    public void displayHistory() {
        StringBuilder sb = new StringBuilder("History: ");
        for (State s = head; s != null; s = s.next) {
            sb.append(s == current ? "[" + s.text + "]" : s.text).append(" -> ");
        }
        System.out.println(sb.substring(0, Math.max(0, sb.length() - 4)));
    }

    private void recount() {
        size = 0;
        for (State s = head; s != null; s = s.next) size++;
    }

    public static void main(String[] args) {
        UndoRedoTextEditor editor = new UndoRedoTextEditor(10);
        editor.addState("");
        editor.addState("Hello");
        editor.addState("Hello World");
        editor.addState("Hello World!");
        editor.displayCurrent();
        editor.displayHistory();

        System.out.println("\n--- Undo x2 ---");
        editor.undo();
        editor.undo();
        editor.displayCurrent();

        System.out.println("\n--- Redo x1 ---");
        editor.redo();
        editor.displayCurrent();

        System.out.println("\n--- New action after undo (redo history is discarded) ---");
        editor.addState("Hello Java");
        editor.displayCurrent();
        editor.redo();
        editor.displayHistory();

        System.out.println("\n--- History limit demo (limit = 3) ---");
        UndoRedoTextEditor small = new UndoRedoTextEditor(3);
        for (String t : new String[]{"a", "ab", "abc", "abcd", "abcde"}) small.addState(t);
        small.displayHistory();
        small.undo();
        small.undo();
        small.undo();
        small.displayCurrent();
    }
}
