public class Properties extends Lands{

    public Properties(int id , String name, double cost){
        super(id,name,cost);
    }

    

    public double Rentcost(){
        if (this.getCost()<2000){
            double rentcost = (double)(this.getCost() * 4/10);
            return rentcost;
        }

        else if (this.getCost()>2001 && this.getCost()<3000){
            double rentcost = (double)(this.getCost() * 3/10);
            return rentcost;
        }

        else{
            double rentcost = (double)(this.getCost() * 35/100);
            return rentcost;
        }
        

        
    }
}
