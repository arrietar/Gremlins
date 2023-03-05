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
    public static final String TABLE_LINEA_PRODUCTOS = "t_lineaproductos";
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
                "cargo TEXT," +
                "turno CHAR)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PROVEEDORES + "(" +
                "Id_Prov INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "numeroIdProv VARCHAR(10) NOT NULL," +
                "nombreProv TEXT NOT NULL," +
                "telefonoProv VARCHAR(13) NOT NULL," +
                "correoProv TEXT NOT NULL," +
                "direccionProv TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PRODUCTOS + "(" +
                "Id_Prod INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "precioCompra DECIMAL(10,2) NOT NULL," +
                "precioVenta DECIMAL(10,2) NOT NULL," +
                "Id_Prov INTEGER NOT NULL," +
                "FOREIGN KEY (Id_Prov) REFERENCES t_proveedores(Id_Prov))");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_LINEA_PRODUCTOS + "(" +
                "Id_Linea INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "cantidad INTEGER NOT NULL," +
                "Id_Prod INTEGER NOT NULL," +
                "FOREIGN KEY (Id_Prod) REFERENCES t_productos(Id_Prod))");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_COMPRADORES + "(" +
                "Id_Comprador INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "numeroIdCompr VARCHAR(11) NOT NULL," +
                "nombreCompr TEXT NOT NULL," +
                "telefonoCompr VARCHAR(13) NOT NULL," +
                "correoCompr TEXT NOT NULL," +
                "direccionCompr TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_TRANSPORTADORES + "(" +
                "Id_Transp INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nit VARCHAR(11) NOT NULL," +
                "razonSocial TEXT NOT NULL," +
                "telefonoTransp VARCHAR(13) NOT NULL," +
                "correoTransp TEXT NOT NULL," +
                "direccionTransp TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PAQUETES + "(" +
                "Id_Paquete INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "fechaCompra DATETIME NOT NULL," +
                "fechaEnvio DATETIME NOT NULL," +
                "valorPagado DECIMAL(10,2) NOT NULL," +
                "dirEntrega TEXT NOT NULL," +
                "CONSTRAINT fk_linea FOREIGN KEY (Id_Linea) REFERENCES t_lineaproductos(Id_Linea)" +
                "ON DELETE CASCADE ON UPDATE CASCADE," +
                "CONSTRAINT fk_comprador FOREIGN KEY (Id_Comprador) REFERENCES t_compradores(Id_Comprador)" +
                "ON DELETE CASCADE ON UPDATE CASCADE," +
                "CONSTRAINT fk_transportador FOREIGN KEY (Id_Transp) REFERENCES t_transportadores(Id_Transp)" +
                "ON DELETE CASCADE ON UPDATE CASCADE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_EMPLEADOS);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_PROVEEDORES);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_PRODUCTOS);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_LINEA_PRODUCTOS);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_COMPRADORES);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_TRANSPORTADORES);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_PAQUETES);
        onCreate(sqLiteDatabase);

    }
}
