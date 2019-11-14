package outros;

public class Codigo {

	public Codigo(Character caracter, String codigo) {
		this.caracter = caracter;
		this.codigo = codigo;
	}

	private Character caracter;
	private String codigo;

	public Character getNome() {
		return caracter;
	}

	public void setNome(Character caracter) {
		this.caracter = caracter;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "[" + caracter + ", " + codigo + "]";
	}
}
