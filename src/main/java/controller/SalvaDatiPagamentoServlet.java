package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ClienteDAO;

import java.io.IOException;

@WebServlet("/SalvaDatiPagamento")
public class SalvaDatiPagamentoServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nickname = request.getParameter("nomeCliente");
        String indirizzo = request.getParameter("indirizzo");
        String tipoCarta = request.getParameter("tipoCarta");
        String numeroCarta = request.getParameter("numeroCarta");

        ClienteDAO clienteDAO = new ClienteDAO();
        if(!clienteDAO.updatePaymentData(nickname, indirizzo, tipoCarta, numeroCarta)){
            response.getWriter().write("successo");
        }else {
            response.getWriter().write("fallito");
        }

    }
}
