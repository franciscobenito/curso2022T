package es.rf.tienda.vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import es.rf.tienda.controladores.ControladorUsu;
import es.rf.tienda.dominio.Usuario;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;

public class VistaUsuario {

	private JFrame frame;
	private JPanel panel;
	private JPanel panelLabel;
	private JPanel panelTabla;
	private JPanel panelButton;
	private JLabel usuLabel;
	private JButton botonCrear;
	private JButton botonModif;
	private JButton botonVer;
	private JButton botonBorrar;
	private JButton botonSalir;
	private JTable tablaUsu;
	private JScrollPane scroll;
	
	private static ControladorUsu controlador;
	private Usuario usu;
	
	public VistaUsuario() throws DAOException, DomainException{
		vista("Usuarios");				//TODO: solucionar los dos errores como los de VistaCategoría y, además, terminar la clase. Faltan columnas de la tabla para ver/modificar
	}

	public void vista (String title) throws DAOException, DomainException {
		controlador = new ControladorUsu();
		
		//Ventana principal
		frame = new JFrame(title);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,300);
		frame.setLocationRelativeTo(null);
		
		//Paneles
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout(0,0));
		panelLabel = new JPanel();
		panelTabla = new JPanel();
		panelButton = new JPanel();
		panelButton.setLayout(new FlowLayout());
				
		//Contenedores
		usuLabel = new JLabel("Tabla de usuarios");
		botonCrear = new JButton("Nuevo");
		botonModif = new JButton("Modificar");
		botonVer = new JButton("Ver");
		botonBorrar = new JButton("Borrar");
		botonSalir = new JButton("Salir");
		
		//Tabla
		List<Usuario> listaUsu = controlador.leerTodos();
		String[][] objeto = new String[listaUsu.size()][3];
		for(int i = 0; i < listaUsu.size(); i++) {
			objeto[i][0] = Integer.toString(listaUsu.get(i).getId_usuario());
			objeto[i][1] = listaUsu.get(i).getUser_nombre();
		}
        String[] tituloCol = { "ID", "Nombre"};
        tablaUsu = new JTable(objeto, tituloCol);
		scroll = new JScrollPane(tablaUsu, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		//Activo el listener para seleccionar una fila de la tabla
		tablaUsu.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {
			    	if (tablaUsu.getSelectedRow() > -1) {
			        	int idSelec = Integer.parseInt(tablaUsu.getValueAt(tablaUsu.getSelectedRow(), 0).toString());
			        	usu=new Usuario(idSelec);
			        }
		    	}
			}
		});
		
		//Añadimos los paneles al panel principal
		panelLabel.add(usuLabel);
		panelTabla.add(tablaUsu);
		panelTabla.add(scroll, BorderLayout.CENTER);
		panelButton.add(botonCrear);
		panelButton.add(botonModif);
		panelButton.add(botonVer);
		panelButton.add(botonBorrar);
		panelButton.add(botonSalir);
				
		panel.add(panelLabel, BorderLayout.NORTH);
		panel.add(panelTabla, BorderLayout.CENTER);
		panel.add(panelButton, BorderLayout.SOUTH);
		
		frame.setContentPane(panel);
		frame.setVisible(true);
		
		//Acción botones
		botonCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearNuevoUsu();
			}
		});
		botonModif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarUsu(usu);
			}
		});
		botonVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verUsuario(usu);
			}
		});
		botonBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarUsuario(usu);
			}
		});
		botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});		
	}
	
	/**
	 * CREA UNA USUARIO NUEVO
	 */
	public void crearNuevoUsu() {
		ventanaNueva("Crear usuario");
	}
	
	/**
	 * MODIFICA UNA CATEGORIA
	 * @param cat Categoría a modificar
	 */
	public void modificarUsu(Usuario usu) {
		ventanaNueva("Modificar usuario");
	}
	
	/**
	 * MUESTRA UNA CATEGORIA 
	 * @param cat Categoría a ver
	 */
	public void verUsuario(Usuario usu) {
		controlador.leer(usu);
		ventanaNueva("Ver usuario");
	}
	
	public void borrarUsuario(Usuario usu) {
		if (controlador.borrar(usu))
			System.out.println("Categoria borrada");
	}
	
	/**
	 * Crea la ventana para crear una categoría nueva, o para modificar o ver una ya existente
	 * @param titulo Título de la ventana nueca++va
	 * @return la ventana nueva
	 */
	private JFrame ventanaNueva(String titulo) {
		JFrame frame2 = new JFrame(titulo);
		frame2.getContentPane().setLayout(new BorderLayout());
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setSize(400,150);
		frame2.setLocationRelativeTo(null);
		
		//Paneles
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		JPanel panelLabel2 = new JPanel();
		panelLabel2.setLayout(new GridLayout(3,1));
		JPanel panelText2 = new JPanel();
		panelText2.setLayout(new GridLayout(3,1));
		JPanel panelButton2 = new JPanel();
		panelButton2.setLayout(new FlowLayout());
		
		panel2.add(panelLabel2, BorderLayout.WEST);
		panel2.add(panelText2, BorderLayout.CENTER);
		panel2.add(panelButton2, BorderLayout.SOUTH);
		
		//Contenedores
		JLabel idLabel = new JLabel("ID");
		JLabel nombreLabel = new JLabel("Nombre");
		JTextField idText = new JTextField();
		idText.setEditable(false);
		JTextField nombreText = new JTextField();
		JTextField descripcionText = new JTextField();
		JButton botonAceptarCrear = new JButton("Crear");
		JButton botonAceptarModif = new JButton("Modificar");
		JButton botonSalir = new JButton("Salir");
		
		switch (titulo) {
			case "Crear usuario":
				panelButton2.add(botonAceptarCrear);	
			break;
				
			case "Modificar usuario":
				idText.setText(""+usu.getId_usuario());
				nombreText.setText(usu.getUser_nombre());
				nombreText.setEditable(true);
				panelButton2.add(botonAceptarModif);	
			break;
			
			case "Ver usuario":
				idText.setText(""+usu.getId_usuario());
				nombreText.setText(usu.getUser_nombre());
				nombreText.setEditable(false);
			break;
		}
		
		panelButton2.add(botonSalir);	
		panelLabel2.add(idLabel);
		panelLabel2.add(nombreLabel);
		panelText2.add(idText);
		panelText2.add(nombreText);
		panelText2.add(descripcionText);
		
		//Acción de botones
		botonAceptarCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!nombreText.getText().equals("")) {
					usu = new Usuario(Integer.parseInt(idText.getText()));
					controlador.grabar(usu);
				}
				
				frame2.setVisible(false);
				frame2.dispose();
			}
		});	
		botonAceptarModif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				Usuario usuActualizado = new Usuario(Integer.parseInt(idText.getText()));
				if (controlador.actualizar(usuActualizado))
					System.out.println("Usuario modificado");
				
			    frame2.setVisible(false);
				frame2.dispose();
			}
		});	
		botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame2.setVisible(false);
				frame2.dispose();
			}
		});	
		
		frame2.setContentPane(panel2);
		frame2.setVisible(true);
		
		return frame2;
	}
}