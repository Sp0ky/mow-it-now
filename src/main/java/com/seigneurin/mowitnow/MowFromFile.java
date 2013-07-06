package com.seigneurin.mowitnow;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.seigneurin.mowitnow.instructions.InstructionsScanner;

/**
 * Execute this to mow with instructions coming from a sample file.
 */
public class MowFromFile {

    public static void main(String[] args) throws FileNotFoundException {

        if (args.length != 1)
            throw new RuntimeException("Expecting a filename in argument");

        InputStream in = new FileInputStream(args[0]);
        InstructionsScanner scanner = new InstructionsScanner();
        scanner.processStream(in, System.out);
    }

}
