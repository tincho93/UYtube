package logica;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import datatypes.DtCanal;
import datatypes.DtCanalWeb;
import datatypes.DtUsuario;
import datatypes.DtUsuarioWeb;
import datatypes.DtVisita;
import jdk.nashorn.internal.parser.Token;
import manejadores.ManejadorCategoria;
import manejadores.ManejadorPorDefecto;
import manejadores.ManejadorToken;
import manejadores.ManejadorUsuario;
import interfaces.IUsuario;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class CUsuario implements IUsuario {
	private Usuario usr;
	private Canal can;
	
	//Operaciones
	@Override 
	public void agregarCanal() {
		try {
			ManejadorUsuario mU = ManejadorUsuario.getInstancia();
			ManejadorPorDefecto mPD = ManejadorPorDefecto.getInstancia();
			usr.agregarCanal();
			this.can = this.usr.getCanal();
			List<String> listasPorDefecto = mPD.getNombresPorDefecto();
			for (String nomPD:listasPorDefecto) {
				this.can.agregarListaDefecto(nomPD);
			}
			mU.modificaDatosUsuario(this.usr);

		} catch (Exception e){
			throw e;
		}	
	}

	//si la categoria esta vacia en canal se le agrega, si ya tiene una categoria se pisa.
	//si recibe null en nomCat deja al canal sin categoria
	@Override
	public void modificarCatCanal(String nick, String nomCat) {
		try{
			ManejadorUsuario mU = ManejadorUsuario.getInstancia();
			ManejadorCategoria mC = ManejadorCategoria.getInstancia();
			Usuario user = mU.obtenerUsuario(nick);
			Categoria cat = null;
			if (nomCat != null){
				cat = mC.obtenerCategoria(nomCat);
			}
			user.getCanal().setCategoria(cat);
			mU.modificaDatosUsuario(user);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override 
	public void agregarUsuario(String nick, String nom, String ape, Calendar fechaN, String email) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		try {
			Usuario usuario = new Usuario(nick, nom, ape, fechaN, email);
			mU.agregarUsuario(usuario);
			this.usr = usuario;
		} catch (Exception e){
			throw e;
		}	
	}
	
	
	@Override
	public void dejarDeSeguirUsuario(String seguidor, String seguido) {		
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		
		if (mU.existeUsuario(seguidor)) { //Si el usuario existe
			if (mU.existeUsuario(seguido)) { //Si el segundo usuario existe
				try {
					Usuario USeguidor = mU.obtenerUsuario(seguidor);
					Usuario USeguido = mU.obtenerUsuario(seguido);
					USeguidor.dejarSeguirUsuario(USeguido);
					USeguido.quitarSeguidor(USeguidor);
					mU.modificaDatosUsuario(USeguidor);
					mU.modificaDatosUsuario(USeguido);
				} catch (Exception e){
					throw e;
				}
			}else {
				throw new java.lang.RuntimeException("No existe un usuario con nick: " + seguido);
			}
		}else {
			throw new java.lang.RuntimeException("No existe un usuario con nick: " + seguidor);
		}
	}
	
	@Override 
	public boolean existeEmail(String email) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		TypedQuery<String> consulta = em.createNamedQuery("existeMail", String.class);
		consulta.setParameter("correoE", email);
		List<String> mails = consulta.getResultList();	
		if (mails.contains(email))
			return true;
		else
			return false;
	}
	
	@Override 
	public boolean existeNickname(String nick) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		if (mU.existeUsuario(nick)) {
			this.usr = mU.obtenerUsuario(nick);
			return true;
		} else
			return false;
	}
	
	@Override
	public void limpiarControlador() { //Operacion para utilizar al final de cada caso de uso
		this.can = null;
		this.usr = null;
	}
	
	@Override 
	public List<String> listarSeguidores() {
		List<String> dtSeguidores = this.usr.listarSeguidores();
		return dtSeguidores;
	}
	
	@Override 
	public List<String> listarSeguidos() {
		List<String> dtSeguidos = this.usr.listarSeguidos();
		return dtSeguidos;
	}
	
	@Override 
	public List<String> listarUsuarios() {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
	    return mU.listarUsuarios();
	}
	
	@Override 
	public List<String> listarUsuariosEliminados() {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
	    return mU.listarUsuariosEliminados();
	}


	@Override 
	public void modificarImagen(String img) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		this.usr.setImagen(img);
		mU.modificaDatosUsuario(this.usr);
	}

	@Override
	public void modificarContrasena(String pass) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		this.usr.setContrasena(pass);
		mU.modificaDatosUsuario(this.usr);
	}
	
	@Override 
	public void modificarInfoCanal(String nomC, String descC, boolean publico) {
		try {
			ManejadorUsuario mU = ManejadorUsuario.getInstancia();
			this.can.setNombre(nomC);
			this.can.setDescripcion(descC);
			this.can.setPublico(publico);
			mU.modificaDatosUsuario(this.usr);
		} catch (Exception e){
			throw e;
		}	
	}
	
	@Override 
	public void modificarInfoUsuario(String nomU, String apeU, Calendar fNacU, String imagen) {
		try {
			ManejadorUsuario mU = ManejadorUsuario.getInstancia();
			this.usr.setNombre(nomU);
			this.usr.setApellido(apeU);
			this.usr.setfNac(fNacU);
			this.usr.setImagen(imagen);
			mU.modificaDatosUsuario(this.usr);
			this.can = usr.getCanal();
		} catch (Exception e){
			throw e;
		}
	}
	
	@Override 
	public DtCanal obtenerInfoCanal() {
		DtCanal dtCan = usr.obtenerInfoCanal();
		return dtCan;
	}
	
	@Override 
	public DtUsuario obtenerInfoUsuario(String nick) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		usr = mU.obtenerUsuario(nick);
		DtUsuario res = new DtUsuario(nick, usr.getNombre(), usr.getApellido(), usr.getfNac(), usr.getImagen(), usr.getCorreoE());
		return res;
	}
	
	@Override 
	public DtUsuario obtenerInfoUsuarioEliminado(String nick) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		usr = mU.obtenerUsuario(nick);
		DtUsuario res = new DtUsuario(nick, usr.getNombre(), usr.getApellido(), usr.getfNac(), usr.getImagen(), usr.getCorreoE(), usr.getfEliminado());
		return res;
	}
	
	public Boolean esCanalPublico(String nick) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		this.usr = mU.obtenerUsuario(nick);
		return this.usr.getCanal().getPublico();
	}
	
	@Override 
	public void seguirUsuario(String seguidor, String seguido) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		if (mU.existeUsuario(seguidor)) { //Si el usuario existe
			if (mU.existeUsuario(seguido)) { //Si el segundo usuario existe
				try {
					Usuario USeguidor = mU.obtenerUsuario(seguidor);
					Usuario USeguido = mU.obtenerUsuario(seguido);
					USeguidor.seguirUsuario(USeguido);
					USeguido.agregarSeguidor(USeguidor);
					mU.modificaDatosUsuario(USeguidor);
					mU.modificaDatosUsuario(USeguido);
				} catch (Exception e){
					throw e;
				}
			} else {
				throw new java.lang.RuntimeException("No existe un usuario con nick: " + seguido);
			}
		} else {
			throw new java.lang.RuntimeException("No existe un usuario con nick: " + seguidor);
		}
	}

	@Override

	//Retorna: 0-> si no coincide los datos
	//		   1-> si coincide con nickname
	//		   2-> si coincide con email
	public Integer iniciarSesion(String nick, String pass){
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		if (existeNickname(nick) && mU.obtenerUsuario(nick).getContrasena().equals(pass) && mU.obtenerUsuario(nick).isActivo()) {
			return 1;
		} else if (existeEmail(nick) && mU.obtenerUsuarioMail(nick).getContrasena().equals(pass) && mU.obtenerUsuarioMail(nick).isActivo()){
			return 2;
		} else {
			return 0;
		}
	}

	@Override
	public DtUsuarioWeb obtenerUsuarioWebNick(String nickname){
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario u = mU.obtenerUsuario(nickname);
		List<String> lis = u.getCanal().listarListasDeUsuario();
		DtUsuarioWeb res = new DtUsuarioWeb(u.getNickname(), u.getImagen(), lis);
		return res;
	}

	@Override
	public DtUsuarioWeb obtenerUsuarioWebEmail(String email){
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario u = mU.obtenerUsuarioMail(email);
		List<String> lis = u.getCanal().listarListasDeUsuario();
		DtUsuarioWeb res = new DtUsuarioWeb(u.getNickname(), u.getImagen(), lis);
		return res;
	}

	@Override
	public List<DtUsuarioWeb> listarUsuariosWeb() {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		List<String> usuarios = mU.listarUsuarios();
		List<DtUsuarioWeb> res = new ArrayList<DtUsuarioWeb>();
		for (String nick: usuarios){
			DtUsuarioWeb usr = obtenerUsuarioWebNick(nick);
			res.add(usr);
		}
		return res;
	}

	public List<DtUsuarioWeb> listarNickFotoWeb(List<String> seguidores){
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		List<Usuario> usrs = new LinkedList<Usuario>();
		List<DtUsuarioWeb> dtUsrs = new ArrayList<DtUsuarioWeb>();
		for (String s: seguidores) {
			usrs.add(mU.obtenerUsuario(s));
		}

		for (Usuario u:usrs){
			dtUsrs.add(new DtUsuarioWeb(u.getNickname(), u.getImagen()));
		}
		return dtUsrs;
	}

	@Override
	public List<DtCanalWeb> busqueda(String query, Boolean ordFecha){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		List<DtCanalWeb> res = new ArrayList<DtCanalWeb>();
		List<Object[]> resQuery;
		if (ordFecha){
			Query consulta = em.createNamedQuery("buscarCanalFecha");
			consulta.setParameter(1, "%" + query + "%");
			resQuery = consulta.getResultList();
		} else {
			Query consulta = em.createNamedQuery("buscarCanalNombre");
			consulta.setParameter(1, "%" + query + "%");
			resQuery = consulta.getResultList();
		}
		for (Object[] o : resQuery){
			DtCanalWeb canal = new DtCanalWeb(o[0].toString(), o[1].toString(), o[2].toString());
			res.add(canal);
		}
		return res;
	}

	@Override
	public void eliminarUsuario(String nick){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario usr = mU.obtenerUsuario(nick);
		ManejadorToken mT = ManejadorToken.getInstancia();

		try {
			borrarTodosSeguidores(usr);
			borrarTodosSeguidos(usr);
			usr.borrarMasVisitados();

			//Borrar videos en listas de otros y borrar videos en MasVisitados de otros
			//Obtengo el id de los videos del usuario a eliminar
			Query qV = em.createNativeQuery("SELECT v.id FROM usuarios u JOIN canal c ON u.nickname = c.usuario_nickname JOIN elemento e ON e.canal_id=c.id JOIN video v ON e.id=v.id WHERE u.nickname LIKE :keyword");
			qV.setParameter("keyword", nick);
			List<Integer> idVideos = qV.getResultList();
			for (Integer i : idVideos) {
				//Obtengo el objeto Video a partir del idVideo
				TypedQuery<Video> consulta2 = em.createQuery("FROM Video WHERE id =:param", Video.class);
				consulta2.setParameter("param", i);
				Video vid = consulta2.getSingleResult();

				//Obtengo los id's de las listas que contienen el video y no son listas del usuario eliminado
				Query q = em.createNativeQuery("SELECT l.listareproduccion_id FROM listareproduccion_video l JOIN elemento e ON e.id=l.listareproduccion_id JOIN canal c ON e.canal_id=c.id WHERE l.videos_id = ?1 AND c.usuario_nickname NOT LIKE ?2");
				q.setParameter(1, i).setParameter(2, nick);
				List<Integer> idLista = (List<Integer>) q.getResultList();

				for (Integer j : idLista) {
					//Obtengo el objeto Lista a partir de cada id anterior
					TypedQuery<ListaReproduccion> consulta = em.createQuery("FROM ListaReproduccion WHERE id =:param", ListaReproduccion.class);
					consulta.setParameter("param", j);
					ListaReproduccion lis = consulta.getSingleResult();
					lis.quitarVideo(vid);
					mU.modificaDatosUsuario(lis.getCanal().getUsuario());
				}

				//Obtengo los id's de las Visitas que contienen dicho video y no son visitas del usuario eliminado
				//Query q2 = em.createNativeQuery ("SELECT id FROM visita WHERE video_id=?1");
				Query q2 = em.createNativeQuery ("SELECT v.id FROM visita v JOIN usuarios_visita u ON v.id=u.masvisitados_id WHERE v.video_id=?1 AND u.usuario_nickname NOT LIKE ?2");
				q2.setParameter(1, i).setParameter(2, nick);
				List<Integer> idVisitas = (List<Integer>) q2.getResultList();
				for (Integer k : idVisitas){
					//Obtengo el objeto visita a partir de los id's obtenidos
					TypedQuery<Visita> consulta = em.createQuery("FROM Visita WHERE id =:param", Visita.class);
					consulta.setParameter("param", k);
					Visita vis = consulta.getSingleResult();

					Query q3 = em.createNativeQuery("SELECT u.usuario_nickname FROM visita v JOIN usuarios_visita u ON v.id=u.masvisitados_id WHERE v.id = ?1");
					q3.setParameter(1, k);
					String nickVisita = (String) q3.getSingleResult();
					Usuario usrVisita = mU.obtenerUsuario(nickVisita);
					usrVisita.quitarVisita(vis);
				}
			}

			//Borrar valoraciones en videos de otros
			Query q = em.createNativeQuery("SELECT v.id FROM valoracion v WHERE v.usuario_nickname like :keyword");
			q.setParameter("keyword", nick);
			List<Integer> idValoraciones = q.getResultList();
			for (Integer i : idValoraciones) {
				Query q2 = em.createNativeQuery("SELECT v.video_id FROM video_valoracion v WHERE v.valoraciones_id = ?1");
				q2.setParameter(1, i);
				Integer idVideo = (Integer) q2.getSingleResult();

				TypedQuery<Elemento> consulta = em.createQuery("FROM Elemento v where v.id=:param", Elemento.class);
				consulta.setParameter("param", idVideo);
				Elemento elem = consulta.getSingleResult();
				Video vid = (Video) elem;
				vid.eliminarValoracion(i);
				mU.modificaDatosUsuario(vid.getCanal().getUsuario());
			}

			//borrar todos los comentarios en videos de otros
			//Obtengo todos los id de comentarios del usuario a eliminar
			Query qC = em.createNativeQuery("SELECT c.id FROM comentario c WHERE c.usuario_nickname LIKE :keyword ORDER BY c.id DESC");
			qC.setParameter("keyword", nick);
			List<Integer> idComentarios = qC.getResultList();

			for (Integer i : idComentarios) {
				TypedQuery<Comentario> q1 = em.createQuery("FROM Comentario c WHERE c.id = ?1", Comentario.class);
				q1.setParameter(1, i);
				Comentario com = q1.getSingleResult();

				//Verifico si es un comentario primario de un video
				Query q2 = em.createNativeQuery("SELECT v.video_id FROM video_comentario v WHERE v.comentarios_id = ?1");
				q2.setParameter(1, i);
				if (!q2.getResultList().isEmpty()) {
					Integer idVideo = (Integer) q2.getSingleResult();
					TypedQuery<Elemento> consulta = em.createQuery("FROM Elemento v WHERE v.id=:param", Elemento.class);
					consulta.setParameter("param", idVideo);
					Video vid = (Video) consulta.getSingleResult();
					vid.eliminarComentario(com);
					mU.modificaDatosUsuario(vid.getCanal().getUsuario());
				} else { //O es una respuesta de un comentario
					Query q3 = em.createNativeQuery("SELECT c.comentario_id FROM comentario_comentario c WHERE c.respuestas_id = ?1");
					q3.setParameter(1, i);
					Integer j = (Integer) q3.getSingleResult();

					TypedQuery<Comentario> q4 = em.createQuery("FROM Comentario c WHERE c.id = ?1", Comentario.class);
					q4.setParameter(1, j);
					Comentario comPadre = (Comentario) q4.getSingleResult();

					comPadre.eliminarRespuesta(com);
				}
			}
			usr.getCanal().borrarContenidoCanal();
			usr.setActivo(false);
			Calendar fElim = Calendar.getInstance();
			usr.setfEliminado(fElim);
			mU.quitarUsuario(usr);

			TypedQuery<TokenUsuario> qT = em.createQuery("FROM TokenUsuario WHERE usuario_id = ?1", TokenUsuario.class);
			qT.setParameter(1, nick);
			List<TokenUsuario> tokens = (List<TokenUsuario>) qT.getResultList();
			for(TokenUsuario tu : tokens){
				mT.eliminarToken(tu);
			}

			System.gc();
		}catch (Exception e){
			throw new IllegalArgumentException("No se pudo eliminar el usuario");
		}
	}

	@Override
	public void borrarTodosSeguidores(Usuario usr){
		if(!usr.getSeguidores().isEmpty()) {
			ManejadorUsuario mU = ManejadorUsuario.getInstancia();
			List<String> seguidores = usr.listarSeguidores();
			for (String u : seguidores) {
				dejarDeSeguirUsuario(u, usr.getNickname());
				Usuario seguidor = mU.obtenerUsuario(u);
				mU.modificaDatosUsuario(seguidor);
			}
		}
	}

	@Override
	public void borrarTodosSeguidos(Usuario usr){
		if(!usr.getSeguidos().isEmpty()) {
			ManejadorUsuario mU = ManejadorUsuario.getInstancia();
			List<String> seguidos = usr.listarSeguidos();
			for (String u : seguidos) {
				dejarDeSeguirUsuario(usr.getNickname(), u);
				Usuario seguido = mU.obtenerUsuario(u);
				mU.modificaDatosUsuario(seguido);
			}
		}
	}

	@Override
	public void agregarVisita(String usrSesion, String usrVid, String nomVid){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		this.usr = mU.obtenerUsuario(usrSesion);
		Video vid = mU.obtenerUsuario(usrVid).getCanal().obtenerVideo(nomVid);
		this.usr.agregarVisita(vid);
		mU.modificaDatosUsuario(this.usr);
	}

	@Override
	public List<DtVisita> listarMasVisitados(String nick){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		this.usr = mU.obtenerUsuario(nick);
		List<Visita> visitas = this.usr.getMasVisitados();
		List<DtVisita> res = new ArrayList<DtVisita>();
		for (Visita v: visitas){
			DtVisita dtVisita = new DtVisita(v.getVideo().getCanal().getUsuario().getNickname(), v.getVideo().getNombre(), v.getUltimaVisita(), v.getCantVisitas());
			dtVisita.setUrlVideo(v.getVideo().getUrl());
			res.add(dtVisita);
		}
		return res;
	}

	@Override
	public void crearToken(String selector, String validador, String usuario){
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		ManejadorToken mT = ManejadorToken.getInstancia();
		this.usr = mU.obtenerUsuario(usuario);
		TokenUsuario token = new TokenUsuario(selector, validador, this.usr);
		this.usr.getTokens().add(token);
		mU.modificaDatosUsuario(this.usr);
		mT.agregarToken(token);
	}

	@Override
	public DtUsuarioWeb obtenerUsuarioConToken(String selector, String validador){
		DtUsuarioWeb res = new DtUsuarioWeb("","");
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		ManejadorToken mT = ManejadorToken.getInstancia();
		TokenUsuario token = mT.obtenerToken(selector);
		if (token != null && token.getValidador().equals(validador)){
			res = obtenerUsuarioWebNick(token.getUsuario().getNickname());
		}
		return res;
	}
}
