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
import javax.swing.SwingConstants;

import com.petgame.game.GameManager;

/**
 * The Instructions class represents the screen where players can view the game instructions and objectives.
 * It provides a detailed description of the game mechanics, including the pet's health, sleep, and other stats.
 * This class also handles user interaction with buttons to navigate through the instructions.
 * 
 * @version 1.0
 * @author Advaith Thakur
 */
public class Instructions4 extends AbstractScreen{
    // create the main game interface and game application window
    GameManager gm;
    Pet pet;
    // used to display the background image (city_backgroumd.jpg)
    public JPanel bgPanel;
    private JLabel instructionsLabel;
    private JLabel objectiveLabel;
    private JLabel lineLabel;
    private JButton continueButton;
    private JButton prevButton;
    private JButton backButton;

    /**
     * Constructs an Instructions screen with a reference to the GameManager.
     * 
     * @param gm The GameManager instance managing the game flow.
     */
    public Instructions4(GameManager gm) {
        this.gm = gm;
        createUIElements();
    }

    /**
     * Creates the background for the instructions screen. The background is a custom design based on a specific pet type.
     * 
     * @param petType The type of pet that determines the background design.
     */
    @Override
    public void createBackground(String petType) {
        super.createBackground("basic");  
        uiCircle.setBounds(300, 20, 1000, 475); 
       bgLabel.remove(uiCircle);
       uiCircle = new JPanel() {
        @Override
        protected void paintComponent(Graphics g){
            // use paintComponent to make sure the background shines through on other elements 
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // create the navy blue colour circle (with transparency)
            Color navyBlue = new Color(28, 57, 102, 230); 
            g2d.setColor(navyBlue);

            // create the navy oval
            int ovalWidth = 500;
            int ovalHeight = 500;
            int x = 200;             
            int y = 40;            
            g2d.fillOval(x, y, ovalWidth, ovalHeight);
            g2d.dispose();
        }

       };
       
       uiCircle.setBounds(50, 0, 1000, 600);
        uiCircle.setOpaque(false);
        bgLabel.add(uiCircle);
        bgLabel.revalidate();
        bgLabel.repaint();

    }

    /**
     * Creates the UI elements for the instructions screen, including buttons, labels, and their respective actions.
     */
    @Override
    public void createUIElements() {
        uiCircle.setLayout(null);

        ImageIcon backIcon = new ImageIcon(getClass().getClassLoader().getResource("com/petgame/assets/back_arrow.png"));
        backButton = new JButton(backIcon);

        backButton.setBounds(330, 95, 24, 24);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> gm.showMainMenu());
        uiCircle.add(backButton);

        instructionsLabel = new JLabel("Instructions");
        instructionsLabel.setBounds(350, 90, 200, 30);
        instructionsLabel.setForeground(Color.WHITE);
        instructionsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        instructionsLabel.setFont(new Font("Inter", Font.BOLD, 25));
        uiCircle.add(instructionsLabel);

        objectiveLabel = new JLabel("Tutorial");
        objectiveLabel.setBounds(345, 150, 200, 30);
        objectiveLabel.setForeground(Color.WHITE);
        objectiveLabel.setHorizontalAlignment(SwingConstants.CENTER);
        objectiveLabel.setFont(new Font("Inter", Font.BOLD, 20));
        uiCircle.add(objectiveLabel);

        lineLabel = new JLabel("--------------------");
        lineLabel.setBounds(300, 170, 300, 30);
        lineLabel.setForeground(Color.WHITE);
        lineLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lineLabel.setFont(new Font("Inter", Font.BOLD, 20));
        uiCircle.add(lineLabel);
        
        JLabel descLabel = new JLabel("<html><div style='text-align: center;'>" + "The settings includes buttons to navigate to parental controls screen, instructions file, save game action, and return to main menu. <br>" +
    " The parental controls screen is password protected. If its the first time that a parent is accessing the parental controls, they will have to create a password. " +
    "Parents have ability to set certain times of the day to allow the user to play, which can be enabled or disabled. " +
    "Parents can view statistics of total play time and average play time per gamming session. This can be reset by the parent if needed. " +
    "Parents can select a save file to revive a pet to normal state with maximum statistics. <br><br>" + "Instructions button will display a popup of a text file with description of the game. <br>" +
    "Save game button allows player to save the game.<br>" +
    "Main menu button returns to the main menu screen.<br>" + "</div></html>");
        descLabel.setBounds(250, 95, 400, 400);
        descLabel.setForeground(Color.WHITE);
        descLabel.setFont(new Font("Inter", Font.PLAIN, 11));
        uiCircle.add(descLabel);

        Color navyBlue = new Color(28, 57, 102, 230);

        continueButton = new JButton("Next");
        continueButton.setBounds(450, 420, 100, 35);
        continueButton.setBackground(Color.WHITE);
        continueButton.setForeground(navyBlue);
        continueButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        continueButton.setFocusPainted(false);
        continueButton.setFont(new Font("Inter", Font.BOLD, 14));
        continueButton.addActionListener(e -> gm.showInstructions5());
        uiCircle.add(continueButton);

        prevButton = new JButton("Previous");
        prevButton.setBounds(340, 420, 100, 35);
        prevButton.setBackground(Color.WHITE);
        prevButton.setForeground(navyBlue);
        prevButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        prevButton.setFocusPainted(false);
        prevButton.setFont(new Font("Inter", Font.BOLD, 14));
        prevButton.addActionListener(e -> gm.showInstructions3());
        uiCircle.add(prevButton);

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
        
        closePanel.setBounds(870, 20, 60, 60);
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
        closeButton.setBounds(870, 20, 60, 60);
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



    };
}
