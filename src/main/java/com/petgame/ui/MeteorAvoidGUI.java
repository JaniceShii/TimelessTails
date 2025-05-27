package com.petgame.ui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * The MeteorAvoidGUI class implements a simple obstacle-dodging game.
 * The player controls a pet sprite that must avoid meteors falling from above.
 * The objective is to survive for a specified amount of time without being hit.
 * 
 * Features:
 * - User-controlled pet movement using arrow keys.
 * - Randomly spawned meteors.
 * - Background and sprite customization based on pet type.
 * - Win condition based on surviving for 15 seconds.
 * - Game Over state handling.
 * 
 * @version 1.0
 * @author Meridith Shang
 */
public class MeteorAvoidGUI extends JFrame {
    static final int GRID_ROWS = 5;  // 5 rows
    static final int GRID_COLS = 5;  // 5 columns
    static final int WINNING_TIME = 15; // 15 seconds to survive
    static final int CELL_SIZE = 125;  // Size of each cell in the grid
    static final char EMPTY = '.';
    static final char PET = 'P';
    static final char METEOR = 'M';

    private char[][] grid = new char[GRID_ROWS][GRID_COLS];
    private int petPosition = GRID_COLS / 2; // Start pet in the middle of the bottom row
    private int timeSurvived = 0;
    private boolean gameOver = false;
    private long startTime;
    private Random random;
    private Timer gameTimer;
    private int[] meteorPositions;

    private Image petImage;
    private Image meteorImage;
    private Image backgroundImage;
    static private String petType;

    /**
     * Constructs a new MeteorAvoidGUI instance with the specified pet type.
     * 
     * @param petType The type of pet selected by the user (e.g., Magearna, Tyrunt, Furfrou).
     */
    public MeteorAvoidGUI(String petType) {
        
        // Set up the window
        setTitle("Dodge the Obstacles");
        setSize(GRID_COLS * CELL_SIZE, GRID_ROWS * CELL_SIZE + 60); // Adjust size to include the last row
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        SwingUtilities.invokeLater(() -> {
            // Request focus on the window
            requestFocus();
        });
        random = new Random();

        // Load images
        this.petType = petType;
        if (petType == "Magearna") {
            petImage = new ImageIcon(getClass().getClassLoader().getResource("com/petgame/assets/magearna_sprite.png")).getImage(); // Load normal pet image
            meteorImage = new ImageIcon(getClass().getClassLoader().getResource("com/petgame/assets/lightning.png")).getImage(); // Load meteor image
            backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("com/petgame/assets/background-factory.jpg")).getImage(); // Load background image
        } else if (petType == "Tyrunt") {
            petImage = new ImageIcon(getClass().getClassLoader().getResource("com/petgame/assets/tyrunt_sprite.png")).getImage(); // Load normal pet image
            meteorImage = new ImageIcon(getClass().getClassLoader().getResource("com/petgame/assets/meteor.png")).getImage(); // Load meteor image
            backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("com/petgame/assets/background_volcano.jpg")).getImage(); // Load background image
        } else if (petType == "Furfrou") {
            petImage = new ImageIcon(getClass().getClassLoader().getResource("com/petgame/assets/furfrou_sprite.png")).getImage(); // Load normal pet image
            meteorImage = new ImageIcon(getClass().getClassLoader().getResource("com/petgame/assets/tennis.png")).getImage(); // Load meteor image
            backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("com/petgame/assets/background-park.jpg")).getImage(); // Load background image
        }

        // Initialize the game grid
        clearGrid();

        // Initialize the meteor positions
        meteorPositions = new int[GRID_COLS];
        for (int i = 0; i < GRID_COLS; i++) {
            meteorPositions[i] = -1;
        }

        // Add key listener for pet movement
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (gameOver) return;

                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT && petPosition > 0) {
                    petPosition--;
                } else if (keyCode == KeyEvent.VK_RIGHT && petPosition < GRID_COLS - 1) {
                    petPosition++;
                }
            }
        });
        setFocusable(true);

        // Use JPanel for painting
        GamePanel gamePanel = new GamePanel();
        add(gamePanel);

        // Start the game
        startGame();
    }

    /**
     * Starts the game timer and initializes gameplay mechanics.
     */
    private void startGame() {
        JOptionPane.showMessageDialog(this, "Avoid the obstacles by using your left and right keys!\nSurvive for 15 seconds to win.");
        startTime = System.currentTimeMillis();
        gameTimer = new Timer(235, e -> {
            if (gameOver) return;

            // Update the grid
            clearGrid();
            spawnMeteors();

            // Check if the pet is hit by a meteor
            if (grid[GRID_ROWS - 1][petPosition] == METEOR) {
                gameOver = true;
                dispose();
            }

            // Update the survival time
            timeSurvived = (int) ((System.currentTimeMillis() - startTime) / 1000);
            if (timeSurvived >= WINNING_TIME) {
                gameOver = true;
                dispose();
                JOptionPane.showMessageDialog(this, "You survived for " + WINNING_TIME + " seconds! You win!");

            }

            // Repaint the grid
            repaint();
        });
        gameTimer.start();
    }

    /**
     * Clears the grid by setting all cells to the EMPTY character.
     */
    private void clearGrid() {
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int col = 0; col < GRID_COLS; col++) {
                grid[row][col] = EMPTY;
            }
        }
    }

    /**
     * Spawns meteors randomly at the top row of the grid and moves them downward.
     */
    private void spawnMeteors() {
        // Move all meteors down by one row
        for (int col = 0; col < GRID_COLS; col++) {
            if (meteorPositions[col] >= 0 && meteorPositions[col] < GRID_ROWS - 1) {
                // Clear previous meteor position
                grid[meteorPositions[col]][col] = EMPTY; 
                meteorPositions[col]++; 
                grid[meteorPositions[col]][col] = METEOR;  // Place the meteor in the new position
            }
        }
    
        // Check if the meteor has reached the bottom row, if so, remove it
        for (int col = 0; col < GRID_COLS; col++) {
            if (meteorPositions[col] == GRID_ROWS - 1) {
                // If meteor is at the bottom row, check for collision with the pet
                if (grid[GRID_ROWS - 1][petPosition] == METEOR) {
                    gameOver = true;
                    dispose();
                    JOptionPane.showMessageDialog(this, "Game Over! You collided with an obstacle.");
                }
                // Despawn meteor (remove it from the grid after reaching the bottom)
                grid[meteorPositions[col]][col] = EMPTY;
                meteorPositions[col] = -1; // Reset meteor position to prevent further movement
            }
        }
    
        // Spawn new meteors at the top row in random columns
        for (int col = 0; col < GRID_COLS; col++) {
            // If there is no meteor in the current column, spawn a new meteor at the top row
            if (meteorPositions[col] == -1 && random.nextInt(7) == 0) { // 1 in 7 chance to spawn meteor
                meteorPositions[col] = 0;  // Place a new meteor at the top row
                grid[0][col] = METEOR;
            }
        }
    }
    

    /**
     * Inner class representing the game panel where rendering is handled.
     */
    private class GamePanel extends JPanel {
        public GamePanel() {
            setPreferredSize(new Dimension(GRID_COLS * CELL_SIZE, (GRID_ROWS + 1) * CELL_SIZE)); // Add space for the bottom row
            setDoubleBuffered(true); // Enable double buffering
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw the entire background image
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

            // Draw the grid with images on top of the background
            for (int row = 0; row < GRID_ROWS; row++) {
                for (int col = 0; col < GRID_COLS; col++) {
                    int x = col * CELL_SIZE;
                    int y = row * CELL_SIZE;

                    if (grid[row][col] == METEOR) {
                        g.drawImage(meteorImage, x, y, CELL_SIZE, CELL_SIZE, this);
                    } else if (row == GRID_ROWS - 1 && col == petPosition) {
                            g.drawImage(petImage, x, y, CELL_SIZE, CELL_SIZE, this); 
                    }
                }
            }

            // Draw the timer at the top left
            String timeText = "Time: " + (15 - timeSurvived) + " s";
            g.setColor(Color.WHITE); // Set text color to white
            g.setFont(new Font("Inter", Font.PLAIN, 25)); // Set font style and size
            g.drawString(timeText, 10, 30); // Draw the text at (10, 30) position
        }
    }
}
