package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "CerrarSesion", value = "/CerrarSesion")
public class CerrarSesion extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        publicadores.CUsuarioPublishService service = new publicadores.CUsuarioPublishService();
        publicadores.CUsuarioPublish port = service.getCUsuarioPublishPort();

        HttpSession s = request.getSession();
        s.removeAttribute("usuario");

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Cookie selector = null;
            Cookie rawValidator = null;
            Cookie tipo = null;
            String selectorValue = "";
            String validatorValue = "";
            for (Cookie aCookie : cookies) {
                if (aCookie.getName().equals("selector")) {
                    selector = aCookie;
                    selectorValue = selector.getValue();
                } else if (aCookie.getName().equals("validator")) {
                    rawValidator = aCookie;
                    validatorValue = rawValidator.getValue();
                } else if (aCookie.getName().equals("tipo")) {
                    tipo = aCookie;
                }
            }
            if (!"".equals(selectorValue) && !"".equals(validatorValue)) {
                selector.setMaxAge(0);
                selector.setPath("/");
                rawValidator.setMaxAge(0);
                rawValidator.setPath("/");
                tipo.setMaxAge(0);
                tipo.setPath("/");

                response.addCookie(selector);
                response.addCookie(rawValidator);
                response.addCookie(tipo);
            }
        }
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        publicadores.CUsuarioPublishService service = new publicadores.CUsuarioPublishService();
        publicadores.CUsuarioPublish port = service.getCUsuarioPublishPort();

        HttpSession s = request.getSession();
        s.removeAttribute("usuario");

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Cookie selector = null;
            Cookie rawValidator = null;
            Cookie tipo = null;
            String selectorValue = "";
            String validatorValue = "";
            for (Cookie aCookie : cookies) {
                if (aCookie.getName().equals("selector")) {
                    selector = aCookie;
                    selectorValue = selector.getValue();
                } else if (aCookie.getName().equals("validator")) {
                    rawValidator = aCookie;
                    validatorValue = rawValidator.getValue();
                } else if (aCookie.getName().equals("tipo")) {
                    tipo = aCookie;
                }
            }
            if (!"".equals(selectorValue) && !"".equals(validatorValue)) {
                selector.setMaxAge(0);
                selector.setPath("/");
                rawValidator.setMaxAge(0);
                rawValidator.setPath("/");
                tipo.setMaxAge(0);
                tipo.setPath("/");

                response.addCookie(selector);
                response.addCookie(rawValidator);
                response.addCookie(tipo);
            }
        }
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }
}
