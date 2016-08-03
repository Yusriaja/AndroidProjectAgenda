package com.prueba;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

public class MostrarFiltrar extends AppCompatActivity implements View.OnClickListener {
    EditText filt;
    ImageButton b;
    ListView list;
    private  BDHELPER help;
    private SQLiteDatabase bd;
    DataBaseManager man;
    List<String> DATOS=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_filtrar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        filt=(EditText)findViewById(R.id.numerot);
        b=(ImageButton)findViewById(R.id.clic);
        b.setOnClickListener(this);
        list=(ListView)findViewById(R.id.list);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        try {
                switch (v.getId())
                {
                    case(R.id.clic):
                    {
                        help=new BDHELPER(this);
                        bd=help.getWritableDatabase();
                        man=new DataBaseManager(this);
                        String number=filt.getText().toString();
                        Cursor c=man.Filtrar(number);
                        while (c.moveToNext())
                        {
                            DATOS.add(c.getString(c.getColumnIndex("Numero")));
                        }
                        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,DATOS);

                        list.setAdapter(adapter);

                    }
                }
        }catch (Exception e)
        {

        }
    }
}
