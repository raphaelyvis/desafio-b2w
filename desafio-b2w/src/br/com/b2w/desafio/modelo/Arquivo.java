package br.com.b2w.desafio.modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Classe que recebe os dados dos arquivos de matrículas
 * e criam os arquivos com as matrículas tratadas
 * 
 * @author rapha
 *
 */
public class Arquivo {
	
	private String arquivo;
	private String nome;
	
	/**
	 * Método que lê o arquivo de entrada
	 * e identifica se deve criar ou validar
	 * dígitos verificadores
	 * 
	 * @param nome
	 * @return
	 * @throws IOException
	 */
	public ArrayList<String> leArquivo(String nome) throws Exception {
		
		this.nome = nome; // armazena o nome do arquivo de entrada
		ArrayList<String> linhas = new ArrayList<String>(); // cria um array com as matrículas tratadas que será enviado para o arquivo de saída
		
		FileReader entrada = new FileReader(nome); // armazena o arquivo de entrada
		BufferedReader leEntrada = new BufferedReader(entrada); // lê o conteúdo do arquivo de entrada
		
		// percorre todas as linhas até o processo de repetição atingir o final do arquivo texto
		while (leEntrada.ready()) {
			
			String linha = leEntrada.readLine(); // lê as linhas do arquivo de entrada
			// aqui se considera que as matrículas sempre serão separadas em cada linha
			
			if (linha.length() == 10) { // se o tamanho da matrícula for igual a 10, será criado o dígito verificador
				
				Matricula matricula = new Matricula(); // instancia um objeto da classe matricula
				
				String digitoVerificador = matricula.criaDigitoVerificador(linha); // chama o método que cria o dígito verificador e o armazena
				linha += digitoVerificador; // adiciona o dígito verificador à matrícula
				
			} else if (linha.charAt(10) == '-') { // se o tamanho da matrícula for maior que 10 e conter separador, o dígito verificador será validado
				
				Matricula matricula = new Matricula(); // instancia um objeto da classe matricula
				
				boolean validado = matricula.validaDigitoVerificador(linha); // chama o método que valida o dígito verificador e armazena o status de validação
				linha += " " + validado; // adiciona o status de validação ao lado da matrícula
			} else {
				leEntrada.close();
				throw new Exception("O arquivo contém dados inválidos. Favor verificar");
			}
			
			linhas.add(linha); // adiciona a linha com a matrícula modificada ao array de linhas
		}
		
		leEntrada.close(); // fecha o arquivo
		
		return linhas; // retorna o array com todas as matrículas tratadas
	}
	
	/**
	 * Método que cria o arquivo de saída
	 * para os dígitos verificadores criados ou validados
	 * 
	 * @param linhas
	 * @throws IOException
	 */
	public void escreveArquivo(ArrayList<String> linhas) throws IOException {
		
		int indice = this.nome.lastIndexOf("\\"); // identifica qual é o último caractere que contém o caminho do arquivo de entrada 
	    String diretorio = this.nome.substring(0, indice+1); // armazena o caminho do arquivo de entrada
	    
	    if (this.nome.contains("matriculas_sem_dv")) { // se for o arquivo de matrículas sem dígito verificador
	    	this.arquivo = diretorio + "matriculas_com_dv.txt"; // cria o arquivo com os dígitos criado no mesmo caminho
	    	System.out.println();
	    	System.out.println("Dígitos verificadores criados");
	    }
	    if (this.nome.contains("matriculas_para_validar")) { // se for o arquivo de matrículas para validar
	    	this.arquivo = diretorio + "matriculas_validadas.txt"; // cria o arquivo com os dígitos criado no mesmo caminho
	    	System.out.println();
	    	System.out.println("Matrículas validadas");
	    }
		
		FileWriter saida = new FileWriter(this.arquivo); // cria o arquivo de saída
		PrintWriter escreveSaida = new PrintWriter(saida); // escreve o conteúdo no arquivo de saída
		
		for(String linha : linhas) {
			escreveSaida.println(linha); // percorre o array recebido e escreve cada matrícula em uma linha
		}
		
		escreveSaida.close(); // fecha o arquivo de saída
		
		System.out.println();
		System.out.println("Arquivo criado com sucesso >> " + this.arquivo);
	}
	
}
