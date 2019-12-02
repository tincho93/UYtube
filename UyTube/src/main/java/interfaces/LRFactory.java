package interfaces;

import logica.CListaReproduccion;

public class LRFactory {

private static LRFactory instancia = null;
	
	private LRFactory() {}
	
	public static LRFactory getInstancia() {
		if (instancia == null)
			instancia = new LRFactory();
		return instancia;
	}
	
	public IListaReproduccion getIListaReproduccion() {
		return new CListaReproduccion();
	}
	
}
