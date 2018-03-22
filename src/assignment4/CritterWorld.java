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
 * Spring 2018
 */

import java.util.ArrayList;

public class CritterWorld {

    public static ArrayList<Critter> world;

    public CritterWorld() {
        world = new ArrayList<Critter>();
    }


    // adds critter to world and grid, assumes critter is new to world
    public static void addToWorld(Critter critter) {
        world.add(critter);
        return;
    }

    // removes critter from world, precondition critter is in world
    public static void removeFromWorld(Critter critter) {
        world.remove(critter);
        return;
    }



}
