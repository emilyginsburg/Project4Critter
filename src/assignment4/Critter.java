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


import java.util.*;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	private static String myPackage;
	//private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}

	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}

	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}


	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }

	private int energy = 0;
	protected int getEnergy() { return energy; }

	private int x_coord;
	private int y_coord;
	private boolean movedThisStep;
	private static int walking_steps = 1;
	private static int running_steps = 2;


	/*
	*  Note that critters cannot move twice from within the same doTimeStep
	 function. If a Critter subclass calls walk and/or run two (or more) times within a single
	 time step, you must deduct the appropriate energy cost from the critter for walking/running,
	 but you must not actually alter the critter’s position. Critters can die in this fashion
	* */

	protected final void walk(int direction) {
		if(!movedThisStep)
			this.move(direction, walking_steps);
		energy -= Params.walk_energy_cost;
	}

	protected final void run(int direction) {
		if(!movedThisStep)
			this.move(direction, running_steps);
		energy -= Params.run_energy_cost;
	}

	private final void move(int direction, int steps) {

		int oldX = x_coord;
		int oldY = y_coord;

		if(direction == 0)	// E
			x_coord += steps;
		if(direction == 2)	// N
			y_coord -= steps;
		if(direction == 4)	// W
			x_coord -= steps;
		if(direction == 6)	// S
			y_coord += steps;
		if(direction == 1)	// NE
		{
			x_coord += steps;
			y_coord -= steps;
		}
		if(direction == 3)	// NW
		{
			x_coord -= steps;
			y_coord -= steps;
		}
		if(direction == 5)	// SW
		{
			x_coord -= steps;
			y_coord += steps;
		}
		if(direction == 7)	// SE
		{
			x_coord += steps;
			y_coord += steps;
		}
		// account for world wrapping
		if(x_coord >= Params.world_width)	// fell off right side of world
			x_coord = x_coord - Params.world_width;
		if(x_coord < 0)		// fell off left side of world
			x_coord = x_coord + Params.world_width;
		if(y_coord >= Params.world_height)	// fell off bottom of world
			y_coord = y_coord - Params.world_height;
		if(y_coord < 0)		// fell off top of world
			y_coord = y_coord + Params.world_height;

		movedThisStep = true;

		if(this.getClass().toString().equals("assignment4.Emily"))
		{
			for(Critter c : CritterWorld.world)
				if(c.x_coord == this.x_coord && c.y_coord == this.y_coord)	// illegal move during fight
				{
					this.x_coord = oldX;
					this.y_coord = oldY;
				}
		}



	}


	protected final void reproduce(Critter offspring, int direction) {
		if(this.getEnergy() < Params.min_reproduce_energy)
			return;

		offspring.energy = this.getEnergy() / 2;
		//round parent energy up
		if( this.getEnergy() % 2 != 0)
			this.energy = (this.getEnergy() / 2) + 1;
		else this.energy = this.getEnergy() / 2;

		offspring.x_coord = this.x_coord;
		offspring.y_coord = this.y_coord;
		offspring.walk(direction);
		offspring.movedThisStep = false;

		babies.add(offspring);


	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);

	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		// TODO figure out how to do the error with lowercase class name

		Class critterClass = null;
		try {
			critter_class_name = "assignment4." + critter_class_name;
			critterClass = Class.forName(critter_class_name);
			Critter critter = (Critter) critterClass.newInstance();

			critter.x_coord = Critter.getRandomInt(Params.world_width);
			critter.y_coord = Critter.getRandomInt(Params.world_height);
			critter.energy = Params.start_energy;
			CritterWorld.addToWorld(critter);
			critter.movedThisStep = false;

		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			throw new InvalidCritterException(critter_class_name);
		}


	}

	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();

		try {
			Class critterClass = Class.forName("assignment4." + critter_class_name);
			for(Critter c : CritterWorld.world)
			{
				if(c.getClass().equals(critterClass))
					result.add(c);
			}
		} catch (ClassNotFoundException e) {
			throw new InvalidCritterException(critter_class_name);
		}

		return result;
	}

	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();
	}

	/* the TestCritter class allows some critters to "cheat". If you want to
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here.
	 *
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}

		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}

		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}

		protected int getX_coord() {
			return super.x_coord;
		}

		protected int getY_coord() {
			return super.y_coord;
		}


		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work. EDIT: edited to reflect implementation
		 */
		protected static List<Critter> getPopulation() {
			return CritterWorld.world;
		}

		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population
		 * at either the beginning OR the end of every timestep. EDIT: in use
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		for(Critter c : CritterWorld.world)
			CritterWorld.removeFromWorld(c);
	}

	public static void worldTimeStep() throws InvalidCritterException {

		// TODO
		// 1. increment timestep; timestep++;
		// 2. doTimeSteps();
		// 3. Do the fights. doEncounters();
		// 4. updateRestEnergy();
		// 5. Generate Algae genAlgae();
		// 6. Move babies to general population. population.addAll(babies); babies.clear();

		for(Critter c : CritterWorld.world) {
			c.movedThisStep = false;
			c.doTimeStep();
		}

		resolveEncounters();

		// remove dead critters from world
		for(int i = 0; i < CritterWorld.world.size(); i++)
		{
			Critter c = CritterWorld.world.get(i);
			c.energy -= Params.rest_energy_cost;	// every critter loses rest energy
			if(c.getEnergy() <= 0)
			{
				CritterWorld.removeFromWorld(c);
				i--;
			}
		}


		for(int i = 0; i < Params.refresh_algae_count; i++) {
			makeCritter("Algae");
		}

		// add new critters (babies) to world (after encounters resolved)
		for(Critter baby : babies)
			CritterWorld.addToWorld(baby);

		babies.clear();

	}

	public static void displayWorld() {

		Critter [][] grid = new Critter[Params.world_width][Params.world_height];    // used for printing ONLY
		for(int i = 0; i < Params.world_width; i++)
			for(int j = 0; j < Params.world_height; j++)
				grid[i][j] = null;

		for(Critter c : CritterWorld.world)
		{
			grid[c.x_coord][c.y_coord] = c;
		}

		int i; // used in for loops
		System.out.print("+");
		for(i = 0; i < Params.world_width; i++)
			System.out.print("-");
		System.out.println("+");

		for(i = 0; i<Params.world_height; i++)
		{
			System.out.print("|");
			for(int j = 0; j<Params.world_width; j++)
			{
				if(grid[j][i] != null)	// TODO make sure it should be j i (and not i j)
					System.out.print(grid[j][i].toString());
				else
					System.out.print(" ");
			}
			System.out.println("|");
		}

		System.out.print("+");
		for(i = 0; i < Params.world_width; i++)
			System.out.print("-");
		System.out.println("+");


	}

	// returns arraylist with critters that are found in the same position (all on one)
	// figure out a way so that this only ends when all encounters are found?
	private static void resolveEncounters() {
		for(Critter c1 : CritterWorld.world) {
			for(Critter c2 : CritterWorld.world) {
				if((c1.x_coord == c2.x_coord) && (c1.y_coord == c2.y_coord)){	// encounter found
					if(c1 != c2)
						whoGetsToStay(c1, c2);
				}
			}
		}
	}

	// passes in an arraylist of critters who are on the same location
	// at the return, the loser is dead (enery of zero, but still present in world)
	// loser will be removed from world during time step
	private static void whoGetsToStay(Critter A, Critter B) {
		Critter winner, loser;
		if (A.energy == 0 || B.energy == 0) // one critter is already dead, no fight needed
			return;

		boolean AwantsToFight = A.fight(B.toString());
		boolean BwantsToFight = B.fight(A.toString());

		if ((A.energy != 0) && (B.energy != 0)) // neither critter has died attempting to run
			if ((A.x_coord == B.x_coord) && (A.y_coord == B.y_coord)) {    // neither critter has run away

				// fight occurs
				//dementors always wins (but they don't fight Algae)
				if (A.toString().equals("D") && AwantsToFight) {
					winner = A;
					loser = B;
				} else if (B.toString().equals("D") && BwantsToFight) {
					winner = B;
					loser = A;
					//TODO: if algae and dementor, make sure neither is affected in fight (both stay on map unless energy is drained)
					//dementor is not involved
				} else {
					int diceA, diceB;
					//set dice
					//A
					if (AwantsToFight)
						diceA = getRandomInt(A.energy);
					else
						diceA = 0;
					//B
					if (BwantsToFight)
						diceB = getRandomInt(B.energy);
					else
						diceB = 0;
					//compare the dice
					if (diceA >= diceB) {    // A wins (if they are equal, A also wins)
						winner = A;
						loser = B;
					} else {                // B wins
						winner = B;
						loser = A;
					}
				}

				winner.energy += (loser.energy / 2);
				loser.energy = 0;
			}
	}
	//TODO: finish for algae and dementor
	private static Critter specialCases (Critter A, Critter B,boolean critABool, boolean critBBool){
		return A;


	}
}
