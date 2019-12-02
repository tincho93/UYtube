package interfaces;

import datatypes.DtComentario;
import datatypes.DtElementoUsuario;
import datatypes.DtElementoWeb;
import datatypes.DtVideo;
import datatypes.DtValoracion;
import java.util.Calendar;
import java.util.List;


public interface IVideo {

	 public void agregarCategoria(String nomC);
	 
	 public void agregarVideo(String nick, String nomV, String desc, Calendar fPub, int dur, String url);
	 
	 public void limpiarControlador();
	 
	 public List<String> listarVideosDeUsuario(String nick);

	 public List<DtElementoWeb> listarVideosDeUsuarioWeb(String nick);
	 
	 public List<String> listarVideosPublicosDeUsuario(String nick);

	 public List<DtElementoWeb> listarVideosPublicosDeUsuarioWeb(String nick);

	 List<DtElementoUsuario> listarVideosPublicos();

	 List<DtElementoWeb> listarVideosPublicosWeb();

	 public void modificarInfoVideo(String nomV, String desc, Calendar fecha, int dur, String url, boolean publico);
	 
	 public List<DtComentario> obtenerComentariosVideo(String nomVid);
	 
	 public DtVideo obtenerInfoVideo(String nomVid);

	 public List<DtValoracion> obtenerValoracionVideo();
	 
	 public void responderComentario(int idCom, String nick, Calendar fcom, String texto);
	 
	 public void realizarComentario(String nick, Calendar fCom, String texto);
	 
	 public void valorarVideo(String nickVal, boolean val);

	 public Boolean existeVideo(String nick, String nomV);

	 Integer cantidadGusta();

	 Integer cantidadNoGusta();

	 public void setUsr(String usr);

	 public void setVid(String vid);

	 /*si ordFecha es true se debe devolver la busqueda en ordenada del mas nuevo al mas viejo, de lo contario alfabeticamente
	 	en caso de que la busqueda no obtenga ningun resultado, debe devolverse una lista vacia (nunca null)	  */
	 public List<DtElementoWeb> busqueda(String query, Boolean ordFecha);

    public Integer obtenerIdVideo(String usuario, String nomVid);

    public DtElementoWeb obtenerVideo(Integer idVid);
}
