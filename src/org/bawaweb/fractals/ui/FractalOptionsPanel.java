/**
 * 
 */
package org.bawaweb.fractals.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.bawaweb.fractals.FractalOptions;

/**
 * @author Navroz
 *
 */
public class FractalOptionsPanel extends JPanel {
	
	private static final long serialVersionUID = 12765434L;

	private String fractalChoice = "Mandelbrot";
	
	private static final String[] PIX_TRANSFORM_OPTIONS = { "none", "absolute", "absoluteSquare", "reciprocal",
			"reciprocalSquare", "square", "cube", "root", "exponent", "log(10)", "log(e)", "sine", "cosine", "tan",
			"cosec", "sec", "cot", "sinh", "cosh", "tanh", "arcsine", "arccosine", "arctangent", "arcsinh", "arccosh",
			"arctanh" };

	private JComboBox<String> pxXTransformCombo = new JComboBox<String>(PIX_TRANSFORM_OPTIONS);
	private JComboBox<String> pxYTransformCombo = new JComboBox<String>(PIX_TRANSFORM_OPTIONS);

	private String pixXTransform = "none";
	private String pixYTransform = "none";

//	Pix_XY_Operation
	private JComboBox<String> intraPixOperationCombo = new JComboBox<String>(PIX_INTRA_OPRNS);
	private String pixIntraXYOperation = "Plus";

	//	for	x+iy,	x-iy,	x*iy,	x/iy,	x^y,	y^x
	private static final String[] OPERATIONS = new String[] {"Plus","Minus","Multiply","Divide","PowerX^Y","PowerY^X"};

	//	for	x+iy,	x-iy,	x*iy,	x/iy,	x^y,	y^x
	private static final String[] PIX_INTRA_OPRNS = OPERATIONS;
	
	//	for	z+C, z-C, z*C, z/C, z^C, C^Z
	private static final String[] PIX_CONST_OPRNS = new String[] {"Plus","Minus","Multiply","Divide","PowerZ^C","PowerC^Z"};
	private static final String[] FUNCTION_OPTIONS 		= {"None","sine","cosine","tan","cosec","sec","cot","sinh","cosh","tanh","arcsine","arccosine","arctan","arcsinh","arccosh","arctanh","reciprocal", "reciprocalSquare","square","cube","exponent(e)", "root",/*"cube-root",*/ "log(e)"};
	
	private JComboBox<String> pxFuncCombo = new JComboBox<String>(FUNCTION_OPTIONS);
	private String pxFuncChoice = "None";
	
	// for Constants
	private JComboBox<String> constFuncCombo = new JComboBox<String>(FUNCTION_OPTIONS);
	private String constFuncChoice = "None";

	private JComboBox<String> pxConstOprnCombo = new JComboBox<String>(PIX_CONST_OPRNS);
	private String pxConstOprnChoice = "Plus";

	private JLabel labelRealConst = new JLabel("Constant: Real: ");
	private JTextField realConstTf = new JTextField(5);
	private final JLabel labelImgConst = new JLabel("  Imag:");
	private JTextField imgConstTf = new JTextField(5);

	private JTextField exponentTf = new JTextField(5);
	private JTextField maxIterTf = new JTextField(5);
	private JTextField boundaryTf = new JTextField(5);
	private JTextField xCenterTf = new JTextField(5);
	private JTextField yCenterTf = new JTextField(5);
	private JTextField scaleSizeTf = new JTextField(5);
	
	private JRadioButton useNormalCalcRb = new JRadioButton("Normal", true);
	private JRadioButton useRangeEstCalRb = new JRadioButton("Use RangeEstimation");
	private JRadioButton useCenterCalRb = new JRadioButton("Set Center");
	private ButtonGroup calculationBg = new ButtonGroup();
	
	private String calculationMethod = "Normal";	//	others are RangeEst, from Center
	
	//	for	RangeEstimation
	private JTextField xMinTf = new JTextField(5);
	private double xMinVal;	
	private JTextField yMinTf = new JTextField(5);
	private double yMinVal;
	
	private JTextField xMaxTf = new JTextField(5);
	private double xMaxVal;	
	private JTextField yMaxTf = new JTextField(5);
	private double yMaxVal;

	private final JLabel labelMinX = new JLabel("Minimum (x,y) : X:");
	private final JLabel labelMaxX = new JLabel("Maximum (x,y) : X:");
	private final JLabel labelMinY = new JLabel("  Y:");
	private final JLabel labelMaxY = new JLabel("  Y:");
	
	//	for	CenterCalc
	private JTextField xCntrTf = new JTextField(5);
	private double xCntrVal;	
	private JTextField yCntrTf = new JTextField(5);
	private double yCntrVal;
	
	private final JLabel labelCentrX = new JLabel("Center (x,y) : X:");
	private final JLabel labelCntrY = new JLabel("  Y:");
	private final JLabel labelCentrZoom = new JLabel("ZoomLevel : ");
	
	private static final String[] CENTER_ZOOM_OPTIONS = new String[]{"1:1","1:10","1:1000","1:1000000"};
	private JComboBox<String> centrZoomCombo = new JComboBox<String>(CENTER_ZOOM_OPTIONS);
	private String centrZoomChoice = "1:1";
	

	public FractalOptionsPanel(String choice) {
		super();
		this.setFractalChoice(choice);
		this.setupFractalOptionsPanel();
		this.setupListeners();
		
		this.setVisible(true);
	}

	public String getFractalChoice() {
		return fractalChoice;
	}

	public void setFractalChoice(String fractalChoice) {
		this.fractalChoice = fractalChoice;

		if (!fractalChoice.equals("Mandelbrot")) {
			this.labelRealConst.setVisible(true);
			this.realConstTf.setVisible(true);
			this.labelImgConst.setVisible(true);
			this.imgConstTf.setVisible(true);
		} else {
			this.labelRealConst.setVisible(false);
			this.realConstTf.setVisible(false);
			this.labelImgConst.setVisible(false);
			this.imgConstTf.setVisible(false);
		}
	}

	private void setupListeners() {
		this.pxXTransformCombo.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				String comboOption = (String) cb.getSelectedItem();
				doSetPxXTransformCombosCommand(comboOption);
			}

			private void doSetPxXTransformCombosCommand(String comboOption) {
				pixXTransform = comboOption;
			}
		});
		this.pxYTransformCombo.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				String comboOption = (String) cb.getSelectedItem();
				doSetPxYTransformCombosCommand(comboOption);
			}

			private void doSetPxYTransformCombosCommand(String comboOption) {
				pixYTransform = comboOption;
			}
		});
		this.intraPixOperationCombo.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				String comboOption = (String) cb.getSelectedItem();
				doSetIntraPixOperationCombosCommand(comboOption);
			}

			private void doSetIntraPixOperationCombosCommand(String comboOption) {
				pixIntraXYOperation = comboOption;
			}
		});
		this.pxFuncCombo.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				String comboOption = (String) cb.getSelectedItem();
				doSetPxFuncCombosCommand(comboOption);
			}
			private void doSetPxFuncCombosCommand(String comboOption) {
				pxFuncChoice=comboOption;
			}
		});
		this.constFuncCombo.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				String comboOption = (String) cb.getSelectedItem();
				doSetConstFuncCombosCommand(comboOption);
			}
			private void doSetConstFuncCombosCommand(String comboOption) {
				constFuncChoice = comboOption;
			}
		});
		this.pxConstOprnCombo.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				String comboOption = (String) cb.getSelectedItem();
				doSetPxConstOprnCombosCommand(comboOption);
			}
			private void doSetPxConstOprnCombosCommand(String comboOption) {
				pxConstOprnChoice=comboOption;				
			}
		});

		this.useNormalCalcRb.addActionListener(this.keepCalculationRbListener());
		this.useRangeEstCalRb.addActionListener(this.keepCalculationRbListener());
		this.useCenterCalRb.addActionListener(this.keepCalculationRbListener());
		
		this.centrZoomCombo.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				String comboOption = (String) cb.getSelectedItem();
				doSetCenterZoomCombosCommand(comboOption);
			}
			private void doSetCenterZoomCombosCommand(String comboOption) {
				centrZoomChoice = comboOption;
			}
		});
	}
	

	private ActionListener keepCalculationRbListener() {
		return new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				doCalculationSelectCommand();
			}
		};
	}

	protected void doCalculationSelectCommand() {
		if (this.useNormalCalcRb.isSelected()) {
			this.calculationMethod="Normal";
			
			this.labelMinX.setVisible(false);
			this.xMinTf.setVisible(false);
			this.labelMinY.setVisible(false);
			this.yMinTf.setVisible(false);
			this.labelMaxX.setVisible(false);
			this.xMaxTf.setVisible(false);
			this.labelMaxY.setVisible(false);
			this.yMaxTf.setVisible(false);
			
			this.labelCentrX.setVisible(false);
			this.xCntrTf.setVisible(false);
			this.labelCntrY.setVisible(false);
			this.yCntrTf.setVisible(false);
			this.labelCentrZoom.setVisible(false);
			this.centrZoomCombo.setVisible(false);

		} else if (this.useRangeEstCalRb.isSelected()) {
			this.calculationMethod="RangeEstimation";
			
			this.labelMinX.setVisible(true);
			this.xMinTf.setVisible(true);
			this.labelMinY.setVisible(true);
			this.yMinTf.setVisible(true);
			this.labelMaxX.setVisible(true);
			this.xMaxTf.setVisible(true);
			this.labelMaxY.setVisible(true);
			this.yMaxTf.setVisible(true);			

			this.labelCentrX.setVisible(false);
			this.xCntrTf.setVisible(false);
			this.labelCntrY.setVisible(false);
			this.yCntrTf.setVisible(false);
			this.labelCentrZoom.setVisible(false);
			this.centrZoomCombo.setVisible(false);
			
			
		} else if (this.useCenterCalRb.isSelected()) {
			this.calculationMethod="CenterZoom";
			
			this.labelCentrX.setVisible(true);
			this.xCntrTf.setVisible(true);
			this.labelCntrY.setVisible(true);
			this.yCntrTf.setVisible(true);
			this.labelCentrZoom.setVisible(true);
			this.centrZoomCombo.setVisible(true);

			this.labelMinX.setVisible(false);
			this.xMinTf.setVisible(false);
			this.labelMinY.setVisible(false);
			this.yMinTf.setVisible(false);
			this.labelMaxX.setVisible(false);
			this.xMaxTf.setVisible(false);
			this.labelMaxY.setVisible(false);
			this.yMaxTf.setVisible(false);
		}

		repaint();

	}

	private void setupFractalOptionsPanel() {
		this.setLayout(new GridLayout(10,6));
		this.add(new JLabel("Pixel Transformation:  X: "));
		this.pxXTransformCombo.setSelectedItem(PIX_TRANSFORM_OPTIONS[0]);
		this.add(pxXTransformCombo);
		this.add(new JLabel(" ,  Y: "));
		this.pxYTransformCombo.setSelectedItem(PIX_TRANSFORM_OPTIONS[0]);
		this.add(pxYTransformCombo);		
		this.add(new JLabel("Intra Pixel Operation:"));
		this.intraPixOperationCombo.setSelectedItem(PIX_INTRA_OPRNS[0]);
		this.add(this.intraPixOperationCombo);
		this.add(new JLabel("Pixel z(x,y) Functions:"));
		this.pxFuncCombo.setSelectedItem(FUNCTION_OPTIONS[0]);
		this.add(this.pxFuncCombo);		
		this.add(new JLabel("Constant (C) Functions:"));
		this.constFuncCombo.setSelectedItem(FUNCTION_OPTIONS[0]);
		this.add(this.constFuncCombo);
		this.add(new JLabel("Pixel-Constant Operation:"));
		this.pxConstOprnCombo.setSelectedItem(PIX_CONST_OPRNS[0]);
		this.add(this.pxConstOprnCombo);

		// fixed constant for julia,	C same with all pixel calcs
		// dynamic constant for mandelbrot, Z=C
		this.add(this.labelRealConst);
		if (this.fractalChoice.equals("Mandelbrot")) {
			this.labelRealConst.setVisible(false);
		}
		this.realConstTf.setText("0.25");
		this.add(this.realConstTf);
		if (this.fractalChoice.equals("Mandelbrot")) {
			this.realConstTf.setVisible(false);
		}
		this.add(this.labelImgConst);	
		if (this.fractalChoice.equals("Mandelbrot")) {
			this.labelImgConst.setVisible(false);
		}
		this.imgConstTf.setText("0.0");
		this.add(this.imgConstTf);
		if (this.fractalChoice.equals("Mandelbrot")) {
			this.imgConstTf.setVisible(false);
		}
		
		this.add(new JLabel("Exponent: "));
		this.exponentTf.setText("2");
		this.add(this.exponentTf);
		this.add(new JLabel("Maximum Itertions: "));
		this.maxIterTf.setText("500");
		this.add(this.maxIterTf);
		this.add(new JLabel("Boundary: "));
		this.boundaryTf.setText("2.0");
		this.add(this.boundaryTf);
		this.add(new JLabel("Center: X"));
		
		final String xCtr = this.fractalChoice.equals("Mandelbrot") ? "-0.5" : "0.0";
		this.xCenterTf.setText(xCtr);
		this.add(this.xCenterTf);
		this.add(new JLabel("Center: Y"));
		this.yCenterTf.setText("0.0");
		this.add(this.yCenterTf);
		this.add(new JLabel("Scale Size"));
		this.scaleSizeTf.setText("2.0");
		this.add(this.scaleSizeTf);
		

		this.add(new JLabel("Calculation Method:"));
		this.calculationBg.add(this.useNormalCalcRb);
		this.calculationBg.add(this.useRangeEstCalRb);
		this.calculationBg.add(this.useCenterCalRb);
		this.useNormalCalcRb.setSelected(true);
		this.add(this.useNormalCalcRb);
		this.add(this.useRangeEstCalRb);
		this.add(this.useCenterCalRb);
		
		this.add(this.labelMinX);		this.labelMinX.setVisible(false);
		this.add(this.xMinTf);			this.xMinTf.setVisible(false);
		this.add(this.labelMinY);		this.labelMinY.setVisible(false);
		this.add(this.yMinTf);			this.yMinTf.setVisible(false);
		this.add(this.labelMaxX);		this.labelMaxX.setVisible(false);
		this.add(this.xMaxTf);			this.xMaxTf.setVisible(false);
		this.add(this.labelMaxY);		this.labelMaxY.setVisible(false);
		this.add(this.yMaxTf);			this.yMaxTf.setVisible(false);
		
		this.add(this.labelCentrX);		this.labelCentrX.setVisible(false);
		this.add(this.xCntrTf);			this.xCntrTf.setVisible(false);
		this.add(this.labelCntrY);		this.labelCntrY.setVisible(false);
		this.add(this.yCntrTf);			this.yCntrTf.setVisible(false);
		this.add(this.labelCentrZoom);	this.labelCentrZoom.setVisible(false);
		this.add(this.centrZoomCombo);	this.centrZoomCombo.setVisible(false);
	}
	
	public FractalOptions getFractalOptions() {
		FractalOptions frOptions = new FractalOptions();
		frOptions.setxTransformation((String) this.pxXTransformCombo.getSelectedItem());
		frOptions.setyTransformation((String) this.pxYTransformCombo.getSelectedItem());
		frOptions.setXyOperation((String) this.intraPixOperationCombo.getSelectedItem());
		frOptions.setzTransformation((String) this.pxFuncCombo.getSelectedItem());
		frOptions.setcTransformation((String) this.constFuncCombo.getSelectedItem());
		frOptions.setZcOperation((String) this.pxConstOprnCombo.getSelectedItem());
		
		if (!this.fractalChoice.equals("Mandelbrot")) {
			frOptions.setFractalChoice("Julia");
			// z!= constant
			frOptions.setDynamicC(false);
			frOptions.setRealConstant(Double.parseDouble(this.realConstTf.getText().trim()));
			frOptions.setImagConstant(Double.parseDouble(this.imgConstTf.getText().trim()));
		} else {
			// z=c
			frOptions.setFractalChoice("Mandelbrot");
			frOptions.setDynamicC(true);
		}
		
		frOptions.setExponent(Double.parseDouble(this.exponentTf.getText().trim()));
		frOptions.setMaxIter(Integer.parseInt(this.maxIterTf.getText().trim()));
		frOptions.setBoundary(Double.parseDouble(this.boundaryTf.getText().trim()));
		frOptions.setScaleSize(Double.parseDouble(this.scaleSizeTf.getText().trim()));

		frOptions.setxCenter(Double.parseDouble(this.xCenterTf.getText().trim()));
		frOptions.setyCenter(Double.parseDouble(this.yCenterTf.getText().trim()));
		
		if (this.calculationMethod.equals("Normal")) {

		} else if(this.calculationMethod.equals("RangeEstimation")){
			frOptions.setX_min_val(Double.parseDouble(this.xMinTf.getText().trim()));
			frOptions.setY_min_val(Double.parseDouble(this.yMinTf.getText().trim()));
			frOptions.setX_max_val(Double.parseDouble(this.xMaxTf.getText().trim()));
			frOptions.setY_max_val(Double.parseDouble(this.yMaxTf.getText().trim()));
		} else if(this.calculationMethod.equals("CenterZoom")){
			
		}
		return frOptions;
	}

}
