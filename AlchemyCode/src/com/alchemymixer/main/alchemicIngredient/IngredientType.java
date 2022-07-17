package com.alchemymixer.main.alchemicIngredient;

import com.alchemymixer.exceptions.IllegalSpecialNameAssignmentException;
import com.alchemymixer.main.observables.State;
import com.alchemymixer.main.observables.Temperature;

/**
 * The IngredientType class
 * Describes the type of ingredient that an AlchemicalIngredient is. This type holds all the information about the
 * standard state that a certain ingredient exists in, as well as its name(s), which includes:
 *  * standard state
 *  * standard temperature
 *  * name or names of its components in case of a mixture
 *  * special name in case of a mixture
 *
 * @invar There is at least one component, and thus at least one name in its name components.
 * @invar state is not null
 * @invar temperature is not null
 * @invar a non-mixed ingredient type (exactly one name component) has no special name (special name is null).
 *
 * @author Dieter "Dimme" D.
 * @version 1.0
 */
public class IngredientType {

    /**
     * Holds the names of each ingredient component that was mixed into this type.
     * If this is a pure ingredient, holds only one name.
     */
    private final Name[] nameComponents;

    /**
     * The standard state this ingredient type exists in
     */
    private final State state;

    /**
     * The standard temperature this ingredient type exists in
     */
    private final Temperature temperature;

    /**
     * The special name of this ingredient, if it is mixed and was given one
     */
    private Name specialName = null;

    /**
     * Constructs a new ingredient type with given names of the components, standard state and standard temperature. If
     * any of the given arguments are null, or the name components are empty, an IllegalArgumentException is thrown.
     *
     * @param nameComponents The names of the component ingredients
     * @param state          The standard state of this ingredient
     * @param temperature    The standard temperature of this ingredient
     */
    public IngredientType(Name[] nameComponents, State state, Temperature temperature) {
        if (nameComponents == null) {
            throw new IllegalArgumentException("name components cannot be null");
        } if (nameComponents.length == 0) {
            throw new IllegalArgumentException("name components must have at least one name");
        } if (state == null) {
            throw new IllegalArgumentException("standard state cannot be null");
        } if (temperature == null) {
            throw new IllegalArgumentException("standard temperature cannot be null");
        }
        this.nameComponents = nameComponents;
        this.state = state;
        this.temperature = temperature;
    }

    /**
     * Constructs a new pure ingredient type, with a given name as a String, standard state and standard temperature.
     *
     * @param name        The String of the name of the new pure ingredient type
     * @param state       The standard state of the new pure ingredient type
     * @param temperature The standard temperature of the new pure ingredient type
     */
    public IngredientType(String name, State state, Temperature temperature) {
        this.nameComponents = new Name[]{new Name(name)};
        this.state = state;
        this.temperature = temperature;
    }

    /**
     * The getter for the name components of this IngredientType.
     * @return The name components of this IngredientType
     */
    public Name[] getNameComponents() {
        return nameComponents;
    }

    /**
     * The getter for the standard state of this IngredientType.
     * @return The standard state of this IngredientType
     */
    public State getState() {
        return state;
    }

    /**
     * The getter for the standard temperature of this IngredientType.
     * @return The standard temperature of this IngredientType
     */
    public Temperature getTemperature() {
        return temperature;
    }

    /**
     * The getter for the special name of this IngredientType.
     * @return The special name of this IngredientType
     */
    public Name getSpecialName() {
        return specialName;
    }

    /**
     * Sets the special name of this IngredientType, if it is a mixed type.
     * @param specialName The Name to which this object's specialName is set
     */
    public void setSpecialName(Name specialName) {
        if (isMixture())
            this.specialName = specialName;
        else
            throw new IllegalSpecialNameAssignmentException(this);
    }

    /**
     * Returns the simple name of this IngredientType. If this is a mixed ingredient type, the name components are
     * sorted, and the lexicographically sorted first name will be followed by " mixed with ", after which each next
     * name will be written, separated by commas ", ".
     *
     * @return The String of the simple name of this ingredient type
     */
    public String getSimpleName() {
        StringBuilder result = new StringBuilder(getNameComponents()[0].getName());
        result.append(" mixed with ");

        for (int i = 1; i < getNameComponents().length; i++) {
            result.append(getNameComponents()[i].getName());
            result.append(", ");
        }

        return result.toString();
    }

    /**
     * Checker for whether this object represents a mixed ingredient type.
     * @return True if this object represents a mixed ingredient type
     */
    public boolean isMixture() {
        return getNameComponents().length > 1;
    }

    /**
     * Checker for whether this object has a special name set.
     * @return True if this object has a special name set
     */
    public boolean hasSpecialName() {
        return getSpecialName() != null;
    }
}
