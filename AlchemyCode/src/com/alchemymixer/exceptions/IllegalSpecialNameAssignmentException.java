package com.alchemymixer.exceptions;

import com.alchemymixer.main.alchemicIngredient.IngredientType;

/**
 * Exception class which is thrown when an attempt was made to assign a special name to a non-mixed ingredient.
 *
 * @author Dieter "Dimme" D.
 * @version 1.0.1
 */
public class IllegalSpecialNameAssignmentException extends RuntimeException {

    /**
     * The IngredientType of a non-mixed AlchemicalIngredient to which a special name was assigned
     */
    private final IngredientType ingredientType;

    /**
     * Constructs a new IllegalSpecialNameAssignmentException with ingredientType set to the given IngredientType.
     * @param ingredientType The IngredientType of the non-mixed AlchemicalIngredient to which a special name was
     *                       assigned
     */
    public IllegalSpecialNameAssignmentException(IngredientType ingredientType) {
        this.ingredientType = ingredientType;
    }

    /**
     * The getter for the IngredientType to which a special name was assigned.
     * @return the IngredientType to which a special name was assigned
     */
    public IngredientType getIngredientType() {
        return ingredientType;
    }
}
