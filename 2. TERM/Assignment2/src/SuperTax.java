import java.io.FileWriter;
import java.io.IOException;

public class SuperTax extends Special_Squares{

    public SuperTax(){
        setId(39);
        setName("Super Tax");
    }

    @Override
    public int PurposeofSquare(GamePlayer currentPlayer, GamePlayer othPlayer, Banker banker, Square[] board,
            int dice,FileWriter out,GamePlayer[] PlayerList) {
                try{
        Player.TradeMoney(currentPlayer, banker, 100);
        if (specialpurposecalled>0){
            out.write(currentPlayer.getName() + "  "+ "paid" + " "+"Tax \n");
        }
        else{
        out.write(currentPlayer.getName() + "    "+dice + " "+currentPlayer.getPosition()+"  " +(int)PlayerList[0].getMoney()+"   " +(int)PlayerList[1].getMoney()+"    " +currentPlayer.getName() + "  "+ "paid" + " "+"Tax \n");
        }
        return 0 ;}
        catch (IOException e){
            e.getStackTrace();
            return 0;
        }
    }

    
    
}
