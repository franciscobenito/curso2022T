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

import es.rf.tienda.controladores.ControladorCat;
import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;

public class VistaCategoria{

	private JFrame frame;
	private JPanel panel;
	private JPanel panelLabel;
	private JPanel panelTabla;
	private JPanel panelButton;
	private JLabel catLabel;
	private JButton nuevo;
	private JButton modificar;
	private JButton ver;
	private JButton salir;
	private JTable tablaCat;
	//private DefaultTableModel modelo;
	private JScrollPane scroll;
	
	private static ControladorCat controlador;
	private Categoria cat;
	
	public VistaCategoria() throws DAOException, DomainException{
		vista("Categorias");
	}
	
	//private static VistaCategoria instancia;
	
	//public static VistaCategoria getInstance() throws DAOException, DomainException {
	//	if (instancia==null) instancia = new VistaCategoria();
	//	return instancia;
	//}

	public void vista (String title) throws DAOException, DomainException {
		controlador = new ControladorCat();
		
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
		catLabel = new JLabel("Tabla de categorías");
		nuevo = new JButton("Nueva");
		modificar = new JButton("Modificar");
		ver = new JButton("Ver");
		salir = new JButton("Salir");
		//Tabla
		List<Categoria> listaCat = controlador.leerTodos();
		String[][] objeto = new String[listaCat.size()][3];
		for(int i = 0; i < listaCat.size(); i++) {
			objeto[i][0] = Integer.toString(listaCat.get(i).getId());
			objeto[i][1] = listaCat.get(i).getCat_nombre();
			objeto[i][2] = listaCat.get(i).getCat_descripcion();
		}
        String[] tituloCol = { "ID", "Nombre", "Descripcion" };
        tablaCat = new JTable(objeto, tituloCol);
		scroll = new JScrollPane(tablaCat, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		//Añadimos los paneles al panel principal
		panelLabel.add(catLabel);
		panelTabla.add(tablaCat);
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
				crearNuevaCat();
			}
		});
		modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablaCat.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				    @Override
				    public void valueChanged(ListSelectionEvent event) {
				        if (tablaCat.getSelectedRow() > -1) {
				        	int id = Integer.parseInt(tablaCat.getValueAt(tablaCat.getSelectedRow(), 0).toString());
				        	String nombre = tablaCat.getValueAt(tablaCat.getSelectedRow(), 1).toString();
				        	String desc = tablaCat.getValueAt(tablaCat.getSelectedRow(), 2).toString();
				        	cat=new Categoria(id, nombre, desc);
				        	modificarCat(cat);
				        }
				    }
				});	
			}
		});
		ver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablaCat.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				    @Override
				    public void valueChanged(ListSelectionEvent event) {
				        if (tablaCat.getSelectedRow() > -1) {
				        	int id = Integer.parseInt(tablaCat.getValueAt(tablaCat.getSelectedRow(), 0).toString());
				        	String nombre = tablaCat.getValueAt(tablaCat.getSelectedRow(), 1).toString();
				        	String desc = tablaCat.getValueAt(tablaCat.getSelectedRow(), 2).toString();
				        	cat=new Categoria(id, nombre, desc);
				        	verCategoria(cat);
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
	public void crearNuevaCat() {
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
		JLabel descrLabel = new JLabel("Descripcion");
		JTextField idText = new JTextField();
		JTextField nombreText = new JTextField();
		JTextField descripcionText = new JTextField();
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");
		
		panelLabel2.add(idLabel);
		panelLabel2.add(nombreLabel);
		panelLabel2.add(descrLabel);
		panelText2.add(idText);
		panelText2.add(nombreText);
		panelText2.add(descripcionText);
		panelButton2.add(aceptar);
		panelButton2.add(cancelar);	
		
		//Acción de botones
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer.parseInt(idText.getText());
					if(!idText.getText().equals("") && !nombreText.getText().equals("") && !descripcionText.getText().equals("")) {
						cat = new Categoria(Integer.parseInt(idText.getText()), nombreText.getText(), descripcionText.getText());
						controlador.grabar(cat);
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
	public void modificarCat(Categoria cat) {
		//Nueva ventana para modificar categorías
		JFrame frame2 = new JFrame("Modificar categoría");
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
		JLabel descrLabel = new JLabel("Descripcion");
		
		JTextField idText = new JTextField(cat.getId());
		idText.setEditable(false);
		JTextField nombreText = new JTextField(cat.getCat_nombre());
		JTextField descripcionText = new JTextField(cat.getCat_descripcion());
		
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");
		
		panelLabel2.add(idLabel);
		panelLabel2.add(nombreLabel);
		panelLabel2.add(descrLabel);
		panelText2.add(idText);
		panelText2.add(nombreText);
		panelText2.add(descripcionText);
		panelButton2.add(aceptar);
		panelButton2.add(cancelar);	
		
		//Acción de botones
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				Categoria catActualizada = new Categoria(Integer.parseInt(idText.getText()), nombreText.getText(), descripcionText.getText());
				if (controlador.actualizar(catActualizada))
					System.out.println("Categoria modificada");
				
				List<Categoria> listaCat = controlador.leerTodos();
				String[][] objeto = new String[listaCat.size()][3];
				for(int i = 0; i < listaCat.size(); i++) {
					objeto[i][0] = Integer.toString(listaCat.get(i).getId());
					objeto[i][1] = listaCat.get(i).getCat_nombre();
					objeto[i][2] = listaCat.get(i).getCat_descripcion();
				}
				
				tablaCat = new JTable(objeto, null);
				
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
	 *  MUESTRA UNA CATEGORIA   *
	 ****************************/
	public void verCategoria(Categoria cat) {
		controlador.leer(cat);
		
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
		JLabel descrLabel = new JLabel("Descripcion");

		JTextField idText = new JTextField(cat.getId());
		idText.setEditable(false);
		JTextField nombreText = new JTextField(cat.getCat_nombre());
		nombreText.setEditable(false);
		JTextField descripcionText = new JTextField(cat.getCat_descripcion());
		descripcionText.setEditable(false);
		
		JButton salir = new JButton("Salir");
		
		panelLabel2.add(idLabel);
		panelLabel2.add(nombreLabel);
		panelLabel2.add(descrLabel);
		panelText2.add(idText);
		panelText2.add(nombreText);
		panelText2.add(descripcionText);
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