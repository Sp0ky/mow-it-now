package com.seigneurin.mowitnow.service;

import com.seigneurin.mowitnow.instructions.Instruction;
import com.seigneurin.mowitnow.model.Field;
import com.seigneurin.mowitnow.model.Mower;
import com.seigneurin.mowitnow.model.Orientation;
import com.seigneurin.mowitnow.model.Position;

/**
 * A service responsible for managing a mower on a field.
 */
public class MowingService {

    /**
     * Applies an instruction to a mower, taking care of the definition of the field.
     */
    public void applyInstruction(Field field, Mower mower, Instruction instruction) {

        // apply the instruction to compute the next position (may be an invalid position)
        Position nextPosition = computeNextPosition(mower.getPosition(), instruction);

        // check if the position is valid (within the field)
        boolean isValid = isWithinField(nextPosition, field);

        // only set the new position if it is valid
        if (isValid)
            mower.setPosition(nextPosition);
    }

    /**
     * Checks whether the position is within the boundaries of the field.
     */
    private boolean isWithinField(Position position, Field field) {

        int x = position.getX();
        if (x < 0 || x >= field.getWidth())
            return false;

        int y = position.getY();
        if (y < 0 || y >= field.getHeight())
            return false;

        return true;
    }

    /**
     * Given an instruction, computes the next position regardless of whether the resulting position is valid or not.
     */
    private Position computeNextPosition(Position position, Instruction instruction) {

        Position res;

        if (instruction == Instruction.MoveForward)
            res = moveForward(position);
        else if (instruction == Instruction.TurnLeft)
            res = turnLeft(position);
        else if (instruction == Instruction.TurnRight)
            res = turnRight(position);
        else
            throw new RuntimeException("Instruction not supported: " + instruction);

        return res;
    }

    /**
     * Returns a new position after moving forward (the result may be invalid).
     */
    private Position moveForward(Position position) {

        Orientation orientation = position.getOrientation();
        int x = position.getX();
        int y = position.getY();

        if (orientation == Orientation.North)
            y++;
        else if (orientation == Orientation.South)
            y--;
        else if (orientation == Orientation.East)
            x++;
        else if (orientation == Orientation.West)
            x--;

        Position res = new Position(x, y, orientation);
        return res;
    }

    /**
     * Returns a new position after turning left.
     */
    private Position turnLeft(Position position) {

        Orientation orientation = position.getOrientation();
        int x = position.getX();
        int y = position.getY();

        if (orientation == Orientation.North)
            orientation = Orientation.West;
        else if (orientation == Orientation.South)
            orientation = Orientation.East;
        else if (orientation == Orientation.East)
            orientation = Orientation.North;
        else if (orientation == Orientation.West)
            orientation = Orientation.South;

        Position res = new Position(x, y, orientation);
        return res;
    }

    /**
     * Returns a new position after turning right.
     */
    private Position turnRight(Position position) {

        Orientation orientation = position.getOrientation();
        int x = position.getX();
        int y = position.getY();

        if (orientation == Orientation.North)
            orientation = Orientation.East;
        else if (orientation == Orientation.South)
            orientation = Orientation.West;
        else if (orientation == Orientation.East)
            orientation = Orientation.South;
        else if (orientation == Orientation.West)
            orientation = Orientation.North;

        Position res = new Position(x, y, orientation);
        return res;
    }

}
