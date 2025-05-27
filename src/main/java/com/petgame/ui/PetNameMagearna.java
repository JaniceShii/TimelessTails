package com.petgame.ui;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.petgame.game.GameManager;

/**
 * The class shows a screen where the user can name their Magearna pet.
 * This screen allows the user to input a name for the pet, which is then saved in the GameManager instance.
 * 
 * @version 1.0
 * @author Advaith Thakur
 */
public class PetNameMagearna extends AbstractScreen{
    // create the main game interface and game application window
    GameManager gm;
    Pet pet;
    // used to display the background image (city_backgroumd.jpg)
    public JPanel bgPanel;
    private JButton continueButton;
    private JButton backButton;

    /**
     * Constructs a PetNameFurfrou screen with the specified game manager.
     * Initializes UI elements and retrieves the current pet from the game manager.
     *
     * @param gm The game manager responsible for managing the game state.
     */
    public PetNameMagearna(GameManager gm) {
        this.gm = gm;
        createUIElements();
        pet = gm.getCurrentPet();           
    }

    /**
     * Creates the background for the screen and adds the UI circle panel.
     *
     * @param petType The type of pet being named. For this screen, it is set to "Magearna".
     */
    @Override
    public void createBackground(String petType) {
       super.createBackground("Magearna");   
       uiCircle.setBounds(300, 20, 1000, 475); 
       bgLabel.add(uiCircle);
    }

    /**
     * Creates all UI elements for the screen, including buttons, labels, text fields,
     * and handling user input for naming the pet.
     */
    @Override
    public void createUIElements() {

        //Pet Image
        JPanel magearnaPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            g2d.setColor(Color.WHITE);
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            
            g2d.dispose();
            }
        };

        magearnaPanel.setBounds(150, 200, 90, 90);
        magearnaPanel.setOpaque(false);
        magearnaPanel.setLayout(new BorderLayout());
        uiCircle.add(magearnaPanel);

        JLabel magearnaLabel = new JLabel();
        ImageIcon magearnaUnscaled = new ImageIcon(getClass().getClassLoader().getResource("com/petgame/assets/magearna.png"));
        Image magearnaScaledImage = magearnaUnscaled.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon magearnaImage = new ImageIcon(magearnaScaledImage);
        magearnaLabel.setIcon(magearnaImage);
        magearnaPanel.add(magearnaLabel);

        JLabel headingLabel = new JLabel("Name Your Pokemon!");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headingLabel.setForeground(Color.WHITE);
        headingLabel.setBounds(115, 145, 500, 50);
        uiCircle.add(headingLabel);
        
        //add textbox for user to enter pet name
        JTextField textField = new JTextField();
        textField.setBounds(122, 300, 150, 45);
        uiCircle.add(textField);
        //needs to save the NAME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Color navyBlue = new Color(28, 57, 102, 230);

        continueButton = new JButton("Continue");
        continueButton.setBounds(148, 370, 100, 45);
        continueButton.setBackground(Color.WHITE);
        continueButton.setForeground(navyBlue);
        continueButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        continueButton.setFocusPainted(false);
        continueButton.setFont(new Font("Inter", Font.BOLD, 14));
        uiCircle.add(continueButton);
        continueButton.addActionListener(e -> {
            String petName = textField.getText().trim();
            if (petName.isEmpty()) {
                JOptionPane.showMessageDialog(magearnaPanel, "You must enter a name for your pet!");
            } else if (petName.length() >= 15) {
                JOptionPane.showMessageDialog(magearnaPanel, "You must enter a name that has 15 or fewer characters!");
            } else if (petName.contains(" ")) {
                JOptionPane.showMessageDialog(magearnaPanel, "Spaces are not allowed in the pet name!");
            } else if (!petName.matches("[a-zA-Z0-9]*")) {
                JOptionPane.showMessageDialog(magearnaPanel, "Special characters are not allowed in the pet name!");
            } else {

                // !!!!!!!! get the gameManagerPet and sets its name to the one user inputted
                pet = new Magearna(petName, gm);
                gm.setCurrentPet(pet);
                textField.setText("");
                String name = gm.getCurrentPet().getName();
        
                gm.showMainGamePlayMagearna(); // <-- THIS is where you should now construct GamePlayUI
            }
        });
        

        ImageIcon backIcon = new ImageIcon(getClass().getClassLoader().getResource("com/petgame/assets/back_arrow.png"));
        backButton = new JButton(backIcon);
        backButton.setBounds(80, 158, 24, 24);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> gm.previous());
        uiCircle.add(backButton);


        JPanel closePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
                
                g2d.dispose();
            }
        };
        
        closePanel.setBounds(910, 20, 60, 60);
        closePanel.setOpaque(false);
        closePanel.setLayout(new BorderLayout());
        uiCircle.add(closePanel);

        JLabel closeBgLabel = new JLabel();
        ImageIcon closeUnscaled = new ImageIcon(getClass().getClassLoader().getResource("com/petgame/assets/closeIcon.png"));
        Image closeScaledImage = closeUnscaled.getImage().getScaledInstance(60, 55, Image.SCALE_SMOOTH);
        ImageIcon closeBackground = new ImageIcon(closeScaledImage);
        closeBgLabel.setIcon(closeBackground);
        closePanel.add(closeBgLabel);

        JButton closeButton = new JButton();
        closeButton.setBounds(910, 20, 60, 60);
        closeButton.setFocusPainted(false); 
        closeButton.setContentAreaFilled(false);
        closeButton.setBorder(null);
        closeButton.setOpaque(false); 
        uiCircle.add(closeButton);
        closeButton.addActionListener(e -> {
            int result = JOptionPane.showOptionDialog(
            null,                          
            "Are you sure you want to close? If you close, unsaved progress will be lost.", 
            "Confirm Close",                 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE,
            null, 
            new Object[] {"Yes", "No"},
            "Yes" 
        );
    
        if (result == 0) { 
            gm.closeWindow();
        }
            
        });
        
    }
}
