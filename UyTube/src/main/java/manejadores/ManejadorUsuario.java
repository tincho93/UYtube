package manejadores;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import logica.Conexion;
import logica.Usuario;

public class ManejadorUsuario {
	private static ManejadorUsuario instancia = null;

	private ManejadorUsuario(){}
	
	public static ManejadorUsuario getInstancia() {
		if (instancia == null)
			instancia = new ManejadorUsuario();
		return instancia;
	}
	
	public boolean existeUsuario(String nick) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		if (em.find(Usuario.class, nick) == null){//Si no existe el nickname en la base
			return false;
		} else {
			return true;
		}
	}
	
	public void agregarUsuario(Usuario usuario) {
		Conexion conexion=Conexion.getInstancia();
		EntityManager em =conexion.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
		} catch (Exception e){
			if (e instanceof RollbackException)
				if (em.getTransaction().isActive())
					em.getTransaction().rollback();
			throw new IllegalArgumentException("Hubo un error inesperado");
		}
	}
	
	public Usuario obtenerUsuario(String nickname){
		Conexion conexion=Conexion.getInstancia();
		EntityManager em =conexion.getEntityManager();
		Usuario usuario = em.find(Usuario.class, nickname);
		return usuario;
	}
	
	public List<Usuario> obtenerUsuarios(){
		Conexion conexion=Conexion.getInstancia();
		EntityManager em =conexion.getEntityManager();
		TypedQuery<Usuario> consulta = em.createQuery("FROM Usuario WHERE activo = true", Usuario.class);
		List<Usuario> usuarios = consulta.getResultList();
		return usuarios;
	}
	
	public List<String> listarUsuarios(){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		TypedQuery<String> consulta = em.createQuery("SELECT u.nickname FROM Usuario u WHERE u.activo = true", String.class);
	    List<String> usuarios = consulta.getResultList();
	    return usuarios;
	}
	
	public List<String> listarUsuariosEliminados(){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		TypedQuery<String> consulta = em.createQuery("SELECT u.nickname FROM Usuario u WHERE u.activo = false", String.class);
	    List<String> usuarios = consulta.getResultList();
	    return usuarios;
	}

	public Usuario obtenerUsuarioMail(String email){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		TypedQuery<String> consulta = em.createNamedQuery("usuarioMail", String.class);
		consulta.setParameter("correoE", email);
		String nickname = consulta.getSingleResult();
		Usuario usuario = obtenerUsuario(nickname);
		return usuario;
	}
	
	public void modificaDatosUsuario(Usuario usuario) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
		}catch (Exception e){
			if (e instanceof RollbackException)
				if (em.getTransaction().isActive())
					em.getTransaction().rollback();
			throw e;
		}
	}


	public void quitarUsuario(Usuario usuario){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
		}catch (Exception e){
			if (e instanceof RollbackException)
				if (em.getTransaction().isActive())
					em.getTransaction().rollback();
			throw new IllegalArgumentException("Hubo un error inesperado");
		}
    }
	
	public void cerrarConexion(){
		Conexion conexion=Conexion.getInstancia();
		conexion.cerrar();
	}
}