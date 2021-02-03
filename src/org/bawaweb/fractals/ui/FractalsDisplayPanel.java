package org.bawaweb.fractals.ui;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.bawaweb.fractals.FractalOptions;

public class FractalsDisplayPanel extends JPanel {
	private static final long serialVersionUID = 35267L;
	private FractalOptions options;

	public FractalsDisplayPanel() {
		super();
	}

	FractalCanvas fractalCanvas = null;
	BufferedImage fractalImage = null;

	public FractalsDisplayPanel(String choice) {
		super();
		if (choice.equals("Mandelbrot")) {
			fractalCanvas = new MandelbrotCanvas();
		} else if (choice.equals("Julia")) {
			fractalCanvas = new JuliaCanvas();
		}
		fractalCanvas.setVisible(true);
		this.add(fractalCanvas);
		

        Border displayPanelBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY), "Fractal");
		
        this.setBorder(displayPanelBorder);
		this.setVisible(true);
	}
	
	public FractalsDisplayPanel(String choice, FractalOptions opt) {
		super();
		this.options = opt;
		if (choice.equals("Mandelbrot")) {
			fractalCanvas = new MandelbrotCanvas(opt);
		} else if (choice.equals("Julia")) {
			fractalCanvas = new JuliaCanvas(opt);
		}
		fractalImage = fractalCanvas.getImage();
		fractalCanvas=null;
		/*fractalCanvas.setVisible(true);
		this.add(fractalCanvas);*/
		
		final JLabel imgLbl = new JLabel(new ImageIcon(fractalImage));
		imgLbl.setVisible(true);
		this.add(imgLbl);		

        Border displayPanelBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY), "Fractal");
		
        this.setBorder(displayPanelBorder);
		this.setVisible(true);
	}
	
	public FractalsDisplayPanel(FractalCanvas[] fractals) {
		
	}
	
	public void setChoice(String choice) {
		this.fractalCanvas = null;
		if (choice.equals("Mandelbrot")) {
			this.fractalCanvas = new MandelbrotCanvas();
		} else if (choice.equals("Julia")) {
			this.fractalCanvas = new JuliaCanvas();
		}
		repaint();
	}

	public FractalCanvas getFractalCanvas() {
		return this.fractalCanvas;
	}

	public void setFractalCanvas(FractalCanvas fCanvas) {
		this.fractalCanvas = fCanvas;
	}

}
