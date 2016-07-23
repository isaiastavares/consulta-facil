package br.com.consultafacil.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.consultafacil.adapter.Especialidades;
import br.com.consultafacil.adapter.EspecialidadesAdapter;
import br.com.consultafacil.fragment.DatePickerFragment;
import br.com.consultafacil.util.Utilidade;

public class EspecialidadesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_especialidades);

        initToolbar();

        initButtonBack();

        initFields();
    }

    @Override
    protected void initFields() {
        ArrayList<Especialidades> list = new ArrayList<>();
        list.add(new Especialidades(R.mipmap.clinico_geral, "Clínico Geral"));
        list.add(new Especialidades(R.mipmap.dentista, "Dentista"));
        list.add(new Especialidades(R.mipmap.hematologista, "Hematologista"));
        list.add(new Especialidades(R.mipmap.neurologista, "Neurologista"));
        list.add(new Especialidades(R.mipmap.pediatra, "Pediatra"));
        list.add(new Especialidades(R.mipmap.pneumologista, "Pneumologista"));

        EspecialidadesAdapter adapterItem = new EspecialidadesAdapter(this, list);
        ListView listView = (ListView) findViewById(R.id.listEspecialidades);
        listView.setAdapter(adapterItem);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Especialidades item = (Especialidades) parent.getAdapter().getItem(position);

                String especialidade = item.getNome();

                getConsulta().setEspecialidade(especialidade);

                DialogFragment newFragment = new DatePickerFragment() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String data = Utilidade.getDataString(year, monthOfYear, dayOfMonth);
                        getConsulta().setData(data);

                        callPrestadorActivity();
                    }
                };
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
    }

    private void callPrestadorActivity() {
        Intent intent = new Intent(this, PrestadorActivity.class);
        startActivity(intent);
    }
}