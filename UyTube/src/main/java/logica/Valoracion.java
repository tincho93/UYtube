package logica;

import javax.persistence.*;

@NamedNativeQueries( {
		@NamedNativeQuery(name = "obtenerValoracionesDeUsuario", query = "select v.id from Valoracion v where v.usuario_nickname = :nick")
} )
@Entity
public class Valoracion {
	@Id
	@SequenceGenerator(name = "valoracionGenerator", sequenceName = "VALORACION_SEQUENCE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "valoracionGenerator")
	private int id;
	
	private boolean gusta;
	
	@ManyToOne
	private Usuario usuario;


	//Constructores
	public Valoracion() {
		super();
	}
	
	public Valoracion(boolean gusta, Usuario usuario) {
		super();
		this.gusta = gusta;
		this.usuario = usuario;
	}
	
	//Getters & Setters
	public boolean isGusta() {
		return gusta;
	}

	public void setGusta(boolean gusta) {
		this.gusta = gusta;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
