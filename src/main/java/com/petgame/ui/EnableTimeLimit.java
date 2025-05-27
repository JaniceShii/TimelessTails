package com.petgame.ui;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.time.LocalTime;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.petgame.game.GameManager;

/**
 * The EnableTimeLimit class represents a screen where the player can enable and set time limits
 * for playing the game. 
 * Users can specify start and end hours, and the system will restrict playtime based on the limits.
 * This class extends the AbstractScreen class.
 * 
 * @version 1.0
 * @author Advaith Thakur
 */
public class EnableTimeLimit extends AbstractScreen {
    GameManager gm;

    private JLabel titleLabel;
    private JLabel titleLabel2;
    private JButton backButton;
    private JButton continueButton;
    private JPanel closePanel = new JPanel();
    private JButton closeButton;
    private String correctPassword;
    public static boolean isTimeEnabled = false;
    public static int startHour;
    public static int endHour;

    /**
     * Constructs an EnableTimeLimit screen.
     * @param gm The GameManager instance managing the game flow.
     */
    public EnableTimeLimit(GameManager gm) {
        this.gm = gm;
        createUIElements();
    }

    /**
     * Creates the UI elements for the EnableTimeLimit screen.
     * This includes labels, text fields, buttons, and their  actions.
     */
    @Override
    public void createUIElements() {
        /** Title Label */
        titleLabel = new JLabel("Enable Limits");
        titleLabel.setBounds((400 - 150) / 2 - 20, 250, 200, 30);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Inter", Font.BOLD, 20));
        uiCircle.add(titleLabel);

        // Back Button
        ImageIcon backIcon = new ImageIcon(getClass().getClassLoader().getResource("com/petgame/assets/back_arrow.png"));
        backButton = new JButton(backIcon);
        backButton.setBounds(100, 254, 24, 24);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> gm.previous());
        uiCircle.add(backButton);

        // Prompts users to enter the start time limit
        JLabel startLabel = new JLabel("Enter the start of your limit (24h)");
        startLabel.setFont(new Font("Inter", Font.BOLD, 16));
        startLabel.setForeground(Color.WHITE);
        startLabel.setBounds(70, 320, 300, 15);
        uiCircle.add(startLabel);

        JTextField textField = new JTextField();
        textField.setBounds(100, 340, 200, 45);
        uiCircle.add(textField);

        // Prompts users to enter the ned time limit
        JLabel endLabel = new JLabel("Enter the end of your limit (24h)");
        endLabel.setFont(new Font("Inter", Font.BOLD, 16));
        endLabel.setForeground(Color.WHITE);
        endLabel.setBounds(70, 400, 300, 15);
        uiCircle.add(endLabel);

        JTextField textField2 = new JTextField();
        textField2.setBounds(100, 420, 200, 45);
        uiCircle.add(textField2);


        Color navyBlue = new Color(28, 57, 102, 230);
        
        String correctPassword = "password";

        // Clicking on continue Button sets the the time limit and enables the restriction*
        continueButton = new JButton("Continue");
        continueButton.setBounds(148, 480, 100, 45);
        continueButton.setBackground(Color.WHITE);
        continueButton.setForeground(navyBlue);
        continueButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        continueButton.setFocusPainted(false);
        continueButton.setFont(new Font("Inter", Font.BOLD, 14));
        uiCircle.add(continueButton);
                continueButton.addActionListener(e -> {
            try {
                int startHour = Integer.parseInt(textField.getText().trim()); 
                int endHour = Integer.parseInt(textField2.getText().trim());
                
                // Validate the hours
                if (startHour < 0 || startHour > 23 || endHour < 0 || endHour > 23) {
                    JOptionPane.showMessageDialog(null, "Please enter valid hours between 0 and 23.");
                    return;
                }
               
                gm.getParentalControl().setEnabled(true);
                gm.getParentalControl().setAllowedStart(LocalTime.of(startHour, 0));
                gm.getParentalControl().setAllowedEnd(LocalTime.of(endHour, 0));
                JOptionPane.showMessageDialog(null, "Play time limit enabled. You will only be able to play from " + 
                    startHour + ":00 to " + endHour + ":00");

                gm.previous(); // Return to previous screen after setting the limit
                textField.setText(""); // Clear the text field
                textField2.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid numbers for the hours.");
            }
        });

        
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


        uiCircle.setVisible(true);
    }
}