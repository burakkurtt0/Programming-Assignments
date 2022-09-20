import java.io.*;
public class FreeParking extends Special_Squares{

    public FreeParking(){
        setId(21);
        setName("Free Parking");
    }

    @Override
    public int PurposeofSquare(GamePlayer currentPlayer, GamePlayer othPlayer, Banker banker, Square[] board,
            int dice,FileWriter out,GamePlayer[] PlayerList) {try{
        
       int jailcount = GameMethods.jail(currentPlayer,1);
       if (specialpurposecalled>0){
           out.write(currentPlayer.getName()+"  "+ "is" + " "+"in" + "   "+"Free" +"   "+"Parking" +"\n");
       }
       else{
       out.write(currentPlayer.getName() + "    "+dice + " "+currentPlayer.getPosition()+"  " +(int)PlayerList[0].getMoney()+"   " +(int)PlayerList[1].getMoney()+"    " + currentPlayer.getName()+"  "+ "is" + " "+"in" + "   "+"Free" +"   "+"Parking" +"\n");
        }
       return jailcount;}
       catch(IOException e){
           e.getStackTrace();
           return 0;
       }

    }


    
    
}
