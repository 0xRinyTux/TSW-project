package controller;

// Import delle librerie necessarie per la gestione delle servlet e delle richieste HTTP
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ClienteDAO;

import java.io.IOException;

/**
 * Servlet per il controllo dell'email inserita dall'utente.
 * Verifica se l'email contiene '@' e se è già presente nel database.
 * @author Rinaldo Perna
 */
@WebServlet("/ControlEmail")
public class ControlEmailServlet extends HttpServlet {
    /**
     * Gestisce la richiesta POST per il controllo dell'email.
     * 1. Recupera l'email dalla richiesta.
     * 2. Verifica la presenza di '@' e l'esistenza nel database tramite ClienteDAO.
     * 3. Risponde con "corretto" o "scorretto".
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String email = request.getParameter("email");
        ClienteDAO clienteDAO = new ClienteDAO();

        if(email.contains("@") && clienteDAO.seachEmail(email)){
            response.getWriter().write("corretto");
        }else{
            response.getWriter().write("scorretto");
        }
    }
}
