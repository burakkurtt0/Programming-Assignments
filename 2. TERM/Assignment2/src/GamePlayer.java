import java.util.*;
public class GamePlayer extends Player{
    private int position;
    private ArrayList<Integer> OwnProperties;
    private int RailRoadcount;
    private boolean jailStatus;
    private int turncount;

    
    public boolean isJailStatus() {
        return jailStatus;
    }

    public void setJailStatus(boolean jailStatus) {
        this.jailStatus = jailStatus;
    }

    public int getTurncount() {
        return turncount;
    }

    public void setTurncount(int turncount) {
        this.turncount = turncount;
    }

    public int getRailRoadcount() {
        return RailRoadcount;
    }

    public void setRailRoadcount(int railRoadcount) {
        RailRoadcount = railRoadcount;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setOwnProperties(ArrayList<Integer> ownProperties) {
        OwnProperties = ownProperties;
    }

    public ArrayList<Integer> getOwnProperties() {
        return OwnProperties;
    }

    public GamePlayer(String name){
        this.jailStatus = false;
        this.turncount = 0;
        this.RailRoadcount = 0;
        this.position = 1;
        setName(name);
        setMoney((double)15000);
        ArrayList<Integer> properties = new ArrayList<>();
        setOwnProperties(properties);
    }

    
        
        
        
    
}
