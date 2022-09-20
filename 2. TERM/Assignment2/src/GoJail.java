import java.io.FileWriter;
import java.io.IOException;

public class GoJail extends Special_Squares{

    public GoJail(){
        setId(31);
        setName("Go to Jail");
    }

    @Override
    public int PurposeofSquare(GamePlayer currentPlayer, GamePlayer othPlayer, Banker banker, Square[] board,
            int dice,FileWriter out,GamePlayer[] PlayerList) {try{
        
       int jailcount = GameMethods.jail(currentPlayer,3);
       currentPlayer.setPosition(11);
       if (specialpurposecalled>0){
           out.write(currentPlayer.getName()+"  "+ "went" + " "+"To" + "   "+"jail \n");
       }
       else{
       out.write(currentPlayer.getName() + "    "+dice + " "+currentPlayer.getPosition()+"  " +(int)PlayerList[0].getMoney()+"   " +(int)PlayerList[1].getMoney()+"    " + currentPlayer.getName()+"  "+ "went" + " "+"To" + "   "+"jail \n");
       }


       return jailcount;}
       catch (IOException e){
           e.getStackTrace();
           return 0;
       }

    }
    
}
