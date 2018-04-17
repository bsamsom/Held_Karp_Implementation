import java.util.Set;
public class Node {
   public Node next;
   public Set<Integer> set;
   public Node(Set<Integer> set){
       this.set = set;
       this.next = null;
   }
}
