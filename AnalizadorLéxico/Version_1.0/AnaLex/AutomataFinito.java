package AnaLex;

import java.util.ArrayList;
import java.util.List;

/**
* La clase AutomataFinito representa un aut�mata finito abstracto. Permite representar libremente las transiciones del mismo.
* Parte de la pr�ctica 4 de la asignatura Procesadores de Lenguajes. 
* @author: Ignacio Marco P�rez
* @version: V1 - 22/04/2021
* @see <a href = "https://aps.unirioja.es/GuiasDocentes/servlet/agetguiahtml?2020-21,801G,445"> Gu�a de la Asignatura: Procesadores de Lenguajes. </a>
* @see AnalizadorLexico
*/

public abstract class AutomataFinito {
	
	private int numEstados;
	private boolean[] finales;
	private int tamAlfabeto;
	private int estActual;
	
	/**
    * Constructor de AutomataFinito. No incluye la especificaci�n de estados finales.
    * @param num Representa el n�mero de estados que compondr�n el AutomataFinito.
    * @param alfabeto Representa el n�mero de letras que compondr�n el alfabeto.
    */
	public AutomataFinito(int num, int alfabeto) {
		
		this.numEstados = num;
		this.finales = new boolean[num];
		
		for(int i = 0; i < num; i++) this.finales[i] = false;
		
		this.tamAlfabeto = alfabeto;
		this.estActual = 0;
		
	}
	
	/**
	* Constructor de AutomataFinito. Contempla la informaci�n de estados finales mediante un array de booleanos.
	* @param num Representa el n�mero de estados que compondr�n el AutomataFinito.
    * @param alfabeto Representa el n�mero de letras que compondr�n el alfabeto.
    * @param finales Indica los estados finales. El tama�o de "finales" es igual a "num", y (finales[i] implies estado "i" es final).
	*/
	public AutomataFinito(int num, int alfabeto, boolean[] finales) {
		
		this.numEstados = num;
		this.finales = finales;
		this.tamAlfabeto = alfabeto;
		this.estActual = 0;
		
	}
	
	/**
	* Constructor de AutomataFinito. Contempla la informaci�n de estados finales mediante una lista de enteros.
	* @param num Representa el n�mero de estados que compondr�n el AutomataFinito.
    * @param alfabeto Representa el n�mero de letras que compondr�n el alfabeto.
    * @param finales Representa la lista de estados que son finales.
	*/
	public AutomataFinito(int num, int alfabeto, List<Integer> finales) {
		
		this.numEstados = num;
		this.finales = new boolean[num];
		
		for(int i = 0; i < num; i++) this.finales[i] = (finales.contains(i) ? true : false);
		
		this.tamAlfabeto = alfabeto;
		this.estActual = 0;
		
	}
	
	/**
    * M�todo que marca un estado como final
    * @param estado Estado final.
    */
	public void marcarFinal(int estado) {
		
		this.finales[estado] = true;
		
	}
	
	/**
    * M�todo que indica los estados finales del aut�mata mediante un array de booleanos.
    * @param estadosFinales Indica los estados finales. El tama�o de "estadosFinales" es igual a "num", y (finales[i] implies estado "i" es final).
    * @see AutomataFinito#marcarFinal(int)
    */
	public void setFinales(boolean[] estadosFinales) {
		
		this.finales = estadosFinales;
		
	}
	
	/**
    * M�todo que indica los estados finales del aut�mata.
    * @param estadosFinales Indica los estados finales.
    * @see AutomataFinito#marcarFinal(int)
    */
	public void setFinales(List<Integer> estadosFinales) {
		
		for(int i = 0; i < this.numEstados; i++) this.finales[i] = (estadosFinales.contains(i) ? true : false);
		
	}
	
	/**
    * M�todo que devuelve el n�mero de estados del aut�mata.
    * @return N�mero de estados del aut�mata.
    */
	public int getNumEstados() {
		
		return this.numEstados;
		
	}
	
	/**
    * M�todo que devuelve el estado actual.
    * @return Estado actual.
    */
	public int getEstActual() {
		
		return this.estActual;
		
	}
	
	/**
    * M�todo que devuelve el n�mero de letras que conforman el alfabeto del aut�mata.
    * @return N�mero de estados del aut�mata.
    */
	public int getTamAlfabeto() {
		
		return this.tamAlfabeto;
		
	}
	
	/**
    * M�todo que devuelve una lista de booleanos que indica los estados finales.
    * @return Indica los estados finales. El tama�o de la lista "return" es igual a "num", y (return[i] implies estado "i" es final).
    */
	public boolean[] getFinalesBooleanList() {
		
		return this.finales;
		
	}
	
	/**
	    * M�todo que devuelve una lista con los estados finales.
	    * @return Indica los estados finales.
	    */
	public List<Integer> getFinalesIntegerList() {
		
		List<Integer> finales = new ArrayList<Integer>();
		
		for(int i = 0; i < this.numEstados; i++) if(this.finales[i]) finales.add(i);
		
		return finales;
		
	}
	
	/**
    * M�todo que indica si un estado es final.
    * @param estado Estado que evaluar.
    * @return Es cierto si el estado "estado" es final.
    */
	public boolean esEstadoFinal(int estado) {
		
		return this.finales[estado];
		
	}
	
	/**
    * M�todo que devuelve el estado resultante de aplicar una transici�n dada a un estado dados.
    * En caso de no existir dicha transici�n, devuelve "-1".
    * @param estado Representa el estado de partida.
    * @param letra Representa la transici�n a aplicar.
    * @return Estado al que se llega tras aplicar la transici�n ("-1" en caso de no existir).
    */
	public abstract int getEstadoTransicion(int estado, int letra);
	
	/**
    * M�todo que aplica al aut�mata la transici�n dada en caso de ser v�lida.
    * @param letra Representa la transici�n a aplicar.
    * @return Devuelve true en caso de haber sido posible la transici�n y false en caso contrario.
    * @see AutomataFinito#getEstadoTransicion(int, int)
    */
	public boolean aplicarTransicion(int letra) {
		
		
		int estado = this.getEstadoTransicion(this.estActual, letra);
		
		if(estado == -1) return false;
		else {
			this.estActual = estado;
			return true;
		}
		
	}
	
	/**
    * M�todo que devuelve el estado resultante de aplicar una serie de transiciones dadas a un estado dados.
    * En caso de haber una transici�n no posible, el m�todo devuelve el �ltimo estado.
    * @param estado Representa el estado de partida.
    * @param cadena Representa la cadena de transiciones a aplicar.
    * @return Estado al que se llega tras aplicar la serie de transiciones.
    */
	public int getCierreTransicion(int estado, int[] cadena) {
		
		for(int i = 0; i < cadena.length; i++) {
			
			if(estado != 1) estado = this.getEstadoTransicion(estado, cadena[i]);
			else return estado;
			
		}
		
		return estado;
		
	}
	
	/**
    * M�todo que aplica al aut�mata la lista de transiciones dada.
    * En caso de haber una transici�n no posible, el aut�mata se queda en el �ltimo estado alcanzado.
    * @param cadena Representa la serie de transiciones a aplicar.
    * @return Devuelve true en caso de haber sido todas las transiciones posibles y false en caso contrario.
    * @see AutomataFinito#getCierreTransicion(int, int[])
    */
	public boolean aplicarCierreTransicion(int[] cadena) {
		
		for(int i = 0; i < cadena.length; i++) if(!this.aplicarTransicion(cadena[i])) return false;
		
		return true;
		
		// Otra forma:
		// this.estActual = this.getCierreTransicion(this.estActual,cadena);
		
	}
	
	/**
    * M�todo que indica si una palabra pertenece al lenguaje definido por el aut�mata finito.
    * @param palabra Representa la palabra a evaluar.
    * @return Indica si la palabra pertenece al lenguaje definido por el aut�mata finito.
    */
	public boolean perteneceLenguaje(int[] palabra) {
		
		this.aplicarCierreTransicion(palabra);
		
		return (this.esEstadoFinal(this.estActual));
		
	}
	
	/**
    * M�todo que devuelve las condiciones del aut�mata a las originales.
    * Reinicia el indicador del estado actual.
    */
	public void reset() {
		
		this.estActual = 0;
		
	}
	
}
