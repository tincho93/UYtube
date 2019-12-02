package datatypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Calendar;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtUsuario {
	private String nickname;
	private String nombre;
	private String apellido;
	private Calendar fNac;
	private String imagen;
	private String correoE;
	private Calendar fElim;
	
	//Constructor
	public DtUsuario() {
		super();
	}

	public DtUsuario(String nickname, String nombre, String apellido, Calendar fNac, String imagen, String correoE) {
		super();
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fNac = fNac;
		this.imagen = imagen;
		this.correoE = correoE;
	}
	
	public DtUsuario(String nickname, String nombre, String apellido, Calendar fNac, String imagen, String correoE, Calendar fElim) {
		super();
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fNac = fNac;
		this.imagen = imagen;
		this.correoE = correoE;
		this.fElim = fElim;
	}

	//Getters
	public String getNickname() {
		return nickname;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public Calendar getfNac() {
		return fNac;
	}

	public String getImagen() {
		return imagen;
	}

	public String getCorreoE() {
		return correoE;
	}
	
	public Calendar getfElim() {
		return fElim;
	}

	//Setters
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setfNac(Calendar fNac) {
		this.fNac = fNac;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public void setCorreoE(String correoE) {
		this.correoE = correoE;
	}
	
	public void setfElim(Calendar fElim) {
		this.fElim = fElim;
	}

	@Override
	public String toString() {
		return "DtUsuario [nickname=" + nickname + ", nombre=" + nombre + ", apellido=" + apellido + ", fNac=" + fNac
				+ ", imagen=" + imagen + ", correoE=" + correoE + "]";
	}
}
