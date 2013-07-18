/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		centerX = getWidth()/2;
		topY = (getHeight() - 1.2*SCAFFOLD_HEIGHT) /2;
		GLine beam = new GLine(centerX, topY, centerX-BEAM_LENGTH, topY);
		GLine rope = new GLine(centerX, topY, centerX, topY+ROPE_LENGTH);
		GLine scaffold = new GLine(centerX-BEAM_LENGTH, topY, centerX-BEAM_LENGTH, topY+SCAFFOLD_HEIGHT);
		add(beam);
		add(rope);
		add(scaffold);
		/* define hangman part*/
		double neckTopY = topY+ROPE_LENGTH+2*HEAD_RADIUS;
		head = new GOval(centerX-HEAD_RADIUS, topY+ROPE_LENGTH, 2*HEAD_RADIUS, 2*HEAD_RADIUS);
		body = new GLine(centerX, neckTopY, centerX, neckTopY+BODY_LENGTH);
		leftArm1 = new GLine(centerX, neckTopY+ARM_OFFSET_FROM_HEAD, centerX-UPPER_ARM_LENGTH, neckTopY+ARM_OFFSET_FROM_HEAD);
		leftArm2 = new GLine(centerX-UPPER_ARM_LENGTH, neckTopY+ARM_OFFSET_FROM_HEAD, centerX-UPPER_ARM_LENGTH, neckTopY+ARM_OFFSET_FROM_HEAD+LOWER_ARM_LENGTH);
		rightArm1 = new GLine(centerX, neckTopY+ARM_OFFSET_FROM_HEAD, centerX+UPPER_ARM_LENGTH, neckTopY+ARM_OFFSET_FROM_HEAD);
		rightArm2 = new GLine(centerX+UPPER_ARM_LENGTH, neckTopY+ARM_OFFSET_FROM_HEAD, centerX+UPPER_ARM_LENGTH, neckTopY+ARM_OFFSET_FROM_HEAD+LOWER_ARM_LENGTH);
		
		double hipY = neckTopY+BODY_LENGTH;
		leftLeg1 = new GLine(centerX, hipY, centerX - HIP_WIDTH/2, hipY);
		rightLeg1 = new GLine(centerX, hipY, centerX + HIP_WIDTH/2, hipY);
		leftLeg2 = new GLine(centerX - HIP_WIDTH/2, hipY, centerX - HIP_WIDTH/2, hipY + LEG_LENGTH);
		rightLeg2 = new GLine(centerX + HIP_WIDTH/2, hipY, centerX + HIP_WIDTH/2, hipY + LEG_LENGTH);
		leftFoot = new GLine(centerX - HIP_WIDTH/2, hipY + LEG_LENGTH, centerX - HIP_WIDTH/2 - FOOT_LENGTH, hipY + LEG_LENGTH);
		rightFoot = new GLine(centerX + HIP_WIDTH/2, hipY + LEG_LENGTH, centerX + HIP_WIDTH/2 + FOOT_LENGTH, hipY + LEG_LENGTH);

	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		GObject oldDisplay = getElementAt(centerX-BEAM_LENGTH, topY+1.1*SCAFFOLD_HEIGHT);
		if (oldDisplay != null){
			remove(oldDisplay);
		}
		GLabel display = new GLabel(word, centerX-BEAM_LENGTH, topY+1.1*SCAFFOLD_HEIGHT);
		add(display);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		/* add incorrect letter notes*/
		strLetter += letter;
		GObject oldDisplay = getElementAt(centerX-BEAM_LENGTH, topY+1.15*SCAFFOLD_HEIGHT);
		if (oldDisplay != null){
			remove(oldDisplay);
		}
		GLabel incorrectGuess = new GLabel(strLetter, centerX-BEAM_LENGTH, topY + 1.15*SCAFFOLD_HEIGHT);
		add(incorrectGuess);

		/* change count number*/
		count++;

		/* add hangman*/
		addHangman();
		
	}
	
	private void addHangman(){

		switch(count){
		case 1:
			add(head);
			break;
		
		case 2:
			add(body);
			break;
		case 3:
			add(leftArm1);
			add(leftArm2);
			break;
		case 4:
			add(rightArm1);
			add(rightArm2);
			break;
		case 5:
			add(leftLeg1);
			add(leftLeg2);
			break;
		case 6:
			add(rightLeg1);
			add(rightLeg2);
			break;
		case 7:
			add(leftFoot);
			break;
		case 8:
			add(rightFoot);
			break; 
		default: break;
		}
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	
	private double centerX;
	private double topY;
	private int count = 0;
	private String strLetter = "";
	private GOval head;
	private GLine body;
	private GLine leftArm1;
	private GLine leftArm2;
	private GLine rightArm1;
	private GLine rightArm2;
	private GLine leftLeg1;
	private GLine leftLeg2;
	private GLine rightLeg1;
	private GLine rightLeg2;
	private GLine leftFoot;
	private GLine rightFoot;
	


}
