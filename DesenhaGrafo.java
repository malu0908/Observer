package tb17;
import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

public class DesenhaGrafo extends JPanel implements Observer {

	public void paintComponent(Graphics grafo) {
		super.paintComponent(grafo);
		this.setBackground(Color.black);

		int escala = 6;
		int j = 1, i = 0;
		grafo.setColor(Color.cyan);
		grafo.fillOval((int) (Matrizes.getMatriz()[Vetor.getVetor()[i]][0] * escala - 7),
				(int) (Matrizes.getMatriz()[Vetor.getVetor()[i]][1] * escala - 7), 15, 15);
		grafo.setColor(Color.black);
		for (i = 1; i < Vetor.getVetor().length; i++) {
			grafo.setColor(Color.red);
			grafo.fillOval((int) (Matrizes.getMatriz()[Vetor.getVetor()[i]][0] * escala - 7),
					(int) (Matrizes.getMatriz()[Vetor.getVetor()[i]][1] * escala - 7), 15, 15);
			grafo.setColor(Color.white);
		}
		for (i = 0; i < Vetor.getVetor().length && j < Vetor.getVetor().length; i++) {
			grafo.setColor(Color.white);
			grafo.drawLine((int) (Matrizes.getMatriz()[Vetor.getVetor()[i]][0] * escala),
					(int) (Matrizes.getMatriz()[Vetor.getVetor()[i]][1] * escala), (int) (Matrizes.getMatriz()[Vetor.getVetor()[j]][0] * escala),
					(int) (Matrizes.getMatriz()[Vetor.getVetor()[j]][1] * escala));
			grafo.setColor(Color.white);
			j++;
		}

		grafo.setColor(Color.MAGENTA);
		grafo.fillOval((int) (Matrizes.getMatriz()[Vetor.getVetor()[j - 1]][0] * escala - 7),
				(int) (Matrizes.getMatriz()[Vetor.getVetor()[j - 1]][1] * escala - 7), 15, 15);
		grafo.setColor(Color.white);

		grafo.setColor(Color.pink);
		grafo.drawLine((int) (Matrizes.getMatriz()[Vetor.getVetor()[j - 1]][0] * escala),
				(int) (Matrizes.getMatriz()[Vetor.getVetor()[j - 1]][1] * escala),
				(int) (Matrizes.getMatriz()[Vetor.getVetor()[0]][0] * escala), (int) (Matrizes.getMatriz()[Vetor.getVetor()[0]][1] * escala));

		
	}

	public void update(Observable o, Object arg) {
		Vetor.setVetor((int[])arg);
		this.repaint();
		try {Thread.sleep(100);} catch (InterruptedException ex) {}
	}
	
}