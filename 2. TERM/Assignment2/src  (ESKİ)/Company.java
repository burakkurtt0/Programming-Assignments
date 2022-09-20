public class Company extends Lands{

    public Company(int id , String name , double cost){
        super(id, name, cost);
    }

    @Override
    public double Rentcost() {
        
        double rentcost;
        rentcost = (double) (GamePlayer.RollDice() * 4);
        return rentcost;
    }

}
