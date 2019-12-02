package logica;

import datatypes.DtComentario;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

@Entity
public class Comentario {
	
	@Id
	@SequenceGenerator(name = "comentarioGenerator", sequenceName = "COMENTARIO_SEQUENCE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comentarioGenerator")
	private Integer id;
	private Calendar fecha;
	private String texto;
	
	@ManyToOne
	private Usuario usuario;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Comentario> respuestas = new ArrayList<Comentario>();
	
	//Constructores
	public Comentario() {
		super();
	}
	public Comentario(Calendar fecha, String texto, Usuario usuario) {
		super();
		this.fecha = fecha;
		this.texto = texto;
		this.usuario = usuario;
	}
	
	//Getters & Setters
	public Integer getId() {
		return id;
	}
	public Calendar getFecha() {
		return fecha;
	}

	public String getTexto() {
		return texto;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	
	public List<Comentario> getRespuestas() {
		return respuestas;
	}
	
	//Operaciones
	public void crearRespuesta(Usuario uC, Calendar fCom, String texto) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();
		Comentario c = new Comentario(fCom, texto, uC);
		respuestas.add(c);
		em.persist(c);
		em.persist(this);
		em.getTransaction().commit();
	}
	
	public List<DtComentario> listarRespuestas(){
		List<DtComentario> retorno = new ArrayList<DtComentario>();
		if (!this.getRespuestas().isEmpty()) {
			for (Comentario c : respuestas) {
				DtComentario res = new DtComentario(c.getId(), c.getUsuario().getNickname(), c.getFecha(), c.getTexto(), c.listarRespuestas());
				retorno.add(res);
			}
		}
		return retorno;
	}

	public void eliminarRespuestas(){
		List<Comentario> respuestas = this.getRespuestas();
		if (!respuestas.isEmpty()){ //Si tiene respuestas
			Iterator iterator = respuestas.iterator();
			while (iterator.hasNext() && respuestas.size()>0) {
				Comentario r = (Comentario) iterator.next();
				r.eliminarRespuestas();
			}
		} else {
			this.respuestas.clear();
		}
	}

	public void eliminarRespuesta(Comentario c){
		c.eliminarRespuestas();
		this.respuestas.remove(c);
	}
}