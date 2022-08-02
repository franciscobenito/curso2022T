package es.rf.tienda.objetos.daos;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import es.rf.tienda.exception.DAOException;

public class OracleJDBC {
	private static OracleJDBC instancia = null;
	private static Connection conn;

	private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	//private final static String DATABASE = "";
	private final static String USUARIO = "alumno";
	private final static String PASSWORD = "Curso2022";

	public static void main(String[] argv) {
		conn = null;
		OracleJDBC.getInstance();

	}

	private OracleJDBC() {	
		conexion();
	}

	public static OracleJDBC getInstance() {
		if (instancia == null) {
			instancia = new OracleJDBC();
		}
		return instancia;
	}
	
	private static Connection conexion() {
		//System.out.println("-------- Prueba de conexion a BBDD --------");

		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("No falta la inclusion del driver de oracle?");
			e.printStackTrace();
		}

		//System.out.println("Oracle JDBC Driver Registered!");

		try {
			conn = DriverManager.getConnection(URL, USUARIO, PASSWORD);
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			System.out.println("Ha fallado la conexion, compruebe la consola");
			e.printStackTrace();
		}

		/*if (conn != null) 
			System.out.println("Hecho!, Ya tiene pleno acceso al gestor de la BBDD");
		else 
			System.out.println("Error al hacer la conexión!");
		*/
		return conn;
	}

	public static void closeConnection() throws Exception {
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (Exception e) {
			System.out.println("No se ha podido cerrar la conexión a la BD");
			throw new Exception("Error al cerrar conexión a BD");
		} finally {
			conn = null;
		}
	}

	public static void commit() throws Exception {
		try {
			if (conn != null)
				conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Error al realizar commit");
		}
	}

	public static void rollback() throws Exception {
		try {
			if (conn != null)
				conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Error al realizar rollback");
		}
	}

	public static void closeStatement(PreparedStatement ps) throws Exception {
		try {
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("No ha sido posible realizar operación close sobre elemento Statement");
		}
	}

	public static void closeStatement(Statement ps) throws Exception {
		try {
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("No ha sido posible realizar operación close sobre elemento Statement");
		}
	}

	public void closeResulSet(ResultSet rs) throws Exception {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("No ha sido posible realizar operación close sobre elemento ResultSet");
		}
	}

	public static int ejecutar(String sql) throws Exception {
		System.out.println("ejecutar:" + sql);
		Statement stm = null;
		conn=conexion();
		int retorno;
		
		try {
			stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			retorno = stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw (new Exception("error en " + sql));
		} finally {
			closeStatement(stm);
		}
		return retorno;
	}

	public static ResultSet ejecutarQuery(String sql) throws Exception {
		System.out.println("ejecutarQuery:" + sql);
		Statement stm = null;
		ResultSet rs;
		conn=conexion();
		
		try {
			stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stm.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw (new Exception("error en " + sql));
		}
		
		return rs;
	}

	public static int consigueClave(String tabla, String campo) throws Exception {
		String sql = "SELECT MAX(" + campo + ") as ix FROM " + tabla;
		ResultSet rs = ejecutarQuery(sql);
		try {
			if (rs.next())
				System.out.println("Tiene datos");
			else
				return 1;
			return rs.getInt("ix") + 1;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Error buscando PK :" + sql);
		}

	}
}
