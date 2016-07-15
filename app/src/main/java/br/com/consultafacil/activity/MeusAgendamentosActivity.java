package br.com.consultafacil.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.com.consultafacil.adapter.ItemListView;
import br.com.consultafacil.adapter.ListAdapterItem;
import br.com.consultafacil.domain.Consulta;
import br.com.consultafacil.domain.User;
import br.com.consultafacil.domain.util.LibraryClass;
import br.com.consultafacil.enums.PRESTADOR;

public class MeusAgendamentosActivity extends BaseActivity {

    private ListAdapterItem adapterItem;
    private ArrayList<ItemListView> list;
    private DatabaseReference databaseReference;
    private ValueEventListener valueEventListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_agendamentos);

        initToolbar();

        String idUsuario = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = LibraryClass.getFirebaseDatabase()
                .child(User.USUARIOS)
                .child(idUsuario)
                .child(Consulta.CONSULTAS);

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Consulta consulta = d.getValue(Consulta.class);
                    PRESTADOR prestador = PRESTADOR.fromNome(consulta.getPrestador());
                    String descricao = prestador.getEndereco() + " - " + consulta.getData() + " às " + consulta.getHorario();
                    list.add(new ItemListView(prestador.getImagem(),
                            prestador.getNome(),
                            descricao));
                }
                initFields();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        databaseReference.addValueEventListener(valueEventListener);

        initFields();
    }

    @Override
    protected void initFields() {
        if (list == null || list.isEmpty()) {
            findViewById(R.id.layoutAgendamentos).setVisibility(View.GONE);
            findViewById(R.id.nenhum_agendamento).setVisibility(View.VISIBLE);
        } else {
            adapterItem = new ListAdapterItem(this, list);
            ListView listView = (ListView) findViewById(R.id.listAgendamentos);
            listView.setAdapter(adapterItem);
            findViewById(R.id.layoutAgendamentos).setVisibility(View.VISIBLE);
            findViewById(R.id.nenhum_agendamento).setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseReference.removeEventListener(valueEventListener);
        if (adapterItem != null) {
            adapterItem.clear();
        }
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
                FirebaseAuth.getInstance().signOut();
                callLoginActivity();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
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
}
