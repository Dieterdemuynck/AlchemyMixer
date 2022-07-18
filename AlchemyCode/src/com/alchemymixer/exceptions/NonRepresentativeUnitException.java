package com.alchemymixer.exceptions;

import com.alchemymixer.main.observables.State;
import com.alchemymixer.main.observables.Unit;

/**
 * Exception class which is thrown when an attempt was made to create an AlchemicalIngredient with a Quantity with a
 * Unit that is not representative for the AlchemicalIngredient's given State.
 * @author Dieter "Dimme" D.
 * @version 1.0.1
 */
public class NonRepresentativeUnitException extends RuntimeException {

    /**
     * The state in which the ingredient is or would be in
     */
    private final State state;

    /**
     * The unit which is not representative for the state
     */
    private final Unit unit;

    /**
     * Constructs a new NonRepresentativeUnitException with given state and non-representative unit.
     * @param state The State the ingredient is or would be in
     * @param unit  The Unit that is not representative for the state
     */
    public NonRepresentativeUnitException(State state, Unit unit) {
        this.state = state;
        this.unit = unit;
    }

    /**
     * The getter for the state in this exception.
     * @return The state which clashes with the given unit
     */
    public State getState() {
        return state;
    }

    /**
     * The getter for the unit in this exception.
     * @return The Unit which is unrepresentative for the given state
     */
    public Unit getUnit() {
        return unit;
    }
}
