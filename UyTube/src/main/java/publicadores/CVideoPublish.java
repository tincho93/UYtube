package publicadores;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;
import datatypes.DtComentario;
import datatypes.DtElementoUsuario;
import datatypes.DtElementoWeb;
import datatypes.DtFecha;
import datatypes.DtVideo;
import datatypes.DtValoracion;
import interfaces.IVideo;
import interfaces.VFactory;
import java.util.Calendar;
import java.util.List;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class CVideoPublish {
    private VFactory vidFactory;
    private IVideo iVid;
    private Endpoint endpoint;

    public CVideoPublish() {
        this.vidFactory = VFactory.getInstancia();
        this.iVid = this.vidFactory.getIVideo();
    }

    @WebMethod(exclude = true)
    public void publicar() {
        endpoint = Endpoint.publish("http://" + "127.0.0.1" + ":" + "16000" + "/Video", this);
        System.out.println("WebService de ControladorVideo publicado en: "+ "http://" + "127.0.0.1" + ":" + "16000" + "/Video" + "?wsdl");
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }


    //MÉTODOS QUE VAMOS A PUBLICAR
    @WebMethod
    public void agregarCategoria(String nomC) {
        iVid.agregarCategoria(nomC);
    }

    @WebMethod
    public void agregarVideo(String nick, String nomV, String desc, DtFecha fPub, int dur, String url){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, fPub.getAnio());
        cal.set(Calendar.MONTH, fPub.getMes());
        cal.set(Calendar.DAY_OF_MONTH, fPub.getDia());
        iVid.agregarVideo(nick, nomV, desc, cal, dur, url);
    }

    @WebMethod
    public String[] listarVideosDeUsuario(String nick){
        List<String> vids = iVid.listarVideosDeUsuario(nick);
        int i = 0;
        String[] ret = new String[vids.size()];
        for (String v : vids) {
            ret[i]=v;
            i++;
        }
        return ret;
    }

    @WebMethod
    public DtElementoWeb[] listarVideosDeUsuarioWeb(String nick){
        List<DtElementoWeb> dtElem = iVid.listarVideosDeUsuarioWeb(nick);
        int i = 0;
        DtElementoWeb[] ret = new DtElementoWeb[dtElem.size()];
        for (DtElementoWeb e : dtElem) {
            ret[i]=e;
            i++;
        }
        return ret;
    }


    @WebMethod
    public String[] listarVideosPublicosDeUsuario(String nick) {
        List<String> vids = iVid.listarVideosPublicosDeUsuario(nick);
        int i = 0;
        String[] ret = new String[vids.size()];
        for (String v : vids) {
            ret[i]=v;
            i++;
        }
        return ret;
    }

    @WebMethod
    public DtElementoWeb[] listarVideosPublicosDeUsuarioWeb(String nick) {
        List<DtElementoWeb> dtElem = iVid.listarVideosPublicosDeUsuarioWeb(nick);
        int i = 0;
        DtElementoWeb[] ret = new DtElementoWeb[dtElem.size()];
        for (DtElementoWeb e : dtElem) {
            ret[i]=e;
            i++;
        }
        return ret;
    }

    @WebMethod
    public DtElementoUsuario[] listarVideosPublicos() {
        List<DtElementoUsuario> dtElem = iVid.listarVideosPublicos();
        int i = 0;
        DtElementoUsuario[] ret = new DtElementoUsuario[dtElem.size()];
        for (DtElementoUsuario e : dtElem) {
            ret[i]=e;
            i++;
        }
        return ret;
    }

    @WebMethod
    public DtElementoWeb[] listarVideosPublicosWeb() {
        List<DtElementoWeb> dtElem = iVid.listarVideosPublicosWeb();
        int i = 0;
        DtElementoWeb[] ret = new DtElementoWeb[dtElem.size()];
        for (DtElementoWeb e : dtElem) {
            ret[i]=e;
            i++;
        }
        return ret;
    }

    @WebMethod
    public void modificarInfoVideo(String nomV, String desc, DtFecha fecha, int dur, String url, boolean publico) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, fecha.getAnio());
        cal.set(Calendar.MONTH, fecha.getMes());
        cal.set(Calendar.DAY_OF_MONTH, fecha.getDia());
        iVid.modificarInfoVideo(nomV, desc, cal, dur, url, publico);
    }

    @WebMethod
    public DtComentario[] obtenerComentariosVideo(String nomVid) {
        List<DtComentario> dtCom = iVid.obtenerComentariosVideo(nomVid);
        int i = 0;
        DtComentario[] ret = new DtComentario[dtCom.size()];
        for (DtComentario c : dtCom) {
            ret[i]=c;
            i++;
        }
        return ret;
    }

    @WebMethod
    public DtVideo obtenerInfoVideo(String nomVid) {
        return iVid.obtenerInfoVideo(nomVid);
    }

    @WebMethod
    public DtValoracion[] obtenerValoracionVideo() {
        List<DtValoracion> dtVal = iVid.obtenerValoracionVideo();
        int i = 0;
        DtValoracion[] ret = new DtValoracion[dtVal.size()];
        for (DtValoracion v : dtVal) {
            ret[i]=v;
            i++;
        }
        return ret;
    }

    @WebMethod
    public void responderComentario(int idCom, String nick, DtFecha fcom, String texto) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, fcom.getAnio());
        cal.set(Calendar.MONTH, fcom.getMes());
        cal.set(Calendar.DAY_OF_MONTH, fcom.getDia());
        iVid.responderComentario(idCom, nick, cal, texto);
    }

    @WebMethod
    public void realizarComentario(String nick, DtFecha fCom, String texto) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, fCom.getAnio());
        cal.set(Calendar.MONTH, fCom.getMes());
        cal.set(Calendar.DAY_OF_MONTH, fCom.getDia());
        iVid.realizarComentario(nick, cal, texto);
    }

    @WebMethod
    public void valorarVideo(String nickVal, boolean val) {
        iVid.valorarVideo(nickVal, val);
    }


    @WebMethod
    public Boolean existeVideo(String nick, String nomV) {
        return iVid.existeVideo(nick, nomV);
    }

    @WebMethod
    public Integer cantidadGusta(){
        return iVid.cantidadGusta();
    }

    @WebMethod
    public Integer cantidadNoGusta(){
        return iVid.cantidadNoGusta();
    }

    @WebMethod
    public void setUsr(String usr) {
        iVid.setUsr(usr);
    }

    @WebMethod
    public void setVid(String vid) {
        iVid.setVid(vid);
    }

    @WebMethod
    public DtElementoWeb[] busqueda(String query, Boolean ordFecha){
        List<DtElementoWeb> dtElem = iVid.busqueda(query, ordFecha);
        int i = 0;
        DtElementoWeb[] ret = new DtElementoWeb[dtElem.size()];
        for (DtElementoWeb e : dtElem) {
            ret[i]=e;
            i++;
        }
        return ret;
    }

    @WebMethod
    public Integer obtenerIdVideo(String usuario, String nomVid){
        return iVid.obtenerIdVideo(usuario, nomVid);
    }

    @WebMethod
    public DtElementoWeb obtenerVideo(Integer idVid){
        return iVid.obtenerVideo(idVid);

    }
}
