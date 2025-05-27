package ui;

// create interface to always keep track of the state a pet is in
public interface PetStateListener {
    void onStateChanged(String gifFileName, String portraitFileName);
}