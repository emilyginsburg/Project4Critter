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
//Dementors
//Time Step:
//          Dementors change direction counter clockwise every 5 Steps
//          Dementors reproduce every 7 steps
//          Dementors run otherwise
//Fight:
//          Dementors always fight, unless it is against Algae, in which case they ignore the algae
//Report Stats:
//          The amount of Dementors is reported
//          The amount of Kills by Dementors is reported

public class Dementor extends Critter{

    private int dir;
    //TODO: what happens when dementor dies? How can I keep the amount of kills it had while still removing it from the world?
    //add STATIC modifier to kills variable so all the same?
    //TODO: Same problem in Monkey
    private int kills = 0;
    private int moves;
    private int reproductionCount;

    Dementor(){
        dir = Critter.getRandomInt(8);
        moves = 0;
        reproductionCount = 0;
    }
    private int getKills(){
        return kills;
    }

    @Override
    public String toString() { return "D"; }

    @Override
    public void doTimeStep() {
        //changes direction every 5 steps
        if(moves % 5 == 0)
            dir++;
        //reproduce every 7 steps
        if(reproductionCount % 7 == 0){
            Dementor child = new Dementor();
            reproduce(child, Critter.getRandomInt(8));
        }else run(dir);
    }

    @Override
    public boolean fight(String oponent) {
        //does not fight algae only
        if(oponent.equals("@"))
            return false;
        return true;            //Dementors fight everything else
    }

    public static void runStats(java.util.List<Critter> dementors) {
        int totalKills = 0;
        for(Critter dem : dementors){
            Dementor dementor = (Dementor) dem;
            totalKills += dementor.getKills();
        }

        System.out.print("" + dementors.size() + " total Dementors,    ");
        System.out.print("" + totalKills + " total kills by Dementors    ");
        System.out.println();
    }
}
