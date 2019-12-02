package manejadores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import logica.Conexion;
import logica.Categoria;

	public class ManejadorCategoria {

	private static ManejadorCategoria instancia = null;
		
		private ManejadorCategoria(){}
		
		public static ManejadorCategoria getInstancia() {
			if (instancia == null)
				instancia = new ManejadorCategoria();
			return instancia;
		}
	
	public void agregarCategoria(Categoria categoria) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(categoria);
			em.getTransaction().commit();
		} catch (Exception e){
			if (e instanceof RollbackException)
				if (em.getTransaction().isActive())
					em.getTransaction().rollback();
			throw new IllegalArgumentException("Hubo un error inesperado");
		}
	}
	
	public void modificarCategoria(Categoria categoria) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(categoria);
			em.getTransaction().commit();
		} catch (Exception e){
			if (e instanceof RollbackException)
				if (em.getTransaction().isActive())
					em.getTransaction().rollback();
			throw new IllegalArgumentException("Hubo un error inesperado");
		}
	}
	
	public Categoria obtenerCategoria(String nombre){
		Conexion conexion=Conexion.getInstancia();
		EntityManager em =conexion.getEntityManager();
		Categoria categoria = em.find(Categoria.class, nombre);
		return categoria;
	}
	
	public List<String> listarCategorias() {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		TypedQuery<String> consulta = em.createQuery("SELECT c.nombre FROM Categoria c", String.class);
	    List<String> categorias = consulta.getResultList();
	    //em.close();
	    return categorias;
	}
	
	public boolean existeCategoria(String nomC) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		if (em.find(Categoria.class, nomC) != null) {
			return true;
		} else {
			return false;
		}
	}

	public void cerrarConexion(){
		Conexion conexion=Conexion.getInstancia();
		EntityManager em =conexion.getEntityManager();
		conexion.cerrar();
	}
	
}

