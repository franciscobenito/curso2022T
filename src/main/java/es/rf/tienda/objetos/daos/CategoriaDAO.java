package es.rf.tienda.objetos.daos;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.interfaces.daos.ICategoria;

public class CategoriaDAO implements ICategoria {
	private static Connection conn;
	private static ResultSet rs;
	private static Statement st;

	private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	//private final static String DATABASE = "";
	private final static String USUARIO = "hr";
	private final static String PASSWORD = "hr";
	
	@Override	//TODO: solucionar
	public Categoria leerRegistro(Categoria c) throws DAOException {
		String sql = "SELECT * FROM Categoria WHERE id_categoria = " +c.getId() + " AND cat_nombre = " + c.getCat_nombre()
		+ " AND cat_descripcion = " + c.getCat_descripcion();
		
		try {
			ResultSet rs = OracleJDBC.ejecutarQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return c;
	}

	@Override	//TODO: solucionar
	public List<Categoria> leerTodos() {
		String sql = "SELECT * FROM Categoria";
		List<Categoria> list = new ArrayList<>();
		
		try {
			conn = DriverManager.getConnection(URL, USUARIO, PASSWORD);
			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = OracleJDBC.ejecutarQuery(sql);
			System.out.println(sql);
			
			while(rs.next()) {
				Categoria c = new Categoria();
				//System.out.println("ID: "+rs.getInt(1)+"\tNOMBRE: "+rs.getString(2)+"\tDESCRIPCION: "+rs.getString(3));
				list.add(c);
				System.out.println(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override	//TODO: solucionar
	public List<Categoria> leerRegistros(Categoria c) {
		List<Categoria> lista = new ArrayList<Categoria>();
		String sql = "SELECT * FROM" + c;
		
		try {
			ResultSet rs = OracleJDBC.ejecutarQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	@Override	//TODO: solucionar
	public void insertar(Categoria c) {
		String sql = "INSERT INTO categoria VALUES ("+c.getId()+", '"+c.getCat_nombre()+"', '"+c.getCat_descripcion()+"')";
		
		try {
			ResultSet rs = OracleJDBC.ejecutarQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override	//TODO: solucionar
	public void actualizar(Categoria c) {
		String sql = "UPDATE categoria SET cat_nombre = "+c.getCat_nombre()+" AND cat_descripcion = "+c.getCat_descripcion()
						+"WHERE id_categoria = "+c.getId();
		
		
		try {
			ResultSet rs = OracleJDBC.ejecutarQuery(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override	//TODO: solucionar
	public void borrar(Categoria c) {
		String sql = "DELETE FROM categoria WHERE id_categoria = "+c.getId();
		
		try {
			ResultSet rs = OracleJDBC.ejecutarQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
