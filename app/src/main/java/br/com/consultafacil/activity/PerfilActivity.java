package br.com.consultafacil.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import br.com.consultafacil.R;

/**
 * Created by Isaias on 14/06/2016.
 */
public class PerfilActivity extends BaseActivity {

    private TextView nome;
    private TextView email;
    private Menu menu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        initToolbar();

        initButtonBack();

        initUser();
    }

    private void initUser() {
        nome = (TextView) findViewById(R.id.text_nome);
        nome.setText("Isaias");

        email = (TextView) findViewById(R.id.text_email);
        email.setText("isaiasengsoft@gmail.com");
    }

    private void initMenu(boolean exibir) {
        menu.findItem(R.id.editar).setVisible(exibir);
        menu.findItem(R.id.alterar_senha).setVisible(exibir);
        menu.findItem(R.id.sair).setVisible(exibir);
    }

    private void editPerfil(boolean editar) {
        nome.setEnabled(editar);
        email.setEnabled(editar);
        initMenu(!editar);
        menu.findItem(R.id.salvar).setVisible(editar);
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
                Toast.makeText(this, "Alterar Senha", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sair:
                signOut();
                break;
            case R.id.salvar:
                editPerfil(false);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
