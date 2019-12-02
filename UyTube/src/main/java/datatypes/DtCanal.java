package datatypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtCanal {
	private String nombre;
	private String descripcion;
	private Boolean publico;
	private String categoria;
	
	//Constructores
	public DtCanal() {
		super();
	}

	public DtCanal(String nombre, String descripcion, Boolean publico, String categoria) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.publico = publico;
		this.categoria = categoria;
	}

	//Getters
	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Boolean getPublico() {
		return publico;
	}

	public String getCategoria() { return categoria; }

	//Setters
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setPublico(Boolean publico) {
		this.publico = publico;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
