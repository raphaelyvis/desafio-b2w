package br.com.b2w.desafio.modelo;

/**
 * Classe que cria e valida d�gitos verificadores de matr�culas
 * 
 * @author rapha
 *
 */
public class Matricula {
	
	private int soma = 0;
	private String digitoVerificador;
	
	/**
	 * M�todo que cria o d�gito verificador de uma matr�cula
	 * 
	 * @param matricula
	 * @return
	 */
	public String criaDigitoVerificador(String matricula) {
		
		this.digitoVerificador = "-"; // adiciona o separador para o d�gito verificador
		
		for (char digito : matricula.toCharArray()) { // l� cada caractere de uma matr�cula separadamente
			this.soma += Integer.parseInt(""+digito, 16); // soma cada d�gito, convertendo para a base decimal
		}
		this.digitoVerificador += Integer.toString(this.soma % 16, 16); // divide o resultado da soma por 16, pega o resto e converte para hexadecimal
		
		return this.digitoVerificador; // retorna o d�gito verificador convertido
	}
	
	/**
	 * M�todo que valida o d�gito verificador de uma matr�cula
	 * 
	 * @param matricula
	 * @return
	 */
	public boolean validaDigitoVerificador(String matricula) {
		
		this.digitoVerificador = matricula.substring(11); // aramazena o d�gito verificador da matr�cula
		matricula = matricula.substring(0, 10); // armazena os d�gitos da matr�cula antes do separador
		
		for (char digito : matricula.toCharArray()) { // l� cada d�gito separadamente
			this.soma += Integer.parseInt(""+digito, 16); // soma cada d�gito, convertendo para a base decimal
		}
		
		String resto = Integer.toString(soma % 16, 16); // divide o resultado da soma por 16, pega o resto e converte para hexadecimal
		
		if (this.digitoVerificador.equalsIgnoreCase(resto)) {
			return true; // se o d�gito verificador for igual ao resto calculado, retorna true
		}
		
		return false; // se n�o, retorna falso
		
	}
	
}
