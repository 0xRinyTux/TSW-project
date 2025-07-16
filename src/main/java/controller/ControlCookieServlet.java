package controller;

// Import delle librerie necessarie per la gestione delle servlet e delle richieste HTTP
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

/**
 * Servlet per il controllo dei cookie dell'utente.
 * Se trova il cookie 'username', imposta la sessione e reindirizza alla home, altrimenti alla pagina di login.
 * @author Rinaldo Perna
 */
@WebServlet("/ControlCookie")
public class ControlCookieServlet extends HttpServlet {

    /**
     * Gestisce la richiesta GET per il controllo dei cookie.
     * 1. Recupera i cookie dalla richiesta.
     * 2. Se trova il cookie 'username', imposta la sessione e reindirizza alla home.
     * 3. Altrimenti reindirizza alla pagina di login.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String indirizzo = "Log.html";
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equalsIgnoreCase("username")) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("username", cookies[i].getValue());
                    indirizzo = "index.html";
                }
            }
        }
        response.sendRedirect(indirizzo);
    }
}
