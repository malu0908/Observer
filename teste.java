package tb17;

import java.io.IOException;

public class teste {
	public static void main(String[] args) throws IOException {
		tb17 projeto = new tb17();
		DesenhaTela tela = new DesenhaTela();
		projeto.addObserver(tela.getDesenhaGrafo());
		projeto.executa();
	}
}
