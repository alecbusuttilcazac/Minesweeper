package com.alexanderbusuttilcazac;

import java.awt.*;
import javax.swing.ImageIcon;

class BombTile extends Tile {    
    protected BombTile(String exploredChar) {
        super(exploredChar);
    }
    
    protected int getBombsAroundTile() {
        return -1; // BombTile does not have any bombs around it, as it is a bomb itself
    }
    
    protected void setExplored(Player player, Color colour) {
        // tile can not be unexplored
        if (!isExplored) {
            button.setBackground(colour);
            isExplored = true;
            player.adjustScore(-300);
            button.setIcon(new ImageIcon(getClass().getResource("/images/explosion.png")));
        }
    }
    
    protected void toggleFlagged(Player player, String flagChar) {
        if (isFlagged) {
            isFlagged = false;
            player.adjustScore(-50);
            button.setIcon(null);
        }else{
            isFlagged = true;
            player.adjustScore(50);
            button.setIcon(new ImageIcon(getClass().getResource("/images/flag.png")));
        }
    }
}