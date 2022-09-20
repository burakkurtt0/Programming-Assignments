public class PizzaStore {
    public static void main(String[] args) {
        

        Pizza NewPizza = new Salami(new Onion(new Pepper(new American_Pan_p())));

        System.out.println(NewPizza.PizzaCost());
        System.out.println(NewPizza.PizzaType());

    }
    
}
