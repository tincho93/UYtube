package logica;

import java.util.List;
import manejadores.ManejadorCategoria;
import datatypes.DtElementoUsuario;
import datatypes.DtElementoWeb;
import interfaces.ICategoria;

public class CCategoria implements ICategoria {
	private Categoria cat;
	
	//Operaciones

	@Override 
	public void altaCategoria(String nomC) throws IllegalArgumentException {
		ManejadorCategoria mc = ManejadorCategoria.getInstancia();
		try {
			Categoria c = new Categoria(nomC);
			mc.agregarCategoria(c);
			this.cat = c;
		} catch (Exception e){
			throw e;
		}
	}
	
	@Override 
	public List<DtElementoUsuario> listarElemCategoria(String nomC) {
		ManejadorCategoria mc = ManejadorCategoria.getInstancia();
		if (mc.existeCategoria(nomC)) { //si existe la categoria
			this.cat = mc.obtenerCategoria(nomC);
			List<DtElementoUsuario> elementos = this.cat.obtenerElemCategoria();
			return elementos;	
		} else {
			throw new IllegalArgumentException("No se encontro una categoria con ese nombre");
		}
	}

	@Override
	public List<DtElementoWeb> listarVideosPublicosCategoria(String nomC) {
		ManejadorCategoria mc = ManejadorCategoria.getInstancia();
		if (mc.existeCategoria(nomC)) { //si existe la categoria
			this.cat = mc.obtenerCategoria(nomC);
			List<DtElementoWeb> elementos = this.cat.obtenerVideosPublicosWeb();
			return elementos;
		} else {
			throw new IllegalArgumentException("No se encontro una categoria con ese nombre");
		}
	}

	@Override
	public List<DtElementoWeb> listarListasPublicasCategoria(String nomC) {
		ManejadorCategoria mc = ManejadorCategoria.getInstancia();
		if (mc.existeCategoria(nomC)) { //si existe la categoria
			this.cat = mc.obtenerCategoria(nomC);
			List<DtElementoWeb> elementos = this.cat.obtenerListasPublicasWeb();
			return elementos;
		} else {
			throw new IllegalArgumentException("No se encontro una categoria con ese nombre");
		}
	}
	
	@Override 
	public List<String> listarCategorias() {
		ManejadorCategoria mc = ManejadorCategoria.getInstancia();
		return mc.listarCategorias();
	}
	
	@Override 
	public boolean existeCategoria(String nombre) {
		ManejadorCategoria mc = ManejadorCategoria.getInstancia();
		return mc.existeCategoria(nombre);
	}
	
	public void limpiarControlador() { //Operacion para utilizar al final de cada caso de uso
		this.cat = null;
	}

}