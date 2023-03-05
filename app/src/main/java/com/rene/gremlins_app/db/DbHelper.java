package com.rene.gremlins_app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    //Control de version de base de datos
    private static final int DATABASE_VERSION = 1;
    //Nombre de base de datos
    private static final String DATABASE_NAME = "gremlins.db";

    public static final String TABLE_EMPLEADOS = "t_empleados";
    public static final String TABLE_PROVEEDORES = "t_proveedores";
    public static final String TABLE_PRODUCTOS = "t_productos";
    public static final String TABLE_LINEA_PRODUCTOS = "t_lineaProductos";
    public static final String TABLE_PAQUETES= "t_paquetes";
    public static final String TABLE_COMPRADORES= "t_compradores";
    public static final String TABLE_TRANSPORTADORES= "t_transportadores";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_EMPLEADOS + "(" +
                "Id_Empleado INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "cedula VARCHAR(10) NOT NULL," +
                "nombre TEXT NOT NULL," +
                "telefono VARCHAR(13) NOT NULL," +
                "correo TEXT NOT NULL," +
                "direccion TEXT," +
                "tipoEmpleado TEXT," +
                "turno CHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_EMPLEADOS);
        onCreate(sqLiteDatabase);

    }
}
