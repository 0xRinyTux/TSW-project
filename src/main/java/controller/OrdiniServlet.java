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

        //cerca gli id delle fatture di un cliente in base al nickname
        FatturaBean fatturaBean = new FatturaBean();
        FatturaDAO fatturaDAO = new FatturaDAO();
        ArrayList<Integer> idsFattura = fatturaDAO.retriveIDsFatturaByNickname(nickname);

        if(idsFattura != null) {

            //Con l' id fattura cerca tutti i giochi comprati dall'utente
            CollocamentoBean collocamentoBean = new CollocamentoBean();
            CollocamentoDAO collocamentoDAO = new CollocamentoDAO();
            ArrayList<CollocamentoBean> collocamentoBeans = collocamentoDAO.retrieveIDsGiocoByFatture(idsFattura);

            //Con l'id del gioco e della fattura ritira tutte le key appartenenti all'utente dei titoli acquistati
            KeyDAO keyDAO = new KeyDAO();
            ArrayList<KeyBean> keyBeans = keyDAO.retrieveIDKeyByGiocchiAndFatture(collocamentoBeans);


            request.setAttribute("keyBeans", keyBeans);

        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/results/ordini.jsp");
        requestDispatcher.forward(request, response);


    }
}
