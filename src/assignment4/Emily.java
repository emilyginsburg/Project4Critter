package assignment4;

public class Emily extends Critter {

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
        run(direction);
        // TODO cannot run to a spot that is occupied by another critter
        return false;
    }
}
