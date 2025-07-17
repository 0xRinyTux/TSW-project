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
import java.util.ArrayList;

@WebServlet("/Key")
public class KeyServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int idFattura = (int) request.getAttribute("id_fattura");

        KeyDAO keyDAO = new KeyDAO();
        CollocamentoDAO collocamentoDAO = new CollocamentoDAO();

        ArrayList<CollocamentoBean> collocamentoBeans = collocamentoDAO.getCollocamentoByFattura(idFattura);
        ArrayList<KeyBean> keyBeans = new ArrayList<>();

        for(CollocamentoBean collocamentoBean : collocamentoBeans) {
            KeyBean keyBean = new KeyBean();
            keyBean.setFattura(idFattura);
            keyBean.setNomeGioco(collocamentoBean.getIdGioco());
            keyBean.setKey(collocamentoBean.getIdGioco());
            keyDAO.insertIntoDatabase(keyBean);
            keyBean.setIdChiave(keyDAO.retrieveIDKeyByGiocoAndFattura(idFattura, collocamentoBean.getIdGioco()));
            keyBeans.add(keyBean);
        }

        request.setAttribute("key", keyBeans);


        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/results/fattura.jsp");
        requestDispatcher.forward(request, response);


    }
}
