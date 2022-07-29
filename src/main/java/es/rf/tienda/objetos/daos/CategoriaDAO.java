package es.rf.tienda.objetos.daos;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.util.Validator;

public class CategoriaDAO{
	
	public CategoriaDAO(){
		OracleJDBC.getInstance();
	}
	
	public List<Categoria> listarCategorias() {
		ResultSet rs;
		String sql ="SELECT * FROM CATEGORIA";
		List<Categoria> lista = new ArrayList<>();
		try {
			rs = OracleJDBC.ejecutarQuery(sql);
			if(OracleJDBC.ejecutar(sql)>1)
				OracleJDBC.commit();
			
			while (rs.next()) {
				  int id = rs.getInt("ID_CATEGORIA");
				  String nombre = rs.getString("CAT_NOMBRE");
				  String descripcion = rs.getString("CAT_DESCRIPCION");
				  Categoria cat = new Categoria(id, nombre, descripcion);
				  lista.add(cat);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public Categoria listarUnaCategoria(Categoria obj) {
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
			ResultSet rs = OracleJDBC.ejecutarQuery(sql);
			if(OracleJDBC.ejecutar(sql)>1)
				OracleJDBC.commit();
			cat = new Categoria(rs.getInt(1), rs.getString(2), rs.getString(3));
		} catch (Exception e) {
			e.getMessage();
		}
		
		return cat;
	}
	
	public List<Categoria> leerSQL(String sql) {
		List<Categoria> lista = new ArrayList<Categoria>();

		try {
			ResultSet rs = OracleJDBC.ejecutarQuery(sql);
			if(OracleJDBC.ejecutar(sql)>1)
				OracleJDBC.commit();
			Categoria c = new Categoria(rs.getInt(1), rs.getString(2), rs.getString(3));
			lista.add(c);
			
		} catch (Exception e) {
			e.getMessage();
		}

		return lista;
	}
	
	public void crearCategoria(Categoria obj) {
		
		String sql = "INSERT INTO CATEGORIA VALUES ";

		if (obj.isValid()) {
			sql = sql + "(" + obj.getId() + ",'" + obj.getCat_nombre() + "','" + obj.getCat_descripcion() + "')";
			try {
				if(OracleJDBC.ejecutar(sql)>1)
					OracleJDBC.commit();
				System.out.println("Categoría creada\n");
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		else
			System.out.println("No se ha podido crear la categoría\n");
	}

	public boolean actualizarCategoria(Categoria obj) {
		String sql = "UPDATE * FROM CATEGORIA WHERE ID_CATEGORIA = ";
		if (obj.isValid()) {
			sql = sql + "(" + obj.getId() + ",'" + obj.getCat_nombre() + "','" + obj.getCat_descripcion() + "')";

			try {
				if(OracleJDBC.ejecutar(sql)>1)
					OracleJDBC.commit();
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

	public boolean borrarCategoria(Categoria obj) {
		String sql = "DELETE FROM CATEGORIA WHERE ID_CATEGORIA = ";
		if (obj.isValid()) {
			sql = sql + "(" + obj.getId() + ",'" + obj.getCat_nombre() + "','" + obj.getCat_descripcion() + "')";

			try {
				if(OracleJDBC.ejecutar(sql)>1)
					OracleJDBC.commit();
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

	
	
}
