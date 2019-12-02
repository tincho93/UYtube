package interfaces;

import logica.CCategoria;

public class CFactory {
	private static CFactory instancia = null;
	
	private CFactory() {}
	
	public static CFactory getInstancia() {
		if (instancia == null)
			instancia = new CFactory();
		return instancia;
	}
	
	public ICategoria getICategoria() {
		return new CCategoria();
	}

}
