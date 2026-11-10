public class Q4_InventorySingly {
 static class Node{String name;int id,qty;double price;Node next;Node(String n,int i,int q,double p){name=n;id=i;qty=q;price=p;}}
 static Node head;
 static void addFirst(String n,int i,int q,double p){Node x=new Node(n,i,q,p);x.next=head;head=x;}
 static void addLast(String n,int i,int q,double p){Node x=new Node(n,i,q,p);if(head==null){head=x;return;}Node t=head;while(t.next!=null)t=t.next;t.next=x;}
 static void addAt(int pos,String n,int i,int q,double p){if(pos<=1){addFirst(n,i,q,p);return;}Node t=head;for(int k=1;t!=null&&k<pos-1;k++)t=t.next;if(t!=null){Node x=new Node(n,i,q,p);x.next=t.next;t.next=x;}}
 static Node byId(int i){for(Node x=head;x!=null;x=x.next)if(x.id==i)return x;return null;}static Node byName(String n){for(Node x=head;x!=null;x=x.next)if(x.name.equalsIgnoreCase(n))return x;return null;}
 static boolean remove(int i){if(head==null)return false;if(head.id==i){head=head.next;return true;}Node t=head;while(t.next!=null&&t.next.id!=i)t=t.next;if(t.next==null)return false;t.next=t.next.next;return true;}
 static boolean updateQty(int i,int q){Node x=byId(i);if(x==null)return false;x.qty=q;return true;}static double totalValue(){double s=0;for(Node x=head;x!=null;x=x.next)s+=x.price*x.qty;return s;}
 interface Cmp{int compare(Node a,Node b);}static Node sort(Node h,Cmp c){if(h==null||h.next==null)return h;Node slow=h,fast=h.next;while(fast!=null&&fast.next!=null){slow=slow.next;fast=fast.next.next;}Node r=slow.next;slow.next=null;return merge(sort(h,c),sort(r,c),c);}
 static Node merge(Node a,Node b,Cmp c){Node d=new Node("",0,0,0),t=d;while(a!=null&&b!=null){if(c.compare(a,b)<=0){t.next=a;a=a.next;}else{t.next=b;b=b.next;}t=t.next;}t.next=a!=null?a:b;return d.next;}
 static void sortName(boolean asc){head=sort(head,(a,b)->(asc?1:-1)*a.name.compareToIgnoreCase(b.name));}static void sortPrice(boolean asc){head=sort(head,(a,b)->(asc?1:-1)*Double.compare(a.price,b.price));}
 static void display(){for(Node x=head;x!=null;x=x.next)System.out.printf("%d %s qty=%d price=%.2f%n",x.id,x.name,x.qty,x.price);}
 public static void main(String[] a){addLast("Keyboard",11,5,700);addLast("Mouse",12,10,350);addFirst("Monitor",10,2,8500);addAt(2,"USB Cable",13,8,150);updateQty(12,12);display();System.out.println("Total value="+totalValue());sortName(true);System.out.println("Sorted by name:");display();sortPrice(false);System.out.println("Sorted by price descending:");display();}
}
