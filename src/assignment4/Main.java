package assignment4;
/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Marielle Lopez
 * mal5427
 * 15495
 * Emily Ginsburg
 * eag3598
 * 15495
 * Slip days used: <0>
 * Fall 2016
 */

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import java.io.*;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) throws InvalidCritterException {
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }

        /* Do not alter the code above for your submission. */
        /* Write your code below. */
        //prompt and read

        CritterWorld critterWorld = new CritterWorld();
        while(true)
        {
            System.out.println("critters> ");
            String input = kb.nextLine();
            String[] split = input.split(" ");  //space is the delimiter
            try
            {
                if(split[0].equals("quit"))
                    break;
                if(split[0].equals("show"))
                {
                    if(split.length != 1)
                        throw new InvalidCritterException("show");

                    Critter.displayWorld();
                }
                else if(split[0].equals("step"))
                {
                    if(split.length > 2)
                        throw new InvalidCritterException("step");

                    if(split.length == 1)
                        Critter.worldTimeStep();
                    else
                    {
                        int steps = Integer.parseInt(split[1]);
                        for(int i = 0; i < steps; i++)
                            Critter.worldTimeStep();
                    }
                }
                else if(split[0].equals("seed"))
                {
                    if(split.length != 2)
                        throw new InvalidCritterException("seed");

                    Critter.setSeed(Integer.parseInt(split[1]));
                }
                else if(split[0].equals("make"))
                {
                    if(split.length != 2 && split.length != 3)
                        throw new InvalidCritterException("make");

                    int i;
                    if(split.length == 2)
                        i = 1;
                    else // length == 3
                        i = Integer.parseInt(split[2]);

                    for(int m = 0; m < i; m++)
                        Critter.makeCritter(split[1]);
                    // TODO check note from pdf
                }
                else if(split[0].equals("stats"))
                {
                    if(split.length != 2)
                        throw new InvalidCritterException("stats");

                    List<Critter> stats = Critter.getInstances(split[1]);

                    for(Critter c : stats)
                        System.out.println(c);

                    Class critterClass = Class.forName("assignment4." + split[1]);
                    Class[] parameters = new Class[1];
                    parameters[0] = java.util.List.class;
                    java.lang.reflect.Method runStats = critterClass.getMethod("runStats", parameters);
                    runStats.invoke(critterClass, stats);
                }
                else // invalid command
                {
                    System.out.println("invalid command: " + input);
                }
            }
            catch(InvalidCritterException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e)
            {
                System.out.println("error processing: " + input);
            }

            //TODO account for tabs in addition to spaces
        }


        /* Write your code above */
        System.out.flush();

    }
}
