package edu.banditutorials.beveragebar;

/**
 * Custom class for regular hot tea.
 * 
 * @author Ajay Bandi
 */
public class Tea extends AbstractBeverage{

    public Tea(String name) {
        super(name);
    }

    @Override
    public String getDescription() {
        return "a delicious cup of tea, served hot.";
    }
    
}
