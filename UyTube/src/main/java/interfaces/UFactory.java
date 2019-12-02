package interfaces;

import logica.CUsuario;

public class UFactory {
	private static UFactory instancia = null;
	
	private UFactory() {}
	
	public static UFactory getInstancia() {
		if (instancia == null)
			instancia = new UFactory();
		return instancia;
	}
	
	public IUsuario getIUsuario() {
		return new CUsuario();
	}
}
