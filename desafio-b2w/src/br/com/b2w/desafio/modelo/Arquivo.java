package br.com.b2w.desafio.modelo;

/**
 * Classe que cria o nome do arquivo de sa�da
 * 
 * @author rapha
 *
 */
public class Arquivo {
	
	private String arquivo;
	
	/**
	 * Construtor que cria o nome e caminho do arquivo de sa�da
	 * de acordo com o nome e caminho do arquivo de entrada.
	 * A classe � instanciada obrigatoriamente recebendo como
	 * par�metro o nome e caminho de um arquivo.
	 * Esta classe considera que os arquivos sempre ter�o o
	 * nome especificado no desafio.
	 * 
	 * @param nome
	 */
	public Arquivo(String nome) {
		int indice = nome.lastIndexOf("\\"); // identifica qual � o �ltimo caractere que cont�m o caminho do arquivo de entrada 
	    String diretorio = nome.substring(0, indice+1); // armazena o caminho do arquivo de entrada
	    
	    if (nome.contains("matriculas_sem_dv")) { // se for o arquivo de matr�culas sem d�gito verificador
	    	this.arquivo = diretorio + "matriculas_com_dv.txt"; // cria o arquivo com os d�gitos criado no mesmo caminho
	    }
	    if (nome.contains("matriculas_para_validar")) { // se for o arquivo de matr�culas para validar
	    	this.arquivo = diretorio + "matriculas_validadas.txt"; // cria o arquivo com os d�gitos criado no mesmo caminho
	    }
	}
	
	@Override
	public String toString() { // sobrescreve o m�todo para retornar o nome do arquivo de sa�da
		return this.arquivo;
	}
	
}
