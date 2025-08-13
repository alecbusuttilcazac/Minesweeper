package com.alexanderbusuttilcazac;

import com.alexanderbusuttilcazac.utils.SwingEditor;
import com.alexanderbusuttilcazac.utils.Utils;
import com.alexanderbusuttilcazac.utils.Counter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main{
    static SwingEditor swing; // instance of SwingEditor to edit JComponents
    static Settings setting; // instance of Settings to set/write get/read settings from settings.dat
    
    public static void main(String[] args){
        System.out.println("Welcome to Minesweeper!");
        
        // Frame
        System.out.println("Loading JFrame...");
        final JFrame frame = new JFrame();
        Utils.clearLine();
        
        // Frame settings
        SwingEditor.Frame(frame, "Minesweeper Menu", 760, 290, 350, 400);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(new ImageIcon("assets//gameIcon.png").getImage());
        System.out.println("Loaded JFrame.");
        
        // Configure main menu
        menuMain(frame);
        
        // Finalize Frame
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
        System.out.println("Showing window.\nWaiting for user input...");
    }
    
    static void menuMain(JFrame frame){
        final JPanel panel = new JPanel();
        final JButton button1 = new JButton();
        final JButton button2 = new JButton();
        final JButton button3 = new JButton();
        final JButton buttonExit = new JButton();
        
        // Configure components
        SwingEditor.Panel(frame, panel, Color.black, new GridLayout(4, 1));
        SwingEditor.Button(panel, button1, "PLAY GAME",             Color.black, Color.yellow, 25);
        SwingEditor.Button(panel, button2, "Leaderboard",           Color.black, Color.white, 25);
        SwingEditor.Button(panel, button3, "Settings",              Color.black, Color.white, 25);
        SwingEditor.Button(panel, buttonExit, "EXIT",               Color.white, Color.red, 25);
        
        // Listeners
        Counter counter = new Counter("Loading JComponents... ", "Loaded JComponents.", 5);
        counter.increment();
        
        button1.addMouseListener(new MouseAdapter(){ // PLAY GAME BUTTON
            public void mousePressed(MouseEvent e){
                System.out.println("Selected button 1");
                frame.remove(panel);
                menuDifficulty(frame);
                frame.revalidate();
                frame.repaint();
            }
        });
        counter.increment();
        
        // Leaderboard
        button2.addMouseListener(new MouseAdapter(){ // LEADERBOARD BUTTON
            public void mousePressed(MouseEvent e){
                System.out.println("Selected button 2");
            }
        });
        counter.increment();
        
        // Settings
        button3.addMouseListener(new MouseAdapter(){ // SETTINGS BUTTON
            public void mousePressed(MouseEvent e){
                System.out.println("Selected button 3");
                frame.remove(panel);
                menuSettings(frame);
                frame.revalidate();
                frame.repaint();
            }
        });
        counter.increment();
        
        // Exit Game
        buttonExit.addMouseListener(new MouseAdapter(){ // EXIT BUTTON
            public void mousePressed(MouseEvent e){
                System.out.println("Selected button 4");
                frame.setVisible(false);
                System.exit(0);
            }
        });
        counter.increment();
    }
    
    static void menuSettings(JFrame frame){
        // JComponents
        JPanel panel = new JPanel();
        JButton buttonBack = new JButton();
        
        // Configure components
        SwingEditor.Panel(frame, panel, Color.black, new GridLayout(4, 2));
        SwingEditor.Button(panel, buttonBack, "Back",   Color.black, Color.yellow, 25);
        
        buttonBack.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){ //Settings BUTTON
                System.out.println("Selected button back");
                frame.remove(panel);
                menuMain(frame);
                frame.revalidate();
                frame.repaint();
            }
        });
    }
    
    static void menuDifficulty(JFrame frame){
        // JComponents
        JPanel panel = new JPanel();
        JButton button1 = new JButton();
        JButton button2 = new JButton();
        JButton button3 = new JButton();
        JButton buttonBack = new JButton();
        
        // Configure components
        SwingEditor.Panel(frame, panel, Color.black, new GridLayout(4, 1));
        SwingEditor.Button(panel, button1, "Easy",      Color.black, new Color(255, 255, 255), 25);
        SwingEditor.Button(panel, button2, "Normal",    Color.black, new Color(200, 200, 200), 25);
        SwingEditor.Button(panel, button3, "Hard",      Color.black, new Color(150, 150, 150), 25);
        SwingEditor.Button(panel, buttonBack, "Back",   Color.black, Color.yellow, 25);
        
        // Listeners
        Counter counter = new Counter("Loading JComponents... ", "Loaded JComponents.", 5);
        counter.increment();
        
        button1.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){ //PLAY GAME BUTTON
                System.out.println("Selected button 1");
                frame.remove(panel);
                frame.setVisible(false);
                new Minesweeper(10, 10, 10);
            }
        });
        counter.increment();
        
        // Leaderboard
        button2.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){ //Leaderboard BUTTON
                System.out.println("Selected button 2");
                frame.remove(panel);
                frame.setVisible(false);
                new Minesweeper(15, 15, 23);
            }
        });
        counter.increment();
        
        // Settings
        button3.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){ //Settings BUTTON
                System.out.println("Selected button 3");
                frame.remove(panel);
                frame.setVisible(false);
                new Minesweeper(20, 20, 40);
            }
        });
        counter.increment();
        
        // Settings
        buttonBack.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){ //Settings BUTTON
                System.out.println("Selected button 4");
                frame.remove(panel);
                menuMain(frame);
                frame.revalidate();
                frame.repaint();
            }
        });
        counter.increment();
        
        frame.revalidate();
        frame.repaint();
    }
}