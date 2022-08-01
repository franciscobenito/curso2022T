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
	private JButton nuevo;
	private JButton modificar;
	private JButton ver;
	private JButton salir;
	private JTable tablaUsu;
	//private DefaultTableModel modelo;
	private JScrollPane scroll;
	
	private static ControladorUsu controlador;
	private Usuario usu;
	
	public VistaUsuario() throws DAOException, DomainException{
		vista("Usuarios");
	}
	
	//private static VistaUsuario instancia;
	
	//public static VistaUsuario getInstance() throws DAOException, DomainException {
	//	if (instancia==null) instancia = new VistaUsuario();
	//	return instancia;
	//}

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
		nuevo = new JButton("Nuevo");
		modificar = new JButton("Modificar");
		ver = new JButton("Ver");
		salir = new JButton("Salir");
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
		
		//Añadimos los paneles al panel principal
		panelLabel.add(usuLabel);
		panelTabla.add(tablaUsu);
		panelTabla.add(scroll, BorderLayout.CENTER);
		panelButton.add(nuevo);
		panelButton.add(modificar);
		panelButton.add(ver);
		panelButton.add(salir);
				
		panel.add(panelLabel, BorderLayout.NORTH);
		panel.add(panelTabla, BorderLayout.CENTER);
		panel.add(panelButton, BorderLayout.SOUTH);
		
		frame.setContentPane(panel);
		frame.setVisible(true);
		
		//Acción botones
		nuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearNuevoUsu();
			}
		});
		modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablaUsu.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				    @Override
				    public void valueChanged(ListSelectionEvent event) {
				        if (tablaUsu.getSelectedRow() > -1) {
				        	int id = Integer.parseInt(tablaUsu.getValueAt(tablaUsu.getSelectedRow(), 0).toString());
				        	//String nombre = tablaCat.getValueAt(tablaCat.getSelectedRow(), 1).toString();
				        	//String desc = tablaCat.getValueAt(tablaCat.getSelectedRow(), 2).toString();
				        	usu=new Usuario(id);
				        	modificarUsu(usu);
				        }
				    }
				});	
			}
		});
		ver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablaUsu.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				    @Override
				    public void valueChanged(ListSelectionEvent event) {
				        if (tablaUsu.getSelectedRow() > -1) {
				        	int id = Integer.parseInt(tablaUsu.getValueAt(tablaUsu.getSelectedRow(), 0).toString());
				        	//String nombre = tablaCat.getValueAt(tablaCat.getSelectedRow(), 1).toString();
				        	//String desc = tablaCat.getValueAt(tablaCat.getSelectedRow(), 2).toString();
				        	usu=new Usuario(id);
				        	verUsuario(usu);
				        }
				        else
				        	System.out.println("Selecciona una fila de la tabla");
				    }
				});
				
			}
		});
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});		
	}

	//TODO: conseguir que cree la categoría bien. El error estará en el CategoriaDAO seguramente. Porque no la crea en la bbdd
	/****************************
	 * CREA UNA CATEGORIA NUEVA *
	 ****************************/
	public void crearNuevoUsu() {
		//Nueva ventana para crear categorías
		JFrame frame2 = new JFrame("Añadir categoría");
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
		JTextField nombreText = new JTextField();
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");
		
		panelLabel2.add(idLabel);
		panelLabel2.add(nombreLabel);
		panelText2.add(idText);
		panelText2.add(nombreText);
		panelButton2.add(aceptar);
		panelButton2.add(cancelar);	
		
		//Acción de botones
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer.parseInt(idText.getText());
					if(!idText.getText().equals("") && !nombreText.getText().equals("")) {
						usu = new Usuario(Integer.parseInt(idText.getText()));
						controlador.grabar(usu);
					}
				}catch(Exception e1){
					e1.getMessage();
					System.out.println("¡El id debe ser un valor numerico!");
				}finally {
					controlador.leerTodos();
					frame2.setVisible(false);
					frame2.dispose();
				}
			}
		});	
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame2.setVisible(false);
				frame2.dispose();
			}
		});	
		
		frame2.setContentPane(panel2);
		frame2.setVisible(true);
	}
	
	//TODO: conseguir que modifique una fila existente y que muestre la categoria seleccionada
	/****************************
	 *  MODIFICA UNA CATEGORIA  *
	 ****************************/
	public void modificarUsu(Usuario usu) {
		//Nueva ventana para modificar usuarios
		JFrame frame2 = new JFrame("Modificar Usuario");
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
		
		JTextField idText = new JTextField(usu.getId_usuario());
		idText.setEditable(false);
		JTextField nombreText = new JTextField(usu.getUser_nombre());
		
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");
		
		panelLabel2.add(idLabel);
		panelLabel2.add(nombreLabel);
		panelText2.add(idText);
		panelText2.add(nombreText);
		panelButton2.add(aceptar);
		panelButton2.add(cancelar);	
		
		//Acción de botones
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				Usuario usuActualizado = new Usuario(Integer.parseInt(idText.getText()));
				if (controlador.actualizar(usuActualizado))
					System.out.println("Categoria modificada");
				
				List<Usuario> listaUsu = controlador.leerTodos();
				String[][] objeto = new String[listaUsu.size()][3];
				for(int i = 0; i < listaUsu.size(); i++) {
					objeto[i][0] = Integer.toString(listaUsu.get(i).getId_usuario());
					objeto[i][1] = listaUsu.get(i).getUser_nombre();
				}
				
				tablaUsu = new JTable(objeto, null);
				
			    frame2.setVisible(false);
				frame2.dispose();
			}
		});	
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame2.setVisible(false);
				frame2.dispose();
			}
		});	
		
		frame2.setContentPane(panel2);
		frame2.setVisible(true);
	}

	//TODO: conseguir que muestre sólo una ventana. Ahora se abren hasta 4. Tampoco se ve la id_categoria
	/****************************
	 *  MUESTRA UN USUARIO   *
	 ****************************/
	public void verUsuario(Usuario usu) {
		controlador.leer(usu);
		
		//Nueva ventana para modificar categorías
		JFrame frame2 = new JFrame("Ver categoría");
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

		JTextField idText = new JTextField(usu.getId_usuario());
		idText.setEditable(false);
		JTextField nombreText = new JTextField(usu.getUser_nombre());
		nombreText.setEditable(false);
		
		JButton salir = new JButton("Salir");
		
		panelLabel2.add(idLabel);
		panelLabel2.add(nombreLabel);
		panelText2.add(idText);
		panelText2.add(nombreText);
		panelButton2.add(salir);	
		
		//Acción de botones
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame2.setVisible(false);
				frame2.dispose();
			}
		});	
		
		frame2.setContentPane(panel2);
		frame2.setVisible(true);
	}
}
