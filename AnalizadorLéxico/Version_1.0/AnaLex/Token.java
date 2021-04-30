package AnaLex;

/**
* La clase Token representa una dupla token-lexema de un analizador l�xico.
* Parte de la pr�ctica 4 de la asignatura Procesadores de Lenguajes. 
* @author: Ignacio Marco P�rez
* @version: V1 - 22/04/2021
* @see <a href = "https://aps.unirioja.es/GuiasDocentes/servlet/agetguiahtml?2020-21,801G,445"> Gu�a de la Asignatura: Procesadores de Lenguajes. </a>
* @see AnalizadorLexico
*/

public class Token {
	
	private String idToken;
	private int[] lexema;
	
	/**
	* Constructor de Token.
	* @param id Representa el nombre o id del Token.
    * @param lexema Representa el lexema del token, la cadena de letras que lo conforman.
	*/
	public Token(String id, int[] lexema) {
		
		this.idToken = id;
		this.lexema = lexema;
		
	}
	
	/**
    * M�todo que devuelve el nombre o id del Token.
    * @return Nombre o id del Token.
    */
	public String getId() {
		
		return this.idToken;
		
	}
	
	/**
    * M�todo que devuelve el lexema del token, la cadena de letras que lo conforman.
    * @return Lexema del token, la cadena de letras que lo conforman.
    */
	public int[] getLexema(){
		
		return this.lexema;
		
	}
	
}
