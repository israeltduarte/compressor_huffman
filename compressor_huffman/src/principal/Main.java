package principal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import outros.Texto;

public class Main {

	public static void main(String[] args) {
		System.out.println("Início\n");

		String entrada = "entrada.txt";

		String palavra = "";
		try {
			FileReader arq = new FileReader(entrada);
			BufferedReader lerArq = new BufferedReader(arq);
			palavra = lerArq.readLine();
			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}

		Texto texto = new Texto(palavra);

		System.out.println(texto);

		System.out.println("\nFim");
	}

}
