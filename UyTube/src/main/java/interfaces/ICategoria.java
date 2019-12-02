package interfaces;

import java.util.List;
import datatypes.DtElementoUsuario;
import datatypes.DtElementoWeb;

public interface ICategoria {

	public void altaCategoria(String nomC);
	
	public List<DtElementoUsuario> listarElemCategoria(String nomC);

	public List<DtElementoWeb> listarVideosPublicosCategoria(String nomC);

	public List<DtElementoWeb> listarListasPublicasCategoria(String nomC);
	
	public List<String> listarCategorias();
	
	public boolean existeCategoria(String nombre);
	
	public void limpiarControlador();
	
}