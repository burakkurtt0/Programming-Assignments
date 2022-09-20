import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class GameMethods {

    public static int TakeAction(GamePlayer currentPlayer, GamePlayer othPlayer , Banker banker, Square[] board , int position, int dice,FileWriter out,GamePlayer[] PlayerList)throws IOException{
        int jailcount = 0;
        
        for (Square sqr : board ){
            if (position == sqr.getId()){
                jailcount = sqr.PurposeofSquare(currentPlayer,othPlayer,banker,board,dice,out,PlayerList);




                // If PurposeofSquare is called 2 times in one row of command (for example chance cards), these integers prevent printing same sentence again and again in output.
                Lands.Landspurposecalled = 0;
                Special_Squares.specialpurposecalled=0;

                

                currentPlayer.setTurncount(currentPlayer.getTurncount()+1);
                
            }
        }return jailcount;

    }
    

    public static int jail(GamePlayer currentPlayer,int turn){
        currentPlayer.setJailStatus(true);
        return turn + currentPlayer.getTurncount();

    }



    // Showing player's properties. (It is used in show() method)
    public static String ShowProperties(GamePlayer player , Square[] board){
        ArrayList<String> propertiesWithNames =new ArrayList<>();

        for(int i  : player.getOwnProperties()){
            for (Square land : board){
                if (i == land.getId()){
                    propertiesWithNames.add(land.getName());

                }
            }
        } 

        String properties_string = "";
        for (String landname : propertiesWithNames){
            properties_string += landname+", ";
        }
        if (properties_string.length()>5){
        properties_string = properties_string.substring(0,properties_string.length()-2);}

        return (player.getName()+"   "+(int)(player.getMoney())+"   "+"have: "+properties_string);
    }



    // show() method
    public static void Show(GamePlayer[] PlayerList, Square[] board ,Banker bank , FileWriter out ){
    try{
    out.write("----------------------------------------------------------------------\n");
    out.write(GameMethods.ShowProperties(PlayerList[0],board)+"\n");
    out.write(GameMethods.ShowProperties(PlayerList[1], board)+"\n");
    out.write(bank.getName() + " "+(int)bank.getMoney()+"\n");

    if (PlayerList[0].getMoney()>PlayerList[1].getMoney()){
        out.write("Winner "+PlayerList[0].getName() +"\n");
    }
    else{
        out.write("Winner "+PlayerList[1].getName() +"\n");
    }
    out.write("----------------------------------------------------------------------\n");
}

    catch(IOException e){
        e.getStackTrace();
        
    }
    }


    // show() method if any player has 0 or less than 0 money (Game End show() method)
    public static void Show(GamePlayer[] PlayerList, Square[] board ,Banker bank , FileWriter out,GamePlayer winner){
        try{
            out.write("----------------------------------------------------------------------\n");
            out.write(GameMethods.ShowProperties(PlayerList[0],board)+"\n");
            out.write(GameMethods.ShowProperties(PlayerList[1], board)+"\n");
            out.write(bank.getName() + " "+(int)bank.getMoney()+"\n");
        
            out.write("Winner   "+ winner.getName()+"\n");
            
            out.write("----------------------------------------------------------------------\n");
        }
        
            catch(IOException e){
                e.getStackTrace();
                
            }
    }
}
