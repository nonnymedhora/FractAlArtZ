package org.bawaweb.fractals.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;

import org.bawaweb.fractals.FractalOptions;
import org.bawaweb.fractals.utils.ComplexNumber;

public class MandelbrotCanvas extends FractalCanvas {
	
	private static final long serialVersionUID = 134567L;

	public MandelbrotCanvas() {
		super();
    }
	
	public MandelbrotCanvas(FractalOptions opt) {
		super(opt);

		double xCenter = opt.getxCenter();//-0.5;
		double yCenter = opt.getyCenter();//0;
		double scaleSize = opt.getScaleSize();//2;

		int n = super.height; 			// create n-by-n image
		int maxIter = opt.getMaxIter(); // maximum number of iterations
		
		this.data = new int[n * n];

		int pixPt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				double x0 = xCenter - scaleSize / 2 + scaleSize * i / n;
				double y0 = yCenter - scaleSize / 2 + scaleSize * j / n;
				ComplexNumber z0 = new ComplexNumber(x0, y0);
				int gray = maxIter - mand(z0, opt);
				gray = checkColor(gray);
				Color color = new Color(gray, gray, gray);

				this.data[pixPt] = color.getRGB();
				pixPt += 1;
			}
		}
		
		this.setUpFractalImage();
	}
	



	// return number of iterations to check if 
	//	c = a + ib is in Mandelbrot set
    private int mand(ComplexNumber z0, FractalOptions opt) {
        ComplexNumber z = z0;
        final int maxIter = opt.getMaxIter();
		final double boundary = opt.getBoundary();
		for (int t = 0; t < maxIter; t++) {
			if (z.abs() > boundary)
				return t;
			z = getZValue(z0, z, opt);
		}
        return maxIter;
    }


	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		
		 // set color to red 
        g2.setColor(Color.red); 

        // set Font 
        g2.setFont(new Font("Bold", 1, 10)); 

        // draw a string 
        g2.drawString("Mandelbrot", 100, 20);
        
        setVisible(true);
	}
	

}
