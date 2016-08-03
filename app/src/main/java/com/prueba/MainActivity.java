package com.prueba;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView t;
    EditText num;
    Button btn;
    Button btn2;
    Button btn3;
    Button btnMod;
    ListView lista;
    private  BDHELPER help;
    private SQLiteDatabase bd;
    DataBaseManager man;
    List<String> DATOS=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        num=(EditText)findViewById(R.id.numero);
        btn=(Button)findViewById(R.id.agregar);
        btn2=(Button)findViewById(R.id.mostrar);
        btn3=(Button)findViewById(R.id.mostrarF);
        btnMod=(Button)findViewById(R.id.button);
        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btnMod.setOnClickListener(this);
        lista=(ListView)findViewById(R.id.lista);

        BDHELPER help=new BDHELPER(this);
        SQLiteDatabase bd=help.getWritableDatabase();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                 //       .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
       try {
             switch(v.getId())
           {
               case R.id.agregar :
           {


             String nume=num.getText().toString();
             help=new BDHELPER(this);
             bd=help.getWritableDatabase();
             man=new DataBaseManager(this);
             long result= man.Insertar(nume);
             if(result>0)
             {
                 Toast t=Toast.makeText(this,"Insertado",Toast.LENGTH_LONG);
                 t.show();
                 AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                 dlgAlert.setMessage("Se ha agregado exitosamente!");
                 dlgAlert.setTitle("Agregar Persona");
                 dlgAlert.setPositiveButton("Ok",
                         new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int which) {
                                 //dismiss the dialog
                             }
                         });
                 dlgAlert.setCancelable(true);
                 dlgAlert.create().show();
             }
             break;
           }
              case  R.id.mostrar :
              {
                  help=new BDHELPER(this);
                  //bd=help.getWritableDatabase();
                  man=new DataBaseManager(this);
                  String [] Campos=new  String[]{"Numero"};
                  int [] Valor=new  int[] {android.R.id.text1};
                  Cursor c=man.Todos();
                  while (c.moveToNext())
                  {
                      DATOS.add(c.getString(c.getColumnIndex("Numero")));
                  }
                  ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,DATOS);
                  //SimpleCursorAdapter adapter=new SimpleCursorAdapter (this,android.R.layout.two_line_list_item,c,Campos,Valor,0) ;
                  //final TodoCursorAdapter todoAdapter = new TodoCursorAdapter(this, c);

                  lista.setAdapter(adapter);
                  break;
              }

               case  R.id.mostrarF :
               {
                   /*String number=num.getText().toString();
                   String [] Campos=new  String[]{"Numero"};
                   int [] Valor=new  int[] {android.R.id.text1};
                   Cursor c=man.Filtrar(number);
                   SimpleCursorAdapter adapter=new SimpleCursorAdapter (this,android.R.layout.two_line_list_item,c,Campos,Valor,0) ;
                   lista.setAdapter(adapter);*/
                   Intent intent=new Intent(this,MostrarFiltrar.class);
                   startActivity(intent);

                   break ;
               }
               case R.id.button:
               {
                   Intent intent=new Intent(this,Modificar.class);
                   startActivity(intent);

                   break ;

               }
           }

          }catch (Exception e)
          {
              Toast t=Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG);
              t.show();


          }finally {
              help=new BDHELPER(this);
              help.close();
          }

    }
}
