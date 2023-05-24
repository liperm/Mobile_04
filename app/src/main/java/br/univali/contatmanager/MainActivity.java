package br.univali.contatmanager;


import android.content.Context;
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

import br.univali.contatmanager.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewContatos;
    private Button btnAdicionar;
    private ContatoAdapter adapterTimes;
    private ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
    private DatabaseHelper db;

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

        ContatoAdapter adapter = new ContatoAdapter(pessoas);
        recyclerViewContatos.setAdapter(adapter);

        this.btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                EditText nameText = (EditText)findViewById(R.id.editTextName);
//                String name = nameText.getText().toString();
//
//                EditText phoneNumberText = (EditText) findViewById(R.id.editTextPhone);
//                String phoneNumber = phoneNumberText.getText().toString();
//
//                Pessoa pessoa = new Pessoa(name, phoneNumber, "residencial");
//                pessoas.add(pessoa);
//                adapter.notifyDataSetChanged();
            }
        });
    }
}