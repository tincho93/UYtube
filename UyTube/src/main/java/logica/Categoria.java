package logica;


import datatypes.DtElementoUsuario;
import datatypes.DtElementoWeb;
import datatypes.tipoElemento;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Categoria {
		
		//ATRIBUTOS
		@Id
		private String nombre;

		@OneToMany(mappedBy="categoria", cascade=CascadeType.MERGE)
		private List<Elemento> elementos = new ArrayList<Elemento>();
		
		
		//CONSTRUCTORES
		public Categoria() {
		}
		
		public Categoria(String nombre) {
			super();
			this.nombre = nombre;
		}

		//GETTERS & SETTERS
		public String getNombre() {
			return nombre;
		}

		public List<DtElementoUsuario> obtenerElemCategoria(){
			List<DtElementoUsuario> res = new LinkedList<DtElementoUsuario>();
			DtElementoUsuario elem;
			for (Elemento e: elementos) {
				elem = e.obtenerElemCategoria();
				res.add(elem);
			}
			return res;
		}

		public List<DtElementoWeb> obtenerVideosPublicosWeb(){
			List<DtElementoWeb> res = new LinkedList<DtElementoWeb>();
			DtElementoWeb infoVid;
			for (Elemento e: elementos) {
				if (e instanceof Video && ((Video) e).isPublico()) {
					Video video = (Video) e;
					infoVid = new DtElementoWeb(video.getCanal().getUsuario().getNickname(), video.getNombre(), tipoElemento.VIDEO, video.getUrl());
					res.add(infoVid);
				}
			}
			return res;
		}

	public List<DtElementoWeb> obtenerListasPublicasWeb(){
		List<DtElementoWeb> res = new LinkedList<DtElementoWeb>();
		DtElementoWeb infoVid;
		for (Elemento e: elementos) {
			if (e instanceof Particular && ((Particular) e).isPublico()) {
				Particular part = (Particular) e;
				infoVid = new DtElementoWeb(part.getCanal().getUsuario().getNickname(), part.getNombre(), tipoElemento.LISTA, null);
				res.add(infoVid);
			}
		}
		return res;
	}
		
		public void agregarElemento(Elemento e) {
			e.setCategoria(this);
			elementos.add(e);
		}
		
		public void quitarElemento(Elemento e) {
			e.setCategoria(null);
			elementos.remove(e);
		}
}