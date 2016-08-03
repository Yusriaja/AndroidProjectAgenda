package com.prueba;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDHELPER extends SQLiteOpenHelper {

    private  BDHELPER help;
    private SQLiteDatabase bd;
    Context ctx;
    public BDHELPER(Context context) {
        super(context, "Agenda", null,1);
        ctx=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE Telefonos (IdTelefono integer primary key autoincrement,Numero text not null)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table  Telefonos");
    }


    public void Cerrar(SQLiteDatabase db)
    {

        db.close();

    }

}
