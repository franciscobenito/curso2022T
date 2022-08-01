package es.rf.tienda.controladores;

import java.util.List;
import java.util.Map;

import es.rf.tienda.dominio.Usuario;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.objetos.daos.UsuarioDAO;

public class ControladorUsu implements Controlador<Usuario>{

	private UsuarioDAO uDAO;
	
	public ControladorUsu() throws DAOException, DomainException {
		super();
		uDAO = new UsuarioDAO();
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
	public Usuario leer(Usuario obj) {
		return uDAO.listarUnUsuario(obj);
	}

	@Override
	public List<Usuario> leerTodos() {
		return uDAO.listarUsuarios();
	}

	@Override
	public List<Usuario> leerSQL(String sql) {
		return uDAO.leerSQL(sql);
	}
	
	@Override
	public void grabar(Usuario obj) {
		uDAO.crearUsuario(obj);
	}

	@Override
	public boolean actualizar(Usuario obj) {
		return uDAO.actualizarUsuario(obj);			
	}

	@Override
	public boolean borrar(Usuario obj) {
		return uDAO.borrarUsuario(obj);
	}

	@Override
	public Map<String, String> obtenerSelect() {
		// TODO Auto-generated method stub
		return null;
	}
}
