package AnaLex;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Clase de prueba para el proyecto AnalizadorLéxico. Permite comprobar su funcionamiento, y ofrece además métodos de conversión entre Strings y listas de enteros.
* Parte de la práctica 4 de la asignatura Procesadores de Lenguajes. 
* @author: Ignacio Marco Pérez
* @version: V1 - 22/04/2021
* @see <a href="https://aps.unirioja.es/GuiasDocentes/servlet/agetguiahtml?2020-21,801G,445">Guía de la Asignatura: Procesadores de Lenguajes.</a> 
* @see AnalizadorLexico
*/

public class Principal {

	/**
    * Método main de la clase Principal.
    * @param args Argumentos del main. En este caso, ninguna entrada.
    */
	public static void main(String[] args) {
		
		int matriz[][] = {{1, 2}, {3, 4}, {-1, -1}, {3, 2}, {5, 6}, {5, -1}, {-1, 6}};
		int tamAlfabeto = 2;
		int NumEstados=7;
		boolean finales[] = {false, true, true, false, true, true, true};
		
		String palabra="aaaaaaaaaaababbbabaabaaaabbabaaaabbbabababaaabbbaaaaababaaababa";
		
		Map<Integer, String> equivTokens = new HashMap<>();
		equivTokens.put(1, "dos");
		equivTokens.put(2, "cuatro");
		equivTokens.put(4, "uno");
		equivTokens.put(5, "tres");
		equivTokens.put(6, "dos");
		
		AutomataFinito A = new AutomataFinitoMatriz(NumEstados, tamAlfabeto, finales, matriz);
		AnalizadorLexico escaner = new AnalizadorLexico(codificadorLetrasEnteros(palabra), A, equivTokens);
		
		escaner.finalizarAnalisis();
		
		printTokensConLetras(escaner.getHistorico());
		
	}
	
	/**
    * Método que traduce una cadena de caracteres en un vector de enteros.
    * @param letras Cadena de caracteres a traducir.
    * @return a - 0, b - 1, c - 2...
    */
	public static int[] codificadorLetrasEnteros(String letras) {
		
		int[] enteros = new int[letras.length()];
		
		for(int i = 0; i < letras.length(); i++) {
			
			enteros[i] = (int) letras.toCharArray()[i] - (int) 'a';
			
		}
		
		return enteros;
		
	}
	
	/**
    * Método que traduce un vector de enteros en una cadena de caracteres.
    * @param enteros Vector de enteros a traducir.
    * @return 0 - a, 1 - b, 2 - c...
    */
	public static String decodificadorLetrasEnteros(int[] enteros) {
		
		String letras = "";
		
		for(int i = 0; i < enteros.length; i++) {
			
			letras += Character.toString((char) (enteros[i] + (int) 'a'));
			
		}
		
		return letras;
		
	}
	
	/**
    * Método que muestra por pantalla los tokens de una lista.
    * @param tokens Lista de tokens a mostrar.
    */
	public static void printTokens(List<Token> tokens) {
		
		System.out.println("Tokens:");
		
		for(Token token : tokens) {
			
			System.out.println("<" + token.getId() + ", " + token.getLexema() + ">");
			
		}
		
	}
	
	/**
    * Método que muestra por pantalla los tokens de una lista.
    * Traduce los lexemas de vectores de enteros a cadenas de caracteres.
    * @param tokens Lista de tokens a mostrar.
    */
	public static void printTokensConLetras(List<Token> tokens) {
		
		System.out.println("Tokens:");
		
		for(Token token : tokens) {
			
			System.out.println("<" + token.getId() + ", " + decodificadorLetrasEnteros(token.getLexema()) + ">");
			
		}
		
	}

}
