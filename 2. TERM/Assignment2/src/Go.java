import java.io.FileWriter;
import java.io.IOException;

public class Go extends Special_Squares{

    public Go(){
        setId(1);
        setName("GO");
    }

    @Override
    public int PurposeofSquare(GamePlayer currentPlayer, GamePlayer othPlayer, Banker banker, Square[] board,
            int dice,FileWriter out,GamePlayer[] PlayerList) {try{
        
        
            Player.TradeMoney(banker,currentPlayer,200);
            if (specialpurposecalled>0){
                out.write(currentPlayer.getName()+"  "+ "is" + " "+"in" + "   "+"GO"+" "+"square\n");
            }
            else{
            out.write(currentPlayer.getName() + "    "+dice + " "+currentPlayer.getPosition()+"  " +(int)PlayerList[0].getMoney()+"   " +(int)PlayerList[1].getMoney()+"    " + currentPlayer.getName()+"  "+ "is" + " "+"in" + "   "+"GO"+" "+"square\n");
            }
            return 0;}
            catch (IOException e){
                e.getStackTrace();
                return 0;
            }
    }

   
    

    

    

    

    
    
}
