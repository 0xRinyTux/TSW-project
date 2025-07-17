package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/Ordini")
public class OrdiniServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nickname = (String) request.getSession().getAttribute("username");
        System.out.println("[DEBUG] Ordini - Retrieved nickname from session: " + nickname);

        ArrayList<FatturaBean> fattureWithKeysAndTitles = new ArrayList<>();
        if (nickname != null) {
            FatturaDAO fatturaDAO = new FatturaDAO();
            // Usa il nuovo metodo per recuperare fatture con chiavi e titoli
            ArrayList<FatturaBean> result = fatturaDAO.retrieveFattureWithKeysAndTitles(nickname);
            if (result != null) {
                fattureWithKeysAndTitles = result;
            }
            System.out.println("[DEBUG] Ordini - Found fatture with keys and titles: " + fattureWithKeysAndTitles.size());
        }
        
        System.out.println("[DEBUG] Ordini - Final fatture size: " + fattureWithKeysAndTitles.size());
        request.setAttribute("fattureWithKeys", fattureWithKeysAndTitles);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/results/ordini.jsp");
        requestDispatcher.forward(request, response);
    }
}
