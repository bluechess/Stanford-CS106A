/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {

	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		GLabel bottomMsg = new GLabel(msg);
		GObject oldMsg = getElementAt(getWidth() / 2, getHeight()-BOTTOM_MESSAGE_MARGIN);
		bottomMsg.setFont(MESSAGE_FONT);
		if(oldMsg != null){
			remove(oldMsg);
		}
		add(bottomMsg, (getWidth() - bottomMsg.getWidth())/ 2, getHeight()-BOTTOM_MESSAGE_MARGIN);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		if(profile != null){
			displayNewProfile(profile);
		}
	}
	
	private void displayNewProfile(FacePamphletProfile profile){
		
		
		// display name
		GLabel nameLabel = new GLabel(profile.getName());
		nameLabel.setFont(PROFILE_NAME_FONT);
		nameLabel.setColor(Color.blue);
		add(nameLabel, LEFT_MARGIN, TOP_MARGIN + nameLabel.getHeight());
		
		// display image
		// display rectangle
		GRect rect = new GRect(IMAGE_WIDTH, IMAGE_HEIGHT);
		double yImage = IMAGE_MARGIN + TOP_MARGIN + nameLabel.getHeight();
		add(rect, LEFT_MARGIN, yImage);
		// display associate image, if any; else, display label "No Image"
		if(profile.getImage() != null){
			GImage image = profile.getImage();
			image.scale(IMAGE_WIDTH / image.getWidth(), IMAGE_HEIGHT / image.getHeight());
			add(image, LEFT_MARGIN, yImage);
		} else {
			GLabel noImageLabel = new GLabel("No Image");
			noImageLabel.setFont(PROFILE_IMAGE_FONT);
			double xNoImageLabel = LEFT_MARGIN + (IMAGE_WIDTH - noImageLabel.getWidth()) / 2;
			double yNoImageLabel = yImage + (IMAGE_HEIGHT - noImageLabel.getHeight()) / 2;
			add(noImageLabel, xNoImageLabel, yNoImageLabel);
		}
		
		// display status
		GLabel statusLabel = new GLabel("No current status");
		if(!profile.getStatus().equals("")){
			String str = profile.getName() + " is " + profile.getStatus();
			statusLabel = new GLabel(str);
		}
		statusLabel.setFont(PROFILE_STATUS_FONT);
		double yStatus = STATUS_MARGIN + statusLabel.getHeight() + yImage + IMAGE_HEIGHT;
		add(statusLabel, LEFT_MARGIN, yStatus);

		
		// display friend list
		// add friend header
		GLabel friendHeader = new GLabel("Friends:");
		friendHeader.setFont(PROFILE_FRIEND_LABEL_FONT);
		double yFriendHeader = yImage;
		add(friendHeader, getWidth() / 2, yFriendHeader);
		// add friend list
		Iterator<String> it = profile.getFriends();
		double yFriendList = yFriendHeader;
		double yFriendListSpace = friendHeader.getHeight() / 2;
		while (it.hasNext()){
			GLabel friendName = new GLabel(it.next());
			friendName.setFont(PROFILE_FRIEND_FONT);
			yFriendList += yFriendListSpace + friendName.getHeight();
			add(friendName, getWidth() / 2, yFriendList);
		}

	}
	

}
