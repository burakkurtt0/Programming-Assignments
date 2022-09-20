import java.io.IOException;
import java.util.*;
import java.io.*;
public class Basketball {
    String Team_name;
    int Played_Matches;
    int Games_won;
    int Games_lost;
    int Games_draw;
    int Scores;
    int Negative_scores;
    int Ranking_score;

    public static List<Basketball> TeamSetB = new ArrayList<Basketball>() ;
        
    
        
    

    public Basketball(String Teamname){
        this.Team_name = Teamname;
        this.Played_Matches = 0;
        this.Games_won = 0;
        this.Games_lost = 0;
        this.Games_draw = 0 ;
        this.Scores = 0;
        this.Negative_scores = 0;
        this.Ranking_score = 0;
    }

    public static void createBasketballTeam(String filename)throws IOException{

        FileReader in = new FileReader(filename);
        Scanner scn = new Scanner(in);

        while (scn.hasNextLine()){
            
            String line = scn.nextLine();
            String[] line_array = line.split("\t");
            
            if (line_array[0].startsWith("B")){
               
                boolean Teamexist1 = true;
                boolean Teamexist2 = true;

                for (Basketball team:TeamSetB){
                    
                    
                    if (team.Team_name.equals(line_array[1])){
                        Teamexist1 = false;
                    }
                    if (team.Team_name.equals(line_array[2])){
                        Teamexist2 = false;

                    }
                }

                if(Teamexist1){
                   Basketball team = new Basketball(line_array[1]);
                   TeamSetB.add(team);
                }
                if (Teamexist2){
                    Basketball team2 = new Basketball(line_array[2]);
                    TeamSetB.add(team2);
                    
                    
                }
            }
        }
        scn.close();

    }
    public static void CompareRanks(FileWriter out)throws IOException{
        
        ArrayList<Integer> Rankscores = new ArrayList<>();
        for (Basketball team:TeamSetB){
            Rankscores.add(team.Ranking_score);
        }
        ArrayList<Integer> Averages = new ArrayList<>();
        Collections.sort(Rankscores,Collections.reverseOrder());


        for (int rankscore : Rankscores){ 
            for (Basketball team: TeamSetB){
                if (team.Ranking_score == rankscore){
                    Averages.add(team.Scores-team.Negative_scores);
                }
            }
     }
 
    ArrayList<Integer> averages = new ArrayList<Integer>();
     
    for(Basketball team1 : TeamSetB){
        averages.add(team1.Scores - team1.Negative_scores);
    }     
        Basketball team1 = null;
        
        boolean samerankscore = false;

        try{
        for(int i=0; i<4;i++){
            for (int j =0;j<4;j++){
                if(Rankscores.get(i)==TeamSetB.get(j).Ranking_score){
                    if (i+1<4){
                    if(Rankscores.get(i)== Rankscores.get(i+1)){
                        team1 = TeamSetB.get(j);
                        samerankscore = true;
                        i++;
                        continue;
                        
                    }}

                    if(samerankscore){
                        Basketball team2 = TeamSetB.get(j);
                        if((team2.Scores-team2.Negative_scores)> (team1.Scores-team1.Negative_scores)){
                            out.write(i+" "+team2.Team_name+" "+team2.Played_Matches+"    "+team2.Games_won+" "+team2.Games_draw+"    "+team2.Games_lost+"    "+team2.Scores+":"+team2.Negative_scores+"  "+team2.Ranking_score+"\n");
                            out.write(i+1+" "+team1.Team_name+" "+team1.Played_Matches+"    "+team1.Games_won+" "+team1.Games_draw+"    "+team1.Games_lost+"    "+team1.Scores+":"+team1.Negative_scores+"  "+team1.Ranking_score+"\n");
                            samerankscore = false;
                        }
                        else{
                            out.write(i+" "+team1.Team_name+" "+team1.Played_Matches+"    "+team1.Games_won+" "+team1.Games_draw+"    "+team1.Games_lost+"    "+team1.Scores+":"+team1.Negative_scores+"  "+team1.Ranking_score+"\n");
                            samerankscore=false;
                            out.write(i+1+" "+team2.Team_name+" "+team2.Played_Matches+"    "+team2.Games_won+" "+team2.Games_draw+"    "+team2.Games_lost+"    "+team2.Scores+":"+team2.Negative_scores+"  "+team2.Ranking_score+"\n");
                        }
                        

                    }
                    else{
                        out.write(i+1+" "+TeamSetB.get(j).Team_name+"  "+TeamSetB.get(j).Played_Matches+" "+TeamSetB.get(j).Games_won+"  "+TeamSetB.get(j).Games_draw+" "+TeamSetB.get(j).Games_lost+" "+TeamSetB.get(j).Scores+":"+TeamSetB.get(j).Negative_scores+"    "+TeamSetB.get(j).Ranking_score+"\n");
                    }

                }
            }
        }
                    
                    

                
            
        
}
catch (IndexOutOfBoundsException e){
    boolean error = true;
}

        out.close();
        }

        
        

                



            
    }

 


    
        
    
                    
                    
                
            
        

       

        
        

    

