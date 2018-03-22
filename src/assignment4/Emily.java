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
//Emily
//Time Step:
//          Emilys change direction randomly, based on energy, each time step
//Fight:
//          Emilys will either try to run away from a fight (freak out), or they will not fight at all
//Report Stats:
//          The amount of Emilys is reported
//          The amount of Freakouts by Emilys is reported


import java.util.List;

public class Emily extends Critter {

    private int direction;
    private int numFreakOuts;

    public Emily() {
        direction = 0;
        numFreakOuts = 0;
    }

    @Override
    public String toString() { return "e"; }


    @Override
    public void doTimeStep() {
        // if we want her to be able to run away from fights, she cannot move during timestep
        direction = getEnergy() % 8;
    }

    @Override
    public boolean fight(String not_used) {
        // Emily tries to run away from the fight
        if(direction % 8 >= 4)  // freak out
        {
            run(direction); // cannot run to a spot that is occupied by another critter, implemented in move function
            numFreakOuts++;
        }
        return false;   // if run fails, or if no freak out, Emily will not fight back
    }

    public static void runStats(List<Critter> critters) {
        int totalFreakOuts = 0;
        for(Critter em : critters)
        {
            Emily emily = (Emily) em;
            totalFreakOuts += emily.numFreakOuts;
        }

        System.out.print("" + critters.size() + " total Emilys,    ");
        System.out.print("" + totalFreakOuts + " total Freakouts by Emilys    ");
        System.out.println();
    }
}
