package ui;
import game.GameManager;

public class DefaultPet extends Pet{

    public DefaultPet(String name, GameManager gm) {
        super(name, gm);
    }


    @Override
    public String getPetType() {
        return "default pet type";
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getPetType'");
    }

}
