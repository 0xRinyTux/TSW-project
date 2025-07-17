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
import java.util.ArrayList;

@WebServlet("/SearchItem")
public class SearchItemServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ricerca = request.getParameter("search");
        ArrayList<GiocoBean> items;
        GiocoDAO giocoDAO = new GiocoDAO();
        items = giocoDAO.search(ricerca);
        request.getSession().setAttribute("items", items);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/risultato-ricerca.jsp");
        dispatcher.forward(request, response);

    }

}
