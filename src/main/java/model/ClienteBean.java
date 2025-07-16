package model;



import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class ClienteBean {
    private String nickname;
    private String password;
    private String email;


    private String nome;
    private String cognome;
    private String dataDiNascita;

    private String indirizzo;
    private String indirizzoFatturazione;
    private String numeroCarta;
    private String tipoCarta;

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getIndirizzoFatturazione() {
        return indirizzoFatturazione;
    }

    public void setIndirizzoFatturazione(String indirizzoFatturazione) {
        this.indirizzoFatturazione = indirizzoFatturazione;
    }

    public String getNumeroCarta() {
        return numeroCarta;
    }

    public void setNumeroCarta(String numeroCarta) {
        this.numeroCarta = numeroCarta;
    }

    public String getTipoCarta() {
        return tipoCarta;
    }

    public void setTipoCarta(String tipoCarta) {
        this.tipoCarta = tipoCarta;
    }




    public String getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(String dataDiNascita) {
       /* String year = dataDiNascita.substring(0, 4);
        String mounth = dataDiNascita.substring(5, 7);
        String day = dataDiNascita.substring(8, 10);

        this.dataDiNascita = new Date(Integer.parseInt(year), Integer.parseInt(mounth), Integer.parseInt(day)) {
        */
        this.dataDiNascita = dataDiNascita;
    }



    public String getNickname() {
        return nickname;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public static String hashText(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(text.getBytes(StandardCharsets.UTF_8));
            return String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPassword(String password) {
        this.password = hashText(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
