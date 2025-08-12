import java.awt.*;
import javax.swing.ImageIcon;

class EmptyTile extends Tile{
    protected int bombsAroundTile;
    protected EmptyTile(String exploredChar, int bombsAroundTile){
        super(exploredChar);
        this.bombsAroundTile = bombsAroundTile;
    }
    
    protected int getBombsAroundTile()    { return bombsAroundTile; }
    
    protected void setExplored(Player player, Color colour){
        // tile can not be unexplored
        if(!isExplored){
            isExplored = true;
            button.setIcon(null); // remove flag icon if it exists
            
            player.adjustScore(+10);
            button.setBackground(colour);
            button.setText(exploredChar);
        }
    }
    
    protected void toggleFlagged(Player player, String flagChar){
        //tile can be flagged / unflagged
        if(isFlagged){
            isFlagged = false;
            player.adjustScore(-20);
            button.setIcon(null);
        }else{
            isFlagged = true;
            player.adjustScore(20);
            button.setIcon(new ImageIcon("assets//flag.png"));
        }
    }
}