import java.util.Set;
public class LinkedList {
    public Node top;
    public int size = 0;
    public LinkedList() { 
       top = null;
    }
    public LinkedList(Set<Integer> set) { 
       top = new Node(set);
       top.next = null;
    }
    public void addOrdered(Set<Integer> set) { 
        Node newNode = new Node(set);
        if(top == null){
            top = newNode;
            top.next = null;
        }
        else{
            Node curr = top;
            Node prev = curr;
            boolean end = false;
            
            while(curr != null && !end){
                if(prev.set.size() == set.size() && curr.set.size() > set.size()){
                    end = true;
                }
                else{
                    prev = curr;
                    curr = curr.next;
                }
            }
            if(end){
                prev.next = newNode;
                newNode.next = curr;
            }
            else{
                if(curr != null){
                   curr.next = newNode; 
                }
                else{
                    prev.next = newNode; 
                }
            }
        }
        size++;
    }
    public boolean contains(Set<Integer> set){
        boolean result = false;
        if(!isEmpty()){
            Node start = top;
            while(start.next != null){
                if(start.set.containsAll(set)){
                    return true;
                }
                start = start.next;
            }
        }
        return result;
    }
    
    public boolean isEmpty(){
        return top == null;
    }
    public void remove(Set<Integer> set){
        Node curr = top;
        Node prev = curr;
        while(curr != null & curr.set != set){
            prev = curr;
            curr = curr.next;
        }
        if(curr.set == set){
            prev.next = curr.next;
        }
        size--;
    }
}
