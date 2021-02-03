/**
 * 
 */
package org.bawaweb.fractals;

/**
 * @author Navroz
 *
 */
public class FractalOptions {
	
	private String fractalChoice;
	
	private String colorChoiceStrategy;
	private String colorStrategyType;
	
	private String xTransformation = "None";		//	Power,Trig,Exponential,Reciprocal/etc
	private String yTransformation = "None";		//	Power,Trig,Exponential,Reciprocal/etc
	private String xyOperation = "Plus";			//	Plus,Minus,Divides,Multiply,Power
	
	private String zTransformation = "None";		//	Power,Trig,Exponential,Reciprocal/etc
	private String cTransformation = "None";		//	Power,Trig,Exponential,Reciprocal/etc
	private String zcOperation = "Plus";		//	Plus,Minus,Divides,Multiply,Power
	
	//	if z=C then isDynamicC, or Mandelbrot
	// 	if C is a constant. applicable to all, then not isDynamicC, or Julia
	private boolean isDynamicC = true;	
	private double realConstant=0.25;
	private double imagConstant=0.0;
	
	//	focal center points of the image generated
	//	NOT of the image displayed
	private double xCenter = 0.0;//this.fractalChoice.equals("Mandelbrot")?-0.5:0.0;
	private double yCenter = 0.0;
	
	//		Range
	private double x_min_val;
	private double y_min_val;
	private double x_max_val;
	private double y_max_val;
	
	private double exponent = 2;
	private int maxIter = 500;
	private double boundary = 2.0;
	private double scaleSize = 2.0;
	
	private String calculationTypeChoice = "Normal";
	
	public FractalOptions(String type) {
		this.fractalChoice = type;
		this.xCenter = this.fractalChoice.equals("Mandelbrot") ? -0.5 : 0.0;
		this.yCenter = 0.0;
		
	}

	public FractalOptions() {
	}



	public String getFractalChoice() {
		return this.fractalChoice;
	}



	public void setFractalChoice(String choice) {
		this.fractalChoice = choice;
	}



	public String getColorChoiceStrategy() {
		return this.colorChoiceStrategy;
	}



	public void setColorChoiceStrategy(String strategy) {
		this.colorChoiceStrategy = strategy;
	}



	public String getColorStrategyType() {
		return this.colorStrategyType;
	}



	public void setColorStrategyType(String type) {
		this.colorStrategyType = type;
	}



	public String getxTransformation() {
		return this.xTransformation;
	}



	public void setxTransformation(String transformation) {
		this.xTransformation = transformation;
	}



	public String getyTransformation() {
		return this.yTransformation;
	}



	public void setyTransformation(String transformation) {
		this.yTransformation = transformation;
	}



	public String getXyOperation() {
		return this.xyOperation;
	}



	public void setXyOperation(String operation) {
		this.xyOperation = operation;
	}



	public String getzTransformation() {
		return this.zTransformation;
	}



	public void setzTransformation(String zTransformation) {
		this.zTransformation = zTransformation;
	}



	public String getcTransformation() {
		return cTransformation;
	}



	public void setcTransformation(String cTransformation) {
		this.cTransformation = cTransformation;
	}



	public String getZcOperation() {
		return this.zcOperation;
	}



	public void setZcOperation(String operation) {
		this.zcOperation = operation;
	}



	public boolean isDynamicC() {
		return this.isDynamicC;
	}



	public void setDynamicC(boolean isDynamic) {
		this.isDynamicC = isDynamic;
	}



	public double getRealConstant() {
		return this.realConstant;
	}



	public void setRealConstant(double rConstant) {
		this.realConstant = rConstant;
	}



	public double getImagConstant() {
		return this.imagConstant;
	}



	public void setImagConstant(double iConstant) {
		this.imagConstant = iConstant;
	}



	public double getxCenter() {
		return this.xCenter;
	}



	public void setxCenter(double xCtr) {
		this.xCenter = xCtr;
	}



	public double getyCenter() {
		return this.yCenter;
	}



	public void setyCenter(double yCtr) {
		this.yCenter = yCtr;
	}



	public double getX_min_val() {
		return this.x_min_val;
	}



	public void setX_min_val(double x_min) {
		this.x_min_val = x_min;
	}



	public double getY_min_val() {
		return this.y_min_val;
	}



	public void setY_min_val(double y_min) {
		this.y_min_val = y_min;
	}



	public double getX_max_val() {
		return this.x_max_val;
	}



	public void setX_max_val(double x_max) {
		this.x_max_val = x_max;
	}



	public double getY_max_val() {
		return this.y_max_val;
	}



	public void setY_max_val(double y_max) {
		this.y_max_val = y_max;
	}



	public double getExponent() {
		return this.exponent;
	}



	public void setExponent(double exp) {
		this.exponent = exp;
	}



	public int getMaxIter() {
		return this.maxIter;
	}



	public void setMaxIter(int max) {
		this.maxIter = max;
	}



	public double getBoundary() {
		return this.boundary;
	}



	public void setBoundary(double bdry) {
		this.boundary = bdry;
	}



	public double getScaleSize() {
		return this.scaleSize;
	}



	public void setScaleSize(double size) {
		this.scaleSize = size;
	}



	public String getCalculationTypeChoice() {
		return this.calculationTypeChoice;
	}



	public void setCalculationTypeChoice(String calculationTypeChoice) {
		this.calculationTypeChoice = calculationTypeChoice;
	}
	
	
	

}
