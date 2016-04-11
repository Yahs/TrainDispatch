/*
 * Simple dialog to collect information about the station from the user.
 * 
 */

import java.awt.GridLayout;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class CreateStationDialog extends JPanel {

	JTextField name = new JTextField ();
	JTextField capacity = new JTextField();
	JLabel nameLabel = new JLabel ("Station Name: ");
	JLabel capacityLabel = new JLabel ("Capcity: ");
	Point location;
	
	public CreateStationDialog (Point p, String defaultName, String defaultCap)
	{
		setLayout(new GridLayout(3, 2));
		location = p;
		name.setText(defaultName);
		capacity.setText(defaultCap);
		
		add(nameLabel);
		add(name);
		add(capacityLabel);
		add(capacity);		
	}


	public Station getStation () {
		try {
			int cap = Integer.parseInt(capacity.getText());
			return new Station (name.getText(), cap, location);
		} catch (Exception e) {
			System.out.println("Failed to create station: Invalid input");
		}
		return null;
	}
}
