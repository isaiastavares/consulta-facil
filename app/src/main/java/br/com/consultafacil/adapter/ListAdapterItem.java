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

/**
 * Created by Isaias on 11/06/2016.
 */
public class ListAdapterItem extends ArrayAdapter<ItemListView> {

    private Context context;
    private ArrayList<ItemListView> lista;

    public ListAdapterItem(Context context, ArrayList<ItemListView> lista) {
        super(context, 0, lista);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemListView itemPosicao = this.lista.get(position);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.item, null);

        ImageView imagemLista = (ImageView) convertView.findViewById(R.id.imagem_lista);
        imagemLista.setImageResource(itemPosicao.getImagem());

        TextView nome = (TextView) convertView.findViewById(R.id.nome);
        nome.setText(itemPosicao.getNome());

        TextView descricao = (TextView) convertView.findViewById(R.id.descricao);
        if (itemPosicao.getDescricao() != null) {
            descricao.setText(itemPosicao.getDescricao());
        }

        return convertView;
    }
}
