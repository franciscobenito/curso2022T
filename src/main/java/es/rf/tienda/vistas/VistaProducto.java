package es.rf.tienda.vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;

public class VistaProducto {
	
	private JFrame frame;
	private JPanel panel;
	private JPanel panelLabel;
	private JPanel panelButton;
	private JLabel prodLabel;
	private JButton botonSalir;
	
	public VistaProducto () throws DAOException, DomainException {
		vista("Productos");
	}

	public void vista (String title) throws DAOException, DomainException {
		
		//Ventana principal
		frame = new JFrame(title);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,120);
		frame.setLocationRelativeTo(null);
		
		//Paneles
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout(0,0));
		panelLabel = new JPanel();
		panelLabel.setBackground(Color.white);
		panelButton = new JPanel();
		panelButton.setLayout(new FlowLayout());
				
		//Contenedores
		prodLabel = new JLabel("Estamos con tareas de mantenimiento.\nSentimos las molestias.");
		botonSalir = new JButton("Salir");
		
		//Añadimos los paneles al panel principal
		panelLabel.add(prodLabel);
		panelButton.add(botonSalir);
				
		panel.add(panelLabel, BorderLayout.CENTER);
		panel.add(panelButton, BorderLayout.SOUTH);
		
		frame.setContentPane(panel);
		frame.setVisible(true);
		
		//Acción botones
		botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});		
	}
}
