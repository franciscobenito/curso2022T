package es.rf.tienda.controladores;

import java.util.List;
import java.util.Map;

import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.objetos.daos.CategoriaDAO;

public class ControladorCat implements Controlador<Categoria>{

	private CategoriaDAO cDAO;
	
	public ControladorCat() throws DAOException, DomainException {
		super();
		cDAO = new CategoriaDAO();
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
		return cDAO.listarUnaCategoria(obj);
	}

	@Override
	public List<Categoria> leerTodos() {
		return cDAO.listarCategorias();
	}

	@Override
	public List<Categoria> leerSQL(String sql) {
		return cDAO.leerSQL(sql);
	}
	
	@Override
	public void grabar(Categoria obj) {
		cDAO.crearCategoria(obj);
	}

	@Override
	public boolean actualizar(Categoria obj) {
		return cDAO.actualizarCategoria(obj);			
	}

	@Override
	public boolean borrar(Categoria obj) {
		return cDAO.borrarCategoria(obj);
	}

	@Override
	public Map<String, String> obtenerSelect() {
		// TODO Auto-generated method stub
		return null;
	}
}
