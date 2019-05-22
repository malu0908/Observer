package tb17;

import javax.swing.JFrame;

public class DesenhaTela extends JFrame {
	private int[] seqCidade = new int[58];
	private double[][] matriz = new double[58][2];
	private DesenhaGrafo grafo;
	
	public DesenhaTela() {
		this.setSize(750, 750);
		this.setTitle("Caixeiro Viajante - Rota");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.grafo = new DesenhaGrafo();
		this.add(grafo);
	}

	public DesenhaGrafo getDesenhaGrafo() {
		return this.grafo;
	}
}
