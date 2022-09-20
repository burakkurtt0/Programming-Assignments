public abstract class ToppingDecorator implements Pizza{

    Pizza tempPizza;

    public ToppingDecorator(Pizza New_pizza) {
        this.tempPizza = New_pizza;
    }

    @Override
    public Double PizzaCost() {
       
        return tempPizza.PizzaCost();
    }

    @Override
    public String PizzaType() {
        
        return tempPizza.PizzaType();
    }

    

    
}
