package ui;
import game.GameManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
public class GamePlayUI extends AbstractScreen implements PetStateListener{
    // create the main game interface and game application window
    GameManager gm;
    private JButton bedButton;
    private JButton feedButton;
    private JButton giftButton;
    private JButton vetButton;
    private JButton playButton;
    private JButton exerciseButton;
    private JButton settingsButton;
    private JButton closeButton;
    private int scoreString;
    private Inventory inventory;
    private boolean onInventory = false;
    

    private BufferedImage image;

    private JPanel petPanel;
    private JPanel textPanel = null;
    private Timer messageTimer = null;

    // create label and panel for the GIFs
    JLabel GIFLabel = new JLabel();
    JPanel GIFPanel = new JPanel();
    JPanel scorePanel = new JPanel();
    JLabel scoreLabel = new JLabel();

    private Pet pet;

    // constructor method
    public GamePlayUI(GameManager gm, Inventory inventory) {
        this.gm = gm;
        this.inventory = inventory;
        this.pet = gm.getCurrentPet();
        pet.setListener(this);
        createUIElements();
        startScoreUpdater();
    }

    private void startScoreUpdater() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000); // Update every 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Safely update UI
                SwingUtilities.invokeLater(() -> scoreLabel.setText("<html><div style='text-align: center;'>Score:\n" + "<br>" + pet.getPoints() + "</div></html>"));
            }
        }).start();
    }

    @Override
    public void createBackground(String petType) {
        super.createBackground("Furfrou"); 
        bgLabel.remove(uiCircle);
        uiCircle = new JPanel();
        uiCircle.setBounds(0, 0, 1000, 600);
        uiCircle.setOpaque(false);
        uiCircle.setLayout(null);
        bgLabel.add(uiCircle);
    }

    // @Override
    // public void createUIElements() {
    //     Color navyBlue = new Color(28, 57, 102, 255); 
    //     Color lightGreen = new Color(152, 253, 169, 255);
    //     //Pet pet = new Furfrou("pet");

    private void showMessage(String message) {
        // Cancel any existing timer
        if (messageTimer != null && messageTimer.isRunning()) {
            messageTimer.stop();
        }
        
        // Remove existing text panel if it exists
        if (textPanel != null) {
            uiCircle.remove(textPanel);
        }
        
        // Create new text panel
        textPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // create the navy blue colour circle (with transparency)
                Color navyOpaque = new Color(28, 57, 102, 255);
                g2d.setColor(navyOpaque);
                g2d.fillRect((1000-660)/2, 10, 660, 80);
                g2d.dispose();
            }
        };
        
        textPanel.setBounds(0, 0, 1000, 600);
        textPanel.setOpaque(false);
        textPanel.setLayout(null);
        
        // Add message label
        JLabel textLabel = new JLabel("<html>" + message + "</html>");
        textLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        textLabel.setForeground(Color.WHITE);
        textLabel.setBounds((1000-660)/2 + 20, 10, 625, 80);
        textPanel.add(textLabel);
        
        // Add panel to UI
        uiCircle.add(textPanel);
        
        // Refresh UI
        revalidate();
        repaint();
        
        // Start timer for automatic removal
        messageTimer = new Timer(10000, e -> {
            uiCircle.remove(textPanel);
            revalidate();
            repaint();
        });
        messageTimer.setRepeats(false);
        messageTimer.start();
    }


    @Override
    public void createUIElements() {
        Color navyBlue = new Color(28, 57, 102, 255); 
        Color lightGreen = new Color(152, 253, 169, 255);
        Color lightRed = new Color(255, 178, 178, 255);
        Color lightYellow = new Color(255, 243, 176, 255);
        //Pet pet = new Furfrou("pet");



        JPanel statsPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                // use paintComponent to make sure the background shines through on other elements 
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(navyBlue);
                int rectHeight = 200;
                int rectWidth = 200;
                int rectHDiameter = 200;
                int rectWDiameter = 200;
                g2d.fillRoundRect(10, getHeight() - rectHeight - 40, rectWidth, rectHeight, rectWDiameter, rectHDiameter);
                g2d.dispose();
            }
        };

        statsPanel.setBounds(0, 0, 1000, 600);
        statsPanel.setOpaque(false);
        statsPanel.setLayout(null);
        uiCircle.add(statsPanel);


        try {
            image = ImageIO.read(getClass().getResourceAsStream("/portraits/furfrounormal.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        setPreferredSize(new Dimension(200,200));

        petPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                Shape circle = new Ellipse2D.Double(0,0,200,200);
                g2d.setClip(circle);

                g2d.drawImage(image, 0, 0, 200, 200, this);
                
                g2d.dispose();
            }
        };
        petPanel.setBounds(11, 360, 200, 200);
        petPanel.setOpaque(false);
        petPanel.setLayout(new BorderLayout());
        statsPanel.add(petPanel);

        // add pet gif
        // onStateChanged("Idle.gif");

        JPanel statsPanel2 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // create the navy blue colour circle (with transparency)
                Color navyBlue = new Color(28, 57, 102, 230); 
                g2d.setColor(navyBlue);
                int rectHeight = 200;
                int rectWidth = 385;
                int rectHDiameter = 200;
                int rectWDiameter = 100;
                g2d.fillRoundRect(50, getHeight() - rectHeight - 40, rectWidth, rectHeight, rectWDiameter, rectHDiameter);
                // center of navy blue circle is at (200,400)
                g2d.dispose();
            }
        };

        statsPanel2.setBounds(0, 0, 1000, 600);
        statsPanel2.setOpaque(false);
        statsPanel2.setLayout(null);
        statsPanel.add(statsPanel2);

        // adding text for the statistics bars in the statistics panel
        JLabel health = new JLabel("Health");
        health.setFont(new Font("Inter", Font.BOLD, 16));
        health.setForeground(Color.WHITE);
        health.setBounds(230, 380, 50, 16);
        statsPanel2.add(health);

        JLabel sleep = new JLabel("Sleep");
        sleep.setFont(new Font("Inter", Font.BOLD, 16));
        sleep.setForeground(Color.WHITE);
        sleep.setBounds(230, 420, 50, 16);
        statsPanel2.add(sleep);

        JLabel fullness = new JLabel("Fullness");
        fullness.setFont(new Font("Inter", Font.BOLD, 16));
        fullness.setForeground(Color.WHITE);
        fullness.setBounds(230, 460, 100, 16);
        statsPanel2.add(fullness);

        JLabel happiness = new JLabel("Happiness");
        happiness.setFont(new Font("Inter", Font.BOLD, 16));
        happiness.setForeground(Color.WHITE);
        happiness.setBounds(230, 500, 100, 16);
        statsPanel2.add(happiness);

        // bars for each statistic
        JPanel healthBar = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(230, 400, 175, 10, 10, 10);
        
                g2d.dispose();
            }
        };
        
        healthBar.setBounds(0, 0, 1000, 600);
        healthBar.setOpaque(false);
        healthBar.setLayout(new BorderLayout());
        statsPanel2.add(healthBar);


        JPanel healthBarColor = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                 // Get health percentage from pet instance
                float healthPercentage = pet.getHealthPercentage();

                if (healthPercentage < 0.25) {
                    g2d.setColor(lightRed);
                } else if (healthPercentage < 0.60) {
                    g2d.setColor(lightYellow);
                } else {
                    g2d.setColor(lightGreen);
                }

                 // Calculate new width based on health percentage
                int newWidth = (int) (175 * healthPercentage);

                g2d.fillRoundRect(230, 400, newWidth, 10, 10, 10);
        
                g2d.dispose();
            }
        };
        
        healthBarColor.setBounds(0, 0, 1000, 600);
        healthBarColor.setOpaque(false);
        healthBarColor.setLayout(new BorderLayout());
        healthBar.add(healthBarColor);
        healthBarColor.repaint();



        JPanel sleepBar = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(230, 440, 175, 10, 10, 10);
        
                g2d.dispose();
            }
        };
        
        sleepBar.setBounds(0, 0, 1000, 600);
        sleepBar.setOpaque(false);
        sleepBar.setLayout(new BorderLayout());
        statsPanel2.add(sleepBar);

        JPanel sleepBarColor = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                 // Get health percentage from pet instance
                float sleepPercentage = pet.getSleepPercentage();

                if (sleepPercentage < 0.25) {
                    g2d.setColor(lightRed);
                } else if (sleepPercentage < 0.60) {
                    g2d.setColor(lightYellow);
                } else {
                    g2d.setColor(lightGreen);
                }

                 // Calculate new width based on health percentage
                int newWidth = (int) (175 * sleepPercentage);

                g2d.fillRoundRect(230, 440, newWidth, 10, 10, 10);
        
                g2d.dispose();
            }
        };
        
        sleepBarColor.setBounds(0, 0, 1000, 600);
        sleepBarColor.setOpaque(false);
        sleepBarColor.setLayout(new BorderLayout());
        sleepBar.add(sleepBarColor);
        sleepBarColor.repaint();



        JPanel fullnessBar = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(230, 480, 175, 10, 10, 10);
        
                g2d.dispose();
            }
        };
        
        fullnessBar.setBounds(0, 0, 1000, 600);
        fullnessBar.setOpaque(false);
        fullnessBar.setLayout(new BorderLayout());
        statsPanel2.add(fullnessBar);

        JPanel fullnessBarColor = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                 // Get health percentage from pet instance
                float fullnessPercentage = pet.getFullnessPercentage();

                if (fullnessPercentage < 0.25) {
                    g2d.setColor(lightRed);
                } else if (fullnessPercentage < 0.6) {
                    g2d.setColor(lightYellow);
                } else {
                    g2d.setColor(lightGreen);
                }


                 // Calculate new width based on health percentage
                int newWidth = (int) (175 * fullnessPercentage);
                g2d.fillRoundRect(230, 480, newWidth, 10, 10, 10);
        
                g2d.dispose();
            }
        };
        
        fullnessBarColor.setBounds(0, 0, 1000, 600);
        fullnessBarColor.setOpaque(false);
        fullnessBarColor.setLayout(new BorderLayout());
        fullnessBar.add(fullnessBarColor);
        fullnessBarColor.repaint();
        



        JPanel happinessBar = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(230, 520, 175, 10, 10, 10);
        
                g2d.dispose();
            }
        };
        
        happinessBar.setBounds(0, 0, 1000, 600);
        happinessBar.setOpaque(false);
        happinessBar.setLayout(new BorderLayout());
        statsPanel2.add(happinessBar);
        healthBarColor.repaint();


        JPanel happinessBarColor = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                 // Get health percentage from pet instance
                float happinessPercentage = pet.getHappinessPercentage();

                if (happinessPercentage < 0.25) {
                    g2d.setColor(lightRed);
                } else if (happinessPercentage < 0.60) {
                    g2d.setColor(lightYellow);
                } else {
                    g2d.setColor(lightGreen);
                }

                 // Calculate new width based on health percentage
                int newWidth = (int) (175 * happinessPercentage);
                g2d.fillRoundRect(230, 520, newWidth, 10, 10, 10);
        
                g2d.dispose();
            }
        };
        
        happinessBarColor.setBounds(0, 0, 1000, 600);
        happinessBarColor.setOpaque(false);
        happinessBarColor.setLayout(new BorderLayout());
        happinessBar.add(happinessBarColor);
        happinessBarColor.repaint();



        JPanel inventoryPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                
                g2d.dispose();
            }
        };

        inventoryPanel.setBounds(10, 10, 60, 60);
        inventoryPanel.setOpaque(false);
        inventoryPanel.setLayout(new BorderLayout());
        uiCircle.add(inventoryPanel);

        JLabel invBgLabel = new JLabel();
        ImageIcon invUnscaled = new ImageIcon(getClass().getClassLoader().getResource("assets/InventoryIcon.png"));
        Image invScaledImage = invUnscaled.getImage().getScaledInstance(57, 55, Image.SCALE_SMOOTH);
        ImageIcon invBackground = new ImageIcon(invScaledImage);
        invBgLabel.setIcon(invBackground);
        inventoryPanel.add(invBgLabel);

        JButton inventoryButton = new JButton();
        inventoryButton.setBounds(10,10,60,60);
        inventoryButton.setFocusPainted(false);
        inventoryButton.setContentAreaFilled(false);
        inventoryButton.setBorder(null);
        inventoryButton.setOpaque(false);
        uiCircle.add(inventoryButton);
        inventoryButton.addActionListener(e -> {
            
            gm.showInventoryScreen();
  //          while (gm.getVariable()) { }
//            Thread.sleep(1000);
            // something that calls something on your inventory 
            //gm variable when not on inv = true, when on inv make false
            // do a while loop using the gm variable
            //int points = pet.getPoints();
       //     scoreLabel.setText("<html><div style='text-align: center;'>Score:\n" + "<br>" + points + "</div></html>");

        });


        scorePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                
                g2d.dispose();
            }
        };

        scorePanel.setBounds(10, 80, 60, 60);
        scorePanel.setOpaque(false);
        scorePanel.setLayout(new BorderLayout());
        uiCircle.add(scorePanel);

        scoreLabel = new JLabel("<html><div style='text-align: center;'>Score:\n " + "<br>" + scoreString + "</div></html>");
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setVerticalAlignment(SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Inter", Font.BOLD, 13));
        scorePanel.add(scoreLabel);

        bedButton = new JButton("Go To Bed");
        bedButton.setBounds(450, 400, 150, 60);
        bedButton.setFocusPainted(false);
        bedButton.setBackground(navyBlue);
        bedButton.setForeground(Color.WHITE);
        bedButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        bedButton.setFont(new Font("Inter", Font.BOLD, 14));
        uiCircle.add(bedButton);
        bedButton.addActionListener(e -> {
            int sleepIncrease = pet.sleep();
            scoreLabel.setText("<html><div style='text-align: center;'>Score:\n" + "<br>" + pet.getPoints() + "</div></html>");
            if (sleepIncrease >= 0) {
                // change
                showMessage(pet.getName() + " went to sleep. " + pet.getName() + " will sleep until its sleep statistic is at " + pet.getMaxSleep() + ".");
            } else if (pet.getState().equals("dead")) {
                showMessage(pet.getName() + " has died.");
            } else if (pet.getState().equals("angry")) {
                showMessage(pet.getName() + " is angry! Please increase its happiness first by playing with " + pet.getName() +" or giving it a gift!");
            } else if (pet.getState().equals("sleep")) {
                showMessage(pet.getName() + " is already asleep!");
            }
        });

        feedButton = new JButton("Feed");
        feedButton.setBounds(620, 400, 150, 60);
        feedButton.setFocusPainted(false);
        feedButton.setBackground(navyBlue);
        feedButton.setForeground(Color.WHITE);
        feedButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        feedButton.setFont(new Font("Inter", Font.BOLD, 14));
        uiCircle.add(feedButton);
        feedButton.addActionListener(e -> {
            if (pet.getState().equals("sleep")) {
                showMessage(pet.getName() + " is asleep! Please wait for " + pet.getName() + " to wake up first before feeding it");
            } else if (pet.getState().equals("angry")) {
                showMessage(pet.getName() + " is angry! Please increase its happiness first by playing with " + pet.getName() +" or giving it a gift!");
            } else {
                gm.showInventoryScreen();
            }
        });

        // come back to feed, need to have text panel appear

        giftButton = new JButton("Give Gifts");
        giftButton.setBounds(790, 400, 150, 60);
        giftButton.setFocusPainted(false);
        giftButton.setBackground(navyBlue);
        giftButton.setForeground(Color.WHITE);
        giftButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        giftButton.setFont(new Font("Inter", Font.BOLD, 14));
        uiCircle.add(giftButton);
        giftButton.addActionListener(e -> {
            if (pet.getState().equals("sleep")) {
                showMessage(pet.getName() + " is asleep! Please wait for " + pet.getName() + " to wake up first before giving it a gift.");
            } else {
                gm.showInventoryScreen();
            }
        });

        vetButton = new JButton("Take To Vet");
        vetButton.setBounds(450, 480, 150, 60);
        vetButton.setFocusPainted(false);
        vetButton.setBackground(navyBlue);
        vetButton.setForeground(Color.WHITE);
        vetButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        vetButton.setFont(new Font("Inter", Font.BOLD, 14));
        uiCircle.add(vetButton);
        vetButton.addActionListener(e -> {
            int increaseHealth = pet.takeVet();
            scoreLabel.setText("<html><div style='text-align: center;'>Score:\n" + "<br>" + pet.getPoints() + "</div></html>");
            
            if (increaseHealth >= 0) {
                showMessage("You took " + pet.getName() + " to the vet. " + pet.getName() + "'s health increased by " + increaseHealth + ".");
            } else if (pet.getState().equals("dead")) {
                showMessage(pet.getName() + " has died.");
            } else if (pet.getState().equals("angry")) {
                showMessage(pet.getName() + " is angry! Please increase its happiness first by playing with " + pet.getName() +" or giving it a gift!");
            } else if (pet.getState().equals("sleep")) {
                showMessage(pet.getName() + " is asleep! Please wait for " + pet.getName() + " to wake up first before taking it to the vet.");
            } else {
                showMessage("Vet is on cooldown. Please wait for it to be available again.");
            }
        });


        playButton = new JButton("Play");
        playButton.setBounds(620, 480, 150, 60);
        playButton.setFocusPainted(false);
        playButton.setBackground(navyBlue);
        playButton.setForeground(Color.WHITE);
        playButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        playButton.setFont(new Font("Inter", Font.BOLD, 14));
        uiCircle.add(playButton);
        playButton.addActionListener(e -> {
            int increaseHappiness = pet.play();
            // change the text based on cooldown (returns negative numbers, etc.)
            scoreLabel.setText("<html><div style='text-align: center;'>Score:\n" + "<br>" + pet.getPoints() + "</div></html>");
            if (increaseHappiness >= 0) {
                // change
                showMessage(pet.getName() + " played a game. " + pet.getName() + "'s happiness increased by " + increaseHappiness + ".");
            } else if (pet.getState().equals("dead")) {
                showMessage(pet.getName() + " has died.");
            } else if (pet.getState().equals("sleep")) {
                showMessage(pet.getName() + " is asleep! Please wait for " + pet.getName() + " to wake up first before playing.");
            } else {
                showMessage("Play is on cooldown. Please wait for it to be available again.");
            }
        });
        

        exerciseButton = new JButton("Exercise");
        exerciseButton.setBounds(790, 480, 150, 60);
        exerciseButton.setFocusPainted(false);
        exerciseButton.setBackground(navyBlue);
        exerciseButton.setForeground(Color.WHITE);
        exerciseButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        exerciseButton.setFont(new Font("Inter", Font.BOLD, 14));
        uiCircle.add(exerciseButton);
        exerciseButton.addActionListener(e -> {
            int increaseStats = pet.exercise(inventory);
            scoreLabel.setText("<html><div style='text-align: center;'>Score:\n" + "<br>" + pet.getPoints() + "</div></html>");
            String inventoryMsg = inventory.getMessage(pet.getName());
            // change the text based on cooldown (returns negative numbers, etc.)
            if (increaseStats >= 0) {
                // change
                showMessage("You took " + pet.getName() + " out for a walk! " + pet.getName() + " ran all around the neighbourhood. " + pet.getName() + "'s health increased by 25. Sleep and fullness decreased by 10.\n " + inventoryMsg);
            } else if (pet.getState().equals("dead")) {
                showMessage(pet.getName() + " has died.");
            } else if (pet.getState().equals("angry")) {
                showMessage(pet.getName() + " is angry! Please increase its happiness first by playing with" + pet.getName() +" or giving it a gift!");
            } else if (pet.getState().equals("sleep")) {
                showMessage(pet.getName() + " is asleep! Please wait for " + pet.getName() + " to wake up first before exercising.");
            } else {
                showMessage("Exercise is on cooldown. Please wait for it to be available again.");
            }
        });
        

       JPanel settingsPanel = new JPanel() {
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

        settingsPanel.setBounds(850, 20, 60, 60);
        settingsPanel.setOpaque(false);
        settingsPanel.setLayout(new BorderLayout());
        uiCircle.add(settingsPanel);

        JButton settingsButton = new JButton();
        settingsButton.setBounds(850, 20, 60, 60);
        settingsButton.setFocusPainted(false); 
        settingsButton.setContentAreaFilled(false);
        settingsButton.setBorder(null);
        settingsButton.setOpaque(false); 
        uiCircle.add(settingsButton);
        settingsButton.addActionListener(e -> gm.showSettingsScreen());

        JLabel setBgLabel = new JLabel();
        ImageIcon setUnscaled = new ImageIcon(getClass().getClassLoader().getResource("assets/SettingsIcon.png"));
        Image setScaledImage = setUnscaled.getImage().getScaledInstance(55, 50, Image.SCALE_SMOOTH);
        ImageIcon setBackground = new ImageIcon(setScaledImage);
        setBgLabel.setIcon(setBackground);
        settingsPanel.add(setBgLabel);


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

        SwingUtilities.invokeLater(() -> setupGameplayShortcuts());
    }

    private void setupGameplayShortcuts() {
        JRootPane rootPane = SwingUtilities.getRootPane(this);
        if (rootPane == null) {
            return;
        }
    
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = rootPane.getActionMap();
    
        inputMap.put(KeyStroke.getKeyStroke("control I"), "openInventory");
        inputMap.put(KeyStroke.getKeyStroke("control P"), "playAction");
        inputMap.put(KeyStroke.getKeyStroke("control V"), "takeToVet");
        inputMap.put(KeyStroke.getKeyStroke("control E"), "exercisePet");
        inputMap.put(KeyStroke.getKeyStroke("control Z"), "sleepPet");
    
        actionMap.put("openInventory", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { gm.showInventoryScreen(); }
        });
        actionMap.put("playAction", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { playButton.doClick(); }
        });
        actionMap.put("takeToVet", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { vetButton.doClick(); }
        });
        actionMap.put("exercisePet", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { exerciseButton.doClick(); }
        });
        actionMap.put("sleepPet", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { bedButton.doClick(); }
        });
    }

    public void createPetGIF(String GIFFileName) {
        String path = "src/spriteGifs/" + GIFFileName;
        File file = new File(path);
        ImageIcon petGIF = new ImageIcon(path);
    
        // removing any previous icon on label
        GIFLabel.setIcon(null);

        // add the new icon to the label
        GIFLabel.setIcon(petGIF);
        GIFPanel.setBounds(430, 250, 120, 120);
        GIFPanel.setOpaque(false);
        GIFPanel.add(GIFLabel);
        uiCircle.add(GIFPanel);
    
        uiCircle.revalidate();
        uiCircle.repaint();
    }

    public void createPetPortrait(String portraitFileName) {
        String path = "src/portraits/" + portraitFileName;
        File file = new File(path);

        try {
            image = ImageIO.read(file);
            petPanel.repaint();  // Redraw the panel with the new image
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    @Override
    public void onStateChanged(String gifFileName, String portraitFileName) {
        createPetGIF("furfrou" + gifFileName);
        createPetPortrait("furfrou" + portraitFileName);
    }
    
}