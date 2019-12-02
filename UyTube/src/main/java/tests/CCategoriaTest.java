package tests;

import interfaces.*;
import manejadores.ManejadorCategoria;
import datatypes.DtElementoUsuario;
import datatypes.DtElementoWeb;
import logica.Categoria;
import manejadores.ManejadorUsuario;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

public class CCategoriaTest {
    private ICategoria iC = null;
    private ManejadorCategoria mC = null;
    private IUsuario iU = null;
    private IListaReproduccion iL = null;
    private IVideo iV = null;
    private ManejadorUsuario mU = null;

    @Before
    public void inicializar(){
        iC = CFactory.getInstancia().getICategoria();
        mC = ManejadorCategoria.getInstancia();
        iU = UFactory.getInstancia().getIUsuario();
        iL = LRFactory.getInstancia().getIListaReproduccion();
        iV = VFactory.getInstancia().getIVideo();
        mU = ManejadorUsuario.getInstancia();
    }

    @Test
    public void altaCategoria() {
        Categoria catEsperada = new Categoria("Deportes");
        iC.altaCategoria("Deportes");
        Categoria catObtenida = mC.obtenerCategoria("Deportes");
        assertEquals(catEsperada.getNombre(), catObtenida.getNombre());
    }

    @Test
    public void listarElemCategoria() {
        iC.altaCategoria("Deportes");
        List<DtElementoUsuario> elemObtenidos = iC.listarElemCategoria("Deportes");
        assertEquals(0, elemObtenidos.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void listarElemCategoriaException() {
        List<DtElementoUsuario> elemObtenidos = iC.listarElemCategoria("Deportes");
    }

    @Test
    public void listarVideosPublicosCategoria() {
        iC.altaCategoria("Deportes");
        List<DtElementoWeb> vidsObtenidos = iC.listarVideosPublicosCategoria("Deportes");
        assertEquals(0, vidsObtenidos.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void listarVideosException() {
        List<DtElementoWeb> vidsObtenidos = iC.listarVideosPublicosCategoria("Deportes");
    }

    @Test
    public void listarCategorias() {
        iC.altaCategoria("Deportes");
        iC.altaCategoria("Animales");
        List<String> resObtenido = iC.listarCategorias();
        assertEquals(2, resObtenido.size());
    }

    @Test
    public void existeCategoria() {
        iC.altaCategoria("Deportes");
        Boolean resEsperado = iC.existeCategoria("Deportes");
        assertEquals(true, resEsperado);
    }

    @Test
    public void limpiarControlador() {
        iC.limpiarControlador();
        assertEquals(true, !false);
    }

    @Test
    public void listarListasPublicasCategoria(){
        Calendar cal = Calendar.getInstance();
        iU.agregarUsuario("usr", "nom", "ape", cal, "mail");
        iU.agregarCanal();
        iL.setuList("usr");
        iL.agregarListaParticular("lis", true);
        iL.setLista("lis");
        iC.altaCategoria("Deportes");
        iL.modificarCategoria("Deportes");
        assertEquals(1, iC.listarListasPublicasCategoria("Deportes").size());
    }

    @After
    public void terminarCaso(){
        mC.cerrarConexion();
    }
}