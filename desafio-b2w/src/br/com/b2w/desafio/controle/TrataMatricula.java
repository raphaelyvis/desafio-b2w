package br.com.b2w.desafio.controle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import br.com.b2w.desafio.modelo.Arquivo;
import br.com.b2w.desafio.modelo.Matricula;

public class TrataMatricula {
	
	public static void main(String[] args) {
		
		Scanner ler = new Scanner(System.in); // instancia um Scanner para ler a entrada do usuário
		 
	    System.out.println();
		System.out.print("Informe o nome do arquivo texto de matrículas a ser tratado: ");
	    String arquivoDeEntrada = ler.nextLine(); // lê o caminho do arquivo informado pelo usuário
	    
	    Arquivo arquivo = new Arquivo(arquivoDeEntrada); // cria o nome e caminho do arquivo de saída de acordo com o arquivo de entrada
	    String arquivoDeSaida = arquivo.toString(); // armazena o nome e caminho do arquivo de saída
		
		try {
			
			FileReader entrada = new FileReader(arquivoDeEntrada); // armazena o arquivo de entrada
			BufferedReader leEntrada = new BufferedReader(entrada); // lê o conteúdo do arquivo de entrada
			
			FileWriter saida = new FileWriter(arquivoDeSaida); // cria o arquivo de saída
			PrintWriter escreveSaida = new PrintWriter(saida); // escreve o conteúdo no arquivo de saída
			
			String linha = leEntrada.readLine(); // lê a primeira linha do arquivo de entrada
			// aqui se considera que as matrículas sempre serão separadas em cada linha
			
			// lê cada linha até o processo de repetição atingir o final do arquivo texto (quando a linha é nula)
			while (linha != null) {
				
				if (linha.length() == 10) { // se o tamanho da matrícula for igual a 10, será criado o dígito verificador
					
					Matricula matricula = new Matricula(); // instancia um objeto da classe matricula
					
					String digitoVerificador = matricula.criaDigitoVerificador(linha); // chama o método que cria o dígito verificador e o armazena
					escreveSaida.println(linha + digitoVerificador); // escreve a matrícula com dígito verificador no arquivo de saída
					
				} else if (linha.length() > 10) { // se o tamanho da matrícula for maior que 10, o dígito verificador será validado
					
					Matricula matricula = new Matricula(); // instancia um objeto da classe matricula
					
					boolean validado = matricula.validaDigitoVerificador(linha); // chama o método que valida o dígito verificador e armazena o status de validação
					escreveSaida.println(linha + " " + validado); // escreve a matrícula e o status de validação no arquivo de saída
					
				}
				
				linha = leEntrada.readLine(); // lê da segunda até a última linha do arquivo de matrículas sem dígito verificador
				
			}
			
			entrada.close(); // fecha o arquivo de entrada
			saida.close(); // fecha o arquivo de saída
			ler.close(); // fecha a entrada do usuário
			
			System.out.println();
			
			if (arquivoDeSaida.contains("matriculas_com_dv.txt")) {
				System.out.println("Dígitos verificadores criados");
			}
			if (arquivoDeSaida.contains("matriculas_validadas.txt")) {
				System.out.println("Matrículas validadas");
			}
			
			System.out.println();
			System.out.println("Arquivo criado com sucesso >> " + arquivo);
			
		} catch (IOException e) {
			System.out.println();
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage()); // caso o arquivo de entrada não posso ser lido, a exceção é chamada
	    }
		
	}
	
}
