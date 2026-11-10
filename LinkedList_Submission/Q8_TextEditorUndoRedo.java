public class Q8_TextEditorUndoRedo {
 static class Node{String text;Node prev,next;Node(String s){text=s;}}static Node head,tail,current;static int size;static final int LIMIT=10;
 static void addState(String text){if(current!=null){Node d=current.next;current.next=null;tail=current;while(d!=null){Node n=d.next;d.prev=d.next=null;d=n;size--;}}Node x=new Node(text);if(head==null)head=tail=current=x;else{x.prev=tail;tail.next=x;tail=current=x;}size++;while(size>LIMIT){Node old=head;head=head.next;head.prev=null;old.next=null;size--;}}
 static boolean undo(){if(current==null||current.prev==null)return false;current=current.prev;return true;}static boolean redo(){if(current==null||current.next==null)return false;current=current.next;return true;}
 static void display(){System.out.println("Current text: "+(current==null?"":current.text));}
 public static void main(String[] a){addState("");addState("Hello");addState("Hello, world");addState("Hello, world!");display();undo();display();undo();display();redo();display();addState("New text after undo");display();System.out.println("History:");for(Node x=head;x!=null;x=x.next)System.out.println(x.text+(x==current?" <-- current":""));}
}
