package logica;

import javax.persistence.*;
import datatypes.DtElementoUsuario;
import datatypes.tipoElemento;

@Entity
@DiscriminatorValue("LP")
@NamedQueries({
@NamedQuery(name="existeListaParticular", query="select p.nombre from Particular p where p.nombre=:nombreLista")
})
@NamedNativeQueries({
		@NamedNativeQuery(name = "buscarListasFecha", query = "SELECT c.usuario_nickname, e.nombre from particular p " +
				"inner join elemento e " +
				"on e.id = p.id " +
				"inner join canal c " +
				"on c.id = e.canal_id " +
				"inner join listareproduccion lp " +
				"on lp.id = p.id " +
				"left join listareproduccion_video lpv " +
				"ON lpv.listareproduccion_id = lp.id " +
				"left join video ON video.id = lpv.videos_id " +
				"where c.publico = true " +
				"and p.publico = true " +
				"and upper(e.nombre) like upper(?)" +
				"order by video.fpublicacion desc "),
		@NamedNativeQuery(name = "buscarListasNombre", query = "SELECT e.nombre, c.usuario_nickname, video.descripcion from particular p\n" +
				"inner join elemento e " +
				"on e.id = p.id " +
				"inner join canal c " +
				"on c.id = e.canal_id " +
				"inner join listareproduccion lp " +
				"on lp.id = p.id " +
				"left join listareproduccion_video lpv " +
				"ON lpv.listareproduccion_id = lp.id " +
				"left join video ON video.id = lpv.videos_id " +
				"where c.publico = true " +
				"and p.publico = true " +
				"and upper(e.nombre) like upper(?)" +
				"order by e.nombre asc")
})


public class Particular extends ListaReproduccion{
	private boolean publico;
	
	@ManyToOne
	private Categoria categoria;

	public Particular() {
		super();
	}


	public Particular(String nombre, Canal canal, boolean publico, Categoria cat) {
		super(nombre, canal);
		this.publico = publico;
		this.categoria = cat;
	}

	//Getters & Setters
	@Override
	public boolean isPublico() {
		return publico;
	} 
	
	public void setPublico(boolean publico) {
		this.publico = publico;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	
	//Operaciones
	public DtElementoUsuario obtenerElemCategoria() {
		DtElementoUsuario particular = new DtElementoUsuario(this.getCanal().getUsuario().getNickname(), this.getNombre(), tipoElemento.LISTA);
		return particular;
	}
}