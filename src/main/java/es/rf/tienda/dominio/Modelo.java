package es.rf.tienda.dominio;

public abstract class Modelo {

	private String pk = "id_cat";
	private String tablaCat = "categoria";
	
	Modelo(String tablaCat, String pk){
		this.tablaCat=tablaCat;
		this.pk=pk;
	}
	
	public String getTabla() {
		return tablaCat;
	}
	
	public String getNombrePK() {
		return pk;
	}
	
	public abstract boolean isValid();
	public abstract int getId();
	public abstract void setId(int id);
	public abstract String[] toArray();
}
