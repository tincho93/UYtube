package filtros;

import publicadores.DtUsuarioWeb;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "RecordarSesion", value = "/*")
public class RecordarSesion implements Filter {

    static final int SESION_CORTA = 100;
    static final int SESION_LARGA = 604800;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        publicadores.CUsuarioPublishService service = new publicadores.CUsuarioPublishService();
        publicadores.CUsuarioPublish port = service.getCUsuarioPublishPort();

        String path = ((HttpServletRequest)req).getServletPath();
        Boolean pathValido = true;
        if(path.length() > 7){
            //pathValido = !path.substring(0,7).equals("/assets") && !path.substring(0,4).equals("/img") && !path.substring(0,3).equals("/v/");
            pathValido = !path.substring(0,7).equals("/assets");
        } else if(path.length() > 4) {
            pathValido = !path.substring(0,4).equals("/img");
        }
        if (!path.equals("/module/iniciarSesion.jsp") && !path.equals("/IniciarSesion") && !path.equals("/CerrarSesion") && pathValido) {

            HttpSession s = ((HttpServletRequest) req).getSession();
            //DtUsuarioWeb usr = (DtUsuarioWeb) s.getAttribute("usuario");
            DtUsuarioWeb usr = null;
            Cookie[] cookies = ((HttpServletRequest) req).getCookies();
            if (cookies != null) {
                Cookie selector = null;
                Cookie rawValidator = null;
                Cookie tipo = null;
                String selectorValue = "";
                String validatorValue = "";
                Boolean recordar = false;
                for (Cookie aCookie : cookies) {
                    if (aCookie.getName().equals("selector")) {
                        selector = aCookie;
                        selectorValue = selector.getValue();
                    } else if (aCookie.getName().equals("validator")) {
                        rawValidator = aCookie;
                        validatorValue = rawValidator.getValue();
                    } else if (aCookie.getName().equals("tipo")) {
                        recordar = aCookie.getValue().equals("long");
                        tipo = aCookie;
                    }
                }
                if (!"".equals(selectorValue) && !"".equals(validatorValue)) {
                    usr = port.obtenerUsuarioConToken(selector.getValue(), rawValidator.getValue());
                    if (usr.getNickname().equals("")) {
                        usr = null;
                    }
                    s.setAttribute("usuario", usr);
                    if (recordar) {
                        selector.setMaxAge(SESION_LARGA);
                        selector.setPath("/");
                        rawValidator.setMaxAge(SESION_LARGA);
                        rawValidator.setPath("/");
                        tipo.setMaxAge(SESION_LARGA);
                        tipo.setPath("/");

                        ((HttpServletResponse) resp).addCookie(selector);
                        ((HttpServletResponse) resp).addCookie(rawValidator);
                        ((HttpServletResponse) resp).addCookie(tipo);
                    } else {

                        selector.setMaxAge(SESION_CORTA);
                        selector.setPath("/");
                        rawValidator.setMaxAge(SESION_CORTA);
                        rawValidator.setPath("/");
                        tipo.setMaxAge(SESION_CORTA);
                        tipo.setPath("/");

                        ((HttpServletResponse) resp).addCookie(selector);
                        ((HttpServletResponse) resp).addCookie(rawValidator);
                        ((HttpServletResponse) resp).addCookie(tipo);
                    }
                } else {
                    s.removeAttribute("usuario");
                }
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
