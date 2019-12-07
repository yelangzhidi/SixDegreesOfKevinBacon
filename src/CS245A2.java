
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class CS245A2 {
    static Graph graph = new Graph();
    public static void main(String[] args) {
        readUserInput();
        readFile(args[0]);
    }
    public static String[] parseCast(String s) throws ParseException {

        String[] result = new String[10];
        String casts = s.split(",",3)[2];
        System.out.println(s);
        System.out.println(casts);
        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(casts);




        return result;
    }
    public static void readFile(String filename) {
        String row;
        try (BufferedReader csvReader = new BufferedReader(new FileReader(filename))) {
            row = csvReader.readLine();
            while((row = csvReader.readLine())!= null){
                parseCast(row);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    public static void readUserInput() {
        try(BufferedReader userReader = new BufferedReader(new InputStreamReader(System.in))) {
            boolean finished = false;
            while(!finished){
                System.out.print("Actor 1 name: ");
                String actor1 = userReader.readLine();
                while(!graph.inGraph(actor1.toLowerCase())) {
                    System.out.println("No such actor.");
                    System.out.print("Actor 1 name: ");
                    actor1 = userReader.readLine();
                }
                System.out.print("Actor 2 name: ");
                String actor2 = userReader.readLine();
                while(!graph.inGraph(actor2.toLowerCase())) {
                    System.out.println("No such actor.");
                    System.out.print("Actor 2 name: ");
                    actor2 = userReader.readLine();
                }
                System.out.format("Path between %s and %s: \n", actor1, actor2);
                System.out.print("Continue? [Y/N] ");
                if(userReader.readLine().equals("N"))
                    finished = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
