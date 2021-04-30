package InterfazAnaLex;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

import AnaLex.*;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;

public class AnalizadorLexicoInterfaz {

	private JFrame frmAnalizadorLxico;
	private JTextField textField;
	JTextArea textArea;
	
	//----------------------------
	
	AnalizadorLexico AL;
	
	AutomataFinito AF;
	Map<Integer, String> equivTokens;
	
	String cadenaActual;
	
	boolean automataFinitoInformado;
	boolean equivTokensInformado;
	
	//----------------------------
	
	JButton btnNewButton, btnSiguiente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnalizadorLexicoInterfaz window = new AnalizadorLexicoInterfaz();
					window.frmAnalizadorLxico.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AnalizadorLexicoInterfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		AL = null;
		
		AF = new AutomataFinitoMatriz(0, 0);
		equivTokens = new HashMap<>();
		
		cadenaActual = null;
		
		automataFinitoInformado = false;
		equivTokensInformado = false;
		
		frmAnalizadorLxico = new JFrame();
		frmAnalizadorLxico.setTitle("Analizador L\u00E9xico");
		frmAnalizadorLxico.setBounds(100, 100, 474, 469);
		frmAnalizadorLxico.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAnalizadorLxico.getContentPane().setLayout(null);
		
		btnNewButton = new JButton("Siguiente Token");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				clickSiguienteToken();
				
			}
		});
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(39, 366, 165, 53);
		frmAnalizadorLxico.getContentPane().add(btnNewButton);
		
		btnSiguiente = new JButton("Completar An\u00E1lisis");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				clickCompletarAnalisis();
				
			}
		});
		btnSiguiente.setEnabled(false);
		btnSiguiente.setBounds(252, 366, 165, 53);
		frmAnalizadorLxico.getContentPane().add(btnSiguiente);
		
		textField = new JTextField();
		textField.setBounds(10, 101, 438, 50);
		frmAnalizadorLxico.getContentPane().add(textField);
		textField.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 187, 438, 168);
		frmAnalizadorLxico.getContentPane().add(textArea);
		
		JLabel lblNewLabel = new JLabel("Cadena:");
		lblNewLabel.setBounds(10, 76, 46, 14);
		frmAnalizadorLxico.getContentPane().add(lblNewLabel);
		
		JLabel lblTokens = new JLabel("Tokens:");
		lblTokens.setBounds(10, 162, 46, 14);
		frmAnalizadorLxico.getContentPane().add(lblTokens);
		
		JButton btnIndicar = new JButton("Indicar Token - Estado final");
		btnIndicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				clickTokenEstado();
				
			}
		});
		btnIndicar.setBounds(39, 11, 165, 54);
		frmAnalizadorLxico.getContentPane().add(btnIndicar);
		
		JButton btnNewButton_1_1 = new JButton("Definir Aut\u00F3mata");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				clickDefinirAutomata();
				
			}
		});
		btnNewButton_1_1.setBounds(252, 11, 165, 54);
		frmAnalizadorLxico.getContentPane().add(btnNewButton_1_1);
	}
	
	private void clickDefinirAutomata() {
		
		DefinirAutomataDialogo frame = new DefinirAutomataDialogo();
		frame.setVisible(true);
		frame.setModal(true);
		
		AF = frame.getAF();
		
		automataFinitoInformado = true;
		if(equivTokensInformado) comenzamosConElAnalizador();
		
	}
	
	private void clickTokenEstado() {
		
		DefinirEquivTokensDialogo frame = new DefinirEquivTokensDialogo();
		frame.setVisible(true);
		frame.setModal(true);
		
		equivTokens = frame.getEquivTokens();
		
		equivTokensInformado= true;
		if(automataFinitoInformado) comenzamosConElAnalizador();
		
	}
	
	private void clickSiguienteToken() {
		
		if(!this.textField.getText().equals(this.cadenaActual)) {
			
			this.textArea.setText("");
			this.cadenaActual = this.textField.getText();
			this.AL.nuevaCadena(codificadorLetrasEnteros(this.cadenaActual));
			
		}
		
		this.AL.nextToken();
		this.textArea.setText(toStringTokensConLetras(this.AL.getHistorico()));
		
	}

	private void clickCompletarAnalisis() {
		
		if(!this.textField.getText().equals(this.cadenaActual)) {
			
			this.textArea.setText("");
			this.cadenaActual = this.textField.getText();
			this.AL.nuevaCadena(codificadorLetrasEnteros(this.cadenaActual));
			
		}
		
		this.AL.finalizarAnalisis();
		this.textArea.setText(toStringTokensConLetras(this.AL.getHistorico()));
		
	}
	
	private void comenzamosConElAnalizador() {
		
		this.AL = new AnalizadorLexico(AF, equivTokens);
		
		btnNewButton.setEnabled(true);
		btnSiguiente.setEnabled(true);
		
	}
	
	//-------------------------------
	
	public static int[] codificadorLetrasEnteros(String letras) {
		
		int[] enteros = new int[letras.length()];
		
		for(int i = 0; i < letras.length(); i++) {
			
			enteros[i] = (int) letras.toCharArray()[i] - (int) 'a';
			
		}
		
		return enteros;
		
	}
	
	public static String decodificadorLetrasEnteros(int[] enteros) {
		
		String letras = "";
		
		for(int i = 0; i < enteros.length; i++) {
			
			letras += Character.toString((char) (enteros[i] + (int) 'a'));
			
		}
		
		return letras;
		
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
