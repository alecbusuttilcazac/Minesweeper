import java.awt.*;
import javax.swing.ImageIcon;

public class BombTile extends Tile {    
    BombTile(String exploredChar) {
        super(exploredChar);
    }
    
    int getBombsAroundTile() {
        return 9; // BombTile does not have any bombs around it, as it is a bomb itself
    }
    
    void setExplored(Player player, Color colour) {
        // tile can not be unexplored
        if (!isExplored) {
            button.setBackground(colour);
            isExplored = true;
            player.adjustScore(-300);
            button.setIcon(new ImageIcon("assets/explosion.png"));
        }
    }
    
    void toggleFlagged(Player player, String flagChar) {
        if (isFlagged) {
            isFlagged = false;
            player.adjustScore(-50);
            button.setIcon(null);
        }else{
            isFlagged = true;
            player.adjustScore(50);
            button.setIcon(new ImageIcon("assets/flag.png"));
        }
    }
}