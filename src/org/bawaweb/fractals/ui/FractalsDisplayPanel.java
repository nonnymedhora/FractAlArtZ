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

	FractalPanel fractalPanel = null;
	BufferedImage fractalImage = null;

	public FractalsDisplayPanel(String choice) {
		super();
		if (choice.equals("Mandelbrot")) {
			fractalPanel = new MandelbrotPanel();
		} else if (choice.equals("Julia")) {
			fractalPanel = new JuliaPanel();
		}
		fractalPanel.setVisible(true);
		this.add(fractalPanel);
		

        Border displayPanelBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY), "Fractal");
		
        this.setBorder(displayPanelBorder);
		this.setVisible(true);
	}
	
	public FractalsDisplayPanel(String choice, FractalOptions opt) {
		super();
		this.options = opt;
		if (choice.equals("Mandelbrot")) {
			fractalPanel = new MandelbrotPanel(opt);
		} else if (choice.equals("Julia")) {
			fractalPanel = new JuliaPanel(opt);
		}
		fractalImage = fractalPanel.getImage();
		fractalPanel=null;
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
	
	public FractalsDisplayPanel(FractalPanel[] fractals) {
		
	}
	
	public void setChoice(String choice) {
		this.fractalPanel = null;
		if (choice.equals("Mandelbrot")) {
			this.fractalPanel = new MandelbrotPanel();
		} else if (choice.equals("Julia")) {
			this.fractalPanel = new JuliaPanel();
		}
		repaint();
	}

	public FractalPanel getFractalCanvas() {
		return this.fractalPanel;
	}

	public void setFractalCanvas(FractalPanel fCanvas) {
		this.fractalPanel = fCanvas;
	}

}
