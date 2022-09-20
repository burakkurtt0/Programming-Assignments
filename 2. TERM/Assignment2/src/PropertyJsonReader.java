import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;



public class PropertyJsonReader {
     private LinkedList<Square> squares = new LinkedList<Square>();


     // These are created for preventing downcasting in Chance part and only used in there.
     public LinkedList<Properties> properties = new LinkedList<>();
     public LinkedList<RailRoad> railRoads = new LinkedList<>();
     
     
     public PropertyJsonReader(){
         JSONParser processor = new JSONParser();
         try (Reader file = new FileReader("property.json")){
             JSONObject jsonfile = (JSONObject) processor.parse(file);
             JSONArray Land = (JSONArray) jsonfile.get("1");
             for(Object i:Land){
				 
				 //You can reach items by using statements below:
				 int id =Integer.parseInt((String)((JSONObject)i).get("id"));
				 String name =(String)((JSONObject)i).get("name");
				 double cost =(double)Integer.parseInt((String)((JSONObject)i).get("cost"));
				 //And you can add these items to any data structure (e.g. array, linkedlist etc.
				 Properties prop = new Properties(id, name, cost);
                 Square property = new Properties(id, name, cost);
                 squares.add(property);
                 properties.add(prop);
                 
				 
                 
             }
             JSONArray RailRoad = (JSONArray) jsonfile.get("2");
             for(Object i:RailRoad){
				 //You can reach items by using statements below:
                int id =Integer.parseInt((String)((JSONObject)i).get("id"));
				String name = (String)((JSONObject)i).get("name");
				double cost = (double)Integer.parseInt((String)((JSONObject)i).get("cost"));
				//And you can add these items to any data structure (e.g. array, linkedlist etc.
                Square Railroad = new RailRoad(id, name, cost);
                squares.add(Railroad);
             }
			 
             JSONArray Company = (JSONArray) jsonfile.get("3");
             for(Object i:Company){
				 //You can reach items by using statements below:
                 int id =Integer.parseInt((String)((JSONObject)i).get("id"));
				 String name = (String)((JSONObject)i).get("name");
				 double cost = (double)Integer.parseInt((String)((JSONObject)i).get("cost"));
                 Square company = new Company(id, name, cost);
                 squares.add(company);
             }
             
         } catch (IOException e){
             e.printStackTrace();
         } catch (ParseException e){
             e.printStackTrace();
         }
     }

    public LinkedList<Square> getSquares() {
        return squares;
    }

    
     

     
}