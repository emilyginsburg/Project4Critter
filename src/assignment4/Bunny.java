package assignment4;
import java.util.List;
/* CRITTERS Bunny.java
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
//

/**
 * Bunny (class made by eag3598)
 * Time Step:
 *          After a Bunny has been through a time step, it grows up from "b" to "B"
 *          Bunnys give birth to up to 4 (energy provided) children each time step
 * Fight:
 *          Bunnys always fight, unless it is against Algae, in which case they ignore the algae
 * Report Stats:
 *          The amount of Bunnys is reported
 *          The amount of Baby Bunnys is reported ("b")
 *          The amount of Grown Bunnys is reported ("B")
 */
public class Bunny extends Critter {

    private String bunnyChar;

    /**
     * Bunny() is the constructor. All Bunnies start of "b" not "B"
     */
    public Bunny() {
        bunnyChar = "b";
    }

    /**
     * toString() overrides for this class.
     * @return returns a string representation of the Bunny.
     */
    @Override
    public String toString() {
        return bunnyChar;
    }

    /**
     * doTimeStep() updates the Bunny behaviors.
     * determines configuration of babies and reproduces in that pattern.
     */
    @Override
    public void doTimeStep() {
        bunnyChar = "B"; // grown up
        if(getEnergy() % 2 == 0)    // + configuration of babies
        {
            for(int i = 0; i < 8; i += 2)
            {
                Bunny baby = new Bunny();
                reproduce(baby, i);
            }
        }
        else    // x configuration of babies
        {
            for(int i = 1; i < 8; i+= 2)
            {
                Bunny baby = new Bunny();
                reproduce(baby, i);
            }
        }
    }

    /**
     * fight() determines whether the Bunny will fight in an encounter or not
     * @param opponent is the string representation of the opponent the Bunny encountered
     * @return returns true unless the Bunny fights an Algae
     */
    @Override
    public boolean fight(String opponent) {
        if(opponent.equals("@"))    // carnivore
            return false;

        return true;    // fights for babies
    }

    /**
     * runStats() compiles and outputs the statistics of the class that it is called on.
     * @param critters is the list of Critters (precondition: the passed list must contain
     *                 Critters of the same kind that it is called on)
     */
    public static void runStats(List<Critter> critters) {
        int totalBabyBunnies = 0;
        int totalRabbits = 0;
        for(Critter b : critters)
        {
            Bunny bunny = (Bunny) b;
            if(bunny.bunnyChar.equals("b"))
                totalBabyBunnies++;
            else    // "B"
                totalRabbits++;
        }
        System.out.print("" + critters.size() + " total Bunnies,    ");
        System.out.print("" + totalBabyBunnies + " total Baby Bunnies,    ");
        System.out.print("" + totalRabbits + " total Grown Rabbits    ");
        System.out.println();
    }
}
