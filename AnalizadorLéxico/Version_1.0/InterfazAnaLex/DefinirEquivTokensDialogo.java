package InterfazAnaLex;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DefinirEquivTokensDialogo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	Map<Integer, String> equivTokens;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DefinirEquivTokensDialogo dialog = new DefinirEquivTokensDialogo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DefinirEquivTokensDialogo() {
		equivTokens = new HashMap<>();
		
		setTitle("Equivalencia de estados finales - tokens");
		setBounds(100, 100, 339, 422);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			table = new JTable();
			table.setModel(new DefaultTableModel(
				new Object[][] {
					{"Estado", "Token"},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
				},
				new String[] {
					"New column", "New column"
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
						
						construirEquivTokens();
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	public Map<Integer, String> getEquivTokens() {
		
		return this.equivTokens;
		
	}
	
	private void construirEquivTokens() {
		
		for(int row = 1; row < 20; row++) {
			
			if(this.table.getModel().getValueAt(row, 0) != null && this.table.getModel().getValueAt(row, 1) != null) {
				
				this.equivTokens.put((Integer) this.table.getModel().getValueAt(row, 0), (String) this.table.getModel().getValueAt(row, 1));
				
			}
			
		}
		
	}

}
