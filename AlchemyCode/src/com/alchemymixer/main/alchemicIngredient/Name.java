package com.alchemymixer.main.alchemicIngredient;

import com.alchemymixer.exceptions.IllegalNameComponentException;

import java.util.Objects;

/**
 * The Name class
 * This class holds a String which represents a valid simple name for a non-mixed ingredient. If an invalid name is
 * passed, this will throw an IllegalNameComponentException.
 * A valid simple non-mixed name exists out of either one word which starts with a capital letter and has at least three
 * characters (lowercase letters a-z or an apostrophe '), or multiple words, each starting with a capital letter and
 * each has at least two characters.
 *
 * @invar name is a valid simple non-mixed name.
 *
 * @author Dieter "Dimme" D.
 * @version 1.0
 */
public class Name implements Comparable<Name> {

    /**
     * The name stored in this Name object
     */
    private final String name;

    /**
     * Constructs a new Name object using the given name if it is valid. Throws an IllegalNameComponentException
     * otherwise.
     * A valid simple non-mixed name exists out of either one word which starts with a capital letter and has at least three
     * characters (lowercase letters a-z or an apostrophe '), or multiple words, each starting with a capital letter and
     * each has at least two characters.
     * @param name The string which is the name we want to store in this Name object
     * @throws IllegalNameComponentException name is null or not a valid name.
     */
    public Name(String name) throws IllegalNameComponentException{
        if (name == null || !name.matches("([A-Z][a-z']+( [A-Z][a-z']+)+|[A-Z][a-z']{2,})") ) {
            throw new IllegalNameComponentException(name);
        } else {
            this.name = name;
        }
    }

    /**
     * Constructs a new Name object which contains the name "Water" for the default Water ingredient.
     */
    public Name() {
        this.name = "Water";
    }

    /**
     * Returns the name stored in this Name object.
     * @return the name stored in this Name object
     */
    public String getName() {
        return this.name;
    }

    /**
     * Compares two Name objects by comparing their saved Strings.
     * @param o The other Name object which is compared to this Name object
     * @return  this.getName().compareTo(o.getName())
     */
    @Override
    public int compareTo(Name o) {
        return getName().compareTo(o.getName());
    }

    /**
     * Returns whether this Name object has the same saved name String as the given other Object.
     * @param o The other Name object which is compared to this Name object
     * @return True if the given object is also a Name object and the stored name Strings are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return getName().equals(name1.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
