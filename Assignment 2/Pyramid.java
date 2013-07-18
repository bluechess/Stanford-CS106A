/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {
		int startX = getWidth() / 2 - BRICKS_IN_BASE * BRICK_WIDTH /2 ;
		int startY = getHeight() - BRICK_HEIGHT;
		int nOfBricks = BRICKS_IN_BASE;
		while ((startX + BRICK_WIDTH / 2) <= getWidth() / 2){			
			for (int j = 0; j < nOfBricks; j++) {
				int x = startX + j * BRICK_WIDTH;
				int y = startY;
				GRect rect = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
				add(rect);
			}
			startX = startX + BRICK_WIDTH / 2; 
			startY = startY - BRICK_HEIGHT;
			nOfBricks = nOfBricks - 1;
		}
		
		
	}
}

