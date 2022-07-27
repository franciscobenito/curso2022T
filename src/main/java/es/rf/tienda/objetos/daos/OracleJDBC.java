package es.rf.tienda.objetos.daos;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleJDBC {
	private static OracleJDBC instancia = null;
	private static Connection conn;

	private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	//private final static String DATABASE = "";
	private final static String USUARIO = "hr";
	private final static String PASSWORD = "hr";

	public static void main(String[] argv) {
		conn = null;
		OracleJDBC ojdbc = OracleJDBC.getInstance();

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
	
	private void conexion() {
		System.out.println("-------- Prueba de conexion a BBDD --------");

		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("No falta la inclusion del driver de oracle?");
			e.printStackTrace();
			return;
		}

		System.out.println("Oracle JDBC Driver Registered!");

		try {
			conn = DriverManager.getConnection(URL, USUARIO, PASSWORD);
		} catch (SQLException e) {
			System.out.println("Ha fallado la conexion, compruebe la consola");
			e.printStackTrace();
			return;
		}

		if (conn != null) 
			System.out.println("Hecho!, Ya tiene pleno acceso al gestor de la BBDD");
		else 
			System.out.println("Error al hacer la conexión!");
		
	}

	public void closeConnection() throws Exception {
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

	public void commit() throws Exception {
		try {
			if (conn != null)
				conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Error al realizar commit");
		}
	}

	public void rollback() throws Exception {
		try {
			if (conn != null)
				conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Error al realizar rollback");
		}
	}

	public void closeStatement(PreparedStatement ps) throws Exception {
		try {
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("No ha sido posible realizar operación close sobre elemento Statement");
		}
	}

	public void closeStatement(Statement ps) throws Exception {
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
			throw new Exception("No ha sido posible realizar operaci�n close sobre elemento ResultSet");
		}
	}

	public int ejecutar(String sql) throws Exception {
		System.out.println("ejecutar:" + sql);
		Statement stm = null;
		int retorno;
		
		try {
			stm = conn.createStatement();
			retorno = stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw (new Exception("error en " + sql));
		} finally {
			closeStatement(stm);
		}
		return retorno;
	}

	public ResultSet ejecutarQuery(String sql) throws Exception {
		System.out.println("ejecutarQuery:" + sql);
		Statement stm = null;
		ResultSet retorno;
		
		try {
			stm = conn.createStatement();
			retorno = stm.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw (new Exception("error en " + sql));
		}
		
		return retorno;
	}

	public int consigueClave(String tabla, String campo) throws Exception {
		String sql = "SELECT MAX(" + campo + ") FROM " + tabla;
		ResultSet rs = ejecutarQuery(sql);
		
		try {
			if (rs.next())
				System.out.println("Tiene datos");
			else
				return 1;
			return rs.getInt(0) + 1;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Error buscando PK :" + sql);
		}
	}
}
