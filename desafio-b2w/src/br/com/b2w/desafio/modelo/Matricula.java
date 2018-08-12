package br.com.b2w.desafio.modelo;

/**
 * Classe que cria e valida dígitos verificadores de matrículas
 * 
 * @author rapha
 *
 */
public class Matricula {
	
	private int soma = 0;
	private String digitoVerificador;
	
	/**
	 * Método que cria o dígito verificador de uma matrícula
	 * 
	 * @param matricula
	 * @return
	 */
	public String criaDigitoVerificador(String matricula) {
		
		this.digitoVerificador = "-"; // adiciona o separador para o dígito verificador
		
		for (char digito : matricula.toCharArray()) { // lê cada caractere de uma matrícula separadamente
			this.soma += Integer.parseInt(""+digito, 16); // soma cada dígito, convertendo para a base decimal
		}
		this.digitoVerificador += Integer.toString(this.soma % 16, 16); // divide o resultado da soma por 16, pega o resto e converte para hexadecimal
		
		return this.digitoVerificador; // retorna o dígito verificador convertido
	}
	
	/**
	 * Método que valida o dígito verificador de uma matrícula
	 * 
	 * @param matricula
	 * @return
	 */
	public boolean validaDigitoVerificador(String matricula) {
		
		this.digitoVerificador = matricula.substring(11); // aramazena o dígito verificador da matrícula
		matricula = matricula.substring(0, 10); // armazena os dígitos da matrícula antes do separador
		
		for (char digito : matricula.toCharArray()) { // lê cada dígito separadamente
			this.soma += Integer.parseInt(""+digito, 16); // soma cada dígito, convertendo para a base decimal
		}
		
		String resto = Integer.toString(soma % 16, 16); // divide o resultado da soma por 16, pega o resto e converte para hexadecimal
		
		if (this.digitoVerificador.equalsIgnoreCase(resto)) {
			return true; // se o dígito verificador for igual ao resto calculado, retorna true
		}
		
		return false; // se não, retorna falso
		
	}
	
}
