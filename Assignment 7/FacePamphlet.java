/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		initInteractors();
		database = new FacePamphletDatabase();
		canvas = new FacePamphletCanvas();
		add(canvas);

    }
	
	/* initialize all labels, buttons and text fields*/
	private void initInteractors(){
		// add west side interactors
		westStatus = new JButton("Change Status");
		westPicture = new JButton("Change Picture");
		westAdd = new JButton("Add Friend");
		westTF1 = new JTextField(TEXT_FIELD_SIZE);
		westTF2 = new JTextField(TEXT_FIELD_SIZE);
		westTF3 = new JTextField(TEXT_FIELD_SIZE);
		
		westTF1.setActionCommand("Change Status");
		westTF2.setActionCommand("Change Picture");
		westTF3.setActionCommand("Add Friend");
		
		add(westTF1, WEST);
		add(westStatus, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(westTF2, WEST);
		add(westPicture, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(westTF3, WEST);
		add(westAdd, WEST);
		
		// add north side interactors
		northAdd = new JButton("Add");
		northDelete = new JButton("Delete");
		northLookup = new JButton("Lookup");
		northTF = new JTextField(TEXT_FIELD_SIZE);

		add(new JLabel("                                 Name "), NORTH);
		add(northTF, NORTH);
		add(northAdd, NORTH);
		add(northDelete, NORTH);
		add(northLookup, NORTH);
		
		// add action listeners
		addActionListeners();	
		westTF1.addActionListener(this);
		westTF2.addActionListener(this);
		westTF3.addActionListener(this);

	}
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Change Status")){
			if(!westTF1.getText().equals(""))
				changeStatus();
		} else if(e.getActionCommand().equals("Change Picture")){
			if(!westTF2.getText().equals("")){
				changePicture();
			}
		} else if(e.getActionCommand().equals("Add Friend")){
			if(!westTF3.getText().equals("")){
				addFriend();
			}
		} else if(e.getActionCommand().equals("Add")){
			addProfile();
		} else if(e.getActionCommand().equals("Delete")){
			deleteProfile();
		} else if(e.getActionCommand().equals("Lookup")){
			lookupProfile();
		}
		
		// update canvas
		if(currentProfile != null) {
			canvas.displayProfile(currentProfile);
			canvas.showMessage(msg);
		} else {
			canvas.showMessage(msg);
		}
		


    }
    
    private void changeStatus(){
    	if(currentProfile != null){
    		String name = currentProfile.getName();
    		String status = westTF1.getText();
    		database.getProfile(name).setStatus(status);
    		msg = "Status updated to " + status;
    	} else {
    		msg = "Please select a profile to change status";
    	}
    }
    
    private void changePicture(){
    	if(currentProfile != null){
    		String name = currentProfile.getName();
    		GImage image = getImageFromFilename(westTF2.getText());
    		if(image != null){  						// check if a valid GImage
    			database.getProfile(name).setImage(image);
    			msg = "Picture updated";
    		}
    	} else {
    		msg = "Please select a profile to change picture";
    	}
    }
    
    /* get image from a given filename
     * if there is no valid GImage, the variable still have the value null
     * */
    private GImage getImageFromFilename(String filename){
    	GImage image = null;
    	try {
    		image = new GImage(filename);
    	} catch (ErrorException ex) {
    		msg = "Unable to open image file " + filename;
    	}
    	return image;
    }
    
    private void addFriend(){
    	if(currentProfile != null){                   // check if there is a currentProfile (true if there is)
    		String name = currentProfile.getName();
    		String friend = westTF3.getText();
    		if(database.containsProfile(friend)){     // check if "friend" is a valid profile (true is valid)
    			boolean p = database.getProfile(name).addFriend(friend);   // add "friend" as "name"'s friend 
    			if(p){                                // check if "name" and "friend" are friends now (true if not friend now)
    				database.getProfile(friend).addFriend(name);  // add "name" as "friend"'s friend (reciprocal)
    				msg = friend + " added as a friend";
    			} else {
    				msg = name + " already has  " + friend + " as a friend";
    			}
    		} else {
    			msg = friend + " does not exist";
    		}
    	} else {
    		msg = "Please select a profile to add friend";
    	}
    }
    
    private void addProfile(){
    	String name = northTF.getText();
		if(!database.containsProfile(name)){
			currentProfile = new FacePamphletProfile(name);
			database.addProfile(currentProfile);
			msg = "New profile created";
		} else {
			currentProfile = database.getProfile(name);
			msg = "A profile with the name " + name + " already exists";
		}
    }
    
    private void deleteProfile(){
    	String name = northTF.getText();
		currentProfile = null;
		if(database.containsProfile(name)){
			database.deleteProfile(name);
			msg = "Profile of " + name + " deleted";
		} else {
			msg = "A profile with name " + name + " does not exist";
		}
    }
    
    private void lookupProfile(){
    	String name = northTF.getText();
		if(database.containsProfile(name)){
			currentProfile = database.getProfile(name);
			msg = "Displaying " + name;
		} else {
			currentProfile = null;
			msg = "A profile with the name " + name + "does not exist";
		}
    }
	
	/* instance variables */
	private JButton westStatus;
	private JButton westPicture;
	private JButton westAdd;
	private JTextField westTF1;
	private JTextField westTF2;
	private JTextField westTF3;
	private JButton northAdd;
	private JButton northDelete;
	private JButton northLookup;
	private JTextField northTF;
	private FacePamphletDatabase database;
	private FacePamphletProfile currentProfile;
	private FacePamphletCanvas canvas;
	private String msg = "";

}
