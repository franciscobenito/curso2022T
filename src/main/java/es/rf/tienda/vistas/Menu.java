package es.rf.tienda.vistas;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.rf.tienda.controladores.ControladorCat;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;

public class Menu {
	private static JFrame frame;
	private static JPanel panel;
	private static JButton productos;
	private static JButton categorias;
	private static JButton usuarios;
	private static JButton salir;
	
	private static ControladorCat controlador;
	private static Connection conn;
	private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final static String USUARIO = "alumno";
	private final static String PASSWORD = "Curso2022";
	
	public static void main(String[] args) throws DAOException, DomainException {
		//Arranco la BBDD
		System.out.println("-------- Prueba de conexion a BBDD --------");

		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("No falta la inclusion del driver de oracle?");
			e.printStackTrace();
		}

		System.out.println("Oracle JDBC Driver Registered!");

		try {
			conn = DriverManager.getConnection(URL, USUARIO, PASSWORD);
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			System.out.println("Ha fallado la conexion, compruebe la consola");
			e.printStackTrace();
		}

		if (conn != null) 
			System.out.println("Hecho!, Ya tiene pleno acceso al gestor de la BBDD");
		else 
			System.out.println("Error al hacer la conexión!");
		
		controlador = new ControladorCat();
		
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
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});	
	}

}
