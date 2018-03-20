package assignment4;

import java.util.List;

public class Emily extends Critter {

    public Emily() {

    }

    @Override
    public String toString() { return "e"; }


    @Override
    public void doTimeStep() {
        // if we want her to be able to run away from fights, she cannot move during timestep

    }

    @Override
    public boolean fight(String not_used) {
        // Emily tries to run away from the fight
        int direction = getEnergy() % 8;
        run(direction); // cannot run to a spot that is occupied by another critter, implemented in move function
        return false;   // if run fails, Emily will not fight back
    }

    public static void runStats(List<Critter> critters) {

    }
}
