package filtros;

import publicadores.DtElementoWeb;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "filtroVideo", value = "/v/*")
public class filtroVideo implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String idVideo = ((HttpServletRequest)req).getServletPath().substring(3);
        publicadores.CVideoPublishService serviceVideo = new publicadores.CVideoPublishService();
        publicadores.CVideoPublish portVideo = serviceVideo.getCVideoPublishPort();
        DtElementoWeb video = portVideo.obtenerVideo(Integer.parseInt(idVideo));
        if(!video.getNickname().equals("")) {
            //redirecciono a el video
            ((HttpServletResponse) resp).sendRedirect(((HttpServletRequest) req).getContextPath() + "/module/visualizarVideo.jsp?u=" + video.getNickname() + "&v=" + video.getNombreE());
        } else {
            //redirecciono a invalido
            RequestDispatcher rd = req.getRequestDispatcher("/module/invalido.jsp");
            rd.forward(req,resp);
        }
        //chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
