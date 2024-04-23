import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class Planner {

    public final Task[] taskArray;
    public final Integer[] compatibility;
    public final Double[] maxWeight;
    public final ArrayList<Task> planDynamic;
    public final ArrayList<Task> planGreedy;

    public Planner(Task[] taskArray) {

        // Should be instantiated with an Task array
        // All the properties of this class should be initialized here

        this.taskArray = taskArray;
        this.compatibility = new Integer[taskArray.length];
        maxWeight = new Double[taskArray.length];
        

        this.planDynamic = new ArrayList<>();
        this.planGreedy = new ArrayList<>();
    }

    /**
     * @param index of the {@link Task}
     * @return Returns the index of the last compatible {@link Task},
     * returns -1 if there are no compatible {@link Task}s.
     */
    public int binarySearch(int index) {
        
        return -1;
    }


    /**
     * {@link #compatibility} must be filled after calling this method
     */
    public void calculateCompatibility() {
        this.compatibility[0] = -1;
        for(int i = taskArray.length-1 ; i > 0 ; i--){
            Task currentT = taskArray[i];
            int comp = -1;
            for(int j = i-1;j>=0;j--){
                int result = Task.CompareTimes(Task.TimeConvert(currentT.start), Task.TimeConvert(taskArray[j].getFinishTime()));
                if(result >=0){
                    comp = j;
                    break;
                }
                
            }
            this.compatibility[i] = comp;
        }
    }








    /**
     * Uses {@link #taskArray} property
     * This function is for generating a plan using the dynamic programming approach.
     * @return Returns a list of planned tasks.
     */
    public ArrayList<Task> planDynamic() {
        calculateCompatibility();
        System.out.println("Calculating max array\n---------------------");
        calculateMaxWeight(taskArray.length-1);
        System.out.println();

        System.out.println("Calculating the dynamic solution\n--------------------------------");
        solveDynamic(taskArray.length-1);
        Collections.reverse(planDynamic);
        System.out.println("\nDynamic Schedule\n----------------");
        for(int i = 0; i < planDynamic.size() ;i++){
            System.out.println("At "+ planDynamic.get(i).start+", " + planDynamic.get(i).name+".");
        }
        return planDynamic;
    }






    /**
     * {@link #planDynamic} must be filled after calling this method
     */
    public void solveDynamic(int i) {
        
    

        if(i==-1){
            return;
        }
        System.out.println("Called solveDynamic(" + i+")");
        if( i == 0){
            planDynamic.add(taskArray[0]);
        }
        
        else{
        if(maxWeight[i-1] >= taskArray[i].getWeight() + ((compatibility[i] == -1)? 0:maxWeight[compatibility[i]])){
            solveDynamic(i-1);
        }
        else{
            planDynamic.add(taskArray[i]);
            solveDynamic(compatibility[i]);
        }
    }
    
    }
        
        
    






    /**
     * {@link #maxWeight} must be filled after calling this method
     */
    /* This function calculates maximum weights and prints out whether it has been called before or not  */
    public Double calculateMaxWeight(int i) {
        System.out.println("Called calculateMaxWeight(" + i + ")");
        if (i == -1) {
            return 0.0;
        }
        if (maxWeight[i] != null) {
            return maxWeight[i];
        }
        double includeT = taskArray[i].getWeight() + calculateMaxWeight(compatibility[i]);
        double excludeT = calculateMaxWeight(i-1);
        maxWeight[i] = Math.max(includeT, excludeT);
        return maxWeight[i];

    }









    /**
     * {@link #planGreedy} must be filled after calling this method
     * Uses {@link #taskArray} property
     *
     * @return Returns a list of scheduled assignments
     */
    /*
     * This function is for generating a plan using the greedy approach.
     * */
    public ArrayList<Task> planGreedy() {
        calculateCompatibility();
        planGreedy.add(taskArray[0]);
        int comp = 0;
        for (int i = 1 ; i < taskArray.length; i++){
                
                if(compatibility[i] == comp  || compatibility[i] > comp){
                planGreedy.add(taskArray[i]);
                comp = i;
            }

            
        }
        System.out.println("Greedy Schedule\n---------------");
        for(int i = 0; i < planGreedy.size();i++){
            System.out.println("At "+ planGreedy.get(i).start+", " + planGreedy.get(i).name+".");
        }
        return planGreedy;
    }
}
