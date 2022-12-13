import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Interface extends JPanel {
	City[] cities;
	Pheromone[][] pheromones;

	public Interface(City[] cities, Pheromone[][] pheromones) {
		this.cities = cities;
		this.pheromones = pheromones;
	}

	public void paint(Graphics g) {
		// affichage des villes
		for (City city : cities) {
			g.drawOval(city.x, city.y, 10, 10);
		}

		for (int i = 0; i < cities.length; i++)
			for (int j = 0; j < i; j++) {

				if (pheromones[i][j].quantitePheromone / (Pheromone.borneMax - Pheromone.borneMin) > 0.03) {
					g.setColor(Color.RED);
					BasicStroke line = new BasicStroke(3.0f);
					((Graphics2D) g).setStroke(line);
					g.drawLine(pheromones[i][j].ville1.x, pheromones[i][j].ville1.y, pheromones[i][j].ville2.x,
							pheromones[i][j].ville2.y);
				}

				else if (pheromones[i][j].quantitePheromone / (Pheromone.borneMax - Pheromone.borneMin) > 0.02) {
					BasicStroke line = new BasicStroke(1.0f);
					((Graphics2D) g).setStroke(line);
					g.setColor(Color.BLUE);
					g.drawLine(pheromones[i][j].ville1.x, pheromones[i][j].ville1.y, pheromones[i][j].ville2.x,
							pheromones[i][j].ville2.y);
				}

				else if (pheromones[i][j].quantitePheromone / (Pheromone.borneMax - Pheromone.borneMin) > 0.01) {
					BasicStroke line = new BasicStroke(0.0f);
					((Graphics2D) g).setStroke(line);
					g.setColor(Color.LIGHT_GRAY);
					g.drawLine(pheromones[i][j].ville1.x, pheromones[i][j].ville1.y, pheromones[i][j].ville2.x,
							pheromones[i][j].ville2.y);
				}
			}

		/*
		 * if (pheromones[i][j].nbPassage != 0) { g.setColor(Color.LIGHT_GRAY);
		 * g.drawLine(pheromones[i][j].ville1.x, pheromones[i][j].ville1.y,
		 * pheromones[i][j].ville2.x, pheromones[i][j].ville2.y); }
		 */
	}

	// affichage des lignes de phéromones
	/*
	 * g.drawLine(30, 30, 200, 200); BasicStroke line = new BasicStroke(5.0f); //
	 * modif l'épaisseur de la ligne tracée ((Graphics2D) g).setStroke(line);
	 * g.drawLine(0, 0, 120, 230);
	 */
}