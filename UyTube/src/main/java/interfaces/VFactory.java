package interfaces;

import logica.CVideo;

public class VFactory {

private static VFactory instancia = null;
	
	private VFactory() {}
	
	public static VFactory getInstancia() {
		if (instancia == null)
			instancia = new VFactory();
		return instancia;
	}
	
	public IVideo getIVideo() {
		return new CVideo();
	}
	
}
