package edu.banditutorials.beveragebar;

/**
 * Custom class for regular cold milk.
 * 
 * @author Ajay Bandi
 */
public class Milk extends AbstractBeverage{

    public Milk(String name) {
        super(name);
    }

    @Override
    public String getDescription() {
        return "a delicious glass of milk, served cold.";
    }
    
}
