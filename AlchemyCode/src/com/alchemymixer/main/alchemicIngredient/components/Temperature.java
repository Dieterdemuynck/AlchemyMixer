package com.alchemymixer.main.alchemicIngredient.components;

/**
 * The Temperature class
 * Objects of this class represent temperatures of ingredients, devices, etc. Temperatures are represented using a
 * hotness value and a coldness value, where at least one of these values equals zero. Temperatures are immutables, and
 * heating and cooling will return a new Temperature object.
 *
 * @invar The hotness never exceeds the set MAX_VALUE
 * @invar The coldness never exceeds the set MAX_VALUE
 * @invar Either coldness or hotness, or both, must equal zero.
 *
 * @author Dieter "Dimme" D.
 * @version 1.0
 */
public class Temperature {

    /**
     * The long representation of this Temperature
     */
    private final long temperatureValue;

    /**
     * The maximum value for the hotness and coldness
     */
    public final long MAX_VALUE = 10000;

    /**
     * Constructs a new Temperature object with given hotness and coldness.
     * If neither value is 0, it is assumed that one value represents a certain amount of cooling/heating represented in
     * a lazy way. Thus, the values are updated to represent a valid temperature.
     * If one of the temperature values (perhaps after adjustment) exceeds MAX_VALUE, that value is set to MAX_VALUE.
     *
     * @param coldness The value of coldness of an ingredient
     * @param hotness  The value of hotness of an ingredient
     */
    public Temperature(long coldness, long hotness) {
        // If the values are invalid, we claim one value heats or cools the other. In reality, this just makes the code
        // prettier and easier to write.
        this(hotness - coldness);
    }

    /**
     * Constructs a new Temperature object using the given temperature value. Caps the value off such that it remains
     * between -MAX_VALUE and MAX_VALUE.
     * @param temperatureValue The value to which this temperature is set
     */
    private Temperature(long temperatureValue) {
        if (temperatureValue > MAX_VALUE) {
            this.temperatureValue = MAX_VALUE;
        } else
            this.temperatureValue = Math.max(temperatureValue, -MAX_VALUE);
    }

    /**
     * Returns a temperature array which has the coldness and hotness of this temperature in that order.
     * @return a long array with the coldness and hotness in that order
     */
    public long[] getTemperature() {
        return new long[]{getColdness(), getHotness()};
    }

    /**
     * Returns the hotness of this temperature.
     * @return the hotness value of this temperature
     */
    public long getHotness() {
        return Math.max(0, this.temperatureValue);
    }

    /**
     * Returns the coldness of this temperature;
     * @return the coldness value of this temperature
     */
    public long getColdness() {
        return -Math.min(0, this.temperatureValue);
    }

    /**
     * Returns a new Temperature object which is hotter than this Temperature object by the given value, or is equal to
     * [0, MAX_VALUE] if the resulting value exceeds the MAX_VALUE. The given value must be positive. If the value is
     * negative, the value will be updated to zero and, essentially, a copy of this Temperature object will be given.
     *
     * @param  heatValue The value by which we want to heat up this temperature
     * @return a Temperature object which is hotter than this object by the given amount
     */
    public Temperature heat(long heatValue) {
        heatValue = Math.max(0, heatValue);
        return new Temperature(this.temperatureValue + heatValue);
    }

    /**
     * Returns a new Temperature object which is cooler than this Temperature object by the given value, or is equal to
     * [MAX_VALUE, 0] if the resulting value exceeds the MAX_VALUE. The given value must be positive. If the value is
     * negative, the value will be updated to zero and, essentially, a copy of this Temperature object will be given.
     *
     * @param  coolValue The value by which we want to cool down this temperature
     * @return a Temperature object which is cooler than this object by the given amount
     */
    public Temperature cool(long coolValue) {
        return new Temperature(this.temperatureValue - coolValue);
    }
}
