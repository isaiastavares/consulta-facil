package br.com.consultafacil.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.consultafacil.activity.R;

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

        ImageView imagemLista = (ImageView) convertView.findViewById(R.id.imagem);
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
