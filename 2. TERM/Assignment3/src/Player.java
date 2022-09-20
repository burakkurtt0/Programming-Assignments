

public class Player implements Comparable<Player>{
    private int temp_score;
    private int score;
    private String name;
    Player(){
        this.score = 0;
        this.temp_score = 0;
    }



    public int getTemp_score() {
        return temp_score;
    }



    public void setTemp_score(int temp_score) {
        this.temp_score = temp_score;
    }



    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }



    @Override
    public int compareTo(Player o) {
        int cmp = this.getScore() > o.getScore() ? +1 : this.getScore() < o.getScore() ? -1 : 0;
        return  cmp;
       
    }

    

}


    

