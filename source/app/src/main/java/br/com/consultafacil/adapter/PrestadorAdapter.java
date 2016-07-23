package br.com.consultafacil.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.consultafacil.activity.R;

public class PrestadorAdapter extends ArrayAdapter<Prestador> {

    private Context context;
    private ArrayList<Prestador> lista;

    public PrestadorAdapter(Context context, ArrayList<Prestador> lista) {
        super(context, 0, lista);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Prestador itemPosicao = this.lista.get(position);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.prestador, null);

        ImageView imagem = (ImageView) convertView.findViewById(R.id.imagem);
        imagem.setImageResource(itemPosicao.getImagem());

        TextView nome = (TextView) convertView.findViewById(R.id.nome);
        nome.setText(itemPosicao.getNome());

        TextView endereco = (TextView) convertView.findViewById(R.id.endereco);
        endereco.setText(itemPosicao.getEndereco());

        return convertView;
    }
}
