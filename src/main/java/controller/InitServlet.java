package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import model.GiocoBean;
import model.GiocoDAO;

import java.util.ArrayList;

@WebServlet(name = "InitServlet", urlPatterns = "/InitServlet", loadOnStartup = 0)
public class InitServlet extends HttpServlet {
    public void init() throws ServletException {
        GiocoDAO giocoDAO = new GiocoDAO();
        ArrayList<GiocoBean> giochi = giocoDAO.doRetrieveAll();
        getServletContext().setAttribute("giochi", giochi);
        super.init();
    }
}
