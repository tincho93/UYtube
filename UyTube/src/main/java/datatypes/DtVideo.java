package datatypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Calendar;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtVideo {
	private String nombre;
	private String descripcion;
	private Calendar fPublicacion;
	private Integer duracion;
	private String url;
	private Boolean publico;
	private String categoria;
	
	//Constructor
	public DtVideo() {
		super();
	}

	public DtVideo(String nombre, String descripcion, Calendar fPublicacion, Integer duracion, String url,
			Boolean publico, String categoria) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fPublicacion = fPublicacion;
		this.duracion = duracion;
		this.url = url;
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

	public Calendar getfPublicacion() {
		return fPublicacion;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public String getUrl() {
		return url;
	}

	public Boolean getPublico() {
		return publico;
	}

	public String getCategoria() {
		return categoria;
	} 
	
	//Setters
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setfPublicacion(Calendar fPublicacion) {
		this.fPublicacion = fPublicacion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setPublico(Boolean publico) {
		this.publico = publico;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
