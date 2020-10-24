package view_control;

import util.Mathstuff;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CalculatorControl {

    private boolean initialCalcAreaInputState;
    private enum STATE { INITIAL, SAVE1, SAVE2, CALC }
    //restricts all the values STATE can be by enumerating it as the above 4

    private STATE mathState;

    // calculator values
    private Mathstuff.OPERATOR mathOp;
    private double arg1;
    public double arg2;
    private double calcAnswer;
    //we are porting these variables over so that they can be used for control

    public static void main(String[] args) {
        CalculatorUI calculator = new CalculatorUI();
        calculator.setVisible(true);

        /*
        button_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                button_1.setBackground(Color.WHITE);
            }
            //(MouseEvent e) triggers a method related to pressing the button
            @Override
            public void mouseReleased(MouseEvent e) {
                button_1.setBackground(Color.PINK);
            }
        });
        button_1.addActionListener(e -> updateCalcArea(button_1.getText()));

         */
        //In theory, calculator.button_1 should not cause an error IF button_1 is a variable(?) within the CalculatorUI class
        //However, unlike other variables that can be made public and accessed like arg2 or mathState, these continue to cause an
        //unrecognized



    }

}
