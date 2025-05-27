package ui;

import java.awt.*;
import javax.swing.*;

// public abstract class AbstractScreen {
public abstract class AbstractScreen extends JPanel{
    
//     protected JFrame window;
    protected JPanel bgPanel;
    protected JLabel bgLabel;
    protected JPanel uiCircle;

    public AbstractScreen(){
        setLayout(null);
        createMainField();
        createBackground("basic");
    }

//         window.setVisible(true);
//     }
    public void createMainField(){
//         window = new JFrame();
        setPreferredSize(new Dimension(1000,600));
        setBackground(Color.black);
        // setDefaultCloseOperation(EXIT_ON_CLOSE);
        // getContentPane().setBackground(Color.black);
//         window.setSize(1000,600);
//         window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         window.getContentPane().setBackground(Color.black);
//         window.setLayout(null);
//     }
    }
    public void createBackground(String petType) {
        setLayout(null);
        bgPanel = new JPanel();
        bgPanel.setBounds(0, 0, 1000, 600);
        bgPanel.setBackground(null);
        bgPanel.setLayout(null);
        this.add(bgPanel);

        bgLabel = new JLabel();
        bgLabel.setBounds(0, 0, 1000, 600);
        ImageIcon bgUnscaled;
        if (petType.equals("Tyrunt")){
            bgUnscaled = new ImageIcon(getClass().getClassLoader().getResource("assets/past_background.jpeg"));
        } else if (petType.equals("Magearna")){
            bgUnscaled = new ImageIcon(getClass().getClassLoader().getResource("assets/future_background.jpg"));
        } else if (petType.equals("Furfrou")) {
            bgUnscaled = new ImageIcon(getClass().getClassLoader().getResource("assets/city_background.jpg"));
        } else {
            bgUnscaled = new ImageIcon(getClass().getClassLoader().getResource("assets/basic_background.jpg"));
        }
        Image scaledImage = bgUnscaled.getImage().getScaledInstance(1000, 600, Image.SCALE_SMOOTH);
        bgLabel.setIcon(new ImageIcon(scaledImage));
        bgPanel.add(bgLabel);

        uiCircle = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Color navyBlue = new Color(28, 57, 102, 230);
                g2d.setColor(navyBlue);
                int circleSize = 400;
                g2d.fillOval(0, getHeight() - circleSize, circleSize, circleSize);

                g2d.dispose();
            }
        };

        uiCircle.setBounds(0, 0, 1000, 600);
        uiCircle.setOpaque(false);
        uiCircle.setLayout(null);
        bgLabel.add(uiCircle);
    }
    
    public abstract void createUIElements();


}


