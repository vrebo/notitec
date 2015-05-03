package org.itver.notitecportal.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.itver.notitecdao.dao.mysql.NoticiaMySQLDAOImpl;
import org.itver.notitecentidadbd.entidad.Noticia;

/**
 *
 * @author vrebo
 */
public class Controlador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher despachador;
        String redirect = "";

        switch (request.getServletPath()) {
            case "/IniciarSesion":
                redirect = autentificarUsuario(request, response);
                break;

            case "/RegistraNoticia":
                redirect = registarNoticia(request, response);
                break;

            case "/MuestraNoticias":
                redirect = obtenerNoticias(request, response);
                break;

            case "/EliminarNoticia":
                redirect = eliminarNoticia(request, response);
                break;

            case "/AltaNoticia":
                redirect = "/WEB-INF/vistas/form_alta.jsp";
                break;

            case "/EditarNoticia":
                redirect = obtenerNoticia(request, response);
                break;

            case "/SalvarNoticia":
                redirect = salvarNoticia(request, response);
                break;
        }
        despachador = request.getRequestDispatcher(redirect);
        despachador.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String obtenerNoticias(HttpServletRequest request, HttpServletResponse response) {
        List<Noticia> listaNoticias = new NoticiaMySQLDAOImpl().buscaTodos();
        request.setAttribute("listaNoticias", listaNoticias);
        return "/WEB-INF/vistas/noticias.jsp";
    }

    private String obtenerNoticia(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Noticia noticia;
        long idNoticia = Long.parseLong(request.getParameter("id_noticia"));
        noticia = new NoticiaMySQLDAOImpl().buscaPorId(idNoticia);
        System.out.println("buscando noticia idNoticia = " + idNoticia);
        request.setAttribute("noticia", noticia);
        return "/WEB-INF/vistas/form_edicion.jsp";
    }

    private String salvarNoticia(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Noticia noticia;
        long idNoticia = Long.parseLong(request.getParameter("id_noticia"));
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String contenido = request.getParameter("contenido");
        noticia = new Noticia(idNoticia, titulo, contenido, new Timestamp(new Date().getTime()), autor);
        new NoticiaMySQLDAOImpl().salva(noticia);
        return "/MuestraNoticias";
    }

    private String eliminarNoticia(HttpServletRequest request, HttpServletResponse response) {
        long id = Long.parseLong(request.getParameter("id_noticia"));
        new NoticiaMySQLDAOImpl().elimina(id);
        return "/MuestraNoticias";
    }

    private String autentificarUsuario(HttpServletRequest request, HttpServletResponse response) {
        String usuario = request.getParameter("usuario");
        String contraseña = request.getParameter("contrasenia");
        return "/MuestraNoticias";
    }

    private String registarNoticia(HttpServletRequest request, HttpServletResponse response) {
        Noticia noticia = new Noticia();
        noticia.setTitulo(request.getParameter("titulo"));
        noticia.setAutor(request.getParameter("autor"));
        noticia.setContenido(request.getParameter("contenido"));
        noticia.setFechaPublicacion(new Timestamp(new Date().getTime()));
        new NoticiaMySQLDAOImpl().persiste(noticia);
        return "/MuestraNoticias";
    }
}
