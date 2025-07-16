package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ClienteBean;
import model.ClienteDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.GregorianCalendar;

@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String indirizzo = "/WEB-INF/results";
        RequestDispatcher dispatcher;
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        if (email.contains("@")) {
            String password = request.getParameter("password");
            String username = request.getParameter("nickname");
            String dataDiNascita = request.getParameter("data-di-nascita");

            ClienteBean clienteBean = new ClienteBean();
            clienteBean.setNickname(username);
            clienteBean.setEmail(email);
            clienteBean.setPassword(password);
            clienteBean.setCognome(cognome);
            clienteBean.setNome(nome);
            clienteBean.setDataDiNascita(dataDiNascita);
            ClienteDAO clienteDAO = new ClienteDAO();
            try {
                if (clienteDAO.insertIntoDatabase(clienteBean)) {
                    dispatcher = request.getRequestDispatcher(indirizzo + "/registrazionesuccesso.jsp");
                } else {
                    dispatcher = request.getRequestDispatcher(indirizzo + "/registrazionefallita.jsp");
                }
                dispatcher.forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }


        }
    }}
