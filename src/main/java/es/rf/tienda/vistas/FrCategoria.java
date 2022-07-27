package es.rf.tienda.vistas;

import javax.swing.JTextField;

public class FrCategoria {
	private JTextField idText;
	private JTextField nombreText;
	
	public int getIdText() {
		return Integer.parseInt(idText.getText());
	}
	public void setIdText(JTextField idText) {
		this.idText = idText;
	}
	public JTextField getNombreText() {
		return nombreText;
	}
	public void setNombreText(JTextField nombreText) {
		this.nombreText = nombreText;
	}
	
	
}
