public class Railroad extends Lands{

    public Railroad(int id , String name , double cost){
        super(id,name,cost);
    }

    @Override
    public double Rentcost() {
        
        return 0;
    }

    public double Rentcost(Players player){
        double rentcost;

        
        rentcost = (double)(player.getRailRoadCount() * 25);

        return rentcost;
    }

    
    
}
