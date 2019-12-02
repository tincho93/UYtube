package datatypes;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtUsuarioWeb {
    private String nickname;
    private String foto;
    private List<String> listaRep = new ArrayList<String>();

    public DtUsuarioWeb() {
    }

    public DtUsuarioWeb(String nickname, String foto) {
        this.nickname = nickname;
        this.foto = foto;
    }

    public DtUsuarioWeb(String nickname, String foto, List<String> listaRep) {
        this.nickname = nickname;
        this.foto = foto;
        this.listaRep = listaRep;
    }

    //Getters
    public String getNickname() {
        return nickname;
    }

    public String getFoto() {
        return foto;
    }

    public List<String> getListaRep() {
        return listaRep;
    }

    //Setters
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setListaRep(List<String> listaRep) {
        this.listaRep = listaRep;
    }
}


