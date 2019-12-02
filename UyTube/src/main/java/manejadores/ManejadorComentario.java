package manejadores;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

import logica.Comentario;
import logica.Conexion;

public class ManejadorComentario {

	private static ManejadorComentario instancia = null;
	
	private ManejadorComentario(){}
	
	public static ManejadorComentario getInstancia() {
		if (instancia == null)
			instancia = new ManejadorComentario();
		return instancia;
	}

	public Comentario obtenerComentario(Integer id) {
		Conexion conexion=Conexion.getInstancia();
		EntityManager em =conexion.getEntityManager();
		Comentario comentario = em.find(Comentario.class, id);
		//em.close();
		return comentario;
	}
	
	public boolean existeComentario(Integer id) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		if (em.find(Comentario.class, id) == null){
			return false;
		}else {
			return true;
		}
	}
}
