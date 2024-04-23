import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;

public class TravelMap {

    // Maps a single Id to a single Location.
    public Map<Integer, Location> locationMap = new HashMap<>();

    // List of locations, read in the given order
    public List<Location> locations = new ArrayList<>();

    // List of trails, read in the given order
    public List<Trail> trails = new ArrayList<>();

    // You are free to add more variables if necessary.

    public void initializeMap(String filename) {
        // Read the XML file and fill the instance variables locationMap, locations and
        // trails.
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(filename));

            NodeList locList = doc.getElementsByTagName("Location");
            for (int i = 0; i < locList.getLength(); i++) {
                Node locNode = locList.item(i);
                Element locationEl = (Element) locNode;
                String name = locationEl.getElementsByTagName("Name").item(0).getTextContent();
                int id = Integer.parseInt(locationEl.getElementsByTagName("Id").item(0).getTextContent());
                Location l = new Location(name, id);
                locations.add(i, l);
                locationMap.put(l.id, l);
            }
            NodeList trailList = doc.getElementsByTagName("Trail");
            for (int i = 0; i < trailList.getLength(); i++) {
                Node trailNode = trailList.item(i);
                Element trailEl = (Element) trailNode;
                int source = Integer.parseInt(trailEl.getElementsByTagName("Source").item(0).getTextContent());
                int destination = Integer
                        .parseInt(trailEl.getElementsByTagName("Destination").item(0).getTextContent());
                int danger = Integer.parseInt(trailEl.getElementsByTagName("Danger").item(0).getTextContent());

                Location s = locationMap.get(source);
                Location d = locationMap.get(destination);

                trails.add(i, new Trail(s, d, danger));
            }

        } catch (Exception e) {

            e.printStackTrace();
        }


    }

    public List<Trail> getSafestTrails() {
        List<Trail> safestTrails = new ArrayList<>();
        Collections.sort(trails);
        
        int[] parent = new int[locations.size()];
        Arrays.fill(parent,-1);
        for(Trail t : trails){
            int x = find(parent,t.source.id);
            int y = find(parent, t.destination.id);
            if(x!=y){
                safestTrails.add(t);
                union(parent, x, y);
            }
        }
  
        return safestTrails;
    }

    public void printSafestTrails(List<Trail> safestTrails) {
        int total_danger = 0;
        System.out.println("Safest trails are:");
        for(Trail t : safestTrails){
            total_danger+= t.danger;
            System.out.println("The trail from " + t.source.name + " to "+ t.destination.name +" with danger " + t.danger);
        }
        System.out.println("Total danger: " + total_danger);

    }


    private int find(int[] parent , int i ){
        if(parent[i] == -1){
            return i;
        }
        return find(parent,parent[i]);
    }

    private void union(int[] parent , int x , int y){
        int root_X = find(parent,x);
        int root_Y = find(parent,y);
        parent[root_X] = root_Y;
    }

}
