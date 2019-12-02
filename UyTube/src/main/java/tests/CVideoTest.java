package tests;

import datatypes.DtElementoWeb;
import datatypes.tipoElemento;
import logica.Usuario;
import logica.Video;
import manejadores.ManejadorCategoria;
import manejadores.ManejadorUsuario;
import datatypes.DtComentario;
import interfaces.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Calendar;
import java.util.List;
import static org.junit.Assert.*;

public class CVideoTest {
    private ICategoria iC = null;
    private IVideo iV = null;
    private IUsuario iU = null;
    private ManejadorCategoria mC = null;
    private ManejadorUsuario mU = null;

    @Before
    public void inicializar(){
        iC = CFactory.getInstancia().getICategoria();
        iV = VFactory.getInstancia().getIVideo();
        iU = UFactory.getInstancia().getIUsuario();
        mC = ManejadorCategoria.getInstancia();
        mU = ManejadorUsuario.getInstancia();
    }

    @Test
    public void agregarCategoria() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iV.setUsr("usr");
        iC.altaCategoria("cat");
        iV.agregarVideo("usr", "vid", "desc", cal, 10, "url");
        iV.agregarCategoria("cat");
        assertEquals("cat", iV.obtenerInfoVideo("vid").getCategoria());
    }

    @Test
    public void agregarVideo() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iV.setUsr("usr");
        iV.agregarVideo("usr", "vid", "desc", cal, 10, "url");
        assertEquals("vid", iV.obtenerInfoVideo("vid").getNombre());
    }

    @Test
    public void limpiarControlador() {
        iV.limpiarControlador();
    }

    @Test
    public void listarVideosDeUsuario() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iV.setUsr("usr");
        iC.altaCategoria("cat");
        iV.agregarVideo("usr", "vid", "desc", cal, 10, "url");
        assertEquals(1, iV.listarVideosDeUsuario("usr").size());
    }

    @Test
    public void listarVideosDeUsuarioWeb() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iV.setUsr("usr");
        iC.altaCategoria("cat");
        iV.agregarVideo("usr", "vid", "desc", cal, 10, "url");
        assertEquals(1, iV.listarVideosDeUsuarioWeb("usr").size());
    }

    @Test
    public void listarVideosPublicosDeUsuario() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iV.setUsr("usr");
        iC.altaCategoria("cat");
        iV.agregarVideo("usr", "vid", "desc", cal, 10, "url");
        iV.modificarInfoVideo("vid", "desc", cal, 10, "url", true);
        assertEquals(1, iV.listarVideosPublicosDeUsuario("usr").size());
    }

    @Test
    public void listarVideosPublicosDeUsuarioWeb() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iV.setUsr("usr");
        iC.altaCategoria("cat");
        iV.agregarVideo("usr", "vid", "desc", cal, 10, "url");
        iV.modificarInfoVideo("vid", "desc", cal, 10, "url", true);
        assertEquals(1, iV.listarVideosPublicosDeUsuarioWeb("usr").size());
    }

    @Test
    public void listarVideosPublicos() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iV.setUsr("usr");
        iC.altaCategoria("cat");
        iV.agregarVideo("usr", "vid", "desc", cal, 10, "url");
        iV.modificarInfoVideo("vid", "desc", cal, 10, "url", true);
        assertEquals(1, iV.listarVideosPublicos().size());
    }

    @Test
    public void listarVideosPublicosWeb() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iV.setUsr("usr");
        iC.altaCategoria("cat");
        iV.agregarVideo("usr", "vid", "desc", cal, 10, "url");
        iV.modificarInfoVideo("vid", "desc", cal, 10, "url", true);
        assertEquals(1, iV.listarVideosPublicosWeb().size());
    }

    @Test
    public void modificarInfoVideo() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iV.setUsr("usr");
        iV.agregarVideo("usr", "vid", "desc", cal, 10, "url");
        iV.modificarInfoVideo("vid", "desc", cal, 10, "url", true);
        assertEquals(true, iV.obtenerInfoVideo("vid").getPublico());
    }

    @Test
    public void obtenerComentariosVideo() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iV.setUsr("usr");
        iV.agregarVideo("usr", "vid", "desc", cal, 10, "url");
        iV.realizarComentario("usr", cal, "coment");
        assertEquals(1, iV.obtenerComentariosVideo("vid").size());
    }

    @Test
    public void obtenerInfoVideo() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iV.setUsr("usr");
        iV.agregarVideo("usr", "vid", "desc", cal, 10, "url");
        assertEquals("vid", iV.obtenerInfoVideo("vid").getNombre());
    }

    @Test
    public void obtenerValoracionVideo() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iV.setUsr("usr");
        iV.agregarVideo("usr", "vid", "desc", cal, 10, "url");
        iV.valorarVideo("usr", true);
        assertEquals(1, iV.obtenerValoracionVideo().size());
    }

    @Test
    public void responderComentario() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iV.setUsr("usr");
        iV.agregarVideo("usr", "vid", "desc", cal, 10, "url");
        iV.realizarComentario("usr", cal, "coment");
        List<DtComentario> comentarios = iV.obtenerComentariosVideo("vid");
        iV.responderComentario(comentarios.get(0).getId(), "usr", cal, "resp");
        comentarios = iV.obtenerComentariosVideo("vid");
        assertEquals(1, comentarios.get(0).getRespuestas().size());
    }

    @Test
    public void realizarComentario() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iV.setUsr("usr");
        iV.agregarVideo("usr", "vid", "desc", cal, 10, "url");
        iV.realizarComentario("usr", cal, "coment");
        assertEquals(1, iV.obtenerComentariosVideo("vid").size());
    }

    @Test
    public void valorarVideo() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iV.setUsr("usr");
        iV.agregarVideo("usr", "vid", "desc", cal, 10, "url");
        iV.valorarVideo("usr", true);
        assertEquals(true, iV.obtenerValoracionVideo().get(0).getGusta());
    }

    @Test
    public void existeVideo() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iV.setUsr("usr");
        assertEquals(false, iV.existeVideo("usr", "vid"));
    }

    @Test
    public void cantidadGusta() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iV.setUsr("usr");
        iV.agregarVideo("usr", "vid", "desc", cal, 10, "url");
        iV.valorarVideo("usr", true);
        assertEquals(1,(long) iV.cantidadGusta());
    }

    @Test
    public void cantidadNoGusta() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iV.setUsr("usr");
        iV.agregarVideo("usr", "vid", "desc", cal, 10, "url");
        iV.valorarVideo("usr", false);
        iV.setVid("vid");
        assertEquals(1,(long) iV.cantidadNoGusta());
    }

    @Test
    public void busqueda() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iU.modificarInfoCanal("nom", "desc", true);
        iV.setUsr("usr");
        iV.agregarVideo("usr", "vid", "desc", cal, 10, "url");
        iV.modificarInfoVideo("vid", "desc", cal, 10, "url", true);
        assertEquals(1, iV.busqueda("vid", false).size());
    }

    @Test
    public void busquedaFecha() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iU.modificarInfoCanal("nom", "desc", true);
        iV.setUsr("usr");
        iV.agregarVideo("usr", "vid", "desc", cal, 10, "url");
        iV.modificarInfoVideo("vid", "desc", cal, 10, "url", true);
        assertEquals(1, iV.busqueda("vid", true).size());
    }

    @Test
    public void setUsr(){
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");

    }

    @Test
    public void setVid(){
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iV.agregarVideo("usr", "vid", "desc", cal, 10, "url");
        Usuario usr = mU.obtenerUsuario("usr");
        Video vid = usr.getCanal().obtenerVideo("vid");
        iV.setVid("vid");
        assertEquals("vid", vid.getNombre());
    }

    @Test
    public void obtenerIdVideo(){
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iV.setUsr("usr");
        iV.agregarVideo("usr", "vid", "desc", cal, 10, "url");
        iV.setVid("vid");
        Integer idEsperado = 1; //aca hay que poner el id del video que agregas
        Integer idObtenido = iV.obtenerIdVideo("usr", "vid");
        assertEquals(idEsperado, idObtenido);
    }

    @Test
    public void obtenerVideo(){
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iV.setUsr("usr");
        iV.agregarVideo("usr", "vid", "desc", cal, 10, "url");
        iV.setVid("vid");
        DtElementoWeb videoObtenido = iV.obtenerVideo(25); //aca hay que poner el id del video que agregas
        assertEquals("", videoObtenido.getNombreE());
    }

    @After
    public void terminarCaso(){
        mC.cerrarConexion();
        mU.cerrarConexion();
    }
}