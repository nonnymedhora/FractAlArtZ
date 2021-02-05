/**
 * 
 */
package org.bawaweb.fractals.ui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

import org.bawaweb.fractals.FractalOptions;
import org.bawaweb.fractals.utils.ComplexNumber;

/**
 * @author Navroz
 *
 *	https://stackoverflow.com/questions/38558773/java-jframe-adding-canvas-and-jpanel-to-it
 *	It's usually a bad idea to mix AWT and Swing components. 
 *	Are you absolutely positive that you need to use Canvas? 
 *	JPanels are double buffered by default and that usually 
 *	smooths out any animation if that's your goal.
 */
public abstract class FractalPanel extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 19876543L;
	private Color bgcolor;
	protected int width=300;
	protected int height=width;

	protected MemoryImageSource source = null;
	protected int[] data = null;
	protected int[] iterArr = null;
	protected Map<ComplexNumber, Integer> zIterMap = new HashMap<ComplexNumber, Integer>();
	protected Map<Integer, Integer> iterFrequencyMap = new HashMap<Integer,Integer>();
	private BufferedImage fractalImage = null;
	private BufferedImage iterImage = null;
	private BufferedImage iterFrequencyImage = null;

	public FractalPanel() {		
		setSize(width,height);
		setColor(Color.green);
	    setPreferredSize(new Dimension(width, height));
	    setMaximumSize(new Dimension(width, height));
	    setMinimumSize(new Dimension(width, height));
	    setFocusable(false);
	    setVisible(true);
	}
	
	public FractalPanel(FractalOptions opt) {
		this();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.green.darker().darker().darker());
		g2.fillRect(0, 0, width, height);
	}


	private void setColor(Color aClr) {
		this.bgcolor = aClr;
	}


	public int[] getData() {
		return this.data;
	}

	public void setData(int[] d) {
		this.data = d;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {

	}
	
	
	protected void add2IterFrequencyMap(int frequency) {
		if (this.iterFrequencyMap.get(frequency) != null) {
			int count = this.iterFrequencyMap.get(frequency);
			this.iterFrequencyMap.put(frequency, ++count);
		} else {
			this.iterFrequencyMap.put(frequency, 1);
		}
	}



	/**
	 * Returns the pixel Z value
	 * @param zConstant	-	the constant used against the pixel
	 * @param z			-	the pixel - z value, usually x + <b><i>i</i></b>y
	 * @param options	-	the fractaloptions
	 * @return
	 */
	protected ComplexNumber getZValue(ComplexNumber zConstant, ComplexNumber z, FractalOptions options) {
		double exp = options.getExponent();
		String zCOp = options.getZcOperation();
		
		z = processForZExponent(z,exp);
		z = processForZCOperation(z,zCOp,zConstant);
		return z;//z.times(z).plus(zConstant);
	}



	protected ComplexNumber processForZCOperation(ComplexNumber z, String op, ComplexNumber zConstant) {
		/*private static final String[] PIX_CONST_OPRNS = new String[] 
		 * {"Plus","Minus","Multiply","Divide","PowerZ^C","PowerC^Z"};*/
		switch (op) {
			case "Plus":
				z = z.plus(zConstant);
				break;
			case "Minus":
				z = z.minus(zConstant);
				break;
			case "Multiply":
				z = z.times(zConstant);
				break;
			case "Divide":
				z = z.divides(zConstant);
				break;
			case "PowerZ^C":
				z = z.power(zConstant);
				break;
			case "PowerC^Z":
				z = zConstant.power(z);
				break;				
				
			default:	//"Plus"
				z = z.plus(zConstant);
		}
		return z;
	}



	protected ComplexNumber processForZExponent(ComplexNumber z, double exp) {
		int pow = (int) exp;
		for(int i = 1; i < pow; i++)
			z = z.times(z);
		return z;
	}


	protected int checkColor(int grey) {
		return grey = grey < 0 ? 0 : (grey > 255 ? 255 : grey);
	}
	
	public BufferedImage getImage() {
		return this.fractalImage;
	}
	
	protected void setImage(BufferedImage img) {
		this.fractalImage = img;
	}

	public BufferedImage getIterImage() {
		return this.iterImage;
	}

	public void setIterImage(BufferedImage itrImg) {
		this.iterImage = itrImg;
	}

	public BufferedImage getIterFrequencyImage() {
		return this.iterFrequencyImage;
	}

	public void setIterFrequencyImage(BufferedImage image) {
		this.iterFrequencyImage = image;
	}

	protected void setUpFractalImage() {
		Image img = createImage(new MemoryImageSource(width, height, data, 0, width));
		
		BufferedImage buff = convertImage(img);
		
		this.setImage(buff);
	}
	
	protected void setUpIterImage() {
		Image img = createImage(new MemoryImageSource(width, height, iterArr, 0, width));
		
		BufferedImage buff = convertImage(img);
		
		this.setIterImage(buff);
	}
	
	protected void setUpIterFrequencyImage() {
		//	plots the iterator-frequency map.
		//		x	-	width
		int min = getMin(this.iterFrequencyMap);
		int max = getMax(this.iterFrequencyMap);
		//		y	-	height
		int minCount = getMinValue(this.iterFrequencyMap); 
		int maxCount = getMaxValue(this.iterFrequencyMap);
		
		int freqTotal=0;
		
System.out.println("x_range (Iterations)  min["+min+"] max["+max+"]");	
System.out.println("y_range (Frequency)   minCount["+minCount+"]   maxCount["+maxCount+"]");
		
		BufferedImage buff = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		Graphics g = buff.getGraphics();
		g.setColor(Color.red);
		g.fillRect(0,0,width,height);
		
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.blue);
		
		Set<Integer> keys = this.iterFrequencyMap.keySet();
		List<Integer> keysList = new ArrayList<Integer>(); 
	    keysList.addAll(keys); 
	    
	    Collections.sort(keysList);

System.out.println("Iterations ==> Frequency");    
	    Iterator<Integer> iter = keysList.iterator();
		while (iter.hasNext()) {
			int key = iter.next();
			int val = this.iterFrequencyMap.get(key);
System.out.println(key+" ==>  "+val);
			g.drawRect(key * width / (max - min), val * height / (maxCount - minCount), 2, 2);
freqTotal+=val;
		}
System.out.println("Total Frequency Count ==> "+ freqTotal);		
		this.setIterFrequencyImage(buff);
		
		//problem 4 graph display	-	out liars
		//	Iterations ==> Frequency
		//	499 ==>  2
		//	500 ==>  34060
		
	}
	
	private int getMaxValue(Map<Integer, Integer> map) {
		Collection<Integer> vals = map.values();
		int max = 0;//Integer.MIN_VALUE;
		Iterator<Integer> iter = vals.iterator();
		while (iter.hasNext()) {
			int val = iter.next();
			max = Math.max(max,val);
		}
		return max;
	}

	private int getMinValue(Map<Integer, Integer> map) {
		Collection<Integer> vals = map.values();
		int min = Integer.MAX_VALUE;
		Iterator<Integer> iter = vals.iterator();
		while (iter.hasNext()) {
			int val = iter.next();
			min = Math.min(min, val);
		}
		return min;
	}
	
	private int getMax(Map<Integer, Integer> map) {
		Set<Integer> k = map.keySet();
		int max = 0;//Integer.MIN_VALUE;
		Iterator<Integer> iter = k.iterator();
		while (iter.hasNext()) {
			int val = iter.next();
			max = Math.max(max, val);
		}
		return max;
	}

	private int getMin(Map<Integer, Integer> map) {
		Set<Integer> k = map.keySet();
		int min = Integer.MAX_VALUE;
		Iterator<Integer> iter = k.iterator();
		while (iter.hasNext()) {
			int val = iter.next();
			min = Math.min(min, val);
		}
		return min;
	}
	

	protected BufferedImage convertImage(Image original) {
        ColorModel cm = ColorModel.getRGBdefault();
        int width = original.getWidth(null);
        int height = original.getHeight(null);
        BufferedImage image = new BufferedImage (
        							cm, 
        							cm.createCompatibleWritableRaster(width, height),
        							cm.isAlphaPremultiplied(), 
        							null);
        Graphics2D bg = image.createGraphics();
        bg.drawImage(original, 0, 0, width, height, null);
        bg.dispose();
        
        return image;
    }


	/**
	 * @param args
	
	public static void main(String[] args) {

	}
 */
}
