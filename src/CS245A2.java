
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class CS245A2 {
    public static void main(String[] args) {
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
}
