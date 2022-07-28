package es.rf.tienda.objetos.daos;

import es.rf.tienda.dominio.Categoria;

public class CategoriaDAO{
	
	public CategoriaDAO(){
		OracleJDBC.getInstance();
	}
	
	//TODO
	public void listarCategorias() {
	
	}

	//TODO
	public Categoria listarUnaCategoria(String values) {
		return null;
	}
	
	public void crearCategoria(Categoria obj) {
		String nuevaCat = "INSERT INTO CATEGORIES VALUES('" + obj.getId() + "','" + obj.getCat_nombre()+ "','" + obj.getCat_descripcion() + "')";
		try {
			OracleJDBC.ejecutar(nuevaCat);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
