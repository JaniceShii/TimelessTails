package ui;

import game.GameManager;
import java.awt.*;
import javax.swing.*;

public class PetName extends AbstractScreen{
    // create the main game interface and game application window
    GameManager gm;
    Pet pet;
    String type;
    // used to display the background image (city_backgroumd.jpg)
    public JPanel bgPanel;
    private JButton continueButton;
    private JButton backButton;

    // constructor method
    public PetName(GameManager gm, String type) {
        this.gm = gm;
        createUIElements();
        // !!!!!!!!!!!!!!! use the game manager pet
        pet = gm.getCurrentPet();           
    }

    @Override
    public void createBackground(String petType) {
       super.createBackground(gm.getCurrentPet().getPetType()); 
       uiCircle.setBounds(300, 20, 1000, 475); 
       bgLabel.add(uiCircle);
    }
    @Override
    public void createUIElements() {

        //Pet Image
        JPanel petPanel = new JPanel() {
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

        petPanel.setBounds(150, 200, 90, 90);
        petPanel.setOpaque(false);
        petPanel.setLayout(new BorderLayout());
        uiCircle.add(petPanel);

        JLabel petLabel = new JLabel();
        ImageIcon petUnscaled = new ImageIcon(getClass().getClassLoader().getResource("assets/furfrou.png"));
        Image petScaledImage = petUnscaled.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon petImage = new ImageIcon(petScaledImage);
        petLabel.setIcon(petImage);
        petPanel.add(petLabel);

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
                JOptionPane.showMessageDialog(petPanel, "You must enter a name for your pet!");
            } else {

                // !!!!!!!! get the gameManagerPet and sets its name to the one user inputted
                if (type.equals("furfrou")) {
                    pet = new Furfrou(petName, gm);
                } else if (type.equals("tyrunt")) {
                    pet = new Tyrunt(petName, gm);
                } else if (type.equals("magearna")) {
                    pet = new Magearna(petName, gm);
                }
                gm.setCurrentPet(pet);

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
