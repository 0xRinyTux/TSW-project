package model;

import java.util.GregorianCalendar;

public class GiocoBean {
    private String nomeGioco;
    private String genereGioco;
    private double prezzoGioco;

    public int getInVendita() {
        return inVendita;
    }

    public void setInVendita(int inVendita) {
        this.inVendita = inVendita;
    }

    private int inVendita;

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public int getInSconto() {
        return inSconto;
    }

    private String console;

    private String descrizione;
    private String softwareHouse;
    private String dataGioco;
    private String url;
    private int inSconto;
    private double prezzoScontato;

    private String immagine;

    private int numeroVendite;

    public int getNumeroVendite() {
        return numeroVendite;
    }

    public void setNumeroVendite(int numeroVendite) {
        this.numeroVendite = numeroVendite;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public double getPrezzoScontato() {return prezzoScontato;}

    public void setPrezzoScontato(double prezzoScontato) {
        this.prezzoScontato = prezzoScontato;
    }

    public String getNomeGioco() {
        return nomeGioco;
    }

    public void setNomeGioco(String nomeGioco) {
        this.nomeGioco = nomeGioco;
    }

    public String getGenereGioco() {
        return genereGioco;
    }

    public void setGenereGioco(String genereGioco) {
        this.genereGioco = genereGioco;
    }

    public double getPrezzoGioco() {
        return prezzoGioco;
    }

    public void setPrezzoGioco(double prezzoGioco) {
        this.prezzoGioco = prezzoGioco;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getSoftwareHouse() {
        return softwareHouse;
    }

    public void setSoftwareHouse(String softwareHouse) {
        this.softwareHouse = softwareHouse;
    }

    public String getDataGioco() {
        return dataGioco;
    }

    public void setDataGioco(String dataGioco) {
        this.dataGioco = dataGioco;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int isInSconto() {
        return inSconto;
    }

    public void setInSconto(int inSconto) {
        this.inSconto = inSconto;
    }
}
