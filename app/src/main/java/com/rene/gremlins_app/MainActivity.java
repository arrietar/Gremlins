package com.rene.gremlins_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rene.gremlins_app.adaptadores.ListaEmpleadosAdapter;
import com.rene.gremlins_app.db.DbEmpleados;
import com.rene.gremlins_app.db.DbHelper;
import com.rene.gremlins_app.entidades.Empleados;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView listaEmpleados;
    ArrayList<Empleados> listaArrayEmpleados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaEmpleados = findViewById(R.id.listaEmpleados);
        listaEmpleados.setLayoutManager(new LinearLayoutManager(this));

        DbEmpleados dbEmpleados = new DbEmpleados(MainActivity.this);

        listaArrayEmpleados = new ArrayList<>();

        ListaEmpleadosAdapter adapter = new ListaEmpleadosAdapter(dbEmpleados.mostrarEmpleados());
        listaEmpleados.setAdapter(adapter);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this, activity_empleado.class);
        startActivity(intent);
    }
}