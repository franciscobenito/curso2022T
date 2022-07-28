package es.rf.tienda.controladores;

import java.util.List;
import java.util.Map;

import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.objetos.daos.CategoriaDAO;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categoria> leerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categoria> leerSQL(String sql) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void grabar(Categoria obj) {
		cDAO.crearCategoria(obj);
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
