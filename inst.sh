#!/bin/bash
make -f libks.mak
make -f kstest.mak
make -f binom.mak
javac Etaus.java
javac KSTest.java
javac Binom.java
javac Timing.java
javac JavaRand.java
javac DieGen.java
