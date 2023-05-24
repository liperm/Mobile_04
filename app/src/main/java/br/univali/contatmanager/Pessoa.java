package br.univali.contatmanager;

import java.util.ArrayList;

public class Pessoa {
    private String name;

    public ArrayList<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(ArrayList<Contato> contatos) {
        this.contatos = contatos;
    }

    public void addContato(Contato contato) {
        this.contatos.add(contato);
    }

    private ArrayList<Contato> contatos;

    public Pessoa(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
