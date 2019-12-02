package logica;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NombrePorDefecto {
	@Id
	private String nombre;

	public NombrePorDefecto() {
		super();
	}
	
	public NombrePorDefecto(String nombre) {
		super();
		this.nombre = nombre;
	}
}
