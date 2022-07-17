package com.alchemymixer.main.observables;

import com.alchemymixer.math.Rational;

import java.util.Objects;

/**
 * The Quantity class
 * A record to store the amount of an AlchemicalIngredient in a certain Unit.
 *
 * @invar unit is never null
 * @invar amount is greater than or equal to zero
 *
 * @author Dieter "Dimme" D.
 * @version 1.1
 */
public record Quantity(long amount, Unit unit) implements Comparable<Quantity> {

    /**
     * Constructs a new Quantity record where the amount must be greater than or equal to zero, and the unit may not be
     * null.
     */
    public Quantity {
        if (amount < 0) {
            throw new IllegalArgumentException("amount must be greater than or equal to zero");
        } else if (unit == null) {
            throw new IllegalArgumentException("unit may not be null");
        }
    }

    /**
     * The getter for the Spoon-value of this Quantity, expressed in a Rational.
     * @return the Rational representing the Spoon-value of this Quantity
     */
    public Rational getSpoonValue() {
        return new Rational(this.amount(), 1).multiply(this.unit().getValue());
    }

    /**
     * The getter for the Storeroom-value of this Quantity, expressed in a Rational.
     * @return the Rational representing the Storeroom-value of this Quantity
     */
    public Rational getStoreroomValue() {
        return getSpoonValue().divide(Unit.Storeroom.getValue());
    }

    /**
     * Compares this Quantity to other based on their value in Spoons.
     * Returns -1 if this.getSpoonValue() < other.getSpoonValue(),
     * 0 if this.getSpoonValue() == other.getSpoonValue(),
     * and 1 if this.getSpoonValue() > other.getSpoonValue().
     *
     * @param other The Quantity to compare to this Quantity
     * @return -1 if this.getSpoonValue() < other.getSpoonValue(),
     *          0 if this.getSpoonValue() == other.getSpoonValue(),
     *          1 if this.getSpoonValue() > other.getSpoonValue()
     */
    @Override
    public int compareTo(Quantity other) {
        return getSpoonValue().compareTo(other.getSpoonValue());
    }

    /**
     * Checker for whether two Quantities represent the same amount (in Spoons).
     *
     * @param other The Quantity that is compared to this Quantity
     * @return True if and only if this and other represent the same value (in Spoons)
     */
    public boolean representsSameAs(Quantity other) {
        return this.compareTo(other) == 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Quantity) obj;
        return this.amount == that.amount &&
                Objects.equals(this.unit, that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, unit);
    }
}
