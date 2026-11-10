public class Q3_TaskSchedulerCircular {
 static class Node{int id,priority;String name,due;Node next;Node(int i,String n,int p,String d){id=i;name=n;priority=p;due=d;}}
 static Node head,tail,current;
 static void addFirst(int i,String n,int p,String d){Node x=new Node(i,n,p,d);if(head==null){head=tail=x;x.next=x;}else{x.next=head;head=x;tail.next=head;}if(current==null)current=head;}
 static void addLast(int i,String n,int p,String d){if(head==null){addFirst(i,n,p,d);return;}Node x=new Node(i,n,p,d);tail.next=x;tail=x;tail.next=head;}
 static void addAt(int pos,int i,String n,int p,String d){if(pos<=1){addFirst(i,n,p,d);return;}if(head==null)return;Node t=head;for(int k=1;k<pos-1&&t.next!=head;k++)t=t.next;Node x=new Node(i,n,p,d);x.next=t.next;t.next=x;if(t==tail)tail=x;}
 static boolean remove(int id){if(head==null)return false;Node prev=tail,x=head;do{if(x.id==id){if(x==head&&x==tail){head=tail=current=null;return true;}prev.next=x.next;if(x==head)head=x.next;if(x==tail)tail=prev;if(current==x)current=x.next;tail.next=head;return true;}prev=x;x=x.next;}while(x!=head);return false;}
 static void nextTask(){if(current!=null){current=current.next;System.out.println(current.name);}}
 static void display(){if(head==null){System.out.println("No tasks");return;}Node x=head;do{System.out.printf("%d %s priority=%d due=%s%n",x.id,x.name,x.priority,x.due);x=x.next;}while(x!=head);}
 static void searchPriority(int p){if(head==null)return;Node x=head;do{if(x.priority==p)System.out.println(x.name);x=x.next;}while(x!=head);}
 public static void main(String[] a){addLast(1,"Submit assignment",1,"2026-11-12");addLast(2,"Prepare presentation",2,"2026-11-14");addFirst(3,"Revise Java",1,"2026-11-11");addAt(2,4,"Practice DSA",3,"2026-11-13");display();nextTask();searchPriority(1);remove(3);}
}
