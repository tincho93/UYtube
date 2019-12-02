package logica;

import datatypes.DtElementoWeb;
import datatypes.DtListaRep;
import datatypes.DtVideo;
import datatypes.DtVideoUsuario;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@NamedNativeQueries({
		@NamedNativeQuery(name = "buscarCanalNombre", query = "select u.nickname, c.nombre, u.imagen\n" +
				"from usuarios u inner join canal c on c.usuario_nickname = u.nickname\n" +
				"where upper(c.nombre) like upper(?) and c.publico = true\n" +
				"order by c.nombre asc"),
		@NamedNativeQuery(name = "buscarCanalFecha", query = "select u.nickname, c.nombre, u.imagen, u.fecha_de_nacimiento\n" +
				"from usuarios u inner join canal c on c.usuario_nickname = u.nickname\n" +
				"where upper(c.nombre) like upper(?) and c.publico = true\n" +
				"order by u.fecha_de_nacimiento desc")
})
public class Canal {

	private String nombre;
	
	@Id
	@GeneratedValue
	private int id;

	private String descripcion;

	private Boolean publico;
	
	@OneToOne
	private Usuario usuario;

    @OneToOne
    private Categoria categoria;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Video> videos = new ArrayList<Video>();
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	private List<ListaReproduccion> listas = new ArrayList<ListaReproduccion>();
	
	
	//Constructores
	public Canal() {
		super();
	}

	public Canal(String nombre, String descripcion, boolean publico) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.publico = publico;
	}

	//Getters & Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Boolean getPublico() { return publico; }

	public void setPublico(Boolean publico) {
		this.publico = publico;
	}
	//Fin Getters & Setters

	
	public Particular agregarListaParticular(String nomL, boolean publico) {
		Particular lisPar = new Particular(nomL, this, publico, null);
		this.listas.add(lisPar);
		return lisPar;
	}

	public Particular agregarListaParticularConCategoria(String nomL, boolean publico, Categoria cat) {
		Particular lisPar = new Particular(nomL, this, publico, cat);
		this.listas.add(lisPar);
		return lisPar;
	}
	
	public void agregarListaDefecto(String nomL) {
		PorDefecto def = new PorDefecto(nomL, this);
		this.listas.add(def);
	}
	
	public Video agregarVideo(String nomV, String desc, Calendar fPub, int dur, String url) {
		boolean publico = false;
		Video v = new Video(nomV, desc, fPub, dur, url, publico, this);
		this.videos.add(v);
		return v;
	}

	public void agregarVideoListaParticular(Video v, String nomList) {
		for (ListaReproduccion lr:this.listas) {
			if (lr instanceof Particular) {
				if (lr.getNombre().contentEquals(nomList)) {
					lr.agregarVideo(v);
				}
			}
		}
	}
	
	public void agregarVideoListaPorDefecto(Video v, String nomList) {
		for (ListaReproduccion lr:this.listas) {
			if (lr instanceof PorDefecto) {
				if (lr.getNombre().contentEquals(nomList)) {
					lr.agregarVideo(v);
				}
			}
		}
	}
	
	public void eliminarVideoDeLista(Video v, String nomList) {
		for (ListaReproduccion lr: this.listas) {
			if (nomList.contentEquals(lr.getNombre())) {
				lr.eliminarVideo(v);	
			}
		}
	}

	public boolean existeLista(String nomL) {
		for (ListaReproduccion lr:this.listas) {
			if (nomL.contentEquals(lr.getNombre())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean existeListaParticular(String nomL) {
		for (ListaReproduccion lr:this.listas) {
			if (lr instanceof Particular) {
				if (lr.getNombre().contentEquals(nomL)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public List<String> listarListasDeUsuario() {
		List<String> nomListas = new ArrayList<String>();
		for (ListaReproduccion lr:this.listas) {
			nomListas.add(lr.getNombre());
		}
		return nomListas;
	}
	
	public List<String> listarListasPorDefecto() {
		List<String> listasDefectoU = new ArrayList<String>();
		for (ListaReproduccion lr:this.listas) {
			if (lr instanceof PorDefecto) {
				listasDefectoU.add(lr.getNombre());
			}
		}
		return listasDefectoU;
	}
	
	public List<String> listarListasParticulares() {
		List<String> listasPartU = new ArrayList<String>();
		for (ListaReproduccion lr:this.listas) {
			if (lr instanceof Particular) {
				listasPartU.add(lr.getNombre());
			}
		}
		return listasPartU;
	}

	public List<String> listarListasParticularesPublicas() {
		List<String> listasPartU = new ArrayList<String>();
		for (ListaReproduccion lr:this.listas) {
			if (lr instanceof Particular && lr.isPublico()) {
				listasPartU.add(lr.getNombre());
			}
		}
		return listasPartU;
	}
	
	public List<DtVideoUsuario> listarVideosdeLista(String nomList) {
		for (ListaReproduccion lr:this.listas) {
			if (nomList.contentEquals(lr.getNombre())) {
				return lr.listarVideos();
			}
		}
		return null;
	}

	public List<DtElementoWeb> listarVideosListaWeb(String nomList) {
		for (ListaReproduccion lr:this.listas) {
			if (nomList.contentEquals(lr.getNombre())) {
				return lr.listarVideosWeb();
			}
		}
		return null;
	}

	public DtListaRep obtenerListaDeUsuario(String nomList) {
		for (ListaReproduccion lr:this.listas) {
			if (nomList.contentEquals(lr.getNombre())) {
				if (lr instanceof Particular) {
					DtListaRep dtLisRep;
					if (lr.categoria == null) {
						dtLisRep = new DtListaRep(lr.getNombre(), lr.isPublico(), true, "");
					} else {
						dtLisRep = new DtListaRep(lr.getNombre(), lr.isPublico(), true, lr.categoria.getNombre());
					}
					return dtLisRep;
				} else if (lr instanceof PorDefecto) {
					DtListaRep dtLisRep = new DtListaRep(lr.getNombre(), false, false);
					return dtLisRep;
				}
			}
		}
		return null;
	}

	
	public DtVideo obtenerInfoVideo(String nomVid) {
		for (Video v:this.videos) {
			if (v.getNombre().equals(nomVid)) {
				DtVideo dtVid;
				if (v.getCategoria() == null){
					dtVid = new DtVideo(v.getNombre(), v.getDescripcion(), v.getfPublicacion(), v.getDuracion(), v.getUrl(), v.isPublico(), null);
				} else {
					dtVid = new DtVideo(v.getNombre(), v.getDescripcion(), v.getfPublicacion(), v.getDuracion(), v.getUrl(), v.isPublico(), v.getCategoria().getNombre());
				}
				return dtVid;
			}
		}
		return null;
	}
	
	public ListaReproduccion obtenerLista(String nomLis) {
		boolean encontre = false;
		int i=0;
		while (!encontre && i<this.listas.size()-1) {
			if (nomLis.contentEquals(this.listas.get(i).getNombre())){
				encontre = true;
			} else {
				i++;
			}
		}
		return this.listas.get(i);
	}
	
	public List<String> obtenerNombreVideosPublicos(){
		List<String> videosU = new ArrayList<String>();
		for (Video v:this.videos) {
			if (v.isPublico()) {
				videosU.add(v.getNombre());
			}
		}
		return videosU;
	}
	
	public List<String> obtenerNombreVideos(){
		List<String> videosU = new ArrayList<String>();
		for (Video v:this.videos) {
			videosU.add(v.getNombre());
		}
		return videosU;
	}
	
	
	public Video obtenerVideo(String nomVid) {
		boolean encontre = false;
		int i=0;
		while (!encontre && i<this.videos.size()-1) {
			if (nomVid.contentEquals(this.videos.get(i).getNombre())){
				encontre = true;
			} else {
				i++;
			}
		}
		return this.videos.get(i);
	}
	
	public boolean existeVideo(String nomVid) {
		boolean existe = false;
		int i=0;
		while (!existe && i<this.videos.size()) {
			if (nomVid.contentEquals(this.videos.get(i).getNombre())) {
				existe = true;
			} else {
				i++;
			}
		}
		return existe;
	}

	public void borrarContenidoCanal(){
		borrarTodosVideos();
		borrarTodasListas();
		this.publico = false;
	}

	private void borrarTodosVideos() {
		if (!this.videos.isEmpty()){
			for (Video v : this.videos) {
				v.setPublico(false);
				v.eliminarValoraciones();
				v.eliminarComentarios();
			}
		}
	}
	private  void borrarTodasListas() {
		for (ListaReproduccion l : this.listas) {
			if(l instanceof Particular){
				((Particular) l).setPublico(false);
			}
			l.quitarVideos();
		}
	}
}