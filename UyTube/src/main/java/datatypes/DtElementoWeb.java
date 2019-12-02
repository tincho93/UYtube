package datatypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtElementoWeb {
    private String nickname;
    private String nombreE;
    private tipoElemento tipo;
    private String url;

    //Constructores
    public DtElementoWeb() {
    }

    public DtElementoWeb(String nickname, String nombreE, tipoElemento tipo, String url) {
        this.nickname = nickname;
        this.nombreE = nombreE;
        this.tipo = tipo;
        this.url = url;
    }

    //Getters
    public String getNickname() {
        return nickname;
    }

    public String getNombreE() {
        return nombreE;
    }

    public tipoElemento getTipo() {
        return tipo;
    }

    public String getUrl() {
        return url;
    }

    //Setters
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setNombreE(String nombreE) {
        this.nombreE = nombreE;
    }

    public void setTipo(tipoElemento tipo) {
        this.tipo = tipo;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
