package outros;

public class Pilha {

	public Nodo[] pilha;
	public int posicaoPilha;

	public Pilha(int tamanho) {
		this.posicaoPilha = -1;
		this.pilha = new Nodo[tamanho];
	}

	public boolean pilhaVazia() {
		if (this.posicaoPilha == -1) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		String result = "Pilha [";
		for (int i = 0; i <= this.posicaoPilha; i++) {
			result += pilha[i] + ", ";
		}
		return result.substring(0, result.length() - 2) + "]";
	}

	public int tamanho() {
		if (this.pilhaVazia()) {
			return 0;
		}
		return this.posicaoPilha + 1;
	}

	public Nodo exibeUltimoValor() {
		if (this.pilhaVazia()) {
			return null;
		}
		return this.pilha[this.posicaoPilha];
	}

	public Nodo desempilhar() {
		if (pilhaVazia()) {
			return null;
		}
		return this.pilha[this.posicaoPilha--];
	}

	public void empilhar(Nodo valor) {
		if (this.posicaoPilha < this.pilha.length - 1) {
			this.pilha[++posicaoPilha] = valor;
		}
	}

}