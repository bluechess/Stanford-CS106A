/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {
	private static final double W = 150;
	private static final double H = 50;
	public void run() {
		double centerX = getWidth()/2.0;
		double centerY = getHeight()/2.0-H/2;
		
		// define all labels
		GLabel prog = new GLabel("Program");
		GLabel console = new GLabel("ConsoleProgram");
		GLabel graphics = new GLabel("GraphicsProgram");
		GLabel dialog = new GLabel("DialogProgram");

		//double wordHeight= getHeight() - prog.getAscent();
		double wordHeight = 10;
		double y1 = centerY- H + wordHeight/2; // Y of label "Program"
		double y2 = centerY + H + wordHeight/2; // Y of label "ConsoleProgram"
		double centerXD = centerX + H/2 + W;
		double centerXG = centerX - H/2 - W;
		
		// all lines
		GLine middle = new GLine(centerX, centerY-H/2, centerX, centerY+H/2);
		GLine left = new GLine(centerX, centerY - H/2, centerXG, centerY + H/2);
		GLine right = new GLine(centerX, centerY - H/2,centerXD, centerY + H/2 );
		
		// set label locations
		//GLabel console = new GLabel("ConsoleProgram");
		console.setLocation(centerX-console.getWidth()/2, y2);
		//GLabel prog = new GLabel("Program");
		prog.setLocation(centerX-prog.getWidth()/2, y1);
		//GLabel graphics = new GLabel("GraphicsProgram");
		graphics.setLocation(centerXG-graphics.getWidth()/2, y2);
		//GLabel dialog = new GLabel("DialogProgram");
		dialog.setLocation(centerXD-dialog.getWidth()/2, y2);
		
		// all boxes
		GRect boxUp = new GRect(centerX-W/2, centerY-3*H/2, W, H);
		GRect boxMid = new GRect(centerX-W/2, centerY+H/2, W, H);
		GRect boxLeft = new GRect(centerXG-W/2, centerY+H/2, W, H);
		GRect boxRight = new GRect(centerXD-W/2, centerY+H/2, W, H);

		add(middle);
		add(left);
		add(right);
		
		add(console);
		add(prog);
		add(graphics);
		add(dialog);
		
		add(boxUp);
		add(boxMid);
		add(boxLeft);
		add(boxRight);
		
	}
}

