package com.alchemymixer.math;

/**
 * The Rational Number class
 * A Rational represents a rational number using two longs, which stand for numerator and denominator. The denominator
 * cannot be zero.
 * Based on the Rational class found at https://liveexample.pearsoncmg.com/html/Rational.html?
 *
 * @invar The denominator does not equal zero
 *
 * @author Dieter "Dimme" D.
 * @version 1.0
 */
public class Rational extends Number implements Comparable<Rational> {

    /**
     * The numerator of the Rational
     */
    private final long numerator;

    /**
     * The denominator of the Rational
     */
    private final long denominator;

    /**
     * Constructs a new Rational with given numerator and denominator, and simplifies.
     * @param numerator   The numerator of the Rational
     * @param denominator The denominator of the Rational
     */
    public Rational(long numerator, long denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("denominator of a rational may not be zero");
        }
        long gcd = gcd(numerator, denominator);
        this.numerator = Long.signum(denominator) * numerator / gcd;
        this.denominator = Math.abs(denominator) / gcd;
    }

    /**
     * Calculates the greatest common divisor of the two given values.
     *
     * @param a The first value
     * @param b The second value
     * @return The greatest common divisor of the given values
     */
    private static long gcd(long a, long b) {
        a = Math.abs(a);
        b = Math.abs(b);

        // Algorithm for GCD found at https://en.wikipedia.org/wiki/Euclidean_algorithm#Implementations
        long t;
        while (b != 0) {
            t = b;
            b = a % b;
            a = t;
        }

        return a;
    }

    /**
     * Returns the numerator of this Rational.
     * @return the numerator of this Rational
     */
    public long getNumerator() {
        return numerator;
    }

    /**
     * Returns the denominator of this Rational.
     * @return the denominator of this Rational
     */
    public long getDenominator() {
        return denominator;
    }

    /**
     * Returns a new Rational object which equals this Rational plus the given Rational.
     * @param other The Rational to add to this Rational
     * @return The addition of the two Rationals
     */
    public Rational add(Rational other) {
        long numerator = this.numerator * other.getDenominator()
                       + this.denominator * other.getNumerator();
        long denominator = this.denominator * other.getDenominator();
        return new Rational(numerator, denominator);
    }

    /**
     * Returns a new Rational object which equals this Rational minus the given Rational.
     * @param other The Rational to subtract from this Rational
     * @return The subtraction of the two Rationals
     */
    public Rational subtract(Rational other) {
        long numerator = this.numerator * other.getDenominator()
                       - this.denominator * other.getNumerator();
        long denominator = this.denominator * other.getDenominator();
        return new Rational(numerator, denominator);
    }

    /**
     * Returns a new Rational object which equals this Rational multiplied by the given Rational.
     * @param other The Rational to multiply with this Rational
     * @return The multiplication of the two Rationals
     */
    public Rational multiply(Rational other) {
        long numerator = this.numerator * other.getNumerator();
        long denominator = this.denominator * other.getDenominator();
        return new Rational(numerator, denominator);
    }

    /**
     * Returns a new Rational object which equals this Rational divided by the given Rational.
     * @param other The Rational to divide this Rational by
     * @return The division of the two Rationals
     */
    public Rational divide(Rational other) {
        long numerator = this.numerator * other.getDenominator();
        long denominator = this.denominator * other.getNumerator();
        return new Rational(numerator, denominator);
    }

    @Override
    public int compareTo(Rational other) {
        return Long.signum(this.subtract(other).getNumerator());
    }

    @Override
    public int intValue() {
        return (int) (getNumerator() / getDenominator());
    }

    @Override
    public long longValue() {
        return getNumerator() / getDenominator();
    }

    @Override
    public float floatValue() {
        return (float) doubleValue();
    }

    @Override
    public double doubleValue() {
        return (double) getNumerator() / getDenominator();
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}
