package com.alchemymixer.main.observables;

/**
 * The State enum
 * This enum holds all physical states that an AlchemicalIngredient can be in (such as Powder or Liquid). Each state
 * holds an array of Units, in ascending order by their values, which are all the Units that can be used to represent an
 * amount of an ingredient in said state.
 *
 * @invar The representativeUnits of a State are in ascending order
 * @invar representativeUnits is never null
 *
 * @author Dieter "Dimme" D.
 * @version 1.0.1
 */
public enum State {
    Powder(new Unit[]{
            Unit.Pinch, Unit.Spoon, Unit.Sachet, Unit.Box, Unit.Sack, Unit.Chest, Unit.Storeroom
    }),
    Liquid(new Unit[]{
            Unit.Drop, Unit.Spoon, Unit.Vial, Unit.Bottle, Unit.Jug, Unit.Barrel, Unit.Storeroom
    });

    /**
     * The Units that can represent a quantity of an AlchemicalIngredient in this State
     */
    private final Unit[] representativeUnits;

    /**
     * Constructs a new State object with the given representative Units. The array may not be null or empty, and the
     * given Units must be in ascending order of value.
     * @param representativeUnits The Units that can represent a quantity of an AlchemicalIngredient in this State
     */
    State(Unit[] representativeUnits) {
        this.representativeUnits = representativeUnits;
    }

    /**
     * The getter for the representative Units of this State.
     * @return the representative Units of this State
     */
    public Unit[] getRepresentativeUnits() {
        return representativeUnits;
    }
}
