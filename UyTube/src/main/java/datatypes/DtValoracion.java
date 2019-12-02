package datatypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtValoracion {
	private String nickname;
	private Boolean gusta;
	
	public DtValoracion() {
		super();
	}
	
	public DtValoracion(String nickname, Boolean gusta) {
		super();
		this.nickname = nickname;
		this.gusta = gusta;
	}

	//Getters
	public String getNickname() {
		return nickname;
	}

	public Boolean getGusta() {
		return gusta;
	}

	//Setters
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setGusta(Boolean gusta) {
		this.gusta = gusta;
	}
}
