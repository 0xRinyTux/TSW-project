package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/VerificaLogin")
public class VerificaLoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String indirizzo = "Log.html";
        if(cookies != null) {
            for (Cookie cookie : cookies){
                if (cookie.getName().equalsIgnoreCase("username")) {                    //controlla se l'utente è loggato per vedere il carrello
                    indirizzo = "carrello.jsp";                                                    //se si lo spedisce alla pagina carrello, altrimenti alla pagina di login
                }
            }
        }
        response.sendRedirect(indirizzo);



    }
}
