package com.alchemymixer.main.observables;

import com.alchemymixer.math.Rational;

/**
 * The Unit enum
 * This enum holds all possible units in which amounts of a certain ingredient can be expressed in. Each enum holds a
 * Rational object, which represents its value compared to a Spoon, and also holds a boolean to check whether that Unit
 * can act as a container (such as a Barrel or Chest, unlike a Pinch or Storeroom).
 *
 * @invar A Unit's value is never null
 *
 * @author Dieter "Dimme" D.
 * @version 1.0.1
 */
public enum Unit {
    // Universal
    Spoon(1, 1, true),
    Storeroom(6300, 1, false),

    // Liquid
    Drop(1, 8, false),
    Vial(5, 1, true),
    Bottle(15, 1, true),
    Jug(105, 1, true),
    Barrel(1260, 1, true),

    // Powder
    Pinch(1, 6, false),
    Sachet(7, 1, true),
    Box(42, 1, true),
    Sack(126, 1, true),
    Chest(1260, 1, true);

    /**
     * The value of this Unit
     */
    private final Rational value;

    /**
     * Tells whether this Unit represents a container
     */
    private final boolean isContainer;

    /**
     * Constructs a new Unit with a given value compared to a Spoon, and if this new Unit can represent a container.
     * The value is split up into separate numerator and denominator values, which are used to construct a Rational
     * object.
     * @param numerator   The numerator of the Rational for the value of the new Unit
     * @param denominator The denominator of the Rational for the value of the new Unit
     * @param isContainer A boolean which represents whether the new Unit represents a container
     */
    Unit(long numerator, long denominator, boolean isContainer) {
        this.value = new Rational(numerator, denominator);
        this.isContainer = isContainer;
    }

    /**
     * The getter for the value of this Unit compared to a Spoon.
     * @return A Rational with its value equal to the value of this Unit compared to a Spoon.
     */
    public Rational getValue() {
        return value;
    }

    /**
     * The checker for whether this Unit represents a container.
     * @return a boolean, which is true if this Unit represents a container, false otherwise.
     */
    public boolean isContainer() {
        return isContainer;
    }
}
