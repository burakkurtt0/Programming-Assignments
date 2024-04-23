import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

import java.io.File;
import java.util.*;

public class Kingdom {
    public ArrayList<City> cities;
    

    public Kingdom() {
        cities = new ArrayList<>();
        
    }


    public void FillNetwork(List<Colony> colonies){
        for(Colony c: colonies){
            for(int city_nums : c.cities){
                c.roadNetwork.put(city_nums, cities.get(city_nums).roadTo);
            }
        }
    }



    public void initializeKingdom(String filename) {

        try {
            Scanner sc = new Scanner(new File(filename));
            int city_num = 0;
            
            while (sc.hasNextLine()) {

                String[] l = sc.nextLine().split(" ");
                City city = new City(city_num++);
                
                

                for (int i = 0; i < l.length; i++) {

                    if (l[i].equals("1")) {

                        city.addRoad(i);
                        
                    }
                }

                
                cities.add(city);
            }
            sc.close();

            for (int i = 0; i < cities.size(); i++) {
                cities.get(i).fill_DFS(cities);
                cities.get(i).connected = cities.get(i).dfsNeighbours;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<Colony> getColonies() {
        List<Colony> colonies = new ArrayList<>();

        for (City c : cities) {

            if (c.connected.isEmpty() || c.connected.get(0) == -1) {

                continue;
            }

            for (int i = 0; i < c.connected.size(); i++) {
                c.addConnected(cities.get(c.connected.get(i)));
                cities.get(c.connected.get(i)).deleteConnected();
            }

            for (int i = 0; i < c.connected.size(); i++) {

            }

        }

        List<Colony> subColonies = new ArrayList<>();

        for (City c : cities) {
            if (c.connected.isEmpty()) {
                Colony col = new Colony();
                col.cities.add(c.city_num);

                subColonies.add(col);
            }

            if (!(c.connected.get(0) == -1)) {
                Colony col = new Colony();
                col.cities = c.connected;
                col.cities.add(c.city_num);
                subColonies.add(col);
            } else {
                continue;
            }
        }

        for (Colony c1 : subColonies) {
            for (Colony c2 : subColonies) {
                if (c1.equals(c2)) {
                    continue;
                }
                if (c1.cities.contains(-1)) {
                    continue;
                }

                for (int i = 0; i < c1.cities.size(); i++) {
                    if (c2.cities.contains(c1.cities.get(i))) {
                        c1.MergeSubColonies(c2);

                    }
                }
            }
        }

        for (Colony c : subColonies) {
            Collections.sort(c.cities);
        }

        for (int i = 0; i < subColonies.size(); i++) {
            for (int j = i + 1; j < subColonies.size(); j++) {
                if (subColonies.get(i).cities.get(0).equals(subColonies.get(j).cities.get(0))) {

                    subColonies.get(j).cities.set(0, -1);
                }
            }
        }

        for (Colony c : subColonies) {
            if (c.cities.get(0) != -1) {
                Colony col = new Colony();
                col.cities = c.cities;
                colonies.add(col);
            }
        }

        FillNetwork(colonies);
        return colonies;

    }

    public void printColonies(List<Colony> discoveredColonies) {

        System.out.println("Discovered colonies are:");
        for (int i = 0; i < discoveredColonies.size(); i++) {
            Collections.sort(discoveredColonies.get(i).cities);
            System.out.print("Colony " + (i + 1) + ": [");
            for (int j = 0; j < discoveredColonies.get(i).cities.size(); j++) {
                if (j == discoveredColonies.get(i).cities.size() - 1) {
                    System.out.print((discoveredColonies.get(i).cities.get(j) + 1) + "]");
                    System.out.println();
                } else {
                    System.out.print((discoveredColonies.get(i).cities.get(j) + 1) + ", ");
                }
            }
        }
    }

}
