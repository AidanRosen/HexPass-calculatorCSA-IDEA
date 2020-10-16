package view_control;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import util.Mathstuff.OPERATOR;
//Because the class had to be renamed to enable exponent operations, this must also be changed

public class CalculatorUI extends JFrame {
	private final JLabel calcArea = new JLabel("");

	private boolean initialCalcAreaInputState;
	private enum STATE { INITIAL, SAVE1, SAVE2, CALC }
	//restricts all the values STATE can be by enumerating it as the above 4

	private STATE mathState;
	
	// calculator values
    private OPERATOR mathOp;
	private double arg1;
    private double arg2;
    private double calcAnswer;
    //Privates indicate that these can only be used in this class. Therefore, these are NOT the same
	//as the parameters in Mathstuff
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				CalculatorUI frame = new CalculatorUI();
				//calls the constructor and creates a window
				frame.setVisible(true);
				//sets the window to being seen (visisble obviously)
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	private void calculateAnswer()  // method to perform calculation
	{
	    calcAnswer = util.Mathstuff.calculateIt(arg1, mathOp, arg2);
	    //mathOp is how the program knows which operation to perform.
		calcArea.setText(String.valueOf(calcAnswer));
		//calcAnswer is set to the returned value above. String.valueOf converts a numerical value into a string instead of just a long string
	    arg1 = Double.parseDouble(calcArea.getText());
	    //parseDouble returns the Double value of a string argument, meaning you get a decimal value from a string input
	    mathState = STATE.CALC;
	    //CALC is the last mathState made in the enumerator so this indicates calculation can now take place
		initialCalcAreaInputState = true;
	}
	
	private void updateCalcArea(String string) {
		//The parameter "String string" allows strings to be put in here and then update that part of the
		//GUI on the calculator
		if (initialCalcAreaInputState) {  // sets text to string on initial key typed
			calcArea.setText(string);
			initialCalcAreaInputState = false;
			//set to false to avoid infinite loop
	    } else  {                         // concatenates string to end of text subsequent keys typed
			calcArea.setText(calcArea.getText() + string);
			//calcArea.getText() will just set it to the default and the string in the parameter
			//				As Mr. Mortensen placed in his comment above.
			//				Note that, since it's concatenation, the strings just get put on the ends together
			//				And not being mathematically added
			//We use /getText() because after buttons are clicked, the text in the calcArea changes.
	    }
	}
	
	/**
	 * Save values for Calculator.
	 */
	private void saveValueOfArg1() { // method to store 1st value in calculation (arg1)
	    arg1 = Double.parseDouble((calcArea.getText()));
	    mathState = STATE.SAVE1;
		initialCalcAreaInputState = true;
	}
	
	private void saveValueOfArg2() { // method to store 2nd value in calculation (arg2)
		if (mathState != STATE.CALC) {
			//This is important because it avoids resetting the second number while calculation goes on
			arg2 = Double.parseDouble((calcArea.getText()));
			//This means that whatever number is in the top "screen" of the calculator becomes Arg2 which is
			//important to use while NOT in .CALC because whatever calculated number is made should be arg1 and
			//not 2
			mathState = STATE.SAVE2;
		}
	}
	
	private void saveValueOfMathOp(OPERATOR op) { // method to store operator
		mathOp = op;
	}
	
	private void clearCalculator() {  // helper method to clear and update calculator to default
		// calculator control
		String calcAreaDefault = "0.0";
		calcArea.setText(calcAreaDefault);
		mathState = STATE.INITIAL; //avoid issues with starting on Arg1
		initialCalcAreaInputState = true;
		arg1 = 0.0;
		arg2 = 0.0;
		calcAnswer = 0.0;
		//reset everything for thoroughness
	}

	/**
	 * Create the frame.
	 */
	public CalculatorUI() {
		//this is a constructor and we know that because it's named the same as the file
		getContentPane().setBackground(new Color(175, 238, 238));
		//Sets background color
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 418, 315);
		getContentPane().setLayout(null);
		//Empties current layout
		calcArea.setForeground(Color.WHITE);
		calcArea.setFont(new Font("Lucida Grande", Font.PLAIN, 72));
		calcArea.setHorizontalAlignment(SwingConstants.RIGHT);
		calcArea.setBounds(18, 6, 377, 67);
		getContentPane().add(calcArea);
		
		JButton button_1 = new JButton("1");
		//Text for the number "1". Use this as a template for other buttons to use
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
		button_1.setBorder(new MatteBorder(4, 4, 4, 4, Color.WHITE));
		button_1.setOpaque(true);
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(Color.PINK);
		button_1.addActionListener(e -> updateCalcArea(button_1.getText()));
		//
		button_1.setBounds(35, 86, 75, 40);
		//Sets location at (35,86)
		getContentPane().add(button_1);
		
		JButton button_2 = new JButton("2");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_2.setBackground(Color.WHITE);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				button_2.setBackground(Color.PINK);
			}
		});
		button_2.addActionListener(e -> updateCalcArea(button_2.getText()));
		button_2.setOpaque(true);
		button_2.setForeground(Color.WHITE);
		button_2.setBorder(new MatteBorder(4, 4, 4, 4, Color.WHITE));
		//This sets a border around the button of the type MatterBorder.
		//			MatterBorder means the top is 4 pixels high, the bottom is 4 pixels low, and so on
		button_2.setBackground(Color.PINK);
		button_2.setBounds(122, 86, 75, 40);
		//Notice how button 2 is 87 pixels to the right on the x axis but has the same y value as button 1 (35, 86) and (122,86)
		getContentPane().add(button_2);
		//Be sure to actually add it to the JFrame
		
		JButton button_3 = new JButton("3");
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_3.setBackground(Color.WHITE);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				button_3.setBackground(Color.PINK);
			}
		});
		button_3.addActionListener(e -> updateCalcArea(button_3.getText()));
		button_3.setOpaque(true);
		button_3.setForeground(Color.WHITE);
		button_3.setBorder(new MatteBorder(4, 4, 4, 4, Color.WHITE));
		button_3.setBackground(Color.PINK);
		button_3.setBounds(209, 86, 75, 40);
		getContentPane().add(button_3);
		
		JButton button_4 = new JButton("4");
		button_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_4.setBackground(Color.WHITE);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				button_4.setBackground(Color.PINK);
			}
		});
		button_4.addActionListener(e -> updateCalcArea(button_4.getText()));
		button_4.setOpaque(true);
		button_4.setForeground(Color.WHITE);
		button_4.setBorder(new MatteBorder(4, 4, 4, 4, Color.WHITE));
		button_4.setBackground(Color.PINK);
		button_4.setBounds(35, 138, 75, 40);
		getContentPane().add(button_4);
		
		JButton button_5 = new JButton("5");
		button_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_5.setBackground(Color.WHITE);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				button_5.setBackground(Color.PINK);
			}
		});
		button_5.addActionListener(e -> updateCalcArea(button_5.getText()));
		button_5.setOpaque(true);
		button_5.setForeground(Color.WHITE);
		button_5.setBorder(new MatteBorder(4, 4, 4, 4, Color.WHITE));
		button_5.setBackground(Color.PINK);
		button_5.setBounds(122, 138, 75, 40);
		getContentPane().add(button_5);
		
		JButton button_6 = new JButton("6");
		button_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_6.setBackground(Color.WHITE);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				button_6.setBackground(Color.PINK);
			}
		});
		button_6.addActionListener(e -> updateCalcArea(button_6.getText()));
		button_6.setOpaque(true);
		button_6.setForeground(Color.WHITE);
		button_6.setBorder(new MatteBorder(4, 4, 4, 4, Color.WHITE));
		button_6.setBackground(Color.PINK);
		button_6.setBounds(209, 138, 75, 40);
		getContentPane().add(button_6);
		
		JButton button_7 = new JButton("7");
		button_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_7.setBackground(Color.WHITE);
			}
			
			public void mouseReleased(MouseEvent e) {
				button_7.setBackground(Color.PINK);
			}
		});
		button_7.addActionListener(e -> updateCalcArea(button_7.getText()));
		button_7.setOpaque(true);
		button_7.setForeground(Color.WHITE);
		button_7.setBorder(new MatteBorder(4, 4, 4, 4, Color.WHITE));
		button_7.setBackground(Color.PINK);
		button_7.setBounds(35, 190, 75, 40);
		getContentPane().add(button_7);
		
		JButton button_8 = new JButton("8");
		button_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_8.setBackground(Color.WHITE);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				button_8.setBackground(Color.PINK);
			}
		});
		button_8.addActionListener(e -> updateCalcArea(button_8.getText()));
		button_8.setOpaque(true);
		button_8.setForeground(Color.WHITE);
		button_8.setBorder(new MatteBorder(4, 4, 4, 4, Color.WHITE));
		button_8.setBackground(Color.PINK);
		button_8.setBounds(122, 190, 75, 40);
		getContentPane().add(button_8);
		
		JButton button_9 = new JButton("9");
		button_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_9.setBackground(Color.WHITE);
			}
			
			public void mouseReleased(MouseEvent e) {
				button_9.setBackground(Color.PINK);
			}
		});
		button_9.addActionListener(e -> updateCalcArea(button_9.getText()));
		button_9.setOpaque(true);
		button_9.setForeground(Color.WHITE);
		button_9.setBorder(new MatteBorder(4, 4, 4, 4, Color.WHITE));
		button_9.setBackground(Color.PINK);
		button_9.setBounds(209, 190, 75, 40);
		getContentPane().add(button_9);
		
		JButton button_0 = new JButton("0");
		button_0.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_0.setBackground(Color.WHITE);
			}
			
			public void mouseReleased(MouseEvent e) {
				button_0.setBackground(Color.PINK);
			}
		});
		button_0.addActionListener(e -> updateCalcArea(button_0.getText()));
		button_0.setOpaque(true);
		button_0.setForeground(Color.WHITE);
		button_0.setBorder(new MatteBorder(4, 4, 4, 4, Color.WHITE));
		button_0.setBackground(Color.PINK);
		button_0.setBounds(122, 242, 75, 40);
		getContentPane().add(button_0);
		
		JButton button_plus = new JButton("+");
		button_plus.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_plus.setBackground(Color.WHITE);
			}
			
			public void mouseReleased(MouseEvent e) {
				button_plus.setBackground(Color.PINK);
			}
		});
		button_plus.addActionListener(e -> {
			saveValueOfArg1();
			saveValueOfMathOp(OPERATOR.PLUS);
		});
		button_plus.setOpaque(true);
		button_plus.setForeground(Color.WHITE);
		button_plus.setBorder(new MatteBorder(4, 4, 4, 4, Color.WHITE));
		button_plus.setBackground(Color.PINK);
		button_plus.setBounds(327, 138, 75, 40);
		getContentPane().add(button_plus);
		
		JButton button_minus = new JButton("-");
		button_minus.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_minus.setBackground(Color.WHITE);
			}
			
			public void mouseReleased(MouseEvent e) {
				button_minus.setBackground(Color.PINK);
			}
		});
		button_minus.addActionListener(e -> {
			saveValueOfArg1();
			saveValueOfMathOp(OPERATOR.MINUS);
		});
		button_minus.setOpaque(true);
		button_minus.setForeground(Color.WHITE);
		button_minus.setBorder(new MatteBorder(4, 4, 4, 4, Color.WHITE));
		button_minus.setBackground(Color.PINK);
		button_minus.setBounds(327, 190, 75, 40);
		//Notice how the minus button is 190-138 = 52 pixels above the plus button. So, all buttons should be 52 pixels apart
		getContentPane().add(button_minus);
		
		JButton button_equals = new JButton("=");

		//The code below sets the color of the equals sign to changing color when clicked
		button_equals.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_equals.setBackground(Color.WHITE);
			}
			
			public void mouseReleased(MouseEvent e) {
				button_equals.setBackground(Color.PINK);
			}
		});
		button_equals.addActionListener(e -> {
			saveValueOfArg2();
			calculateAnswer();
		});
		button_equals.setOpaque(true);
		button_equals.setForeground(Color.WHITE);
		button_equals.setBorder(new MatteBorder(4, 4, 4, 4, Color.WHITE));
		button_equals.setBackground(Color.PINK);
		button_equals.setBounds(327, 242, 75, 40);
		getContentPane().add(button_equals);
		
		JButton button_clear = new JButton("AC");

		button_clear.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_clear.setBackground(Color.WHITE);
			}
			
			public void mouseReleased(MouseEvent e) {
				button_clear.setBackground(Color.PINK);
			}
			//Adding WHITE allows the color to change
		});
		button_clear.addActionListener(e -> clearCalculator());
		button_clear.setOpaque(true);
		button_clear.setForeground(Color.BLACK);
		button_clear.setBorder(new MatteBorder(4, 4, 4, 4, Color.WHITE));
		button_clear.setBackground(new Color(221, 160, 221));
		button_clear.setBounds(327, 85, 75, 40);
		getContentPane().add(button_clear);


		//Creating the exponent button
		JButton button_exponent = new JButton("^");

		button_exponent.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_exponent.setBackground(Color.GREEN);
			}

			public void mouseReleased(MouseEvent e) {
				button_exponent.setBackground(new Color(244, 17, 95));
				//Changing the color from pink because I want it to go back to the original shade of red
			}
			//Adding WHITE allows the color to change
		});
		button_exponent.addActionListener(e ->{
			saveValueOfArg1();
			saveValueOfMathOp(OPERATOR.EXPONENT);
		});
		//I need to make a function for using exponents.
		button_exponent.setOpaque(true);
		//Sets it to a solidly colored button. I infer that .setTransparent(true) makes it transparent
		button_exponent.setForeground(Color.BLACK);
		button_exponent.setBorder(new MatteBorder(4, 4, 4, 4, Color.WHITE));
		button_exponent.setBackground(new Color(244, 17, 95));
		button_exponent.setBounds(327, 294, 75, 40);
		getContentPane().add(button_exponent);


		JButton button_negative = new JButton("(-)");

		button_negative.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_negative.setBackground(Color.BLACK);
			}

			public void mouseReleased(MouseEvent e) {
				button_negative.setBackground(new Color(57, 255, 20));
				//Changing the color from pink because I want it to go back to the original shade of red
			}
			//Adding WHITE allows the color to change
		});
		button_negative.addActionListener(e ->{
			double negativeVal = Double.parseDouble(calcArea.getText())*-1;
			//Must use get.Text because we want the text not some string from the label itself
			calcArea.setText(String.valueOf(negativeVal));
			System.out.println("Negative change made");
			//need only to set calcArea to negative value to negative text
			//need to get the string value of the long expression, which sets the text to negative by fetching the
			//numerical value of the text first
		});
		//I need to make a function for using exponents.
		button_negative.setOpaque(true);
		//Sets it to a solidly colored button. I infer that .setTransparent(true) makes it transparent
		button_negative.setForeground(Color.BLACK);
		button_negative.setBorder(new MatteBorder(4, 4, 4, 4, Color.WHITE));
		button_negative.setBackground(new Color(57, 255, 20));
		button_negative.setBounds(327, 346, 75, 40);
		getContentPane().add(button_negative);



		
		JLabel lblElliesCalculator = new JLabel("Sample Calculator -- design by Ellie");
		lblElliesCalculator.setBounds(6, 6, 134, 16);
		getContentPane().add(lblElliesCalculator);

	}
}






	
