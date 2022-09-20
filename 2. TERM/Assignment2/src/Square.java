import java.io.*;
public interface Square {

    
    public int PurposeofSquare(GamePlayer currentPlayer,GamePlayer othPlayer,Banker banker, Square[] board, int dice,FileWriter out,GamePlayer[] PlayerList)throws IOException; // It returns jailcount whether it happened or not (0 for no jail situation)
    public int getId(); //Shows the square's location in map.
    public void setName(String name);
    public String getName();
    
}
