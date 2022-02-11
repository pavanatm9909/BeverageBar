package edu.banditutorials.beveragebar;

/**
 * Custom class for regular hot coffee.
 * 
 * @author Ajay Bandi
 */
public class Coffee extends AbstractBeverage{

    public Coffee(String name) {
        super(name);
    }

    @Override
    public String getDescription() {
        return "a delicious cup of coffee, served hot.";
    }
    
}
