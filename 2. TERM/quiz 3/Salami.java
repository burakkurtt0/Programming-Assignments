public class Salami extends ToppingDecorator{

    public Salami(Pizza New_pizza) {
        super(New_pizza);
    }

    @Override
    public Double PizzaCost() {
        
        return super.PizzaCost()+ 10.0;
    }

    @Override
    public String PizzaType() {
        
        return super.PizzaType() + " Salami";
    }

    

}