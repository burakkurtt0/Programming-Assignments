import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class general_funcs {



    public static void play_match(String file)throws IOException{

        FileReader in = new FileReader(file);
        Scanner scn = new Scanner(in);

        while(scn.hasNextLine()){

            String line = scn.nextLine();
            String[] line_Array = line.split("\t");
            String[] scores = line_Array[3].split(":");

            if (line_Array[0].equals("F")){
                for (Football team:Football.TeamSetF){
                    if (team.Team_name.equals(line_Array[1])){
                        for (Football team2:Football.TeamSetF){
                            if(team2.Team_name.equals(line_Array[2])){
                                team.Played_Matches+=1;
                                team2.Played_Matches+=1;
                                team.Scores += Integer.parseInt(scores[0]);
                                team.Negative_scores += Integer.parseInt(scores[1]);
                                team2.Scores += Integer.parseInt(scores[1]);
                                team2.Negative_scores += Integer.parseInt(scores[0]);

                                if (Integer.parseInt(scores[0])>Integer.parseInt(scores[1])){
                                    team.Games_won+=1;
                                    team.Ranking_score +=3;
                                    team2.Games_lost+=1;
                                }
                              else if (Integer.parseInt(scores[1])>Integer.parseInt(scores[0])){
                                team.Games_lost+=1;
                                team2.Games_won +=1;
                                team2.Ranking_score+=3;
                                }
                                else{
                                    team.Games_draw+=1;
                                    team.Ranking_score+=1;
                                    team2.Games_draw+=1;
                                    team2.Ranking_score+=1;
                                }
                            }} 
                        }
                    }
                }


            

                if (line_Array[0].equals("V")){
                    for (Volleyball team:Volleyball.TeamSetV){
                        if (team.Team_name.equals(line_Array[1])){
                            for (Volleyball team2:Volleyball.TeamSetV){
                                if(team2.Team_name.equals(line_Array[2])){
                                    team.Played_Matches+=1;
                                    team2.Played_Matches+=1;
                                    team.Scores += Integer.parseInt(scores[0]);
                                    team.Negative_scores += Integer.parseInt(scores[1]);
                                    team2.Scores += Integer.parseInt(scores[1]);
                                    team2.Negative_scores += Integer.parseInt(scores[0]);
    
                                    if (Integer.parseInt(scores[0])==3 && ((Integer.parseInt(scores[1])==1 || Integer.parseInt(scores[1])==0))){
                                        team.Games_won+=1;
                                        team.Ranking_score+=3;
                                        team2.Games_lost+=1;
                                    }
                                   else if (Integer.parseInt(scores[0])==3 && Integer.parseInt(scores[1])==2){
                                    team.Games_won+=1;
                                    team.Ranking_score+=2;
                                    team2.Ranking_score+=1;
                                    team2.Games_lost +=1;
                                    }
                                    else if (Integer.parseInt(scores[1])==3 && Integer.parseInt(scores[0])==2){
                                        team.Games_lost+=1;
                                        team.Ranking_score+=1;
                                        team2.Games_won+=1;
                                        team2.Ranking_score+=2;
                                    }
                                    else if (Integer.parseInt(scores[1])==3 && ((Integer.parseInt(scores[1])==1 || Integer.parseInt(scores[0])==0))){ 
                                        team.Games_lost+=1;
                                        
                                        team2.Games_won+=1;
                                        team2.Ranking_score+=3;
                                    }
                                
                                    }
                                }}
                            }
                        }
                    

            if (line_Array[0].equals("B")){
                for (Basketball team:Basketball.TeamSetB){
                    if (team.Team_name.equals(line_Array[1])){
                        for (Basketball team2:Basketball.TeamSetB){
                            if(team2.Team_name.equals(line_Array[2])){
                                team.Played_Matches+=1;
                                team2.Played_Matches+=1;
                                team.Scores += Integer.parseInt(scores[0]);
                                team.Negative_scores += Integer.parseInt(scores[1]);
                                team2.Scores += Integer.parseInt(scores[1]);
                                team2.Negative_scores += Integer.parseInt(scores[0]);

                                if (Integer.parseInt(scores[0])>Integer.parseInt(scores[1])){
                                    team.Games_won+=1;
                                    team.Ranking_score+=2;
                                    team2.Games_lost+=1;
                                    team2.Ranking_score+=1;
                                }
                              else if (Integer.parseInt(scores[1])>Integer.parseInt(scores[0])){
                                team.Games_lost+=1;
                                team.Ranking_score+=1;
                                team2.Games_won +=1;
                                team2.Ranking_score+=2;
                                }
                                else{
                                    team.Games_draw+=1;
                                    team2.Games_draw+=1;
                                }
                            } }
                        }
                    }
                }

                if (line_Array[0].equals("H")){
                    for (Handball team:Handball.TeamSetH){
                        if (team.Team_name.equals(line_Array[1])){
                            for (Handball team2:Handball.TeamSetH){
                                if(team2.Team_name.equals(line_Array[2])){
                                    team.Played_Matches+=1;
                                    team2.Played_Matches+=1;
                                    team.Scores += Integer.parseInt(scores[0]);
                                    team.Negative_scores += Integer.parseInt(scores[1]);
                                    team2.Scores += Integer.parseInt(scores[1]);
                                    team2.Negative_scores += Integer.parseInt(scores[0]);
    
                                    if (Integer.parseInt(scores[0])>Integer.parseInt(scores[1])){
                                        team.Games_won+=1;
                                        team.Ranking_score+=2;
                                        team2.Games_lost+=1;
                                    }
                                  else if (Integer.parseInt(scores[1])>Integer.parseInt(scores[0])){
                                    team.Games_lost+=1;
                                    team2.Games_won +=1;
                                    team2.Ranking_score+=2;
                                    }
                                    else{
                                        team.Games_draw+=1;
                                        team.Ranking_score+=1;
                                        team2.Ranking_score+=1;
                                        team2.Games_draw+=1;
                                    }
                                }} 
                            }
                        }
                    }
 }scn.close();
}





}
    

