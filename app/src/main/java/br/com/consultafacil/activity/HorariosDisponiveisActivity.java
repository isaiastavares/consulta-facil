package br.com.consultafacil.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.consultafacil.enums.PRESTADOR;

public class HorariosDisponiveisActivity extends BaseActivity {

    private ImageView imagemPrestador;
    private TextView nomePrestador;
    private TextView enderecoPrestador;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios_disponiveis);

        initToolbar();

        initButtonBack();

        initFields();
    }

    @Override
    protected void initFields() {
        imagemPrestador = (ImageView) findViewById(R.id.imagem_prestador);
        nomePrestador = (TextView) findViewById(R.id.nome_prestador);
        enderecoPrestador = (TextView) findViewById(R.id.endereco_prestador);

        String nomePrestadorText = getConsulta().getPrestador();
        PRESTADOR prestador = PRESTADOR.fromNome(nomePrestadorText);
        imagemPrestador.setImageResource(prestador.getImagem());
        nomePrestador.setText(nomePrestadorText);
        enderecoPrestador.setText(prestador.getEndereco());
    }

    public void salvarHorario(View v) {
        Button button = (Button) findViewById(v.getId());
        getConsulta().setHorario(button.getText().toString());
        callConfirmarAgendamentoActivity();
    }

    private void callConfirmarAgendamentoActivity() {
        Intent intent = new Intent(this, ConfirmarAgendamentoActivity.class);
        startActivity(intent);
    }
}
