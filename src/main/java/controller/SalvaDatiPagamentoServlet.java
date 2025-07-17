package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import jakarta.servlet.ServletException;

@WebServlet("/SalvaDatiPagamento")
public class SalvaDatiPagamentoServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String nickname = (String) session.getAttribute("username");
        System.out.println("[DEBUG] Processing payment for user: " + nickname);
        
        String indirizzo = request.getParameter("fatturazione");
        String tipoCarta = request.getParameter("tipo_carta");
        String numeroCarta = request.getParameter("numero_carta");

        // Update customer payment data
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.updatePaymentData(nickname, indirizzo, tipoCarta, numeroCarta);

        // Record each cart item and generate key entries
        @SuppressWarnings("unchecked")
        List<GiocoBean> cart = (List<GiocoBean>) session.getAttribute("carrello");
        System.out.println("[DEBUG] Cart size: " + (cart != null ? cart.size() : "null"));
        
        // Collect generated keys for confirmation
        List<KeyBean> generatedKeys = new ArrayList<>();
        FatturaDAO fatturaDAO = new FatturaDAO();
        int idFattura = 0;
        
        if (cart != null && !cart.isEmpty()) {
            CollocamentoDAO collocamentoDAO = new CollocamentoDAO();
            KeyDAO keyDAO = new KeyDAO();
            
            // Per ogni gioco nel carrello, crea una fattura separata con chiave e titolo
            for (GiocoBean gioco : cart) {
                System.out.println("[DEBUG] Processing game: " + gioco.getNomeGioco());
                
                // Crea una nuova fattura per ogni gioco con chiave e titolo
                FatturaBean fatturaGioco = new FatturaBean();
                fatturaGioco.setDataAcquisto(Date.valueOf(LocalDate.now()));
                fatturaGioco.setNicknameCliente(nickname);
                fatturaGioco.setIndirizzoFatturazione(indirizzo);
                fatturaGioco.setTipoCarta(tipoCarta);
                fatturaGioco.setNumeroCarta(numeroCarta);
                
                // Genera la chiave per questo gioco
                String generatedKey = UUID.randomUUID().toString().substring(0, 20);
                fatturaGioco.setChiave(generatedKey);
                fatturaGioco.setTitolo(gioco.getNomeGioco());
                
                // Salva la fattura con chiave e titolo
                boolean savedGioco = fatturaDAO.insertIntoDatabase(fatturaGioco);
                System.out.println("[DEBUG] Game invoice saved: " + savedGioco + " for game: " + gioco.getNomeGioco());
                
                if (savedGioco) {
                    // Ottieni l'ID della fattura appena creata
                    int fatturaGiocoId = fatturaDAO.getLastIDFattura(nickname);
                    
                    // Imposta idFattura per la prima fattura creata (per la pagina di conferma)
                    if (idFattura == 0) {
                        idFattura = fatturaGiocoId;
                    }
                    
                    // Save collocation record
                    CollocamentoBean coll = new CollocamentoBean();
                    coll.setIdGioco(gioco.getNomeGioco());
                    coll.setIdFattura(fatturaGiocoId);
                    collocamentoDAO.insetIntoDatabase(coll);
                    System.out.println("[DEBUG] Saved collocation for: " + gioco.getNomeGioco());
                    
                    // Generate and save license key
                    KeyBean keyBean = new KeyBean();
                    keyBean.setNomeGioco(gioco.getNomeGioco());
                    keyBean.setFattura(fatturaGiocoId);
                    keyBean.setKey(generatedKey);
                    keyDAO.insertIntoDatabase(keyBean);
                    System.out.println("[DEBUG] Generated key for " + gioco.getNomeGioco() + ": " + generatedKey);
                    generatedKeys.add(keyBean);
                }
            }
        }
        
        // Se non ci sono giochi nel carrello o non sono state create fatture
        if (cart == null || cart.isEmpty() || idFattura == 0) {
            request.setAttribute("error", true);
            request.getRequestDispatcher("/WEB-INF/results/pagamento.jsp").forward(request, response);
            return;
        }
        
        System.out.println("[DEBUG] Total generated keys: " + generatedKeys.size());
        // Clear cart
        session.removeAttribute("carrello");
        // Forward to confirmation page with generated keys
        request.setAttribute("generatedKeys", generatedKeys);
        request.setAttribute("idFattura", idFattura);
        request.getRequestDispatcher("/WEB-INF/results/conferma.jsp").forward(request, response);
        return;
    }
}
