package br.com.b2w.desafio.controle;

import java.util.ArrayList;
import java.util.Scanner;

import br.com.b2w.desafio.modelo.Arquivo;

public class TrataMatricula {
	
	public static void main(String[] args) {
		
		Scanner ler = new Scanner(System.in); // instancia um Scanner para ler a entrada do usuário
		 
	    System.out.print("Informe o nome do arquivo texto de matrículas a ser tratado: ");
	    String arquivoDeEntrada = ler.nextLine(); // lê o caminho do arquivo informado pelo usuário
	    
	    // valida se o nome do arquivo informado está correto
	    while(!arquivoDeEntrada.contains("matriculas_sem_dv.txt") && !arquivoDeEntrada.contains("matriculas_para_validar.txt")) {
	    	System.out.println();
	    	System.out.print("Nome de arquivo inválido. Informe o nome do arquivo texto de matrículas a ser tratado: ");
		    arquivoDeEntrada = ler.nextLine();
	    }
	    
	    Arquivo arquivo = new Arquivo(); // cria o nome e caminho do arquivo de saída de acordo com o arquivo de entrada
	    
	    ArrayList<String> matriculas = new ArrayList<String>(); // Cria um array que irá aramazenar todas as matrículas do arquivo de entrada
	    
	    try {
	    	matriculas = arquivo.leArquivo(arquivoDeEntrada); // Chama o método de leitura do arquivo e armazena as matrículas tratadas
		    arquivo.escreveArquivo(matriculas); // Chama o método para escrever o arquivo com as matrículas tratadas
		    
		} catch (Exception e) {
			System.out.println();
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage()); // caso o arquivo de entrada não posso ser lido, a exceção é chamada
		}
	    
	    ler.close(); // fecha a entrada do usuário
		
	}
	
}
