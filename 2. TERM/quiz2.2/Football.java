import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class Football {

    String Team_name;
    int Played_Matches;
    int Games_won;
    int Games_lost;
    int Games_draw;
    int Scores;
    int Negative_scores;
    int Ranking_score;

    public static List<Football> TeamSetF = new ArrayList<Football>() ;
        
    
        
    

    public Football(String Teamname){
        this.Team_name = Teamname;
        this.Played_Matches = 0;
        this.Games_won = 0;
        this.Games_lost = 0;
        this.Games_draw = 0 ;
        this.Scores = 0;
        this.Negative_scores = 0;
        this.Ranking_score = 0;
    }

    public static void createFootballTeam(String filename)throws IOException{

        FileReader in = new FileReader(filename);
        Scanner scn = new Scanner(in);

        while (scn.hasNextLine()){
            
            String line = scn.nextLine();
            String[] line_array = line.split("\t");
            
            if (line_array[0].startsWith("F")){
               
                boolean Teamexist1 = true;
                boolean Teamexist2 = true;

                for (Football team:TeamSetF){
                    
                    
                    if (team.Team_name.equals(line_array[1])){
                        Teamexist1 = false;
                    }
                    if (team.Team_name.equals(line_array[2])){
                        Teamexist2 = false;

                    }
                }

                if(Teamexist1){
                   Football team = new Football(line_array[1]);
                   TeamSetF.add(team);
                }
                if (Teamexist2){
                    Football team2 = new Football(line_array[2]);
                    TeamSetF.add(team2);
                    
                    
                }
            }
        }
        scn.close();

    }
    public static void CompareRanks(FileWriter out)throws IOException{
        
        ArrayList<Integer> Rankscores = new ArrayList<>();
        for (Football team:TeamSetF){
            Rankscores.add(team.Ranking_score);
        }

        Collections.sort(Rankscores,Collections.reverseOrder());
        int i =0;
        for (int rankscore : Rankscores){
            i++;
            for (Football team: TeamSetF){
                if (rankscore == team.Ranking_score){
                    out.write(i+"."+"   "+team.Team_name+"  "+team.Played_Matches+" "+team.Games_won+"  "+team.Games_draw+" "+team.Games_lost+" "+
                    team.Scores+":"+team.Negative_scores+"  "+team.Ranking_score+"\n");
                }
            }
        }

       out.close();

        
        }
    }

