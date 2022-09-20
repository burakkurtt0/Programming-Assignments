import java.io.FileWriter;

public abstract class Special_Squares implements Square{

    // Other squares except Lands are subclasses of Special_Squares

    public static int specialpurposecalled = 0;
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public abstract int PurposeofSquare(GamePlayer currentPlayer, GamePlayer othPlayer, Banker banker, Square[] board,
            int dice,FileWriter out,GamePlayer[] PlayerList) ;
            
    @Override
    public void setName(String Name) {
    this.name = Name;
    }

    public String getName(){
        return name;
    }
        
        
    

    
    
}
