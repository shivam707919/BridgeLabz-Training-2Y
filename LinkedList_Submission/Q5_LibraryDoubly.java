public class Q5_LibraryDoubly {
 static class Node{String title,author,genre;int id;boolean available;Node prev,next;Node(String t,String a,String g,int i,boolean v){title=t;author=a;genre=g;id=i;available=v;}}
 static Node head,tail;
 static void addFirst(String t,String a,String g,int i,boolean v){Node x=new Node(t,a,g,i,v);if(head==null)head=tail=x;else{x.next=head;head.prev=x;head=x;}}
 static void addLast(String t,String a,String g,int i,boolean v){Node x=new Node(t,a,g,i,v);if(tail==null)head=tail=x;else{tail.next=x;x.prev=tail;tail=x;}}
 static void addAt(int p,String t,String a,String g,int i,boolean v){if(p<=1){addFirst(t,a,g,i,v);return;}Node c=head;for(int k=1;c!=null&&k<p;k++)c=c.next;if(c==null){addLast(t,a,g,i,v);return;}Node x=new Node(t,a,g,i,v),q=c.prev;q.next=x;x.prev=q;x.next=c;c.prev=x;}
 static Node find(int id){for(Node x=head;x!=null;x=x.next)if(x.id==id)return x;return null;}static boolean remove(int id){Node x=find(id);if(x==null)return false;if(x.prev!=null)x.prev.next=x.next;else head=x.next;if(x.next!=null)x.next.prev=x.prev;else tail=x.prev;return true;}
 static void search(String key){for(Node x=head;x!=null;x=x.next)if(x.title.equalsIgnoreCase(key)||x.author.equalsIgnoreCase(key))print(x);}
 static boolean updateAvailability(int id,boolean v){Node x=find(id);if(x==null)return false;x.available=v;return true;}static int count(){int n=0;for(Node x=head;x!=null;x=x.next)n++;return n;}
 static void print(Node x){System.out.printf("%d | %s | %s | %s | %s%n",x.id,x.title,x.author,x.genre,x.available?"Available":"Issued");}static void forward(){for(Node x=head;x!=null;x=x.next)print(x);}static void reverse(){for(Node x=tail;x!=null;x=x.prev)print(x);}
 public static void main(String[] a){addLast("The Hobbit","J.R.R. Tolkien","Fantasy",1,true);addLast("1984","George Orwell","Dystopian",2,true);addFirst("A Game of Thrones","George R.R. Martin","Fantasy",3,false);addAt(2,"Dune","Frank Herbert","Sci-Fi",4,true);forward();System.out.println("Reverse:");reverse();updateAvailability(1,false);remove(3);System.out.println("Book count: "+count());search("George Orwell");}
}
