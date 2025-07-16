package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ClienteDAO;

import java.io.IOException;

@WebServlet("/ControlUsername")
public class ControlUsernameServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nickname = request.getParameter("nickname");
        ClienteDAO clienteDAO = new ClienteDAO();

        if(clienteDAO.seachNickname(nickname)){
            response.getWriter().write("esiste");
        }else {
            response.getWriter().write("non esiste");
        }
    }
}
