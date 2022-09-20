import java.io.*;
public class Company extends Lands{

    public Company(int id , String name , double cost){
        setId(id);
        setName(name);
        setCost(cost);
    }

    @Override
    public double CalculateRentCost(GamePlayer p1) {
       
        return 0;
    }

    public double CalculateRentCost(int dice){

        double rentCost;
        rentCost = dice * 4;
        return rentCost;

    }

    @Override
    public int PurposeofSquare(GamePlayer currentPlayer, GamePlayer othPlayer, Banker banker, Square[] board, int dice,FileWriter out,GamePlayer[] PlayerList) {
        try{
        if (currentPlayer.getOwnProperties().contains(this.getId())){
            if(Landspurposecalled>0){
                out.write(currentPlayer.getName()+"  "+"has"+"   "+ super.FindLandFromId(this.getId(), board)+"\n");
            }
            else{
            out.write(currentPlayer.getName() + "    "+dice + " "+currentPlayer.getPosition()+"  " +(int)PlayerList[0].getMoney()+"   " +(int)PlayerList[1].getMoney()+"    " + currentPlayer.getName()+"  "+"has"+"   "+ super.FindLandFromId(this.getId(), board)+"\n");

        }}

        else if (othPlayer.getOwnProperties().contains(this.getId())){
                Player.TradeMoney(currentPlayer, othPlayer, this.CalculateRentCost(dice));
                if (Landspurposecalled>0){
                    out.write(currentPlayer.getName()+ "  "+"paid"+"   "+ "rent"+"   "+"for"+"   "
                    +super.FindLandFromId(this.getId(), board)+"\n");
                }
                else{
                out.write(currentPlayer.getName() + "    "+dice + " "+currentPlayer.getPosition()+"  " +(int)PlayerList[0].getMoney()+"   " +(int)PlayerList[1].getMoney()+"    " +currentPlayer.getName()+ "  "+"paid"+"   "+ "rent"+"   "+"for"+"   "
                +super.FindLandFromId(this.getId(), board)+"\n");
        }}

        else{

            if (currentPlayer.getMoney() >= this.getCost()){

                

                Player.TradeMoney(currentPlayer, banker, this.getCost());
                currentPlayer.getOwnProperties().add(this.getId());
                if (Landspurposecalled>0){
                    out.write(currentPlayer.getName()+"  "+"bought"+"   "+ super.FindLandFromId(this.getId(), board)+"\n");
                }
                else{
                out.write( currentPlayer.getName() + "    "+dice + " "+currentPlayer.getPosition()+"  " +(int)PlayerList[0].getMoney()+"   " +(int)PlayerList[1].getMoney()+"    " +currentPlayer.getName()+"  "+"bought"+"   "+ super.FindLandFromId(this.getId(), board)+"\n");


            }}
            else{
                out.write(currentPlayer.getName() + "    "+dice + " "+currentPlayer.getPosition()+"  " +(int)PlayerList[0].getMoney()+"   " +(int)PlayerList[1].getMoney()+"    " +currentPlayer.getName()+ "  "+"goes  bankrupt"+"\n");
                return 100;
            }
        }
        return 0 ;}
        catch(IOException e){
            e.getStackTrace();
            return 0 ;
        }
    }

    
    
}
