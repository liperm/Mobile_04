package br.univali.contatmanager;

import java.util.ArrayList;

public class Pessoa {
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long id;

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
        this.contatos = new ArrayList<Contato>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
