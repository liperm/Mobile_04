package br.univali.contatmanager;

public class Contato {
    public Contato(String number, String type) {
        this.number = number;
        this.type = type;
    }

    public Contato() {

    }

    private String number;
    private String type;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
