package controller;

// Import delle librerie necessarie per la gestione delle servlet e delle richieste HTTP
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.ClienteBean;
import model.ClienteDAO;

import java.io.IOException;

/**
 * Servlet per gestire la pagina di acquisto.
 * Recupera il cliente loggato tramite sessione o cookie e lo passa alla pagina di pagamento.
 * @author Rinaldo Perna
 */
@WebServlet("/Acquisto")
public class AcquistoServlet extends HttpServlet {
    /**
     * Gestisce la richiesta GET per la pagina di acquisto.
     * 1. Recupera il nickname dalla sessione o dal cookie.
     * 2. Recupera i dati del cliente dal database.
     * 3. Inserisce il cliente nella sessione.
     * 4. Inoltra la richiesta alla pagina di pagamento.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String nickname = (String) session.getAttribute("username");
        if(nickname == null){
            Cookie[] cookies = request.getCookies();
            for(Cookie cookie : cookies){
                if(cookie.getName().equalsIgnoreCase("username")){
                    nickname = cookie.getValue();
                    session.setAttribute("username", nickname);
                }
            }
        }
        ClienteDAO clienteDAO = new ClienteDAO();
        ClienteBean clienteBean = clienteDAO.retrieveClientByNickname(nickname);
        String indirizzo = "/WEB-INF/results/pagamento.jsp";
        session.setAttribute("cliente", clienteBean);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(indirizzo);
        requestDispatcher.forward(request, response);
    }
}
