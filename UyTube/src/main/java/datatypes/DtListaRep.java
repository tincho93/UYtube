package datatypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtListaRep {
	private String nombre;
	private Boolean publico;
	private Boolean esParticular;
	private String categoria;
	
	//Constructores
	public DtListaRep() {
		super();
	}

	public DtListaRep(String nombre, Boolean publico, Boolean esParticular) {
		super();
		this.nombre = nombre;
		this.publico = publico;
		this.esParticular = esParticular;
	}
	
	
	
	public DtListaRep(String nombre, Boolean publico, Boolean esParticular, String categoria) {
		super();
		this.nombre = nombre;
		this.publico = publico;
		this.esParticular = esParticular;
		this.categoria = categoria;
	}

	//Getters
	public String getNombre() {
		return nombre;
	}

	public Boolean getPublico() {
		return publico;
	}

	public Boolean getEsParticular() {
		return esParticular;
	}

	public String getCategoria() {
		return categoria;
	}

	//Setters
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPublico(Boolean publico) {
		this.publico = publico;
	}

	public void setEsParticular(Boolean esParticular) {
		this.esParticular = esParticular;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
