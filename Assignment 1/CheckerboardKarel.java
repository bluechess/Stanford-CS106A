/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run(){		
		if (leftIsBlocked()){
			OnlyOneRow();
		} else {
		
			while (frontIsClear()){
				putOneLine();
				makeTheMove();
			}
			putOneLine();
			goDestination();
		}
	}
	
	private void putOneLine(){
		turnLeft();
		putBeeper();

		while (frontIsClear()){
			putOtherBeepers();
		}
		putOtherBeepers();
		turnAround();
		moveToWall();
		turnLeft();
	}
	
	private void OnlyOneRow(){
		putBeeper();
		while (frontIsClear()){
			putOtherBeepers();
		}
		putOtherBeepers();
		turnLeft();
	}
	
	private void makeTheMove(){
		if (beepersPresent()){
			move();
			turnLeft();
			move();
			turnRight();
			
		} else {
			move();
		}
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
	private void moveToWall(){
		while (frontIsClear()){
			move();
		}
	}
	private void goDestination(){
		turnLeft();
		moveToWall();
	}
}
