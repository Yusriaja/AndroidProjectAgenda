package com.prueba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by PC on 01/08/2016.
 */
public class DataBaseManager extends SQLiteOpenHelper{

    private BDHELPER helper;
    private SQLiteDatabase db;


    public DataBaseManager(Context context) {
        super(context, "Agenda", null,1);
        helper=new BDHELPER(context);
        db=helper.getWritableDatabase();
    }

    public ContentValues getValores(String numero)
    {
        ContentValues V=new ContentValues();
        V.put("Numero",numero);

        return  V;
    }
    public long Insertar(String numero)
    {
        SQLiteDatabase db = getWritableDatabase();
        long result=db.insert("Telefonos", null, getValores(numero));
        db.close();
        return result;
    }
    public Cursor Todos()
    {  SQLiteDatabase db = getWritableDatabase();
       String [] Columnas =new  String[]{"Numero"};
       Cursor c=db.query("Telefonos", Columnas, null, null, null, null, null);

        return c;

    }
    public Cursor Filtrar(String nume)
    {
        SQLiteDatabase db = getWritableDatabase();
    String[] Columnas = new String[]{"Numero"};
    Cursor c = db.query("Telefonos", Columnas, "Numero" + "=?", new String[]{nume}, null, null, null, null);
       // String query = ("SELECT * FROM Telefonos  WHERE Numero =  " + nume + ";");
       // Cursor c = db.rawQuery(query, null);
    if (c != null) {
        c.moveToFirst();

    }
        db.close();
        return  c;
    }
    public long Modificar(String numero,String  numeroViejo)
    {

        //db.execSQL("UPDATE Telefonos SET Numero="+numero+" where Numero="+numeroViejo+"");
        SQLiteDatabase db = getWritableDatabase();
        long result=db.update("Telefonos", getValores(numero), "Numero" + "= ?", new String[] { String.valueOf(numeroViejo)});
        db.close();
        return result;

    }
    public void borrarPersona(String numero){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM Telefonos  WHERE Numero" + " = " + numero + ";");
        db.close();
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
