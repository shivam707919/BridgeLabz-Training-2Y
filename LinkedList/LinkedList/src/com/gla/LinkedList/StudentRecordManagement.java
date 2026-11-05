package com.gla.LinkedList;

/**
 * Problem 1: Singly Linked List - Student Record Management
 */
public class StudentRecordManagement {

    static class Student {
        int rollNo;
        String name;
        int age;
        String grade;
        Student next;

        Student(int rollNo, String name, int age, String grade) {
            this.rollNo = rollNo;
            this.name = name;
            this.age = age;
            this.grade = grade;
        }

        @Override
        public String toString() {
            return "Roll No: " + rollNo + " | Name: " + name + " | Age: " + age + " | Grade: " + grade;
        }
    }

    private Student head;

    public void addAtBeginning(int rollNo, String name, int age, String grade) {
        Student s = new Student(rollNo, name, age, grade);
        s.next = head;
        head = s;
    }

    public void addAtEnd(int rollNo, String name, int age, String grade) {
        Student s = new Student(rollNo, name, age, grade);
        if (head == null) {
            head = s;
            return;
        }
        Student temp = head;
        while (temp.next != null) temp = temp.next;
        temp.next = s;
    }

    /** position is 1-based */
    public void addAtPosition(int position, int rollNo, String name, int age, String grade) {
        if (position <= 1 || head == null) {
            addAtBeginning(rollNo, name, age, grade);
            return;
        }
        Student temp = head;
        for (int i = 1; i < position - 1 && temp.next != null; i++) temp = temp.next;
        Student s = new Student(rollNo, name, age, grade);
        s.next = temp.next;
        temp.next = s;
    }

    public boolean deleteByRollNo(int rollNo) {
        if (head == null) {
            System.out.println("List is empty.");
            return false;
        }
        if (head.rollNo == rollNo) {
            Student old = head;
            head = head.next;
            old.next = null;
            return true;
        }
        Student temp = head;
        while (temp.next != null && temp.next.rollNo != rollNo) temp = temp.next;
        if (temp.next == null) return false;
        Student old = temp.next;
        temp.next = old.next;
        old.next = null;
        return true;
    }

    public Student search(int rollNo) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNo == rollNo) return temp;
            temp = temp.next;
        }
        return null;
    }

    public void display() {
        if (head == null) {
            System.out.println("No student records.");
            return;
        }
        Student temp = head;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public boolean updateGrade(int rollNo, String newGrade) {
        Student s = search(rollNo);
        if (s == null) return false;
        s.grade = newGrade;
        return true;
    }

    public static void main(String[] args) {
        StudentRecordManagement list = new StudentRecordManagement();
        list.addAtEnd(101, "Aman", 19, "A");
        list.addAtEnd(102, "Riya", 20, "B");
        list.addAtBeginning(100, "Karan", 19, "A+");
        list.addAtPosition(3, 103, "Neha", 21, "B+");
        System.out.println("--- All students ---");
        list.display();

        System.out.println("\n--- Search 102 ---");
        Student s = list.search(102);
        System.out.println(s != null ? s : "Not found");

        System.out.println("\n--- Update grade of 102 to A ---");
        System.out.println(list.updateGrade(102, "A") ? "Updated" : "Roll number not found");
        list.display();

        System.out.println("\n--- Delete 100 and 999 ---");
        System.out.println("Delete 100: " + list.deleteByRollNo(100));
        System.out.println("Delete 999: " + list.deleteByRollNo(999));
        list.display();
    }
}
