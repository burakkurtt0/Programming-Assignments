import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Food{
    String Id;
    String name;
    int calorie_count;


    public Food(String ID , String Name, int Calorie_Count){

        this.Id = ID;
        this.name = Name;
        this.calorie_count = Calorie_Count;
    }


public static Food FindFood(String file, String Id)throws IOException{
    FileReader in  = new FileReader(file);
    Scanner sc = new Scanner(in);
    Food food = null;

    while (sc.hasNextLine()){
        String line = sc.nextLine();
        String[] line_Array = line.split("\t");

        if (!line_Array[0].startsWith("1")){
            String FixedlineArray = line_Array[0].substring(3);
            if (FixedlineArray.equals(Id)){
                food = new Food(FixedlineArray,line_Array[1],Integer.parseInt(line_Array[2]));
            }
        }

        else if (line_Array[0].equals(Id)){
            food = new Food(line_Array[0],line_Array[1],Integer.parseInt(line_Array[2]));
            
        }
    }

    sc.close();
    return food;

}
}