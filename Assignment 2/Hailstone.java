/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		int n = readInt("Enter a number: ");
		int i = 0;
		while (n!=1){
			if (n%2 == 1){
				int temp = 3*n +1;
				println(n + " is odd, so I make 3n + 1: "+ temp);
				n = temp;
				i++;
			} else {
				int temp = n / 2;
				println(n + " is even, so I take half: "+ temp);
				n = temp;
				i++;
			}
		}
		println("The process took " + i + " to reach 1");
		
	}
}

