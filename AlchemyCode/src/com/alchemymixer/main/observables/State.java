package com.alchemymixer.main.observables;

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
     * Constructs a new State object with the given representative Units. The given Units must be in ascending order of
     * value.
     * @param representativeUnits The Units that can represent a quantity of an AlchemicalIngredient in this State
     */
    State(Unit[] representativeUnits) {
        this.representativeUnits = representativeUnits;
    }

    /**
     * Returns the representative Units of this State.
     * @return the representative Units of this State
     */
    public Unit[] getRepresentativeUnits() {
        return representativeUnits;
    }
}
