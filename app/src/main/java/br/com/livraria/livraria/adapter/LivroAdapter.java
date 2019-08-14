package br.com.livraria.livraria.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.livraria.livraria.R;
import br.com.livraria.livraria.modelo.Livro;

public class LivroAdapter extends RecyclerView.Adapter<LivroAdapter.MyViewHolder> {

    private Context context;
    private List<Livro> livros;


    public LivroAdapter(Context context, List<Livro> livros) {
        this.context = context;
        this.livros = livros;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_livro, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtNome.setText(livros.get(position).getNome());
        holder.txtPublicacao.setText(livros.get(position).getPublicacao());
        holder.txtAutor.setText(livros.get(position).getAutor().getNome());
    }

    @Override
    public int getItemCount() {
        return livros.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txtNome, txtPublicacao, txtAutor;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtNome = itemView.findViewById(R.id.txtNome);
            txtPublicacao = itemView.findViewById(R.id.txtPublicacao);
            txtAutor = itemView.findViewById(R.id.txtAutor);
        }
    }

}
