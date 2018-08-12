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
		
		Scanner ler = new Scanner(System.in); // instancia um Scanner para ler a entrada do usu�rio
		 
	    System.out.println();
		System.out.print("Informe o nome do arquivo texto de matr�culas a ser tratado: ");
	    String arquivoDeEntrada = ler.nextLine(); // l� o caminho do arquivo informado pelo usu�rio
	    
	    Arquivo arquivo = new Arquivo(arquivoDeEntrada); // cria o nome e caminho do arquivo de sa�da de acordo com o arquivo de entrada
	    String arquivoDeSaida = arquivo.toString(); // armazena o nome e caminho do arquivo de sa�da
		
		try {
			
			FileReader entrada = new FileReader(arquivoDeEntrada); // armazena o arquivo de entrada
			BufferedReader leEntrada = new BufferedReader(entrada); // l� o conte�do do arquivo de entrada
			
			FileWriter saida = new FileWriter(arquivoDeSaida); // cria o arquivo de sa�da
			PrintWriter escreveSaida = new PrintWriter(saida); // escreve o conte�do no arquivo de sa�da
			
			String linha = leEntrada.readLine(); // l� a primeira linha do arquivo de entrada
			// aqui se considera que as matr�culas sempre ser�o separadas em cada linha
			
			// l� cada linha at� o processo de repeti��o atingir o final do arquivo texto (quando a linha � nula)
			while (linha != null) {
				
				if (linha.length() == 10) { // se o tamanho da matr�cula for igual a 10, ser� criado o d�gito verificador
					
					Matricula matricula = new Matricula(); // instancia um objeto da classe matricula
					
					String digitoVerificador = matricula.criaDigitoVerificador(linha); // chama o m�todo que cria o d�gito verificador e o armazena
					escreveSaida.println(linha + digitoVerificador); // escreve a matr�cula com d�gito verificador no arquivo de sa�da
					
				} else if (linha.length() > 10) { // se o tamanho da matr�cula for maior que 10, o d�gito verificador ser� validado
					
					Matricula matricula = new Matricula(); // instancia um objeto da classe matricula
					
					boolean validado = matricula.validaDigitoVerificador(linha); // chama o m�todo que valida o d�gito verificador e armazena o status de valida��o
					escreveSaida.println(linha + " " + validado); // escreve a matr�cula e o status de valida��o no arquivo de sa�da
					
				}
				
				linha = leEntrada.readLine(); // l� da segunda at� a �ltima linha do arquivo de matr�culas sem d�gito verificador
				
			}
			
			entrada.close(); // fecha o arquivo de entrada
			saida.close(); // fecha o arquivo de sa�da
			ler.close(); // fecha a entrada do usu�rio
			
			System.out.println();
			
			if (arquivoDeSaida.contains("matriculas_com_dv.txt")) {
				System.out.println("D�gitos verificadores criados");
			}
			if (arquivoDeSaida.contains("matriculas_validadas.txt")) {
				System.out.println("Matr�culas validadas");
			}
			
			System.out.println();
			System.out.println("Arquivo criado com sucesso >> " + arquivo);
			
		} catch (IOException e) {
			System.out.println();
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage()); // caso o arquivo de entrada n�o posso ser lido, a exce��o � chamada
	    }
		
	}
	
}
