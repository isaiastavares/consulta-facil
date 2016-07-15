package br.com.consultafacil.adapter;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.consultafacil.activity.R;
import br.com.consultafacil.fragment.DatePickerFragment;

public class EspecialidadesAdapter extends ArrayAdapter<Especialidades> {

    private Context context;
    private ArrayList<Especialidades> lista;

    public EspecialidadesAdapter(Context context, ArrayList<Especialidades> lista) {
        super(context, 0, lista);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Especialidades itemPosicao = this.lista.get(position);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.especialidades, null);

        ImageView imagem = (ImageView) convertView.findViewById(R.id.imagem);
        imagem.setImageResource(itemPosicao.getImagem());

        TextView nome = (TextView) convertView.findViewById(R.id.nome);
        nome.setText(itemPosicao.getNome());

        return convertView;
    }
}
