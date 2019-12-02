package tests;

import manejadores.ManejadorCategoria;
import manejadores.ManejadorUsuario;
import interfaces.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Calendar;
import static org.junit.Assert.*;

public class CListaReproduccionTest {
    private IUsuario iU = null;
    private ICategoria iC = null;
    private IListaReproduccion iL = null;
    private IVideo iV = null;
    private ManejadorUsuario mU = null;
    private ManejadorCategoria mC = null;

    @Before
    public void inicializar(){
        iU = UFactory.getInstancia().getIUsuario();
        iC = CFactory.getInstancia().getICategoria();
        iL = LRFactory.getInstancia().getIListaReproduccion();
        iV = VFactory.getInstancia().getIVideo();
        mU = ManejadorUsuario.getInstancia();
        mC = ManejadorCategoria.getInstancia();
    }

    @Test
    public void agregarCategoriaALista() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iL.setuList("usr");
        iC.altaCategoria("cat");
        iL.agregarListaParticular("lis", true);
        iL.agregarCategoriaALista("cat");
        assertEquals("cat", iL.obtenerListaDeUsuario("lis").getCategoria());
    }

    @Test
    public void agregarListaDefecto() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iL.setuList("usr");
        iC.altaCategoria("cat");
        iL.agregarListaDefecto("lis");
        assertEquals(false, iL.obtenerListaDeUsuario("lis").getEsParticular());
    }

    @Test
    public void agregarListaParticular() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iL.setuList("usr");
        iL.agregarListaParticular("lis", true);
        assertEquals(true, iL.obtenerListaDeUsuario("lis").getEsParticular());
    }

    @Test
    public void agregarListaParticularCategoria() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iL.setuList("usr");
        iC.altaCategoria("cat");
        iL.agregarListaParticularCategoria("lis", true, "cat");
        assertEquals("cat", iL.obtenerListaDeUsuario("lis").getCategoria());
    }

    @Test
    public void agregarVideoListaParticular() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iL.setuList("usr");
        iL.agregarListaParticular("lis", true);
        iV.agregarVideo("usr", "nom", "desc", cal, 10, "url");
        iL.agregarVideoListaParticular("usr", "nom", "lis");
        assertEquals(1, iL.listarVideosdeLista("lis").size());
    }

    @Test
    public void agregarVideoListaPorDefecto() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iL.setuList("usr");
        iL.agregarListaDefecto("lis");
        iV.agregarVideo("usr", "nom", "desc", cal, 10, "url");
        iL.agregarVideoListaPorDefecto("usr", "nom", "lis");
        assertEquals(1, iL.listarVideosdeLista("lis").size());
    }

    @Test
    public void eliminarVideoDeLista() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iL.setuList("usr");
        iL.agregarListaDefecto("lis");
        iV.agregarVideo("usr", "nom", "desc", cal, 10, "url");
        iL.agregarVideoListaPorDefecto("usr", "nom", "lis");
        iL.eliminarVideoDeLista("usr", "nom", "lis");
        assertEquals(0, iL.listarVideosdeLista("lis").size());
    }

    @Test
    public void existeLista() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iL.setuList("usr");
        iL.agregarListaParticular("lis", true);
        assertEquals(true, iL.existeLista("lis"));
    }

    @Test
    public void existeListaFalse() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iL.setuList("usr");
        assertEquals(false, iL.existeLista("lis"));
    }

    @Test
    public void existeListaDefecto() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iL.setuList("usr");
        iL.agregarListaDefecto("lis");
        assertEquals(true, iL.existeListaDefecto("lis"));
    }

    @Test
    public void existeListaDefectoFalse() {
        assertEquals(false, iL.existeListaDefecto("lis"));
    }

    @Test
    public void existeListaParticular() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iL.setuList("usr");
        iL.agregarListaParticular("lis", true);
        assertEquals(true, iL.existeListaParticular("usr", "lis"));
    }

    @Test
    public void existeListaParticularFalse() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iL.setuList("usr");
        assertEquals(false, iL.existeListaParticular("usr", "lis"));
    }

    @Test
    public void limpiarControlador() {
        iL.limpiarControlador();
    }

    @Test
    public void listarListasDeUsuario() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iL.setuList("usr");
        iL.agregarListaParticular("lis", true);
        assertEquals(1, iL.listarListasDeUsuario("usr").size());
    }

    @Test
    public void listarListasParticulares() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iL.setuList("usr");
        iL.agregarListaParticular("lis", true);
        assertEquals(1, iL.listarListasParticulares("usr").size());
    }

    @Test
    public void listarListasParticularesPublicas() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iL.setuList("usr");
        iL.agregarListaParticular("lis", true);
        assertEquals(1, iL.listarListasParticularesPublicas("usr").size());
    }

    @Test
    public void listarListasPorDefecto() {
        Calendar cal = Calendar.getInstance();
        iL.agregarListaDefecto("lis");
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        assertEquals(1, iL.listarListasPorDefecto("usr").size());
    }

    @Test
    public void listarVideosdeLista() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iL.setuList("usr");
        iL.agregarListaDefecto("lis");
        iV.agregarVideo("usr", "nom", "desc", cal, 10, "url");
        iL.agregarVideoListaPorDefecto("usr", "nom", "lis");
        assertEquals(1, iL.listarVideosdeLista("lis").size());
    }

    @Test
    public void listarVideosdeListVacio() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iL.setuList("usr");
        iL.agregarListaDefecto("lis");
        assertEquals(0, iL.listarVideosdeLista("lis").size());
    }

    @Test
    public void listarVideosListaWeb() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iL.setuList("usr");
        iL.agregarListaDefecto("lis");
        iV.agregarVideo("usr", "nom", "desc", cal, 10, "url");
        iL.agregarVideoListaPorDefecto("usr", "nom", "lis");
        assertEquals(1, iL.listarVideosListaWeb("lis").size());
    }

    @Test
    public void listarVideosdeListaWebVacio() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iL.setuList("usr");
        iL.agregarListaDefecto("lis");
        assertEquals(0, iL.listarVideosListaWeb("lis").size());
    }

    @Test
    public void modificarCategoria() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iL.setuList("usr");
        iC.altaCategoria("cat");
        iC.altaCategoria("cat2");
        iL.agregarListaParticularCategoria("lis", true, "cat");
        iL.modificarCategoria("cat2");
        assertEquals("cat2", iL.obtenerListaDeUsuario("lis").getCategoria());
    }

    @Test
    public void modificarInfoLista() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iL.setuList("usr");
        iL.agregarListaParticular("lis", true);
        iL.setLista("lis");
        iL.modificarInfoLista("lis", false);
        assertEquals(false, iL.obtenerListaDeUsuario("lis").getPublico());
    }

    @Test
    public void obtenerListaDeUsuario() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iL.setuList("usr");
        assertEquals(null, iL.obtenerListaDeUsuario("lis"));
    }

    @Test
    public void setuVid() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iL.setuVid("usr");
    }

    @Test
    public void setuList() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iL.setuList("usr");
    }

    @Test
    public void setVideo() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iL.setuList("usr");
        iL.agregarListaDefecto("lis");
        iV.agregarVideo("usr", "nom", "desc", cal, 10, "url");
        iL.setuVid("usr");
        iL.setVideo("nom");
    }

    @Test
    public void setLista() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iL.setuList("usr");
        iL.agregarListaDefecto("lis");
        iL.setLista("lis");
    }

    @Test
    public void eliminarCategoria() {
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iL.setuList("usr");
        iC.altaCategoria("cat");
        iL.agregarListaParticularCategoria("lis", true, "cat");
        iL.eliminarCategoria();
        assertEquals("", iL.obtenerListaDeUsuario("lis").getCategoria());
    }

    @Test
    public void busqueda() {
        Integer[] esperado =  { 1, 1};
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iU.modificarInfoCanal("usr", "sda", true);
        iL.setuList("usr");
        iC.altaCategoria("cat");
        iL.agregarListaParticularCategoria("lis", true, "cat");
        Integer[] obtenido =  { iL.busqueda("lis", true).size(), iL.busqueda("lis", false).size()};
        assertArrayEquals(esperado, obtenido);
    }

    @After
    public void terminarCaso(){
        mU.cerrarConexion();
    }
}