package InterfazAnaLex;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import AnaLex.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DefinirAutomataDialogo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	
	AutomataFinito AF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DefinirAutomataDialogo dialog = new DefinirAutomataDialogo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DefinirAutomataDialogo() {
		setTitle("Matriz de transiciones del analizador l\u00E9xico");
		setBounds(100, 100, 869, 416);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			table = new JTable();
			table.setModel(new DefaultTableModel(
				new Object[][] {
					{"Estado", "Final?", "a", "b", "c", "d", "e", "f", "g", "h", "i"},
					{new Integer(0), "no", null, null, null, null, null, null, null, null, null},
					{new Integer(1), "si", null, null, null, null, null, null, null, null, null},
					{new Integer(2), "si", null, null, null, null, null, null, null, null, null},
					{new Integer(3), "si", null, null, null, null, null, null, null, null, null},
					{new Integer(4), "si", null, null, null, null, null, null, null, null, null},
					{new Integer(5), "si", null, null, null, null, null, null, null, null, null},
					{new Integer(6), "si", null, null, null, null, null, null, null, null, null},
					{new Integer(7), "si", null, null, null, null, null, null, null, null, null},
					{new Integer(8), "si", null, null, null, null, null, null, null, null, null},
					{new Integer(9), "si", null, null, null, null, null, null, null, null, null},
					{new Integer(10), "si", null, null, null, null, null, null, null, null, null},
					{new Integer(11), "si", null, null, null, null, null, null, null, null, null},
					{new Integer(12), "si", null, null, null, null, null, null, null, null, null},
					{new Integer(13), "si", null, null, null, null, null, null, null, null, null},
					{new Integer(14), "si", null, null, null, null, null, null, null, null, null},
					{new Integer(15), "si", null, null, null, null, null, null, null, null, null},
					{new Integer(16), "si", null, null, null, null, null, null, null, null, null},
					{new Integer(17), "si", null, null, null, null, null, null, null, null, null},
					{new Integer(18), "si", null, null, null, null, null, null, null, null, null},
				},
				new String[] {
					"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
				}
			));
			contentPanel.add(table);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						construirAF();
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	public AutomataFinito getAF() {
		
		return this.AF;
		
	}
	
	private void construirAF() {
		
		int[][] matriz = new int[19][9];
		boolean[] finales = new boolean[19];
		
		for(int row = 1; row < 20; row++) {
			
			for(int col = 2; col < 11; col++) {
				
				matriz[row-1][col-2] = this.table.getModel().getValueAt(row, col) == null ? -1 : Integer.parseInt((String) this.table.getModel().getValueAt(row, col));
				
			}
			
			finales[row-1] = ((String) this.table.getModel().getValueAt(row, 1)).equals("si") ? true : false;
			
		}
		
		this.AF = new AutomataFinitoMatriz(19, 9, finales, matriz);
		
	}

}
