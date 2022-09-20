import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class People{

    String ID;
    String name;
    String gender;
    int weight;
    int height;
    int age;
    int taken_calorie;
    int burned_calorie;
    int result_calorie;
    
    public People(String ID, String name , String gender, int weight , int height , int age){

        this.ID = ID;
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.taken_calorie = 0 ;
        this.burned_calorie = 0 ;
        this.result_calorie = 0;
    }
public int set_result_Calorie(People ppl){

    int neededcalorie = dailyCalorieNeeds(ppl);
    int resultcalorie = this.taken_calorie - this.burned_calorie - neededcalorie;
    this.result_calorie = resultcalorie;
    return resultcalorie;
}
public static People Find_people(String file, String Id,Set<People> peopleset)throws IOException{
    FileReader in = new FileReader(file);
    People ppl =null;
    Scanner scn = new Scanner(in);
    while (scn.hasNextLine()){
        
        String line = scn.nextLine();
        String[] line_array = line.split("\t");

        if (!line_array[0].startsWith("1")){
            boolean isPeople = true;
            String fixedLinearray = line_array[0].substring(3);
            if (fixedLinearray.equals(Id)){
                for (People pepl:peopleset){
                    if (pepl.ID.equals(fixedLinearray)){
                        
                        isPeople=false;
                        scn.close();
                        return pepl;
                    }
                }
            if (isPeople){ ppl = new People(fixedLinearray,line_array[1],line_array[2],Integer.parseInt(line_array[3]),Integer.parseInt(line_array[4]),
                (2022-Integer.parseInt(line_array[5])));} 
        }}
        else if (line_array[0].equals(Id)){
            boolean isPeople = true;
            for (People pepl:peopleset){
                if (pepl.ID.equals(line_array[0])){
                    isPeople = false;
                    scn.close();
                    return pepl;
                }
            }
            if (isPeople){ppl = new People(line_array[0],line_array[1],line_array[2],Integer.parseInt(line_array[3]),Integer.parseInt(line_array[4]),
                (2022-Integer.parseInt(line_array[5])));}
        }
    }
    scn.close();
    return ppl;
}

public static int  dailyCalorieNeeds(People ppl){
    double calorie;
    if (ppl.gender.equals("male")){
         calorie = (66 + 13.75 * ppl.weight + 5*ppl.height - 6.8*ppl.age);
    }
    else{
         calorie = 665 + 9.6*ppl.weight + 1.7*ppl.height - 4.7*ppl.age;
    }
    int needed_calorie = (int) Math.round(calorie);

    return needed_calorie;
    }
}
