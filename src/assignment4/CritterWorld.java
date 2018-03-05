package assignment4;

import java.util.ArrayList;

public class CritterWorld {

    public static ArrayList<Critter> world;

    public CritterWorld() {
        world = new ArrayList<Critter>();
    }


    // adds critter to world, assumes critter is new to world
    public static void addToWorld(Critter critter) {
        world.add(critter);
        return;
    }

    // removes critter from world, precondition critter is in world
    public static void removeFromWorld(Critter critter) {
        world.remove(critter);
        return;
    }

    // returns arraylist with critters that are found in the same position (all on one)
    // figure out a way so that this only ends when all encounters are found?
    public static void resolveEncounters() {
        return;

    }

    // passes in an arraylist of critters who are on the same location, at the return, only one is left the other is dead
    private static void whoGetsToStay(ArrayList<Critter> critters) {
        return;
    }

}
