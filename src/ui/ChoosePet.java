package ui;

import game.GameManager;
import java.awt.*;
import javax.swing.*;

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

     // constructor method
     public ChoosePet(GameManager gm) {
         this.gm = gm;
         createUIElements();
     }

     @Override
     public void createBackground(String petType) {
        super.createBackground("basic"); 
        uiCircle.setBounds(300, 20, 1000, 475); 
        bgLabel.add(uiCircle);
     }

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

        tyruntPanel.setBounds(40, 200, 80, 80);
        tyruntPanel.setOpaque(false);
        tyruntPanel.setLayout(new BorderLayout());
        uiCircle.add(tyruntPanel);

        JLabel tyruntLabel = new JLabel();
        ImageIcon tyruntUnscaled = new ImageIcon(getClass().getClassLoader().getResource("assets/tyrunt.png"));
        Image tyruntScaledImage = tyruntUnscaled.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon tyruntImage = new ImageIcon(tyruntScaledImage);
        tyruntLabel.setIcon(tyruntImage);
        tyruntPanel.add(tyruntLabel);


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

        furfrouPanel.setBounds(160, 200, 80, 80);
        furfrouPanel.setOpaque(false);
        furfrouPanel.setLayout(new BorderLayout());
        uiCircle.add(furfrouPanel);


        JLabel furfrouLabel = new JLabel();
        ImageIcon furfrouUnscaled = new ImageIcon(getClass().getClassLoader().getResource("assets/furfrou.png"));
        Image furfrouScaledImage = furfrouUnscaled.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon furfrouImage = new ImageIcon(furfrouScaledImage);
        furfrouLabel.setIcon(furfrouImage);
        furfrouPanel.add(furfrouLabel);


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

        magearnaPanel.setBounds(280, 200, 80, 80);
        magearnaPanel.setOpaque(false);
        magearnaPanel.setLayout(new BorderLayout());
        uiCircle.add(magearnaPanel);

        JLabel magearnaLabel = new JLabel();
        ImageIcon magearnaUnscaled = new ImageIcon(getClass().getClassLoader().getResource("assets/magearna.png"));
        Image magearnaScaledImage = magearnaUnscaled.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon magearnaImage = new ImageIcon(magearnaScaledImage);
        magearnaLabel.setIcon(magearnaImage);
        magearnaPanel.add(magearnaLabel);

        Color navyBlue = new Color(28, 57, 102);


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


        JLabel headingLabel = new JLabel("Choose Your Pokemon!");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headingLabel.setForeground(Color.WHITE);
        headingLabel.setBounds(110, 145, 500, 50);
        uiCircle.add(headingLabel);

        ImageIcon backIcon = new ImageIcon(getClass().getClassLoader().getResource("assets/back_arrow.png"));
        backButton = new JButton(backIcon);
        backButton.setBounds(80, 158, 24, 24);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> gm.showMainMenu());
        uiCircle.add(backButton);


     }

     private void deselectAllButtons() {
        tyruntButton.setBackground(navyBlue);
        furfrouButton.setBackground(navyBlue);
        magearnaButton.setBackground(navyBlue);

        isTyruntSelected = false;
        isFurfrouSelected = false;
        isMagearnaSelected = false;
    }

}
