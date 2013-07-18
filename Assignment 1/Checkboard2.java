/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class Checkboard2 extends SuperKarel {

	public void run(){
		putRow();
		while (leftIsClear()){
			if (noBeepersPresent()){
				repositionForRowToWest();
				putRow();
			} else {
				repositionForRowToWest();
				if (frontIsClear()){
					move();
					putRow();
				}
			}
			if (rightIsClear()){
				if (noBeepersPresent()){
					repositionForRowToEast();
					putRow();
				} else {
					repositionForRowToEast();
					if (frontIsClear()){
						move();
						putRow();
					}
				}
			} else {
				turnAround();
				moveToWall();
			}
		}
		turnLeft();
	}
	private void putRow(){
		putBeeper();
		while (frontIsClear()){
			putOtherBeepers();
		}
		putOtherBeepers();		
	}
	
	private void putOtherBeepers(){
		if(beepersPresent()){
			if (frontIsClear()){
				move();
			}
		} else {
			if (frontIsClear()){
				move();
				putBeeper();
			}
		}		
	}
	private void repositionForRowToWest(){
		turnLeft();
		move();
		turnLeft();
	}
	private void repositionForRowToEast(){
		turnRight();
		move();
		turnRight();
	}
	private void moveToWall(){
		while (frontIsClear()){
			move();
		}
	}
}
