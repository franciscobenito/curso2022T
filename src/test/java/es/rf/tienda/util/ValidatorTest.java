package es.rf.tienda.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

import org.junit.jupiter.api.Test;

class ValidatorTest {
	
	//Alfanumerico
	public final static String NOT_ALFANUMERIC = "jkhk.,! ¡( hjk657675/&%/&%/?=)?)?cvx vcxcvxvc"; 
	public final static String ALFANUMERIC = "uhsbcaub131413e ucha2 23 oin";
	
	//Codigo producto
	public final static String CODIGO_PRODUCTO_OK = "AB123";
	public final static String CODIGO_PRODUCTO_NO1 = "A123B";
	public final static String CODIGO_PRODUCTO_NO2 = "Ab123";
	public final static String CODIGO_PRODUCTO_NO3 = "A0123";
	public final static String CODIGO_PRODUCTO_FORMATO_ERR_NUM = "12345";
	public final static String CODIGO_PRODUCTO_FORMATO_ERR_ALF = "ABCDE";
	public final static String CODIGO_PRODUCTO_FORMATO_ERR_LON = "AB345678";
	
	//Vacio
	String STRING_NULA;
	public final static String STRING_VACIA = "";
	
	//Correo electronico
	public final static String CORREO_ELECTRONICO_CORRECTO = "migarcia@recursosformacion.com";
	public final static String CORREO_ELECTRONICO_ERRONEO_1 = "migarcia.recursosformacion.com";
	public final static String CORREO_ELECTRONICO_ERRONEO_2 = "migarcia@recursosformacion";
	
	//DNI
	public final static String NUMERO_DNI_OK = "12.345.678-Z"; 
	public final static String NUMERO_DNI_ERROR_LETRA = "12.345.678-A";
	public final static String NUMERO_DNI_ERROR_FORM_CORTO = "12.2.678-A";
	public final static String NUMERO_DNI_ERROR_FORM_CORTO2 = "122.678-A";
	public final static String NUMERO_DNI_ERROR_FORM_LARGO = "123.456.678-A";
	public final static String NUMERO_DNI_ERROR_FORM_ERR = "12345678A";
	public final static String NUMERO_DNI_ERROR_FORM_ERR2 = "12.345.678.A";
	
	//Email
	public final static String MAIL_OK = "Francisco.benito@qaracter.es";
	public final static String MAIL_OK1 = "francisco@qaracter.info.com";
	public final static String MAIL_OK2 = "francisco_10@qaracter.es";
	public final static String MAIL_SIN_DOMINIO = "franciscobenito";
	public final static String MAIL_SIN_USER = "@qaracter.es";
	public final static String MAIL_SIN_PUNTO = "francisco.benito@qaracter";
	
	//Telefono
	public final static String PHONENUMBER_OK = "123456789";
	public final static String PHONENUMBER_CORTO = "1234567 ";
	public final static String PHONENUMBER_LARGO = "123456789101112";
	public final static String PHONENUMBER_FORMATO = "(12)345678901";
	
	//Password -> ENTRE 8 Y 12 CARACTERES. MÍNIMO UNA MAYUSCULA, UNA MINUSCULA, UN CARACTER ESPECIAL, USUARIO NO FORMAR PARTE DE ELLA
	public final static String USER = "francisco";
	public final static String PASSWORD_VALIDA = "Sb@qar12";
	public final static String PASSWORD_ER1 = "Sbqar12"; //Sin caracter especial
	public final static String PASSWORD_ER2 = "b@qar12"; //Sin mayus
	public final static String PASSWORD_ER3 = "S@12"; //Sin minus
	public final static String PASSWORD_ER4 = "francisco@Qar12"; //Con user

	//Cadena
	private static final String TEXTO_OK = "Aijsdsaijd d sdoinc. Jo ksmv 12";
	
	//Rango
	public final static double VALOR = 50;
	public final static double VALOR_MAXIMO = 101;
	public final static double VALOR_MINIMO = 0;
	public final static int RANGO_MAXIMO = 100;
	public final static int RANGO_MINIMO = 1;
	
	//Fechas
	public static String FECHA_OK = "01/01/2022";
	public static String FECHA_OK_BIS = "29/02/2022";
	public static String FECHA_ERR1 = "00/01/2022";
	public static String FECHA_ERR2 = "32/01/2022";
	public static String FECHA_ERR3 = "30/02/2022";
	public static String FECHA_ERR4 = "2a/02/2022";
	public static String FECHA_ERR5 = "01/00/2022";
	public static String FECHA_ERR6 = "01/13/2022";
	public static String FECHA_ERR7 = "01/f2/2022";
	public static String FECHA_ERR8 = "01/02/00a0";
	public static Calendar DIA_ACTUAL;
	public static Calendar DIA_ANTERIOR;
	public static Calendar DIA_POSTERIOR;
	
	static {
		Calendar aux = Calendar.getInstance();
		aux.add(Calendar.DAY_OF_YEAR, -1);
		DIA_ANTERIOR = aux;
		
		aux = Calendar.getInstance();
		aux.add(Calendar.DAY_OF_YEAR, +1);
		DIA_POSTERIOR = aux;
		
		DIA_ACTUAL = Calendar.getInstance();
	}
	
	/**
	 * ALFANUMERICO
	 */
	@Test
	void testIsAlfanumericIS() {
		assertTrue(Validator.isAlfanumeric(ALFANUMERIC));
	}
	
	@Test
	void testIsAlfanumericNOT() {
		assertFalse(Validator.isAlfanumeric(NOT_ALFANUMERIC));
	}

	/**
	 * VACIO
	 */
	@Test
	void testIsVacio() {
		assertAll(
				() -> assertTrue(Validator.isVacio(STRING_NULA)),
				() -> assertTrue(Validator.isVacio(STRING_VACIA)),
				() -> assertFalse(Validator.isVacio(ALFANUMERIC)));
	}

	/**
	 * TELEFONO
	 */
	@Test
	void testCumplePhoneNumber() {
		assertTrue(Validator.cumplePhoneNumber(PHONENUMBER_OK));
	}

	@Test
	void testNoCumplePhoneNumber() {
		assertFalse(Validator.cumplePhoneNumber(PHONENUMBER_CORTO));
		assertFalse(Validator.cumplePhoneNumber(PHONENUMBER_LARGO));
		assertFalse(Validator.cumplePhoneNumber(PHONENUMBER_FORMATO));
	}
	
	/**
	 * EMAIL
	 */
	@Test
	void testIsEmailValido() {
		assertAll(
				() -> assertTrue(Validator.isEmailValido(CORREO_ELECTRONICO_CORRECTO)),
				() -> assertFalse(Validator.isEmailValido(CORREO_ELECTRONICO_ERRONEO_1)),
				() -> assertFalse(Validator.isEmailValido(CORREO_ELECTRONICO_ERRONEO_2)));
	}

	/**
	 * DNI
	 */
	@Test
	void testCumpleDNI() {
		assertAll(
				() -> assertTrue(Validator.cumpleDNI(NUMERO_DNI_OK)),
				() -> assertFalse(Validator.cumpleDNI(NUMERO_DNI_ERROR_LETRA)),
				() -> assertFalse(Validator.cumpleDNI(NUMERO_DNI_ERROR_FORM_CORTO)),
				() -> assertFalse(Validator.cumpleDNI(NUMERO_DNI_ERROR_FORM_CORTO2)),
				() -> assertFalse(Validator.cumpleDNI(NUMERO_DNI_ERROR_FORM_LARGO)),
				() -> assertFalse(Validator.cumpleDNI(NUMERO_DNI_ERROR_FORM_ERR)),
				() -> assertFalse(Validator.cumpleDNI(NUMERO_DNI_ERROR_FORM_ERR2)));
	}

	/**
	 * RANGOS
	 */
	@Test
	void testCumpleRangoInt() {
		assertTrue(Validator.cumpleRango(VALOR,RANGO_MINIMO,RANGO_MAXIMO));
	}

	@Test
	void testCumpleRangoDouble() {
		assertTrue(Validator.cumpleRango(VALOR,RANGO_MINIMO,RANGO_MAXIMO));
	}

	/**
	 * LONGITUD TEXTO
	 */
	@Test
	void testCumpleLongitudMin() {
		assertTrue(Validator.cumpleLongitudMin(TEXTO_OK, TEXTO_OK.length()+1));
	}
	
	@Test
	void testCumpleLongitudMin1() {
		assertTrue(Validator.cumpleLongitudMin(TEXTO_OK, TEXTO_OK.length()));
	}

	
	@Test
	void testNOCumpleLongitudMin() {
		assertFalse(Validator.cumpleLongitudMin(TEXTO_OK, TEXTO_OK.length()-1));
	}


	@Test
	void testCumpleLongitudMax() {
		assertTrue(Validator.cumpleLongitudMax(TEXTO_OK, TEXTO_OK.length()-1));
	}
	
	@Test
	void testCumpleLongitudMax1() {
		assertTrue(Validator.cumpleLongitudMax(TEXTO_OK, TEXTO_OK.length()));
	}
	
	@Test
	void testNOCumpleLongitudMax() {
		assertFalse(Validator.cumpleLongitudMax(TEXTO_OK, TEXTO_OK.length()+1));
	}

	@Test
	void testCumpleLongitud() {
		assertTrue(Validator.cumpleLongitud(TEXTO_OK, TEXTO_OK.length()-1, TEXTO_OK.length()+1));
	}

	/**
	 * PRODUCTO
	 */
	@Test
	void testIsCodigoPoductoOK() {
		assertTrue(Validator.isCodigoProducto(CODIGO_PRODUCTO_OK));
	}
	
	@Test
	void testIsCodigoProductoNoOK() {
		assertFalse(Validator.isCodigoProducto(CODIGO_PRODUCTO_NO1));
		assertFalse(Validator.isCodigoProducto(CODIGO_PRODUCTO_NO2));
		assertFalse(Validator.isCodigoProducto(CODIGO_PRODUCTO_NO3));
	}
	
	@Test
	void testIsCodigoProductoFormatoERR() {
		assertFalse(Validator.isCodigoProducto(CODIGO_PRODUCTO_FORMATO_ERR_NUM));
		assertFalse(Validator.isCodigoProducto(CODIGO_PRODUCTO_FORMATO_ERR_LON));
		assertFalse(Validator.isCodigoProducto(CODIGO_PRODUCTO_FORMATO_ERR_ALF));
	}
	
	/**
	 * FECHAS
	 */
	@Test
	void testValDateMin() {	
		assertTrue(Validator.valDateMin(DIA_POSTERIOR, DIA_ACTUAL));
	}
	
	@Test
	void testValDateMinERR() {	
		assertFalse(Validator.valDateMin(DIA_ANTERIOR, DIA_ACTUAL));
	}
	
	@Test
	void testValDateMinERR1() {	
		assertFalse(Validator.valDateMin(DIA_POSTERIOR, DIA_POSTERIOR));
	}

	@Test
	void testValDateMax() { 
		assertTrue(Validator.valDateMax(DIA_ANTERIOR, DIA_ACTUAL));
	}
	
	@Test
	void testValDateMaxERR() { 
		assertFalse(Validator.valDateMax(DIA_ANTERIOR, DIA_ANTERIOR));
	}
	
	@Test
	void testValDateMaxERR1() { 
		assertFalse(Validator.valDateMax(DIA_POSTERIOR, DIA_ACTUAL));
	}

	//FORMATOS
	@Test
	void testEsFechaValida() {
		assertTrue(Validator.esFechaValida(FECHA_OK));
	}
	
	@Test
	void testEsFechaValida1() {
		assertTrue(Validator.esFechaValida(FECHA_OK_BIS));
	}
	
	@Test
	void testNOEsFechaValida1() {
		assertFalse(Validator.esFechaValida(FECHA_ERR1));
	}

	
	@Test
	void testNOEsFechaValida2() {
		assertFalse(Validator.esFechaValida(FECHA_ERR2));
	}

	
	@Test
	void testNOEsFechaValida3() {
		assertFalse(Validator.esFechaValida(FECHA_ERR3));
	}

	
	@Test
	void testNOEsFechaValida4() {
		assertFalse(Validator.esFechaValida(FECHA_ERR4));
	}
	
	@Test
	void testNOEsFechaValida5() {
		assertFalse(Validator.esFechaValida(FECHA_ERR5));
	}

	
	@Test
	void testNOEsFechaValida6() {
		assertFalse(Validator.esFechaValida(FECHA_ERR6));
	}

	
	@Test
	void testNOEsFechaValida7() {
		assertFalse(Validator.esFechaValida(FECHA_ERR7));
	}

	
	@Test
	void testNOEsFechaValida8() {
		assertFalse(Validator.esFechaValida(FECHA_ERR8));
	}

	/**
	 * PASSWORD
	 */
	@Test
	void testEsPasswordValida() {
		assertTrue(Validator.esPasswordValida(PASSWORD_VALIDA));
	}

	@Test
	void testEsPasswordValidaER1() {
		assertFalse(Validator.esPasswordValida(PASSWORD_ER1));
	}
	
	@Test
	void testEsPasswordValidaER2() {
		assertFalse(Validator.esPasswordValida(PASSWORD_ER2));
	}
	
	@Test
	void testEsPasswordValidaER3() {
		assertFalse(Validator.esPasswordValida(PASSWORD_ER3));
	}
	
	@Test
	void testEsPasswordValidaER4() {
		assertFalse(Validator.esPasswordValida(PASSWORD_ER4));
	}
	
	/**
	 * EMAIL
	 * 
	 */
	@Test
	void isEmailValido() {
		assertTrue(Validator.isEmailValido(MAIL_OK));
	}

	@Test
	void isEmailValido1() {
		assertTrue(Validator.isEmailValido(MAIL_OK1));
	}
	
	@Test
	void isEmailValido2() {
		assertTrue(Validator.isEmailValido(MAIL_OK2));
	}
	
	@Test
	void isNOTEmailValido() {
		assertFalse(Validator.isEmailValido(MAIL_SIN_DOMINIO));
	}
	
	@Test
	void isNOTEmailValido1() {
		assertFalse(Validator.isEmailValido(MAIL_SIN_USER));
	}
	
	@Test
	void isNOTEmailValido2() {
		assertFalse(Validator.isEmailValido(MAIL_SIN_PUNTO));
	}	
}
