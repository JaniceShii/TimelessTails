package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenu extends JDialog {
    private Image backgroundImage;
    private MeteorAvoidGUI game;

    public static void main(String[] args) {
        // Create an instance of the MainMenu dialog and make it modal
        MainMenu menu = new MainMenu(null, "Tyrunt");  // Parent window is null since it's a standalone dialog
        menu.setVisible(true);
    }

    public MainMenu(JFrame parent, String petType) {
        // Set up the main menu window
        super(parent, "Dodge the Obstacles", true); // 'true' makes this dialog modal
        setSize(600, 400);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(parent); // Center the dialog relative to the parent window

        // Load background image
        backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("assets/timebackground.jpg")).getImage(); 

        // Set up the content panel with a layout manager
        JPanel contentPanel = new BackgroundPanel();
        contentPanel.setLayout(null); // We still use null layout for custom positioning of components
        setContentPane(contentPanel);

        // Create the "Start Game" button with navy blue color and white outline
        JButton startButton = new JButton("Start Game");
        startButton.setBounds(200, 200, 175, 60); // Position the button (x, y, width, height)
        startButton.setBackground(new Color(28, 57, 102, 255));
        startButton.setForeground(Color.WHITE); // Set the text color to white
        startButton.setFocusPainted(false); // Remove focus outline
        startButton.setFont(new Font("Inter", Font.PLAIN, 18));

        // Add white border to the button
        startButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        // Add hover effect to change background color when mouse enters
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startButton.setBackground(Color.WHITE); // Change to white when hovered
                startButton.setForeground(new Color(28, 57, 102, 230)); // Change text color to navy blue
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startButton.setBackground(new Color(28, 57, 102, 230)); // Revert to navy blue when not hovered
                startButton.setForeground(Color.WHITE); // Revert text color to white
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

    // Custom panel to handle background image and title painting
    private class BackgroundPanel extends JPanel {
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

    public MeteorAvoidGUI returnGame() {
        return game;
    }
}
