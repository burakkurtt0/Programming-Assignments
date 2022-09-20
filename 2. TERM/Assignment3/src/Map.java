import java.io.*;
import java.util.*;


public class Map {

    private List<ArrayList<String>> map;
    String Stringmap;

    Map(String fileName )throws IOException{
    FileReader txt = new FileReader(fileName);
    Scanner scn = new Scanner(txt);
    map = new ArrayList<ArrayList<String>>();
        
    
    while (scn.hasNextLine()){
        String Line = scn.nextLine();
        ArrayList<String> list = new ArrayList<>();

        String[] linearray = Line.split(" ");
        for (String w : linearray){
            list.add(w);
        }
        map.add(list);
    }

    scn.close();
    }
    

    public List<ArrayList<String>> getMap(){
        return this.map;
    }
   
    public String createStringMap(){
        this.Stringmap ="";
        for (ArrayList<String> line : this.map){
            for (String c : line){
                Stringmap+= " "+c;
            }
            Stringmap+="\n";
        }
        return Stringmap;
    }
}
