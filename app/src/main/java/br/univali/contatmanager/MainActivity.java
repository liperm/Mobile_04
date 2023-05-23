package br.univali.contatmanager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewContatos;
    private Button btnAdicionar;
    private ContatoAdapter adapterTimes;
    private ArrayList<Contato> contatos = new ArrayList<Contato>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activity_main);

        this.btnAdicionar = findViewById(R.id.button);
        recyclerViewContatos = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewContatos.setLayoutManager(layoutManager);

        ContatoAdapter adapter = new ContatoAdapter(contatos);
        recyclerViewContatos.setAdapter(adapter);

        this.btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nameText = (EditText)findViewById(R.id.editTextName);
                String name = nameText.getText().toString();

                EditText phoneNumberText = (EditText) findViewById(R.id.editTextPhone);
                String phoneNumber = phoneNumberText.getText().toString();

                Contato contato = new Contato(name, phoneNumber, "residencial");
                contatos.add(contato);
                adapter.notifyDataSetChanged();
            }
        });
    }
}