package com.rene.gremlins_app.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.rene.gremlins_app.entidades.Empleados;

import java.util.ArrayList;

public class DbEmpleados extends DbHelper{

    Context context;

    public DbEmpleados(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertEmpleado(String cedula, String nombre, String telefono, String direccion, String correo, String cargo, String turno){
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
            values.put("tipoEmpleado", cargo);
            values.put("turno", turno);

            id = db.insert(TABLE_EMPLEADOS, null, values);

        }catch(Exception e){
            e.toString();
        }
        return id;
    }

    public ArrayList<Empleados> mostrarEmpleados(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Empleados> listaEmpleados = new ArrayList<>();
        Empleados empleado = null;
        Cursor cursorEmpleado = null;

        cursorEmpleado = db.rawQuery("SELECT * FROM " + TABLE_EMPLEADOS, null);

        if(cursorEmpleado.moveToFirst()){
            do{
                empleado = new Empleados();
                empleado.setId_Empleado(cursorEmpleado.getInt(0));
                empleado.setCedula(cursorEmpleado.getString(1));
                empleado.setNombre(cursorEmpleado.getString(2));
                empleado.setTelefono(cursorEmpleado.getString(3));
                empleado.setCorreo(cursorEmpleado.getString(4));
                empleado.setDireccion(cursorEmpleado.getString(5));
                empleado.setCargo(cursorEmpleado.getString(6));
                empleado.setTurno(cursorEmpleado.getString(7));

                listaEmpleados.add(empleado);
            }while (cursorEmpleado.moveToNext());
        }
        cursorEmpleado.close();

        return listaEmpleados;
    }

    public Empleados verEmpleado(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Empleados empleado = null;
        Cursor cursorEmpleado = null;

        cursorEmpleado = db.rawQuery("SELECT * FROM " + TABLE_EMPLEADOS + " WHERE Id_Empleado = " + id + " LIMIT 1", null);

        if(cursorEmpleado.moveToFirst()){
                empleado = new Empleados();
                empleado.setId_Empleado(cursorEmpleado.getInt(0));
                empleado.setCedula(cursorEmpleado.getString(1));
                empleado.setNombre(cursorEmpleado.getString(2));
                empleado.setTelefono(cursorEmpleado.getString(3));
                empleado.setCorreo(cursorEmpleado.getString(4));
                empleado.setDireccion(cursorEmpleado.getString(5));
                empleado.setCargo(cursorEmpleado.getString(6));
                empleado.setTurno(cursorEmpleado.getString(7));
        }
        cursorEmpleado.close();

        return empleado;
    }

    public boolean editarEmpleado(int id, String cedula, String nombre, String telefono, String direccion, String correo, String cargo, String turno){
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_EMPLEADOS + " SET cedula = '"+ cedula +"', nombre = '"+ nombre +"', telefono = '"+ telefono +"'," +
                    "direccion = '"+ direccion +"', correo = '"+ correo +"', cargo = '"+ cargo +"'," +
                    "turno = '"+ turno +"' WHERE Id_Empleado = '"+ id +"' ");
             correcto = true;

        }catch(Exception e){
            e.toString();
            correcto = false;
        }finally {
            db.close();
        }
        return correcto;
    }
}
