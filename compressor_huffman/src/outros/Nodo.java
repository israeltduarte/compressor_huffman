package outros;

public class Nodo {

	private String nome;
	private int valor;
	private Nodo esquerda;
	private Nodo direita;

	public Nodo() {
	}

	public Nodo(Nodo esquerda, Nodo direita) {
		this.esquerda = esquerda;
		this.direita = direita;
	}

	public Nodo(Par par) {
		this.nome = new String("" + par.getChave());
		this.valor = par.getValor();
	}

	public Nodo getEsquerda() {
		return esquerda;
	}

	public Nodo getDireita() {
		return direita;
	}

	public void setEsquerda(Nodo esquerda) {
		this.esquerda = esquerda;
	}

	public void setDireita(Nodo direita) {
		this.direita = direita;
	}

	@Override
	public String toString() {
		return "[" + nome + ", " + valor + "]";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public Par toPar() {
		return new Par(this.getNome(), this.getValor());
	}

}
