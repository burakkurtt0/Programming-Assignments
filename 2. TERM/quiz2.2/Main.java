import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException{

        String file = "fixtures.txt";
        FileWriter football = new FileWriter("Football.txt");
        FileWriter volleyball = new FileWriter("Volleyball.txt");
        FileWriter handball = new FileWriter("Handball.txt");
        FileWriter basketball = new FileWriter("Basketball.txt");
        Football.createFootballTeam(file);
        Volleyball.createVolleyballTeam(file);
        Handball.createHandballTeam(file);
        Basketball.createBasketballTeam(file);

        general_funcs.play_match(file);

        Football.CompareRanks(football);
        Volleyball.CompareRanks(volleyball);
        Basketball.CompareRanks(basketball);
        Handball.CompareRanks(handball);

        
        
        

        



    }
    
}

