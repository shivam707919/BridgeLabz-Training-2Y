package com.gla.LinkedList;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem 6: Circular Linked List - Round Robin Scheduling Algorithm
 * (All processes are assumed to arrive at time 0.)
 */
public class RoundRobinScheduler {

    static class Process {
        int id;
        int burstTime;
        int remaining;
        int priority;
        int completionTime;
        Process next;

        Process(int id, int burstTime, int priority) {
            this.id = id;
            this.burstTime = burstTime;
            this.remaining = burstTime;
            this.priority = priority;
        }
    }

    private Process head, tail, current;
    private int size;
    private final List<Process> finished = new ArrayList<>();

    public void addProcess(int id, int burstTime, int priority) {
        Process p = new Process(id, burstTime, priority);
        if (head == null) {
            head = tail = current = p;
            p.next = p;
        } else {
            tail.next = p;
            p.next = head;
            tail = p;
        }
        size++;
    }

    /** Removes a process by ID (used after its execution). Moves 'current' forward if needed. */
    public boolean removeProcess(int id) {
        if (head == null) return false;
        if (size == 1) {
            if (head.id != id) return false;
            head = tail = current = null;
            size = 0;
            return true;
        }
        Process prev = tail, cur = head;
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

    public void displayQueue() {
        if (head == null) {
            System.out.println("  Queue: (empty)");
            return;
        }
        StringBuilder sb = new StringBuilder("  Queue: ");
        Process temp = head;
        do {
            sb.append("[P").append(temp.id)
              .append(" rem=").append(temp.remaining)
              .append(" pr=").append(temp.priority).append("] ");
            temp = temp.next;
        } while (temp != head);
        System.out.println(sb);
    }

    public void simulate(int quantum) {
        int time = 0, round = 1;
        while (size > 0) {
            System.out.println("Round " + round + ":");
            int turns = size;                     // one full pass over the current queue
            for (int i = 0; i < turns; i++) {
                Process p = current;
                int run = Math.min(quantum, p.remaining);
                System.out.println("  t=" + time + " -> P" + p.id + " runs for " + run);
                time += run;
                p.remaining -= run;
                if (p.remaining == 0) {
                    p.completionTime = time;
                    finished.add(p);
                    System.out.println("  P" + p.id + " finished at t=" + time);
                    removeProcess(p.id);          // also advances 'current'
                } else {
                    current = p.next;
                }
            }
            displayQueue();
            round++;
        }
    }

    public void displayAverages() {
        double totalWT = 0, totalTAT = 0;
        System.out.println("\nPID | Burst | Turnaround | Waiting");
        for (Process p : finished) {
            int tat = p.completionTime;           // arrival = 0
            int wt = tat - p.burstTime;
            totalTAT += tat;
            totalWT += wt;
            System.out.println("P" + p.id + "  |  " + p.burstTime + "    |    " + tat + "      |   " + wt);
        }
        System.out.printf("Average Waiting Time    = %.2f%n", totalWT / finished.size());
        System.out.printf("Average Turnaround Time = %.2f%n", totalTAT / finished.size());
    }

    public static void main(String[] args) {
        RoundRobinScheduler rr = new RoundRobinScheduler();
        rr.addProcess(1, 10, 2);
        rr.addProcess(2, 5, 1);
        rr.addProcess(3, 8, 3);
        int quantum = 3;

        System.out.println("Initial queue:");
        rr.displayQueue();
        System.out.println("\nTime quantum = " + quantum + "\n");
        rr.simulate(quantum);
        rr.displayAverages();
    }
}
