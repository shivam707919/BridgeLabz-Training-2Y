import java.time.LocalDateTime;import java.time.format.DateTimeFormatter;
public class Q9_TicketReservationCircular {
 static class Node{int id,seat;String customer,movie,time;Node next;Node(int i,String c,String m,int s,String t){id=i;customer=c;movie=m;seat=s;time=t;}}
 static Node head,tail;static void addTicket(int id,String c,String m,int seat){String t=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));Node x=new Node(id,c,m,seat,t);if(head==null){head=tail=x;x.next=x;}else{tail.next=x;tail=x;tail.next=head;}}
 static boolean removeTicket(int id){if(head==null)return false;Node prev=tail,x=head;do{if(x.id==id){if(x==head&&x==tail){head=tail=null;return true;}prev.next=x.next;if(x==head)head=x.next;if(x==tail)tail=prev;tail.next=head;return true;}prev=x;x=x.next;}while(x!=head);return false;}
 static void display(){if(head==null){System.out.println("No reservations");return;}Node x=head;do{System.out.printf("Ticket:%d Customer:%s Movie:%s Seat:%d Time:%s%n",x.id,x.customer,x.movie,x.seat,x.time);x=x.next;}while(x!=head);}
 static void search(String key){if(head==null)return;boolean found=false;Node x=head;do{if(x.customer.equalsIgnoreCase(key)||x.movie.equalsIgnoreCase(key)){System.out.println(x.id+" | "+x.customer+" | "+x.movie+" | seat "+x.seat);found=true;}x=x.next;}while(x!=head);if(!found)System.out.println("No matching ticket");}
 static int count(){if(head==null)return 0;int n=0;Node x=head;do{n++;x=x.next;}while(x!=head);return n;}
 public static void main(String[] a){addTicket(501,"Aarav","Interstellar",12);addTicket(502,"Diya","Dune",8);addTicket(503,"Kabir","Interstellar",13);display();search("Interstellar");removeTicket(502);System.out.println("After cancellation:");display();System.out.println("Total tickets: "+count());}
}
