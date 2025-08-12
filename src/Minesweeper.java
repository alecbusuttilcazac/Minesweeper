import java.awt.*;
// import java.util.*;
import javax.swing.*;
import java.awt.event.*;

// Actual minesweeper game class
class Minesweeper{
    Minesweeper(int x, int y, int numBombs){
        final int numExplored[] = {0}; // number of tiles explored (use array for mutability in inner class)
        
        Map map = new Map(x, y, numBombs); // Create a new map with default size and number of bombs
        Player player = new Player();
        
        JFrame gameFrame = new JFrame();
        SwingEditor.Frame(gameFrame, "Minesweeper", 1200, 800);
        gameFrame.setIconImage(new ImageIcon("assets//gameIcon.png").getImage());
        
        JPanel minesweeperPanel = new JPanel();
        SwingEditor.Panel(gameFrame, minesweeperPanel, Color.white, new GridLayout(y, x));
        minesweeperPanel.setPreferredSize(new Dimension(800, 800));
        
        // Secondary panel for information and controls
        JPanel bottomPanel = new JPanel();
        SwingEditor.Panel(gameFrame, bottomPanel, Color.white);
        bottomPanel.setPreferredSize(new Dimension(250, y*40));
        
        //nested for-loop to output buttons in a grid
        for(int j=0; j<y; j++){
            for(int i=0; i<x; i++){
                boolean lightDark; // true for light colour, false for dark colour.
                
                // Alternate checkerboard pattern for button colours
                if(i%2 == 0){
                    if(j%2 == 0) lightDark = true;
                    else lightDark = false;
                }else{
                    if(j%2 == 0) lightDark = false;
                    else lightDark = true;
                }
                
                // set the button for each tile (with alternate colours)
                if(lightDark){
                    map.getTile(i, j).setButton(new Color(0, 255, 0)); // lime
                    map.getTile(i, j).lightDark = true; // set to light colour
                }else{
                    map.getTile(i, j).setButton(new Color(0, 220, 0)); // green
                    map.getTile(i, j).lightDark = false; // set to dark colour
                }
                
                int i2 = i, j2 = j;
                map
                .getTile(i, j)
                .getButton()
                .addMouseListener(new MouseAdapter(){ // Add listeners for left and right clicks on the button
                    public void mousePressed(MouseEvent e){
                        if (e.getButton() == MouseEvent.BUTTON1){ // left click
                            if (map.getTile(i2, j2) instanceof BombTile) {
                                System.out.println("Bomb clicked at ("+i2+", "+j2+")");
                                
                                gameFrame.dispose(); // Close the game window
                            }else{
                                System.out.println("> Cluster("+i2+", "+j2+", map, player)");
                                int clusterSize = Cluster(i2, j2, map, player);
                                numExplored[0] += clusterSize;
                                minesweeperPanel.repaint();
                                
                                System.out.println("  | returned cluster size = " +clusterSize);
                                System.out.println("  | number of explored tiles = " +numExplored[0]);
                                
                                if (x*y - numExplored[0] - numBombs == 0) {
                                    System.out.println("All non-bomb tiles explored! You win!");
                                    gameFrame.dispose(); // Close the game window
                                }
                            }
                        }
                        
                        
                        
                        
                        
                        if (e.getButton() == MouseEvent.BUTTON3){ // right click
                            if(!(map.getTile(i2, j2).getExplored())){ // tile cannot be flagged if it has been explored
                                map.getTile(i2, j2).toggleFlagged(player, Settings.getFlagChar());
                                System.out.println("> map.getTile("+i2+", "+j2+").toggleFlagged(player, flagChar)");
                                minesweeperPanel.repaint();
                            }
                        }
                    }
                });
                
                // Add the button to the minesweeper panel
                // This will display the buttons in a grid layout
                minesweeperPanel.add(
                    map
                    .getTile(i, j)
                    .getButton()
                );
            }
        }
        
        JLabel l1 = new JLabel();
        SwingEditor.Label(bottomPanel, l1, "Left CLick: Explore the Tile", Color.black, 15);
        JLabel l2 = new JLabel();
        SwingEditor.Label(bottomPanel, l2, "Right Click: Flag the Tile", Color.black, 15);
        
        // Add the panels to the game frame
        gameFrame.setLayout(new BorderLayout());
        gameFrame.add(minesweeperPanel, BorderLayout.WEST);
        gameFrame.add(bottomPanel, BorderLayout.EAST);
        
        gameFrame.repaint();
        gameFrame.revalidate();
        minesweeperPanel.repaint(); 
        minesweeperPanel.revalidate();
        gameFrame.setVisible(true);
        
    }
    
    int Cluster(int x, int y, Map map, Player player){
        int numExplored = 0;
        if (!(map.getTile(x, y) instanceof BombTile) && !(map.getTile(x, y).getExplored())){
            
            if(map.getTile(x, y).getLightDark()) map.getTile(x, y).setExplored(player, new Color(209, 209, 209));
            else map.getTile(x, y).setExplored(player, new Color(170, 170, 170));
            
            if(map.getTile(x, y).getBombsAroundTile() == 0){
                if(x > 0) numExplored += Cluster(x-1, y, map, player);
                if(x < map.sizeX-1) numExplored += Cluster(x+1, y, map, player);
                if(y > 0) numExplored += Cluster(x, y-1, map, player);
                if(y < map.sizeY-1) numExplored += Cluster(x, y+1, map, player);
                
                if(x > 0 && y > 0) numExplored += Cluster(x-1, y-1, map, player);
                if(x < map.sizeX-1 && y < map.sizeY-1) numExplored += Cluster(x+1, y+1, map, player);
                if(x > 0 && y < map.sizeY-1) numExplored += Cluster(x-1, y+1, map, player);
                if(x < map.sizeX-1 && y > 0) numExplored += Cluster(x+1, y-1, map, player);
            }
            return numExplored + 1; // return the number of tiles explored in this cluster
        }
        return numExplored;
    }
    
    boolean isGameOver(Map map){
        
        return true;
    }
    
    
    // void Difficulty(JFrame f, Settings set){
        
    //     JPanel p = new JPanel();
    //     JButton b1 = new JButton();
    //     JButton b2 = new JButton();
    //     JButton b3 = new JButton();
    //     JLabel l = new JLabel();
        
    //     se.Frame(f, "Difficulty Selection");
    //     se.Panel(f, p, Color.black, new GridLayout(4, 1));
    //     se.Label(p, l, "Choose the difficulty", Color.white, 30, true);
    //     se.Button(p, b1, "EASY (10x10)", Cwolor.black, Color.yellow, 30);
    //     se.Button(p, b2, "Normal (15x15)", Color.black, Color.orange, 30);
    //     se.Button(p, b3, "Hard (20x20)", Color.black, Color.red, 30);
        
    //     b1.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){ //EASY
    //         f.setVisible(false);
    //         f.remove(p);
    //         playGame(10, 10, set.getNumBombs());
    //         //playGame(10, 10, set.getNumBombs());
    //     }});
    //     b2.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){ //NORMAL
    //         f.setVisible(false);
    //         f.remove(p);
    //         playGame(15, 15, set.getNumBombs());
    //     }});
    //     b3.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){ //HARD
    //         f.setVisible(false);
    //         f.remove(p);
    //         playGame(20, 20, set.getNumBombs());
    //     }});
        
    //     f.repaint();
    //     f.revalidate();
    //     p.repaint();
    //     p.revalidate();
    // }
    
    void endGame(Player p, JFrame frame){
        
        // frame.removeAll();
        // JPanel endPanel = new JPanel();
        // se.Panel(frame, endPanel, Color.gray, new GridLayout(3, 1));
        // JLabel l1 = new JLabel();
        // se.Label(endPanel, l1, "Good Game!", Color.black, 30, true);
        // JLabel l2 = new JLabel();
        // se.Label(endPanel, l2, "Final Score: "+p.getScore(), Color.black, 20, true);
        // JButton b = new JButton();
        // se.Button(endPanel, b, "Back to MENU", Color.black, Color.yellow, 20);
        // b.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){ //left click
        //     MainMenu.main(null);
        // }});
        
        // frame.repaint();
        // frame.revalidate();
        // endPanel.repaint();
        // endPanel.revalidate();
    }
}
