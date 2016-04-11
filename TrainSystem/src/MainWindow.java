import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class MainWindow extends JFrame implements ActionListener, ChangeListener {

	private MapPanel mapPanel;
	private Map map;
	
	private JLabel backgroundLabel = new JLabel("Backgorund:");
	private JButton loadBackground = new JButton ("Load");
	private JButton toggleBackground = new JButton ("Toggle");
	
	private JButton play = new JButton(">");
	private JButton pause = new JButton("||");
	private JButton stop = new JButton("Reset");
	private JButton speedUp = new JButton("+");
	private JButton slowDown = new JButton("-");
	
	private String[] controllers = {"Base Case", "Optimized"};
	private JComboBox controller = new JComboBox(controllers);
	
	private TrainSimulator trainSimulator;
	private boolean isPaused = false;
	
	public MainWindow() {
		JPanel controls = new JPanel();
		controls.setLayout(new BorderLayout());
		
		JPanel simulateControls = new JPanel();
		simulateControls.add(play);
		simulateControls.add(pause);
		simulateControls.add(stop);
		simulateControls.add(speedUp);
		simulateControls.add(slowDown);
		simulateControls.add(controller);
		
		JPanel canvasControls = new JPanel();
		canvasControls.add(backgroundLabel);
		canvasControls.add(loadBackground);
		canvasControls.add(toggleBackground);	
		
		controls.add(simulateControls, BorderLayout.EAST);
		controls.add(canvasControls, BorderLayout.WEST);
		
		play.addActionListener(this);
		stop.addActionListener(this);
		pause.addActionListener(this);
		speedUp.addActionListener(this);
		slowDown.addActionListener(this);
		loadBackground.addActionListener(this);
		toggleBackground.addActionListener(this);
		controller.addActionListener(this);
		
		mapPanel = new MapPanel();
		map = mapPanel.getMap();
		setLayout(new BorderLayout());
		add(mapPanel, BorderLayout.CENTER);
		add(controls, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		setVisible(true);
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == play) {
			trainSimulator = new TrainSimulator(mapPanel);
			trainSimulator.simulate();
		} else if (event.getSource() == stop) {
			map.reset();
			mapPanel.repaint();
		} else if (event.getSource() == pause) {
			if (!isPaused) {
				trainSimulator.pause();
			} else {
				trainSimulator.play();
			}
		} else if (event.getSource() == speedUp) {
			trainSimulator.speedUp();
		} else if (event.getSource() == slowDown) {
			trainSimulator.slowDown();
		} else if (event.getSource() == loadBackground) {
			mapPanel.loadBackground();
		} else if (event.getSource() == toggleBackground) {
			mapPanel.toggleBackground();
		} else  if (event.getSource() == controller) {
			String selection = controller.getSelectedItem().toString();
			System.out.println(selection);
		}
	}
	
	@Override 
	public void paint (Graphics g) {
		super.paint(g);
		mapPanel.repaint();
	}
	
	public static void main (String args[]) {
		MainWindow window = new MainWindow();
		window.setVisible(true);
	}

	@Override
	public void stateChanged(ChangeEvent event) {
		
	}
		
}
