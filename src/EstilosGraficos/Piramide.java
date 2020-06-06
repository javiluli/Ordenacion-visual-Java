package EstilosGraficos;

import java.awt.Graphics2D;

import Sorts.Sorts;

public class Piramide extends EstiloGrafico {
	private static final long serialVersionUID = 1L;
	public Piramide() {
	}
	public Piramide(int i, Graphics2D graphics) {
		height = (Sorts.n[i] * BAR_HEIGHT) + BAR_HEIGHT;
		xBegin = i + (BAR_WIDTH - 1) * i;
		yBegin = (WIN_HEIGHT - height) / 2;
		graphics.fillRect(xBegin, yBegin, BAR_HEIGHT, height);
	}
}