package datatypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtCanalWeb {
    private String nickname;
    private String nomCanal;
    private String imgUsr;

    public DtCanalWeb() {
    }

    public DtCanalWeb(String nickname, String nomCanal, String imgUsr) {
        this.nickname = nickname;
        this.nomCanal = nomCanal;
        this.imgUsr = imgUsr;
    }

    //Getters
    public String getNickname() {
        return nickname;
    }

    public String getNomCanal() {
        return nomCanal;
    }

    public String getImgUsr() {
        return imgUsr;
    }

    //Setters
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setNomCanal(String nomCanal) {
        this.nomCanal = nomCanal;
    }

    public void setImgUsr(String imgUsr) {
        this.imgUsr = imgUsr;
    }
}
