package br.com.consultafacil.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import br.com.consultafacil.adapter.ItemListView;
import br.com.consultafacil.adapter.ListAdapterItem;

/**
 * Created by Isaias on 11/06/2016.
 */
public class MeusAgendamentosActivity extends BaseActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private ListAdapterItem adapterItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_agendamentos);

        initToolbar();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() == null) {
                    Intent intent = new Intent(MeusAgendamentosActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        mAuth = FirebaseAuth.getInstance();
        mAuth.addAuthStateListener(authStateListener);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void initFields() {
        ArrayList<ItemListView> list = new ArrayList<ItemListView>();
        ItemListView a = new ItemListView(R.drawable.ic_action_add, "Item a", "Descrição do Item a");
        ItemListView b = new ItemListView(R.drawable.ic_action_add, "Item b", "Descrição do Item b");
        ItemListView c = new ItemListView(R.drawable.ic_action_add, "Item c", "Descrição do Item c");
        ItemListView d = new ItemListView(R.drawable.ic_action_add, "Item d", "Descrição do Item d");
        ItemListView e = new ItemListView(R.drawable.ic_action_add, "Item e", "Descrição do Item e");
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);

        if (!list.isEmpty()) {
            findViewById(R.id.layoutAgendamentos).setVisibility(View.VISIBLE);
            findViewById(R.id.nenhum_agendamento).setVisibility(View.GONE);
        } else {
            findViewById(R.id.layoutAgendamentos).setVisibility(View.GONE);
            findViewById(R.id.nenhum_agendamento).setVisibility(View.VISIBLE);
        }

        adapterItem = new ListAdapterItem(this, list);
        ListView listView = (ListView) findViewById(R.id.listAgendamentos);
        listView.setAdapter(adapterItem);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adapterItem != null) {
            adapterItem.clear();
        }

        if (authStateListener != null) {
            mAuth.removeAuthStateListener(authStateListener);
        }
    }

    public void callConvenioActivity(View v) {
        Intent intent = new Intent(this, ConvenioActivity.class);
        startActivity(intent);
    }

    private void callPerfilActivity() {
        Intent intent = new Intent(this, PerfilActivity.class);
        startActivity(intent);
    }

    private void callLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        menu.findItem(R.id.perfil).setVisible(true);
        menu.findItem(R.id.sair).setVisible(true);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.perfil:
                callPerfilActivity();
                break;
            case R.id.sair:
                mAuth.signOut();
                callLoginActivity();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
