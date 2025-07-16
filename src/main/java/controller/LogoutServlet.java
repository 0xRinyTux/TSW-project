package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookie = request.getCookies();
        if(cookie != null) {
            for (int i = 0; i < cookie.length; i++) {
                cookie[i].setMaxAge(0);
                response.addCookie(cookie[i]);
            }
            HttpSession session = request.getSession(false);

            if(session!=null){
                ArrayList<String> names = Collections.list(session.getAttributeNames());
                for(String name : names){
                    session.removeAttribute(name);
                }
            }

        }

        response.sendRedirect("index.html");
    }
}
