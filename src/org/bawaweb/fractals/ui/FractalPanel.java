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
	private BufferedImage fractalImage = null;

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

	protected void setUpFractalImage() {
		Image img = createImage(new MemoryImageSource(width, height, data, 0, width));
		
		BufferedImage buff = convertImage(img);
		
		this.setImage(buff);
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
	 */
	public static void main(String[] args) {

	}

}
