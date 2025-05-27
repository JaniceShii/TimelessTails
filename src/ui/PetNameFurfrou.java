package ui;

import game.GameManager;
import java.awt.*;
import javax.swing.*;

public class PetNameFurfrou extends AbstractScreen{
    // create the main game interface and game application window
    GameManager gm;
    Pet pet;
    // used to display the background image (city_backgroumd.jpg)
    public JPanel bgPanel;
    private JButton continueButton;
    private JButton backButton;

    // constructor method
    public PetNameFurfrou(GameManager gm) {
        this.gm = gm;
        createUIElements();
        // !!!!!!!!!!!!!!! use the game manager pet
        pet = gm.getCurrentPet();           
    }

    @Override
    public void createBackground(String petType) {
       super.createBackground("Furfrou");   
       uiCircle.setBounds(300, 20, 1000, 475); 
       bgLabel.add(uiCircle);
    }
    @Override
    public void createUIElements() {

        //Pet Image
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

        furfrouPanel.setBounds(150, 200, 90, 90);
        furfrouPanel.setOpaque(false);
        furfrouPanel.setLayout(new BorderLayout());
        uiCircle.add(furfrouPanel);

        JLabel furfrouLabel = new JLabel();
        ImageIcon furfrouUnscaled = new ImageIcon(getClass().getClassLoader().getResource("assets/furfrou.png"));
        Image furfrouScaledImage = furfrouUnscaled.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon furfrouImage = new ImageIcon(furfrouScaledImage);
        furfrouLabel.setIcon(furfrouImage);
        furfrouPanel.add(furfrouLabel);

        JLabel headingLabel = new JLabel("Name Your Pokemon!");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headingLabel.setForeground(Color.WHITE);
        headingLabel.setBounds(115, 145, 500, 50);
        uiCircle.add(headingLabel);
        
        //add textbox for user to enter pet name
        JTextField textField = new JTextField();
        textField.setBounds(122, 300, 150, 45);
        uiCircle.add(textField);
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
                JOptionPane.showMessageDialog(furfrouPanel, "You must enter a name for your pet!");
            } else if (petName.length() >= 15) {
                JOptionPane.showMessageDialog(furfrouPanel, "You must enter a name that has 15 or fewer characters!");
            } else if (petName.contains(" ")) {
                JOptionPane.showMessageDialog(furfrouPanel, "Spaces are not allowed in the pet name!");
            } else if (!petName.matches("[a-zA-Z0-9]*")) {
                JOptionPane.showMessageDialog(furfrouPanel, "Special characters are not allowed in the pet name!");
            } else {

                pet = new Furfrou(petName, gm);
                gm.setCurrentPet(pet);
                textField.setText("");
                String name = gm.getCurrentPet().getName();
        
                gm.showMainGamePlayFurfrou(); // <-- THIS is where you should now construct GamePlayUI
            }
        });
        
        ImageIcon backIcon = new ImageIcon(getClass().getClassLoader().getResource("assets/back_arrow.png"));
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
        ImageIcon closeUnscaled = new ImageIcon(getClass().getClassLoader().getResource("assets/closeIcon.png"));
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
