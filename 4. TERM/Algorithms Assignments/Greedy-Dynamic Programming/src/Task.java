import java.time.LocalTime;

public class Task implements Comparable {
    public String name;
    public String start;
    public int duration;
    public int importance;
    public boolean urgent;
    

    /*
        Getter methods
     */
    public String getName() {
        return this.name;
    }

    public String getStartTime() {
        return this.start;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getImportance() {
        return this.importance;
    }

    public boolean isUrgent() {
        return this.urgent;
    }


    /**
     * Finish time should be calculated here
     *
     * @return calculated finish time as String
     */
    public String getFinishTime() {
        int[] time =  TimeConvert(this.start);
        int finHour = (time[0] + this.duration) % 24 ;
        
        String finishTime = "";
        if(finHour < 10){
            finishTime+= "0";
        }
        finishTime+= finHour +":";

        if(time[1] <10 ){
            finishTime+="0";
        }
        finishTime += time[1];
        
        return finishTime;
    }


    /**
     * Weight calculation should be performed here
     *
     * @return calculated weight
     */
    public double getWeight() {
        return (double) (importance * (urgent ? 2000:1) / duration);
    }




    /**
     * This method is needed to use {@link java.util.Arrays#sort(Object[])} ()}, which sorts the given array easily
     *
     * @param o Object to compare to
     * @return If self > object, return > 0 (e.g. 1)
     * If self == object, return 0
     * If self < object, return < 0 (e.g. -1)
     */
    @Override
    public int compareTo(Object o) {
        Task taskO = (Task) o;
        int[] selfFinish = TimeConvert(this.getFinishTime());
        int[] taskOfinish = TimeConvert(taskO.getFinishTime());

        return CompareTimes(selfFinish, taskOfinish);
    }

    public static int CompareTimes(int[] selfFinish , int[] taskOfinish){
        if(selfFinish[0] > taskOfinish[0]){
            return 1;
        }
        else if (selfFinish[0] < taskOfinish[0]){
            return -1;
        }
        else{
            if(selfFinish[1] > taskOfinish[1]){
                return 1;
            }
            else if (selfFinish[1] < taskOfinish[1]){
                return -1;
            }
            else{
                return 0;
            }
        }
    }


    public static int[] TimeConvert(String time){
        String[] times = time.split(":");
        int[] timesInt = new int[2];
        timesInt[0] = Integer.parseInt(times[0]);
        timesInt[1] = Integer.parseInt(times[1]);
        
        return timesInt;
    }
}
