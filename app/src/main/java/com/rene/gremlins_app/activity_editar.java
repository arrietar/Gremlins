package com.rene.gremlins_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rene.gremlins_app.db.DbEmpleados;
import com.rene.gremlins_app.entidades.Empleados;

public class activity_editar extends AppCompatActivity {

    EditText txtNombre, txtTelefono, txtDireccion, txtCorreo, txtCedula;
    Spinner spnCargo, spnTurno;
    Button btnGuardar;
    boolean correcto = false;

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

        DbEmpleados dbEmpleados = new DbEmpleados(activity_editar.this);
        empleado = dbEmpleados.verEmpleado(id);

        if(empleado != null){
            txtCedula.setText(empleado.getCedula());
            txtNombre.setText(empleado.getNombre());
            txtTelefono.setText(empleado.getTelefono());
            txtCorreo.setText(empleado.getTelefono());
            txtDireccion.setText(empleado.getDireccion());

        }
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtNombre.getText().equals("")&&!txtCedula.getText().equals("")){
                    correcto = dbEmpleados.editarEmpleado(id, txtNombre.getText().toString(),txtCedula.getText().toString(),
                            txtTelefono.getText().toString(), txtDireccion.getText().toString(), txtCorreo.getText().toString(),
                            spnCargo.getSelectedItem().toString(), spnTurno.getSelectedItem().toString());
                    if(correcto){
                        Toast.makeText(activity_editar.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    }else{
                        Toast.makeText(activity_editar.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(activity_editar.this, "DEBE LLENAR LOS CAMPOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void verRegistro(){
        Intent intent = new Intent(this, activity_ver.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}
