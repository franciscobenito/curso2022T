package es.rf.tienda.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RutinasTest {

	public final static String VALOR_CAMPO_1 = "A123B";
	public final static String NOMBRE_CAMPO_1 = "AB123";
	public final static String VALOR_CAMPO_2 = "A123B";
	public final static String NOMBRE_CAMPO_2 = "Ab123";
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddCampoStringStringDoubleString() {
		String salida="";
		assertEquals(NOMBRE_CAMPO_2+" = "+VALOR_CAMPO_2, 
						Rutinas.addCampo(salida, NOMBRE_CAMPO_2, VALOR_CAMPO_2, ","));
	}
	
	@Test
	public void testAddCampoStringStringStringStringNULL() {
		String dato = null;
		String salida="";
		assertEquals(NOMBRE_CAMPO_1+" = null", 
				Rutinas.addCampo(salida, NOMBRE_CAMPO_1, dato, ","));
	}
}
