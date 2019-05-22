package tb17;
import java.io.*;
import Lista.*;
import java.util.*;
import javax.swing.JFrame;

public class tb17 extends Observable {
	public boolean visitados[] = new boolean[58];
	public int seqCidade[] = new int[58];

	public void copiaLista(Lista l) {
		if(l != null) {
			Node atual = l.top;
			int i = 0;
			while(atual != null) {
				seqCidade[i] = atual.value;
				atual = atual.next;
				i++;
			}
		}
	}
	
	public Lista procuraMelhorPosicao(Lista l, double SomaAnt) throws IOException {
		int vezes = 0;
		double Soma = 0.0;

		while (vezes < 1000) {
			Random random = new Random();
			int j = random.nextInt(58);

			int valor = l.buscaValor(j).value;
			// troca o conteúdo dos índices i e j do vetor
			int melhorPosicao = mudaPosicao(j, l, valor, SomaAnt);
			l.insereIndice(valor, melhorPosicao);
			Soma = calculaDistancia(l);
			if (Soma > SomaAnt) {
				l.removePorIndice(melhorPosicao);
				l.insereIndice(valor, j);
			}
			else {
				copiaLista(l);
				notifica();
			}
			vezes++;
		}
		return l;
	}

	public int mudaPosicao(int i, Lista l, int valor, double SomaAnt) throws IOException {
		double Soma = 0.0;

		int j = 0;
		int melhorIndice = j;

		Node removido = l.removeList(valor);
		while (l.buscaValor(j) != null) {
			l.insereIndice(removido.value, j);

			Soma = calculaDistancia(l);
			if (Soma < SomaAnt) {
				SomaAnt = Soma;
				melhorIndice = j;
			}
			removido = l.removeList(valor);
			j++;
		}
		return melhorIndice;
	}

	public Lista procuraMaiorOrigem(Lista l, boolean visitados[]) throws IOException {
		double SomaAnt = 500000.0;
		double Soma = 0.0;
		
		Lista melhor = new Lista();
		for (int i = 0; i < Matrizes.getMatrizAdj().length; i++) {
			l.insereIndice(i, 0);
			visitados[i] = true;
			vizinhoMaisProx(l, 1, i, visitados);

			// inicializando com false novamente
			for (int j = 0; j < visitados.length; j++) {
				visitados[j] = false;
			}

			Soma = calculaDistancia(l);

			if (Soma < SomaAnt) {
				SomaAnt = Soma;
				melhor.clean();
				melhor.clonar(l);
				copiaLista(l);
				notifica();
			}
			l.clean();
		}
		return melhor;
	}

	public int buscaMelhor(int i, boolean visitados[]) throws IOException {
		double menor = 100000.0;
		int j;
		int indice = 0;

		for (j = 0; j < Matrizes.getMatrizAdj().length; j++) {
			// se o vertice nao tiver sido visitado
			if (visitados[j] == false)
				// se a distancia do vertice atual for menor que o menor anterior o menor recebe
				// ele e o indice recebe o vertice
				if (Matrizes.getMatrizAdj()[i][j] < menor && i != j) {
					menor = Matrizes.getMatrizAdj()[i][j];
					indice = j;
				}
		}
		return indice;
	}

	public Lista vizinhoMaisProx(Lista l, int j, int w, boolean visitados[]) throws IOException {

		for (int i = 1; i < Matrizes.getMatrizAdj().length; i++) {
			int melhor = buscaMelhor(w, visitados);
			visitados[melhor] = true;
			l.insereIndice(melhor, j);
			j++;
			w = melhor;
		}
		Node atual = l.top;
		int i = 0;
		while (atual != null) {
			seqCidade[i] = atual.value;
			i++;
			atual = atual.next;
		}
		return l;
	}

	public double calculaDistancia(Lista l) throws IOException {
		double soma = 0.0;

		// soma as distancias das cidades consecutivas do vetor v
		for (int i = 0; i < Matrizes.getMatrizAdj().length - 1; i++) {
			Node valor1 = l.buscaValor(i);
			Node valor2 = l.buscaValor(i + 1);
			soma += Matrizes.getMatrizAdj()[valor1.value][valor2.value];
		}
		// soma as distancias da primeira cidade com a ultima
		soma += Matrizes.getMatrizAdj()[l.top.value][l.down.value];
		return soma;
	}
	
	public void notifica() {
		setChanged();
		notifyObservers(seqCidade);
	}

	public void executa() throws IOException {
		
		//inicializando as matrizes
		Matrizes.inicializa();

		Lista cidades = new Lista();
		for (int i = 0; i < 58; i++) {
			cidades.toList(i);
		}

		copiaLista(cidades);
		notifica();
		cidades.clean();

		// aplica o metodo do vizinhoMaisProximo procurando a melhor origem
		cidades = procuraMaiorOrigem(cidades, visitados);
		double SomaAnt = calculaDistancia(cidades);


		// aplica o metodo que procura a melhor posicao para determinadas cidades
		cidades = procuraMelhorPosicao(cidades, SomaAnt);
		double Soma = calculaDistancia(cidades);
		System.out.println("Distância Final: " + Soma);

	}
}