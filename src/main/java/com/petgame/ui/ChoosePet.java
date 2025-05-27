package com.petgame.ui;

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
import javax.swing.JPanel;

import com.petgame.game.GameManager;

/**
 * The ChoosePet class represents a screen where the player can choose between different pets to start their game.
 * <br><br>
 * It has buttons for selecting each pet and a continue button to proceed after selection.
 * This class extends the AbstractScreen class.
 * 
 * @version 1.0
 * @author Advaith Thakur
 */
public class ChoosePet extends AbstractScreen{
     // create the main game interface and game application window
     GameManager gm;
     
     // ui components
     private JButton tyruntButton;
     private JButton furfrouButton;
     private JButton magearnaButton;
     private JButton continueButton;
     private JButton backButton;
     private JPanel closePanel = new JPanel();

     //initial states for the pet selection buttons
     private boolean isTyruntSelected = false;
     private boolean isFurfrouSelected = false;
     private boolean isMagearnaSelected = false;
     private Color navyBlue = new Color(28, 57, 102);

    /**
     * Constructs a ChoosePet screen.
     * @param gm The GameManager instance managing the game flow.
     */
    public ChoosePet(GameManager gm) {
         this.gm = gm;
         createUIElements();
     }

     /**
     * Creates the background for the ChoosePet screen.
     * @param petType The type of pet selected by the player.
     */
     @Override
     public void createBackground(String petType) {
        super.createBackground("basic"); 
        uiCircle.setBounds(300, 20, 1000, 475); 
        bgLabel.add(uiCircle);
     }

     /**
     * Initializes and sets up the UI components for pet selection.
     * Adds action listeners to buttons and prepares visual components.
     */
     @Override
     public void createUIElements() {
        // images of each pet option displayed
        JPanel tyruntPanel = new JPanel() {
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
        // set the size and layout of the panel 
        tyruntPanel.setBounds(40, 200, 80, 80);
        tyruntPanel.setOpaque(false);
        tyruntPanel.setLayout(new BorderLayout());
        uiCircle.add(tyruntPanel);
        // load the image of the pet 
        JLabel tyruntLabel = new JLabel();
        ImageIcon tyruntUnscaled = new ImageIcon(getClass().getClassLoader().getResource("com/petgame/assets/tyrunt.png"));
        Image tyruntScaledImage = tyruntUnscaled.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon tyruntImage = new ImageIcon(tyruntScaledImage);
        tyruntLabel.setIcon(tyruntImage);
        tyruntPanel.add(tyruntLabel);

        /** create a panel for the second pet option */
        JPanel furfrouPanel = new JPanel() {
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

        // set the size and layout of the panel
        furfrouPanel.setBounds(160, 200, 80, 80);
        furfrouPanel.setOpaque(false);
        furfrouPanel.setLayout(new BorderLayout());
        uiCircle.add(furfrouPanel);

        // load the image of the pet
        JLabel furfrouLabel = new JLabel();
        ImageIcon furfrouUnscaled = new ImageIcon(getClass().getClassLoader().getResource("com/petgame/assets/furfrou.png"));
        Image furfrouScaledImage = furfrouUnscaled.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon furfrouImage = new ImageIcon(furfrouScaledImage);
        furfrouLabel.setIcon(furfrouImage);
        furfrouPanel.add(furfrouLabel);

        /** create a panel for the third pet option */
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

        /** create a panel for the third pet option */
        magearnaPanel.setBounds(280, 200, 80, 80);
        magearnaPanel.setOpaque(false);
        magearnaPanel.setLayout(new BorderLayout());
        uiCircle.add(magearnaPanel);

        /** create a panel for the third pet option */
        JLabel magearnaLabel = new JLabel();
        ImageIcon magearnaUnscaled = new ImageIcon(getClass().getClassLoader().getResource("com/petgame/assets/magearna.png"));
        Image magearnaScaledImage = magearnaUnscaled.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon magearnaImage = new ImageIcon(magearnaScaledImage);
        magearnaLabel.setIcon(magearnaImage);
        magearnaPanel.add(magearnaLabel);

        Color navyBlue = new Color(28, 57, 102);

        // create buttons for each pet option 
        tyruntButton = new JButton("Tyrunt");
        tyruntButton.setBounds(35, 290, 90, 45);
        tyruntButton.setFocusPainted(false);
        tyruntButton.setFont(new Font("Inter", Font.BOLD, 14));
        tyruntButton.setBackground(navyBlue);
        tyruntButton.setForeground(Color.WHITE);
        tyruntButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        tyruntButton.setSelected(false);
        uiCircle.add(tyruntButton);

        tyruntButton.addActionListener(e -> {
            deselectAllButtons();
            isTyruntSelected = !isTyruntSelected;  // Toggle state
            tyruntButton.setBackground(isTyruntSelected ? Color.GREEN : Color.LIGHT_GRAY);
        });


        furfrouButton = new JButton("Furfrou");
        furfrouButton.setBounds(155, 290, 90, 45);
        furfrouButton.setFocusPainted(false);
        furfrouButton.setFont(new Font("Inter", Font.BOLD, 14));
        furfrouButton.setBackground(navyBlue);
        furfrouButton.setForeground(Color.WHITE);
        furfrouButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        furfrouButton.setSelected(false);
        uiCircle.add(furfrouButton);

        furfrouButton.addActionListener(e -> {
            deselectAllButtons();
            isFurfrouSelected = !isFurfrouSelected;  // Toggle state
            furfrouButton.setBackground(isFurfrouSelected ? Color.GREEN : Color.LIGHT_GRAY);
        });

        magearnaButton = new JButton("Magearna");
        magearnaButton.setBounds(275, 290, 90, 45);
        magearnaButton.setBackground(navyBlue);
        magearnaButton.setForeground(Color.WHITE);
        magearnaButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        magearnaButton.setFocusPainted(false);
        magearnaButton.setFont(new Font("Inter", Font.BOLD, 14));
        magearnaButton.setSelected(false);
        uiCircle.add(magearnaButton);

        magearnaButton.addActionListener(e -> {
            deselectAllButtons();
            isMagearnaSelected = !isMagearnaSelected;  // Toggle state
            magearnaButton.setBackground(isMagearnaSelected ? Color.GREEN : Color.LIGHT_GRAY);
        });
        
        // create a continue button to proceed with the selected pet
        continueButton = new JButton("Continue");
        continueButton.setBounds(150, 370, 100, 45);
        continueButton.setBackground(Color.WHITE);
        continueButton.setForeground(navyBlue);
        continueButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        continueButton.setFocusPainted(false);
        continueButton.setFont(new Font("Inter", Font.BOLD, 14));
        uiCircle.add(continueButton);

        continueButton.addActionListener(e -> {
            if (isFurfrouSelected) {
                gm.showPetName();
                furfrouButton.setBackground(isFurfrouSelected ? navyBlue : Color.LIGHT_GRAY);
            } else if (isTyruntSelected) {
                gm.showPetNameTyrunt();
                tyruntButton.setBackground(isFurfrouSelected ? navyBlue : Color.LIGHT_GRAY);
            } else if (isMagearnaSelected) {
                gm.showPetNameMagearna();
                magearnaButton.setBackground(isFurfrouSelected ? navyBlue : Color.LIGHT_GRAY);
            }
        });

        // create a heading for the page
        JLabel headingLabel = new JLabel("Choose Your Pokemon!");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headingLabel.setForeground(Color.WHITE);
        headingLabel.setBounds(110, 145, 500, 50);
        uiCircle.add(headingLabel);

        ImageIcon backIcon = new ImageIcon(getClass().getClassLoader().getResource("com/petgame/assets/back_arrow.png"));
        backButton = new JButton(backIcon);
        backButton.setBounds(80, 158, 24, 24);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> gm.showMainMenu());
        uiCircle.add(backButton);


     }

     
    /**
     * Deselects all pet selection buttons by resetting their states and colors.
     */
     private void deselectAllButtons() {
        tyruntButton.setBackground(navyBlue);
        furfrouButton.setBackground(navyBlue);
        magearnaButton.setBackground(navyBlue);

        isTyruntSelected = false;
        isFurfrouSelected = false;
        isMagearnaSelected = false;
    }

}
