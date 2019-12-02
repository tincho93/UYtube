package datatypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtVideoUsuario {
	private String nickname;
	private String nombreE;
	
	
	//Constructores
	public DtVideoUsuario() {
		super();
	}
	
	public DtVideoUsuario(String nickname, String nombreE) {
		super();
		this.nickname = nickname;
		this.nombreE = nombreE;
	}
	
	//Getters
	public String getNickname() {
		return nickname;
	}
	
	public String getNombreE() {
		return nombreE;
	}

	//Setters
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setNombreE(String nombreE) {
		this.nombreE = nombreE;
	}
}
