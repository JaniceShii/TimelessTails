package ui;
import game.GameManager;
import java.awt.*;
import javax.swing.*;

public class Instructions6 extends AbstractScreen{
    // create the main game interface and game application window
    GameManager gm;
    Pet pet;
    // used to display the background image (city_backgroumd.jpg)
    public JPanel bgPanel;
    private JLabel instructionsLabel;
    private JLabel objectiveLabel;
    private JLabel lineLabel;
    private JButton continueButton;
    private JButton prevButton;
    private JButton backButton;

    // constructor method
    public Instructions6(GameManager gm) {
        this.gm = gm;
        createUIElements();
    }

    @Override
    public void createBackground(String petType) {
        super.createBackground("basic");  
        uiCircle.setBounds(300, 20, 1000, 475); 
       bgLabel.remove(uiCircle);
       uiCircle = new JPanel() {
        @Override
        protected void paintComponent(Graphics g){
            // use paintComponent to make sure the background shines through on other elements 
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // create the navy blue colour circle (with transparency)
            Color navyBlue = new Color(28, 57, 102, 230); 
            g2d.setColor(navyBlue);

            // create the navy oval
            int ovalWidth = 500;
            int ovalHeight = 500;
            int x = 200;             
            int y = 40;            
            g2d.fillOval(x, y, ovalWidth, ovalHeight);
            g2d.dispose();
        }

       };
       
       uiCircle.setBounds(50, 0, 1000, 600);
        uiCircle.setOpaque(false);
        bgLabel.add(uiCircle);
        bgLabel.revalidate();
        bgLabel.repaint();

    }


    @Override
    public void createUIElements() {
        uiCircle.setLayout(null);

        ImageIcon backIcon = new ImageIcon(getClass().getClassLoader().getResource("assets/back_arrow.png"));
        backButton = new JButton(backIcon);

        backButton.setBounds(330, 95, 24, 24);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> gm.showMainMenu());
        uiCircle.add(backButton);

        instructionsLabel = new JLabel("Instructions");
        instructionsLabel.setBounds(350, 90, 200, 30);
        instructionsLabel.setForeground(Color.WHITE);
        instructionsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        instructionsLabel.setFont(new Font("Inter", Font.BOLD, 25));
        uiCircle.add(instructionsLabel);

        objectiveLabel = new JLabel("Tutorial");
        objectiveLabel.setBounds(345, 150, 200, 30);
        objectiveLabel.setForeground(Color.WHITE);
        objectiveLabel.setHorizontalAlignment(SwingConstants.CENTER);
        objectiveLabel.setFont(new Font("Inter", Font.BOLD, 20));
        uiCircle.add(objectiveLabel);

        lineLabel = new JLabel("--------------------");
        lineLabel.setBounds(300, 170, 300, 30);
        lineLabel.setForeground(Color.WHITE);
        lineLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lineLabel.setFont(new Font("Inter", Font.BOLD, 20));
        uiCircle.add(lineLabel);
        
        JLabel descLabel = new JLabel("<html><div style='text-align: center;'>" + "Based on pets current stats," +
        "then its state will change and limit available commands <br>" + 
        "- Dead state: no commands<br>" + "- Sleeping state: no commands: <br>" + "- Angry state: Give gift and play<br>"
        + "- Hungry state: all commands<br>" + "- Normal state: all commands<br><br>" + "Score is also kept throughout the game. <br>"
        + "- Score starts at 0<br>" + "- Score increases by 100 when user performs any action<br>"
        + "- Score increases by 150 when user wins the minigame through the play action<br>" +
        "- Score does not decrease " + "</div></html>");
        //descLabel.setHorizontalAlignment(SwingConstants.CENTER);
        //descLabel.setVerticalAlignment(SwingConstants.CENTER);
        descLabel.setBounds(250, 95, 400, 400);
        descLabel.setForeground(Color.WHITE);
        descLabel.setFont(new Font("Inter", Font.PLAIN, 11));
        uiCircle.add(descLabel);

        Color navyBlue = new Color(28, 57, 102, 230);

        prevButton = new JButton("Previous");
        prevButton.setBounds(400, 440, 100, 35);
        prevButton.setBackground(Color.WHITE);
        prevButton.setForeground(navyBlue);
        prevButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        prevButton.setFocusPainted(false);
        prevButton.setFont(new Font("Inter", Font.BOLD, 14));
        prevButton.addActionListener(e -> gm.showInstructions5());
        uiCircle.add(prevButton);

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
        
        closePanel.setBounds(870, 20, 60, 60);
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
        closeButton.setBounds(870, 20, 60, 60);
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


    };
}
