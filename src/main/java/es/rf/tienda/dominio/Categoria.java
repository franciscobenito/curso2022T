package es.rf.tienda.dominio;

import es.rf.tienda.exception.DomainException;
import es.rf.tienda.util.Validator;

/**
 * 
 * Nombre		Categoria
 * Descripcion	Lista de categorías
 * @author 		Miguel Garcia
 * @version		13 de abr. de 2016
 *
 */
public class Categoria{	
	private int id_categoria;			//identificador categoria
	private String cat_nombre;			//nombre de la categoria
	private String cat_descripcion;		//descripcion de la categoria
	
	
	public Categoria(int id_categoria, String cat_nombre, String cat_descripcion){
		this.id_categoria = id_categoria;
		this.cat_nombre = cat_nombre;
		this.cat_descripcion = cat_descripcion;
	}
		
	public boolean isValid(){	
		return !Validator.isVacio(cat_nombre) && id_categoria > 0;
	}

	public int getId() {
		return id_categoria;
	}

	public void setId(int id) {
		id_categoria=id;
	}

	public String[] toArray() {
		return new String[] {getId()+"", getCat_nombre()+"", getCat_descripcion()+""};
	}
		
	/**
	 * Setter para identificador de categoria
	 * 
	 */
	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	
	/**
	 * Getter para el nombre de categoria
	 * @return cadena con el nombre de la categoria
	 */
	public String getCat_nombre() {
		return cat_nombre;
	}
	
	/**
	 * Setter para el nombre de categoria
	 * 
	 */
	public void setCat_nombre(String cat_nombre) throws DomainException {
		this.cat_nombre = cat_nombre;
	}
	
	/**
	 * Getter para la descripcion de categoria
	 * @return cadena con la descripcion de la categoria
	 */
	public String getCat_descripcion() {
		return cat_descripcion;
	}
	
	/**
	 * setter para la descripcion de categoria
	 * @throws DomainException 
	 * 
	 */
	public void setCat_descripcion(String cat_descripcion) throws DomainException {
		this.cat_descripcion = cat_descripcion;
	}
}
