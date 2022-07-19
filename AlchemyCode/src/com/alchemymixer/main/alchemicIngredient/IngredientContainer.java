package com.alchemymixer.main.alchemicIngredient;

import com.alchemymixer.main.observables.Quantity;
import com.alchemymixer.main.observables.Unit;

import java.util.Arrays;

/**
 * The IngredientContainer class
 *
 * This class represents objects that hold AlchemicalIngredient, to move them between devices, laboratories, etc. A
 * container must have a capacity equal to exactly one of a certain container unit, and it holds at most one type of
 * ingredient.
 *
 * @invar The contents of this container are either null, or an ingredient with a quantity less than the capacity.
 * @invar The capacity is a container unit (Unit.isContainer())
 *
 * @author Dieter "Dimme" D.
 * @version 1.0
 */
public class IngredientContainer {

    /**
     * The capacity of this container
     */
    private final Quantity capacity;

    /**
     * The contents of this container
     */
    private AlchemicalIngredient content = null;

    /**
     * Constructs a new IngredientContainer with 1 times the given unit as its capacity. If the given unit is not a
     * container unit, which means that the unit itself does not represent a container such as a vial, bottle, sachet...
     * then an IllegalArgumentException will be thrown.
     * @param capacity The container Unit which will be the capacity of this container
     */
    public IngredientContainer(Unit capacity) {
        if (capacity.isContainer())
            this.capacity = new Quantity(1, capacity);
        else
            throw new IllegalArgumentException("capacity must be a container unit");
    }

    /**
     * The getter for the capacity of this container, expressed as a Quantity.
     * @return The capacity of this container, expressed as a Quantity
     */
    public Quantity getCapacity() {
        return capacity;
    }

    /**
     * The getter for the contents of this container.
     * @return The contents of this container
     */
    private AlchemicalIngredient getContent() {
        return content;
    }

    /**
     * The setter for the contents of this container.
     * @param content The AlchemicalIngredient to insert into this container
     */
    private void setContent(AlchemicalIngredient content) {
        this.content = content;
    }

    /**
     * The checker for if this container can hold a given ingredient. Returns true if and only if the container's unit
     * is representative for the state of the ingredient, and the container's capacity is larger than the ingredient's
     * quantity.
     * @param ingredient The ingredient which is checked for if it could be inserted into this container
     * @return True, if and only if the container's unit is representative for the state of the ingredient, and the
     *         container's capacity is larger than the ingredient's quantity.
     */
    public boolean canHold(AlchemicalIngredient ingredient) {
        return Arrays.asList(ingredient.getState().getRepresentativeUnits()).contains(getCapacity().unit())
                && getCapacity().compareTo(ingredient.getQuantity()) >= 0;
    }

    /**
     * The checker for if this container already has ingredient inside, and thus it can not hold any more ingredient.
     * @return True if and only if getContent() != null
     */
    public boolean isFull() {
        return getContent() != null;
    }

    /**
     * Inserts the given ingredient if and only if the ingredient can be held inside the container (canHold(ingredient))
     * and the container is not full (!isFull()). Returns true if the ingredient was successfully inserted, and false if
     * any of the previous conditions were not fulfilled.
     * @param ingredient The AlchemicalIngredient to insert into the container.
     * @return True if the ingredient was successfully inserted, and false if any of the previous conditions were not
     *         fulfilled.
     */
    public boolean insert(AlchemicalIngredient ingredient) {
        if (canHold(ingredient) && !isFull()) {
            setContent(ingredient);
            return true;
        }
        return false;
    }

    /**
     * Retrieves the ingredient from the container, and sets the contents of this container to null.
     * @return The AlchemicalIngredient that was stored inside this container
     */
    public AlchemicalIngredient retrieve() {
        AlchemicalIngredient ingredient = getContent();
        setContent(null);
        return ingredient;
    }
}
