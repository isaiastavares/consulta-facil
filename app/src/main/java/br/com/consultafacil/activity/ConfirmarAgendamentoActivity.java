package br.com.consultafacil.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.consultafacil.enums.PRESTADOR;

public class ConfirmarAgendamentoActivity extends BaseActivity {

    private TextView data;
    private TextView endereco_prestador;
    private ImageView imagem_prestador;
    private TextView nome_prestador;
    private TextView especialidade;
    private TextView paciente;
    private TextView telefone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_agendamento);

        initToolbar();

        initButtonBack();

        initFields();
    }

    @Override
    protected void initFields() {
        data = (TextView) findViewById(R.id.data);
        endereco_prestador = (TextView) findViewById(R.id.endereco_prestador);
        imagem_prestador = (ImageView) findViewById(R.id.imagem_prestador);
        nome_prestador = (TextView) findViewById(R.id.nome_prestador);
        especialidade = (TextView) findViewById(R.id.especialidade);
        paciente = (TextView) findViewById(R.id.nome_paciente);
        telefone = (TextView) findViewById(R.id.telefone);

        String dataString = getConsulta().getData() + " às " + getConsulta().getHorario();
        data.setText(dataString);
        PRESTADOR prestador = PRESTADOR.fromNome(getConsulta().getPrestador());
        endereco_prestador.setText(prestador.getEndereco());
        imagem_prestador.setImageResource(prestador.getImagem());
        nome_prestador.setText(prestador.getNome());
        especialidade.setText(getConsulta().getEspecialidade());

        paciente.setText(getUser().getNome());
        telefone.setText(getUser().getTelefone());
    }

    public void confirmarAgendamento(View v) {
        getConsulta().saveDB();
        callMeusAgendamentosActivity();
    }

    private void callMeusAgendamentosActivity() {
        Intent intent = new Intent(this, MeusAgendamentosActivity.class);
        startActivity(intent);
    }
}
