package org.bawaweb.fractals.utils;
/**
 * 
 */


/**
 * @author Navroz
 *
 */
public class FunctionEvaluator {
	
	Expr expression; // The definition of the function f(z).

	ComplexNumber z; // A value of z for which f(z) is to be calculated.
	ComplexNumber val; // The value of f(z) for the specified value of x.

	String type = "Complex"; // other is "Real"

	/////////// for Real /////////////////
	double xR = 0.0; // A value of x for which f(x,y,z) is to be calculated.
	double yR = 0.0; // in case its for lesser dimension
	double zR = 0.0;

	double valR; // The value of f(x,y,z) for the specified value of x,y,z.

	public FunctionEvaluator() {
	}
	
	public double evaluate(String funcString, final double x, final double y, final double z) {
		/*
		 * If number is not a legal number, print an error
		 * message. Otherwise, compute f(x) and return the result.
		 */
		if (x == (Double.NaN) || y == (Double.NaN) || z == (Double.NaN)) {
			/*System.out.println("Err___line2");*/
			return Double.NaN;
		}
		if (funcString == null || funcString.length() == 0) {
			/*System.out.println("Err___line");*/
			return 0.0;
		}
		
		funcString = this.stripWhitespaces(funcString.toLowerCase());
		funcString = funcString.replaceAll("pi", String.valueOf(Math.PI));
		funcString = funcString.replaceAll("π", String.valueOf(Math.PI));
		
		try {
			if (funcString.indexOf('i') == -1) {
				expression = new Expr(funcString, "Real");
			} else {
				return 0.0;// expression = new Expr(this.processForI(funcString),"Complex");
			}
		} catch (IllegalArgumentException e) {
			// An error was found in the input. Print an error message
			expression.error("Error!  The definition of f(x) is not valid.");
			System.out.println(e.getMessage());
		}
		
		try {
			xR = x;
			yR = y;
			zR = z;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("\"" +  "\" is not a legal number.");
		}
		
		valR = expression.value(xR,yR,zR);
		
//		if (Double.isNaN(valR))
//			System.out.println("f("+x+","+y+"," + z + ") is undefined.");
//		else {
//			/* System.out.println("f(" + z + ") = " + val); */}
		return valR;

	}

	public ComplexNumber evaluate(String funcString, final ComplexNumber z0) {
		//	System.out.println("\nf(z) = " + funcString.trim());
		if (funcString.length() == 0) {
			/*System.out.println("Err___line");*/
			return null;
		}

		/*
		 * If complexNumber is not a legal complex number, print an error
		 * message. Otherwise, compute f(x) and return the result.
		 */
		if (z0 == null || z0.isNaN()) {
			/*System.out.println("Err___line2");*/
			return new ComplexNumber(Double.NaN, Double.NaN);
		}
		
		funcString = this.stripWhitespaces(funcString.toLowerCase());
		funcString = funcString.replaceAll("pi", String.valueOf(Math.PI));
		funcString = funcString.replaceAll("π", String.valueOf(Math.PI));

		if (funcString.indexOf("i") > -1) {		//	funcString contains 'i'
			funcString = this.processForI(funcString);
		}
/*System.out.println("After--processfuncString = "+funcString);*/
		try {
			expression = new Expr(funcString);
		} catch (IllegalArgumentException e) {
			// An error was found in the input. Print an error message
			expression.error("Error!  The definition of f(x) is not valid.");
			System.out.println(e.getMessage());
		}
		
		try {
			z = z0;
		} catch (NumberFormatException e) {
			System.out.println("\"" + z0 + "\" is not a legal complexnumber.");
		}
		
		val = expression.value(z);
		
//		if ( Double.isNaN(val.real) || Double.isNaN(val.imaginary) )
//			/*System.out.println("f(" + z + ") is undefined.");*/
//		else {
//			/* System.out.println("f(" + z + ") = " + val); */}
		return val;
	}

	private String processForI(String aString) {
		String[] iSplits = aString.toLowerCase().split("i");
		String funcString = "";
		for (int i = 0; i < iSplits.length; i++) {
			String aSplit = iSplits[i];
			funcString += aSplit;
			if ((aSplit.length() == 0) && (i == 0) && funcString.equals(aSplit)) {
				funcString += "i*";
				continue;
			}

			if (aSplit.length() != 1 && i != iSplits.length - 1) {
				boolean isPrvsCharDigit = this.isLastCharDigit(funcString);
				boolean isPrvsCharCloseBrace = this.isLastCharCloseBrace(funcString);
				if (isPrvsCharDigit || isPrvsCharCloseBrace) {
					funcString += "*i";
					continue;
				}
				boolean isPrvsCharOperator = this.isLastCharOperator(funcString);
				if (isPrvsCharOperator) {
					funcString += "i";
				}
			} else {
				if (i != iSplits.length - 1) {
					boolean isPrvsCharDigit = this.isLastCharDigit(funcString);
					boolean isPrvsCharCloseBrace = this.isLastCharCloseBrace(funcString);
					if (isPrvsCharDigit || isPrvsCharCloseBrace) {

						funcString += "*i";
						continue;

					}
					boolean isPrvsCharOperator = this.isLastCharOperator(funcString);
					if (isPrvsCharOperator) {
						funcString += "i";
					}
				}
			}

		}
		return funcString;
	}

	private boolean isLastCharCloseBrace(String aString) {
		char ch = aString.charAt(aString.length() - 1);
		return ch == ')';
	}

	private boolean isLastCharOperator(String aString) {
		char ch = aString.charAt(aString.length() - 1);
		return (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^');
	}

	private boolean isLastCharDigit(String aString) {
		return Character.isDigit(aString.charAt(aString.length() - 1));
	}
	
	private String stripWhitespaces(String aString) {
		String retStr = "";
		for (int i = 0; i < aString.length(); i++) {
			char ch = aString.charAt(i);
			if (!Character.isWhitespace(ch)) {
				retStr += ch;
			}
		}
		return retStr;
	}

	public static void main(String[] args) {
//		FunctionEvaluator funcEval = new FunctionEvaluator();
//		String formula=null;
//		ComplexNumber compConst = new ComplexNumber(5,-1.5);		
//		ComplexNumber zz = new ComplexNumber(3,4);
		
		/*//1
		String dx_dt = "x*(y+z)";
		String dy_dt = "y*(z+x)";
		String dz_dt = "z*(x+y)";

		double x = 10.1;
		double y = 15.1;
		double z = 0.1;*/
		
		//2	DeJong
		/** a = -2.24, b = 0.43, c = -0.65, d = -2.43 
		 * a = 2.01, b = -2.53, c = 1.61, d = -0.33 
		 * a = -2, b = -2, c = -1.2, d = 2*/
		/*String dx_dt = "sin(-2.24 * y) - cos(0.43 * x)";
		String dy_dt = "sin(-0.65 * x) - cos(-2.43 * y)";
		

		double x = 10.1;
		double y = 15.1;
		double z = 0;

		Tuple3d t3d = new Tuple3d(x, y, z);
		System.out.println("Start" + t3d);

		for (int i = 0; i < 50; i++) {
			x = funcEval.evaluate(dx_dt, t3d);
			y = funcEval.evaluate(dy_dt, t3d);
//			z = funcEval.evaluate(dz_dt, t3d);

			t3d = new Tuple3d(x, y, z);
			System.out.println(i + " => " + t3d);
		}*/
		
		
		/*//3
		String formula = "(z-C)/(z+C)";
		ComplexNumber compConst = new ComplexNumber(5,-1.5);		
		ComplexNumber zz = new ComplexNumber(3,4);
		System.out.println("z is: "+zz);
		System.out.println("C is: "+compConst);
		System.out.println("Formula is:-- "+formula);
		System.out.println("Result is: "+funcEval.evaluate(formula,zz,compConst));*/

		//4		NoNoNo
	/*	formula="z+((π/2)*(1.0+0.6i))";
		System.out.println("z is: "+zz);
		System.out.println("C is: "+compConst);
		System.out.println("Formula is:-- "+formula);
		System.out.println("Result is: "+funcEval.evaluate(formula,zz));*/
		
		/*//5
		formula="exp(z-i)";						//"i((z-i)/(z+i))+1.56i";
		System.out.println("Formula is:-- "+formula);
		String[] iSplits = formula.split("i");
		for(int i = 0; i < iSplits.length; i++){
			System.out.println(""+i+"  ["+iSplits[i]+"]  (isNull=="+(iSplits[i]==null)+")"+"  length=="+iSplits[i].length());
		}
		
		System.out.println("Processed 4 i\n"+funcEval.processForI(formula));
		
		System.out.println("z is: "+zz);
		System.out.println("C is: "+compConst);
		System.out.println("Formula is:-- "+formula);
		System.out.println("Result is: "+funcEval.evaluate(formula,zz));*/
		
		/*//6
		formula = "(1/sqrt(2*Pi))*exp(((-Z)^2)/2)";
		zz = new ComplexNumber("100.0 + 8.597662771285476i");
		System.out.println("(6)z is: "+zz);
		System.out.println("C is: "+compConst);
		System.out.println("Formula is:-- "+formula);
		System.out.println("Result is: "+funcEval.evaluate(formula,zz));
		
			f(z)	(1/sqrt(2*Pi))*exp(((-Z)^2)/2)
		 * f(100.0 + 8.597662771285476i) is undefined.
f(100.0 + 8.564273789649416i) is undefined.
f(100.0 + 8.530884808013356i) is undefined.
f(100.0 + 8.497495826377296i) is undefined.
f(100.0 + 8.464106844741234i) is undefined.*/
		
		
	}

}

