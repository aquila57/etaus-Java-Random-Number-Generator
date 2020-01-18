/***************************************************************************
 *  Compilation:  javac Timing.java
 *  Execution:    java Timing
 *
 *  Runs a timing test on 10 million generations of the etaus
 *  random number generator, Etaus.class.
 *
 *  % timing.sh
 *  Timing of this generator is compared to the Java random
 *  number generator Math.random().
 *
 **************************************************************************/
/* Copyright © 2020,  aquila57 at github.com. */

public class Timing {

    public static void main(String[] args) {
	int i;
	Etaus.init();
	for (i=0;i<100000000;i++)
	   {
	   long x;
	   double frac;
	   x = Etaus.gen();
           frac = Etaus.random();
	   } // for each loop
    } // public static void main(String[] args)

} // Timing


