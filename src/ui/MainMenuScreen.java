package ui;
import game.GameManager;
import java.awt.*;
import java.time.LocalTime;
import javax.swing.*;

public class MainMenuScreen extends AbstractScreen{
    GameManager gm;
    
    // ui components
    private JLabel logoLabel;
    private JLabel gameNameLabel;
    private JLabel infoLabel;
    private JPanel infoPanel;
    private JButton startButton;
    private JButton continueButton;
    private JButton settingsButton;

    private Inventory inventory;

    // constructor method
    public MainMenuScreen(GameManager gm, Inventory inv) {
        this.gm = gm;
        this.inventory = inv;
        createUIElements();
    }
    
    @Override
    public void createUIElements() {
        Color navyBlue = new Color(28, 57, 102, 255); 


        JPanel logoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(Color.WHITE);
                g2d.fillOval(0, 0, getWidth(), getHeight());

                g2d.dispose();
            }

        };
        
        logoPanel.setBounds(155, 230, 90, 90);
        logoPanel.setOpaque(false);
        logoPanel.setLayout(new BorderLayout());
        uiCircle.add(logoPanel);
        
        JLabel logoLabel = new JLabel();
        ImageIcon logoUnscaled = new ImageIcon(getClass().getClassLoader().getResource("assets/2212logo.png"));
        Image logoScaledImage = logoUnscaled.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon logoImage = new ImageIcon(logoScaledImage);
        logoLabel.setIcon(logoImage);
        logoPanel.add(logoLabel);
        
        
        gameNameLabel = new JLabel("Timeless Tails");
        gameNameLabel.setBounds(100, 330, 200, 30);
        gameNameLabel.setForeground(Color.WHITE);
        gameNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameNameLabel.setFont(new Font("Inter", Font.BOLD, 20));
        uiCircle.add(gameNameLabel);
        
        startButton = new JButton("Start New Game");
        startButton.setBounds(100, 380, 200, 40);
        startButton.setBackground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.setFont(new Font("Inter", Font.PLAIN, 14));
        uiCircle.add(startButton);
        startButton.addActionListener(e -> {

            if (canPlayGame()) {
                // pre populate inventory with a few items
                inventory.populateInventory();
                gm.showChoosePet();
            } else {
                JOptionPane.showMessageDialog(null, "Game is not allowed during this time.");
            }

        });
        
        continueButton = new JButton("Continue Saved Game");
        continueButton.setBounds(100, 430, 200, 40);
        continueButton.setBackground(Color.WHITE);
        continueButton.setFocusPainted(false);
        continueButton.setFont(new Font("Inter", Font.PLAIN, 14));
        uiCircle.add(continueButton);
        continueButton.addActionListener(e -> {
            if (canPlayGame()) {
                gm.showSaveFileScreen();
            } else {
                JOptionPane.showMessageDialog(null, "Game is not allowed during this time.");
            }
        });
        
        
        settingsButton = new JButton("Settings");
        settingsButton.setBounds(100, 480, 200, 40);
        settingsButton.setBackground(Color.WHITE);
        settingsButton.setFocusPainted(false);
        settingsButton.setFont(new Font("Inter", Font.PLAIN, 14));
        uiCircle.add(settingsButton);
        settingsButton.addActionListener(e -> gm.showSettingsScreen());

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

        JPanel infoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
                g2d.setColor(navyBlue);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                
                g2d.dispose();
            }
        };
        infoPanel.setBounds(500, 475, 490, 40);
        infoPanel.setOpaque(false);
        infoPanel.setLayout(new BorderLayout());
        uiCircle.add(infoPanel);

        JLabel infoLabel = new JLabel("Advaith Thakur, Jessica Yang, Janice Shi, Meridith Shang, Jugraj Khangura");
        infoLabel.setFont(new Font("Inter", Font.BOLD, 12));
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setForeground(Color.WHITE);
        infoLabel.setBounds(0, 0, 400, 15);
        infoPanel.add(infoLabel);

        JPanel infoPanel2 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
                g2d.setColor(navyBlue);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                
                g2d.dispose();
            }
        };
        infoPanel2.setBounds(500, 520, 490, 40);
        infoPanel2.setOpaque(false);
        infoPanel2.setLayout(new BorderLayout());
        uiCircle.add(infoPanel2);

        JLabel infoLabel2 = new JLabel("Created as part of CS2212 at Western University  -  Group 48, CS2212B 2025 Winter");
        infoLabel2.setFont(new Font("Inter", Font.BOLD, 11));
        infoLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel2.setForeground(Color.WHITE);
        infoLabel2.setBounds(0, 0, 400, 15);
        infoPanel2.add(infoLabel2);

    }

    public boolean canPlayGame() {
        if (!gm.getParentalControl().isEnabled()) {
            return true;
        }

        LocalTime now = LocalTime.now();
        
        LocalTime start = gm.getParentalControl().getAllowedStart();
        LocalTime end = gm.getParentalControl().getAllowedEnd();

        if (start.isBefore(end)) {
            return now.isAfter(start) && now.isBefore(end);
        } else {
            return now.isAfter(start) || now.isBefore(end);
        }
        }

}

