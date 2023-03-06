package com.rene.gremlins_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rene.gremlins_app.db.DbEmpleados;
import com.rene.gremlins_app.entidades.Empleados;

public class activity_ver extends AppCompatActivity {

    EditText txtNombre, txtTelefono, txtDireccion, txtCorreo, txtCedula;
    Spinner spnCargo, spnTurno;
    Button btnGuardar;
    FloatingActionButton fabEditar;
    Empleados empleado;
    int id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre = findViewById(R.id.txtNombre);
        txtCedula = findViewById(R.id.txtCedula);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtCorreo = findViewById(R.id.txtCorreo);
        spnCargo = findViewById(R.id.spnCargo);
        spnTurno = findViewById(R.id.spnTurno);
        btnGuardar = findViewById(R.id.btnGuardar);
        fabEditar = findViewById(R.id.fabEditar);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();

            if(extras == null){
                id = Integer.parseInt(null);
            }else{
                id = extras.getInt("ID");
            }
        }else{
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbEmpleados dbEmpleados = new DbEmpleados(activity_ver.this);
        empleado = dbEmpleados.verEmpleado(id);

        if(empleado != null){
            txtCedula.setText(empleado.getCedula());
            txtNombre.setText(empleado.getNombre());
            txtTelefono.setText(empleado.getTelefono());
            txtCorreo.setText(empleado.getTelefono());
            txtDireccion.setText(empleado.getDireccion());
            btnGuardar.setVisibility(View.INVISIBLE);
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtCedula.setInputType(InputType.TYPE_NULL);
            txtTelefono.setInputType(InputType.TYPE_NULL);
            txtDireccion.setInputType(InputType.TYPE_NULL);
            txtCorreo.setInputType(InputType.TYPE_NULL);

        }
        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_ver.this, activity_editar.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });
    }
}