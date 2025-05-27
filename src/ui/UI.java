package ui;
import game.GameManager;
import java.awt.*;
import javax.swing.*;

public class UI {
    // create the main game interface and game application window
    GameManager gm;
    JFrame window;
    // used to display the background image (city_backgroumd.jpg)
    public JPanel bgPanel;
    public JLabel bgLabel;
    
    // ui components
    private JPanel uiPanel;
    private JLabel logoLabel;
    private JLabel gameNameLabel;
    private JButton startButton;
    private JButton continueButton;
    private JButton settingsButton;

    // constructor method
    public UI(GameManager gm) {
        this.gm = gm;

        createMainField();
        createBackground();
        createUIElements();

        window.setVisible(true);
    }

    public void createMainField() {
        window = new JFrame();
        window.setSize(1000, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
    }

    public void createBackground() {
        bgPanel = new JPanel();
        bgPanel.setBounds(0, 0, 1000, 600);
        bgPanel.setBackground(null);
        bgPanel.setLayout(null);
        window.add(bgPanel);

        bgLabel = new JLabel();
        bgLabel.setBounds(0, 0, 1000, 600);
        ImageIcon bgUnscaled = new ImageIcon(getClass().getClassLoader().getResource("city_background.jpg"));
        Image scaledImage = bgUnscaled.getImage().getScaledInstance(1000, 600, Image.SCALE_SMOOTH);
        ImageIcon background = new ImageIcon(scaledImage);
        bgLabel.setIcon(background);
        bgPanel.add(bgLabel);
    }
    
    public void createUIElements() {
        uiPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                // use paintComponent to make sure the background shines through on other elements 
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // create the navy blue colour circle (with transparency)
                Color navyBlue = new Color(28, 57, 102, 230); 
                g2d.setColor(navyBlue);
                int circleSize = 400;
                g2d.fillOval(0, getHeight() - circleSize, circleSize, circleSize);
                // center of navy blue circle is at (200,400)
                g2d.dispose();
            }
        };
        
        uiPanel.setBounds(0, 0, 1000, 600);
        uiPanel.setOpaque(false);
        uiPanel.setLayout(null);
        // make uiPanel a child of bgLabel (makes sure the UI elements appear on top of the background)
        bgLabel.add(uiPanel);
        
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
        logoPanel.setBounds(160, 240, 80, 80);
        logoPanel.setOpaque(false);
        logoPanel.setLayout(new BorderLayout());
        uiPanel.add(logoPanel);
        
        logoLabel = new JLabel("Logo");
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        logoPanel.add(logoLabel, BorderLayout.CENTER);
        
        gameNameLabel = new JLabel("Timeless Tails");
        gameNameLabel.setBounds(100, 330, 200, 30);
        gameNameLabel.setForeground(Color.WHITE);
        gameNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameNameLabel.setFont(new Font("Inter", Font.BOLD, 20));
        uiPanel.add(gameNameLabel);
        
        startButton = new JButton("Start New Game");
        startButton.setBounds(100, 380, 200, 40);
        startButton.setBackground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.setFont(new Font("Inter", Font.PLAIN, 14));
        uiPanel.add(startButton);
        
        continueButton = new JButton("Continue Saved Game");
        continueButton.setBounds(100, 430, 200, 40);
        continueButton.setBackground(Color.WHITE);
        continueButton.setFocusPainted(false);
        continueButton.setFont(new Font("Inter", Font.PLAIN, 14));
        uiPanel.add(continueButton);
        
        settingsButton = new JButton("Settings");
        settingsButton.setBounds(100, 480, 200, 40);
        settingsButton.setBackground(Color.WHITE);
        settingsButton.setFocusPainted(false);
        settingsButton.setFont(new Font("Inter", Font.PLAIN, 14));
        uiPanel.add(settingsButton);
    }
}