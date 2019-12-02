package manejadores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import logica.Conexion;
import logica.NombrePorDefecto;

public class ManejadorPorDefecto {
	private static ManejadorPorDefecto instancia = null;
	
	private ManejadorPorDefecto(){}
	
	public static ManejadorPorDefecto getInstancia() {
		if (instancia == null) {
			instancia = new ManejadorPorDefecto();
		}
		return instancia;
	}
	
	public boolean existePorDefecto(String nombre) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		if (em.find(NombrePorDefecto.class, nombre) == null){
			return false;
		} else {
			return true;
		}
	}
	
	public void agregarPorDefecto(NombrePorDefecto nombre) {
		Conexion conexion=Conexion.getInstancia();
		EntityManager em =conexion.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(nombre);
			em.getTransaction().commit();
		} catch (Exception e){
			if (e instanceof RollbackException)
				if (em.getTransaction().isActive())
					em.getTransaction().rollback();
			throw new IllegalArgumentException("Hubo un error inesperado");
		}
	}
	
	public List<String> getNombresPorDefecto(){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		TypedQuery<String> consulta = em.createQuery("SELECT npd.nombre FROM NombrePorDefecto npd", String.class);
	    List<String> listasPorDefecto = consulta.getResultList();
	    return listasPorDefecto;
	}
}
