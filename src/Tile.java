import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.metal.*;

public abstract class Tile{
    JButton button = new JButton();
    
    boolean isExplored = false;
    boolean isFlagged = false;
    boolean lightDark; // true for light colour, false for dark colour.
    String exploredChar;
    
    // Constructor
    Tile(String exploredChar){
        this.exploredChar = exploredChar;
        button.setUI(new MetalButtonUI(){protected void paintButtonPressed(Graphics g, AbstractButton b){}});
    }
    
    // Getters
    JButton getButton()     { return button; }
    boolean getExplored()   { return isExplored; }
    boolean getLightDark()  { return lightDark; }
    
    // Setters
    void setExploredCharacter(String exploredChar)  { this.exploredChar = exploredChar; }
    void setLightDark(boolean lightDark)            { this.lightDark = lightDark; }
    void setButton(Color clr){
        SwingEditor.Button(button, "", Color.black, clr, 20);
        button.setBorder(null);
    }
    
    abstract void setExplored(Player p, Color c);
    abstract void toggleFlagged(Player p, String flagChar);
    abstract int getBombsAroundTile();
}
