package controller;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.ClienteDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LoginUtente")
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    {
        RequestDispatcher dispatcher;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        ClienteDAO clienteDAO = new ClienteDAO();
       try {
            String username;
            if ((username = clienteDAO.matchCredential(email, password)) != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("username", username);                                 //setta i cookie
                Cookie cookie = new Cookie("username", username);
                if(username.equalsIgnoreCase("admin")){                          //controlla se l'utente che ha fatto il login è l'admin di sistema
                    dispatcher = request.getRequestDispatcher("controllo-giochi.jsp");
                    cookie.setMaxAge(-1);
                }else {                                                                      //altrimenti è un utente normale
                    dispatcher = request.getRequestDispatcher("index.html");
                    cookie.setMaxAge(60*60*24);
                }
                cookie.setSecure(true);
                response.addCookie(cookie);
                dispatcher.forward(request, response);

            }


        }
        catch (SQLException | ClassNotFoundException | IOException | ServletException e){
            e.printStackTrace();
        }
    }

}
