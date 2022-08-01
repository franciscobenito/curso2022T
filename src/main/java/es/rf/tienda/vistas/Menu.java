package es.rf.tienda.vistas;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;

public class Menu {
	private static JFrame frame;
	private static JPanel panel;
	private static JButton productos;
	private static JButton categorias;
	private static JButton usuarios;
	private static JButton salir;

	public static void main(String[] args) throws DAOException, DomainException {
		//Ventana principal
		vistaMenu("Menu");		
	}

	private static void vistaMenu(String title) {
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,150);
		frame.setLocationRelativeTo(null);
		
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(25, 25, 25, 25));
		panel.setLayout(new GridLayout(2,2));
		
		productos = new JButton("Productos");
		categorias = new JButton("Categorias");
		usuarios = new JButton("Usuarios");
		salir = new JButton("Salir");
		
		panel.add(productos);
		panel.add(categorias);
		panel.add(usuarios);
		panel.add(salir);
		
		frame.setContentPane(panel);
		frame.setVisible(true);
		
		//Acción botones
		categorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					@SuppressWarnings("unused")
					VistaCategoria vistaC = new VistaCategoria();
				} catch (DAOException | DomainException e1) {
					e1.printStackTrace();
				}
			}
		});
		usuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					@SuppressWarnings("unused")
					VistaUsuario vistaU = new VistaUsuario();
				} catch (DAOException | DomainException e1) {
					e1.printStackTrace();
				}
			}
		});
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});	
	}

}
