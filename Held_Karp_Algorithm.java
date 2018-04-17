import java.util.HashSet;
import java.util.Set;

public class Held_Karp_Algorithm {
    private int[][] D;
    private int numVertices = -1; 
    private HashTable minCostDP;
    private HashTable parent;
    private LinkedList sets;
    public long run(int[][] data, int verticies) {
        D = data;
        numVertices = verticies;
        sets = new LinkedList();
        createSets();
        int size = numVertices*numVertices;
        minCostDP = new HashTable(size);
        parent    = new HashTable(size);

        long startTime = System.currentTimeMillis();
        int result = TSP();
        long time = System.currentTimeMillis()-startTime;
        
        printTour();
        System.out.println(", Distance: " + result);
        return time;
    }
    private int TSP(){
        Node curr = sets.top;
        while(curr != null){
            for(int i = 1; i < numVertices; i++) {
                if(curr.set.contains(i)) {
                    continue;
                }
                int[] temp = loop(curr.set, Integer.MAX_VALUE, 0, i);
                if(curr.set.isEmpty()) {
                    temp[0] = D[0][i];
                }
                minCostDP.insert(curr.set, i, temp[0]);
                parent.insert(curr.set, i, temp[1]);
            }
            curr = curr.next;
        }
        Set<Integer> set = new HashSet<>();
        for(int i = 1; i < numVertices; i++) {
            set.add(i);
        }
        int[] temp = loop(set, Integer.MAX_VALUE, -1, 0);
        parent.insert(set, 0, temp[1]);
        return temp[0];
    }
    private int[] loop(Set<Integer> set, int min, int minPos, int val){
        int[] results = new int[2];
        results[0] = min;
        results[1] = minPos;
        Set<Integer> setCopy = deepCopy(set); //you get an error if you use the normal set 
        for(int k : set) {
            int cost = D[k][val] + Cost(setCopy, k);
            if(cost < results[0]) {
                results[0] = cost;
                results[1] = k;
            }
        }
        return results;
    }
    private int Cost(Set<Integer> set, int j) {
        set.remove(j);
        int cost = minCostDP.get(set, j);
        set.add(j);
        return cost;
    }
    private Set<Integer> deepCopy(Set<Integer> set){
        Set<Integer> result = new HashSet<>();
        for(int i: set){
            result.add(i);
        }
        return result;
    }
    private void printTour() {
        String order = "";
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < numVertices; i++) {
            set.add(i);
        }
        int start = 0;
        while(!set.isEmpty()) {
            order = (start + 1) + " -> " + order;// fix for 0 indexing
            set.remove(start);
            start = parent.get(set,start);
        }
        order = (0 + 1) + " -> " + order;// we start from vertex 0
        System.out.print("Shortest path: " + order.substring(0,order.length()-4));
        // -4 to remove the last " -> "
    }
    private void printSets(){
        Node top = sets.top;
        while(top != null){
            if(top.set.isEmpty()){
                System.out.println("[0]");
            }
            else{
                System.out.println(top.set);    
            }
            top = top.next;
        }
    }
    private void createSets() {
        int result[] = new int[numVertices-1];
        generateCombination(0, 0, result);
    }
    private void generateCombination(int start, int end, int result[]) {
        if(end != result.length) {
            Set<Integer> set = new HashSet<>();
            if(end > 0){
                for(int i = 0; i < end; i++) {
                    set.add(result[i]);
                } 
            }
            sets.addOrdered(set);
            for(int i = start; i < result.length; i++) {
                result[end] = i+1;
                generateCombination(i+1, end+1, result);
            }
        }
    }
}
