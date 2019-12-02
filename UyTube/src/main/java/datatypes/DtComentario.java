package datatypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtComentario {
	private Integer id;
	private String nickname;
	private Calendar fecha;
	private String texto;
	private List<DtComentario> respuestas = new ArrayList<DtComentario>();
	
	public DtComentario() {
	
	}

	public DtComentario(Integer id, String nickname, Calendar fecha, String texto, List<DtComentario> respuestas) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.fecha = fecha;
		this.texto = texto;
		this.respuestas = respuestas;
	}
	
	public DtComentario(Integer id, String nickname, Calendar fecha, String texto) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.fecha = fecha;
		this.texto = texto;
	}

	//Getters
	public Integer getId() {
		return id;
	}

	public String getNickname() {
		return nickname;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public String getTexto() {
		return texto;
	}

	public List<DtComentario> getRespuestas() {
		return respuestas;
	}

	//Setters
	public void setId(Integer id) {
		this.id = id;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public void setRespuestas(List<DtComentario> respuestas) {
		this.respuestas = respuestas;
	}

	@Override
	public String toString() {
		return nickname + " : " + fecha.get(Calendar.DAY_OF_MONTH) + "/" + (fecha.get(Calendar.MONTH)+1) + "/" + fecha.get(Calendar.YEAR) + " > " + texto ;
	}
	
	
}

