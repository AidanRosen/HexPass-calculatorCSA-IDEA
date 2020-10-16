package view_control;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    /**
     * Launch the application.
     */
    public static void main(String[] args) { //need a main here so that it actually runs
        EventQueue.invokeLater(() -> {
            try {
                MainMenu frame = new MainMenu(); //How the function below is triggered, by creating a new object
                //Notice that the "type" of object is the same as the name of the class below
                frame.setVisible(true);
            } catch (Exception e) { //e must be a kind of error. "e" represents:
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public MainMenu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //this EXIT_ON_CLOSE means that the JFrame will close on hitting x
        setBounds(100, 100, 450, 300); //This means the width and height are 450 pixels by 300 pixels 

        // JLabel with image
        JLabel pic = new JLabel("");
        //This is used to display text within the JFrame
        //Right now, it only creates the label and will be put somewhere else later
        java.awt.Image image = new ImageIcon("APCompSci.jpeg").getImage();
        //
        pic.setIcon(new ImageIcon(image));
        //OK so now that ImageIcon has been created under the object "image", pic.setIcon sets the icon (duh) for
        //the window (the thing in the upper left, like the intellij icon in this window)
        //Also note that pic is a JLabel as defined above.
        //AND because setIcon is a method of pic ,it does not set the window icon to the image, but rather the labels icon to the image
        pic.setBounds(377, 111, 128, 128);
        //this sets size for the picture AND puts it somewhere within the frame: width adn height determine zie, and the location
        //is defined (the very center of the label) as being at (377, 111)

        // Content Panel to add Label and Image
        //https://docs.oracle.com/javase/tutorial/uiswing/components/rootpane.html
        Container content = getContentPane();
        //Creates a container into which all the stuff can be placed
        content.setBackground(new Color(100,100,255));
        //Sets the color. Note to self (Aidan) this is the same as what we used in HexPass
        content.add(pic);
        //This places the pic (which was defined) above using the .add method on content. When content is set to visible
        //the "pic" will appear

        // Menu Objects
        JMenuBar menuBar = new JMenuBar();
        //Creating a new menuBar as an object that can be operated on
        setJMenuBar(menuBar);
        //Sets the menu bar to the created menuBar

        JMenu mnHone = new JMenu("Home");
        //Creates a home menu
        menuBar.add(mnHone);
        //uses "add" method to place the mnHome on menuBar (so, a home menu is now visible)

        JMenuItem mntmPreferences = new JMenuItem("Preferences");
        //JMenuItem places an item into the menu, and this item is defined as mntmPreferences
        mnHone.add(mntmPreferences);
        //Now it has been added to the menu

        JMenu mnApTest = new JMenu("AP Test");
        //This is a different menu
        menuBar.add(mnApTest);
        //The menu has been added to the bar

        JMenuItem mntmApReview = new JMenuItem("Review");
        mntmApReview.addActionListener(e -> {
            AP_UI frame = new AP_UI();
            frame.setVisible(true);
        });
        //What is e -> ?
        mnApTest.add(mntmApReview);

        JMenu mnJig = new JMenu("Labs");
        //The "Labs" part is the text that actually shows up
        menuBar.add(mnJig);

        JMenuItem mntmCalculator = new JMenuItem("Calculator");
        mntmCalculator.addActionListener(e -> {
            CalculatorUI frame = new CalculatorUI();
            frame.setVisible(true);
        });
        mnJig.add(mntmCalculator);

        JMenuItem mntmMethodSearch = new JMenuItem("File Search");
        mntmMethodSearch.addActionListener(e -> {
            MethodSearchUI frame = new MethodSearchUI();
            frame.setVisible(true);
        });

        JMenuItem mntmPalindrome = new JMenuItem("Palindrome");
        mntmPalindrome.addActionListener(e -> {
            PalindromeUI frame = new PalindromeUI();
            frame.setVisible(true);
        });
        mnJig.add(mntmPalindrome);
        mnJig.add(mntmMethodSearch);

        JMenuItem mntmProgrammingLanguages = new JMenuItem("Model Search");
        mntmProgrammingLanguages.addActionListener(e -> {
            ProgLang_UI frame = new ProgLang_UI();
            frame.setVisible(true);
        });
        mnJig.add(mntmProgrammingLanguages);


    }

}
