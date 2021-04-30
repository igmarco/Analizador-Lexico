package AnaLex;

import java.util.HashMap;
import java.util.Map;

/**
* Clase de prueba para el proyecto AnalizadorL�xico. Permite comprobar su funcionamiento.
* Parte de la pr�ctica 4 de la asignatura Procesadores de Lenguajes. 
* @author Ignacio Marco P�rez
* @version V1 - 22/04/2021
* @see <a href="https://aps.unirioja.es/GuiasDocentes/servlet/agetguiahtml?2020-21,801G,445">Gu�a de la Asignatura: Procesadores de Lenguajes.</a> 
* @see AnalizadorLexico
*/

public class Principal {

	/**
    * M�todo main de la clase Principal.
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
		AnalizadorLexico escaner = new AnalizadorLexico(Tools.codificadorLetrasEnteros(palabra), A, equivTokens);
		
		escaner.finalizarAnalisis();
		
		Tools.printTokensConLetras(escaner.getHistorico());
		
	}

}
