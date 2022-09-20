import java.util.*;
public abstract class Players{
    private double money;
    private ArrayList<Integer> properties;
    private int TurnCount;
    private int RailRoadCount;


    

    public int getTurnCount() {
        return TurnCount;
    }

    public void setTurnCount(int turnCount) {
        TurnCount = turnCount;
    }

    public int getRailRoadCount() {
        return RailRoadCount;
    }

    public void setRailRoadCount(int railRoadCount) {
        RailRoadCount = railRoadCount;
    }

    public ArrayList<Integer> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Integer> properties) {
        this.properties = properties;
    }

    public double getMoney(){
        return this.money;
    }

    public void  SetMoney(double x ){
        this.money = x;
    }

    public static void TradeMoney(Players player1,Players player2,double money){

        double newMoney1 = player1.getMoney() + money;
        player1.SetMoney(newMoney1);

        double newMoney2= player2.getMoney() - money;
        player2.SetMoney(newMoney2);

    }

    public abstract void BuyProperty(int PropertyNumber);



}