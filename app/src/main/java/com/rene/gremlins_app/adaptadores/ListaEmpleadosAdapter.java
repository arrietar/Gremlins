package com.rene.gremlins_app.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rene.gremlins_app.R;
import com.rene.gremlins_app.entidades.Empleados;

import java.util.ArrayList;

public class ListaEmpleadosAdapter extends RecyclerView.Adapter<ListaEmpleadosAdapter.EmpleadoViewHolder> {

    ArrayList<Empleados> listaEmpleados;

    public ListaEmpleadosAdapter(ArrayList<Empleados> listaEmpleados){
        this.listaEmpleados = listaEmpleados;
    }

    @NonNull
    @Override
    public EmpleadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_empleado, null, false);

        return new EmpleadoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmpleadoViewHolder holder, int position) {
        holder.viewCedula.setText(listaEmpleados.get(position).getCedula());
        holder.viewNombre.setText(listaEmpleados.get(position).getNombre());
        holder.viewTelefono.setText(listaEmpleados.get(position).getTelefono());
        holder.viewCorreo.setText(listaEmpleados.get(position).getCorreo());

    }

    @Override
    public int getItemCount() {
        return listaEmpleados.size();
    }

    public class EmpleadoViewHolder extends RecyclerView.ViewHolder {
        TextView viewCedula, viewNombre, viewTelefono, viewCorreo;

        public EmpleadoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewCedula = itemView.findViewById(R.id.viewCedula);
            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewTelefono = itemView.findViewById(R.id.viewTelefono);
            viewCorreo = itemView.findViewById(R.id.viewCorreo);
        }
    }
}
