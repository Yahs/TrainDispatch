import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;


public class MapPanel extends JPanel implements ActionListener {

	private Map map = new Map (this);
	private MapCanvas mapCanvas = new MapCanvas(map);
	private JScrollPane mapContainer = new JScrollPane(mapCanvas);
	
	private JToolBar toolbar = new JToolBar();
	private JButton pan = new JButton ("Pan");
	private JButton zoomIn = new JButton ("Zoom IN");
	private JButton zoomOut = new JButton ("Zoom Out");
	private JButton save = new JButton ("Save");
	private JButton loadMap = new JButton ("Load Map");
	private JButton genTrains = new JButton ("Generate Trains");
	private JButton scale = new JButton ("Scale");
	private JButton clear = new JButton ("Clear Map");

	private JButton delete = new JButton ("Delete");
	
	ButtonGroup addButtonGroup = new ButtonGroup();
	private JRadioButton addNode = new JRadioButton("Add Node");
	private JRadioButton addEdge = new JRadioButton("Add Edge");
	private JRadioButton cursor = new JRadioButton ("Cursor");

	private ImageIcon img = null;
	private boolean hideBackground = true;
	
	private JPanel info = new JPanel();
	private JLabel infoLabel = new JLabel();
	JScrollPane infoContainer = new JScrollPane(infoLabel);

	
	public MapPanel () {
		mapContainer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		mapContainer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		
		addButtonGroup.add(addNode);
		addButtonGroup.add(addEdge);
		addButtonGroup.add(cursor);
		
		toolbar.add(pan);
		toolbar.add(addNode);
		toolbar.add(addEdge);
		toolbar.add(save);
		toolbar.add(loadMap);
		toolbar.add(genTrains);
		//toolbar.add(scale);
		toolbar.add(clear);
		
		addNode.setSelected(true);
		
		pan.addActionListener(this);
		zoomIn.addActionListener(this);
		zoomOut.addActionListener(this);
		cursor.addActionListener(this);
		addNode.addActionListener(this);
		addEdge.addActionListener(this);
		delete.addActionListener(this);
		save.addActionListener(this);
		loadMap.addActionListener(this);
		genTrains.addActionListener(this);
		scale.addActionListener(this);
		clear.addActionListener(this);
		
		setLayout(new BorderLayout());
		add(toolbar, BorderLayout.NORTH);
		add(mapContainer, BorderLayout.CENTER);
		add(info, BorderLayout.EAST);
	}
		
	public void setInfo (String msg) {
		info.setLayout(new BorderLayout());
		info.add(infoContainer, BorderLayout.CENTER);
		String html = msg.replaceAll("\n", "<br>");
		html = "<html>" + html + "<br></html>";
		infoLabel.setText(html);
		info.add(delete, BorderLayout.SOUTH);
		revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == pan) {
			mapCanvas.setOperation(MapCanvas.operations.PAN);
		} else if (event.getSource() == zoomIn) {
			mapCanvas.setOperation(MapCanvas.operations.ZOOM_IN);
		} else if (event.getSource() == zoomOut) {
			mapCanvas.setOperation(MapCanvas.operations.ZOOM_OUT);
		} else if (event.getSource() == addNode) {
			mapCanvas.setOperation(MapCanvas.operations.DRAW_NODE);
		} else if (event.getSource() == addEdge) {
			mapCanvas.setOperation(MapCanvas.operations.DRAW_EDGE);
		} else if (event.getSource() == scale) {
			mapCanvas.setOperation(MapCanvas.operations.SCALE);
		} else if (event.getSource() == save) {
			map.save();
		} else if (event.getSource() == loadMap) {
			map.loadMap();
			mapCanvas.updateSize(map.getWidth(), map.getHeight());
			mapCanvas.repaint();
		} else if (event.getSource() == delete) {
			mapCanvas.deleteSelected();
			info.removeAll();
			delete.setSelected(false);
			revalidate();
		} else if (event.getSource() == genTrains) {
			TrainGenerator gen = new TrainGenerator(map);
			int numTrains = Integer.parseInt(JOptionPane.showInputDialog("Number of Trains: "));
			int rate = Integer.parseInt(JOptionPane.showInputDialog("Schedules Per Hour: "));
			gen.generate(numTrains, rate);
			mapCanvas.repaint();
		} else if (event.getSource() == clear) {
			map.clear();
			mapCanvas.repaint();
		}
	}
	
	
	public void loadBackground() {
		Path dir = Paths.get("./");
		JFileChooser imageChooser = new JFileChooser();
		imageChooser.setCurrentDirectory(dir.toFile());
		imageChooser.setSelectedFile(new File("map.png"));
		int result = imageChooser.showOpenDialog(null); //dialog
		if (result != imageChooser.CANCEL_OPTION) {
			String imgPath = imageChooser.getSelectedFile().getAbsolutePath();
			img = new ImageIcon(imgPath);
			mapCanvas.setIcon(img);
			mapCanvas.updateSize(img.getIconWidth(), img.getIconHeight());
		}
	}
	
	public void toggleBackground() {
		if (img != null && !hideBackground) {
			mapCanvas.setIcon(img);
		} else {
			mapCanvas.setIcon(null);
		}
		hideBackground = !hideBackground;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		mapCanvas.repaint();
		mapCanvas.invalidate();
		mapCanvas.validate();
	}
	
	public Map getMap () { return map; }
	public MapCanvas getCanvas() { return mapCanvas; }

}
