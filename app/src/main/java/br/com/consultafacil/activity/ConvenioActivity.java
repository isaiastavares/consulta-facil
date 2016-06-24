package br.com.consultafacil.activity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.consultafacil.R;
import br.com.consultafacil.util.ItemListView;
import br.com.consultafacil.util.ListAdapterItem;

/**
 * Created by Isaias on 13/06/2016.
 */
public class ConvenioActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convenio);

        initToolbar();

        initButtonBack();

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

        ListAdapterItem adapterItem = new ListAdapterItem(this, list);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapterItem);
    }
}
