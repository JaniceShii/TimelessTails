package com.petgame.ui;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * The MainMenu class represents the main menu of the Timeless Tails minigame.
 * It displays a background image, a title, and a "Start Game" button, which when clicked, 
 * launches the game and closes the main menu window.
 * 
 * This class extends JDialog and uses a custom JPanel (BackgroundPanel) to paint the background and title.
 * 
 * @version 1.0
 * @author Meridith Shang
 */

public class MainMenu extends JDialog {
    private Image backgroundImage;
    private MeteorAvoidGUI game;

    /**
     * Constructs the MainMenu dialog with the specified parent window and pet type.
     * Sets up the background image, title, and "Start Game" button with custom styles and behavior.
     * 
     * @param parent The parent JFrame of the dialog, or null if it is a standalone dialog.
     * @param petType The type of pet selected by the player, passed to the game window upon launch.
     */
    public MainMenu(JFrame parent, String petType) {
        // Set up the main menu window
        super(parent, "Dodge the Obstacles", true); 
        setSize(600, 400);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(parent); 

        // Load background image
        backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("com/petgame/assets/timebackground.jpg")).getImage(); 
        JPanel contentPanel = new BackgroundPanel();
        contentPanel.setLayout(null); 
        setContentPane(contentPanel);

        // Create the "Start Game" button with navy blue color and white outline
        JButton startButton = new JButton("Start Game");
        startButton.setBounds(200, 200, 175, 60); 
        startButton.setBackground(new Color(28, 57, 102, 255));
        startButton.setForeground(Color.WHITE); 
        startButton.setFocusPainted(false); 
        startButton.setFont(new Font("Inter", Font.PLAIN, 18));

        // Add white border to the button
        startButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        // Add hover effect to change background color when mouse enters
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startButton.setBackground(Color.WHITE); 
                startButton.setForeground(new Color(28, 57, 102, 230)); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startButton.setBackground(new Color(28, 57, 102, 230)); 
                startButton.setForeground(Color.WHITE); 
            }
        });

        // Action for button click
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // When clicked, create and show the game window
                game = new MeteorAvoidGUI(petType);
                game.setVisible(true);

                // Close the main menu (dialog)
                setVisible(false);
                dispose();
            }
        });

        contentPanel.add(startButton);
    }

    /**
     * A custom JPanel that handles drawing the background image and the game title.
     */
    private class BackgroundPanel extends JPanel {
        /**
         * Paints the background image and the title text with an outlined effect.
         * 
         * @param g The Graphics object used for drawing.
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draw the entire background image
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

            // Draw the title with white outline
            String title = "Timeless Tails";
            Font font = new Font("Inter", Font.BOLD, 50);
            g.setFont(font);
            FontMetrics metrics = g.getFontMetrics();
            int x = getWidth() / 2 - metrics.stringWidth(title) / 2;
            int y = 150; // Manually adjusting to give some space for the button

            // Draw the white outline
            g.setColor(Color.WHITE); // White outline color
            for (int offsetX = -2; offsetX <= 2; offsetX++) {
                for (int offsetY = -2; offsetY <= 2; offsetY++) {
                    g.drawString(title, x + offsetX, y + offsetY); // Draw offset strings for outline
                }
            }

            // Draw the main text (on top of the outline)
            g.setColor(new Color(28, 57, 102, 230)); // Text color (navy blue)
            g.drawString(title, x, y); // Draw main text at center
        }
    }
    
    /**
     * Returns the instance of the game (MeteorAvoidGUI) that was created when the "Start Game" button was clicked.
     * 
     * @return The MeteorAvoidGUI instance representing the active game window.
     */
    public MeteorAvoidGUI returnGame() {
        return game;
    }
}
