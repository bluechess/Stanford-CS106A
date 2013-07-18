/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run(){
		while(frontIsClear()){
			upsend();
			goback();
			move4Steps();
		}
		upsend();
		goback();
		
	}
	private void upsend(){
		turnLeft();
		moveToWall();
		turnRight();
	}

	private void move4Steps(){
		move();
		move();
		move();
		move();
	}
	private void goback(){
		turnRight();
		while(frontIsClear()){
			move();
		}
		turnLeft();
	}

	private void placeStone(){

		if(noBeepersPresent()){
			putBeeper();
		}
	}
	private void moveToWall() {
		while(frontIsClear()){
			placeStone();
			move();
		}
		placeStone();
	}

}
