import java.util.Set;
public class HashTable{  
    private class Node {  
        public Set<Integer> data;
        public int vertex;
        public int minCost;
        public Node next;
        public Node(Set<Integer> data, int vertex, int minCost) {  
            this.data = data;  
            this.vertex = vertex;
            this.minCost = minCost;
            this.next = null;
        }  
    }
    public HashTable(int size){
        list = new Node[size];
        for(int i = 0; i < list.length;i++){
            list[i] = null;
        }
        totalSize = 0;
    }
    private Node[] list;
    public int totalSize;
    private int hash(int vertex, Set<Integer> data){
        if(data != null){
            return ((23 * vertex + (data.hashCode())) % list.length);
        }
        else{
            return vertex % list.length;
        }
    }
    public void insert(Set<Integer> input, int vertex, int minCost) {
        Node temp = new Node(input,vertex,minCost);
        int key = hash(vertex,input);
        if(list[key] == null){
            list[key] = temp;
        }
        else{
            Node curr = list[key];
            while(curr.next != null){
                curr = curr.next;
            }
            curr.next = temp;
        }
        totalSize++;
    }
    public int get(Set<Integer> set, int vertex){
        int key = hash(vertex,set);
        Node curr = list[key];
        if(!set.containsAll(curr.data) || curr.vertex != vertex){
            while(curr.next != null){
                if(vertex == curr.vertex){
                    if(set.containsAll(curr.data)){
                        return curr.minCost;
                    }
                }
                curr = curr.next;
            }
        }
        return curr.minCost;
    }
    public void print(){
        for(int i = 0; i < list.length;i++){
            Node curr = list[i];
            System.out.println("List[" + i + "]");
            while(curr != null){
                System.out.print("\tVertex: " + curr.vertex + " Data:"); 
                if(curr.data.isEmpty()){
                    System.out.print("[0]");
                }
                else{
                    System.out.print(curr.data);    
                }
                System.out.println("\t\tMin cost: " + curr.minCost);
                curr = curr.next;
            }  
        } 
    }
}