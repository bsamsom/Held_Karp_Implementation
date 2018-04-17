
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import java.nio.file.Paths;

public class Main {
    private static int[][] D;
    private static int numVertices = -1;
    public static void main(String[] args) {
        processInput();
        printGraph(D);
        long startTime1 = System.currentTimeMillis();
        System.out.println("Held_Karp_Algorithm:");
        Held_Karp_Algorithm HK = new Held_Karp_Algorithm();
        long t1 = HK.run(D, numVertices);
        long time1 = System.currentTimeMillis()-startTime1;
        time(time1, String.format("%35s", "Held_Karp_Algorithm Total "));
        time(t1, String.format("%35s", "Held_Karp_Algorithm Only Algorithm "));
    }
    private static void time(long time, String msg){
        int secs = 0;
        if(time >= 1000){
            secs = (int)(time/1000);
        }
        time = time-(secs*1000);
        System.out.println(msg + "Time: " + secs + " Seconds, " + time + " Miliseconds");
    }
    public static File getFile() {
        String directory = Paths.get(".").toAbsolutePath().toString();
        JFileChooser chooser = new JFileChooser(directory);
        File file = null;
        int returnValue;
        returnValue = chooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();         
        }
        return file;
    }
    public static void processInput(){
        File aFile = getFile();
        try {
            Scanner fileReader = new Scanner(aFile);  
            String name = fileReader.nextLine();
            String shortestDist = fileReader.nextLine();
            String shorestPath = fileReader.nextLine();
            numVertices = Integer.parseInt(fileReader.nextLine());
            D = new int[numVertices][numVertices]; 
            System.out.println(name);
            System.out.println(shortestDist);
            System.out.println(shorestPath);
            System.out.println("File contains " + numVertices + " vertices.");
            String input = fileReader.nextLine();
            do{
                input = input.trim();
                String[] line = input.split("\\s+");
                int position = -Integer.parseInt(line[0])-1;
                for(int i = 1; i < line.length;i++){
                    int val = Integer.parseInt(line[i]);
                    D[position][i-1] = val;
                }
                input = fileReader.nextLine();
            }while(fileReader.hasNextLine());
            fileReader.close();
            input = input.trim();
            String[] line = input.split("\\s+");
            int position = -Integer.parseInt(line[0])-1;
            for(int i = 1; i < line.length;i++){
                int val = Integer.parseInt(line[i]);
                D[position][i-1] = val;
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }
    }
    private static void printGraph(int[][] graph){
        String[] lines = new String[numVertices];
        String startLine = String.format("%3s", "|");
        for(int i = 0; i < numVertices;i++){
            lines[i] = "";
            for(int j = 0; j < numVertices;j++){
                if(j > 0){
                  lines[i] += " ";  
                }
                lines[i] += String.format("%4d", (graph[i][j]));
            }
            startLine += String.format(" %4d", (i+1));
        }
        String temp = "";
        for(int i = 0; i < startLine.length();i++){
            if(i == 2){
                temp += "|";
            }
            else{
                temp += "-";
            }
        }
        System.out.println(startLine);
        System.out.println(temp);
        for(int i = 0; i < numVertices;i++){
            System.out.println(String.format("%2d| %s",(i+1), lines[i]));
        }
        System.out.println();
    }
}
