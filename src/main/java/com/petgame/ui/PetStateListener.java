package com.petgame.ui;
/**
 * The interface provides a mechanism for tracking changes in the state of a pet. 
 * Classes implementing this interface will be notified when the pet's state changes, 
 * allowing for updates to GIFs and portraits.
 * 
 * <p>
 * This is useful for maintaining consistent state management and ensuring 
 * the user interface reflects the current state of the pet.
 * </p>
 * 
 * @version 1.0
 * @author Janice Shi
 */
public interface PetStateListener {
    void onStateChanged(String gifFileName, String portraitFileName);
}