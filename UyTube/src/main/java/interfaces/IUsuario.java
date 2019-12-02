package interfaces;

import datatypes.DtCanal;
import datatypes.DtCanalWeb;
import datatypes.DtUsuario;
import datatypes.DtUsuarioWeb;
import datatypes.DtVisita;
import java.util.List;
import java.util.Calendar;
import datatypes.*;
import logica.Usuario;

public interface IUsuario {
	
	public void agregarCanal();

    //si la categoria esta vacia en canal se le agrega, si ya tiene una categoria se pisa
    public void modificarCatCanal(String nick, String nomCat);

    public void agregarUsuario(String nick, String nom, String ape, Calendar fechaN, String email);
	
	public void dejarDeSeguirUsuario(String seguidor, String seguido);
	
	public Boolean esCanalPublico(String nick);
	
	public boolean existeEmail(String email);
	
	public boolean existeNickname(String nick);
	
	public void limpiarControlador();

	public List<DtUsuarioWeb> listarNickFotoWeb(List<String> seguidores);
	
	public List<String> listarSeguidores();
	
	public List<String> listarSeguidos();
	
	public List<String> listarUsuarios();
	
	public List<String> listarUsuariosEliminados();
	
	public void modificarImagen(String img);

    public void modificarContrasena(String pass);

    public void modificarInfoCanal(String nomC, String descC, boolean publico);
	
	public void modificarInfoUsuario(String nomU, String apeU, Calendar fNacU, String imagen);
	
	public DtCanal obtenerInfoCanal();
	
	public DtUsuario obtenerInfoUsuario(String nick);
	
	public DtUsuario obtenerInfoUsuarioEliminado(String nick);
	
	public void seguirUsuario(String seguidor, String seguido);

	public Integer iniciarSesion(String nick, String pass);

    public DtUsuarioWeb obtenerUsuarioWebNick(String nickname);

	public DtUsuarioWeb obtenerUsuarioWebEmail(String email);

    public List<DtUsuarioWeb> listarUsuariosWeb();

	/*si ordFecha es true se debe devolver la busqueda en ordenada del mas nuevo al mas viejo, de lo contario alfabeticamente
         en caso de que la busqueda no obtenga ningun resultado, debe devolverse una lista vacia (nunca null)	  */
	public List<DtCanalWeb> busqueda(String query, Boolean ordFecha);

	public void eliminarUsuario(String nick);

    public void borrarTodosSeguidores(Usuario usr);

	public void borrarTodosSeguidos(Usuario usr);

	public void agregarVisita(String usrSesion, String usrVid, String nomVid);

    public List<DtVisita> listarMasVisitados(String nick);

	public void crearToken(String selector, String validador, String usuario);

	public DtUsuarioWeb obtenerUsuarioConToken(String selector, String validador);
}
