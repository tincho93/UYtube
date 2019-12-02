package interfaces;

import datatypes.DtElementoWeb;
import datatypes.DtListaRep;
import datatypes.DtVideoUsuario;

import java.util.List;

public interface IListaReproduccion {

	public void agregarCategoriaALista(String nomC);
	
	public void agregarListaDefecto(String nomL);
	
	public void agregarListaParticular(String nomL, boolean publico);

    void agregarListaParticularCategoria(String nomL, boolean publico, String nomC);

    public void agregarVideoListaParticular(String nickVideo, String nomVid, String nomList);
	
	public void agregarVideoListaPorDefecto(String nickVideo, String nomVid, String nomList);
	
	public void eliminarVideoDeLista(String nickVid, String nomVid, String nomList);
	
	public boolean existeLista(String nomL);
	
	public boolean existeListaDefecto(String nomL);
	
	public boolean existeListaParticular(String nick, String nomL);
	
	public void limpiarControlador();
	
	public List<String> listarListasDeUsuario(String nick);
	
	public List<String> listarListasParticulares(String nick);

	public List<String> listarListasParticularesPublicas(String nick);
	
	public List<String> listarListasPorDefecto(String nick);
	
	public List<DtVideoUsuario> listarVideosdeLista(String nomList);

	public List<DtElementoWeb> listarVideosListaWeb(String nomList);
	
	public void modificarCategoria(String nomC);
	
	public void modificarInfoLista(String nomL, boolean publico);
	
	public DtListaRep obtenerListaDeUsuario(String nomList);

	void setuVid(String nick);

	void setuList(String nick);

	void setVideo(String nomV);

	void setLista(String nomL);

	void eliminarCategoria();

	/*si ordFecha es true se debe devolver la busqueda en ordenada del mas nuevo al mas viejo, de lo contario alfabeticamente
	 	en caso de que la busqueda no obtenga ningun resultado, debe devolverse una lista vacia (nunca null)	  */
	public List<DtElementoWeb> busqueda(String query, Boolean ordFecha);
	
}
