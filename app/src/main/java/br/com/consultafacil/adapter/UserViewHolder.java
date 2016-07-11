package br.com.consultafacil.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.consultafacil.activity.R;

/**
 * Created by Isaias on 28/06/2016.
 */
public class UserViewHolder extends RecyclerView.ViewHolder {

    public TextView nome;
    public TextView dataNascimento;
    public TextView sexo;
    public TextView telefone;
    public TextView email;

    public UserViewHolder(View itemView) {
        super(itemView);

        nome = (TextView) itemView.findViewById(R.id.text_nome);
        dataNascimento = (TextView) itemView.findViewById(R.id.text_data_nascimento);
        sexo = (TextView) itemView.findViewById(R.id.text_sexo);
        telefone = (TextView) itemView.findViewById(R.id.text_telefone);
        email = (TextView) itemView.findViewById(R.id.text_email);
    }
}
