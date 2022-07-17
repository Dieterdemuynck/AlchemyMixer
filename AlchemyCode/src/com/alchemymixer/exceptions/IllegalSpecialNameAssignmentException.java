package com.alchemymixer.exceptions;

import com.alchemymixer.main.alchemicIngredient.IngredientType;

/**
 * Exception class which is thrown when an attempt was made to assign a special name to a non-mixed ingredient.
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
     * Returns the IngredientType to which a special name was assigned.
     * @return the IngredientType to which a special name was assigned
     */
    public IngredientType getIngredientType() {
        return ingredientType;
    }
}
