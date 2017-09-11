package com.hxl.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private SearchView sv;
    private ListView listView;
    public String[] s = {"ss", "dd", "ff"};
    ArrayList<String> datas = new ArrayList<>();

    String text;
    private Myadapter myadapter;
    private EditText textView;
    private TextView textView1;
    private SearchView.SearchAutoComplete textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sv = (SearchView) findViewById(R.id.sv);
//        listView = (ListView) findViewById(R.id.listview);
//        listView.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, s));
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        rv.setLayoutManager(layoutManager);

        myadapter = new Myadapter(MainActivity.this);
        textView2 = ( SearchView.SearchAutoComplete) sv.findViewById(R.id.search_src_text);

        rv.setAdapter(myadapter);
        myadapter.setOnItemClickListener(new Myadapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this,datas.get(position),Toast.LENGTH_SHORT).show();
                textView2.setText(datas.get(position));
            }
        });

//        listView.setTextFilterEnabled(true);
        sv.setIconifiedByDefault(false);
        sv.setOnQueryTextListener(this);
        sv.setSubmitButtonEnabled(true);
        sv.setQueryHint("查找");

        sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datas.add(0,text);
                myadapter.clear();
                myadapter.add(datas);
                myadapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "内容" + text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Toast.makeText(MainActivity.this, "内容" + query, Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        this.text = newText;
//        if (!TextUtils.isEmpty(newText)) {
//            listView.clearTextFilter();
//        } else {
//            listView.setFilterText(newText);
//        }
        return true;
    }
}
