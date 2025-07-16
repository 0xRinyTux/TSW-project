package controller;

// Import delle librerie necessarie per la gestione delle servlet e delle richieste HTTP
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet per il controllo della sessione utente.
 * Recupera il nickname dalla sessione o dal cookie e inoltra alla pagina area-utente.jsp.
 * @author Rinaldo Perna
 */
@WebServlet("/ControlloSessione")
public class ControlloSessioneServlet extends HttpServlet {
    /**
     * Gestisce la richiesta GET per il controllo della sessione.
     * 1. Recupera i cookie e la sessione.
     * 2. Se il nickname non è presente in sessione, lo recupera dal cookie.
     * 3. Inoltra la richiesta alla pagina area-utente.jsp.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession(true);
        String nickname = (String) session.getAttribute("username");
        if(nickname == null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase("username")) {
                    nickname = cookie.getValue();
                    session.setAttribute("username", nickname);
                }
            }
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("area-utente.jsp");
        requestDispatcher.forward(request, response);
    }
}
