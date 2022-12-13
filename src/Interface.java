import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Interface extends JPanel {
	City[] cities;

	public Interface(City[] cities) {
		this.cities = cities;
	}

	public void paint(Graphics g) {
		// affichage des villes
		for (City city : cities) {
			g.drawOval(city.x, city.y, 10, 10);
		}

		// affichage des lignes de phéromones
		g.drawLine(30, 30, 200, 200);
		BasicStroke line = new BasicStroke(5.0f); // modif l'épaisseur de la ligne tracée
		((Graphics2D) g).setStroke(line);
		g.drawLine(0, 0, 120, 230);
	}
}