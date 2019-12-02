package logica;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class Visita {
   @Id
   @GeneratedValue
    private Integer id;

    @ManyToOne
    private Video video;
    private Calendar ultimaVisita;
    private Integer cantVisitas;

    public Visita() {}

    public Visita(Video video, Calendar ultimaVisita, Integer cantVisitas) {
        this.video = video;
        this.ultimaVisita = ultimaVisita;
        this.cantVisitas = cantVisitas;
    }



    public Video getVideo() {
        return video;
    }


    public Calendar getUltimaVisita() {
        return ultimaVisita;
    }

    public void setUltimaVisita(Calendar ultimaVisita) {
        this.ultimaVisita = ultimaVisita;
    }

    public Integer getCantVisitas() {
        return cantVisitas;
    }

    public void setCantVisitas(Integer cantVisitas) {
        this.cantVisitas = cantVisitas;
    }
}
