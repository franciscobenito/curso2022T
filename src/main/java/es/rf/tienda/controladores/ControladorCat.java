package es.rf.tienda.controladores;

import java.util.List;
import java.util.Map;

import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.interfaces.daos.ICategoria;
import es.rf.tienda.objetos.daos.CategoriaDAO;

public class ControladorCat implements Controlador<Categoria>{

	private ICategoria cDAO;
	
	public ControladorCat() {
		cDAO = new CategoriaDAO();
	}
	
	@Override
	public Categoria leer(Categoria obj) {
		try {
			return cDAO.leerRegistro(obj);
		} catch (DAOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Categoria> leerTodos() {
		return cDAO.leerTodos();
	}

	@Override
	public List<Categoria> leerSQL(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean grabar(Categoria obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean actualizar(Categoria obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean borrar(Categoria obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, String> obtenerSelect() {
		// TODO Auto-generated method stub
		return null;
	}

}
