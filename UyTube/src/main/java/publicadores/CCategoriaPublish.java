package publicadores;

import datatypes.DtElementoUsuario;
import datatypes.DtElementoWeb;
import interfaces.CFactory;
import interfaces.ICategoria;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;


import java.util.List;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class CCategoriaPublish {
    private CFactory catFactory;
    private ICategoria iCat;
    private Endpoint endpoint;

    public CCategoriaPublish() {
        this.catFactory = CFactory.getInstancia();
        this.iCat = this.catFactory.getICategoria();
    }

    @WebMethod(exclude = true)
    public void publicar() {
        endpoint = Endpoint.publish("http://" + "127.0.0.1" + ":" + "16000" + "/Categoria", this);
        System.out.println("WebService de ControladorCategoria publicado en: "+ "http://" + "127.0.0.1" + ":" + "16000" + "/Categoria" + "?wsdl");
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    //MÉTODOS QUE VAMOS A PUBLICAR
    @WebMethod
    public void altaCategoria(String nomC) {
        iCat.altaCategoria(nomC);
    }

    @WebMethod
    public DtElementoUsuario[] listarElemCategoria(String nomC) {
        List<DtElementoUsuario> dtUsr = iCat.listarElemCategoria(nomC);
        int i = 0;
        DtElementoUsuario[] ret = new DtElementoUsuario[dtUsr.size()];
        for (DtElementoUsuario u : dtUsr) {
            ret[i]=u;
            i++;
        }
        return ret;
    }

    @WebMethod
    public DtElementoWeb[] listarVideosPublicosCategoria(String nomC) {
        List<DtElementoWeb> dtElem = iCat.listarVideosPublicosCategoria(nomC);
        int i = 0;
        DtElementoWeb[] ret = new DtElementoWeb[dtElem.size()];
        for (DtElementoWeb e : dtElem) {
            ret[i]=e;
            i++;
        }
        return ret;
    }

    @WebMethod
    public DtElementoWeb[] listarListasPublicasCategoria(String nomC) {
        List<DtElementoWeb> dtElem = iCat.listarListasPublicasCategoria(nomC);
        int i = 0;
        DtElementoWeb[] ret = new DtElementoWeb[dtElem.size()];
        for (DtElementoWeb e : dtElem) {
            ret[i]=e;
            i++;
        }
        return ret;
    }

    @WebMethod
    public String[] listarCategorias() {
        List<String> cats = iCat.listarCategorias();
        int i = 0;
        String[] ret = new String[cats.size()];
        for (String c : cats) {
            ret[i]=c;
            i++;
        }
        return ret;
    }

    @WebMethod
    public boolean existeCategoria(String nombre) {
        return iCat.existeCategoria(nombre);
    }

}
