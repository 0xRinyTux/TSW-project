package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.GiocoBean;
import model.GiocoDAO;

import java.io.IOException;

@WebServlet("/AdminInsert")
public class AdminInsertServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String titoloGioco = request.getParameter("titolo");
        Double prezzo = Double.parseDouble(request.getParameter("prezzo"));
        String descrizione = request.getParameter("descrizione");
        String anno = request.getParameter("anno");
        String console = request.getParameter("console");
        String softwareHouse = request.getParameter("software");
        String url = request.getParameter("url");
        String immagine = request.getParameter("immagine");
        int inSconto;
        if(request.getParameter("scelta").equalsIgnoreCase("si")){
            inSconto = 1;
        }
        else {
            inSconto = 0;
        }
        Double prezzoSconto = Double.parseDouble(request.getParameter("sconto"));
        String genere = request.getParameter("genere");
        int inVendita;
        if(request.getParameter("vendita").equalsIgnoreCase("si")){
            inVendita = 1;
        }
        else {
            inVendita = 0;
        }

        GiocoBean giocoBean = new GiocoBean();
        giocoBean.setNomeGioco(titoloGioco);
        giocoBean.setDescrizione(descrizione);
        giocoBean.setPrezzoGioco(prezzo);
        giocoBean.setInSconto(inSconto);
        giocoBean.setSoftwareHouse(softwareHouse);
        giocoBean.setUrl(url);
        giocoBean.setConsole(console);
        giocoBean.setDataGioco(anno);
        giocoBean.setInVendita(inVendita);
        giocoBean.setGenereGioco(genere);
        giocoBean.setPrezzoScontato(prezzoSconto);
        giocoBean.setImmagine(immagine);

        GiocoDAO giocoDAO = new GiocoDAO();
        giocoDAO.insertIntoDatabase(giocoBean);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin-home.html");
        requestDispatcher.forward(request, response);


    }
}
