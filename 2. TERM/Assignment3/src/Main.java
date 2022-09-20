
import java.io.*;
import java.io.IOException;

public class Main{

public static void main(String[] args) throws IOException, NotMatchException {
    FileReader in = Play.CreateFile(args[1]);
    FileWriter leaderboard = new FileWriter("leaderboard.txt",true); 
    FileWriter out = new FileWriter("monitoring.txt");
    Map map = new Map(args[0]);
    Player player = new Player();

    


    Play.PlayGame(map, in,out,player);
    Play.LeaderBoard(leaderboard, player);
    Play.FindLeaderBoardRank("monitoring.txt", "leaderboard.txt", player);


    
   
    


    
    
    

}}