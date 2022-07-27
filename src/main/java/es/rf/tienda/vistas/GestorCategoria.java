package es.rf.tienda.vistas;

import javax.swing.JTextField;

import es.rf.tienda.dominio.Categoria;

public class GestorCategoria {

	Categoria obj;
	
	public void mostrar() {
		try {
			FrCategoria frame = new FrCategoria();
			frame.setIdText(new JTextField(obj.getId()));
			
			//frame.setVisible(true);
		}catch (Exception e) {
			e.getMessage();
		}
	}
}
