package com.seigneurin.mowitnow;

import org.junit.Assert;
import org.junit.Test;

import com.seigneurin.mowitnow.instructions.Instruction;
import com.seigneurin.mowitnow.model.Field;
import com.seigneurin.mowitnow.model.Mower;
import com.seigneurin.mowitnow.model.Orientation;
import com.seigneurin.mowitnow.model.Position;
import com.seigneurin.mowitnow.service.MowingService;

public class TestXebiaInstructions {

    /**
     * Test the first mower.
     * 
     * Instructions:
     * 5 5
     * 1 2 N
     * GAGAGAGAA
     * 
     * Result: 1 3 N
     */
    @Test
    public void testFirstMower() {

        MowingService mowingService = new MowingService();

        Field field = new Field(6, 6); // +1 for width and height because instructions are zero-based
        Position initialPosition = new Position(1, 2, Orientation.North);
        Mower mower = new Mower(initialPosition);

        mowingService.applyInstruction(field, mower, Instruction.TurnLeft);
        mowingService.applyInstruction(field, mower, Instruction.MoveForward);
        mowingService.applyInstruction(field, mower, Instruction.TurnLeft);
        mowingService.applyInstruction(field, mower, Instruction.MoveForward);
        mowingService.applyInstruction(field, mower, Instruction.TurnLeft);
        mowingService.applyInstruction(field, mower, Instruction.MoveForward);
        mowingService.applyInstruction(field, mower, Instruction.TurnLeft);
        mowingService.applyInstruction(field, mower, Instruction.MoveForward);
        mowingService.applyInstruction(field, mower, Instruction.MoveForward);

        Position endingPosition = mower.getPosition();
        Assert.assertEquals(1, endingPosition.getX());
        Assert.assertEquals(3, endingPosition.getY());
        Assert.assertEquals(Orientation.North, endingPosition.getOrientation());
    }

    /**
     * Test the second mower.
     * 
     * Instructions:
     * 5 5
     * 3 3 E
     * AADAADADDA
     * 
     * Result: 5 1 E
     */
    @Test
    public void testSecondMower() {

        MowingService mowingService = new MowingService();

        Field field = new Field(6, 6); // +1 for width and height because instructions are zero-based
        Position initialPosition = new Position(3, 3, Orientation.East);
        Mower mower = new Mower(initialPosition);

        mowingService.applyInstruction(field, mower, Instruction.MoveForward);
        mowingService.applyInstruction(field, mower, Instruction.MoveForward);
        mowingService.applyInstruction(field, mower, Instruction.TurnRight);
        mowingService.applyInstruction(field, mower, Instruction.MoveForward);
        mowingService.applyInstruction(field, mower, Instruction.MoveForward);
        mowingService.applyInstruction(field, mower, Instruction.TurnRight);
        mowingService.applyInstruction(field, mower, Instruction.MoveForward);
        mowingService.applyInstruction(field, mower, Instruction.TurnRight);
        mowingService.applyInstruction(field, mower, Instruction.TurnRight);
        mowingService.applyInstruction(field, mower, Instruction.MoveForward);

        Position endingPosition = mower.getPosition();
        Assert.assertEquals(5, endingPosition.getX());
        Assert.assertEquals(1, endingPosition.getY());
        Assert.assertEquals(Orientation.East, endingPosition.getOrientation());
    }

}
