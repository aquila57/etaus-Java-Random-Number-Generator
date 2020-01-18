/***************************************************************************
 *  Compilation:  javac Etaus.java
 *  Execution:    methods called as subroutines
 *
 *  Generates random numbers
 *
 *  Examples of calling the methods of Etaus Class
 *  % Etaus.init()
 *  % Etaus.gen()
 *  % Etaus.get()
 *  % Etaus.random()
 *
 **************************************************************************/
/* Etaus.java  - etaus random number generator, core methods         */
/* Version 0.1.0                                                     */
/* Copyright (C) 2020 aquila57 at github.com                         */

/* This program is free software; you can redistribute it and/or     */
/* modify it under the terms of the GNU General Public License as    */
/* published by the Free Software Foundation; either version 2 of    */
/* the License, or (at your option) any later version.               */

/* This program is distributed in the hope that it will be useful,   */
/* but WITHOUT ANY WARRANTY; without even the implied warranty of    */
/* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the      */
/* GNU General Public License for more details.                      */

/* You should have received a copy of the GNU General Public License */
/* along with this program; if not, write to:                        */

   /* Free Software Foundation, Inc.                                 */
   /* 59 Temple Place - Suite 330                                    */
   /* Boston, MA 02111-1307, USA.                                    */

public class Etaus {

    static long s1;         /* seed number 1 */
    static long s2;         /* seed number 2 */
    static long s3;         /* seed number 3 */
    static long out;        /* current output */
    static long prev;       /* previous output */
    static long pprev;      /* prior-previous output */
    static long maxint;     /* maximum integer 2^32 */
    static long mask;       /* and mask 2^32 - 1    */
    static double unif;     /* random number zero to one */
    static double modulus;     /* double precision 2^32 */

    // 16384 etaus registers for the Bays-Durham shuffle */

    static long[] state = new long[16384];

    public static void init() {
       int i;
       long num;
       double dblnum;
       modulus  = 65536.0;
       modulus *= 65536.0;
       maxint  = 65536;
       maxint *= 65536;
       mask = maxint - 1;
       //------------------------------------------------
       // Populate all etaus registers with random 32 bit
       // integers.
       //------------------------------------------------
       for (i=0;i<16384;i++)
          {
	  dblnum = Math.random() * modulus;
	  num = (long) Math.floor(dblnum);
	  state[i] = num;
	  } /* for each register in state */
       dblnum = Math.random() * modulus;
       num = (long) Math.floor(dblnum);
       prev = num;
       dblnum = Math.random() * modulus;
       num = (long) Math.floor(dblnum);
       pprev = num;
       dblnum = Math.random() * modulus;
       num = (long) Math.floor(dblnum);
       out = num;
       //--------------------------------------------------
       // Populate etaus seeds with random 32 bit
       // integers.
       //--------------------------------------------------
       dblnum = Math.random() * modulus;
       num = (long) Math.floor(dblnum);
       s1 = num;
       dblnum = Math.random() * modulus;
       num = (long) Math.floor(dblnum);
       s2 = num;
       dblnum = Math.random() * modulus;
       num = (long) Math.floor(dblnum);
       s3 = num;
       } // init()

    public static long gen() {
       long out = 0;
       long tmp;
       int indx;
       //-----------------------------------------
       // Back up previous LFSR and fibonacci numbers
       //-----------------------------------------
       pprev = prev;
       prev  = out;
       //-----------------------------------------
       // Create a new etaus generation
       //-----------------------------------------
       s1 = (((s1&0xfffffffe)<<12)^(((s1<<13)^s1)>>19));
       s2 = (((s2&0xfffffff8)<<4)^(((s2<<2)^s2)>>25));
       s3 = (((s3&0xfffffff0)<<17)^(((s3<<3)^s3)>>11));
       out = s1 ^ s2 ^ s3;
       //-----------------------------------------
       // Bays-Durham shuffle
       //-----------------------------------------
       indx = (int) (pprev & 16383);
       tmp = state[indx];
       state[indx] = out & mask;
       out = tmp & mask;
       //-----------------------------------------
       // generate output
       //-----------------------------------------
       out = (out ^ prev ^ pprev) & mask;
       unif = out;
       unif /= modulus;
       return(out);
       } // gen()

    //-----------------------------------------
    // Display generator output
    //-----------------------------------------
    public static void putetaus() {
       System.out.print("out ");
       System.out.println(out);
       System.out.print("unif ");
       System.out.println(unif);
       } // putlfsr()

    //-----------------------------------------
    // Debugging method to display registers
    //-----------------------------------------
    public static void putreg() {
       System.out.print("s1 ");
       System.out.print(s1);
       System.out.print("s2 ");
       System.out.print(s2);
       System.out.print("s3 ");
       System.out.print(s3);
       System.out.print("out ");
       System.out.println(out);
       System.out.print("unif ");
       System.out.print(unif);
       System.out.print(" prev ");
       System.out.print(prev);
       System.out.print(" pprev ");
       System.out.println(pprev);
       } // putreg()

    //-----------------------------------------
    // Output 32 bit random integer
    //-----------------------------------------
    public static long get() {
       return(out);
       } // get()

    //-----------------------------------------
    // Output double precision random number
    // from zero to one
    //-----------------------------------------
    public static double random() {
       return(unif);
       } // random()

} // Etaus Class

