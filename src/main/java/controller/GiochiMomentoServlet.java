package controller;

// Import delle librerie necessarie per la gestione delle servlet e delle richieste HTTP
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.GiocoBean;
import model.GiocoDAO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet che gestisce la visualizzazione dei giochi più venduti del momento.
 * Recupera la lista dei giochi più venduti dal database e la inserisce nella sessione utente.
 * Reindirizza l'utente alla pagina di ricerca con i risultati aggiornati.
 * @author Rinaldo Perna
 */
@WebServlet("/GiochiMomento")
public class GiochiMomentoServlet extends HttpServlet {
    /**
     * Gestisce la richiesta GET per mostrare i giochi più venduti.
     * 1. Recupera la lista dei giochi più venduti tramite GiocoDAO.
     * 2. Inserisce la lista nella sessione utente.
     * 3. Inoltra la richiesta alla pagina dei risultati.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GiocoDAO giocoDAO = new GiocoDAO();
        ArrayList<GiocoBean> giocoBeans = giocoDAO.retrieveMostSellerGames();
        HttpSession session = request.getSession();
        if(session == null){
            session = request.getSession(true);
        }
        session.setAttribute("items", giocoBeans);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/results/risultato-ricerca.jsp");
        requestDispatcher.forward(request, response);
    }
}
