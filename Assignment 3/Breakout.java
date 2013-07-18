/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		setUpBricks();
		creatPaddle();
		while (remainTurns >0 ){
			startScreen();
			creatBall();

		}
	}
	

	
// set up bricks	
/* Set up the bricks on top of the game */
	private void setUpBricks(){
		double startX = (getWidth() - (NBRICKS_PER_ROW - 1) * BRICK_SEP - BRICK_WIDTH * NBRICKS_PER_ROW) / 2;
		double startY = BRICK_Y_OFFSET; 
		for (int i =0; i < NBRICK_ROWS; i++) {			
			for (int j = 0; j < NBRICKS_PER_ROW; j++) {
				double x = startX + j * (BRICK_WIDTH + BRICK_SEP);
				double y = startY;
				GRect rect = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
				rect.setFilled(true);
				rect.setFillColor(filledColor(i));
				add(rect);
			}
			startY = startY + BRICK_HEIGHT + BRICK_SEP;
		}
	}
	
/* Set the bricks' fill color */
	private Color filledColor(int i) {
		switch (i) {
		case 0: case 1:
			return Color.RED;
		case 2: case 3:
			return Color.ORANGE;
		case 4: case 5:
			return Color.YELLOW;
		case 6: case 7:
			return Color.GREEN;
		case 8: case 9:
			return Color.CYAN;
		default:
			return null;
		}
	}

// set up the paddle	
/* Creat the paddle */
	private void creatPaddle() {
		double paddleY = HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT; 
		double paddleX = (getWidth() - PADDLE_WIDTH) / 2 ;
		paddle = new GRect(paddleX, paddleY, PADDLE_WIDTH, PADDLE_HEIGHT);
		((GRect) paddle).setFilled(true);
		paddle = (GObject) paddle;
		paddle.setColor(Color.BLACK);
		add(paddle);
		addMouseListeners();
	}

	
/* Called on mouse press to record the coordinates of the click */
	public void mousePressed(MouseEvent e) {
		lastX = e.getX();
		lastY = e.getY();
		if (lastY > HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT){  // make sure don't pick bricks
			paddle = getElementAt(lastX, lastY);
		}
	}

	
/* Called on mouse drag to reposition the paddle */ 
	public void mouseDragged(MouseEvent e) {
		if (paddle != null) {
			boolean noMove = (paddle.getX()<0 && e.getX() <= lastX ) || (paddle.getX() + PADDLE_WIDTH > WIDTH && e.getX() >= lastX);
			if (noMove){
				paddle.move(0, 0);
			} else {
				paddle.move(e.getX() - lastX, 0);
			}
			lastX = e.getX();
		}
	}

// set up the ball
/* Creat a ball and get it to bounce off the walls */
	private void creatBall() {
		double x = WIDTH /2 - 2 * BALL_RADIUS;
		double y = HEIGHT / 2 - 2 * BALL_RADIUS;
		ball = new GOval(x, y, 2 * BALL_RADIUS, 2 * BALL_RADIUS);
		ball.setFilled(true);
		ball.setFillColor(Color.BLACK);
		add(ball);
		
		// start the ball
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) vx = -vx;
		vy = 3.0;
		
		while (true) {
			ball.move(vx, vy);
			pause(PAUSE_TIME);
			if (ball.getX() <= 0 || ball.getX() + 2 * BALL_RADIUS >= WIDTH){
				vx = -vx;
			}
			if (ball.getY() <= 0) {
				vy = -vy;
			}
			if (ball.getY() + 2 * BALL_RADIUS >= HEIGHT){ 	// reach the bottom
				remainTurns--;
				addFailureLabel();
				break;
			}
			checkCollisions();
			if (remainBricks == 0){
				addWinLabel();
				remainTurns = 0;
				break;
			}
		}
		
	}
	
/* add the failure information labels */
	private void addFailureLabel() {
		String 	str = "YOU HAVE " + remainTurns + (remainTurns == 1 ? " CHANCE" : " CHANCES") +" LEFT!";
		if (remainTurns == 0) {
			str = "YOU LOST! HAHA! ";
		}
		GLabel failureLabel = new GLabel(str);
		failureLabel.setFont(new Font("Serif", Font.BOLD, 15));
		failureLabel.setColor(Color.BLUE);
		failureLabel.setLocation((WIDTH - failureLabel.getWidth())/2, (HEIGHT - failureLabel.getHeight()) / 2);
		add(failureLabel);
		if (remainTurns > 0) {
			pause(3000);
			remove(failureLabel);
			remove(ball);
		}
		
	}
	
/* add the win label*/
	private void addWinLabel(){
		GLabel winLabel = new GLabel("YOU WON!");
		winLabel.setFont(new Font("Serif", Font.BOLD, 18));
		winLabel.setLocation((WIDTH - winLabel.getWidth()) / 2, (HEIGHT - winLabel.getHeight()) / 2);
		winLabel.setColor(Color.RED);
		add(winLabel);
	}

	// check for collision
/* checking for collisions*/
	private void checkCollisions() {
		GObject collider = getCollidingObject();
		if (collider == paddle) {
			vy = -vy;		
		} else if (collider != null){
			vy = -vy;
			remove(collider);
			remainBricks--;
		}
	}
	
/* get colliding object, check 4 corners  */
	private GObject getCollidingObject() {
		GObject colliderUpLeft = getElementAt(ball.getX(), ball.getY());
		GObject colliderUpRight = getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY());
		GObject colliderBottomLeft = getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS);
		GObject colliderBottomRight = getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS);
		if (colliderUpLeft != null){
			return colliderUpLeft;
		} else if (colliderUpRight != null) {
			return colliderUpRight;
		} else if (colliderBottomLeft != null) {
			return colliderBottomLeft;
		} else if (colliderBottomRight != null) {
			return colliderBottomRight;
		} else {
			return null;
		}
	}



/* creat the start screen */
	private void startScreen() {
		String str;
		GLabel startLabel;
		for (int i=3; i>0; i--) {
			str = "START IN  " + i + "  SECONDS";
			startLabel = new GLabel(str);
			startLabel.setFont(new Font("Serif", Font.BOLD, 18));
			startLabel.setLocation((WIDTH - startLabel.getWidth()) / 2, (HEIGHT - startLabel.getHeight()) / 2);
			startLabel.setColor(Color.BLACK);
			add(startLabel);
			pause(1000);
			remove(startLabel);
		}
	}
	
/* Private instance variables */
	private GObject paddle;         /* The object - "the paddle" being dragged */
	private GOval ball;				/* Then oval - "the ball" */
	private double lastY;			/* The last mouse Y position */
	private double lastX; 			/* The last mouse X position */
	private double vx, vy; 			/* The velocity of the ball */
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private static final int PAUSE_TIME = 20;  /* Delay time between cycles (in milliseconds) */
	private int remainTurns = NTURNS;
	private int remainBricks = NBRICKS_PER_ROW * NBRICK_ROWS;

}
