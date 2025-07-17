package model;

import java.util.Random;

public class KeyBean {
    private int idChiave;
    private String nomeGioco;
    private int fattura;
    private String key;

    public int getIdChiave() {
        return idChiave;
    }

    public void setIdChiave(int idChiave) {
        this.idChiave = idChiave;
    }

    public String getNomeGioco() {
        return nomeGioco;
    }

    public void setNomeGioco(String nomeGioco) {
        this.nomeGioco = nomeGioco;
    }

    public int getFattura() {
        return fattura;
    }

    public void setFattura(int fattura) {
        this.fattura = fattura;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        int length = 40;
        Random random = new Random();
        char[] text = new char[length];
        for (int i = 0; i <length; i++)
        {
            text[i] = key.charAt(random.nextInt(key.length()));
        }
        this.key = new String(text);
    }
}
