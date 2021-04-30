package AnaLex;

import java.util.HashMap;
import java.util.Map;

/**
* Clase de prueba 2 para el proyecto AnalizadorLéxico. Permite comprobar su funcionamiento, y resuelve el ejercicio propuesto de la práctica 4.
* Parte de la práctica 4 de la asignatura Procesadores de Lenguajes. 
* @author Ignacio Marco Pérez
* @version V2 - 28/04/2021
* @see <a href="https://aps.unirioja.es/GuiasDocentes/servlet/agetguiahtml?2020-21,801G,445">Guía de la Asignatura: Procesadores de Lenguajes.</a> 
* @see AnalizadorLexico
*/

public class Principal2 {

	/**
    * Método main de la clase Principal.
    * @param args Argumentos del main. En este caso, ninguna entrada.
    */
	public static void main(String[] args) {
		
		int matriz[][] = {{1,2,3},{4,5,-1},{-1,5,-1},{-1,-1,3},{6,7,-1},{-1,5,-1},{6,5,-1},{-1,8,-1},{-1,5,-1}};
		int tamAlfabeto = 3;
		int NumEstados=9;
		boolean finales[] = {false, true, true, true, false, true, false, true, true};
		
		String palabra="aaaaaaaacccaaababbbabaacbaaaabbabaaaabbbabaccbabaaabbbaccaaaababaaabababaabb";
		
		Map<Integer, String> equivTokens = new HashMap<>();
		equivTokens.put(1, "cero");
		equivTokens.put(2, "uno");
		equivTokens.put(3, "cuatro");
		equivTokens.put(5, "tres");
		equivTokens.put(7, "tres");
		equivTokens.put(8, "dos");
		
		AutomataFinito A = new AutomataFinitoMatriz(NumEstados, tamAlfabeto, finales, matriz);
		AnalizadorLexico escaner = new AnalizadorLexico(Tools.codificadorLetrasEnteros(palabra), A, equivTokens);
		
		escaner.finalizarAnalisis();
		
		Tools.printTokensConLetras(escaner.getHistorico());
		
	}

}
