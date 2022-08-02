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
	private JButton botonCrear;
	private JButton botonModif;
	private JButton botonVer;
	private JButton botonBorrar;
	private JButton botonSalir;
	private JTable tablaCat;
	private JScrollPane scroll;
	
	private static ControladorCat controlador;
	private Categoria cat;
	
	/**
	 * Constructor de la clase
	 * @throws DAOException
	 * @throws DomainException
	 */
	public VistaCategoria() throws DAOException, DomainException{
		vista("Categorias");				//TODO: solucionar SCROLL y que se actualice la tabla cuando se crea, modifica o borra una categoría
	}

	/**
	 * Crea la pantalla principal de las categorías
	 * @param title Título de la ventana
	 * @throws DAOException
	 * @throws DomainException
	 */
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
		botonCrear = new JButton("Nueva");
		botonModif = new JButton("Modificar");
		botonVer = new JButton("Ver");
		botonBorrar = new JButton("Borrar");
		botonSalir = new JButton("Salir");
		
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
		
		//Activo el listener para seleccionar una fila de la tabla
		tablaCat.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {
			    	if (tablaCat.getSelectedRow() > -1) {
			        	int idSelec = Integer.parseInt(tablaCat.getValueAt(tablaCat.getSelectedRow(), 0).toString());
			        	String nombreSelec = tablaCat.getValueAt(tablaCat.getSelectedRow(), 1).toString();
			        	String descSelec = tablaCat.getValueAt(tablaCat.getSelectedRow(), 2).toString();
			        	cat=new Categoria(idSelec, nombreSelec, descSelec);
			        }
		    	}
			}
		});
		
		//Añadimos los paneles al panel principal
		panelLabel.add(catLabel);
		panelTabla.add(tablaCat);
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
				crearNuevaCat();
			}
		});
		botonModif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarCat(cat);
			}
		});
		botonVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCategoria(cat);
			}
		});
		botonBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarCategoria(cat);
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
	 * CREA UNA CATEGORIA NUEVA
	 */
	public void crearNuevaCat() {
		ventanaNueva("Crear categoría");
	}
	
	/**
	 * MODIFICA UNA CATEGORIA
	 * @param cat Categoría a modificar
	 */
	public void modificarCat(Categoria cat) {
		ventanaNueva("Modificar categoría");
	}
	
	/**
	 * MUESTRA UNA CATEGORIA 
	 * @param cat Categoría a ver
	 */
	public void verCategoria(Categoria cat) {
		controlador.leer(cat);
		ventanaNueva("Ver categoría");
	}
	
	public void borrarCategoria(Categoria cat) {
		if (controlador.borrar(cat))
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
		JLabel descrLabel = new JLabel("Descripcion");
		JTextField idText = new JTextField();
		idText.setEditable(false);
		JTextField nombreText = new JTextField();
		JTextField descripcionText = new JTextField();
		JButton botonAceptarCrear = new JButton("Crear");
		JButton botonAceptarModif = new JButton("Modificar");
		JButton botonSalir = new JButton("Salir");
		
		switch (titulo) {
			case "Crear categoría":
				panelButton2.add(botonAceptarCrear);	
			break;
				
			case "Modificar categoría":
				idText.setText(""+cat.getId());
				nombreText.setText(cat.getCat_nombre());
				nombreText.setEditable(true);
				descripcionText.setText(cat.getCat_descripcion());
				descripcionText.setEditable(true);
				
				panelButton2.add(botonAceptarModif);	
			break;
			
			case "Ver categoría":
				idText.setText(""+cat.getId());
				nombreText.setText(cat.getCat_nombre());
				nombreText.setEditable(false);
				descripcionText.setText(cat.getCat_descripcion());
				descripcionText.setEditable(false);
			break;
		}
		
		panelButton2.add(botonSalir);	
		panelLabel2.add(idLabel);
		panelLabel2.add(nombreLabel);
		panelLabel2.add(descrLabel);
		panelText2.add(idText);
		panelText2.add(nombreText);
		panelText2.add(descripcionText);
		
		//Acción de botones
		botonAceptarCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!nombreText.getText().equals("")) {
					cat = new Categoria(Integer.parseInt(idText.getText()), nombreText.getText(), descripcionText.getText());
					controlador.grabar(cat);
				}
				
				frame2.setVisible(false);
				frame2.dispose();
			}
		});	
		botonAceptarModif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				Categoria catActualizada = new Categoria(Integer.parseInt(idText.getText()), nombreText.getText(), descripcionText.getText());
				if (controlador.actualizar(catActualizada))
					System.out.println("Categoria modificada");
				
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