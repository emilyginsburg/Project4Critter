package assignment4;

/* CRITTERS Monkey.java
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
 * Monkeys (class made by mal5427)
 * Time Step:
 *          Monkeys reproduce every 3 Steps
 *          Monkeys run otherwise.
 * Fight:
 *          Monkeys always fight, unless it is against a dementor in which case, they run away
 *          The amount of Algae a monkey eats is always counted
 * Run Stats:
 *          The amount of Monkeys present is reported
 *          The amount of Algae encountered and eaten by Monkeys that are alive is reported
 */
public class Monkey extends Critter{

    private int dir;
    private int moves;
    private int algaeEaten;

    /**
     * Monkey() is the constructor. direction is set to a random number max 8.
     */
    Monkey(){
        dir = Critter.getRandomInt(8);  //get direction
    }

    /**
     * toString() overrides for this class.
     * @return returns a string representation of the Emily.
     */
    @Override
    public String toString() { return "M"; }

    @Override
    public void doTimeStep() {
        moves++;
        //reproduces every 3 time steps
        if(moves % 3 == 0){
            Monkey child = new Monkey();
            reproduce(child, Critter.getRandomInt(8));
        }else{
            run(dir);
        }

    }

    /**
     * fight() monkey always fights unless it encounters a dementor which it ignores
     * @param oponent is the critter they encounters
     * @return returns boolean true if opponent is not a dementor and true otherwise
     */
    @Override
    public boolean fight(String oponent) {
        if(oponent.equals("D"))
            return false;
        //count algae encounters
        if(oponent.equals("@"))
            algaeEaten++;
        return true;
    }

    /**
     * runStats() compiles and outputs the statistics of the class that it is called on.
     * @param critters is the list of Critters (precondition: the passed list must contain
     *                 Critters of the same kind that it is called on)
     */
    public static void runStats(java.util.List<Critter> critters){
        int totalAlgaeEaten = 0;
        for(Critter mon : critters){
            Monkey monkey = (Monkey) mon;
            totalAlgaeEaten += monkey.algaeEaten;
        }
        System.out.print("" + critters.size() + " total Monkeys,    ");
        System.out.print("" + totalAlgaeEaten + " total Algae Eaten by Monkeys    ");
        System.out.println();
    }
}
