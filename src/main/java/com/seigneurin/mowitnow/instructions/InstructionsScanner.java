package com.seigneurin.mowitnow.instructions;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import com.seigneurin.mowitnow.model.Field;
import com.seigneurin.mowitnow.model.Mower;
import com.seigneurin.mowitnow.model.Orientation;
import com.seigneurin.mowitnow.model.Position;
import com.seigneurin.mowitnow.service.MowingService;

/**
 * Scans a text-input stream to read instructions, applies them and writes the result on an output stream.
 * 
 * <b>Important: assumes the file is well-formed.</b>
 */
public class InstructionsScanner {

    private MowingService mowingService = new MowingService();

    /**
     * Reads the input stream passed in argument and writes the position of each mower on the passed output stream.
     */
    public void processStream(InputStream in, PrintStream out) {

        Scanner scanner = new Scanner(in);

        // read the definition of the field
        Field field = createField(scanner);

        while (true) {

            // read the mower and the instructions (and apply them)
            Mower mower = createMower(scanner);
            readAndApplyInstructions(scanner, field, mower);

            // print out the result
            print(mower, out);

            // stop when there's no more input
            if (scanner.hasNext() == false)
                break;
        }

        scanner.close();
    }

    private Field createField(Scanner scanner) {

        // notice that we add 1 because the inputs are 0-based
        int width = scanner.nextInt() + 1;
        int heigth = scanner.nextInt() + 1;

        Field field = new Field(width, heigth);
        return field;
    }

    private Mower createMower(Scanner scanner) {

        int x = scanner.nextInt();
        int y = scanner.nextInt();
        String cardinal = scanner.next("[NSEW]");

        Orientation orientation = getOrientation(cardinal);
        Position position = new Position(x, y, orientation);

        Mower mower = new Mower(position);
        return mower;
    }

    private Orientation getOrientation(String cardinal) {
        if ("N".equals(cardinal))
            return Orientation.North;
        if ("S".equals(cardinal))
            return Orientation.South;
        if ("E".equals(cardinal))
            return Orientation.East;
        if ("W".equals(cardinal))
            return Orientation.West;
        throw new IllegalArgumentException("Cardinal must be one of: N, S, E, W");
    }

    private void readAndApplyInstructions(Scanner scanner, Field field, Mower mower) {

        String instructions = scanner.next("[DGA]+");
        for (char c : instructions.toCharArray()) {
            Instruction instruction = getInstruction(c);
            mowingService.applyInstruction(field, mower, instruction);
        }
    }

    private Instruction getInstruction(char c) {
        if (c == 'D')
            return Instruction.TurnRight;
        if (c == 'G')
            return Instruction.TurnLeft;
        if (c == 'A')
            return Instruction.MoveForward;
        throw new IllegalArgumentException("Instruction must be one of: D, G, A");
    }

    private void print(Mower mower, PrintStream out) {
        Position position = mower.getPosition();
        int x = position.getX();
        int y = position.getY();
        Orientation orientation = position.getOrientation();
        char c = getChar(orientation);
        out.println(x + " " + y + " " + c);
    }

    private char getChar(Orientation orientation) {
        if (orientation == Orientation.North)
            return 'N';
        if (orientation == Orientation.South)
            return 'S';
        if (orientation == Orientation.East)
            return 'E';
        if (orientation == Orientation.West)
            return 'W';
        throw new IllegalArgumentException("Unknown orientation: " + orientation);
    }

}
