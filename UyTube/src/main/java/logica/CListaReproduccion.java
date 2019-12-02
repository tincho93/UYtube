package logica;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import datatypes.DtElementoWeb;
import datatypes.DtListaRep;
import datatypes.DtVideoUsuario;
import datatypes.tipoElemento;
import manejadores.ManejadorCategoria;
import manejadores.ManejadorPorDefecto;
import manejadores.ManejadorUsuario;
import interfaces.IListaReproduccion;

import java.util.ArrayList;
import java.util.List;

public class CListaReproduccion implements IListaReproduccion {
	private Usuario uVid;
	private Usuario uList;
	private Video video;
	private ListaReproduccion lista;

	@Override 
	public void agregarCategoriaALista(String nomC) {
		ManejadorCategoria mC = ManejadorCategoria.getInstancia();
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		if (mC.existeCategoria(nomC)) {
			Categoria cat = mC.obtenerCategoria(nomC);
			cat.agregarElemento(this.lista);
			mC.modificarCategoria(cat);
			mU.modificaDatosUsuario(this.uList);
		}
	}


	@Override 
	public void agregarListaDefecto(String nomL) {	
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		ManejadorPorDefecto mPD = ManejadorPorDefecto.getInstancia();
		List<Usuario> usuarios = mU.obtenerUsuarios();
		
	    for (Usuario u:usuarios) {
			u.getCanal().agregarListaDefecto(nomL);
	    	mU.modificaDatosUsuario(u);
		}
	    NombrePorDefecto nomPD = new NombrePorDefecto(nomL);
		mPD.agregarPorDefecto(nomPD);
	}
	
	
	@Override 
	public void agregarListaParticular(String nomL, boolean publico) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		this.uList = mU.obtenerUsuario(this.uList.getNickname());
		this.lista = this.uList.getCanal().agregarListaParticular(nomL, publico);
		mU.modificaDatosUsuario(uList);
	}

	@Override
	public void agregarListaParticularCategoria(String nomL, boolean publico, String nomC) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		ManejadorCategoria mC = ManejadorCategoria.getInstancia();
		this.uList = mU.obtenerUsuario(this.uList.getNickname());
		Categoria cat = mC.obtenerCategoria(nomC);
		this.lista = this.uList.getCanal().agregarListaParticularConCategoria(nomL, publico, cat);
		cat.agregarElemento(this.lista);
		mC.modificarCategoria(cat);
		mU.modificaDatosUsuario(uList);
	}
	
	@Override 
	public void agregarVideoListaParticular(String nickVideo, String nomVid, String nomList) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		this.uVid = mU.obtenerUsuario(nickVideo);
		this.video = this.uVid.getCanal().obtenerVideo(nomVid);
		this.uList.getCanal().agregarVideoListaParticular(this.video, nomList);
		mU.modificaDatosUsuario(this.uList);
	}
	
	@Override 
	public void agregarVideoListaPorDefecto(String nickVideo, String nomVid, String nomList) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		this.uVid = mU.obtenerUsuario(nickVideo);
		this.video = this.uVid.getCanal().obtenerVideo(nomVid);
		this.uList.getCanal().agregarVideoListaPorDefecto(this.video, nomList);
		mU.modificaDatosUsuario(this.uList);
	}
	
	@Override 
	public void eliminarVideoDeLista(String nickVid, String nomVid, String nomList) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		if (mU.existeUsuario(nickVid)) {
			this.uVid = mU.obtenerUsuario(nickVid);
			this.video = this.uVid.getCanal().obtenerVideo(nomVid);	
			this.uList.getCanal().eliminarVideoDeLista(this.video, nomList);
			mU.modificaDatosUsuario(this.uList);
		}else {
			throw new IllegalArgumentException("No existe un usuario con ese nickname");
		}
	}
	
	@Override 
	public boolean existeLista(String nomL) {
		return this.uList.getCanal().existeLista(nomL);
	}
	
	@Override 
	public boolean existeListaDefecto(String nomL) {
		ManejadorPorDefecto mPD = ManejadorPorDefecto.getInstancia();
		return mPD.existePorDefecto(nomL);
	}
	
	@Override 
	public boolean existeListaParticular(String nick, String nomL) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		this.uList = mU.obtenerUsuario(nick);
		return this.uList.getCanal().existeListaParticular(nomL);
	}
	
	@Override
	public void limpiarControlador() { //Operacion para utilizar al final de cada caso de uso
		this.lista = null;
		this.uList = null;
		this.uVid = null;
		this.video = null;
	}
	
	@Override 
	public List<String> listarListasDeUsuario(String nick) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		this.uList = mU.obtenerUsuario(nick);
		return this.uList.getCanal().listarListasDeUsuario();
	}
	
	@Override 
	public List<String> listarListasParticulares(String nick) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		this.uList = mU.obtenerUsuario(nick);
		return this.uList.getCanal().listarListasParticulares();
	}

	@Override
	public List<String> listarListasParticularesPublicas(String nick) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		this.uList = mU.obtenerUsuario(nick);
		return this.uList.getCanal().listarListasParticularesPublicas();
	}

	@Override 
	public List<String> listarListasPorDefecto(String nick) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		this.uList = mU.obtenerUsuario(nick);
		return this.uList.getCanal().listarListasPorDefecto();
	}
	
	@Override 
	public List<DtVideoUsuario> listarVideosdeLista(String nomList) {
		return this.uList.getCanal().listarVideosdeLista(nomList);
	}

	@Override
	public List<DtElementoWeb> listarVideosListaWeb(String nomList) {
		return this.uList.getCanal().listarVideosListaWeb(nomList);
	}
	
	@Override 
	public void modificarCategoria(String nomC) {
		ManejadorCategoria mC = ManejadorCategoria.getInstancia();
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		if (mC.existeCategoria(nomC)) {
			//Debo obtener la categoria de la lista, para esa categoria sacar la lista
			Particular part = (Particular) this.lista;

			Categoria catPart = part.getCategoria();
			if (!(catPart == null)) {
				catPart.quitarElemento(part);
				mC.modificarCategoria(catPart);
			}
			Categoria cat = mC.obtenerCategoria(nomC);
			cat.agregarElemento(this.lista);
			mC.modificarCategoria(cat);
			mU.modificaDatosUsuario(uList);
		}
	}


	@Override 
	public void modificarInfoLista(String nomL, boolean publico) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		this.lista = this.uList.getCanal().obtenerLista(nomL);
		Particular part = (Particular) this.lista;
		part.setPublico(publico);
		mU.modificaDatosUsuario(this.uList);
	}

	
	@Override 
	public DtListaRep obtenerListaDeUsuario(String nomList) {
		return this.uList.getCanal().obtenerListaDeUsuario(nomList);
	}

	@Override
	public void setuVid(String nick) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		this.uVid =mU.obtenerUsuario(nick);
	}

	@Override
	public void setuList(String nick) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		this.uList = mU.obtenerUsuario(nick);
	}

	@Override
	public void setVideo(String nomV) {
		this.video = this.uVid.getCanal().obtenerVideo(nomV);
	}

	@Override
	public void setLista(String nomL) {
		this.lista = this.uList.getCanal().obtenerLista(nomL);
	}
	
	@Override 
	public void eliminarCategoria() {
		ManejadorCategoria mC = ManejadorCategoria.getInstancia();
		Particular part = (Particular) this.lista;
		Categoria cat = part.getCategoria();
		if (cat != null ){
			cat.quitarElemento(part);
			mC.modificarCategoria(cat);
		}
	}

	@Override
	public List<DtElementoWeb> busqueda(String query, Boolean ordFecha){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		List<DtElementoWeb> res = new ArrayList<DtElementoWeb>();
		List<Object[]> resQuery;
		if (ordFecha){
			Query consulta = em.createNamedQuery("buscarListasFecha");
			consulta.setParameter(1, "%" + query + "%");
			resQuery = consulta.getResultList();
		} else {
			Query consulta = em.createNamedQuery("buscarListasNombre");
			consulta.setParameter(1, "%" + query + "%");
			resQuery = consulta.getResultList();
		}
		for (Object[] o : resQuery){
			DtElementoWeb lis = new DtElementoWeb(o[0].toString(), o[1].toString(), tipoElemento.LISTA, "");
			res.add(lis);
		}
		return res;
	}
	
}