public class Q2_MovieManagementDoubly {
 static class Node {String title,director;int year;double rating;Node prev,next;Node(String t,String d,int y,double r){title=t;director=d;year=y;rating=r;}}
 static Node head,tail;
 static void addFirst(String t,String d,int y,double r){Node x=new Node(t,d,y,r);if(head==null)head=tail=x;else{x.next=head;head.prev=x;head=x;}}
 static void addLast(String t,String d,int y,double r){Node x=new Node(t,d,y,r);if(tail==null)head=tail=x;else{tail.next=x;x.prev=tail;tail=x;}}
 static void addAt(int p,String t,String d,int y,double r){if(p<=1){addFirst(t,d,y,r);return;}Node c=head;for(int i=1;c!=null&&i<p;i++)c=c.next;if(c==null){addLast(t,d,y,r);return;}Node x=new Node(t,d,y,r),q=c.prev;q.next=x;x.prev=q;x.next=c;c.prev=x;}
 static Node find(String t){for(Node x=head;x!=null;x=x.next)if(x.title.equalsIgnoreCase(t))return x;return null;}
 static boolean remove(String t){Node x=find(t);if(x==null)return false;if(x.prev!=null)x.prev.next=x.next;else head=x.next;if(x.next!=null)x.next.prev=x.prev;else tail=x.prev;return true;}
 static void searchDirector(String d){for(Node x=head;x!=null;x=x.next)if(x.director.equalsIgnoreCase(d))print(x);}
 static void searchRating(double r){for(Node x=head;x!=null;x=x.next)if(x.rating==r)print(x);}
 static boolean updateRating(String t,double r){Node x=find(t);if(x==null)return false;x.rating=r;return true;}
 static void print(Node x){System.out.printf("%s | %s | %d | %.1f%n",x.title,x.director,x.year,x.rating);}
 static void forward(){for(Node x=head;x!=null;x=x.next)print(x);}static void reverse(){for(Node x=tail;x!=null;x=x.prev)print(x);}
 public static void main(String[] z){addLast("Inception","Christopher Nolan",2010,8.8);addLast("Interstellar","Christopher Nolan",2014,8.7);addFirst("Dangal","Nitesh Tiwari",2016,8.3);addAt(2,"The Dark Knight","Christopher Nolan",2008,9.0);forward();System.out.println("Reverse:");reverse();updateRating("Inception",9.0);remove("Dangal");}
}
