package assignment4;
/* CRITTERS Dementor.java
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
 * Dementors (class made by mal5427)
 * Time Step:
 *          Dementors change direction counter clockwise every 5 Steps
 *          Dementors reproduce every 7 steps
 *          Dementors run otherwise
 * Fight:
 *          Dementors always fight.
 * Report Stats:
 *          The amount of Dementors is reported
 *          The amount of Kills by Dementors that remain alive is reported
 */
public class Dementor extends Critter{

    private int dir;
    private static int kills;
    private int moves;

    /**
     * Dementor() is the constructor. Sets direction to a random number out of 8. sets kill count and move count to 0.
     */
    Dementor(){
        dir = Critter.getRandomInt(8);
        moves = 0;
        kills = 0;
    }

    /**
     * getKills() is the accessor for the kills counter
     * @return
     */
    private int getKills(){
        return kills;
    }

    /**
     * toString() overrides for this class.
     * @return returns a string representation of the Emily.
     */
    @Override
    public String toString() {
        return "D" + dir + "|" + getEnergy(); }

    /**
     * doTimeStep() updates the Dementor's Behavior.
     * dementor changes direction every 5 steps and reproduces every 7 steps
     */
    @Override
    public void doTimeStep() {
        moves++;
        //changes direction every 5 steps
        if(moves % 5 == 0)
            dir++;
        //reproduce every 7 steps
        if(moves % 7 == 0){
            Dementor child = new Dementor();
            reproduce(child, Critter.getRandomInt(8));
        }else run(dir);
    }

    /**
     * fight() always returns true. kill count is incremented. the input string is never used.
      * @param not_used
     * @return returns true
     */
    @Override
    public boolean fight(String not_used) {
        kills++;
        return true;            //Dementors fight everything else
    }

    /**
     * runStats() compiles and outputs the statistics of the class that it is called on.
     * @param critters is the list of Critters (precondition: the passed list must contain
     *                 Critters of the same kind that it is called on)
     */
    public static void runStats(java.util.List<Critter> critters) {
        int totalKills = 0;
        for(Critter dem : critters){
            Dementor dementor = (Dementor) dem;
            totalKills += dementor.getKills();
        }

        System.out.print("" + critters.size() + " total Dementors,    ");
        System.out.print("" + totalKills + " total kills by Dementors    ");
        System.out.println();
    }
}
