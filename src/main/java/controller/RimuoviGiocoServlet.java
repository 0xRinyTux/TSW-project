package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.GiocoBean;
import model.GiocoDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/RimuoviGioco")
public class RimuoviGiocoServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String titolo = request.getParameter("titolo");
        GiocoDAO giocoDAO = new GiocoDAO();
        if(giocoDAO.rimuoviGioco(titolo)){
            GiocoBean titoloRimosso;
            ArrayList<GiocoBean> giocoBeans = (ArrayList<GiocoBean>) getServletContext().getAttribute("giochi");
            for (int i = 0; i < giocoBeans.size(); i++) {
                if((titoloRimosso = giocoBeans.get(i)).getNomeGioco().equalsIgnoreCase(titolo)){
                    giocoBeans.remove(titoloRimosso);
                }
            }
            getServletContext().setAttribute("giochi", giocoBeans);
        }
        response.sendRedirect("controllo-giochi.jsp");
    }
}
