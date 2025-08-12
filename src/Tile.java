import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.metal.*;

abstract class Tile{
    JButton button = new JButton();
    
    protected boolean isExplored = false;
    protected boolean isFlagged = false;
    protected boolean lightDark; // true for light colour, false for dark colour.
    protected String exploredChar;
    
    // Constructor
    Tile(String exploredChar){
        this.exploredChar = exploredChar;
        button.setUI(new MetalButtonUI(){protected void paintButtonPressed(Graphics g, AbstractButton b){}});
    }
    
    // Getters
    protected JButton getButton()     { return button; }
    protected boolean getExplored()   { return isExplored; }
    protected boolean getLightDark()  { return lightDark; }
    
    // Setters
    protected void setExploredCharacter(String exploredChar)  { this.exploredChar = exploredChar; }
    protected void setLightDark(boolean lightDark)            { this.lightDark = lightDark; }
    protected void setButton(Color clr){
        SwingEditor.Button(button, "", Color.black, clr, 20);
        button.setBorder(null);
    }
    
    protected abstract void setExplored(Player p, Color c);
    protected abstract void toggleFlagged(Player p, String flagChar);
    protected abstract int getBombsAroundTile();
}
