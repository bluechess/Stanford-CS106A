/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	private static final int SENTINEL = 0;
	public void run() {
		println("This program finds the largest and smallest numbers.");

		int sentinel = readInt("? ");
		int largest = sentinel;
		int smallest = sentinel;
		if (sentinel == 0){
			println("!Warning: first sentinel is 0!");
		} else {
			while (sentinel != 0){
				if (largest < sentinel){
					largest = sentinel;
				}
				if (smallest > sentinel){
					smallest = sentinel;
				}
				sentinel = readInt("? ");
			}
			println("smallest: " + smallest);
			println("largest: " + largest);
		}
		
	}
}

