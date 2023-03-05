package com.rene.gremlins_app.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbEmpleados extends DbHelper{

    Context context;

    public DbEmpleados(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertEmpleado(String cedula, String nombre, String telefono, String direccion, String correo, String tipoEmpleado, String turno){
        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("cedula", cedula);
            values.put("nombre", nombre);
            values.put("telefono", telefono);
            values.put("direccion", direccion);
            values.put("correo", correo);
            values.put("tipoEmpleado", tipoEmpleado);
            values.put("turno", turno);

            id = db.insert(TABLE_EMPLEADOS, null, values);

        }catch(Exception e){
            e.toString();
        }
        return id;
    }
}
