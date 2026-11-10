import java.util.ArrayList;import java.util.List;
public class Q6_RoundRobinCircular {
 static class Process{int id,burst,remaining,waiting,turnaround;Process(int i,int b){id=i;burst=b;remaining=b;}}
 static class Node{Process p;Node next;Node(Process p){this.p=p;}}static Node head,tail;static List<Process> all=new ArrayList<>();
 static void addProcess(int id,int burst){Process p=new Process(id,burst);all.add(p);Node x=new Node(p);if(head==null){head=tail=x;x.next=x;}else{tail.next=x;tail=x;tail.next=head;}}
 static void display(){if(head==null){System.out.println("Queue empty");return;}Node x=head;do{System.out.print("P"+x.p.id+"(remaining="+x.p.remaining+") ");x=x.next;}while(x!=head);System.out.println();}
 static void schedule(int quantum){if(quantum<=0)throw new IllegalArgumentException("Quantum must be positive");int time=0,done=0;while(head!=null){Node prev=tail,cur=head;do{Process p=cur.p;int run=Math.min(quantum,p.remaining);p.remaining-=run;time+=run;
                if(p.remaining==0){p.turnaround=time;p.waiting=p.turnaround-p.burst;done++;}
                if(p.remaining==0){if(cur==head&&cur==tail){head=tail=null;}else{prev.next=cur.next;if(cur==head)head=cur.next;if(cur==tail)tail=prev;tail.next=head;}if(head==null)break;cur=prev.next;}else{prev=cur;cur=cur.next;}
            }while(head!=null&&cur!=head);System.out.print("After round: ");display();}
        if(done>0){double w=0,t=0;for(Process p:all){w+=p.waiting;t+=p.turnaround;}System.out.printf("Average waiting time: %.2f%nAverage turnaround time: %.2f%n",w/done,t/done);}}
 public static void main(String[] a){addProcess(1,10);addProcess(2,5);addProcess(3,8);System.out.println("Initial queue:");display();schedule(3);}
}
