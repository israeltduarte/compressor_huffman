package outros;

public class Arvore {

	@Override
	public String toString() {
		return "Arvore [" + raiz + "]";
	}

	public Arvore(Nodo raiz) {
		this.raiz = raiz;
	}

	public Arvore() {
	}

	private Nodo raiz;

	public Nodo getRaiz() {
		return raiz;
	}

	public void setRaiz(Nodo raiz) {
		this.raiz = raiz;
	}

}
