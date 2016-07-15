package br.com.consultafacil.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.consultafacil.adapter.Prestador;
import br.com.consultafacil.adapter.PrestadorAdapter;
import br.com.consultafacil.enums.PRESTADOR;

/**
 * Created by Isaias on 11/07/2016.
 */
public class PrestadorActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestador);

        initToolbar();

        initButtonBack();

        initFields();
    }

    @Override
    protected void initFields() {
        ArrayList<Prestador> list = new ArrayList<>();
        for (PRESTADOR prestador : PRESTADOR.values()) {
            list.add(new Prestador(prestador.getImagem(), prestador.getNome(), prestador.getEndereco()));
        }

        PrestadorAdapter adapterItem = new PrestadorAdapter(this, list);
        ListView listView = (ListView) findViewById(R.id.listPrestador);
        listView.setAdapter(adapterItem);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Prestador item = (Prestador) parent.getAdapter().getItem(position);

                getConsulta().setPrestador(item.getNome());
                getConsulta().setEndereco(item.getEndereco());

                callHorariosDisponiveisActivity();
            }
        });
    }

    private void callHorariosDisponiveisActivity() {
        Intent intent = new Intent(this, HorariosDisponiveisActivity.class);
        startActivity(intent);
    }
}
