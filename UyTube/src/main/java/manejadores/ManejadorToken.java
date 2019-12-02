package manejadores;

import logica.Conexion;
import logica.TokenUsuario;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

public class ManejadorToken {
    private static ManejadorToken instancia = null;

    private ManejadorToken(){}

    public static ManejadorToken getInstancia() {
        if (instancia == null){
            instancia = new ManejadorToken();
        }
        return instancia;
    }

    public void agregarToken(TokenUsuario token){
        Conexion conexion=Conexion.getInstancia();
        EntityManager em =conexion.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(token);
            em.getTransaction().commit();
        } catch (Exception e){
            if (e instanceof RollbackException)
                if (em.getTransaction().isActive())
                    em.getTransaction().rollback();
            throw new IllegalArgumentException("Hubo un error inesperado");
        }
    }

    public void eliminarToken(TokenUsuario token){
        Conexion conexion=Conexion.getInstancia();
        EntityManager em =conexion.getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(token);
            em.getTransaction().commit();
        } catch (Exception e){
            if (e instanceof RollbackException)
                if (em.getTransaction().isActive())
                    em.getTransaction().rollback();
            throw new IllegalArgumentException("Hubo un error inesperado");
        }
    }

    public TokenUsuario obtenerToken(String selector){
        Conexion conexion=Conexion.getInstancia();
        EntityManager em =conexion.getEntityManager();
        TokenUsuario res = em.find(TokenUsuario.class, selector);
        return res;
    }
}
