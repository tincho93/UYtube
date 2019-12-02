package logica;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.*;
import datatypes.DtElementoUsuario;
import datatypes.DtElementoWeb;
import datatypes.DtVideo;
import datatypes.DtComentario;
import datatypes.DtValoracion;
import datatypes.tipoElemento;
import manejadores.ManejadorCategoria;
import manejadores.ManejadorComentario;
import manejadores.ManejadorUsuario;
import interfaces.IVideo;

public class CVideo implements IVideo {
	private Usuario usr;
	private Video vid;
	
	//Operaciones
	@Override 
	public void agregarCategoria(String nomC) {
		ManejadorCategoria mC = ManejadorCategoria.getInstancia();
		if (mC.existeCategoria(nomC)) {
			Categoria cat = mC.obtenerCategoria(nomC);
			this.vid.sacarCategoria();
			cat.agregarElemento(this.vid);
			mC.modificarCategoria(cat);
		}
	}
	
	@Override 
	public void agregarVideo(String nick, String nomV, String desc, Calendar fPub, int dur, String url){
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		this.usr = mU.obtenerUsuario(nick);
		this.vid = this.usr.getCanal().agregarVideo(nomV, desc, fPub, dur, url);
		mU.modificaDatosUsuario(this.usr);
	}
	
	@Override
	public void limpiarControlador() { //Operacion para utilizar al final de cada caso de uso
		this.vid = null;
		this.usr = null;
	}
	
	@Override
	public List<String> listarVideosDeUsuario(String nick){
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		this.usr = mU.obtenerUsuario(nick);
		return this.usr.getCanal().obtenerNombreVideos();
	}

	@Override
	public List<DtElementoWeb> listarVideosDeUsuarioWeb(String nick){
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		this.usr = mU.obtenerUsuario(nick);
		List<DtElementoWeb> dtElems  = new ArrayList<DtElementoWeb>();
		List<String> videos = listarVideosDeUsuario(nick);

		for (String nomVid : videos){
			DtVideo vid = obtenerInfoVideo(nomVid);
			dtElems.add(new DtElementoWeb(nick, nomVid, tipoElemento.VIDEO, vid.getUrl()));
		}

		return dtElems;
	}
	
	
	@Override
	public List<String> listarVideosPublicosDeUsuario(String nick) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		this.usr = mU.obtenerUsuario(nick);
		return this.usr.getCanal().obtenerNombreVideosPublicos();
	}

	@Override
	public List<DtElementoWeb> listarVideosPublicosDeUsuarioWeb(String nick) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario u = mU.obtenerUsuario(nick);
		List<DtElementoWeb> dtElems  = new ArrayList<DtElementoWeb>();
		List<String> videos = listarVideosPublicosDeUsuario(nick);

		for (String nomVid : videos){
			DtVideo vid = obtenerInfoVideo(nomVid);
			dtElems.add(new DtElementoWeb(nick, nomVid, tipoElemento.VIDEO, vid.getUrl()));
		}

		return dtElems;
	}

	@Override
	public List<DtElementoUsuario> listarVideosPublicos() {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		List<String> usuarios = mU.listarUsuarios();
		List<DtElementoUsuario> res  = new ArrayList<DtElementoUsuario>();
		for (String nick: usuarios){
			List<String> videos = listarVideosPublicosDeUsuario(nick);
			for (String vid : videos){
				DtElementoUsuario v = new DtElementoUsuario(nick, vid, tipoElemento.VIDEO);
				res.add(v);
			}
		}
		return res;
	}

	@Override
	public List<DtElementoWeb> listarVideosPublicosWeb() {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		List<String> usuarios = mU.listarUsuarios();
		List<DtElementoWeb> res  = new ArrayList<DtElementoWeb>();
		for (String nick: usuarios){
			List<String> videos = listarVideosPublicosDeUsuario(nick);
			for (String nomVid : videos){
				DtVideo vid = obtenerInfoVideo(nomVid);
				DtElementoWeb v = new DtElementoWeb(nick, nomVid, tipoElemento.VIDEO, vid.getUrl());
				res.add(v);
			}
		}
		return res;
	}

	@Override 
	public void modificarInfoVideo(String nomV, String desc, Calendar fecha, int dur, String url, boolean publico) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		this.vid.setNombre(nomV);
		this.vid.setDescripcion(desc);
		this.vid.setfPublicacion(fecha);
		this.vid.setDuracion(dur);
		this.vid.setUrl(url);
		this.vid.setPublico(publico);
		mU.modificaDatosUsuario(this.usr);
	}
	
	@Override 
	public List<DtComentario> obtenerComentariosVideo(String nomVid) {
		this.vid = this.usr.getCanal().obtenerVideo(nomVid);
		return this.vid.obtenerComentariosVideo();
	}
	
	@Override
	public DtVideo obtenerInfoVideo(String nomVid) {
		this.vid = this.usr.getCanal().obtenerVideo(nomVid);
		return this.usr.getCanal().obtenerInfoVideo(nomVid);
	}

	@Override 
	public List<DtValoracion> obtenerValoracionVideo() {
		return this.vid.listarValoraciones();
	}
	
	@Override 
	public void responderComentario(int idCom, String nick, Calendar fcom, String texto) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		ManejadorComentario mC = ManejadorComentario.getInstancia();
		if (mU.existeUsuario(nick)) {
			Usuario usrRes = mU.obtenerUsuario(nick);
			if (mC.existeComentario(idCom)) {
				Comentario c = mC.obtenerComentario(idCom);
				c.crearRespuesta(usrRes, fcom, texto);
			} else {
				throw new java.lang.RuntimeException("No existe el comentario ingresado");
			}
		} else {
			throw new java.lang.RuntimeException("No existe un usuario con ese nick");
		}
	}
	
	@Override 
	public void realizarComentario(String nick, Calendar fCom, String texto) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		if (mU.existeUsuario(nick)) {
			Usuario usrCom = mU.obtenerUsuario(nick);
			this.vid.crearComentario(usrCom, fCom, texto);
			mU.modificaDatosUsuario(this.usr);
			mU.modificaDatosUsuario(usrCom);
		}
	}
	
	@Override 
	public void valorarVideo(String nickVal, boolean val) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		
		if (mU.existeUsuario(nickVal)) {
			Usuario usrVal = mU.obtenerUsuario(nickVal);
			this.vid.valorarVideo(val, usrVal);
			
			mU.modificaDatosUsuario(this.usr);
		} else {
			throw new java.lang.RuntimeException("No existe un usuario con ese nick");
		}
	}

	
	@Override
	public Boolean existeVideo(String nick, String nomV) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario u = mU.obtenerUsuario(nick);
		return u.getCanal().existeVideo(nomV);
	}

	@Override
	public Integer cantidadGusta(){
		Integer i = 0;
		List<DtValoracion> listaVal = obtenerValoracionVideo();
		for (DtValoracion v: listaVal){
			if (v.getGusta()){
				i++;
			}
		}
		return i;
	}

	@Override
	public Integer cantidadNoGusta(){
		Integer i = 0;
		List<DtValoracion> listaVal = obtenerValoracionVideo();
		for (DtValoracion v: listaVal){
			if (!v.getGusta()){
				i++;
			}
		}
		return i;
	}

	@Override
	public void setUsr(String usr) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		this.usr = mU.obtenerUsuario(usr);
	}

	@Override
	public void setVid(String vid) {
		this.vid = usr.getCanal().obtenerVideo(vid);
	}

	@Override
	public List<DtElementoWeb> busqueda(String query, Boolean ordFecha){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		List<DtElementoWeb> res = new ArrayList<DtElementoWeb>();
		List<Object[]> resQuery;
		if (ordFecha){
			Query consulta = em.createNamedQuery("buscarVideoFecha");
			consulta.setParameter(1, "%" + query + "%");
			resQuery = consulta.getResultList();
		} else {
			Query consulta = em.createNamedQuery("buscarVideoNombre");
			consulta.setParameter(1, "%" + query + "%");
			resQuery = consulta.getResultList();
		}
		for (Object[] o : resQuery){
			DtElementoWeb lis = new DtElementoWeb(o[0].toString(), o[1].toString(), tipoElemento.VIDEO, o[2].toString());
			res.add(lis);
		}
		return res;
	}

	@Override
	public Integer obtenerIdVideo(String usuario, String nomVid){
		setUsr(usuario);
		setVid(nomVid);
		return vid.id;
	}

	@Override
	public DtElementoWeb obtenerVideo(Integer idVid){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		List<Object[]> resQuery;
		DtElementoWeb res  = new DtElementoWeb("", "", tipoElemento.VIDEO, "");;
		Query consulta = em.createNamedQuery("obtenerVideoPorId");
		consulta.setParameter(1, idVid);
		resQuery = consulta.getResultList();
		if (resQuery.size() != 0){
			res = new DtElementoWeb(resQuery.get(0)[0].toString(), resQuery.get(0)[1].toString(), tipoElemento.VIDEO, resQuery.get(0)[2].toString());
		}
		return res;
	}
}