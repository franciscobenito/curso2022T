package es.rf.tienda.interfaces.daos;

import java.util.List;

import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.exception.DAOException;

public interface ICategoria {
	public Categoria leerRegistro(Categoria c) throws DAOException;
	public List<Categoria> leerTodos();
	public List<Categoria> leerRegistros(Categoria c);
	public void insertar(Categoria c);
	public void actualizar(Categoria c);
	public void borrar(Categoria c);
}
