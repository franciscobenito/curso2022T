package es.rf.tienda.dominio;

import java.util.Calendar;
import java.util.Date;

public class Usuario {

	private static final int NOMBRE_LONG_MAX = 100;
	private static final int NOMBRE_LONG_MIN = 5;
	private static final int EMAIL_LONG_MAX = 100;
	private static final int EMAIL_LONG_MIN = 6;
	private static final int PASSWORD_LONG_MAX = 20;
	private static final int PASSWORD_LONG_MIN = 8;
	private static final int DNI_LONG_MAX = 12;
	private static final int DNI_LONG_MIN = 12;
	
	private int id_usuario;
	private String user_nombre;
	private String user_email;
	private String user_pass;
	private int user_tipo;
	private String user_dni;
	private Calendar user_fecAlta;
	private Calendar user_fecConfirmacion;
	
	
	public Usuario() {
		user_dni=null;
		user_fecAlta=null;
		user_fecConfirmacion=null;
	}


	public int getId_usuario() {
		return id_usuario;
	}


	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}


	public String getUser_nombre() {
		return user_nombre;
	}


	public void setUser_nombre(String user_nombre) {
		this.user_nombre = user_nombre;
	}


	public String getUser_email() {
		return user_email;
	}


	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}


	public String getUser_pass() {
		return user_pass;
	}


	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}


	public int getUser_tipo() {
		return user_tipo;
	}


	public void setUser_tipo(int user_tipo) {
		this.user_tipo = user_tipo;
	}


	public String getUser_dni() {
		return user_dni;
	}


	public void setUser_dni(String user_dni) {
		this.user_dni = user_dni;
	}


	public Calendar getUser_fecAlta() {
		return user_fecAlta;
	}


	public void setUser_fecAlta(Calendar user_fecAlta) {
		this.user_fecAlta = user_fecAlta;
	}


	public Calendar getUser_fecConfirmacion() {
		return user_fecConfirmacion;
	}


	public void setUser_fecConfirmacion(Calendar user_fecConfirmacion) {
		this.user_fecConfirmacion = user_fecConfirmacion;
	}
	
}