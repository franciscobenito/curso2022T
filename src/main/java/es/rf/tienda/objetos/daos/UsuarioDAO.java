package es.rf.tienda.objetos.daos;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import es.rf.tienda.dominio.Usuario;
import es.rf.tienda.util.Validator;

public class UsuarioDAO {
	public UsuarioDAO(){
		OracleJDBC.getInstance();
	}
	
	public List<Usuario> listarUsuarios() {
		ResultSet rs;
		String sql ="SELECT * FROM Usuario";
		List<Usuario> lista = new ArrayList<>();
		try {
			rs = OracleJDBC.ejecutarQuery(sql);
			if(OracleJDBC.ejecutar(sql)>1)
				OracleJDBC.commit();
			
			while (rs.next()) {
				  int id = rs.getInt("ID_Usuario");
				  Usuario cat = new Usuario(id);
				  lista.add(cat);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public Usuario listarUnUsuario(Usuario obj) {
		String sql = "SELECT * FROM Usuario WHERE ";

		if (obj.getId_usuario() != 0) {
			sql = sql + "ID_Usuario = " + obj.getId_usuario();
		} else if (!Validator.isVacio(obj.getUser_nombre())) {
			sql = sql + "CAT_NOMBRE = '" + obj.getUser_nombre() + "'";
		}
		
		Usuario cat = null;
		
		try {
			ResultSet rs = OracleJDBC.ejecutarQuery(sql);
			if(OracleJDBC.ejecutar(sql)>1)
				OracleJDBC.commit();
			cat = new Usuario(rs.getInt(1));
		} catch (Exception e) {
			e.getMessage();
		}
		
		return cat;
	}
	
	public List<Usuario> leerSQL(String sql) {
		List<Usuario> lista = new ArrayList<Usuario>();

		try {
			ResultSet rs = OracleJDBC.ejecutarQuery(sql);
			if(OracleJDBC.ejecutar(sql)>1)
				OracleJDBC.commit();
			Usuario c = new Usuario(rs.getInt(1));
			lista.add(c);
			
		} catch (Exception e) {
			e.getMessage();
		}

		return lista;
	}
	
	public void crearUsuario(Usuario obj) {
		
		String sql = "INSERT INTO Usuario VALUES ";

		if (obj.isValid()) {
			sql = sql + "(" + obj.getId_usuario() + "')";
			try {
				if(OracleJDBC.ejecutar(sql)>1)
					OracleJDBC.commit();
				System.out.println("Usuario creada\n");
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		else
			System.out.println("No se ha podido crear el usuario\n");
	}

	public boolean actualizarUsuario(Usuario obj) {
		String sql = "UPDATE * FROM Usuario WHERE ID_Usuario = ";
		if (obj.isValid()) {
			sql = sql + "(" + obj.getId_usuario() + "')";

			try {
				if(OracleJDBC.ejecutar(sql)>1)
					OracleJDBC.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return true;
		}
		else {
			System.out.println("No se ha podido actualizar el usuario");
			return false;
		}
	}

	public boolean borrarUsuario(Usuario obj) {
		String sql = "DELETE FROM Usuario WHERE ID_Usuario = ";
		if (obj.isValid()) {
			sql = sql + "(" + obj.getId_usuario() + "')";

			try {
				if(OracleJDBC.ejecutar(sql)>1)
					OracleJDBC.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return true;
		}
		else {
			System.out.println("No se ha podido borrar el usuario");
			return false;
		}
	}

	
	
}
