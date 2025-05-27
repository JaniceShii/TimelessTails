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
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import com.petgame.game.GameManager;

/**
 * The {@code ParentalControlsLogin} class represents a login screen for accessing parental controls.
 * This screen prompts the user to enter a password and validates it before proceeding to the parental control settings.
 * 
 * @version 1.0
 * @author Jugraj Khangura
 */
public class ParentalControlsLogin extends AbstractScreen {
    GameManager gm;

    private JLabel titleLabel;
    private JLabel titleLabel2;
    private JButton backButton;
    private JButton continueButton;
    private String correctPassword;

    /**
     * Constructs a new login screen.
     *
     * @param gm the {@code GameManager} used for screen navigation and control
     */
    public ParentalControlsLogin(GameManager gm) {
        this.gm = gm;
        createUIElements();
    }

    /** Creates the UI elements for the parental controls login, including the title,
     * back button, continue button, password text entry field, and the close button.
     */
    @Override
    public void createUIElements() {
        //The title label displayed
        titleLabel = new JLabel("Parental");
        titleLabel.setBounds((400 - 150) / 2 - 4, 250, 150, 30);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Inter", Font.BOLD, 20));
        uiCircle.add(titleLabel);

        titleLabel2 = new JLabel("Controls Login");
        titleLabel2.setBounds((400 - 150) / 2 - 25, 275, 200, 30);
        titleLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel2.setForeground(Color.WHITE);
        titleLabel2.setFont(new Font("Inter", Font.BOLD, 20));
        uiCircle.add(titleLabel2);
        //Image for the back button displayed
        ImageIcon backIcon = new ImageIcon(getClass().getClassLoader().getResource("com/petgame/assets/back_arrow.png"));
        backButton = new JButton(backIcon);
        backButton.setBounds(100, 254, 24, 24);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> gm.previous());
        uiCircle.add(backButton);

        JLabel passwordLabel = new JLabel("Enter Your Password");
        passwordLabel.setFont(new Font("Inter", Font.BOLD, 16));
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(110, 350, 300, 15);
        uiCircle.add(passwordLabel);

        //Shows the text entry field for user to add the password
        JPasswordField textField = new JPasswordField();
        textField.setBounds(100, 375, 200, 45);
        uiCircle.add(textField);

        Color navyBlue = new Color(28, 57, 102, 230);
        
        //The correct password is "password"
        String correctPassword = "password";

        continueButton = new JButton("Continue");
        continueButton.setBounds(148, 440, 100, 45);
        continueButton.setBackground(Color.WHITE);
        continueButton.setForeground(navyBlue);
        continueButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        continueButton.setFocusPainted(false);
        continueButton.setFont(new Font("Inter", Font.BOLD, 14));
        uiCircle.add(continueButton);
        continueButton.addActionListener(e -> {
            String enteredPassword = textField.getText().trim();;

            if (enteredPassword.equals(correctPassword)) {
                textField.setText(""); 
                gm.showParentalControlsScreen();
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect password. Please try again.");
            }
        });
        
        //Panel for the close button
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