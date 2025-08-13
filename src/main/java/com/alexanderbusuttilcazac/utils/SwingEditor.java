package com.alexanderbusuttilcazac.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingEditor{
    //JFrame
    public static void Frame(JFrame f, String frameTitle, int locationx, int locationy, int sizex, int sizey){
        f.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){System.exit(0);}});
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setTitle(frameTitle);
        f.setLocation(locationx, locationy);
        f.setSize(sizex, sizey);
    }
    public static void Frame(JFrame f, String frameTitle, int sizex, int sizey){
        f.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){System.exit(0);}});
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setTitle(frameTitle);
        f.setSize(sizex, sizey);
    }
    public static void Frame(JFrame f, String frameTitle){
        f.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){System.exit(0);}});
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setTitle(frameTitle);
    }
    
    //JPanel
    public static void Panel(JFrame f, JPanel p, Color background, LayoutManager layout){
        p.setBackground(background);
        p.setLayout(layout);
        f.add(p);
    }
    public static void Panel(JPanel p, Color background, LayoutManager layout){
        p.setBackground(background);
        p.setLayout(layout);
    }
    public static void Panel(JFrame f, JPanel p, Color background){
        p.setBackground(background);
        f.add(p);
    }
    
    //JLabel
    public static void Label(JPanel p, JLabel l, String text, Color foreground, int size, boolean centerAlign){
        l.setText(text);
        if(centerAlign) l.setHorizontalAlignment(SwingConstants.CENTER);
        l.setFont(new Font("verdana", 0, size));
        l.setForeground(foreground);
        p.add(l);
    }
    public static void Label(JPanel p, JLabel l, String text, Color foreground, int size, int locationx, int locationy){
        l.setText(text);
        l.setLocation(locationx, locationy);
        l.setFont(new Font("verdana", 0, size));
        l.setForeground(foreground);
        p.add(l);
    }
    public static void Label(JPanel p, JLabel l, String text, Color foreground, int size){
        l.setText(text);
        l.setFont(new Font("verdana", 0, size));
        l.setForeground(foreground);
        p.add(l);
    }
    
    //JButton
    public static void Button(JPanel p, JButton b, String text, Color foreground, Color background, int textSize, int buttonSize){
        b.setText(text);
        b.setFont(new Font("verdana", 0, textSize));
        b.setBackground(background);
        b.setForeground(foreground);
        b.setSize(new Dimension(buttonSize, buttonSize));
        p.add(b);
    }
    public static void Button(JPanel p, JButton b, String text, Color foreground, Color background, int textSize){
        b.setText(text);
        b.setFont(new Font("verdana", 0, textSize));
        b.setBackground(background);
        b.setForeground(foreground);
        p.add(b);
    }
    public static void Button(JButton b, String text, Color foreground, Color background, int textSize){
        b.setText(text);
        b.setFont(new Font("verdana", 0, textSize));
        b.setBackground(background);
        b.setForeground(foreground);
    }
}