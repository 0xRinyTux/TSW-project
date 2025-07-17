package controller;

// Import delle librerie necessarie per la gestione delle servlet e delle richieste HTTP
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ClienteBean;
import model.ClienteDAO;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet per il controllo della password dell'utente durante la modifica dei dati.
 * Verifica che la password inserita corrisponda a quella presente nel database.
 * @author Rinaldo Perna
 */
@WebServlet("/ControllaPassword")
public class ControllaPasswordModifica extends HttpServlet {
    /**
     * Gestisce la richiesta POST per il controllo della password.
     * 1. Recupera la password e il nickname dalla sessione o dalla richiesta.
     * 2. Recupera l'email se non presente.
     * 3. Verifica le credenziali tramite ClienteDAO.
     * 4. Risponde con "corretto" o "scorretto".
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String passwordCorrente = request.getParameter("password");
        String nickname = (String) request.getSession().getAttribute("username");
        String email = request.getParameter("email");
        ClienteDAO clienteDAO = new ClienteDAO();
        if(email == null) {
            ClienteBean clienteBean = clienteDAO.retrieveClientByNickname(nickname);
            email = clienteBean.getEmail();
        }
        try {

            nickname = clienteDAO.matchCredential(email, passwordCorrente);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            if(nickname == null){

                response.getWriter().write("scorretto");
            }else {
                response.getWriter().write("corretto");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }



    }
}
