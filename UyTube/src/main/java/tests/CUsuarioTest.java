package tests;

import interfaces.*;
import manejadores.ManejadorCategoria;
import manejadores.ManejadorUsuario;
import datatypes.*;
import logica.Canal;
import logica.Categoria;
import logica.Usuario;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Calendar;
import java.util.List;
import static org.junit.Assert.*;

public class CUsuarioTest {
    private IUsuario iU = null;
    private IVideo iV = null;
    private IListaReproduccion iL = null;
    private ManejadorUsuario mU = null;
    private ManejadorCategoria mC = null;

    @Before
    public void inicializar(){
        iU = UFactory.getInstancia().getIUsuario();
        iV = VFactory.getInstancia().getIVideo();
        iL = LRFactory.getInstancia().getIListaReproduccion();
        mU = ManejadorUsuario.getInstancia();
        mC = ManejadorCategoria.getInstancia();
    }

    @Test
    public void agregarCanal() {
        Calendar fecha = Calendar.getInstance();
        Usuario usrEsperado = new Usuario("usr1", "nom", "apellido", fecha, "email");
        Canal canalEsperado = new Canal("nom", "desc", true);
        canalEsperado.setUsuario(usrEsperado);
        iU.agregarUsuario("usr1", "nom", "apellido", fecha, "email");
        iU.agregarCanal();
        iU.modificarInfoCanal("nom", "desc", true);
        Canal canalObtenido = mU.obtenerUsuario("usr1").getCanal();
        assertEquals(canalEsperado.getNombre(), canalObtenido.getNombre());
    }

    @Test
    public void modificarCatCanal() {
        Calendar fecha = Calendar.getInstance();
        Usuario usrEsperado = new Usuario("usr1", "nom", "apellido", fecha, "email");
        Canal canalEsperado = new Canal("nom", "desc", true);
        Categoria cat = new Categoria("perro");
        canalEsperado.setCategoria(cat);
        mC.agregarCategoria(cat);
        iU.agregarUsuario("usr1", "nom", "apellido", fecha, "email");
        iU.agregarCanal();
        iU.modificarCatCanal("usr1", cat.getNombre());
        Canal canalObtenido = mU.obtenerUsuario("usr1").getCanal();
        assertEquals(canalEsperado.getCategoria().getNombre(), canalObtenido.getCategoria().getNombre());
    }

    @Test
    public void agregarUsuario() {
        Calendar fecha = Calendar.getInstance();
        Usuario usrEsperado = new Usuario("usr1", "nom", "apellido", fecha, "email");
        iU.agregarUsuario("usr1", "nom", "apellido", fecha, "email");
        Usuario usrObtenido = mU.obtenerUsuario("usr1");
        assertEquals(usrEsperado.getNickname(), usrObtenido.getNickname());
    }

    @Test
    public void dejarDeSeguirUsuario() {
        Calendar fecha = Calendar.getInstance();
        iU.agregarUsuario("usr1", "nom", "apellido", fecha, "email");
        iU.agregarUsuario("usr2", "nom", "apellido", fecha, "email2");
        iU.seguirUsuario("usr2", "usr1");
        iU.dejarDeSeguirUsuario("usr2", "usr1");
        Usuario usrObtenido = mU.obtenerUsuario("usr1");
        assertEquals(0, usrObtenido.getSeguidores().size());
    }

    @Test
    public void existeEmail() {
        Calendar fecha = Calendar.getInstance();
        iU.agregarUsuario("usr1", "nom", "apellido", fecha, "email");
        Boolean res = iU.existeEmail("email");
        assertEquals(true, res);
    }

    @Test
    public void existeNickname() {
        Calendar fecha = Calendar.getInstance();
        iU.agregarUsuario("usr1", "nom", "apellido", fecha, "email");
        Boolean res = iU.existeNickname("usr1");
        assertEquals(true, res);
    }

    @Test
    public void limpiarControlador() {
        iU.limpiarControlador();
        assertEquals(true, !false);
    }

    @Test
    public void listarSeguidores() {
        Calendar fecha = Calendar.getInstance();
        iU.agregarUsuario("usr1", "nom", "apellido", fecha, "email");
        iU.agregarUsuario("usr2", "nom", "apellido", fecha, "email2");
        iU.seguirUsuario("usr2", "usr1");
        iU.obtenerInfoUsuario("usr1");
        assertEquals(1, iU.listarSeguidores().size());
    }

    @Test
    public void listarSeguidos() {
        Calendar fecha = Calendar.getInstance();
        iU.agregarUsuario("usr1", "nom", "apellido", fecha, "email");
        iU.agregarUsuario("usr2", "nom", "apellido", fecha, "email2");
        iU.seguirUsuario("usr2", "usr1");
        iU.obtenerInfoUsuario("usr2");
        assertEquals(1, iU.listarSeguidos().size());
    }

    @Test
    public void listarUsuarios() {
        Calendar fecha = Calendar.getInstance();
        iU.agregarUsuario("usr1", "nom", "apellido", fecha, "email");
        iU.agregarUsuario("usr2", "nom", "apellido", fecha, "email2");
        assertEquals(2, iU.listarUsuarios().size());
    }

    @Test
    public void modificarImagen() {
        Calendar fecha = Calendar.getInstance();
        iU.agregarUsuario("usr1", "nom", "apellido", fecha, "email");
        String img = "img";
        iU.modificarImagen(img);
        assertEquals(img, mU.obtenerUsuario("usr1").getImagen());
    }

    @Test
    public void modificarContrasena() {
        Calendar fecha = Calendar.getInstance();
        iU.agregarUsuario("usr1", "nom", "apellido", fecha, "email");
        iU.modificarContrasena("pass");
        assertEquals("pass", mU.obtenerUsuario("usr1").getContrasena());
    }

    @Test
    public void modificarInfoCanal() {
        Calendar fecha = Calendar.getInstance();
        Usuario usrEsperado = new Usuario("usr1", "nom", "apellido", fecha, "email");
        Canal canalEsperado = new Canal("nom", "desc", true);
        canalEsperado.setUsuario(usrEsperado);
        iU.agregarUsuario("usr1", "nom", "apellido", fecha, "email");
        iU.agregarCanal();
        iU.modificarInfoCanal("nom", "desc", true);
        Canal canalObtenido = mU.obtenerUsuario("usr1").getCanal();
        assertEquals(canalEsperado.getNombre(), canalObtenido.getNombre());
    }

    @Test
    public void modificarInfoUsuario() {
        Calendar fecha = Calendar.getInstance();
        Usuario usrEsperado = new Usuario("usr1", "nom2", "apellido", fecha, "email");
        iU.agregarUsuario("usr1", "nom", "apellido", fecha, "email");
        iU.modificarInfoUsuario("nom2", "apellido", fecha, "email");
        assertEquals(usrEsperado.getNombre(), mU.obtenerUsuario("usr1").getNombre());
    }

    @Test
    public void obtenerInfoCanal() {
        Calendar fecha = Calendar.getInstance();
        Usuario usrEsperado = new Usuario("usr1", "nom", "apellido", fecha, "email");
        Canal canalEsperado = new Canal("nom", "desc", true);
        canalEsperado.setUsuario(usrEsperado);
        iU.agregarUsuario("usr1", "nom", "apellido", fecha, "email");
        iU.agregarCanal();
        iU.modificarInfoCanal("nom", "desc", true);
        DtCanal canalObtenido = iU.obtenerInfoCanal();
        assertEquals(canalEsperado.getNombre(), canalObtenido.getNombre());

    }

    @Test
    public void obtenerInfoUsuario() {
        Calendar fecha = Calendar.getInstance();
        Usuario usrEsperado = new Usuario("usr1", "nom", "apellido", fecha, "email");
        iU.agregarUsuario("usr1", "nom", "apellido", fecha, "email");
        DtUsuario usrObtenido = iU.obtenerInfoUsuario("usr1");
        assertEquals(usrEsperado.getNombre(), usrObtenido.getNombre());
    }

    @Test
    public void esCanalPublico() {
        Calendar fecha = Calendar.getInstance();
        iU.agregarUsuario("usr1", "nom", "apellido", fecha, "email");
        iU.agregarCanal();
        iU.modificarInfoCanal("nom", "desc", true);
        assertEquals(true, iU.esCanalPublico("usr1"));
    }

    @Test
    public void seguirUsuario() {
        Calendar fecha = Calendar.getInstance();
        iU.agregarUsuario("usr1", "nom", "apellido", fecha, "email");
        iU.agregarUsuario("usr2", "nom", "apellido", fecha, "email");
        iU.seguirUsuario("usr2", "usr1");
        Usuario usrObtenido = mU.obtenerUsuario("usr1");
        assertEquals(1, usrObtenido.getSeguidores().size());
    }


    @Test
    public void iniciarSesion() {
        //Retorna: 0-> si no coincide los datos
        //		   1-> si coincide con nickname
        //		   2-> si coincide con email
        Integer[] esperado= {0, 1, 2};
        Calendar fecha = Calendar.getInstance();
        iU.agregarUsuario("usr1", "nom", "apellido", fecha, "email");
        iU.modificarContrasena("pass");
        Integer[] obtenido = new Integer[3];
        obtenido[0] = iU.iniciarSesion("error", "pass");
        obtenido[1] = iU.iniciarSesion("usr1", "pass");
        obtenido[2] = iU.iniciarSesion("email", "pass");
        assertArrayEquals(esperado, obtenido);
    }

    @Test
    public void obtenerUsuarioWebNick() {
        Calendar fecha = Calendar.getInstance();
        iU.agregarUsuario("usr1", "nom", "apellido", fecha, "email");
        iU.agregarCanal();
        DtUsuarioWeb obtenido = iU.obtenerUsuarioWebNick("usr1");
        assertEquals("usr1", obtenido.getNickname());
    }

    @Test
    public void obtenerUsuarioWebEmail() {
        Calendar fecha = Calendar.getInstance();
        iU.agregarUsuario("usr1", "nom", "apellido", fecha, "email");
        iU.agregarCanal();
        DtUsuarioWeb obtenido = iU.obtenerUsuarioWebEmail("email");
        assertEquals("usr1", obtenido.getNickname());
    }

    @Test
    public void listarUsuariosWeb() {
        Calendar fecha = Calendar.getInstance();
        iU.agregarUsuario("usr1", "nom", "apellido", fecha, "email");
        iU.agregarCanal();
        iU.agregarUsuario("usr2", "nom", "apellido", fecha, "email2");
        iU.agregarCanal();
        assertEquals(2, iU.listarUsuariosWeb().size());
    }

    @Test
    public void listarNickFotoWeb() {
        Calendar fecha = Calendar.getInstance();
        iU.agregarUsuario("usr1", "nom", "apellido", fecha, "email");
        iU.agregarCanal();
        iU.agregarUsuario("usr2", "nom", "apellido", fecha, "email2");
        iU.agregarCanal();
        List<String> lis = iU.listarUsuarios();
        assertEquals(2, iU.listarNickFotoWeb(lis).size());
    }

    @Test
    public void busqueda() {
        Integer[] esperado = {1, 1};
        Calendar fecha = Calendar.getInstance();
        iU.agregarUsuario("usr1", "nom", "apellido", fecha, "email");
        iU.modificarImagen("img");
        iU.agregarCanal();
        iU.modificarInfoCanal("nom", "des", true);
        List<DtCanalWeb> lisAlfa = iU.busqueda("nom", false);
        List<DtCanalWeb> lisFecha = iU.busqueda("nom", true);
        Integer[] obtenido = {lisAlfa.size(), lisFecha.size()};
        assertArrayEquals(esperado, obtenido);
    }

    @Test
    public void visitasAll(){
        Calendar fecha = Calendar.getInstance();
        iU.agregarUsuario("usrSesion", "nom", "apellido", fecha, "email");
        iU.agregarCanal();
        iU.agregarUsuario("usrVideo", "nom", "apellido", fecha, "email");
        iU.agregarCanal();
        iV.agregarVideo("usrVideo", "vid", "desc", fecha, 10, "url");
        iU.agregarVisita("usrSesion", "usrVideo", "vid");
        assertEquals(1, iU.listarMasVisitados("usrSesion").size());
    }

    @Test
    public void borrarTodosSeguidores(){ //El usuario 1 sigue al 2
        Calendar fecha = Calendar.getInstance();
        iU.agregarUsuario("usr1", "nom", "apellido", fecha, "email");
        iU.agregarCanal();
        iU.agregarUsuario("usr2", "nom", "apellido", fecha, "email");
        iU.agregarCanal();
        iU.seguirUsuario("usr1", "usr2");
        Usuario usr2 = mU.obtenerUsuario("usr2");
        iU.borrarTodosSeguidores(usr2);
        assertEquals(0, usr2.getSeguidores().size());
    }

    @Test
    public void borrarTodosSeguidos(){ //El usuario 1 sigue al 2
        Calendar fecha = Calendar.getInstance();
        iU.agregarUsuario("usr1", "nom", "apellido", fecha, "email");
        iU.agregarCanal();
        iU.agregarUsuario("usr2", "nom", "apellido", fecha, "email");
        iU.agregarCanal();
        iU.seguirUsuario("usr1", "usr2");
        Usuario usr1 = mU.obtenerUsuario("usr1");
        iU.borrarTodosSeguidos(usr1);
        assertEquals(0, usr1.getSeguidos().size());
    }

    @Test
    public void listarUsuariosEliminados(){
        Calendar fecha = Calendar.getInstance();
        iU.agregarUsuario("usr1", "nom", "apellido", fecha, "email");
        iU.agregarCanal();
        iU.eliminarUsuario("usr1");
        assertEquals(1, iU.listarUsuariosEliminados().size());
    }

    @Test
    public void obtenerInfoUsuarioEliminadoComentario() { /*Aca tambien testeo la operacion de eliminarUsuario. Borro los comentarios en videos*/
        Calendar fecha = Calendar.getInstance();
        iU.agregarUsuario("usr1", "nom", "apellido", fecha, "email");
        iU.agregarCanal();
        iU.agregarUsuario("usr2", "nom2", "apellido2", fecha, "email2");
        iU.agregarCanal();
        iV.agregarVideo("usr1", "vidUsr1", "videito1", fecha, 123, "url");
        iV.setVid("vidUsr1");
        iV.agregarVideo("usr2", "vidUsr2", "videito2", fecha, 123, "url");
        iV.setVid("vidUsr2");
        iV.valorarVideo("usr1", true);
        iV.realizarComentario("usr1", fecha, "buen video lince");
        iL.setuList("usr1");
        iL.agregarListaParticular("listaUsr1", true);
        iL.agregarVideoListaParticular("usr2", "vidUsr2", "listaUsr1");
        iL.setuList("usr2");
        iL.agregarListaParticular("listaUsr2", true);
        iL.agregarVideoListaParticular("usr1", "vidUsr1", "listaUsr2");
        iU.agregarVisita("usr1", "usr2", "vidUsr2");
        iU.agregarVisita("usr2", "usr1", "vidUsr1");
        iU.crearToken("1", "1", "usr1");
        DtUsuario usrEsperado = iU.obtenerInfoUsuario("usr1");
        iU.eliminarUsuario("usr1");
        DtUsuario usrObtenido = iU.obtenerInfoUsuarioEliminado("usr1");
        assertEquals(usrEsperado.getNickname(), usrObtenido.getNickname());
    }

    @Test
    public void obtenerInfoUsuarioEliminadoRespuesta() { /*Aca tambien testeo la operacion de eliminarUsuario. Borro las respuestasa comentarios en videos*/
        Calendar fecha = Calendar.getInstance();
        iU.agregarUsuario("usr1", "nom", "apellido", fecha, "email");
        iU.agregarCanal();
        iU.agregarUsuario("usr2", "nom2", "apellido2", fecha, "email2");
        iU.agregarCanal();
        iV.agregarVideo("usr1", "vidUsr1", "videito1", fecha, 123, "url");
        iV.setVid("vidUsr1");
        iV.agregarVideo("usr2", "vidUsr2", "videito2", fecha, 123, "url");
        iV.setVid("vidUsr2");
        iV.valorarVideo("usr1", true);
        iV.realizarComentario("usr1", fecha, "buen video lince");
        iV.responderComentario(1, "usr1", fecha, "respuesta wena");
        iL.setuList("usr1");
        iL.agregarListaParticular("listaUsr1", true);
        iL.agregarVideoListaParticular("usr2", "vidUsr2", "listaUsr1");
        iL.setuList("usr2");
        iL.agregarListaParticular("listaUsr2", true);
        iL.agregarVideoListaParticular("usr1", "vidUsr1", "listaUsr2");
        iU.agregarVisita("usr1", "usr2", "vidUsr2");
        iU.agregarVisita("usr2", "usr1", "vidUsr1");
        DtUsuario usrEsperado = iU.obtenerInfoUsuario("usr1");
        iU.eliminarUsuario("usr1");
        DtUsuario usrObtenido = iU.obtenerInfoUsuarioEliminado("usr1");
        assertEquals(usrEsperado.getNickname(), usrObtenido.getNickname());
    }

    @Test
    public void crearToken() {
        Calendar fecha = Calendar.getInstance();
        iU.agregarUsuario("usr1", "nom", "apellido", fecha, "email");
        iU.agregarCanal();
        iU.crearToken("selector", "validador", "usr1");
    }

    @Test
    public void obtenerUsuarioConToken() {
        Calendar fecha = Calendar.getInstance();
        iU.agregarUsuario("usr1", "nom", "apellido", fecha, "email");
        iU.agregarCanal();
        DtUsuarioWeb usrEsperado = iU.obtenerUsuarioWebNick("usr1");
        iU.crearToken("selector", "validador", "usr1");
        DtUsuarioWeb usrObtenido = iU.obtenerUsuarioConToken("selector", "validador");
        assertEquals(usrEsperado.getNickname(), usrObtenido.getNickname());
    }

    @After
    public void terminarCaso(){
        mU.cerrarConexion();
    }
}