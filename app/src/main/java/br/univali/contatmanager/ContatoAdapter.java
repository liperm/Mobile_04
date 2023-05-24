package br.univali.contatmanager;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContatoAdapter extends RecyclerView.Adapter <ContatoAdapter.ContatoViewHolder > {
    private int checkedPosition = 0;
    private ArrayList<Pessoa> pessoas;
    ContatoAdapter(ArrayList<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }
    private String getAbsoluteAdapterPosition() {
        return null;
    }

    @NonNull
    @Override
    public ContatoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ContatoViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ContatoViewHolder viewHolder, int i) {
        viewHolder.nameTextView.setText(pessoas.get(i).getName());
    }
    @Override
    public int getItemCount() {
        return pessoas.size();
    }
    public Pessoa getSelected() {
        if (checkedPosition != -1) {
            return this.pessoas.get(checkedPosition);
        }
        return null;
    }

    class ContatoViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;

        ContatoViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);

        }
//        void bind(final String time) {
//            Log.w("getBindingAdapterPosition()", getAbsoluteAdapterPosition() + "");
//            if (checkedPosition == -1) {
//                telefoneView.setVisibility(View.GONE);
//            } else {
//                if (checkedPosition == Integer.parseInt(getAbsoluteAdapterPosition())) {
//                    telefoneView.setVisibility(View.VISIBLE);
//                } else {
//                    telefoneView.setVisibility(View.GONE);
//                }
//            }
//            nomeView.setText(time);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    telefoneView.setVisibility(View.VISIBLE);
//                    if (checkedPosition != Integer.parseInt(getAbsoluteAdapterPosition())) {
//                        notifyItemChanged(checkedPosition);
//                        checkedPosition = Integer.parseInt(getAbsoluteAdapterPosition());
//                    }
//                }
//            });
//        }
    }
}








