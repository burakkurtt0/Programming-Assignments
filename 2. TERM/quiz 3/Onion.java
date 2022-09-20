public class Onion extends ToppingDecorator{

    public Onion(Pizza New_pizza) {
        super(New_pizza);
    }

    @Override
    public Double PizzaCost() {
        
        return super.PizzaCost() + 3.0;
    }

    @Override
    public String PizzaType() {
        
        return super.PizzaType() +" Onion";
    }

    
}
