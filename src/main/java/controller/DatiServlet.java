package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.GiocoBean;
import model.GiocoDAO;

import java.io.IOException;

@WebServlet("/Dati")
public class DatiServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String titolo = request.getParameter("titolo");
        GiocoDAO giocoDAO = new GiocoDAO();
        GiocoBean giocoBean = giocoDAO.doRetrieveGameByName(titolo);
        request.setAttribute("giocoBean", giocoBean);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/results/modifica-gioco.jsp");
        requestDispatcher.forward(request, response);
    }
}
