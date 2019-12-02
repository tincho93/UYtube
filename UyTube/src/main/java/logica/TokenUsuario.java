package logica;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "tokenUsuarios")
@NamedNativeQueries({
        @NamedNativeQuery(name = "buscarToken", query = "SELECT c FROM TokenUsuario c WHERE c.selector = :selector")
})
public class TokenUsuario implements Serializable {
    @Id
    private String selector;
    private String validador;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public TokenUsuario() {
    }

    public TokenUsuario(String selector, String validador, Usuario usuario) {
        this.selector = selector;
        this.validador = validador;
        this.usuario = usuario;
    }

    public String getValidador() {
        return validador;
    }


    public Usuario getUsuario() {
        return usuario;
    }
}
