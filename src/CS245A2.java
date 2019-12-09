
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

/**
 * main class
 */
public class CS245A2 {
    private Graph graph = new TableGraph();

    /**
     * main function
     * @param args csv file address
     */
    public static void main(String[] args) {
        CS245A2 cs245A2 = new CS245A2();
        cs245A2.readFile(args[0]);
        cs245A2.readUserInput();
    }

    /**
     * format a name to Capitalize first letter
     * @param name String name
     * @return formatted name
     */
    public String formatName(String name) {
        String[] namearr = name.split(" ");
        String result = "";
        for(String part: namearr)
            result += " "+part.substring(0,1).toUpperCase()+part.substring(1);
        return result.substring(1);
    }

    /**
     * edit the text from csv line and format double quotes to quote
     * @param input line from csv file have double quotes
     * @return formatted line with normal quotes
     */
    private String formatDoubleQuotes(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char curr = input.charAt(i);
            if (curr == '\"' && input.charAt(i+1) == '\"') {
                if(input.charAt(i+2) == '\"' && input.charAt(i+3) == '\"')
                    sb.append(curr);
                continue;
            }
            sb.append(curr);
        }
        return sb.toString();
    }

    /**
     * parse the json to String[]
     * @param s json line
     * @return String[] with actors names
     * @throws ParseException
     */
    private String[] parseCast(String s) throws ParseException {

        String jsonstrings = s.split(",", 3)[2];
        if(jsonstrings.contains("[]"))
            return null;
        String casts = jsonstrings.split("\\[\\{")[1];
        String formatCasts = formatDoubleQuotes("[{" + casts.substring(0, casts.length()-3));

        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(formatCasts);
        String[] result = new String[jsonArray.size()];
        for(int i = 0; i< jsonArray.size(); i++) {
            JSONObject obj = (JSONObject) jsonArray.get(i);
            result[i] = ((String) obj.get("name")).toLowerCase();
        }
        return result;
    }

    /**
     * read the csv file
     * @param filename csv file path
     */
    private void readFile(String filename) {
        try (BufferedReader csvReader = new BufferedReader(new FileReader(filename))) {
            String row = csvReader.readLine();
            while((row = csvReader.readLine())!= null){
                String[] actorarray = parseCast(row);
                if(actorarray != null)
                    graph.addEdges(actorarray);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * read UserInput and run the whole program
     */
    private void readUserInput() {
        try(BufferedReader userReader = new BufferedReader(new InputStreamReader(System.in))) {
            boolean finished = false;
            while(!finished){
                System.out.print("Actor 1 name: ");
                String actor1 = userReader.readLine().toLowerCase();
                while(!graph.inGraph(actor1)) {
                    System.out.println("No such actor.");
                    System.out.print("Actor 1 name: ");
                    actor1 = userReader.readLine().toLowerCase();
                }
                System.out.print("Actor 2 name: ");
                String actor2 = userReader.readLine().toLowerCase();
                while(!graph.inGraph(actor2)) {
                    System.out.println("No such actor.");
                    System.out.print("Actor 2 name: ");
                    actor2 = userReader.readLine().toLowerCase();
                }
                ArrayList<String> route = graph.findShortestRoute(actor2, actor1);
                String result = String.format("Path between %s and %s: ", formatName(actor1), formatName(actor2));
                for(int i = 0; i < route.size(); i++) {
                    result += formatName(route.get(i));
                    if (i != route.size() - 1)
                        result += " --> ";
                }
                System.out.println(result);
                System.out.print("Continue? [Y/N] ");
                if(userReader.readLine().equals("N"))
                    finished = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
