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
		String sql ="SELECT * FROM USUARIO";
		List<Usuario> lista = new ArrayList<>();
		try {
			rs = OracleJDBC.ejecutarQuery(sql);
			OracleJDBC.ejecutarQuery(sql);
			OracleJDBC.commit();
			
			while (rs.next()) {
				  int id = rs.getInt("ID_USUARIO");
				  Usuario usu = new Usuario(id);
				  lista.add(usu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public Usuario listarUnUsuario(Usuario obj) {
		String sql = "SELECT * FROM USUARIO WHERE ";

		if (obj.getId_usuario() != 0) {
			sql = sql + "ID_USUARIO = " + obj.getId_usuario();
		} else if (!Validator.isVacio(obj.getUser_nombre())) {
			sql = sql + "USER_NOMBRE = '" + obj.getUser_nombre() + "'";
		}
		
		Usuario usu = null;
		
		try {
			ResultSet rs = OracleJDBC.ejecutarQuery(sql);
			OracleJDBC.ejecutarQuery(sql);
			OracleJDBC.commit();
			usu = new Usuario(rs.getInt(1));
		} catch (Exception e) {
			e.getMessage();
		}
		
		return usu;
	}
	
	public List<Usuario> leerSQL(String sql) {
		List<Usuario> lista = new ArrayList<Usuario>();

		try {
			ResultSet rs = OracleJDBC.ejecutarQuery(sql);
			OracleJDBC.ejecutarQuery(sql);
			OracleJDBC.commit();
			Usuario u = new Usuario(rs.getInt(1));
			lista.add(u);
			
		} catch (Exception e) {
			e.getMessage();
		}

		return lista;
	}
	
	public void crearUsuario(Usuario obj) {
		if (obj.isValid()) {
			String sql = "INSERT INTO CATEGORIA VALUES (" + obj.getId_usuario() + ",'" + obj.getUser_nombre() + "')";
			try {
				OracleJDBC.ejecutarQuery(sql);
				OracleJDBC.commit();
				System.out.println("Usuario creado\n");
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		else
			System.out.println("No se ha podido crear el usuario\n");
	}

	public boolean actualizarUsuario(Usuario obj) {
		if (obj.isValid()) {
			String sql = "UPDATE USUARIO SET " + "cat_nombre = '" + obj.getUser_nombre()+ "', WHERE ID_USUARIO = " + obj.getId_usuario();
			try {
				OracleJDBC.ejecutarQuery(sql);
				OracleJDBC.commit();
				System.out.println("Usuario actualizado\n");
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
		if (obj.isValid()) {
			String sql = "DELETE FROM USUARIO WHERE ID_USUARIO = " + obj.getId_usuario();
			try {
				OracleJDBC.ejecutarQuery(sql);
				OracleJDBC.commit();
				System.out.println("Usuario borrada\n");
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
