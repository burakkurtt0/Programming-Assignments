import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args){
        ArrayList<People> peopleList = new ArrayList<People>();

        try{
            String peoplefile = "people.txt";
            String sportsfile = "sport.txt";
            String foodsfile = "food.txt";
            FileReader input = new FileReader(args[0]);
            FileWriter output = new FileWriter("monitoring.txt");

            Scanner scn = new Scanner(input);
            int i = 1;
            while (scn.hasNextLine()){
            
                People people;
                Food food;
                Sports sport;
                String line = scn.nextLine();
                String[] line_Array = line.split("\t");


                if (i!=1){
                    output.write("***************\n");}
                
               
                if (line_Array[0].startsWith("printW")){
                    int Warn_count = 0;
                   for (People ppl:peopleList){
                       int result_calorie = ppl.set_result_Calorie(ppl);
                       if (ppl.result_calorie>0){
                           Warn_count+=1;
                           int dailycalorieneeds=People.dailyCalorieNeeds(ppl);
                           output.write(ppl.name+"  "+ppl.age+" "+dailycalorieneeds+"kcal"+"   "+ppl.taken_calorie+"kcal"+"   "+ppl.burned_calorie+"kcal"+
                           "  "+"+"+result_calorie+"kcal"+"\n");
                       }

                   }
                   if (Warn_count ==0){
                       output.write("there"+"   "+"is"+"    "+"no"+"    "+"such"+"  "+"person."+"\n");
                   }
                    }
                else if (line_Array[0].startsWith("printL")){
                    for (People ppl: peopleList){
                        
                        int result_calorie = ppl.set_result_Calorie(ppl);
            
                        if (ppl.taken_calorie==0 && ppl.burned_calorie==0){
                            continue;}
                            
                        else{int dailycalorieneeds = People.dailyCalorieNeeds(ppl);
                            
                            output.write(ppl.name+"  "+ppl.age+"    "+dailycalorieneeds+"kcal"+"   "+ppl.taken_calorie+"kcal"+"   "+ppl.burned_calorie+"kcal"+
                            "  "+"+"+result_calorie+"kcal"+"\n");}
                    }
                    
                }
                else if (line_Array[0].startsWith("print(")){

                    String Idnumber = line_Array[0].substring(6,11);
                    people = People.Find_people(peoplefile, Idnumber,peopleList);
                    
                    int needed_calorie = People.dailyCalorieNeeds(people);
                    int result_calorie=people.set_result_Calorie(people);
                    output.write(people.name +" "+people.age+"  "+needed_calorie+"kcal"+"   "+people.taken_calorie+"kcal"+"    "+people.burned_calorie+"kcal" +"  " +"+"+ 
                    (result_calorie)+"kcal"+"\n");
                    

                }
                else{
                    if (!line_Array[0].startsWith("1")){String fixedLinearray = line_Array[0].substring(3);
                    people = People.Find_people(peoplefile, fixedLinearray,peopleList);
                   
                }
                    else{
                        people = People.Find_people(peoplefile, line_Array[0],peopleList); 
                    }
                    
                    
                    if (line_Array[1].startsWith("1")){
                        
                        food = Food.FindFood(foodsfile, line_Array[1]);
                        people.taken_calorie+= food.calorie_count * Integer.parseInt(line_Array[2]);
                        output.write(people.ID + "  " + "has" + "   "+ "taken"+ "   "+ (food.calorie_count * Integer.parseInt(line_Array[2]))+"kcal"+"   "+
                        "from"+"    " +food.name+"\n");
                        

                    }
                    else if (line_Array[1].startsWith("2")){

                        sport = Sports.FindSport(sportsfile, line_Array[1]);

                        people.burned_calorie+=(int)(sport.Calorie_burn * ((double)Integer.parseInt(line_Array[2]))/(double)60);

                        output.write(people.ID +"   " +"has"+"  "+"burned"+"    "+(int)(sport.Calorie_burn * ((double)Integer.parseInt(line_Array[2]))/(double)60) + 
                        "kcal"+"    "+"thanks"+"    "+"to"+"    "+sport.Name+"\n");
                        
                    }

                    
                    
                }
                i++;              
            }
            scn.close();
            output.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
