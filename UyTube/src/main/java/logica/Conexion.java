package logica;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexion {
	private static Conexion instancia = null;
	private EntityManagerFactory emf;
	private EntityManager em;
	
	private Conexion(){
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
	}
	
	public EntityManager getEntityManager() {
		return this.em;
	}
	
	public static Conexion getInstancia() {
		if (instancia == null) {
			instancia = new Conexion();
		}
		return instancia;
	}

	public void cerrar(){
		em.close();
		instancia = null;
	}
}