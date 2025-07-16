package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ClienteBean;
import model.ClienteDAO;

import java.io.IOException;

@WebServlet("/ModificaUtente")
public class ModificaUtenteServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nickname = (String) request.getSession().getAttribute("username");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String password = request.getParameter("password_nuova");
        String passwordCorrente = request.getParameter("password");
        String numeroCarta = request.getParameter("numero_carta");
        String indirizzoFatturazione = request.getParameter("indirizzo_fatturazione");
        String tipoCarta = request.getParameter("tipo_carta");

        ClienteBean clienteBean = new ClienteBean();
        clienteBean.setNickname(nickname);
        clienteBean.setNome(nome);
        clienteBean.setEmail(email);
        clienteBean.setCognome(cognome);
        clienteBean.setNumeroCarta(numeroCarta);
        clienteBean.setIndirizzoFatturazione(indirizzoFatturazione);
        clienteBean.setTipoCarta(tipoCarta);

        ClienteDAO clienteDAO = new ClienteDAO();
        if(password == ""){
            clienteDAO.updateClienteWithoutPassword(clienteBean);
        }else {
            clienteBean.setPassword(password);
            clienteDAO.updateCliente(clienteBean);
        }
        response.sendRedirect("modifica-successo.html");


    }

}
