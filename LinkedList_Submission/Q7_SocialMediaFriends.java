import java.util.ArrayList;import java.util.List;
public class Q7_SocialMediaFriends {
 static class User{int id,age;String name;List<Integer> friends=new ArrayList<>();User next;User(int i,String n,int a){id=i;name=n;age=a;}}
 static User head;static void addUser(int id,String n,int age){if(find(id)!=null)return;User x=new User(id,n,age);if(head==null)head=x;else{User t=head;while(t.next!=null)t=t.next;t.next=x;}}
 static User find(int id){for(User x=head;x!=null;x=x.next)if(x.id==id)return x;return null;}static User find(String name){for(User x=head;x!=null;x=x.next)if(x.name.equalsIgnoreCase(name))return x;return null;}
 static boolean addFriend(int a,int b){User x=find(a),y=find(b);if(x==null||y==null||a==b)return false;if(!x.friends.contains(b))x.friends.add(b);if(!y.friends.contains(a))y.friends.add(a);return true;}
 static boolean removeFriend(int a,int b){User x=find(a),y=find(b);if(x==null||y==null)return false;x.friends.remove(Integer.valueOf(b));y.friends.remove(Integer.valueOf(a));return true;}
 static List<Integer> mutualFriends(int a,int b){List<Integer> out=new ArrayList<>();User x=find(a),y=find(b);if(x!=null&&y!=null)for(int id:x.friends)if(y.friends.contains(id))out.add(id);return out;}
 static void displayFriends(int id){User x=find(id);if(x==null){System.out.println("User not found");return;}System.out.println("Friends of "+x.name+":");for(int f:x.friends){User y=find(f);if(y!=null)System.out.println(y.id+" - "+y.name);}}
 static void countFriends(){for(User x=head;x!=null;x=x.next)System.out.println(x.name+": "+x.friends.size());}
 public static void main(String[] a){addUser(1,"Aarav",20);addUser(2,"Diya",19);addUser(3,"Kabir",21);addUser(4,"Meera",20);addFriend(1,2);addFriend(1,3);addFriend(2,3);addFriend(2,4);displayFriends(1);System.out.println("Mutual friends of 1 and 2: "+mutualFriends(1,2));removeFriend(1,3);countFriends();System.out.println("Find Meera: "+find("Meera").id);}
}
