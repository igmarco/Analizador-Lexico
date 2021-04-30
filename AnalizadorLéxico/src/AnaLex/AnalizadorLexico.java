package AnaLex;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
* La clase AnalizadorLexico representa un analizador l�xico. Est� compuesto por un aut�mata finito, un diccionario de tokens y la cadena que va a "tokenizar".
* Parte de la pr�ctica 4 de la asignatura Procesadores de Lenguajes. 
* @author Ignacio Marco P�rez
* @version V1 - 22/04/2021
* @see <a href = "https://aps.unirioja.es/GuiasDocentes/servlet/agetguiahtml?2020-21,801G,445"> Gu�a de la Asignatura: Procesadores de Lenguajes. </a>
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
	* Constructor de AnalizadorLexico. Contempla el aut�mata finito y la lista de duplas token-lexema. Contempla la cadena vac�a.
	* @param A Representa el aut�mata finito del analizador l�xico.
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
	* Constructor de AnalizadorLexico. Contempla el aut�mata finito y la lista de duplas token-lexema, as� como la cadena a analizar.
	* @param cadena Representa la cadena a analizar.
	* @param A Representa el aut�mata finito del analizador l�xico.
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
    * M�todo que proporciona el siguiente token generado por la cadena.
    * El indicador de la posici�n de la cadena avanza y registra el token generado en el hist�rico.
    * @return Token generado por la cadena. "null" en caso de no existir.
    * @see Token
    */
	public Token nextToken() {
		
		this.A.reset();
		
		int posActualAuxiliar = this.posActual;
		int �ltimoEstadoVisitado = 0;
		int �ltimoFinalVisitado = 0;
		int �ltimaPosici�nCadenaFinal = this.posActual;
		boolean m�sTransiciones = true;
		
		while(this.cadena.length > posActualAuxiliar && m�sTransiciones) {
			
			if(A.aplicarTransicion(cadena[posActualAuxiliar])) {
				
				�ltimoEstadoVisitado = this.A.getEstActual();
				posActualAuxiliar++;
				
				if(this.A.esEstadoFinal(�ltimoEstadoVisitado)) {
					
					�ltimoFinalVisitado = �ltimoEstadoVisitado;
					�ltimaPosici�nCadenaFinal = posActualAuxiliar;
					
				}
				
			}
			else {
				
				m�sTransiciones = false;
				
			}
			
		}
		
		int[] lexema = new int[�ltimaPosici�nCadenaFinal-this.posActual];
		for(int i = 0; i < �ltimaPosici�nCadenaFinal-this.posActual; i++)
			lexema[i] = this.cadena[this.posActual+i];
		
		Token token = new Token(this.tokens.get(�ltimoFinalVisitado), lexema);
		
		this.posActual = �ltimaPosici�nCadenaFinal;
		this.historico.add(token);
		
		return token;
		
	}
	
	/**
    * M�todo que indica si se puede extraer alg�n token m�s de la cadena.
    * @return Booleano que devuelve true en caso de poder generar alg�n token m�s y que devuelve false en caso contrario.
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
    * M�todo que devuelve el hist�rico de tokens generado.
    * @return Lista de tokens generados por el analizador para la cadena actual.
    * @see Token
    */
	public List<Token> getHistorico() {
		
		return this.historico;
		
	}
	
	/**
    * M�todo que devuelve las condiciones del analizador a las originales (conservando la cadena).
    * Reinicia el indicador de la posici�n actual.
    * Limpia el hist�rico de tokens.
    */
	public void reset() {
		
		this.posActual = 0;
		this.historico.clear();
		
	}
	
	/**
    * M�todo que sustituye la cadena por la informada y devuelve las condiciones del analizador a las originales.
    * Sustituye la cadena.
    * Reinicia el indicador de la posici�n actual.
    * Limpia el hist�rico de tokens.
    * @param cadena Nueva cadena del analizador l�xico.
    */
	public void nuevaCadena(int[] cadena) {
		
		reset();
		this.cadena = cadena;
		
	}
	
	/**
    * M�todo que proporciona el resto de los tokens generados por la cadena.
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
