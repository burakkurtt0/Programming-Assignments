

public abstract class Lands {
    private int id ;
    private String name;
    private double cost;

    public Lands(int ID , String Name , double Cost){

        this.id = ID;
        this.name = Name;
        this.cost = Cost;
    }

    

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    
    public abstract double Rentcost();
    

    
}
