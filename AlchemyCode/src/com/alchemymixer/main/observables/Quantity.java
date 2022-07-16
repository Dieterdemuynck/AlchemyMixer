package com.alchemymixer.main.observables;

import com.alchemymixer.math.Rational;

/**
 * The Quantity class
 * A record to save the amount of an AlchemicalIngredient in a certain Unit.
 */
public record Quantity(long amount, Unit unit) implements Comparable<Quantity> {

    /**
     * Returns the Spoon-value of this Quantity, expressed in a Rational.
     * @return the Spoon-value of this Quantity
     */
    public Rational getSpoonValue() {
        return new Rational(this.amount(), 1).multiply(this.unit().getValue());
    }

    /**
     * Returns the Storeroom-value of this Quantity, expressed in a Rational.
     * @return the Storeroom-value of this Quantity
     */
    public Rational getStoreroomValue() {
        return getSpoonValue().divide(Unit.Storeroom.getValue());
    }

    @Override
    public int compareTo(Quantity other) {
        return getSpoonValue().compareTo(other.getSpoonValue());
    }
}
