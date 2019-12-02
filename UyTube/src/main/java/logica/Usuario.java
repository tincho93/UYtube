package logica;

import javax.persistence.*;
import datatypes.DtCanal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Comparator;

@NamedQueries( {
	@NamedQuery(name = "existeMail", query = "select u.correoE from Usuario u where u.correoE = :correoE"),
	@NamedQuery(name = "usuarioMail", query = "select u.nickname from Usuario u where u.correoE = :correoE")
} )
@Entity
@Table(name="USUARIOS")
public class Usuario {
	
	@Id //Siempre tenenos que tener un atributo Id
	@Column(name="NICKNAME") 
	private String nickname;

	@Column(name="Contrasena")
	private String contrasena;

	@Column(name="NOMBRE")
	private String nombre;

	@Column(name="APELLIDO")
	private String apellido;
	
	@Column(name="FECHA_DE_NACIMIENTO")
	private Calendar fNac;
	
	@Column(name="IMAGEN")
	private String imagen;
	
	@Column(name="CORREO_ELECTRONICO")
	private String correoE;

	@Column(name="ACTIVO")
	private boolean activo;
	
	@OneToOne(mappedBy="usuario", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
	private Canal canal;
	
	@ManyToMany(mappedBy="seguidos")
	//@JoinTable(name="USUARIOS_SEGUIDOS")
	private List<Usuario> seguidores = new ArrayList<Usuario>();
	
	@ManyToMany
	private List<Usuario> seguidos = new ArrayList<Usuario>();

	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Visita> masVisitados = new ArrayList<Visita>();

	@Column(name="FECHA_DE_ELIMINACION")
	private Calendar fEliminado;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	private List<TokenUsuario> tokens = new ArrayList<TokenUsuario>();

	//Constructores
	public Usuario() {
		super();
	}

	public Usuario(String nickname, String nombre, String apellido, Calendar fNac, String correoE) {
		super();
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fNac = fNac;
		this.correoE = correoE;
		this.activo = true;
		this.fEliminado = null;
	}

	//Getters y Setters
	public String getNickname() {
		return nickname;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Calendar getfNac() {
		return fNac;
	}

	public void setfNac(Calendar fNac) {
		this.fNac = fNac;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getCorreoE() {
		return correoE;
	}
	
	public List<Usuario> getSeguidos() {
		return seguidos;
	}

	public List<Usuario> getSeguidores() {
		return seguidores;
	}

	public Canal getCanal() {
		return this.canal;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public boolean isActivo() { return activo; }

	public void setActivo(boolean activo) {	this.activo = activo; }

	public Calendar getfEliminado() { return fEliminado; }

	public void setfEliminado(Calendar fEliminado) { this.fEliminado = fEliminado; }

	public List<TokenUsuario> getTokens() {
		return tokens;
	}

	//Operaciones
	public void agregarCanal() {
		Canal c = new Canal(this.getNickname(), null, false);
		c.setUsuario(this);
		this.canal = c;
	}

	
	public void agregarSeguidor(Usuario u) {
		if (!this.seguidores.contains(u)) {
			this.seguidores.add(u);			
		}
	}

	
	public void dejarSeguirUsuario(Usuario u2) {
		this.seguidos.remove(u2);
	}

	
	public DtCanal obtenerInfoCanal() {
		DtCanal dtCan;
		if (canal.getCategoria() != null){
			dtCan = new DtCanal(canal.getNombre(), canal.getDescripcion(), canal.getPublico(), canal.getCategoria().getNombre());
		} else {
			dtCan = new DtCanal(canal.getNombre(), canal.getDescripcion(), canal.getPublico(), null);
		}
		return dtCan;
	}

	public List<String> listarSeguidores() {
		List<String> dtSeguidores = new ArrayList<String>();
		Collection<Usuario> seguidores = this.getSeguidores();
		
		for (Usuario u:seguidores) {
			dtSeguidores.add(u.getNickname());
		}
		
		return dtSeguidores;
	}
	
	public List<String> listarSeguidos() {
		List<String> dtSeguidos = new ArrayList<String>();
		Collection<Usuario> seguidos = this.getSeguidos();
		
		for (Usuario u:seguidos) {
			dtSeguidos.add(u.getNickname());
		}
		
		return dtSeguidos;
	}

	
	public void quitarSeguidor(Usuario u1) {
		this.seguidores.remove(u1);
	}
	
	public void seguirUsuario(Usuario u2) {
		if (!this.seguidos.contains(u2)) {
			this.seguidos.add(u2);			
		}
	}

	public void agregarVisita(Video video){
		Visita nuevaV = null;
		for (Visita v: masVisitados){
			if (v.getVideo().equals(video)){
				nuevaV = v;
				break;
			}
		}
		Calendar fecha = Calendar.getInstance();
		if (nuevaV == null){
			nuevaV = new Visita(video, fecha, 1);
			masVisitados.add(nuevaV);
		} else {
			nuevaV.setUltimaVisita(fecha);
			nuevaV.setCantVisitas(nuevaV.getCantVisitas()+1);
		}
		masVisitados.sort(Comparator.comparing(Visita::getCantVisitas).reversed());

	}

	public List<Visita> getMasVisitados() {
		return masVisitados;
	}

	public void borrarMasVisitados(){
		this.masVisitados.clear();
	}

	public void quitarVisita(Visita vis){
		this.masVisitados.remove(vis);
	}
}