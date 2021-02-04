/**
 * 
 */
package org.bawaweb.fractals.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.bawaweb.fractals.FractalOptions;

/**
 * @author Navroz
 *
 */
public class ControlPanel extends JPanel {

	private static final long serialVersionUID = 235432678L;

	private FractalsDisplayPanel fdPanel = new FractalsDisplayPanel("Mandelbrot", new FractalOptions("Mandelbrot"));
	private String fractalChoice = "Mandelbrot";
	private final String[] fractalChoices = new String[] {"Mandelbrot","Julia"};
	private JComboBox<String> choicesCombo = new JComboBox<String>(fractalChoices);
	
	protected FractalOptionsPanel fractalOptionsPanel = new FractalOptionsPanel("Julia");	

	private JButton goButton = new JButton("Go");
	public ControlPanel() {
		super();
		this.setUpControlPanel();
		this.setUpListeners();
	}

	private void setUpListeners() {
		this.choicesCombo.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				String comboOption = (String) cb.getSelectedItem();
				doSelectChoicesCombosCommand(comboOption);
			}

			private void doSelectChoicesCombosCommand(String comboOption) {
				fractalChoice = comboOption;
				fractalOptionsPanel.setFractalChoice(fractalChoice);
				fractalOptionsPanel.repaint();
				
				fdPanel.setChoice(fractalChoice);
				fdPanel.repaint();
			}
		});
		
		this.goButton.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				doGo();
			}

			private void doGo() {
				FractalOptions fO = fractalOptionsPanel.getFractalOptions();

				fdPanel = null;

				if (fractalChoice.equals("Mandelbrot")) {
					fdPanel = new FractalsDisplayPanel("Mandelbrot",fO);
				} else if (fractalChoice.equals("Julia")) {
					fdPanel = new FractalsDisplayPanel("Julia",fO);
				}
				
				fdPanel.validate();
				fractalOptionsPanel.validate();
			}
		});
		
	}

	private void setUpControlPanel() {
		this.choicesCombo.setSelectedItem("Mandelbrot");
		this.add(this.choicesCombo,BorderLayout.WEST);
		
		this.add(this.fractalOptionsPanel,BorderLayout.EAST);
		
		this.add(this.fdPanel,BorderLayout.CENTER);
		
		this.add(this.goButton);

		this.setVisible(true);
	}

	/**
	 * @param layout
	 */
	public ControlPanel(LayoutManager layout) {
		super(layout);
	}

	/**
	 * @param isDoubleBuffered
	 */
	public ControlPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
	}

	/**
	 * @param layout
	 * @param isDoubleBuffered
	 */
	public ControlPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				final JFrame frame = new JFrame();
				frame/*.getContentPane()*/.add(new ControlPanel());
				frame.setTitle("BaWaZ FractalArtz: ");
				frame.setSize(900, 800);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setResizable(true);
				frame.setVisible(true);
			}
		});
	}

	public String getFractalChoice() {
		return this.fractalChoice;
	}

	/*public void setFractalChoice(String fChoice) {
		this.fractalChoice = fChoice;

		this.fdPanel = null;
		this.repaint();
		this.fdPanel = new FractalsDisplayPanel(fChoice);
		this.fdPanel.setVisible(true);
		this.fdPanel.repaint();
		this.repaint();
		
	}*/

}
