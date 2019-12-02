package publicadores;

import datatypes.DtElementoWeb;
import datatypes.DtListaRep;
import datatypes.DtVideoUsuario;
import interfaces.IListaReproduccion;
import interfaces.LRFactory;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

import java.util.List;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class CListaRepPublish {
    private LRFactory lisRepFactory;
    private IListaReproduccion iLisRep;
    private Endpoint endpoint;

    public CListaRepPublish() {
        this.lisRepFactory = LRFactory.getInstancia();
        this.iLisRep = this.lisRepFactory.getIListaReproduccion();
    }

    @WebMethod(exclude = true)
    public void publicar() {
        endpoint = Endpoint.publish("http://" + "127.0.0.1" + ":" + "16000" + "/Lista", this);
        System.out.println("WebService de ControladorLista publicado en: "+ "http://" + "127.0.0.1" + ":" + "16000" + "/Lista" + "?wsdl");
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    //MÉTODOS QUE VAMOS A PUBLICAR
    @WebMethod
    public void agregarCategoriaALista(String nomC) {
        this.iLisRep.agregarCategoriaALista(nomC);
    }


    @WebMethod
    public void agregarListaDefecto(String nomL) {
        this.iLisRep.agregarListaDefecto(nomL);
    }


    @WebMethod
    public void agregarListaParticular(String nomL, boolean publico) {
        this.iLisRep.agregarListaParticular(nomL, publico);
    }

    @WebMethod
    public void agregarListaParticularCategoria(String nomL, boolean publico, String nomC) {
        this.iLisRep.agregarListaParticularCategoria(nomL, publico, nomC);
    }

    @WebMethod
    public void agregarVideoListaParticular(String nickVideo, String nomVid, String nomList) {
        this.iLisRep.agregarVideoListaParticular(nickVideo, nomVid, nomList);
    }

    @WebMethod
    public void agregarVideoListaPorDefecto(String nickVideo, String nomVid, String nomList) {
        this.iLisRep.agregarVideoListaPorDefecto(nickVideo, nomVid, nomList);
    }

    @WebMethod
    public void eliminarVideoDeLista(String nickVid, String nomVid, String nomList) {
        this.iLisRep.eliminarVideoDeLista(nickVid, nomVid, nomList);
    }

    @WebMethod
    public boolean existeLista(String nomL) {
        return this.iLisRep.existeLista(nomL);
    }

    @WebMethod
    public boolean existeListaDefecto(String nomL) {
        return iLisRep.existeListaDefecto(nomL);
    }

    @WebMethod
    public boolean existeListaParticular(String nick, String nomL) {
        return iLisRep.existeListaParticular(nick, nomL);
    }
    
    @WebMethod
    public String[] listarListasDeUsuario(String nick) {
        List<String> lists = iLisRep.listarListasDeUsuario(nick);
        int i = 0;
        String[] ret = new String[lists.size()];
        for (String l : lists) {
            ret[i]=l;
            i++;
        }
        return ret;
    }

    @WebMethod
    public String[] listarListasParticulares(String nick) {
        List<String> lists = iLisRep.listarListasParticulares(nick);
        int i = 0;
        String[] ret = new String[lists.size()];
        for (String l : lists) {
            ret[i]=l;
            i++;
        }
        return ret;
    }

    @WebMethod
    public String[] listarListasParticularesPublicas(String nick) {
        List<String> lists = iLisRep.listarListasParticulares(nick);
        int i = 0;
        String[] ret = new String[lists.size()];
        for (String l : lists) {
            ret[i]=l;
            i++;
        }
        return ret;
    }

    @WebMethod
    public String[] listarListasPorDefecto(String nick) {
        List<String> lists = iLisRep.listarListasPorDefecto(nick);
        int i = 0;
        String[] ret = new String[lists.size()];
        for (String l : lists) {
            ret[i]=l;
            i++;
        }
        return ret;
    }

    @WebMethod
    public DtVideoUsuario[] listarVideosdeLista(String nomList) {
        List<DtVideoUsuario> dtVid = iLisRep.listarVideosdeLista(nomList);
        int i = 0;
        DtVideoUsuario[] ret = new DtVideoUsuario[dtVid.size()];
        for (DtVideoUsuario v : dtVid) {
            ret[i]=v;
            i++;
        }
        return ret;
    }

    @WebMethod
    public DtElementoWeb[] listarVideosListaWeb(String nomList) {
        List<DtElementoWeb> dtElem = iLisRep.listarVideosListaWeb(nomList);
        int i = 0;
        DtElementoWeb[] ret = new DtElementoWeb[dtElem.size()];
        for (DtElementoWeb e : dtElem) {
            ret[i]=e;
            i++;
        }
        return ret;
    }

    @WebMethod
    public void modificarCategoria(String nomC) {
        iLisRep.modificarCategoria(nomC); //La lista es recordada por el controlador
    }


    @WebMethod
    public void modificarInfoLista(String nomL, boolean publico) {
        iLisRep.modificarInfoLista(nomL, publico);
    }


    @WebMethod
    public DtListaRep obtenerListaDeUsuario(String nomList) {
        return iLisRep.obtenerListaDeUsuario(nomList); //Como hacer con el canal?
    }


    @WebMethod
    public void setuVid(String nick) {
        iLisRep.setuVid(nick);
    }

    @WebMethod
    public void setuList(String nick) {
        iLisRep.setuList(nick);
    }

    @WebMethod
    public void setVideo(String nomV) {
        iLisRep.setVideo(nomV);
    }

    @WebMethod
    public void setLista(String nomL) {
        iLisRep.setLista(nomL);
    }

    @WebMethod
    public void eliminarCategoria() {
        iLisRep.eliminarCategoria();
    }

    @WebMethod
    public DtElementoWeb[] busqueda(String query, Boolean ordFecha){
        List<DtElementoWeb> dtElem = iLisRep.busqueda(query, ordFecha);
        int i = 0;
        DtElementoWeb[] ret = new DtElementoWeb[dtElem.size()];
        for (DtElementoWeb e : dtElem) {
            ret[i]=e;
            i++;
        }
        return ret;
    }
}
