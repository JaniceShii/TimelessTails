package ui;

import game.GameManager;
import java.awt.*;
import javax.swing.*;

public class ParentalControlsScreen extends AbstractScreen {
    GameManager gm;

    private JLabel titleLabel;
    private JButton backButton;
    private JButton enableTime;
    private JButton disableTime;
    private JButton revivePet;
    private JButton showStatistics;

    public ParentalControlsScreen(GameManager gm) {
        this.gm = gm;
        createUIElements();
    }

    @Override
    public void createUIElements() {
        titleLabel = new JLabel("Parental Controls");
        titleLabel.setBounds((400 - 150) / 2 - 10, 250, 200, 30);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Inter", Font.BOLD, 20));
        uiCircle.add(titleLabel);

        ImageIcon backIcon = new ImageIcon(getClass().getClassLoader().getResource("assets/back_arrow.png"));
        backButton = new JButton(backIcon);
        backButton.setBounds(98, 254, 24, 24);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> gm.showMainMenu());
        uiCircle.add(backButton);

        enableTime = new JButton("Enable Time Limit");
        enableTime.setBounds(75, 310, 250, 40);
        enableTime.setFont(new Font("Inter", Font.PLAIN, 14));
        enableTime.setBackground(Color.white);
        enableTime.setFocusPainted(false);
        enableTime.addActionListener(e -> gm.showEnableTimeControl());

        disableTime = new JButton("Disable Time Limit");
        disableTime.setBounds(75, 370, 250, 40);
        disableTime.setFont(new Font("Inter", Font.PLAIN, 14));
        disableTime.setBackground(Color.white);
        disableTime.setFocusPainted(false);
        disableTime.addActionListener(e -> {
            gm.getParentalControl().setEnabled(false);
            gm.getParentalControl().setAllowedStart(java.time.LocalTime.of(0, 0)); // Default start: 00:00
            gm.getParentalControl().setAllowedEnd(java.time.LocalTime.of(23, 59)); // Default end: 23:59
            JOptionPane.showMessageDialog(null, "Time limit disabled!");

        });

        revivePet = new JButton("Revive Pet");
        revivePet.setBounds(75, 430, 250, 40);
        revivePet.setFont(new Font("Inter", Font.PLAIN, 14));
        revivePet.setBackground(Color.white);
        revivePet.setFocusPainted(false);
        revivePet.addActionListener(e -> {
            gm.showRevive();
        });

        showStatistics = new JButton("Statistics");
        showStatistics.setBounds(75, 490, 250, 40);
        showStatistics.setFont(new Font("Inter", Font.PLAIN, 14));
        showStatistics.setBackground(Color.white);
        showStatistics.setFocusPainted(false);
        showStatistics.addActionListener(e -> {
            gm.showStatistics();
        });


        uiCircle.add(enableTime);
        uiCircle.add(disableTime);
        uiCircle.add(revivePet);
        uiCircle.add(showStatistics);

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