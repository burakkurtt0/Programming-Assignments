import java.util.*;
public class City {
    public int city_num;
    public ArrayList<Integer> roadTo;
    public ArrayList<Integer> dfsNeighbours;
    public ArrayList<Integer> connected;
   


    public City(int num){
        city_num = num;
        roadTo = new ArrayList<>();
        dfsNeighbours = new ArrayList<>();
        connected = new ArrayList<>();
        
    }

    public void addRoad(int city_num){
        this.roadTo.add(city_num);
    }

    public void fill_DFS(ArrayList<City> cities){
        
        
        Stack<City> dfsStack = new Stack<City>();
        dfsStack.push(this);
        

        while(!dfsStack.empty()){
            City t = dfsStack.pop();
            for(int i = 0 ; i < t.roadTo.size(); i++){
                if((!dfsNeighbours.contains(t.roadTo.get(i)) && (t.roadTo.get(i) != this.city_num) )){
                    dfsStack.push(cities.get(t.roadTo.get(i)));
                    dfsNeighbours.add(t.roadTo.get(i));
                }
            }

        }

    }


    public void addConnected(City connectedCity){
        
        if(connectedCity.connected.isEmpty()){
            if(!this.connected.contains(connectedCity.city_num)){
                this.connected.add(connectedCity.city_num);
            }
            
            return;
        }
            
        if(connectedCity.connected.get(0) == -1){   //!!!!!!!!
            return;
        }

        for(int i = 0 ; i<connectedCity.connected.size();i++){
            if(!this.connected.contains(connectedCity.connected.get(i)) && connectedCity.connected.get(i) != this.city_num){
                this.connected.add(connectedCity.connected.get(i));
            }
        }
        
    }

    public void deleteConnected(){
        if(this.connected.isEmpty()){
            this.connected.add(-1);
            return;
        }

        this.connected.set(0, -1);
    }
}
