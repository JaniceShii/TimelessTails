package com.petgame.ui;
import com.petgame.game.GameManager;

/** 
 * Initialize the pet object in the beginning before the player chooses a pet
 * This class extends the Pet class
 * 
 * @version 1.0
 * @author Jessica Yang
 */
public class DefaultPet extends Pet{

    /**
     * Constructs a ChoosePet screen.
     * @param gm The GameManager instance managing the game flow.
     * @param name The name given to the default pet
     */
    public DefaultPet(String name, GameManager gm) {
        super(name, gm);
    }

    /**
     * Provides the default pet type before any pet has been selected
     * 
     * @return The default pet type
     */

    @Override
    public String getPetType() {
        return "default pet type";
    }

}
