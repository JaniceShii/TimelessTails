package com.petgame.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * AbstractScreen serves as a base class for creating different game screens. 
 * It provides methods for setting up the main field, background, and UI elements.
 * This class extends JPanel for ease of integration with Swing components.
 * 
 * @version 1.0
 * @author Janice Shi
 */

public abstract class AbstractScreen extends JPanel{
    
    protected JPanel bgPanel;
    protected JLabel bgLabel;
    protected JPanel uiCircle;

    /**
     * Constructs an AbstractScreen object, initializes layout and creates the main field and background.
     */
    public AbstractScreen(){
        setLayout(null);
        createMainField();
        createBackground("basic");
    }

    /**
     * Initializes the main field of the screen by setting preferred size and background color.
     */
    public void createMainField(){
        setPreferredSize(new Dimension(1000,600));
        setBackground(Color.black);
    }

    /**
     * Creates and sets up the background panel and label with a specified background image
     * based on the given pet type. It also adds a circular UI element overlay.
     *
     * @param petType The type of pet to determine the appropriate background image.
     *                Accepts "Tyrunt", "Magearna", "Furfrou", or defaults to "basic".
     */
    public void createBackground(String petType) {
        setLayout(null);
        bgPanel = new JPanel();
        bgPanel.setBounds(0, 0, 1000, 600);
        bgPanel.setBackground(null);
        bgPanel.setLayout(null);
        this.add(bgPanel);

        bgLabel = new JLabel();
        bgLabel.setBounds(0, 0, 1000, 600);
        ImageIcon bgUnscaled;
        if (petType.equals("Tyrunt")){
            bgUnscaled = new ImageIcon(getClass().getClassLoader().getResource("com/petgame/assets/past_background.jpeg"));
        } else if (petType.equals("Magearna")){
            bgUnscaled = new ImageIcon(getClass().getClassLoader().getResource("com/petgame/assets/future_background.jpg"));
        } else if (petType.equals("Furfrou")) {
            bgUnscaled = new ImageIcon(getClass().getClassLoader().getResource("com/petgame/assets/city_background.jpg"));
        } else {
            bgUnscaled = new ImageIcon(getClass().getClassLoader().getResource("com/petgame/assets/basic_background.jpg"));
        }
        Image scaledImage = bgUnscaled.getImage().getScaledInstance(1000, 600, Image.SCALE_SMOOTH);
        bgLabel.setIcon(new ImageIcon(scaledImage));
        bgPanel.add(bgLabel);

        uiCircle = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Color navyBlue = new Color(28, 57, 102, 230);
                g2d.setColor(navyBlue);
                int circleSize = 400;
                g2d.fillOval(0, getHeight() - circleSize, circleSize, circleSize);

                g2d.dispose();
            }
        };

        uiCircle.setBounds(0, 0, 1000, 600);
        uiCircle.setOpaque(false);
        uiCircle.setLayout(null);
        bgLabel.add(uiCircle);
    }
    
    public abstract void createUIElements();


}


