package AnaLex;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
* La clase AnalizadorLexico representa un analizador léxico. Está compuesto por un autómata finito, un diccionario de tokens y la cadena que va a "tokenizar".
* Parte de la práctica 4 de la asignatura Procesadores de Lenguajes. 
* @author Ignacio Marco Pérez
* @version V1 - 22/04/2021
* @see <a href = "https://aps.unirioja.es/GuiasDocentes/servlet/agetguiahtml?2020-21,801G,445"> Guía de la Asignatura: Procesadores de Lenguajes. </a>
* @see AutomataFinito
* @see Token
*/

public class AnalizadorLexico {
	
	private int[] cadena;
	private AutomataFinito A;
	private int posActual;
	private Map<Integer, String> tokens;
	private List<Token> historico;
	
	/**
	* Constructor de AnalizadorLexico. Contempla el autómata finito y la lista de duplas token-lexema. Contempla la cadena vacía.
	* @param A Representa el autómata finito del analizador léxico.
    * @param tokens Representa la lista de duplas token-lexema.
    * @see AutomataFinito
    * @see Token
	*/
	public AnalizadorLexico(AutomataFinito A, Map<Integer, String> tokens) {
		
		this.cadena = new int[0];
		this.A = A;
		this.posActual = 0;
		this.tokens = tokens;
		this.historico = new ArrayList<Token>();
		
	}
	
	/**
	* Constructor de AnalizadorLexico. Contempla el autómata finito y la lista de duplas token-lexema, así como la cadena a analizar.
	* @param cadena Representa la cadena a analizar.
	* @param A Representa el autómata finito del analizador léxico.
    * @param tokens Representa la lista de duplas token-lexema.
    * @see AutomataFinito
    * @see Token
	*/
	public AnalizadorLexico(int[] cadena, AutomataFinito A, Map<Integer, String> tokens) {
		
		this.cadena = cadena;
		this.A = A;
		this.posActual = 0;
		this.tokens = tokens;
		this.historico = new ArrayList<Token>();
		
	}
	
	/**
    * Método que proporciona el siguiente token generado por la cadena.
    * El indicador de la posición de la cadena avanza y registra el token generado en el histórico.
    * @return Token generado por la cadena. "null" en caso de no existir.
    * @see Token
    */
	public Token nextToken() {
		
		this.A.reset();
		
		int posActualAuxiliar = this.posActual;
		int últimoEstadoVisitado = 0;
		int últimoFinalVisitado = 0;
		int últimaPosiciónCadenaFinal = this.posActual;
		boolean másTransiciones = true;
		
		while(this.cadena.length > posActualAuxiliar && másTransiciones) {
			
			if(A.aplicarTransicion(cadena[posActualAuxiliar])) {
				
				últimoEstadoVisitado = this.A.getEstActual();
				posActualAuxiliar++;
				
				if(this.A.esEstadoFinal(últimoEstadoVisitado)) {
					
					últimoFinalVisitado = últimoEstadoVisitado;
					últimaPosiciónCadenaFinal = posActualAuxiliar;
					
				}
				
			}
			else {
				
				másTransiciones = false;
				
			}
			
		}
		
		int[] lexema = new int[últimaPosiciónCadenaFinal-this.posActual];
		for(int i = 0; i < últimaPosiciónCadenaFinal-this.posActual; i++)
			lexema[i] = this.cadena[this.posActual+i];
		
		Token token = new Token(this.tokens.get(últimoFinalVisitado), lexema);
		
		this.posActual = últimaPosiciónCadenaFinal;
		this.historico.add(token);
		
		return token;
		
	}
	
	/**
    * Método que indica si se puede extraer algún token más de la cadena.
    * @return Booleano que devuelve true en caso de poder generar algún token más y que devuelve false en caso contrario.
    * @see Token
    */
	public boolean hasMoreTokens() {
		
		this.A.reset();
		
		int posActualAuxiliar = this.posActual;
		
		while(this.cadena.length > posActualAuxiliar) {
			
			if(!A.aplicarTransicion(cadena[posActualAuxiliar])) {
				
				return false;
				
			}
			
			if(this.A.esEstadoFinal(this.A.getEstActual())) {
				
				return true;
				
			}
			
			posActualAuxiliar++;
			
		}
		
		return false;
		
	}
	
	/**
    * Método que devuelve el histórico de tokens generado.
    * @return Lista de tokens generados por el analizador para la cadena actual.
    * @see Token
    */
	public List<Token> getHistorico() {
		
		return this.historico;
		
	}
	
	/**
    * Método que devuelve las condiciones del analizador a las originales (conservando la cadena).
    * Reinicia el indicador de la posición actual.
    * Limpia el histórico de tokens.
    */
	public void reset() {
		
		this.posActual = 0;
		this.historico.clear();
		
	}
	
	/**
    * Método que sustituye la cadena por la informada y devuelve las condiciones del analizador a las originales.
    * Sustituye la cadena.
    * Reinicia el indicador de la posición actual.
    * Limpia el histórico de tokens.
    * @param cadena Nueva cadena del analizador léxico.
    */
	public void nuevaCadena(int[] cadena) {
		
		reset();
		this.cadena = cadena;
		
	}
	
	/**
    * Método que proporciona el resto de los tokens generados por la cadena.
    * @return Lista de tokens generados por el analizador para la cadena actual.
    * @see Token
    * @see AnalizadorLexico#nextToken()
    */
	public List<Token> finalizarAnalisis() {
		
		List<Token> tokens = new ArrayList<Token>();
		
		while(this.hasMoreTokens()) {
			
			tokens.add(this.nextToken());
			
		}
		
		return tokens;
		
	}
	
}
