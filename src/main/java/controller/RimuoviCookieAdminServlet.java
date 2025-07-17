package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/RimuoviCookieAdmin")
public class RimuoviCookieAdminServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(int i = 0; i < cookies.length; i++){
                cookies[i].setMaxAge(0);
                response.addCookie(cookies[i]);
            }
        }
        response.sendRedirect("index.html");

    }
}
