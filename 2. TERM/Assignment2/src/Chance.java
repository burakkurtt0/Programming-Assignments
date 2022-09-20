import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class Chance extends Special_Squares{


    public static int i =0; //Represents which card will be played (get +1 after card played).

    public Chance(int id){
        setId(id);
        setName("Chance");
    }

    ListJsonReader listjson = new ListJsonReader();
    PropertyJsonReader propertyjson = new PropertyJsonReader();
    LinkedList<Properties> list = propertyjson.properties;
    

    @Override
    public int PurposeofSquare(GamePlayer currentPlayer, GamePlayer othPlayer, Banker banker, Square[] board, int dice,
            FileWriter out, GamePlayer[] PlayerList) {
                try{
        String chance = listjson.chance.get(i);
        
        if (chance.startsWith("Advance to Go")){
            currentPlayer.setPosition(1);
            Player.TradeMoney(banker,currentPlayer, 200);
            out.write(currentPlayer.getName() + "    "+dice + " "+currentPlayer.getPosition()+"  " +(int)PlayerList[0].getMoney()+"   " +(int)PlayerList[1].getMoney()+"    " +currentPlayer.getName()+"  "+ "draw"+"   Advance"+"   "+"to"+"   "+"Go"+" (collect $200)"+"\n");
        }

        else if(chance.startsWith("Advance to Leicester")){
            int id = 0;
            for (Square land : board){
                if (land.getName().equals("Leicester Square")){
                     id = land.getId();


                     if (currentPlayer.getPosition() > id){
                        currentPlayer.setPosition(id);
                        Player.TradeMoney(banker, currentPlayer, 200);
                        }
                        else{
                            currentPlayer.setPosition(id);
                        }
                    
                    
                     for (Properties prop: list){
                         if (prop.getId() == id){
                     
                    
    
        
                    if (currentPlayer.getOwnProperties().contains(land.getId())){
                        out.write(currentPlayer.getName() + "    "+dice + " "+currentPlayer.getPosition()+"  " +(int)PlayerList[0].getMoney()+"   " +(int)PlayerList[1].getMoney()+"    " +currentPlayer.getName()+"  "+"draw"+"   "+ "Advance"+"   "+"to"+"   "
                        +"Leicester"+" "+"Square"+" ");
                            out.write(currentPlayer.getName() + "  "+"has"+"   "+ land.getName()+"\n");
                         }
                 
                    else if (othPlayer.getOwnProperties().contains(land.getId())){
                                 Player.TradeMoney(currentPlayer, othPlayer,prop.CalculateRentCost(currentPlayer));
                                 out.write(currentPlayer.getName() + "    "+dice + " "+currentPlayer.getPosition()+"  " +(int)PlayerList[0].getMoney()+"   " +(int)PlayerList[1].getMoney()+"    " +currentPlayer.getName()+"  "+"draw"+"   "+ "Advance"+"   "+"to"+"   "
                                 +"Leicester"+" "+"Square"+" ");
                                 out.write(currentPlayer.getName() + "  "+"paid"+"   "+ "rent"+"   "+"for"+"   "
                                 +land.getName()+"\n");
                         }
                 
                    else{
                 
                        if (currentPlayer.getMoney() > prop.getCost()){
                 
                            Player.TradeMoney(currentPlayer, banker, prop.getCost());
                            currentPlayer.getOwnProperties().add(land.getId());
                            out.write(currentPlayer.getName() + "    "+dice + " "+currentPlayer.getPosition()+"  " +(int)PlayerList[0].getMoney()+"   " +(int)PlayerList[1].getMoney()+"    " +currentPlayer.getName()+"  "+"draw"+"   "+ "Advance"+"   "+"to"+"   "
                            +"Leicester"+" "+"Square"+" ");
                            out.write(currentPlayer.getName() + "  "+"bought"+"   "+ land.getName()+"\n");
                 
                         }}}} 
                }
            }

        

        }
        else if (chance.startsWith("Go")){
            
            currentPlayer.setPosition(currentPlayer.getPosition()-3);
            out.write(currentPlayer.getName() + "    "+dice + " "+currentPlayer.getPosition()+"  " +(int)PlayerList[0].getMoney()+"   " +(int)PlayerList[1].getMoney() + "   "+   currentPlayer.getName() + "  "+"draw"+"   "+ "Go"+"   "+"back"+"   "
                +"3"+"  "+"spaces "+"   ");
                for (Square land : board){
                    if (currentPlayer.getPosition() == land.getId()){
                        Lands.Landspurposecalled++;
                        Special_Squares.specialpurposecalled++;
                        land.PurposeofSquare(currentPlayer, othPlayer, banker, board, dice, out, PlayerList);
                    }
                }

                


        }
        
        else if (chance.startsWith("Pay")){

            currentPlayer.setMoney(currentPlayer.getMoney()-15);

            out.write(currentPlayer.getName() +"draw"+" "+ "Pay" +"   "+"poor" + "  "+"tax"+"   "+"(-15$)");

        }

        else if (chance.startsWith("Your building")){

            currentPlayer.setMoney(currentPlayer.getMoney()+150);
            out.write(currentPlayer.getName() +"draw"+" "+ "Your" +"   "+"building" + "  "+"loan"+"   "+"matures"+" "+"(+150$)");
        }

        else if (chance.startsWith("You have")){
            currentPlayer.setMoney(currentPlayer.getMoney()+100);
            out.write(currentPlayer.getName() +"draw"+" "+ "You" +"   "+"have" + "  "+"won"+"   "+"crossword"+" "+"competition."+"  "+"(+100$)");


        }

        i++;
        return 0;}

        catch (IOException e){
            e.getStackTrace();
            return 0;
        }
    }
     



    


}
