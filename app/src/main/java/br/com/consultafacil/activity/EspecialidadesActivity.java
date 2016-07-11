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
        list.add(new Especialidades(R.drawable.ic_action_add, "Ginecologista"));
        list.add(new Especialidades(R.drawable.ic_action_add, "Dermatologia"));
        list.add(new Especialidades(R.drawable.ic_action_add, "Oftalmologia"));
        list.add(new Especialidades(R.drawable.ic_action_add, "Clínica Geral"));
        list.add(new Especialidades(R.drawable.ic_action_add, "Cardiologia"));
        list.add(new Especialidades(R.drawable.ic_action_add, "Ortopedia"));
        list.add(new Especialidades(R.drawable.ic_action_add, "Psiquiatria"));
        list.add(new Especialidades(R.drawable.ic_action_add, "Urologia"));
        list.add(new Especialidades(R.drawable.ic_action_add, "Endocrinologia"));
        list.add(new Especialidades(R.drawable.ic_action_add, "Otorrinolaringologia"));
        list.add(new Especialidades(R.drawable.ic_action_add, "Gastroenterologia"));
        list.add(new Especialidades(R.drawable.ic_action_add, "Neurologia"));
        list.add(new Especialidades(R.drawable.ic_action_add, "Odontologia"));
        list.add(new Especialidades(R.drawable.ic_action_add, "Cirurgia Vascular"));
        list.add(new Especialidades(R.drawable.ic_action_add, "Reumatologia"));
        list.add(new Especialidades(R.drawable.ic_action_add, "Pediatria"));

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