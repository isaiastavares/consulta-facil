package br.com.consultafacil.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.consultafacil.adapter.Prestador;
import br.com.consultafacil.adapter.PrestadorAdapter;

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
        list.add(new Prestador(R.drawable.ic_action_add, "Prestador 1", "Rua 1"));
        list.add(new Prestador(R.drawable.ic_action_add, "Prestador 2", "Rua 2"));
        list.add(new Prestador(R.drawable.ic_action_add, "Prestador 3", "Rua 3"));
        list.add(new Prestador(R.drawable.ic_action_add, "Prestador 4", "Rua 4"));
        list.add(new Prestador(R.drawable.ic_action_add, "Prestador 5", "Rua 5"));

        PrestadorAdapter adapterItem = new PrestadorAdapter(this, list);
        ListView listView = (ListView) findViewById(R.id.listPrestador);
        listView.setAdapter(adapterItem);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Prestador item = (Prestador) parent.getAdapter().getItem(position);

                getConsulta().setPrestador(item.getNome());
                getConsulta().setEndereco(item.getEndereco());

                showToast(getConsulta().getPrestador() + " " + getConsulta().getEndereco());
            }
        });
    }
}
