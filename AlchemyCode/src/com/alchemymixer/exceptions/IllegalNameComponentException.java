package com.alchemymixer.exceptions;

/**
 * This exception is thrown when an invalid String was passed as an argument in an attempt to create a new Name object.
 *
 * @author Dieter "Dimme" D.
 * @version 1.0.1
 */
public class IllegalNameComponentException extends RuntimeException {

    /**
     * The invalid name that was passed to create a Name object
     */
    private final String name;

    /**
     * Constructs a new IllegalNameComponentException with the given, invalid string to create a Name object.
     * @param name The invalid string that was passed to create a new Name object
     */
    public IllegalNameComponentException(String name) {
        this.name = name;
    }

    /**
     * Returns the invalid name.
     * @return the invalid name
     */
    public String getName() {
        return name;
    }
}
