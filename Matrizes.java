package tb17;
import java.io.*;

public class Matrizes {
	public static Matrizes matrizes;
	private static double matrizAdj[][];
	private static double matriz[][];
	
	public static double[][] getMatrizAdj() {
		return matrizAdj;
	}
	
	public static double[][] getMatriz() {
		return matriz;
	}
	
	public static void inicializa() throws IOException {
		if(matrizes == null) {
			LeArq();
			matrizes = new Matrizes();
		}
	}
	
	public static Matrizes getMatrizes() throws IOException {
		return matrizes;
	}

	public static void LeArq() throws IOException{
	
		FileReader arq = new FileReader("/home/2018.1.08.015/Desktop/tb17/src/new.txt");

		BufferedReader lerArq = new BufferedReader(arq);

		String tamanho = lerArq.readLine();
		int tam = Integer.parseInt(tamanho);
		matrizAdj = new double[tam][tam];
		matriz = new double[tam][2];
		
		// lendo as coordenadas
		String linha = lerArq.readLine();
		String[] x = null;
		int i = 0;
		while (linha != null) {
			x = linha.split(",");
			for (int j = 0; j < matriz[0].length; j++) {
				matriz[i][j] = Double.parseDouble(x[j]);
			}
			linha = lerArq.readLine();
			i++;
		}

		// calcula as distancias para montar a matriz de adjcencia
		for (i = 0; i < matrizAdj.length; i++) {
			for (int j = 0; j < matrizAdj[0].length; j++) {
				double distanciaX = matriz[j][0] - matriz[i][0];
				double distanciaY = matriz[j][1] - matriz[i][1];

				double distancia = Math.sqrt(Math.pow(distanciaX, 2) + Math.pow(distanciaY, 2));
				matrizAdj[i][j] = distancia;
				matrizAdj[j][i] = distancia;
			}
		}
		
		lerArq.close();
	}
}
