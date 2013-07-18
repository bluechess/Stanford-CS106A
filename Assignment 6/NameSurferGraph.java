/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		nameList =  new ArrayList<NameSurferEntry>();
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		nameList.clear();
	}
	
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		nameList.add(entry);
	}
	
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		removeAll();
		drawGrid();
		for (int i = 0; i < nameList.size(); i++){
			drawEntry(nameList.get(i), i);
		}

	}
	


	
	private void drawGrid(){
		// add vertical lines
		GLine[] verticalLine = new GLine[NDECADES-1];
		for(int i = 0; i < NDECADES-1; i++){
			double xi = (i + 1) * getWidth() / NDECADES;
			verticalLine[i] = new GLine(xi, 0, xi, getHeight());
			add(verticalLine[i]);
		}
		// add horizontal lines
		GLine horizontalLineUp = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
		GLine horizontalLineDown = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
		add(horizontalLineUp);
		add(horizontalLineDown);
		
		// add decade labels
		GLabel[] decadeLabel= new GLabel[NDECADES]; 
		for(int i = 0; i < NDECADES; i++){
			int decade = START_DECADE + i * 10;
			double xi = 2 + i * getWidth() / NDECADES;
			decadeLabel[i] = new GLabel(Integer.toString(decade), xi, getHeight() - GRAPH_MARGIN_SIZE/4);
			add(decadeLabel[i]);
		}
	}
	
	private void drawEntry(NameSurferEntry entry, int colorIndex){
		Color color = assignColor(colorIndex);
		// set 10 labels and 10 lines
		double step = (getHeight() - 2 * GRAPH_MARGIN_SIZE) / (double) MAX_RANK;

		for(int decade = 0; decade < NDECADES - 1; decade++){
			GLabel label = setLabel(entry, step, decade);
			GLine line = setLine(entry, step, decade);
			label.setColor(color);
			line.setColor(color);
			add(label);
			add(line);
		}		
		// total 11 labels and 10 lines, so we add the last label here
		GLabel lastLabel = setLabel(entry, step, NDECADES - 1);
		lastLabel.setColor(color);
		add(lastLabel);
	}
	
	private GLabel setLabel(NameSurferEntry entry, double step, int decade){
		String str = entry.getName();
		int rank = entry.getRank(decade);
		if (rank == 0) rank = MAX_RANK + 1;
		double xi = 5 + decade * getWidth() / NDECADES;
		double yi = GRAPH_MARGIN_SIZE + rank * step;
		if (rank == (MAX_RANK + 1)){
			str += " *";
		} else {
			str += " " + rank;
		}
		GLabel label = new GLabel(str, xi, yi);
		return label;
	}
	
	private GLine setLine(NameSurferEntry entry, double step, int decade){
		int rank1 = entry.getRank(decade);
		int rank2 = entry.getRank(decade + 1);
		if (rank1 == 0) rank1 = MAX_RANK + 1;
		if (rank2 == 0) rank2 = MAX_RANK + 1;
		double x1 = decade * getWidth() / NDECADES;
		double y1 = GRAPH_MARGIN_SIZE + rank1 * step;
		double x2 = (decade + 1) * getWidth() / NDECADES;
		double y2 = GRAPH_MARGIN_SIZE + rank2 * step;
		GLine line = new GLine(x1, y1, x2, y2);
		return line;
	}
	
	private Color assignColor(int colorIndex){
		Color color;
		switch(colorIndex % 4){
			case 1: color = Color.red;
				break;
			case 2: color = Color.blue;
				break;
			case 3: color = Color.magenta;
				break;
			default: color = Color.black;
				break;
		}
		return color;
	}
	
	
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	/* ivars */
	private ArrayList<NameSurferEntry> nameList;
	
}
