package datatypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Calendar;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtFecha {
    private Integer anio;
    private Integer mes;
    private Integer dia;

    public DtFecha() {
        Calendar cal = Calendar.getInstance();
        this.anio = cal.get(Calendar.YEAR);
        this.mes = cal.get(Calendar.MONTH);
        this.dia = cal.get(Calendar.DAY_OF_MONTH);
    }

    public DtFecha(Integer anio, Integer mes, Integer dia) {
        this.anio = anio;
        this.mes = mes;
        this.dia = dia;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }
}
