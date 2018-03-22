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
//Bunnys
//Time Step:
//          After a Bunny has been through a time step, it grows up from "b" to "B"
//          Bunnys give birth to up to 4 (energy provided) children each time step
//Fight:
//          Bunnys always fight, unless it is against Algae, in which case they ignore the algae
//Report Stats:
//          The amount of Bunnys is reported
//          The amount of Baby Bunnys is reported ("b")
//          The amount of Grown Bunnys is reported ("B")


import java.util.List;

public class Bunny extends Critter {

    private String bunnyChar;

    public Bunny() {
        bunnyChar = "b";
    }

    @Override
    public String toString() {
        return bunnyChar;
    }


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

    @Override
    public boolean fight(String opponent) {
        if(opponent.equals("@"))    // carnivore
            return false;

        return true;    // fights for babies
    }


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
