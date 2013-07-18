/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.graphics.GCompound;
import acm.graphics.GObject;
import acm.graphics.GPoint;
import acm.program.*;
import acm.util.ErrorException;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		
		initInteractors();
		database = new NameSurferDataBase(NAMES_DATA_FILE);
		graph = new NameSurferGraph();
		add(graph);
   	    
	}
	
	/** this method initialize the interactors at the bottom of the window */
	private void initInteractors(){
		
		graphButton = new JButton("Graph");
		clearButton = new JButton("Clear");
		tf = new JTextField(30);
		
		add(new JLabel("Name "), SOUTH);
		add(tf, SOUTH);
		add(graphButton, SOUTH);
		add(clearButton, SOUTH);
		
		tf.addActionListener(this);
		tf.setActionCommand("Graph");
		
		addActionListeners();	
	}

/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Clear")){
			graph.clear();
			graph.update();
		} else if(e.getActionCommand().equals("Graph")){
			String name = tf.getText();
			NameSurferEntry entry = database.findEntry(name);
			if(entry != null){
				graph.addEntry(entry);
				graph.update();
			}
		}
	}
	
	
	/* instance variables */
	private JTextField tf;
	private JButton graphButton;
	private JButton clearButton;
	private NameSurferDataBase database;
	private NameSurferGraph graph;

}
