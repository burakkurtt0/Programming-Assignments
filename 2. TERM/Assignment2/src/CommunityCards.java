import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class CommunityCards extends Special_Squares{
    public static int i =0; //Represents which card will be played (get +1 after card played).
    public CommunityCards(int Id){
        setName("Community Chest");
        setId(Id);
    }
    
    ListJsonReader listjson = new ListJsonReader();
    PropertyJsonReader propertyjson = new PropertyJsonReader();
    LinkedList<Properties> list = propertyjson.properties;
    @Override
    public int PurposeofSquare(GamePlayer currentPlayer, GamePlayer othPlayer, Banker banker, Square[] board, int dice,
            FileWriter out, GamePlayer[] PlayerList) {try{


            String community = listjson.community.get(i);

            if (community.startsWith("Advance to Go")){
                currentPlayer.setPosition(1);
            Player.TradeMoney(banker,currentPlayer, 200);
            out.write(currentPlayer.getName() + "    "+dice + " "+currentPlayer.getPosition()+"  " +(int)PlayerList[0].getMoney()+"   " +(int)PlayerList[1].getMoney()+"    " +currentPlayer.getName()+"    draw     Community   Chest   -advance    to  Go    (Collect $200)"+"\n");

            }

            else if ( community.startsWith("Bank error")){
                currentPlayer.setMoney(currentPlayer.getMoney()+75);

                out.write(currentPlayer.getName() + "    "+dice + " "+currentPlayer.getPosition()+"  " +(int)PlayerList[0].getMoney()+"   " +(int)PlayerList[1].getMoney()+"    " +currentPlayer.getName()+"    draw Community   Chest   -Bank   error    (+$75)"+"\n");

            }

            else if (community.startsWith("Doctor")){
                currentPlayer.setMoney(currentPlayer.getMoney()-50);
                out.write(currentPlayer.getName() + "    "+dice + " "+currentPlayer.getPosition()+"  " +(int)PlayerList[0].getMoney()+"   " +(int)PlayerList[1].getMoney()+"    " +currentPlayer.getName()+"    draw Community   Chest   -Doctor's   fees    (-$50)"+"\n");
            }

            else if (community.startsWith("It")){
                Player.TradeMoney(othPlayer, currentPlayer, 10);
                out.write(currentPlayer.getName() + "    "+dice + " "+currentPlayer.getPosition()+"  " +(int)PlayerList[0].getMoney()+"   " +(int)PlayerList[1].getMoney()+"    " +currentPlayer.getName()+"    draw Community   Chest   -It is your birthday Collect $10 from each player"+"\n");
            }
            
            else if (community.startsWith("Grand")){
                Player.TradeMoney(othPlayer, currentPlayer, 50);
                out.write(currentPlayer.getName() + "    "+dice + " "+currentPlayer.getPosition()+"  " +(int)PlayerList[0].getMoney()+"   " +(int)PlayerList[1].getMoney()+"    " +currentPlayer.getName()+"    draw Community   Chest   -Grand Opera Night - collect $50 from every player for opening night seats"+"\n");
            }

            else if (community.startsWith("Income")){
                currentPlayer.setMoney(currentPlayer.getMoney()+20);
                out.write(currentPlayer.getName() + "    "+dice + " "+currentPlayer.getPosition()+"  " +(int)PlayerList[0].getMoney()+"   " +(int)PlayerList[1].getMoney()+"    " +currentPlayer.getName()+"    draw Community   Chest   -Income Tax refund - collect $20"+"\n");
            }

            else if (community.startsWith("Life")){
                currentPlayer.setMoney(currentPlayer.getMoney()+100);
                out.write(currentPlayer.getName() + "    "+dice + " "+currentPlayer.getPosition()+"  " +(int)PlayerList[0].getMoney()+"   " +(int)PlayerList[1].getMoney()+"    " +currentPlayer.getName()+"    draw Community   Chest   -Life Insurance Matures - collect $100"+"\n");
            }

            else if (community.startsWith("Pay Hospital")){
                currentPlayer.setMoney(currentPlayer.getMoney()-100);
                out.write(currentPlayer.getName() + "    "+dice + " "+currentPlayer.getPosition()+"  " +(int)PlayerList[0].getMoney()+"   " +(int)PlayerList[1].getMoney()+"    " +currentPlayer.getName()+"    draw Community   Chest   -Pay Hospital Fees of $100"+"\n");
            }

            else if (community.startsWith("Pay School")){
                currentPlayer.setMoney(currentPlayer.getMoney()-50);
                out.write(currentPlayer.getName() + "    "+dice + " "+currentPlayer.getPosition()+"  " +(int)PlayerList[0].getMoney()+"   " +(int)PlayerList[1].getMoney()+"    " +currentPlayer.getName()+"    draw Community   Chest   -Pay School Fees of $50"+"\n");
            }

            else if (community.startsWith("You")){
                currentPlayer.setMoney(currentPlayer.getMoney()+100);
                out.write(currentPlayer.getName() + "    "+dice + " "+currentPlayer.getPosition()+"  " +(int)PlayerList[0].getMoney()+"   " +(int)PlayerList[1].getMoney()+"    " +currentPlayer.getName()+"    draw Community   Chest   -You inherit $100"+"\n");

            }

            else if (community.startsWith("From")){
                currentPlayer.setMoney(currentPlayer.getMoney()+50);
                out.write(currentPlayer.getName() + "    "+dice + " "+currentPlayer.getPosition()+"  " +(int)PlayerList[0].getMoney()+"   " +(int)PlayerList[1].getMoney()+"    " +currentPlayer.getName()+"    draw Community   Chest   -From sale of stock you get $50"+"\n");
            }


        
        return 0; }
        catch (IOException e){
            e.getStackTrace();
            return 0;
        }
   

    

    


    
    
}}
