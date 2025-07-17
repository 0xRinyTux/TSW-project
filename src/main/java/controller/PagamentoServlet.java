package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;


import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

@WebServlet("/Pagamento")
public class PagamentoServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        long millis=System.currentTimeMillis();

        Date date = new Date(millis);

        FatturaBean fatturaBean =  new FatturaBean();
        fatturaBean.setDataAcquisto(date);
        String nickname = (String) session.getAttribute("username");

        fatturaBean.setNicknameCliente(nickname);
        fatturaBean.setTipoCarta(request.getParameter("tipo_carta"));
        fatturaBean.setNumeroCarta(request.getParameter("numero_carta"));
        fatturaBean.setIndirizzoFatturazione(request.getParameter("fatturazione"));

        FatturaDAO fatturaDAO = new FatturaDAO();
        fatturaDAO.insertIntoDatabase(fatturaBean);

        int idFattura = fatturaDAO.getLastIDFattura(nickname);
        request.setAttribute("id_fattura", idFattura);

        CollocamentoBean collocamentoBean = new CollocamentoBean();
        ArrayList<GiocoBean> giocoBeans = (ArrayList<GiocoBean>) session.getAttribute("carrello");
        CollocamentoDAO collocamentoDAO = new CollocamentoDAO();

        for(GiocoBean giocoBean : giocoBeans) {
            collocamentoBean.setIdGioco(giocoBean.getNomeGioco());
            collocamentoBean.setIdFattura(idFattura);
            collocamentoDAO.insetIntoDatabase(collocamentoBean);
        }

        session.removeAttribute("carrello");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Key");
        requestDispatcher.include(request, response);





    }

}
