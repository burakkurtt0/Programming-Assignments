import java.util.*;
import java.io.*;

public class Play {

    public static  FileReader CreateFile(String file ) throws IOException,NotMatchException{

        FileReader in = new FileReader(file);
        return in;
    }
    

    public static void PlayGame(Map map, FileReader in ,FileWriter out, Player player) throws IOException{

        
        out.write("Game grid:\n");
        out.write("\n");
        out.write(map.createStringMap());
        out.write("\n");

        


        String checkpoint ="a";
        Scanner scn = new Scanner(in);
        while (scn.hasNextLine()){
            String line = scn.nextLine();
            player.setTemp_score(0);
            if (checkpoint.equals("E")){
                out.write("E" + "\n");
                out.write("\n");
                out.write("Total score: " + player.getScore() +   " points\n" + "\n");
                out.write("Enter name: "+ line + "\n");
                player.setName(line);
                break;
            }

            out.write("Select coordinate or enter E to end the game: ");

            if (line.equals("E")){
                checkpoint = "E";
                continue; 
            }

            int[] coord = FindCoordinates(line);
            
            out.write( coord[0] + " " +coord[1]+"\n" + "\n");
            try{
            MatchMethods.DeleteJewels(map.getMap(), coord[0], coord[1],  player);
            MatchMethods.DropElements(map.getMap());
            out.write(map.createStringMap());
            out.write("\n");
            
            out.write("Score : "+ player.getTemp_score() + "\n" + "\n");
        }
        catch (NotMatchException e){
            out.write("Please enter a valid coordinate!\n\n");
        }
        }
        scn.close();
        out.close();

    }




    public static int[] FindCoordinates(String line){
        int[] coord = new int[2];

        String[] lineArray = line.split(" ");
       
        coord[0] = Integer.parseInt(lineArray[0]);
        coord[1] = Integer.parseInt(lineArray[1]);

        return coord;

    }


    public static void LeaderBoard(FileWriter out, Player player) throws IOException{
      
        out.write("\n"+player.getName() + " " + player.getScore());
        out.close();  
    }

    public static void FindLeaderBoardRank(String monitoring,String leaderboard, Player player) throws IOException{
        FileWriter out = new FileWriter(monitoring, true);
        FileReader leaderb = new FileReader(leaderboard);
        ArrayList<Player> Playerlist = new ArrayList<>();
        Scanner scn = new Scanner(leaderb);
        
        while (scn.hasNextLine()){
            try{
            String line = scn.nextLine();
            Player p = new Player();
            p.setName(line.split(" ")[0]);
            p.setScore(Integer.parseInt(line.split(" ")[1]));
            Playerlist.add(p);}
            catch (IndexOutOfBoundsException e){
                continue;
            }
        }
        scn.close();

        Collections.sort(Playerlist,Collections.reverseOrder());

        int i = 0;
        for (Player p : Playerlist){
            if (p.getScore() == player.getScore()){
                break;
            }
            i++;
        }


        if (i == 0){
            
            out.write("\n" + "Your rank is " +(i+1) +"/"+Playerlist.size()+", your score is "+ (player.getScore() - Playerlist.get(i+1).getScore()) + " points higher than " + Playerlist.get(i+1).getName()+"\n");
        }

        else if (i == Playerlist.size()-1){
            out.write("\n" + "Your rank is " +(i+1) +"/"+Playerlist.size()+", your score is "+ (player.getScore() - Playerlist.get(i-1).getScore()) + " points lower  than " + Playerlist.get(i-1).getName()+ "\n");
        }

        else{
            out.write("\n" + "Your rank is " +(i+1) +"/"+Playerlist.size()+", your score is "+ (Math.abs(player.getScore() - Playerlist.get(i-1).getScore())) + " points lower than " + Playerlist.get(i-1).getName()+" and "+(player.getScore() - Playerlist.get(i+1).getScore()) + " points higher than " + Playerlist.get(i+1).getName()+"\n");
        }
       
        out.write("\nGood bye!");

        out.close();
       

        





    }
}