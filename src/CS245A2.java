
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class CS245A2 {
    static Graph graph = new Graph();
    public static void main(String[] args) {
        CS245A2 cs245A2 = new CS245A2();
        cs245A2.readFile(args[0]);
        cs245A2.readUserInput();
    }
    private String formatDoubleQuotes(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char curr = input.charAt(i);
            if (i == 0 || i == input.length()-1) {
                continue;
            }
            if (curr == '\"' && input.charAt(i+1) == '\"') {
                if(input.charAt(i+2) == '\"' && input.charAt(i+3) == '\"')
                    sb.append(curr);
                continue;
            }
            sb.append(curr);
        }
        return sb.toString();
    }

    private String[] parseCast(String s) throws ParseException {

        String jsonstrings = s.split(",", 3)[2];
        if(jsonstrings.contains("[]"))
            return null;
        String casts = jsonstrings.split("\\[\\{")[1];
        String formatCasts = formatDoubleQuotes("\"[{" + casts.substring(0, casts.length()-2));

        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(formatCasts);
        String[] result = new String[jsonArray.size()];
        for(int i = 0; i< jsonArray.size(); i++) {
            JSONObject obj = (JSONObject) jsonArray.get(i);
            result[i] = (String) obj.get("name");
        }
        return result;
    }
    private void readFile(String filename) {
        String row;
        try (BufferedReader csvReader = new BufferedReader(new FileReader(filename))) {
            row = csvReader.readLine();
            while((row = csvReader.readLine())!= null){
                String[] actorarray = parseCast(row);
                if(actorarray != null)
                    graph.addEdges(actorarray);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    private void readUserInput() {
        try(BufferedReader userReader = new BufferedReader(new InputStreamReader(System.in))) {
            boolean finished = false;
            while(!finished){
                System.out.print("Actor 1 name: ");
                String actor1 = userReader.readLine();
                while(!graph.inGraph(actor1)) {
                    System.out.println("No such actor.");
                    System.out.print("Actor 1 name: ");
                    actor1 = userReader.readLine();
                }
                System.out.print("Actor 2 name: ");
                String actor2 = userReader.readLine();
                while(!graph.inGraph(actor2)) {
                    System.out.println("No such actor.");
                    System.out.print("Actor 2 name: ");
                    actor2 = userReader.readLine();
                }
                ArrayList<String> route = graph.findShortestRoute(actor2, actor1);
                Iterator it = route.iterator();
                String result = String.format("Path between %s and %s: ", actor1, actor2);
                while(it.hasNext()) {
                    result += String.format("%s -->", it.next());
                }
                System.out.println(result);
                System.out.print("Continue? [Y/N] ");
                if(userReader.readLine().equals("N"))
                    finished = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
