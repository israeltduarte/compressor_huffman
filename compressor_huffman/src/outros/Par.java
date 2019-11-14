package outros;

import java.util.Map.Entry;

public class Par {

	private String chave;
	private Integer valor;

	public Par() {
	}

	public Par(Character chave, Integer valor) {
		this.chave = "" + chave;
		this.valor = valor;
	}

	public Par(String chave, Integer valor) {
		this.chave = chave;
		this.valor = valor;
	}

	public Par(Entry<String, Integer> e) {
		this.chave = e.getKey();
		this.valor = e.getValue();
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "[" + chave + ", " + valor + "]";
	}

}
