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
//Monkeys
//Time Step:
//          Monkeys reproduce every 3 Steps
//          Monkeys run otherwise.
//Fight:
//          Monkeys always fight, unless it is against a dementor in which case, they run away
//          The amount of Algae a monkey eats is always counted
//Run Stats:
//          The amount of Monkeys present is reported
//          The amount of Algae encountered and eaten by Monkeys is reported

public class Monkey extends Critter{

    private int dir;
    private int reproductionCount;
    private int algaeEaten;


    Monkey(){
        dir = Critter.getRandomInt(8);  //get direction
    }

    @Override
    public String toString() { return "M"; }

    @Override
    public void doTimeStep() {
        //reproduces every 3 time steps
        if(reproductionCount % 3 == 0){
            Monkey child = new Monkey();
            reproduce(child, Critter.getRandomInt(8));
            reproductionCount++;
        }else{
            run(dir);
        }

    }

    @Override
    public boolean fight(String oponent) {
        //monkey is scared of dementors
        if(oponent.equals("D"))
            return false;
        //count algae encounters
        if(oponent.equals("@"))
            algaeEaten++;
        return true;
    }

    public static void runStats(java.util.List<Critter> monkeys){
        int totalAlgaeEaten = 0;
        for(Critter mon : monkeys){
            Monkey monkey = (Monkey) mon;
            totalAlgaeEaten += monkey.algaeEaten;
        }
        System.out.print("" + monkeys.size() + " total Monkeys,    ");
        System.out.print("" + totalAlgaeEaten + " total Algae Eaten by Monkeys    ");
        System.out.println();
    }
}
