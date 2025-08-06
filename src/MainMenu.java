import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu{
    public static SwingEditor swing; // instance of SwingEditor to edit JComponents
    public static Settings setting; // instance of Settings to set/write get/read settings from settings.dat
    
    public static void main(String[] args){
        System.out.println("Welcome to Minesweeper!");
        
        // MENU
        System.out.println("Loading JComponents...");
        JFrame f = new JFrame();
        JPanel p = new JPanel();
        JButton b1 = new JButton();
        JButton b2 = new JButton();
        JButton b3 = new JButton();
        JButton b4 = new JButton();
        JButton b5 = new JButton();
        JButton b6 = new JButton();
        JButton b7 = new JButton();
        JButton b8 = new JButton();
        System.out.print("\033[1F\33[K");
        System.out.println("Loaded JComponents.");
        
        // Frame settings
        System.out.print("\033[1F\33[K");
        System.out.println("Configuring JComponents.");
        SwingEditor.Frame(f, "Minesweeper Menu", 760, 290, 400, 500);
        f.setLocationRelativeTo(null);
        f.setIconImage(new ImageIcon("assets//gameIcon.png").getImage());
        
        SwingEditor.Panel(f, p, Color.black, new GridLayout(8, 1));
        
        // Buttons
        SwingEditor.Button(p, b1, "PLAY GAME",           Color.black, Color.yellow, 30);
        SwingEditor.Button(p, b2, "Leaderboard",         Color.black, Color.white, 30);
        SwingEditor.Button(p, b3, "Settings",            Color.black, Color.white, 30);
        SwingEditor.Button(p, b4, "4",                   Color.black, Color.white, 30);
        SwingEditor.Button(p, b5, "5",                   Color.black, Color.white, 30);
        SwingEditor.Button(p, b6, "Import Settings",     Color.black, Color.gray, 30);
        SwingEditor.Button(p, b7, "Import Leaderboard",  Color.black, Color.gray, 30);
        SwingEditor.Button(p, b8, "EXIT",                Color.white, Color.red, 30);
        System.out.print("\033[1F\33[K");
        System.out.println("Configured JComponents.");
        
        //Actionlisteners to wait for button presses
        
        // PLAY GAME
        System.out.println("Loading MouseListeners... [ 0%]");
        b1.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){ //PLAY GAME BUTTON
                System.out.println("Selected button 1");
                f.remove(p);
                f.setVisible(false);
                System.out.println("Entered playGame() with parameters "+10+", "+10+", "+15);
                Minesweeper minesweeper = new Minesweeper();
                f.repaint();
                f.revalidate();
                f.add(p);
                f.setVisible(true);
                f.repaint();
                f.revalidate();
            }
        });
        System.out.print("\033[1F\33[K");
        System.out.println("Loading MouseListeners... [12%]");
        
        // Leaderboard
        b2.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){ //Leaderboard BUTTON
                System.out.println("Selected button 2");
                f.setVisible(false);
                f.setVisible(true);
            }
        });
        System.out.print("\033[1F\33[K");
        System.out.println("Loading MouseListeners... [25%]");
        
        // Settings
        b3.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){ //Settings BUTTON
                System.out.println("Selected button 3");
                f.remove(p);
                Settings.displayGUI(f);
            }
        });
        System.out.print("\033[1F\33[K");
        System.out.println("Loading MouseListeners... [37%]");
        
        // <Button 4>
        b4.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                System.out.println("Selected button 4");
                f.setVisible(false);
                f.setVisible(true);
            }
        });
        
        // <Button 5>
        System.out.print("\033[1F\33[K");
        System.out.println("Loading MouseListeners... [50%]");
        b5.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                System.out.println("Selected button 5");
                f.setVisible(false);
                f.setVisible(true);
            }
        });
        System.out.print("\033[1F\33[K");
        System.out.println("Loading MouseListeners... [62%]");
        
        // Import Settings
        b6.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){ //Import Settings BUTTON
                System.out.println("Selected button 6");
                f.setVisible(false);
                f.setVisible(true);
            }});
        System.out.print("\033[1F\33[K");
        System.out.println("Loading MouseListeners... [75%]");
        
        // Import Leaderboard
        b7.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){ //Import Leaderboard BUTTON
                System.out.println("Selected button 7");
                f.setVisible(false);
                f.setVisible(true);
            }
        });
        System.out.print("\033[1F\33[K");
        System.out.println("Loading MouseListeners... [87%]");
        
        // Exit Game
        b8.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){ //EXIT BUTTON
                System.out.println("Selected button 8");
                f.setVisible(false);
                System.exit(0);
            }
        });
        System.out.print("\033[1F\33[K");
        System.out.println("Loaded MouseListeners.");
        
        // Finalize Frame
        f.repaint();
        f.revalidate();
        f.setVisible(true);
        System.out.println("Showing window.\nWaiting for user input...");
    }
}
