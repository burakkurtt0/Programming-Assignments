import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Sports {

    String ID ;
    String Name;
    int Calorie_burn;


    public Sports(String id, String name , int calorie_burn){
        this.ID = id;
        this.Name = name;
        this.Calorie_burn = calorie_burn;
    }
    

    public static Sports FindSport(String file , String Id)throws IOException{
        FileReader in  = new FileReader(file);
        Scanner sc = new Scanner(in);
        Sports sport = null;

        while (sc.hasNextLine()){
            String line = sc.nextLine();
            String[] line_array = line.split("\t");

            if (!line_array[0].startsWith("2")){
                String FixedlineArray = line_array[0].substring(3);
                if (FixedlineArray.equals(Id)){
                    sport = new Sports(FixedlineArray,line_array[1],Integer.parseInt(line_array[2]));
                }
            }

            else if (line_array[0].equals(Id)){
                sport = new Sports(line_array[0],line_array[1],Integer.parseInt(line_array[2]));
            }
        }

        sc.close();
        return sport;

    }
}
