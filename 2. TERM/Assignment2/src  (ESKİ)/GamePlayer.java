import java.util.*;


public class GamePlayer extends Players{

    

    public GamePlayer(){
        SetMoney(15000);
        ArrayList<Integer> properties = new ArrayList<>();
        setProperties(properties);
        
    }

    
    

   


    public static int RollDice(){
        int x = (int)(Math.random()*((6-1)+1))+1;
        return x;
    }




    @Override
    public void BuyProperty(int PropertyNumber) {
        getProperties().add(PropertyNumber);
        
    }

    public static void main(String[] args) {
        Players player1 = new GamePlayer();
        PropertyJsonReader newreader = new PropertyJsonReader();
        newreader.ListToArray();
        
        

        
        }
    }

