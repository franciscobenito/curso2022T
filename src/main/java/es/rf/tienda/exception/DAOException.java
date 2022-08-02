package es.rf.tienda.exception;

@SuppressWarnings("serial")
public class DAOException extends Exception {
	
	public DAOException(){
		super();
	}
	
	public DAOException(String mensaje) {
		super(mensaje);
	}
	
}
