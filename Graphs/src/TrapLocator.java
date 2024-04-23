
import java.util.*;

public class TrapLocator {
    public List<Colony> colonies;

    public TrapLocator(List<Colony> colonies) {
        this.colonies = colonies;
    }

    private int CheckTrap(ArrayList<Integer> visited, HashMap<Integer, List<Integer>> network, int city) {

        for (int c : network.get(city)) {
            if (visited.contains(c)) {

                return 1;
            }
        }
        return 0;
    }

    public List<List<Integer>> revealTraps() {
       
        // Trap positions for each colony, should contain an empty array if the colony
        // is safe.
        // I.e.:
        // 0 -> [2, 15, 16, 31]
        // 1 -> [4, 13, 22]
        // 3 -> []
        // ...
        List<List<Integer>> traps = new ArrayList<>();

        for (Colony c : colonies) {
            ArrayList<Integer> trap = new ArrayList<>();

            for (int i : c.cities) {
                Boolean trapControl = false;
                ArrayList<Integer> visited = new ArrayList<>();

                Stack<Integer> dfs = new Stack<>();
                dfs.push(i);
                while (!dfs.isEmpty()) {
                    int t = dfs.pop();

                    if (!visited.contains(t)) {
                        visited.add(t);
                    }

                    for (int neighbour : c.roadNetwork.get(t)) {
                        int check = CheckTrap(visited, c.roadNetwork, neighbour);
                        if (check == 1) {
                            visited.add(neighbour);
                            trap = visited;
                            trapControl = true;
                            break;
                        }
                        
                    }
                    if (trapControl) {
                        break;
                    }

                    for (int neighbour : c.roadNetwork.get(t)) {

                        if (!visited.contains(neighbour)) {
                            dfs.push(neighbour);
                        }

                    }

                }
                if (trapControl) {

                    break;
                }
            }

            traps.add(trap);
        }

        return traps;
    }

    public void printTraps(List<List<Integer>> traps) {

        System.out.println("Danger exploration conclusions:");

        int ColonyCount = 0;
        
        for (int j = 0; j < colonies.size(); j++) {

            if (traps.get(ColonyCount).isEmpty()) {
                System.out.println("Colony " + (ColonyCount + 1) + ": Safe");

            } else {
                // Collections.sort(traps.get(ColonyCount));
                System.out.print("Colony " + (ColonyCount + 1) + ": Dangerous. Cities on the dangerous path: [");
                for (int i : traps.get(ColonyCount)) {
                    if (i == traps.get(ColonyCount).get(traps.get(ColonyCount).size() - 1)) {
                        System.out.println((i + 1) + "]");
                    } else {
                        System.out.print((i + 1) + ", ");
                    }
                }
            }
            ColonyCount++;

        }

    }

}
