/***************************************************************************
 *  Compilation:  javac KSTest.java
 *  Execution:    java KSTest
 *
 *  Generates 1 million random numbers from zero to one
 *  These numbers are input to a Kolgomorov-Smirnov test.
 *
 *  % java KSTest
 *
 **************************************************************************/
/* KSTest.java  - etaus random number generator for KS Test          */
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

public class KSTest {

    public static void main(String[] args) {
	int i;
	Etaus.init();
	for (i=0;i<1000000;i++)
	   {
	   long x;
	   double frac;
	   x = Etaus.gen();
           frac = Etaus.random();
	   System.out.println(frac);
	   } // for each loop
    } // public static void main(String[] args)

} // KSTest


