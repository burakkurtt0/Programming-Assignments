public class Soudjouk extends ToppingDecorator{

    public Soudjouk(Pizza New_pizza) {
        super(New_pizza);
    }

    @Override
    public Double PizzaCost() {
        
        return super.PizzaCost() + 5.0;
    }

    @Override
    public String PizzaType() {
        
        return super.PizzaType() + "Soudjouk";
    }
    
}
