package com.alchemymixer.main.alchemicIngredient;

import com.alchemymixer.exceptions.NonRepresentativeUnitException;
import com.alchemymixer.main.observables.Quantity;
import com.alchemymixer.main.observables.State;
import com.alchemymixer.main.observables.Temperature;

import java.util.Arrays;

/**
 * The AlchemicalIngredient class
 * This class represents a physical alchemical ingredient, such as Water or Beer, but also Rat's Tail, Dragon's Eye, Red
 * Mushroom Gas... An AlchemicalIngredient (from now on referred to as "ingredient") has an IngredientType which holds
 * all the information of this ingredient in its most basic form, has a (current) State, a (current) Temperature and
 * holds a Quantity.
 *
 * @invar type is not null
 * @invar state is not null
 * @invar quantity is not null
 * @invar temperature is not null
 * @invar quantity.unit() is contained in state.getRepresentativeUnits()
 *
 * @author Dieter "Dimme" D.
 * @version 1.0
 */
public class AlchemicalIngredient {

    /* ********************************************************************************\
     * Fields
    \* ********************************************************************************/

    /**
     * The type of ingredient that this ingredient is
     * Immutable
     */
    private final IngredientType type;

    /**
     * The current state of this ingredient
     * Immutable
     */
    private final State state;

    /**
     * The quantity of ingredient this object represents
     * Immutable
     */
    private final Quantity quantity;

    /**
     * The current temperature of this ingredient
     */
    private Temperature temperature;


    /* ********************************************************************************\
     * Constructors
    \* ********************************************************************************/

    /**
     * Constructs a new AlchemicalIngredient with given ingredient type, current state, quantity and current
     * temperature. None of the arguments may be null, and the quantity's unit must be representative of the current
     * state of this ingredient.
     * @param type        The IngredientType of this ingredient
     * @param state       The current State this ingredient is in
     * @param quantity    The Quantity of ingredient
     * @param temperature The current Temperature this ingredient has
     */
    public AlchemicalIngredient(IngredientType type, State state, Quantity quantity, Temperature temperature) {
        // Check for null pointers:
        if (type == null) {
            throw new IllegalArgumentException("type may not be null");
        } if (state == null) {
            throw new IllegalArgumentException("state may not be null");
        } if (quantity == null) {
            throw new IllegalArgumentException("quantity may not be null");
        } if (temperature == null) {
            throw new IllegalArgumentException("temperature may not be null");
        }

        // Check if state and Quantity are properly representative for each other:
        if (!Arrays.asList(state.getRepresentativeUnits()).contains(quantity.unit())) {
            throw new NonRepresentativeUnitException(state, quantity.unit());
        }

        // We gucci, let's set 'em values right:
        this.type = type;
        this.state = state;
        this.quantity = quantity;
        this.temperature = temperature;
    }

    /**
     * Constructs a new AlchemicalIngredient with a given type and quantity. The current state and current temperature
     * will be set to the type's standard state and temperature.
     * @param type     The IngredientType of this ingredient
     * @param quantity The Quantity of ingredient
     */
    public AlchemicalIngredient(IngredientType type, Quantity quantity) {
        this(type, type.getState(), quantity, type.getTemperature());
    }

    /**
     * Returns the default ingredient type, which is Water with standard temperature and state equal to [0,20] and
     * Liquid respectively.
     * @return The default ingredient type Water, with standard temperature equal to [0,20] and standard state Liquid
     */
    private static IngredientType getDefaultType() {
        return new IngredientType("Water", State.Liquid, new Temperature(0, 20));
    }

    /**
     * Constructs a new AlchemicalIngredient using the default ingredient type: Water. Water has a standard state of
     * Liquid and a standard temperature of [0,20]. This constructor will create Water, with temperature and state equal
     * to the standard temperature and state.
     * @param quantity The Quantity of Water that is created
     */
    public AlchemicalIngredient(Quantity quantity) {
        this(getDefaultType(), quantity);
    }

    /* ********************************************************************************\
     * Getters 1:
     * Direct getters
    \* ********************************************************************************/

    /**
     * The getter for the type of this ingredient.
     * @return The type of this ingredient
     */
    public IngredientType getType() {
        return type;
    }

    /**
     * the getter for the state this ingredient is in.
     * @return The state that this ingredient is in
     */
    public State getState() {
        return state;
    }

    /**
     * The getter for the quantity of ingredient that this ingredient object represents.
     * @return The quantity of ingredient
     */
    public Quantity getQuantity() {
        return quantity;
    }

    /**
     * The getter for the current temperature, as a temperature object.
     * @return The current Temperature (as a Temperature object)
     */
    private Temperature getRawTemperature() {
        return temperature;
    }

    /**
     * Returns the current temperature as an array with two values: the coldness and hotness, in that order.
     * @return The current temperature as an array with two values: the coldness and hotness, in that order
     */
    public long[] getTemperature() {
        return getRawTemperature().getTemperature();
    }

    /* ********************************************************************************\
     * Setters:
    \* ********************************************************************************/

    /**
     * Sets the temperature to the given temperature object.
     * @param temperature The Temperature to set this ingredient to
     */
    private void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    /* ********************************************************************************\
     * Getters 2:
     * Indirect getters
    \* ********************************************************************************/

    /**
     * Generates the simple name of this ingredient, which is exactly equal to its type's simple name. The simple name
     * is the name of the lexicographically sorted first component, followed by " mixed with ", followed by all other
     * component names sorted lexicographically, seperated by the string ", ".
     * @return the simple name of this ingredient, equal to this.getType().getSimpleName()
     */
    public String getSimpleName() {
        return getType().getSimpleName();
    }

    /**
     * Returns the String representation of the special name of the ingredient's type, or null if none was given.
     * @return the String representation of the special name of the ingredient's type, or null if none was given
     */
    public String getSpecialName() {
        return getType().getSpecialName();
    }

    /**
     * Generates the full name of this ingredient, which is its type's full name accompanied by possible pre- and/or
     * postfixes, whose addition depend on the current state of the ingredient.
     * Current prefixes:
     *  * Heated: the current temperature is hotter than the standard temperature
     *  * Cooled: the current temperature is cooler than the standard temperature
     * Current postfixes:
     *  [None]
     *
     * Note: If the existence of certain pre- or postfixes should have an effect on what should be allowed as a
     * component name, the array of disallowedWords in Name should be updated.
     *
     * @return The full name of this ingredient, which is its type's full name accompanied by possible pre- and/or
     *         postfixes.
     */
    public String getFullName() {
        StringBuilder prefix = new StringBuilder();
        StringBuilder postfix = new StringBuilder();

        if (getRawTemperature().compareTo(getType().getTemperature()) < 0) {
            prefix.append("Cooled");
        } else if (getRawTemperature().compareTo(getType().getTemperature()) > 0) {
            prefix.append("Heated");
        }

        return prefix + getType().getFullName() + postfix;
    }

    /* ********************************************************************************\
     * Heating & Cooling
    \* ********************************************************************************/

    /**
     * Heats this ingredient up by the given amount, resulting in a temperature equal to what would be returned by the
     * Temperature.heat(long) method.
     * @param heatValue The value by which this ingredient heats up
     */
    public void heat(long heatValue) {
        setTemperature(getRawTemperature().heat(heatValue));
    }

    /**
     * Cools this ingredient down by the given amount, resulting in a temperature equal to what would be returned by the
     * Temperature.cool(long) method.
     * @param coolValue The value by which this ingredient cools down
     */
    public void cool(long coolValue) {
        setTemperature(getRawTemperature().cool(coolValue));
    }
}
