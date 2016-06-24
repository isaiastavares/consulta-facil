package br.com.consultafacil.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.consultafacil.R;
import br.com.consultafacil.util.ItemListView;
import br.com.consultafacil.util.ListAdapterItem;

/**
 * Created by Isaias on 11/06/2016.
 */
public class MeusAgendamentosActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_agendamentos);

        initToolbar();

        ArrayList<ItemListView> list = new ArrayList<ItemListView>();
        /*ItemListView a = new ItemListView(R.drawable.ic_action_add, "Item a", "Descrição do Item a");
        ItemListView b = new ItemListView(R.drawable.ic_action_add, "Item b", "Descrição do Item b");
        ItemListView c = new ItemListView(R.drawable.ic_action_add, "Item c", "Descrição do Item c");
        ItemListView d = new ItemListView(R.drawable.ic_action_add, "Item d", "Descrição do Item d");
        ItemListView e = new ItemListView(R.drawable.ic_action_add, "Item e", "Descrição do Item e");
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);*/

        if (!list.isEmpty()) {
            findViewById(R.id.listAgendamentos).setVisibility(View.VISIBLE);
            findViewById(R.id.nenhum_agendamento).setVisibility(View.GONE);
        } else {
            findViewById(R.id.listAgendamentos).setVisibility(View.GONE);
            findViewById(R.id.nenhum_agendamento).setVisibility(View.VISIBLE);
        }

        ListAdapterItem adapterItem = new ListAdapterItem(this, list);
        ListView listView = (ListView) findViewById(R.id.listAgendamentos);
        listView.setAdapter(adapterItem);
    }

    public void escolherConvenio(View v) {
        Intent intent = new Intent(this, ConvenioActivity.class);
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
                Intent intent = new Intent(this, PerfilActivity.class);
                startActivity(intent);
                break;
            case R.id.sair:
                signOut();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
