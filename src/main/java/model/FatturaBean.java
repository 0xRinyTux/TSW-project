package model;


import java.sql.Date;

public class FatturaBean{
    private String idFattura;

    private String codiceFattura;
    private Date dataAcquisto;
    private String nicknameCliente;
    private String numeroCarta;

    public String getIndirizzoFatturazione() {
        return indirizzoFatturazione;
    }

    public void setIndirizzoFatturazione(String indirizzoFatturazione) {
        this.indirizzoFatturazione = indirizzoFatturazione;
    }

    private String indirizzoFatturazione;

    private String tipoCarta;
    private double totaleFattura;

    public String getNicknameCliente() {
        return nicknameCliente;
    }

    public void setNicknameCliente(String nicknameCliente) {
        this.nicknameCliente = nicknameCliente;
    }

    public String getIdFattura() {
        return idFattura;
    }

    public void setIdFattura(String idFattura) {
        this.idFattura = idFattura;
    }

    public Date getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(Date dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
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

    public double getTotaleFattura() {
        return totaleFattura;
    }

    public String getCodiceFattura() {
        return codiceFattura;
    }

    public void setCodiceFattura(String codiceFattura) {
        this.codiceFattura = codiceFattura;
    }

    public void setTotaleFattura(double totaleFattura) {
        this.totaleFattura = totaleFattura;
    }

    private String chiave;
    private String titolo;

    public String getChiave() {
        return chiave;
    }

    public void setChiave(String chiave) {
        this.chiave = chiave;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
}
