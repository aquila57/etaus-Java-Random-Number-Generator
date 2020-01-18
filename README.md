# etaus Random Number Generator in Java

This is a Java implementation of the random number
generator in the etaus repository.

The random number generator here is driven by the 32 bit
Tausworthe algorithm and a Bays-Durham shuffle of 16384
registers.  Each 32 bit output is the result of XORing the
current output with two previous outputs.  The expected
period length of this generator approximates the permutations
of 16384, or 1.2 * 10^61036.  This period length is orders
of magnitude longer than the Mercenne Twister, which has a
period length of 2^19937.

The performance of this generator is equivalent to the
Java generator, Math.random();

So far this generator passes the Kolmogorov-Smirnov test
and the binomial distribution test.

The etaus generator is contained in the Etaus.java class.
Etaus.gen() produces a 32 bit random integer with a uniform
distribution.
Etaus.random() produces a double precision uniform number
between zero and one.
