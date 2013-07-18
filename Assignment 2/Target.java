/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	
	public void run() {
		double centerX1 = getWidth() / 2.0 - 72/2.0;
		double centerY1 = getHeight() / 2.0 - 72/2.0;
		double centerX2 = getWidth() / 2.0 - 0.65*72/2.0;
		double centerY2 = getHeight() / 2.0 - 0.65*72/2.0;
		double centerX3 = getWidth() / 2.0 - 0.3*72/2.0;
		double centerY3 = getHeight() / 2.0 - 0.3*72/2.0;
		GOval outer = new GOval(centerX1, centerY1, 72, 72);
		GOval middle = new GOval(centerX2, centerY2, 0.65*72, 0.65*72);
		GOval inner = new GOval(centerX3, centerY3, 0.3*72, 0.3*72);
		outer.setFilled(true);
		outer.setFillColor(Color.RED);
		middle.setFilled(true);
		middle.setFillColor(Color.WHITE);
		inner.setFilled(true);
		inner.setFillColor(Color.RED);
		
		add(outer);
		add(middle);
		add(inner);
		//println(centerX+"    "+centerY);
	}
}
