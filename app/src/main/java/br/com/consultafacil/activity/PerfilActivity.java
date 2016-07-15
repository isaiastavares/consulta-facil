package br.com.consultafacil.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class PerfilActivity extends BaseActivity {

    private TextView nome;
    private TextView dataNascimento;
    private TextView sexo;
    private TextView telefone;
    private TextView email;
    private Menu menu;

    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showProgressDialog();
        setContentView(R.layout.activity_perfil);

        initToolbar();

        initButtonBack();

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();

        initFields();
        init();
    }

    @Override
    protected void initFields() {
        nome = (TextView) findViewById(R.id.text_nome);
        dataNascimento = (TextView) findViewById(R.id.text_data_nascimento);
        sexo = (TextView) findViewById(R.id.text_sexo);
        telefone = (TextView) findViewById(R.id.text_telefone);
        email = (TextView) findViewById(R.id.text_email);
    }

    private void init() {
        nome.setText(getUser().getNome());
        dataNascimento.setText(getUser().getDataNascimento());
        sexo.setText(getUser().getSexo());
        telefone.setText(getUser().getTelefone());
        email.setText(getUser().getEmail());
        hideProgressDialog();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu, menu);

        initMenu(true);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.editar:
                editPerfil(true);
                break;
            case R.id.alterar_senha:
                Intent intent = new Intent(this, AlterarSenhaActivity.class);
                startActivity(intent);
                break;
            case R.id.sair:
                mAuth.signOut();
                callLoginActivity();
                finish();
                break;
            case R.id.salvar:
                editPerfil(false);
                saveUser();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initMenu(boolean exibir) {
        menu.findItem(R.id.editar).setVisible(exibir);
        menu.findItem(R.id.alterar_senha).setVisible(exibir);
        menu.findItem(R.id.sair).setVisible(exibir);
    }

    private void editPerfil(boolean editar) {
        nome.setEnabled(editar);
        dataNascimento.setEnabled(editar);
        sexo.setEnabled(editar);
        telefone.setEnabled(editar);
        email.setEnabled(editar);
        initMenu(!editar);
        menu.findItem(R.id.salvar).setVisible(editar);
    }

    private boolean validateForm() {
        boolean valid = true;

        if (TextUtils.isEmpty(nome.getText().toString())) {
            nome.setError(getString(R.string.error_field_required));
            valid = false;
        } else {
            nome.setError(null);
        }

        if (TextUtils.isEmpty(dataNascimento.getText().toString())) {
            dataNascimento.setError(getString(R.string.error_field_required));
            valid = false;
        } else {
            dataNascimento.setError(null);
        }

        if (TextUtils.isEmpty(sexo.getText().toString())) {
            sexo.setError(getString(R.string.error_field_required));
            valid = false;
        } else {
            sexo.setError(null);
        }

        if (TextUtils.isEmpty(telefone.getText().toString())) {
            telefone.setError(getString(R.string.error_field_required));
            valid = false;
        } else {
            telefone.setError(null);
        }

        if (TextUtils.isEmpty(email.getText().toString())) {
            email.setError(getString(R.string.error_field_required));
            valid = false;
        } else {
            email.setError(null);
        }

        return valid;
    }

    private void saveUser() {
        if (!validateForm()) {
            return;
        }

        getUser().setNome(nome.getText().toString());
        getUser().setDataNascimento(dataNascimento.getText().toString());
        getUser().setSexo(sexo.getText().toString());
        getUser().setTelefone(telefone.getText().toString());
        getUser().setEmail(email.getText().toString());
        getUser().saveDB();
    }

    private void callLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
