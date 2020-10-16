package util;
//What is the above for? What does package util do?
public class Mathstuff {
	
	public static enum OPERATOR { NOOP, PLUS, MINUS, DIVIDE, MULTIPLY, EXPONENT};
	public static double calculateIt(double arg1, OPERATOR mathOp, double arg2) {
		//The parameters above are named exactly like in CalculatorUI, which can be confusing
		//However, just know that those are the parameters above AND, because it's public static, it can be used anywhere
		//so the method can be called in CalculatorUI
		double calcAnswer;		
		switch(mathOp)
	    {
			case EXPONENT:
				//format Math.pow(number, power)
				//Now, we can use exponent math
				calcAnswer = Math.pow(arg1,  arg2);
				break;
	        case PLUS:
	            calcAnswer = arg1 + arg2;
	            break;
	        case MINUS:
	            calcAnswer = arg1 - arg2;
	            break;
	        case DIVIDE:
	            calcAnswer = arg1 / arg2;
	            break;
	        case MULTIPLY:
	            calcAnswer = arg1 * arg2;
	            break;
	        case NOOP:
	        default:	
	            calcAnswer = arg1;
	    }
		return calcAnswer;
	}
	

}
