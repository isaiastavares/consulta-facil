package br.com.consultafacil.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Date;

import br.com.consultafacil.util.Utilidade;

public class ConvenioActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convenio);

        initToolbar();

        initButtonBack();

        initFields();
    }

    @Override
    protected void initFields() {

        String[] convenios = new String[] {"Allianz Saúde", "Amil", "América Saúde", "Banco do Brasil - PAS",
                "Bradesco Saúde", "CAASP", "Caixa Seguros Saúde", "CONAB", "Itaú Saúde", "Mapfre Saúde", "Unimed",
                "Uniodonto", ""};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, convenios);
        ListView listView = (ListView) findViewById(R.id.listConvenios);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getAdapter().getItem(position);

                String data = String.valueOf(new Date().getTime());
                getConsulta().setId(Utilidade.gerarHashMD5(data));
                getConsulta().setIdUsuario(FirebaseAuth.getInstance().getCurrentUser().getUid());
                getConsulta().setConvenio(item.toString());

                callEspecialidadesActivity();
            }
        });
    }

    private void callEspecialidadesActivity() {
        Intent intent = new Intent(this, EspecialidadesActivity.class);
        startActivity(intent);
    }
}
