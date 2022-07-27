package es.rf.tienda.dominio;

import java.util.Date;

public class Pedido {

	private static final int TARJETA_LONG_MAX = 16;
	private static final int TARJETA_LONG_MIN = 16;
	private static final int CCV_LONG_MAX = 3;
	private static final int CCV_LONG_MIN = 3;
	
	private int id_pedido;
	private int id_usuario;
	private int id_producto;
	private int car_cantidad;
	private double car_precio;
	private Direccion car_envio;
	private Direccion car_pago;
	private String car_tarjeta;
	private Date car_feCadud;
	private int car_ccv;
	private String car_nombre;
	private int car_stat;
	private String[] car_feCambio;
	
	public Pedido() {
		
	}
}
