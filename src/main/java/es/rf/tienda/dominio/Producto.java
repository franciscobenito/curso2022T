package es.rf.tienda.dominio;

import java.util.Date;

public class Producto {
	
	private static final int IDPROD_LONG_MAX = 5;
	private static final int IDPROD_LONG_MIN = 5;
	private static final int DESCRIPCION_LONG_MAX = 100;
	private static final int DESCRIPCION_LONG_MIN = 5;
	private static final int EXPLICACION_LONG_MAX = 2000;
	private static final int EXPLICACION_LONG_MIN = 5;
	private static final int UNIVENTA_LONG_MAX = 10;
	private static final int UNIVENTA_LONG_MIN = 1;
	private static final int USORECOMENDADO_LONG_MAX = 2000;
	private static final int USORECOMENDADO_LONG_MIN = 0;
	private static final int ESTADO_LONG_MAX = 1;
	private static final int ESTADO_LONG_MIN = 1;
	
	private String idProducto;
	private String proDescripcion;
	private String pro_desLarga;
	private double pro_precio;
	private int pro_stock;
	private Date pro_fecRepos;
	private Date pro_fecActi;
	private Date pro_fecDesacti;
	private String pro_uniVenta;
	private double pro_cantXUniVenta;
	private String pro_uniUltNivel;
	private int id_pais;
	private String pro_usoRecomendado;
	private int id_categoria;
	private int pro_stkReservado;
	private int pro_nStkAlto;
	private int pro_nStkBajo;
	private char pro_stat;

	public Producto() {
		pro_desLarga=null;
		pro_stock=0;
		pro_fecRepos=null;
		pro_fecActi=null;
		pro_fecDesacti=null;
		pro_cantXUniVenta=0;
		pro_uniUltNivel=null;
		pro_usoRecomendado=null;
		pro_stkReservado=0;
		pro_nStkAlto=0;
		pro_nStkBajo=0;
		pro_stat='A';
		
	}

	public String getIdProducto() {
		return idProducto;
	}


	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}


	public String getProDescripcion() {
		return proDescripcion;
	}


	public void setProDescripcion(String proDescripcion) {
		this.proDescripcion = proDescripcion;
	}


	public String getPro_desLarga() {
		return pro_desLarga;
	}


	public void setPro_desLarga(String pro_desLarga) {
		this.pro_desLarga = pro_desLarga;
	}


	public double getPro_precio() {
		return pro_precio;
	}


	public void setPro_precio(double pro_precio) {
		this.pro_precio = pro_precio;
	}


	public int getPro_stock() {
		return pro_stock;
	}


	public void setPro_stock(int pro_stock) {
		this.pro_stock = pro_stock;
	}


	public Date getPro_fecRepos() {
		return pro_fecRepos;
	}

	public void setPro_fecRepos(Date pro_fecRepos) {
		this.pro_fecRepos = pro_fecRepos;
	}

	public Date getPro_fecActi() {
		return pro_fecActi;
	}

	public void setPro_fecActi(Date pro_fecActi) {
		this.pro_fecActi = pro_fecActi;
	}

	public Date getPro_fecDesacti() {
		return pro_fecDesacti;
	}

	public void setPro_fecDesacti(Date pro_fecDesacti) {
		this.pro_fecDesacti = pro_fecDesacti;
	}

	public String getPro_uniVenta() {
		return pro_uniVenta;
	}

	public void setPro_uniVenta(String pro_uniVenta) {
		this.pro_uniVenta = pro_uniVenta;
	}

	public double getPro_cantXUniVenta() {
		return pro_cantXUniVenta;
	}

	public void setPro_cantXUniVenta(double pro_cantXUniVenta) {
		this.pro_cantXUniVenta = pro_cantXUniVenta;
	}

	public String getPro_uniUltNivel() {
		return pro_uniUltNivel;
	}

	public void setPro_uniUltNivel(String pro_uniUltNivel) {
		this.pro_uniUltNivel = pro_uniUltNivel;
	}

	public int getId_pais() {
		return id_pais;
	}

	public void setId_pais(int id_pais) {
		this.id_pais = id_pais;
	}

	public String getPro_usoRecomendado() {
		return pro_usoRecomendado;
	}

	public void setPro_usoRecomendado(String pro_usoRecomendado) {
		this.pro_usoRecomendado = pro_usoRecomendado;
	}

	public int getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}

	public int getPro_stkReservado() {
		return pro_stkReservado;
	}

	public void setPro_stkReservado(int pro_stkReservado) {
		this.pro_stkReservado = pro_stkReservado;
	}

	public int getPro_nStkAlto() {
		return pro_nStkAlto;
	}

	public void setPro_nStkAlto(int pro_nStkAlto) {
		this.pro_nStkAlto = pro_nStkAlto;
	}

	public int getPro_nStkBajo() {
		return pro_nStkBajo;
	}

	public void setPro_nStkBajo(int pro_nStkBajo) {
		this.pro_nStkBajo = pro_nStkBajo;
	}

	public char getPro_stat() {
		return pro_stat;
	}

	public void setPro_stat(char pro_stat) {
		this.pro_stat = pro_stat;
	}
}
