import java.io.FileWriter;

public abstract class Lands implements Square{

    // Properties , Railroads and Companies are subclasses of Lands.

    public static int Landspurposecalled= 0;
    private int id;
    private String name;
    private double cost;
    
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
    @Override
    public abstract int PurposeofSquare(GamePlayer currentPlayer, GamePlayer othPlayer, Banker banker, Square[] board, int dice,FileWriter out,GamePlayer[] PlayerList) ;


    public abstract double CalculateRentCost(GamePlayer p1);


    public String FindLandFromId(int id, Square[] board){
        String name = "";
        for (Square land: board){

            if (id == land.getId()){
                name = land.getName();
                return name;
            }
        }
        return name;
    }
    

        
    
    
    
        
        
    


    
        
        
    
    
    
}
