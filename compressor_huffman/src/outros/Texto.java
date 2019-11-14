package outros;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Texto {

	private String textoOriginal;

	@Override
	public String toString() {
		return "Texto [\ntextoOriginal=" + textoOriginal + ", \ntextoBinario=" + textoBinario + ", \narrayEmChar="
				+ Arrays.toString(arrayEmChar) + ", \nlistaEmBinario=" + listaEmBinario + ", \ntextoMapeado="
				+ textoMapeado + ", \narvore=" + arvore + ", \ntabelaCodigo=" + tabelaCodigo + ", \ntextoComprimido="
				+ textoComprimido + "\ntaxaDeCompressao=" + this.getTaxaDeCompressao() + "%\n]";
	}

	public void setTabelaCodigo(List<Codigo> tabelaCodigo) {
		this.tabelaCodigo = tabelaCodigo;
	}

	public List<Nodo> getListaNodos() {
		return listaNodos;
	}

	public void setListaNodos(List<Nodo> listaNodos) {
		this.listaNodos = listaNodos;
	}

	private String textoBinario;
	private char[] arrayEmChar;
	private List<String> listaEmBinario;
	private List<Par> textoMapeado;
	private Arvore arvore;
	private List<Nodo> listaNodos = new ArrayList<>();
	private List<Codigo> tabelaCodigo = new ArrayList<>();
	private String textoComprimido;

	public Texto(String textoOriginal) {
		this.textoOriginal = textoOriginal.replace(' ', '-');
		this.arrayEmChar = this.transformaEmArrayDeChar();
		this.textoBinario = this.transformaEmTextoBinario();
		this.listaEmBinario = this.transformaEmListaBinaria();
		this.textoMapeado = this.transformaEmTextoMapeado();
		this.arvore = this.transformaEmArvore();
		this.textoComprimido = this.transformaEmTextoComprimido();
	}

	private String transformaEmTextoComprimido() {
		String resultado = "";

		this.tabelaCodigo = this.getTabelaCodigo();

		for (char c : this.arrayEmChar) {
			for (Codigo codigo : this.tabelaCodigo) {
				if (codigo.getNome() == c) {
					resultado += codigo.getCodigo();
				}
			}
		}

		return resultado;
	}

	public String getTextoComprimido() {
		return this.textoComprimido;
	}

	public void setTextoComprimido(String textoComprimido) {
		this.textoComprimido = textoComprimido;
	}

	private List<Par> transformaEmTextoMapeado() {
		char[] listaDeCaracteres = this.textoOriginal.toCharArray();

		HashMap<Character, Integer> tabelaDeCodigos = new HashMap<>();

		for (char c : listaDeCaracteres) {
			if (tabelaDeCodigos.get(c) != null) {
				tabelaDeCodigos.put(c, tabelaDeCodigos.get(c) + 1);
			} else {
				tabelaDeCodigos.put(c, 1);
			}
		}

		List<Par> lista = new ArrayList<>();

		tabelaDeCodigos.entrySet().stream().sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
				.limit(100).forEach(e -> lista.add(new Par("" + e.getKey(), e.getValue())));

		for (Par par : lista) {
			this.listaNodos.add(new Nodo(par));
		}

		return lista;

	}

	private List<String> transformaEmListaBinaria() {
		ArrayList<String> listaDeBinarios = new ArrayList<>();
		for (char c : arrayEmChar) {
			listaDeBinarios.add(Integer.toBinaryString(c));
		}
		return listaDeBinarios;
	}

	private char[] transformaEmArrayDeChar() {
		return this.textoOriginal.toCharArray();
	}

	private String transformaEmTextoBinario() {
		String result = "";
		for (char c : arrayEmChar) {
			String caractere = Integer.toBinaryString(c);
			if (caractere.length() < 8) {
				int tamanho = caractere.length();
				int auxiliar = 8 - tamanho;
				for (int i = 0; i < auxiliar; i++) {
					result += "0";
				}
				result += caractere;
			}
		}

		return result;
	}

	public String getTextoOriginal() {
		return textoOriginal;
	}

	public void setTextoOriginal(String textoOriginal) {
		this.textoOriginal = textoOriginal;
	}

	public String getTextoBinario() {
		return textoBinario;
	}

	public void setTextoBinario(String textoBinario) {
		this.textoBinario = textoBinario;
	}

	public char[] getArrayEmChar() {
		return arrayEmChar;
	}

	public void setArrayEmChar(char[] arrayEmChar) {
		this.arrayEmChar = arrayEmChar;
	}

	public List<String> getListaEmBinario() {
		return listaEmBinario;
	}

	public void setListaEmBinario(List<String> listaEmBinario) {
		this.listaEmBinario = listaEmBinario;
	}

	public List<Par> getTextoMapeado() {
		return this.textoMapeado;
	}

	public void setTextoMapeado(List<Par> textoMapeado) {
		this.textoMapeado = textoMapeado;
	}

	public Arvore transformaEmArvore() {
		if (this.textoMapeado.size() < 2) {

			System.out.println("Entrada de dados inválida. Apenas um único caractere.\n");
			throw new UmElementoException();
		}

		while (true) {
			Nodo novo = new Nodo();
			this.listaNodos = this.listaNodos.stream().sorted(Comparator.comparingInt(Nodo::getValor).reversed())
					.collect(Collectors.toList());

			if (this.listaNodos.size() < 2) {
				this.arvore = new Arvore(this.listaNodos.get(0));
				this.textoComprimido = this.transformaEmTextoComprimido();
				return this.arvore;
			} else {
				Nodo esquerda = this.listaNodos.get(this.listaNodos.size() - 1);
				Nodo direita = this.listaNodos.get(this.listaNodos.size() - 2);

				this.listaNodos.remove(esquerda);
				this.listaNodos.remove(direita);

				novo.setEsquerda(esquerda);
				novo.setDireita(direita);
				novo.setValor(esquerda.getValor() + direita.getValor());
				novo.setNome(esquerda.getNome() + direita.getNome());

				this.listaNodos.add(novo);
			}
		}

	}

	public void setArvore(Arvore arvore) {
		this.arvore = arvore;
	}

	public Arvore getArvore() {
		return this.arvore;
	}

	public List<Codigo> getTabelaCodigo() {

		List<Codigo> novaTabela = new ArrayList<>();

		for (Par par : this.textoMapeado) {
			char caractere = par.getChave().charAt(0);
			novaTabela.add(new Codigo(caractere, this.encontraCaractere(caractere, this.arvore.getRaiz())));
		}

		this.tabelaCodigo = novaTabela;

		return this.tabelaCodigo;
	}

	public String encontraCaractere(Character caractere, Nodo nodo) {
		if (nodo.getDireita() == null && nodo.getEsquerda() == null) {
			return "";
		}

		if (nodo.getDireita().getNome().indexOf(caractere) > -1) {
			if (nodo.getDireita().getNome().length() > 1) {
				return "1" + this.encontraCaractere(caractere, nodo.getDireita());
			} else {
				return "1";
			}
		} else {
			if (nodo.getEsquerda().getNome().length() > 1) {
				return "0" + this.encontraCaractere(caractere, nodo.getEsquerda());
			} else {
				return "0";
			}
		}
	}

	public double getTaxaDeCompressao() {
		return 100 - (100.0 * this.getTextoComprimido().length() / this.getTextoBinario().length());
	}

}
