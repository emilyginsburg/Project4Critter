package assignment4;

import java.util.ArrayList;
/* CRITTERS CritterWorld.java
 * EE422C Project 4 submission by
 * Marielle Lopez
 * mal5427
 * 15495
 * Emily Ginsburg
 * eag3598
 * 15495
 * Slip days used: <0>
 * Spring 2018
 */

/**
 * CritterWorld class holds all the critters created in the world and provides functionality.
 */
public class CritterWorld {

    public static ArrayList<Critter> world;
    public static ArrayList<Critter> dementors;
    public static ArrayList<Critter> monkeys;

    /**
     * CritterWorld() is the constructor. A new empty structure is created and stored into the world private variable
     */
    public CritterWorld() {
        world = new ArrayList<Critter>();
    }


    /**
     * addToWorld() adds critter to world and grid, precondition is that the critter does not yet exist in the world.
     */
    public static void addToWorld(Critter critter) {
        world.add(critter);
    }

    /**
     * removeFromWorld() removes critter from world, precondition is that critter is in world
      */
    public static void removeFromWorld(Critter critter) {
        world.remove(critter);
    }



}
