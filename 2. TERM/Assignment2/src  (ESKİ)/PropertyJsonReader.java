import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;



public class PropertyJsonReader {
     private ArrayList<Lands> LandList = new ArrayList<>();
     private Lands[] arrayLand = new Lands[28]; // !!!!
	 
     public Lands[] getArrayLand() {
        return arrayLand;
    }

    public void setArrayLand(Lands[] arrayLand) {
        this.arrayLand = arrayLand;
    }

    public ArrayList<Lands> getLandList() {
        return LandList;
    }

    public void setLandList(ArrayList<Lands> landArray) {
        LandList = landArray;
    }

    public PropertyJsonReader(){
         JSONParser processor = new JSONParser();
         try (Reader file = new FileReader("property.json")){
             JSONObject jsonfile = (JSONObject) processor.parse(file);
             JSONArray Land = (JSONArray) jsonfile.get("1");
             for(Object i:Land){
				 
				 //You can reach items by using statements below:
				int id = Integer.parseInt((String)((JSONObject)i).get("id"));
				 String name = (String)((JSONObject)i).get("name");
				 int cost = Integer.parseInt((String)((JSONObject)i).get("cost"));

                 Lands property = new Properties(id, name, cost);
                 LandList.add(property);
				 //And you can add these items to any data structure (e.g. array, linkedlist etc.
				 
				 
                 
             }
             JSONArray RailRoad = (JSONArray) jsonfile.get("2");
             for(Object i:RailRoad){
				 //You can reach items by using statements below:
                int id =Integer.parseInt((String)((JSONObject)i).get("id"));
				String name =(String)((JSONObject)i).get("name");
				double cost =Integer.parseInt((String)((JSONObject)i).get("cost"));
                Lands Railroad = new Railroad(id, name, cost);
                LandList.add(Railroad);
				//And you can add these items to any data structure (e.g. array, linkedlist etc.
             }
			 
             JSONArray Company = (JSONArray) jsonfile.get("3");
             for(Object i:Company){
				 //You can reach items by using statements below:
                 int id =Integer.parseInt((String)((JSONObject)i).get("id"));
				 String name =(String)((JSONObject)i).get("name");
				 double cost = Integer.parseInt((String)((JSONObject)i).get("cost"));
                 Lands company = new Company(id, name, cost);
                 LandList.add(company);
             }
             
         } catch (IOException e){
             e.printStackTrace();
         } catch (ParseException e){
             e.printStackTrace();
         }
     }
     

     public void ListToArray(){
         Lands[] nAL = new Lands[28];

         for (int i =0 ; i<getLandList().size();i++){
             
             for (Lands land : getLandList()){
                 

                if (i == land.getId()){
                    System.out.println("When i =" + i + " land number is "+" "+ land.getId());
                    nAL[i] = land;
                }

             }

         }
         setArrayLand(nAL);
        for (Lands land : getArrayLand()){
            System.out.println(land.getId());
        }
     }
}