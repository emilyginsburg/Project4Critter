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

import java.util.ArrayList;

public class CritterWorld {

    public static ArrayList<Critter> world;
    public static Critter[][] grid;

    public CritterWorld() {

        world = new ArrayList<Critter>();
        grid = new Critter[Params.world_width][Params.world_height];    // used for printing ONLY
        for(int i = 0; i < Params.world_width; i++)
            for(int j = 0; j < Params.world_height; j++)
                grid[i][j] = null;
    }


    // adds critter to world and grid, assumes critter is new to world
    public static void addToWorld(Critter critter, int x, int y) {
        world.add(critter);
        grid[x][y] = critter;   // TODO account for multiple critters
        return;
    }

    // removes critter from world, precondition critter is in world
    public static void removeFromWorld(Critter critter, int x, int y) {
        world.remove(critter);
        grid[x][y] = null;
        return;
    }

    //checks if there is a Critter in the location on the world
    //true if spot is populated,


    public static boolean lookInWorld(int x, int y){
        Critter critter = grid [x][y];
        if(critter == null) return false;
        return true;
    }






}
