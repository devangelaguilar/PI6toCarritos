package com.example.usuariocliente.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usuariocliente.Models.Auto;
import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.Models.Renta;
import com.example.usuariocliente.R;
import com.example.usuariocliente.UsuarioClienteMenu.AutoSelected;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RentasAdapter extends RecyclerView.Adapter<RentasAdapter.AutoViewHolder> {
    List<Renta> rentas;

    List<Renta> rentasList;
    ArrayList<Renta> arrayList;
    Context c;

    public RentasAdapter(List<Renta> rentas) {
        this.rentas = rentas;
        this.arrayList = new ArrayList<>();
        this.arrayList.addAll(rentas);
    }

    @NonNull
    @Override
    public RentasAdapter.AutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.auto_card, parent, false);
        RentasAdapter.AutoViewHolder rvh = new RentasAdapter.AutoViewHolder(v);
        c = v.getContext();
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull AutoViewHolder holder, int position) {

        holder.nombre.setText(rentas.get(position).getModelo());
        holder.transmision.setText(rentas.get(position).getTransmision());
        holder.puertas.setText("4 puertas"); //Insertar un if donde cheque cuantas puertas tiene dependiendo del tipo de auto
        holder.pasajeros.setText("5 pasajeros");
        holder.precio.setText("$ " + rentas.get(position).getPrecio());
        //poner foto
        rentasList = new ArrayList<>();
        holder.cv.setOnClickListener(v -> {
            Auto auto = rentasList.get(position);
            Toast.makeText(c, auto.getModelo(), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(c, Rentaselected.class);
            i.putExtra("auto", auto);
            c.startActivity(i);


        });
        rentasList = Globals.showList(c);
    }



    @Override
    public int getItemCount() {
        return rentas.size();
    }

    public static class AutoViewHolder extends RecyclerView.ViewHolder{
        CardView cv, rentar;
        TextView nombre, transmision, puertas, pasajeros, precio;
        ImageView foto;

        public AutoViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cvAuto);
            nombre = itemView.findViewById(R.id.tvNombre_auto);
            transmision = itemView.findViewById(R.id.transmision);
            puertas = itemView.findViewById(R.id.puertas);
            pasajeros = itemView.findViewById(R.id.pasajeros);
            precio = itemView.findViewById(R.id.tvPrecio);
            foto = itemView.findViewById(R.id.ivAuto);
            rentar = itemView.findViewById(R.id.btnRentar);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
