import java.util.*;
import java.io.*;
public class Handball {
    String Team_name;
    int Played_Matches;
    int Games_won;
    int Games_lost;
    int Games_draw;
    int Scores;
    int Negative_scores;
    int Ranking_score;

    public static List<Handball> TeamSetH = new ArrayList<Handball>() ;
        
    
        
    

    public Handball(String Teamname){
        this.Team_name = Teamname;
        this.Played_Matches = 0;
        this.Games_won = 0;
        this.Games_lost = 0;
        this.Games_draw = 0 ;
        this.Scores = 0;
        this.Negative_scores = 0;
        this.Ranking_score = 0;
    }

    public static void createHandballTeam(String filename)throws IOException{

        FileReader in = new FileReader(filename);
        Scanner scn = new Scanner(in);

        while (scn.hasNextLine()){
            
            String line = scn.nextLine();
            String[] line_array = line.split("\t");
            
            if (line_array[0].startsWith("H")){
               
                boolean Teamexist1 = true;
                boolean Teamexist2 = true;

                for (Handball team:TeamSetH){
                    
                    
                    if (team.Team_name.equals(line_array[1])){
                        Teamexist1 = false;
                    }
                    if (team.Team_name.equals(line_array[2])){
                        Teamexist2 = false;

                    }
                }

                if(Teamexist1){
                   Handball team = new Handball(line_array[1]);
                   TeamSetH.add(team);
                }
                if (Teamexist2){
                    Handball team2 = new Handball(line_array[2]);
                    TeamSetH.add(team2);
                    
                    
                }
            }
        }
        scn.close();

    }
    public static void CompareRanks(FileWriter out)throws IOException{
        
        ArrayList<Integer> Rankscores = new ArrayList<>();
        for (Handball team:TeamSetH){
            Rankscores.add(team.Ranking_score);
        }
        ArrayList<Integer> Averages = new ArrayList<>();
        Collections.sort(Rankscores,Collections.reverseOrder());


        for (int rankscore : Rankscores){ 
            for (Handball team: TeamSetH){
                if (team.Ranking_score == rankscore){
                    Averages.add(team.Scores-team.Negative_scores);
                }
            }
     }
 
    ArrayList<Integer> averages = new ArrayList<Integer>();
     
    for(Handball team1 : TeamSetH){
        averages.add(team1.Scores - team1.Negative_scores);
    }     
        Handball team1 = null;
        
        boolean samerankscore = false;

        try{
        for(int i=0; i<4;i++){
            for (int j =0;j<4;j++){
                if(Rankscores.get(i)==TeamSetH.get(j).Ranking_score){
                    if (i+1<4){
                    if(Rankscores.get(i)== Rankscores.get(i+1)){
                        team1 = TeamSetH.get(j);
                        samerankscore = true;
                        i++;
                        continue;
                        
                    }}

                    if(samerankscore){
                        Handball team2 = TeamSetH.get(j);
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
                        out.write(i+1+" "+TeamSetH.get(j).Team_name+"  "+TeamSetH.get(j).Played_Matches+" "+TeamSetH.get(j).Games_won+"  "+TeamSetH.get(j).Games_draw+" "+TeamSetH.get(j).Games_lost+" "+TeamSetH.get(j).Scores+":"+TeamSetH.get(j).Negative_scores+"    "+TeamSetH.get(j).Ranking_score+"\n");
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
