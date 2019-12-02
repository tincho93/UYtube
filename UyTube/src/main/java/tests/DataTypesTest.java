package tests;

import datatypes.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

public class DataTypesTest {

    @Test
    public void DtCanal() {
        DtCanal can1 = new DtCanal();
        can1.setNombre("mateo");
        can1.setDescripcion("asdasd");
        can1.setCategoria("Deportes");
        can1.setPublico(true);
        DtCanal can2 = new DtCanal(can1.getNombre(), can1.getDescripcion(), can1.getPublico(), can1.getCategoria());
        assertEquals(can1.getNombre(), can2.getNombre());
    }

    @Test
    public void DtCanalWeb() {
        DtCanalWeb can1 = new DtCanalWeb();
        can1.setNickname("mateo");
        can1.setNomCanal("asdasdasdad");
        can1.setImgUsr("asdasdad");
        DtCanalWeb can2 = new DtCanalWeb(can1.getNickname(), can1.getNomCanal(), can1.getImgUsr());
        assertEquals(can1.getNickname(), can2.getNickname());
    }

    @Test
    public void DtComentario() {
        Calendar fecha = Calendar.getInstance();
        List<DtComentario> respuestas = new ArrayList<DtComentario>();
        DtComentario com1 = new DtComentario();
        com1.setId(1);
        com1.setNickname("mateo");
        com1.setFecha(fecha);
        com1.setTexto("texto");
        com1.setRespuestas(respuestas);
        DtComentario com2 = new DtComentario(com1.getId(), com1.getNickname(), com1.getFecha(), com1.getTexto(), com1.getRespuestas());
        assertEquals(com1.getNickname(), com2.getNickname());
    }

    @Test
    public void DtElementoUsuario() {
        DtElementoUsuario usr1 = new DtElementoUsuario();
        usr1.setNickname("mateo");
        usr1.setNombreE("videito");
        usr1.setTipo(tipoElemento.VIDEO);
        DtElementoUsuario usr2 = new DtElementoUsuario(usr1.getNickname(), usr1.getNombreE(), usr1.getTipo());
        assertEquals(usr1.getNombreE(), usr2.getNombreE());
    }

    @Test
    public void DtElementoWeb() {
        DtElementoWeb elem1 = new DtElementoWeb();
        elem1.setNickname("mateo");
        elem1.setNombreE("vid");
        elem1.setTipo(tipoElemento.VIDEO);
        elem1.setUrl("url");
        DtElementoWeb elem2 = new DtElementoWeb(elem1.getNickname(), elem1.getNombreE(), elem1.getTipo(), elem1.getUrl());
        assertEquals(elem1.getNickname(), elem2.getNickname());
    }

    @Test
    public void DtFecha() {
        DtFecha fecha1 = new DtFecha();
        fecha1.setDia(11);
        fecha1.setMes(10);
        fecha1.setAnio(1998);
        DtFecha fecha2 = new DtFecha(fecha1.getAnio(), fecha1.getMes(), fecha1.getDia());
        assertEquals(fecha1.getDia(), fecha2.getDia());
    }

    @Test
    public void DtListaRep() {
        DtListaRep list1 = new DtListaRep();
        list1.setNombre("lista");
        list1.setPublico(true);
        list1.setEsParticular(true);
        list1.setCategoria("Deportes");
        DtListaRep list2 = new DtListaRep(list1.getNombre(), list1.getPublico(), list1.getEsParticular(), list1.getCategoria());
        assertEquals(list1.getNombre(), list2.getNombre());
    }

    @Test
    public void DtUsuario() {
        Calendar fecha = Calendar.getInstance();
        DtUsuario usr1 = new DtUsuario();
        usr1.setNickname("Mateo");
        usr1.setNombre("Mateo");
        usr1.setApellido("Sayas");
        usr1.setfNac(fecha);
        usr1.setImagen("direccion");
        usr1.setCorreoE("mateo@gmail.com");
        usr1.setfElim(fecha);
        DtUsuario usr2 = new DtUsuario(usr1.getNickname(), usr1.getNombre(), usr1.getApellido(), usr1.getfNac(), usr1.getImagen(), usr1.getCorreoE(), usr1.getfElim());
        assertEquals(usr1.getNickname(), usr2.getNickname());
    }

    @Test
    public void DtUsuarioWeb() {
        List<String> lista = new ArrayList<String>();
        DtUsuarioWeb usrWeb1 = new DtUsuarioWeb();
        usrWeb1.setNickname("Mateo");
        usrWeb1.setFoto("foto");
        usrWeb1.setListaRep(lista);
        DtUsuarioWeb usrWeb2 = new DtUsuarioWeb(usrWeb1.getNickname(), usrWeb1.getFoto(), usrWeb1.getListaRep());
        assertEquals(usrWeb1.getNickname(), usrWeb2.getNickname());
    }

    @Test
    public void DtValoracion() {
        DtValoracion val1 = new DtValoracion();
        val1.setNickname("Mateo");
        val1.setGusta(true);
        DtValoracion val2 = new DtValoracion(val1.getNickname(), val1.getGusta());
        assertEquals(val1.getNickname(), val2.getNickname());
    }

    @Test
    public void DtVideo() {
        Calendar fecha = Calendar.getInstance();
        DtVideo vid1 = new DtVideo();
        vid1.setNombre("vid1");
        vid1.setDescripcion("asdas");
        vid1.setfPublicacion(fecha);
        vid1.setDuracion(1);
        vid1.setUrl("url");
        vid1.setPublico(true);
        vid1.setCategoria("Deportes");
        DtVideo vid2 = new DtVideo(vid1.getNombre(), vid1.getDescripcion(), vid1.getfPublicacion(), vid1.getDuracion(), vid1.getUrl(), vid1.getPublico(), vid1.getCategoria());
        assertEquals(vid1.getNombre(), vid2.getNombre());
    }

    @Test
    public void DtVideoUsuario() {
        DtVideoUsuario vidUsr1 = new DtVideoUsuario();
        vidUsr1.setNickname("Mateo");
        vidUsr1.setNombreE("mateo");
        DtVideoUsuario vidUsr2 = new DtVideoUsuario(vidUsr1.getNickname(), vidUsr1.getNombreE());
        assertEquals(vidUsr1.getNickname(), vidUsr2.getNickname());
    }

    @Test
    public void DtVisita() {
        Calendar fecha = Calendar.getInstance();
        DtVisita visita1 = new DtVisita();
        visita1.setUsrVideo("usrVid");
        visita1.setNomVideo("vid");
        visita1.setUrlVideo("url");
        visita1.setUltimaVisita(fecha);
        visita1.setCantVisitas(1);
        DtVisita visita2 = new DtVisita(visita1.getUsrVideo(), visita1.getNomVideo(), visita1.getUltimaVisita(), visita1.getCantVisitas());
        visita2.setUrlVideo(visita1.getUrlVideo());
        assertEquals(visita1.getUsrVideo(), visita2.getUsrVideo());
    }

}
