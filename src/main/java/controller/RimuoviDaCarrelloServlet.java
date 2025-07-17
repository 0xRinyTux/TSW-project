package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ClienteDAO;
import model.GiocoBean;
import model.GiocoDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/RimuoviDaCarrello")
public class RimuoviDaCarrelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String titolo = request.getParameter("titolo");
        GiocoDAO giocoDAO = new GiocoDAO();
        GiocoBean giocoBean = giocoDAO.doRetrieveByImage(titolo);
        ArrayList<GiocoBean> giocoBeans = (ArrayList<GiocoBean>) session.getAttribute("carrello");
        for(int i = 0; i < giocoBeans.size(); i++) {
            if (giocoBeans.get(i).getNomeGioco().equalsIgnoreCase(giocoBean.getNomeGioco())) {
                giocoBeans.remove(i);
                session.setAttribute("carrello", giocoBeans);

            }
        }
        response.sendRedirect("carrello.jsp");

    }
}
