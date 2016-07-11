package br.com.consultafacil.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import br.com.consultafacil.adapter.UserRecyclerAdapter;
import br.com.consultafacil.adapter.UserViewHolder;
import br.com.consultafacil.domain.User;
import br.com.consultafacil.domain.util.LibraryClass;

/**
 * Created by Isaias on 14/06/2016.
 */
public class PerfilActivity extends BaseActivity {

    private TextView nome;
    private TextView dataNascimento;
    private TextView sexo;
    private TextView telefone;
    private TextView email;
    private Menu menu;

    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private UserRecyclerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        initToolbar();

        initButtonBack();

        mAuth = FirebaseAuth.getInstance();
        databaseReference = LibraryClass.getFirebaseDatabase();
    }

    @Override
    protected void onResume() {
        super.onResume();

        init();
        initFields();
    }

    private void init() {
        RecyclerView perfilUsuario = (RecyclerView) findViewById(R.id.perfilUsuario);
        perfilUsuario.setHasFixedSize(true);
        perfilUsuario.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserRecyclerAdapter(
                User.class,
                R.layout.perfil,
                UserViewHolder.class,
                databaseReference.child(User.USUARIOS));
        perfilUsuario.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.cleanup();
    }

    @Override
    protected void initFields() {
        nome = (TextView) findViewById(R.id.text_nome);
        dataNascimento = (TextView) findViewById(R.id.text_data_nascimento);
        sexo = (TextView) findViewById(R.id.text_sexo);
        telefone = (TextView) findViewById(R.id.text_telefone);
        email = (TextView) findViewById(R.id.text_email);
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

    private void callLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
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
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
