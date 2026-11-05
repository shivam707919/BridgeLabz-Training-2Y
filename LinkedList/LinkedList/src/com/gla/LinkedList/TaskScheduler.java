package com.gla.LinkedList;

/**
 * Problem 3: Circular Linked List - Task Scheduler
 */
public class TaskScheduler {

    static class Task {
        int id;
        String name;
        int priority;
        String dueDate;
        Task next;

        Task(int id, String name, int priority, String dueDate) {
            this.id = id;
            this.name = name;
            this.priority = priority;
            this.dueDate = dueDate;
        }

        @Override
        public String toString() {
            return "Task ID: " + id + " | " + name + " | Priority: " + priority + " | Due: " + dueDate;
        }
    }

    private Task head, tail, current;
    private int size;

    public void addAtBeginning(int id, String name, int priority, String due) {
        Task t = new Task(id, name, priority, due);
        if (head == null) {
            head = tail = current = t;
            t.next = t;
        } else {
            t.next = head;
            tail.next = t;
            head = t;
        }
        size++;
    }

    public void addAtEnd(int id, String name, int priority, String due) {
        Task t = new Task(id, name, priority, due);
        if (head == null) {
            head = tail = current = t;
            t.next = t;
        } else {
            tail.next = t;
            t.next = head;
            tail = t;
        }
        size++;
    }

    /** position is 1-based */
    public void addAtPosition(int pos, int id, String name, int priority, String due) {
        if (pos <= 1 || head == null) {
            addAtBeginning(id, name, priority, due);
            return;
        }
        if (pos > size) {
            addAtEnd(id, name, priority, due);
            return;
        }
        Task temp = head;
        for (int i = 1; i < pos - 1; i++) temp = temp.next;
        Task t = new Task(id, name, priority, due);
        t.next = temp.next;
        temp.next = t;
        size++;
    }

    public boolean removeById(int id) {
        if (head == null) return false;

        if (size == 1) {
            if (head.id != id) return false;
            head = tail = current = null;
            size = 0;
            return true;
        }

        Task prev = tail, cur = head;
        do {
            if (cur.id == id) {
                prev.next = cur.next;
                if (cur == head) head = cur.next;
                if (cur == tail) tail = prev;
                if (cur == current) current = cur.next;
                cur.next = null;
                size--;
                return true;
            }
            prev = cur;
            cur = cur.next;
        } while (cur != head);
        return false;
    }

    /** Shows the current task, then moves to the next one. */
    public void viewCurrentAndMoveNext() {
        if (current == null) {
            System.out.println("No tasks.");
            return;
        }
        System.out.println("Current -> " + current);
        current = current.next;
        System.out.println("Moved to next task: " + current.name);
    }

    public void display() {
        if (head == null) {
            System.out.println("No tasks.");
            return;
        }
        Task temp = head;
        do {
            System.out.println(temp);
            temp = temp.next;
        } while (temp != head);
    }

    public void searchByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks.");
            return;
        }
        boolean found = false;
        Task temp = head;
        do {
            if (temp.priority == priority) {
                System.out.println(temp);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);
        if (!found) System.out.println("No task with priority " + priority);
    }

    public static void main(String[] args) {
        TaskScheduler s = new TaskScheduler();
        s.addAtEnd(1, "Write report", 2, "10-11-2026");
        s.addAtEnd(2, "Prepare slides", 1, "12-11-2026");
        s.addAtBeginning(3, "Email client", 3, "08-11-2026");
        s.addAtPosition(2, 4, "Fix bug #42", 1, "09-11-2026");

        System.out.println("--- All tasks ---");
        s.display();

        System.out.println("\n--- Cycling through tasks (6 times) ---");
        for (int i = 0; i < 6; i++) s.viewCurrentAndMoveNext();

        System.out.println("\n--- Search priority 1 ---");
        s.searchByPriority(1);

        System.out.println("\n--- Remove task 4 and 99 ---");
        System.out.println("Remove 4: " + s.removeById(4));
        System.out.println("Remove 99: " + s.removeById(99));
        s.display();
    }
}
