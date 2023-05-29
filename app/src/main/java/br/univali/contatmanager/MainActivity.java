package br.univali.contatmanager;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import br.univali.contatmanager.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewContatos;
    private Button btnAdicionar;
    private ContatoAdapter adapter;
    private ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
    private DatabaseHelper db;

    void sortPessoas(){
        Collections.sort(this.pessoas, new Comparator<Pessoa>() {
            @Override
            public int compare(Pessoa p1, Pessoa p2) {
                return p1.getName().compareTo(p2.getName());
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activity_main);

        this.btnAdicionar = findViewById(R.id.button);
        recyclerViewContatos = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewContatos.setLayoutManager(layoutManager);

        this.db = new DatabaseHelper(this);
        this.pessoas.addAll(db.getPessoas());

        this.sortPessoas();

        this.adapter = new ContatoAdapter(pessoas);
        recyclerViewContatos.setAdapter(adapter);

        adapter.setOnItemClickListener(new ContatoAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                Pessoa p = pessoas.get(position);
                db.deletePessoa(p.getId());
                pessoas.remove(p);
                adapter.notifyDataSetChanged();
                System.out.println("Delete button clicked at position: " + position + " with id " + p.getId());
            }
        });


        this.btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        db = new DatabaseHelper(MainActivity.this);
        pessoas.clear();
        pessoas.addAll(db.getPessoas());
        adapter.notifyDataSetChanged();
        sortPessoas();
    }
}