public class Player {

    private double money;
    private String name;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void TradeMoney(Player p1 , Player p2, double money){ //p1 (First argument) always pays.

        double Tempmoney = p1.getMoney() - money;
        p1.setMoney(Tempmoney);

        double Tempmoney2 = p2.getMoney() + money;
        p2.setMoney(Tempmoney2);
        
    }
    



    
}
