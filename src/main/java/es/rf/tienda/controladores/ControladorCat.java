package es.rf.tienda.controladores;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.objetos.daos.CategoriaDAO;
import es.rf.tienda.objetos.daos.OracleJDBC;
import es.rf.tienda.util.Validator;
import es.rf.tienda.vistas.VistaCategoria;

public class ControladorCat implements Controlador<Categoria>{

	private CategoriaDAO cDAO;
	//private VistaCategoria vistaCat;
	
	public ControladorCat() throws DAOException, DomainException {
		super();
	}
	
	public ControladorCat(VistaCategoria vistaCat) {
		cDAO = new CategoriaDAO();;
		//this.vistaCat = vistaCat;
		
		//vistaCat.setControlador(this);
	}

	
	//TODO
	/*private void setOption(String option, Categoria cat) { 
	
		try {
			switch (option) {
			case "ADD":
				
				break;
			case "UPDATE":
				
				break;
			case "DELETE":
				
				break;
			}
		}catch (Exception e) {
			e.getMessage();
		}
		
	}*/

	@Override
	public Categoria leer(Categoria obj) {
		String sql = "SELECT * FROM CATEGORIA WHERE ";

		if (obj.getId() != 0) {
			sql = sql + "ID_CATEGORIA = " + obj.getId();
		} else if (!Validator.isVacio(obj.getCat_nombre())) {
			sql = sql + "CAT_NOMBRE = '" + obj.getCat_nombre() + "'";
		} else if (!Validator.isVacio(obj.getCat_descripcion())) {
			sql = sql + "CAT_DESCRIPCION = '" + obj.getCat_descripcion() + "'";
		}
		
		Categoria cat = null;
		
		try {
			OracleJDBC.getInstance();
			ResultSet rs = OracleJDBC.ejecutarQuery(sql);
			
			while (rs.next()) {
				cat = new Categoria(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
		} catch (Exception e) {
			e.getMessage();
		}
		
		return cat;
	}

	@Override
	public List<Categoria> leerTodos() {
		return leerSQL("SELECT * FROM CATEGORIA");
	}

	@Override
	public List<Categoria> leerSQL(String sql) {
		List<Categoria> lista = new ArrayList<Categoria>();

		try {
			OracleJDBC.getInstance();
			ResultSet rs = OracleJDBC.ejecutarQuery(sql);
			while (rs.next()) {

				Categoria c = new Categoria(rs.getInt(1), rs.getString(2), rs.getString(3));
				lista.add(c);

			}
		} catch (Exception e) {
			e.getMessage();
		}

		return lista;
	}
	
	@Override
	public void grabar(Categoria obj) {
		cDAO.crearCategoria(obj);
		String query = "INSERT INTO CATEGORIA VALUES ";

		if (obj.isValid()) {
			query = query + "(" + obj.getId() + ",'" + obj.getCat_nombre() + "','" + obj.getCat_descripcion() + "')";

			OracleJDBC.getInstance();
			try {
				OracleJDBC.ejecutar(query);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		else
			System.out.println("No se ha podido crear la categoría");

	}

	@Override
	public boolean actualizar(Categoria obj) {
		String query = "UPDATE * FROM CATEGORIA WHERE ID_CATEGORIA = ";
		if (obj.isValid()) {
			query = query + "(" + obj.getId() + ",'" + obj.getCat_nombre() + "','" + obj.getCat_descripcion() + "')";
			
			OracleJDBC.getInstance();
			try {
				OracleJDBC.ejecutar(query);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return true;
		}
		else {
			System.out.println("No se ha podido actualizar la categoría");
			return false;
		}
			
	}

	@Override
	public boolean borrar(Categoria obj) {
		String query = "DELETE FROM CATEGORIA WHERE ID_CATEGORIA = ";
		if (obj.isValid()) {
			query = query + "(" + obj.getId() + ",'" + obj.getCat_nombre() + "','" + obj.getCat_descripcion() + "')";
			
			OracleJDBC.getInstance();
			try {
				OracleJDBC.ejecutar(query);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return true;
		}
		else {
			System.out.println("No se ha podido borrar la categoría");
			return false;
		}
	}

	@Override
	public Map<String, String> obtenerSelect() {
		// TODO Auto-generated method stub
		return null;
	}
}
