package assignment4;

import java.util.ArrayList;

public class CritterWorld {

    public static ArrayList<Critter> world;
    public static Critter[][] grid;

    public CritterWorld() {

        world = new ArrayList<Critter>();
        grid = new Critter[Params.world_width][Params.world_height];    // used for printing ONLY
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







}
