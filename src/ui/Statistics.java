package ui;

import game.GameManager;
import java.awt.*;
import javax.swing.*;

public class Statistics extends AbstractScreen {
    GameManager gm;

    private JLabel titleLabel;
    private JLabel titleLabel2;
    private JButton backButton;
    private JButton continueButton;
    private String correctPassword;
    private JButton resetButton;

    public Statistics(GameManager input_gm) {
        this.gm = input_gm;
        createUIElements();
    }

    @Override
    public void createUIElements() {

        if(this.gm == null) {
            return;
        }
        titleLabel = new JLabel("Statistics");
        titleLabel.setBounds((400 - 150) / 2 - 4, 250, 150, 30);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Inter", Font.BOLD, 20));
        uiCircle.add(titleLabel);

        ImageIcon backIcon = new ImageIcon(getClass().getClassLoader().getResource("assets/back_arrow.png"));
        backButton = new JButton(backIcon);
        backButton.setBounds(100, 254, 24, 24);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> this.gm.previous());
        uiCircle.add(backButton);


        Color navyBlue = new Color(28, 57, 102, 230);

        
        // Convert milliseconds to a more readable format
        long totalPlayTimeMs = this.gm.getParentalControl().getTotalPlayTime();
        String totalPlayTimeFormatted = formatTime(totalPlayTimeMs);
        
        JLabel playtimeLabel = new JLabel("<html><div style='text-align: center;'>" + 
        "You have played for a total of <br>" + totalPlayTimeFormatted + "</div></html>");
        playtimeLabel.setFont(new Font("Inter", Font.BOLD, 16));
        playtimeLabel.setForeground(Color.WHITE);
        playtimeLabel.setBounds(70, 350, 300, 40);
        uiCircle.add(playtimeLabel);

        // Convert average playtime to readable format
        long avgPlayTimeMs = gm.getParentalControl().getAveragePlayTime();
        String avgPlayTimeFormatted = formatTime(avgPlayTimeMs);
        
        JLabel avgPlaytimeLabel = new JLabel("<html><div style='text-align: center;'>" + 
        "Your average play session is <br>" + avgPlayTimeFormatted + "</div></html>");
        avgPlaytimeLabel.setFont(new Font("Inter", Font.BOLD, 16));
        avgPlaytimeLabel.setForeground(Color.WHITE);
        avgPlaytimeLabel.setBounds(70, 400, 300, 40);
        uiCircle.add(avgPlaytimeLabel);
        
        // Helper method to format time from milliseconds to a readable format
        

        resetButton = new JButton("Reset Stats");
        resetButton.setBounds(70, 450, 300, 40);
        resetButton.setFont(new Font("Inter", Font.BOLD, 16));
        resetButton.setBackground(navyBlue);
        resetButton.setForeground(Color.WHITE);
        resetButton.addActionListener(e -> {
            // Call the parental control method to reset play time statistics.
            gm.getParentalControl().resetPlayTime();
            // Update the labels to show the reset values.
            playtimeLabel.setText("<html><div style='text-align: center;'>You have played for a total of <br> 0 s</div></html>");
            avgPlaytimeLabel.setText("<html><div style='text-align: center;'>Your average play session is <br> 0 s</div></html>");
        });
        uiCircle.add(resetButton);

        
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

    private String formatTime(long timeInMs) {
        if (timeInMs < 1000) {
            return timeInMs + " ms";
        } else if (timeInMs < 60000) {
            return String.format("%.1f seconds", timeInMs / 1000.0);
        } else if (timeInMs < 3600000) {
            return String.format("%.1f minutes", timeInMs / 60000.0);
        } else {
            return String.format("%.1f hours", timeInMs / 3600000.0);
        }
    }
}