package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.GiocoBean;
import model.GiocoDAO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet per la gestione del carrello.
 * Permette di aggiungere giochi al carrello dell'utente.
 * @author Rinaldo Perna
 */
@WebServlet("/Carrello")
public class CarrelloServlet extends HttpServlet {

    /**
     * Gestisce la richiesta GET per aggiungere un gioco al carrello.
     * 1. Recupera il titolo del gioco dalla richiesta.
     * 2. Recupera il gioco dal database tramite GiocoDAO.
     * 3. Se il carrello non esiste in sessione, lo crea e aggiunge il gioco.
     * 4. Altrimenti aggiunge il gioco alla lista esistente.
     * 5. Inoltra la richiesta alla pagina dei risultati.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String name = request.getParameter("titolo");

        GiocoDAO giocoDAO = new GiocoDAO();
        GiocoBean giocoBean = giocoDAO.doRetrieveByImage(name);
        if(session.getAttribute("carrello") == null){                           //controlla se la sessione ha i giochi. Se non li ha, setta l'attributo e aggiunge il nuovo gioco, altrimenti ne aggiunge un altro alla lista.
            ArrayList<GiocoBean> giochiNelCarrello = new ArrayList<>();
            giochiNelCarrello.add(giocoBean);
            session.setAttribute("carrello", giochiNelCarrello);
        }else {
            Object carrelloObj = session.getAttribute("carrello");
            if (carrelloObj instanceof ArrayList<?>) {
                @SuppressWarnings("unchecked")
                ArrayList<GiocoBean> giochiNelCarrello = (ArrayList<GiocoBean>) carrelloObj;
                giochiNelCarrello.add(giocoBean);
                session.setAttribute("carrello", giochiNelCarrello);
            }
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/results/risultato-ricerca.jsp");
        requestDispatcher.forward(request, response);
    }
}
