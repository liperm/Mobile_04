package br.univali.contatmanager;
import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContatoAdapter extends RecyclerView.Adapter <ContatoAdapter.ContatoViewHolder > {
    private int checkedPosition = 0;
    private String[] contatos;
    ContatoAdapter(String[] contatos) {
        this.contatos = contatos;
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
        viewHolder.nomeView.setText(contatos[i]);
    }
    @Override
    public int getItemCount() {
        return contatos.length;
    }
    public String getSelected() {
        if (checkedPosition != -1) {
            return this.contatos[checkedPosition];
        }
        return null;
    }

    class ContatoViewHolder extends RecyclerView.ViewHolder {
        private TextView nomeView;
        //private TextView telefoneView;

        ContatoViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeView = itemView.findViewById(R.id.tvListNome);
            //telefoneView = itemView.findViewById(R.id.imageView);
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








