package br.univali.contatmanager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewContatos;
    private Button btnAdicionar;
    private ContatoAdapter adapterTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnAdicionar = findViewById(R.id.button);
        recyclerViewContatos = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewContatos.setLayoutManager(layoutManager);

        this.btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w("sla", "sla");
            }
        });

        String[] data = new String[]{"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
        ContatoAdapter adapter = new ContatoAdapter(data);
        recyclerViewContatos.setAdapter(adapter);
    }
}