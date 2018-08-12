package br.com.b2w.desafio.modelo;

/**
 * Classe que cria o nome do arquivo de saída
 * 
 * @author rapha
 *
 */
public class Arquivo {
	
	private String arquivo;
	
	/**
	 * Construtor que cria o nome e caminho do arquivo de saída
	 * de acordo com o nome e caminho do arquivo de entrada.
	 * A classe é instanciada obrigatoriamente recebendo como
	 * parâmetro o nome e caminho de um arquivo.
	 * Esta classe considera que os arquivos sempre terão o
	 * nome especificado no desafio.
	 * 
	 * @param nome
	 */
	public Arquivo(String nome) {
		int indice = nome.lastIndexOf("\\"); // identifica qual é o último caractere que contém o caminho do arquivo de entrada 
	    String diretorio = nome.substring(0, indice+1); // armazena o caminho do arquivo de entrada
	    
	    if (nome.contains("matriculas_sem_dv")) { // se for o arquivo de matrículas sem dígito verificador
	    	this.arquivo = diretorio + "matriculas_com_dv.txt"; // cria o arquivo com os dígitos criado no mesmo caminho
	    }
	    if (nome.contains("matriculas_para_validar")) { // se for o arquivo de matrículas para validar
	    	this.arquivo = diretorio + "matriculas_validadas.txt"; // cria o arquivo com os dígitos criado no mesmo caminho
	    }
	}
	
	@Override
	public String toString() { // sobrescreve o método para retornar o nome do arquivo de saída
		return this.arquivo;
	}
	
}
