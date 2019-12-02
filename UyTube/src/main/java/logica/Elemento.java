package logica;

import datatypes.DtElementoUsuario;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Elemento {
	@Id
	@SequenceGenerator(name = "elementoGenerator", sequenceName = "ELEMENTO_SEQUENCE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "elementoGenerator")
	protected int id; 
	
	private String nombre;

	@ManyToOne
	protected Categoria categoria;


	@ManyToOne
	private Canal canal;
	
	//Constructor por defecto
	public Elemento() {
		super();
	}

	public Elemento(String nombre, Canal canal) {
		super();
		this.nombre = nombre;
		this.canal = canal;
	}

	//Getters & Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Canal getCanal() {
		return canal;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public abstract DtElementoUsuario obtenerElemCategoria();
}