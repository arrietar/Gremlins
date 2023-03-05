package com.rene.gremlins_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.rene.gremlins_app.db.DbEmpleados;

public class activity_empleado extends AppCompatActivity {

    EditText txtNombre, txtTelefono, txtDireccion, txtCorreo, txtCedula;
    Spinner spnCargo, spnTurno;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado);

        txtNombre = findViewById(R.id.txtNombre);
        txtCedula = findViewById(R.id.txtCedula);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtCorreo = findViewById(R.id.txtCorreo);
        spnCargo = findViewById(R.id.spnCargo);
        spnTurno = findViewById(R.id.spnTurno);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbEmpleados dbEmpleados = new DbEmpleados(activity_empleado.this);
                long id = dbEmpleados.insertEmpleado(txtNombre.getText().toString(), txtCedula.getText().toString(),
                        txtTelefono.getText().toString(), txtDireccion.getText().toString(), txtCorreo.getText().toString(),
                        spnCargo.getSelectedItem().toString(), spnTurno.getSelectedItem().toString());

                if(id > 0){
                    Toast.makeText(activity_empleado.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                    limpiarCampos();
                }else{
                    Toast.makeText(activity_empleado.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void limpiarCampos(){
        txtNombre.setText("");
        txtCedula.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtCorreo.setText("");
    }

}