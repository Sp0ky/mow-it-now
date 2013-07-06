package com.seigneurin.mowitnow;

import com.seigneurin.mowitnow.instructions.InstructionsScanner;

/**
 * Execute this to mow with instructions coming from the command line.
 */
public class MowFromConsole {

    public static void main(String[] args) {

        System.out.println("Please type in data and instructions...");

        InstructionsScanner scanner = new InstructionsScanner();
        scanner.processStream(System.in, System.out);
    }

}
