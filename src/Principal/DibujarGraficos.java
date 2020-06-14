package Principal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import java.util.Vector;

import Adicionales.Delay;
import EstilosGraficos.Barras;
import EstilosGraficos.CirculoBarras;
import EstilosGraficos.CircunferenciaPunteada;
import EstilosGraficos.Espital;
import EstilosGraficos.EstiloGrafico;
import EstilosGraficos.PantallaCompleta;
import EstilosGraficos.PiramideHorizontal;
import EstilosGraficos.PiramideVertical;
import EstilosGraficos.Pixel;
import EstilosGraficos.TransformCentrarGraficos;

public class DibujarGraficos extends TransformCentrarGraficos {
	private static final long serialVersionUID = 1L;
	EstiloGrafico estiloGrafico;
	public int[] arrayPrincipal;

	/**
	 * Constructor de iniciar el array con numeros ordenados.
	 */
	public DibujarGraficos() {
		barras();
		setBackground(Color.BLACK);
	}

	/**
	 * Constructor de iniciar el array con numeros ordenados.
	 */
	public void barras() {
		arrayPrincipal = new int[NUM_BARS];
		for (int i = 0; i < NUM_BARS; i++) {
			arrayPrincipal[i] = i;
		}
	}

	/**
	 * Desordena un Array.
	 *
	 * @param p el JFrame principal
	 */
	public void desordenarArray() {
		Random rng = new Random();
		for (int i = 0; i < NUM_BARS; i++) {
			int swapWidthIndex = rng.nextInt(NUM_BARS - 1);
			int temp = arrayPrincipal[i];
			arrayPrincipal[i] = arrayPrincipal[swapWidthIndex];
			arrayPrincipal[swapWidthIndex] = temp;
			Delay.delay(1);
			MainAplicacion.panelBarras.repaint();
		}
	}

	/**
	 * Pinta los componentes.
	 *
	 * @param g the g
	 */
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		super.paintComponent(graphics);
		int opcionGrafico = MainAplicacion.comboBoxTiposGraficos.getSelectedIndex();
		configEstilo(opcionGrafico, graphics);

		for (int i = 0; i < NUM_BARS; i++) {
			// Activar el modo multiculor.
			if (activarMulticolor)
				graphics.setPaint(getGradientPaint(i, arrayPrincipal[i], BAR_WIDTH));
			// Color por defecto de todos los graficos.
			else
				graphics.setColor(Color.WHITE);

			// Animacion de final del Sort.
			if (finSort) {
				if (mismo == i)
					graphics.setColor(Color.red);
				else if (anterioresMismo >= i)
					graphics.setColor(Color.green);
			}

			// Selecciona el tipo de grafico.
			menuSeleccionGraficos(opcionGrafico, i, graphics);
		}
	}

	/**
	 * Almacenar estilos.
	 *
	 * @return the vector
	 */
	public Vector<EstiloGrafico> almacenarEstilos() {
		Vector<EstiloGrafico> estilos = new Vector<EstiloGrafico>();
		estilos.add(new Barras());
		estilos.add(new PiramideHorizontal());
		estilos.add(new PiramideVertical());
		estilos.add(new PantallaCompleta());
		estilos.add(new Pixel());
		estilos.add(new CirculoBarras());
		estilos.add(new CircunferenciaPunteada());
		estilos.add(new Espital());
		return estilos;
	}

	/**
	 * Configuraciones sobre el estilo grafico y su sleecion de atributos.
	 *
	 * @param opcionGrafico the opcion grafico
	 * @param graphics      the graphics
	 */
	private void configEstilo(int opcionGrafico, Graphics2D graphics) {
		// Cambio la cantidad de elementos que se pintan en pantalla.
		// Simepre que sean Objetos Transfomaciones tendran multicolor.
		// Siendo un Objeto de la Clase PantallaCompleta siempre tendra multicolor, pero
		// no restriccion en la cantidad de barras.
		if (almacenarEstilos().get(opcionGrafico) instanceof TransformCentrarGraficos) {
			graphics.translate(translateX, translateY);
			MainAplicacion.sliderTamBarras.setMinimum(6);
			activarMulticolor = true;
			MainAplicacion.tglbtnMulticolor.setSelected(activarMulticolor);
		} else if (almacenarEstilos().get(opcionGrafico) instanceof PantallaCompleta) {
			activarMulticolor = true;
			MainAplicacion.tglbtnMulticolor.setSelected(activarMulticolor);
		} else {
			MainAplicacion.sliderTamBarras.setMinimum(1);
		}
	}

	/**
	 * Selecciona y crea un Objeto del estilo seleccionado.
	 *
	 * @param opcionGrafico the opcion grafico
	 * @param i             the i
	 * @param graphics      the graphics
	 */
	private void menuSeleccionGraficos(int opcionGrafico, int i, Graphics2D graphics) {
		switch (opcionGrafico) {
		case 0:estiloGrafico = new Barras					(i, arrayPrincipal, graphics);break;
		case 1:estiloGrafico = new PiramideHorizontal		(i, arrayPrincipal, graphics);break;
		case 2:estiloGrafico = new PiramideVertical			(i, arrayPrincipal, graphics);break;
		case 3:estiloGrafico = new PantallaCompleta			(i, arrayPrincipal, graphics);break;
		case 4:estiloGrafico = new Pixel					(i, arrayPrincipal, graphics);break;
		case 5:estiloGrafico = new CirculoBarras			(graphics);					  break;
		case 6:estiloGrafico = new CircunferenciaPunteada	(i, graphics);				  break;
		case 7:estiloGrafico = new Espital					(i, arrayPrincipal, graphics);break;
		}
	}

	protected GradientPaint getGradientPaint(int position, int value, int columnWidth) {
		float startH = value / (NUM_BARS * 1f);
		float finishH = (value + 1) / (NUM_BARS * 1f);
		float S = 1; // Saturacion
		float B = 1; // Brillo
		Color startColor = Color.getHSBColor(startH, S, B);
		Color finishColor = Color.getHSBColor(finishH, S, B);
		int x = 2 * BAR_WIDTH + columnWidth * position;
		return new GradientPaint(x, 0, startColor, x + columnWidth, 0, finishColor);
	}

	/**
	 * Gets the preferred size.
	 *
	 * @return the preferred size
	 */
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(WIN_WIDTH, WIN_HEIGHT);
	}
}
