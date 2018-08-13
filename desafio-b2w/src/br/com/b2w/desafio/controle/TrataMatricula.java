package br.com.b2w.desafio.controle;

import java.util.ArrayList;
import java.util.Scanner;

import br.com.b2w.desafio.modelo.Arquivo;

public class TrataMatricula {
	
	public static void main(String[] args) {
		
		Scanner ler = new Scanner(System.in); // instancia um Scanner para ler a entrada do usu�rio
		 
	    System.out.print("Informe o nome do arquivo texto de matr�culas a ser tratado: ");
	    String arquivoDeEntrada = ler.nextLine(); // l� o caminho do arquivo informado pelo usu�rio
	    
	    // valida se o nome do arquivo informado est� correto
	    while(!arquivoDeEntrada.contains("matriculas_sem_dv.txt") && !arquivoDeEntrada.contains("matriculas_para_validar.txt")) {
	    	System.out.println();
	    	System.out.print("Nome de arquivo inv�lido. Informe o nome do arquivo texto de matr�culas a ser tratado: ");
		    arquivoDeEntrada = ler.nextLine();
	    }
	    
	    Arquivo arquivo = new Arquivo(); // cria o nome e caminho do arquivo de sa�da de acordo com o arquivo de entrada
	    
	    ArrayList<String> matriculas = new ArrayList<String>(); // Cria um array que ir� aramazenar todas as matr�culas do arquivo de entrada
	    
	    try {
	    	matriculas = arquivo.leArquivo(arquivoDeEntrada); // Chama o m�todo de leitura do arquivo e armazena as matr�culas tratadas
		    arquivo.escreveArquivo(matriculas); // Chama o m�todo para escrever o arquivo com as matr�culas tratadas
		    
		} catch (Exception e) {
			System.out.println();
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage()); // caso o arquivo de entrada n�o posso ser lido, a exce��o � chamada
		}
	    
	    ler.close(); // fecha a entrada do usu�rio
		
	}
	
}
