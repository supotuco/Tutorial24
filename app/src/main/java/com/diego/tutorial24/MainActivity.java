package com.diego.tutorial24;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view_android_version);
        registerForContextMenu(listView);
        String[] android_versions = getResources().getStringArray(R.array.android_version);

        for(String element: android_versions){
            arrayList.add(element);
        }

        adapter = new ArrayAdapter<String>(getBaseContext(),
                                                                R.layout.list_item_row,
                                                                R.id.text_view_row_item,
                                                                arrayList );
        listView.setAdapter(adapter);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, view, menuInfo);
        MenuInflater menuInflater =  getMenuInflater();
        menuInflater.inflate(R.menu.my_contextual_menu, menu);


    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;

        switch (item.getItemId()){
            case R.id.menu_item_delete:
                arrayList.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            case R.id.menu_item_share:
                Toast.makeText(getBaseContext(), arrayList.get(position) + " shared", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_item_help:
                Toast.makeText(getBaseContext(), arrayList.get(position) + " helped", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onContextItemSelected(item);
    }
}
