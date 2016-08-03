package com.prueba;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Modificar extends AppCompatActivity implements OnClickListener{

    Button b;
    Button b2;
    EditText n;
    EditText n2;
     private  BDHELPER help;
    private SQLiteDatabase bd;
    DataBaseManager man;
    List<String> DATOS=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        n=(EditText)findViewById(R.id.editText);
        n2=(EditText)findViewById(R.id.editText2);
        b=(Button)findViewById(R.id.Buscar);
        b2=(Button)findViewById(R.id.Modificar);
        b.setOnClickListener(this);
        b2.setOnClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new OnClickListener() {
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


        if(v.getId()==R.id.Buscar) {
            help=new BDHELPER(this);
            bd=help.getWritableDatabase();
            man=new DataBaseManager(this);
            String number=n.getText().toString();
            Cursor c = man.Filtrar(number);
            String lala=c.getString(c.getColumnIndex("Numero"));
            n2.setText(c.getString(c.getColumnIndex("Numero")));
            bd.close();

        }
              if(v.getId()==R.id.Modificar) {

                  help=new BDHELPER(this);
                  bd=help.getWritableDatabase();
                  man=new DataBaseManager(this);
                  man.Modificar(n2.getText().toString(),n.getText().toString());
                  AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                  dlgAlert.setMessage("Se ha Modificado exitosamente!");
                  dlgAlert.setTitle(" Persona");
                  dlgAlert.setPositiveButton("Ok",
                          new DialogInterface.OnClickListener() {
                              public void onClick(DialogInterface dialog, int which) {
                                  //dismiss the dialog
                              }
                          });
                  dlgAlert.setCancelable(true);
                  dlgAlert.create().show();
                  bd.close();
              }
        }catch (Exception e)
          {
              Toast t=Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG);
              t.show();
          }
    }
}
