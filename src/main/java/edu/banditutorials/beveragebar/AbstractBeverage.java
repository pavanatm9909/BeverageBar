package edu.banditutorials.beveragebar;

/**
 * Super class for traits shared by all child classes. Abstract - don't make a
 * general beverage - only specific types.
 *
 * @author Ajay Bandi
 */
public abstract class AbstractBeverage {

    /**
     * All have a name. Class variable name is not private, but
     * protected - only subclasses can use it.
     */
    protected String name = "no name";
    


    /**
     * Constructor
     *
     * @param name - the name of this instance 
     */
    public AbstractBeverage(String name) {
        this.name = name;
    }

    /**
     * abstract getDescription - has no implementation
     */
    public abstract String getDescription();


    /**
     * Get name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }



}
