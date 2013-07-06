package com.seigneurin.mowitnow.model;

/**
 * A mower.
 * 
 * The mower has a position (location + orientation).
 */
public class Mower {

    private Position position;

    public Mower(Position initialPosition) {
        this.position = initialPosition;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}
