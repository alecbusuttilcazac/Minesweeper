package com.alexanderbusuttilcazac;

import com.alexanderbusuttilcazac.utils.SwingEditor;
import com.alexanderbusuttilcazac.utils.Utils;
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

// Actual minesweeper game class
class Minesweeper{
    Minesweeper(int x, int y, int numBombs){
        final int numExplored[] = {0}; // number of tiles explored (use array for mutability in inner class)
        
        Map map = new Map(x, y, numBombs); // Create a new map with default size and number of bombs
        Player player = new Player();
        
        JFrame gameFrame = new JFrame();
        SwingEditor.Frame(gameFrame, "Minesweeper", 1050, 750);
        gameFrame.setIconImage(new ImageIcon("assets//gameIcon.png").getImage());
        
        JPanel minesweeperPanel = new JPanel();
        SwingEditor.Panel(gameFrame, minesweeperPanel, Color.white, new GridLayout(y, x));
        minesweeperPanel.setPreferredSize(new Dimension(750, 750));
        
        // Secondary panel for information and controls
        JPanel infoPanel = new JPanel();
        SwingEditor.Panel(gameFrame, infoPanel, Color.white);
        infoPanel.setPreferredSize(new Dimension(285, 750));
        
        JLabel infoLabel1 = new JLabel();
        SwingEditor.Label(infoPanel, infoLabel1, "Left CLick: Explore the Tile", Color.black, 15, true);
        JLabel infoLabel2 = new JLabel();
        SwingEditor.Label(infoPanel, infoLabel2, "Right Click: Flag the Tile", Color.black, 15, true);
        JLabel infoLabel3 = new JLabel();
        SwingEditor.Label(infoPanel, infoLabel3, "\n", Color.black, 15);
        JLabel infoLabel4 = new JLabel();
        SwingEditor.Label(infoPanel, infoLabel4, "Number of Explored tiles: " +numExplored[0], Color.black, 15, true);
        
        gameFrame.revalidate();
        gameFrame.repaint();
        infoPanel.revalidate();
        infoPanel.repaint();
        minesweeperPanel.revalidate();
        minesweeperPanel.repaint();
        
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
                        boolean deleteNumExplored = true;
                        
                        if (e.getButton() == MouseEvent.BUTTON1){ // left click
                            if(!map.getTile(i2, j2).getFlagged()){
                                if (map.getTile(i2, j2) instanceof BombTile) {
                                    System.out.println("Bomb clicked at ("+i2+", "+j2+")");
                                    gameFrame.dispose(); // Dispose the game window
                                    
                                    JOptionPane.showMessageDialog(null, "You hit a bomb! Final score: " +player.getScore()+ ".");
                                    SwingUtilities.invokeLater(() -> {
                                        Main.main(null); 
                                    });
                                }else{
                                    Utils.clearLine("> Cluster("+i2+", "+j2+", map, player)");
                                    int clusterSize = Cluster(i2, j2, map, player);
                                    numExplored[0] += clusterSize;
                                    infoLabel4.setText("Number of Explored tiles: " +numExplored[0]);
                                    
                                    infoPanel.revalidate();
                                    infoPanel.repaint();
                                    minesweeperPanel.revalidate();
                                    minesweeperPanel.repaint();
                                    gameFrame.revalidate();
                                    gameFrame.repaint();
                                    
                                    deleteNumExplored = true;
                                    System.out.println("  | returned cluster size = " +clusterSize);
                                    if(deleteNumExplored) System.out.println(" >>>> number of explored tiles = " +numExplored[0]);
                                    
                                    if (x*y - numExplored[0] - numBombs == 0) {
                                        JOptionPane.showMessageDialog(null, "You have successfully cleared the minefield! Final score: " +player.getScore()+ ".");
                                        gameFrame.dispose(); // Dispose the game window
                                        SwingUtilities.invokeLater(() -> {
                                            Main.main(null); 
                                        });
                                    }
                                }
                            }
                        }
                        
                        if (e.getButton() == MouseEvent.BUTTON3){ // right click
                            if(!(map.getTile(i2, j2).getExplored())){ // tile cannot be flagged if it has been explored
                                map.getTile(i2, j2).toggleFlagged(player, Settings.getFlagChar());
                                
                                if(deleteNumExplored){
                                    Utils.clearLine();
                                }
                                System.out.println("> map.getTile("+i2+", "+j2+").toggleFlagged(player, flagChar)");
                                System.out.println(" >>>> number of explored tiles = " +numExplored[0]);
                                deleteNumExplored = false;
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
        // Add the panels to the game frame
        gameFrame.setLayout(new BorderLayout());
        gameFrame.add(minesweeperPanel, BorderLayout.WEST);
        gameFrame.add(infoPanel, BorderLayout.EAST);
        
        gameFrame.revalidate();
        gameFrame.repaint();
        minesweeperPanel.revalidate();
        minesweeperPanel.repaint(); 
        
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
