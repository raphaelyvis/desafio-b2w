package br.com.b2w.desafio.modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Classe que recebe os dados dos arquivos de matr�culas
 * e criam os arquivos com as matr�culas tratadas
 * 
 * @author rapha
 *
 */
public class Arquivo {
	
	private String arquivo;
	private String nome;
	
	/**
	 * M�todo que l� o arquivo de entrada
	 * e identifica se deve criar ou validar
	 * d�gitos verificadores
	 * 
	 * @param nome
	 * @return
	 * @throws IOException
	 */
	public ArrayList<String> leArquivo(String nome) throws Exception {
		
		this.nome = nome; // armazena o nome do arquivo de entrada
		ArrayList<String> linhas = new ArrayList<String>(); // cria um array com as matr�culas tratadas que ser� enviado para o arquivo de sa�da
		
		FileReader entrada = new FileReader(nome); // armazena o arquivo de entrada
		BufferedReader leEntrada = new BufferedReader(entrada); // l� o conte�do do arquivo de entrada
		
		// percorre todas as linhas at� o processo de repeti��o atingir o final do arquivo texto
		while (leEntrada.ready()) {
			
			String linha = leEntrada.readLine(); // l� as linhas do arquivo de entrada
			// aqui se considera que as matr�culas sempre ser�o separadas em cada linha
			
			if (linha.length() == 10) { // se o tamanho da matr�cula for igual a 10, ser� criado o d�gito verificador
				
				Matricula matricula = new Matricula(); // instancia um objeto da classe matricula
				
				String digitoVerificador = matricula.criaDigitoVerificador(linha); // chama o m�todo que cria o d�gito verificador e o armazena
				linha += digitoVerificador; // adiciona o d�gito verificador � matr�cula
				
			} else if (linha.charAt(10) == '-') { // se o tamanho da matr�cula for maior que 10 e conter separador, o d�gito verificador ser� validado
				
				Matricula matricula = new Matricula(); // instancia um objeto da classe matricula
				
				boolean validado = matricula.validaDigitoVerificador(linha); // chama o m�todo que valida o d�gito verificador e armazena o status de valida��o
				linha += " " + validado; // adiciona o status de valida��o ao lado da matr�cula
			} else {
				leEntrada.close();
				throw new Exception("O arquivo cont�m dados inv�lidos. Favor verificar");
			}
			
			linhas.add(linha); // adiciona a linha com a matr�cula modificada ao array de linhas
		}
		
		leEntrada.close(); // fecha o arquivo
		
		return linhas; // retorna o array com todas as matr�culas tratadas
	}
	
	/**
	 * M�todo que cria o arquivo de sa�da
	 * para os d�gitos verificadores criados ou validados
	 * 
	 * @param linhas
	 * @throws IOException
	 */
	public void escreveArquivo(ArrayList<String> linhas) throws IOException {
		
		int indice = this.nome.lastIndexOf("\\"); // identifica qual � o �ltimo caractere que cont�m o caminho do arquivo de entrada 
	    String diretorio = this.nome.substring(0, indice+1); // armazena o caminho do arquivo de entrada
	    
	    if (this.nome.contains("matriculas_sem_dv")) { // se for o arquivo de matr�culas sem d�gito verificador
	    	this.arquivo = diretorio + "matriculas_com_dv.txt"; // cria o arquivo com os d�gitos criado no mesmo caminho
	    	System.out.println();
	    	System.out.println("D�gitos verificadores criados");
	    }
	    if (this.nome.contains("matriculas_para_validar")) { // se for o arquivo de matr�culas para validar
	    	this.arquivo = diretorio + "matriculas_validadas.txt"; // cria o arquivo com os d�gitos criado no mesmo caminho
	    	System.out.println();
	    	System.out.println("Matr�culas validadas");
	    }
		
		FileWriter saida = new FileWriter(this.arquivo); // cria o arquivo de sa�da
		PrintWriter escreveSaida = new PrintWriter(saida); // escreve o conte�do no arquivo de sa�da
		
		for(String linha : linhas) {
			escreveSaida.println(linha); // percorre o array recebido e escreve cada matr�cula em uma linha
		}
		
		escreveSaida.close(); // fecha o arquivo de sa�da
		
		System.out.println();
		System.out.println("Arquivo criado com sucesso >> " + this.arquivo);
	}
	
}
