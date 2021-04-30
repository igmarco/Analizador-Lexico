package AnaLex;

import java.util.List;

/**
* La clase Tools aporta un set de cuatro m�todos est�ticos que permiten trabajar y representar con caracteres y enteros de forma correcta para el programa AnaLex.
* Parte de la pr�ctica 4 de la asignatura Procesadores de Lenguajes. 
* @author Ignacio Marco P�rez
* @version V2 - 29/04/2021
* @see <a href = "https://aps.unirioja.es/GuiasDocentes/servlet/agetguiahtml?2020-21,801G,445"> Gu�a de la Asignatura: Procesadores de Lenguajes. </a>
* @see AnalizadorLexico
*/

public class Tools {
	
	/**
    * M�todo que traduce una cadena de caracteres en un vector de enteros.
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
    * M�todo que traduce un vector de enteros en una cadena de caracteres.
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
    * M�todo que muestra por pantalla los tokens de una lista.
    * @param tokens Lista de tokens a mostrar.
    */
	public static void printTokens(List<Token> tokens) {
		
		System.out.println("Tokens:");
		
		for(Token token : tokens) {
			
			System.out.println("<" + token.getId() + ", " + token.getLexema() + ">");
			
		}
		
	}
	
	/**
    * M�todo que muestra por pantalla los tokens de una lista.
    * Traduce los lexemas de vectores de enteros a cadenas de caracteres.
    * @param tokens Lista de tokens a mostrar.
    */
	public static void printTokensConLetras(List<Token> tokens) {
		
		System.out.println("Tokens:");
		
		for(Token token : tokens) {
			
			System.out.println("<" + token.getId() + ", " + decodificadorLetrasEnteros(token.getLexema()) + ">");
			
		}
		
	}
	
	public static String toStringTokens(List<Token> tokens) {
		
		String tokString = "";
		
		for(Token token : tokens) {
			
			tokString += "<" + token.getId() + ", " + token.getLexema() + ">" + "\r\n";
			
		}
		
		return tokString;
		
	}
	
	public static String toStringTokensConLetras(List<Token> tokens) {
		
		String tokString = "";
		
		for(Token token : tokens) {
			
			tokString += "<" + token.getId() + ", " + decodificadorLetrasEnteros(token.getLexema()) + ">" + "\r\n";
			
		}
		
		return tokString;
		
	}
	
}
