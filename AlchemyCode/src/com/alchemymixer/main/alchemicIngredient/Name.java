package com.alchemymixer.main.alchemicIngredient;

import com.alchemymixer.exceptions.IllegalNameComponentException;

import java.util.Arrays;
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
 * @version 1.2
 */
public class Name implements Comparable<Name> {

    /**
     * The name stored in this Name object
     */
    private final String name;

    /**
     * The array of disallowed words that a name cannot contain
     */
    public static final String[] disallowedWords = new String[]{
            "Heated", "Cooled"
    };

    /**
     * Constructs a new Name object using the given name if it is valid. Throws an IllegalNameComponentException
     * otherwise.
     * A valid simple non-mixed name exists out of either one word which starts with a capital letter and has at least three
     * characters (lowercase letters a-z or an apostrophe '), or multiple words, each starting with a capital letter and
     * each has at least two characters.
     * @param name The string which is the name we want to store in this Name object
     * @throws IllegalNameComponentException name is null or not a valid name.
     *                                       | !isValidName(name)
     */
    public Name(String name) throws IllegalNameComponentException{
        if (!isValidName(name)) {
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
     * The getter for the name stored in this Name object.
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

    /**
     * Generates the hash code for this object.
     * @return the hash code for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    /**
     * Checks a suggested name for its validity as a simple, non-mixed ingredient name.
     * @param name The suggested name to be checked for validity
     * @return True if name is not null and has one word with at least 3 characters from A_Z, a-z or ' and starts with a
     *         capital letter, or name has multiple words each with at least 2 characters and also starts with a capital
     *         letter, where words are split on spaces. None of the words may be equal to at least one of the disallowed
     *         words.
     */
    public static boolean isValidName(String name) {
        if (name == null) return false;

        String[] words = name.split(" ");

        String regex;
        if (words.length > 1) {
            // regex for multiple words
            regex = "[A-Z][a-z']+";
        } else {
            // regex for only one word
            regex = "[A-Z][a-z']{2,}";
        }

        for (String nameComponent: words) {
            if (Arrays.asList(disallowedWords).contains(nameComponent) || !nameComponent.matches(regex)) {
                return false;
            }
        }
        return true;
    }
}
