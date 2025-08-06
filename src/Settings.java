import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Settings{
    static int numBombs;
    static String bombChar;
    static String flagChar;
    static boolean debug;
    
    static String filePath = (System.getProperty("user.dir"))+"\\data\\settings.dat";
    static File file = new File(filePath);
    
    Settings(){
        numBombs = 10;
        bombChar = "💣";
        flagChar = "🚩";
        debug = false;
    }
    
    static int getNumBombs()               {return numBombs;}
    static String getBombChar()            {return bombChar;}
    static String getFlagChar()            {return flagChar;}
    static boolean debug()                 {return debug;}
    
    void setNumBombs(int i)         {numBombs = i;}
    void setBombChar(String i)      {bombChar = i;}
    void setFlagChar(String i)      {flagChar = i;}
    void setDebug(boolean d)        {debug = d;}
    
    
    public static void printSettings(){
        System.out.println("CURRENT SETTINGS:");
        System.out.println("Number of Bombs: "+numBombs);
        System.out.println("Bomb Character: '" +bombChar+ "'");
        System.out.println("Flag Character: '" +flagChar+ "'");
        System.out.println("Debug Status: " +debug);
    }
    
    public static void createFile(){
        try{
            if (file.createNewFile()){
                FileWriter fWriter = new FileWriter(file, true);
                PrintWriter pWriter = new PrintWriter(fWriter);

                pWriter.println("numBombs = 10");
                pWriter.println("bombChar = 💣");
                pWriter.println("flagChar = 🚩");
                pWriter.println("debug = false");

                pWriter.close();
                if(debug) System.out.println("[settings.dat created and initialised successfully]");
            }
            if (file.exists() && debug) System.out.println("(settings.dat could not be created, because it already exists.)");
        }catch(Exception e) {JOptionPane.showMessageDialog(null, "Please check your disk space and try again." +e, "FATAL ERROR", JOptionPane.ERROR_MESSAGE); System.exit(0);}
    }

    public static void displayGUI(JFrame f){
        JPanel p = new JPanel(new GridLayout(5, 1));
        SwingEditor.Panel(f, p, Color.black);

        JButton b1 = new JButton();
        SwingEditor.Button(b1, "Flag Character", Color.black, Color.white, 30);
        p.add(b1);
        b1.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){ //HARD
            
        }});

        JButton b2 = new JButton();
        SwingEditor.Button(b2, "Bomb Character", Color.black, Color.white, 30);
        p.add(b2);

        JButton b3 = new JButton();
        SwingEditor.Button(b3, "Number of Bombs", Color.black, Color.white, 30);
        p.add(b3);

        JButton b4 = new JButton();
        SwingEditor.Button(b4, "DEBUG", Color.black, Color.orange, 30);
        p.add(b4);

        JButton b5 = new JButton();
        SwingEditor.Button(b5, "Exit", Color.white, Color.red, 30);
        p.add(b5);        

        f.repaint();
        f.revalidate();
        p.repaint();
        p.revalidate();

        f.setVisible(true);
    }

    //Writer to settings.dat
    static void write(){
        try{
            new PrintWriter(file).close(); //emptying the file
            if(debug) System.out.println("[settings.dat flushed successfully]");

        }catch (Exception e){JOptionPane.showMessageDialog(null, "Make sure the file is not in use, and try again.\n" +e, "FATAL ERROR", JOptionPane.WARNING_MESSAGE); System.exit(0);}
        try{
            FileWriter fWriter = new FileWriter(file, true);
            PrintWriter pWriter = new PrintWriter(fWriter);

            pWriter.println("numBombs = " +numBombs);
            pWriter.println("bombChar = "+bombChar);
            pWriter.println("flagChar = "+flagChar);
            pWriter.println("debug = "+debug);
            
            pWriter.close();
            if(debug) System.out.println("[settings.dat updated successfully]");
        }catch(Exception e){JOptionPane.showMessageDialog(null, e, "ERROR", JOptionPane.ERROR_MESSAGE);}
    }

    //Reader to settings.dat
    static void read(){
        try{
            Scanner sc = new Scanner(file);
            String SnumBombs = sc.nextLine();
            String SbombChar = sc.nextLine();
            String SflagChar = sc.nextLine();
            String Sdebug = sc.nextLine();
            sc.close();

            if(debug){
                System.out.println("[Read "+SnumBombs+" from line 1]");
                System.out.println("[Read "+SbombChar+" from line 2]");
                System.out.println("[Read "+SflagChar+" from line 4]");
                System.out.println("[Read "+Sdebug+" from line 5]");
            }

            SnumBombs.replace("numBombs = ", "");
            SbombChar.replace("bombChar = ", "");
            SflagChar.replace("flagChar = ", "");
            Sdebug.replace("debug = ", "");

            if(debug){
                System.out.println("Changed SnumBombs to "+SnumBombs);
                System.out.println("Changed SbombChar to "+SbombChar);
                System.out.println("Changed SflagChar to "+SflagChar);
                System.out.println("Changed Sdebug to "+Sdebug);
            }

            if(debug) System.out.println("Converting to Int:");
            try {numBombs = Integer.parseInt(SnumBombs);}
            catch(Exception e) {
                JOptionPane.showMessageDialog(null, "Error when converting mapWidth to int: SnumBombs = "+SnumBombs+ "\n" +e+ "\nDefault settings have been applied.", "ERROR", JOptionPane.ERROR_MESSAGE);
                numBombs = 10;
            }
            bombChar = SbombChar;
            flagChar = SflagChar;
            debug = Boolean.valueOf(Sdebug);

            if(debug) System.out.println("Successfully read settings.");
        }catch (Exception e){JOptionPane.showMessageDialog(null, e, "ERROR", JOptionPane.ERROR_MESSAGE);}
    }
}