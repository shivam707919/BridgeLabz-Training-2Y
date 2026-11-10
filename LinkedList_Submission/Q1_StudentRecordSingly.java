public class Q1_StudentRecordSingly {
 static class Node {int roll,age;String name,grade;Node next;Node(int r,String n,int a,String g){roll=r;name=n;age=a;grade=g;}}
 static Node head;
 static void addFirst(int r,String n,int a,String g){Node x=new Node(r,n,a,g);x.next=head;head=x;}
 static void addLast(int r,String n,int a,String g){Node x=new Node(r,n,a,g);if(head==null){head=x;return;}Node t=head;while(t.next!=null)t=t.next;t.next=x;}
 static void addAt(int p,int r,String n,int a,String g){if(p<1)return;if(p==1){addFirst(r,n,a,g);return;}Node t=head;for(int i=1;t!=null&&i<p-1;i++)t=t.next;if(t!=null){Node x=new Node(r,n,a,g);x.next=t.next;t.next=x;}}
 static boolean delete(int r){if(head==null)return false;if(head.roll==r){head=head.next;return true;}Node t=head;while(t.next!=null&&t.next.roll!=r)t=t.next;if(t.next==null)return false;t.next=t.next.next;return true;}
 static Node search(int r){for(Node t=head;t!=null;t=t.next)if(t.roll==r)return t;return null;}
 static boolean updateGrade(int r,String g){Node x=search(r);if(x==null)return false;x.grade=g;return true;}
 static void display(){for(Node t=head;t!=null;t=t.next)System.out.printf("%d %s %d %s%n",t.roll,t.name,t.age,t.grade);}
 public static void main(String[] args){addLast(101,"Aarav",20,"A");addLast(102,"Diya",19,"B");addFirst(100,"Kabir",20,"A+");addAt(2,105,"Meera",18,"A");display();updateGrade(102,"A");delete(100);System.out.println("After update/delete:");display();}
}
