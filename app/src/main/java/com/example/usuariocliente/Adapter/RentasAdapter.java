package com.example.usuariocliente.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usuariocliente.Models.Auto;
import com.example.usuariocliente.Models.Cliente;
import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.Models.Renta;
import com.example.usuariocliente.R;
import com.example.usuariocliente.UsuarioClienteMenu.AutoSelected;

import java.util.ArrayList;
import java.util.List;

public class RentasAdapter extends RecyclerView.Adapter<RentasAdapter.RentaViewHolder> {
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
    public RentasAdapter.RentaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rent_card, parent, false);
        RentasAdapter.RentaViewHolder rvh = new RentasAdapter.RentaViewHolder(v);
        c = v.getContext();
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RentaViewHolder holder, int position) {
        //Auto auto = Globals.getAuto(rentasList.get(position), c);
        //List<Cliente> cliente = Globals.getCliente(c, rentasList.get(position));
        holder.nombre.setText("ID cliente: " + rentas.get(position).getId_usuario());
        //holder.placas.setText(auto.getPlacas());
        //holder.usuario.setText(cliente.get(0).getNombres() + " " + cliente.get(0).getApellido_paterno() + " " + cliente.get(0).getApellido_materno());

        //poner foto

        holder.cv.setOnClickListener(v -> {
            Renta renta = rentas.get(position);
            Toast.makeText(c, renta.getFecha_inicio(), Toast.LENGTH_SHORT).show();
            //Intent i = new Intent(c, Rentaselected.class);
            //i.putExtra("renta", renta);
            //c.startActivity(i);
        });
        holder.entregar.setOnClickListener(v -> Toast.makeText(c, "ENTREGAR", Toast.LENGTH_SHORT).show());

    }



    @Override
    public int getItemCount() {
        return rentas.size();
    }

    public static class RentaViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView nombre, usuario, placas;
        ImageView foto;
        Button entregar;

        public RentaViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cvRenta);
            nombre = itemView.findViewById(R.id.tvNombre_auto);
            foto = itemView.findViewById(R.id.ivAuto);
            usuario = itemView.findViewById(R.id.tvNombre_usuario);
            placas = itemView.findViewById(R.id.tvPlacas);
            entregar = itemView.findViewById(R.id.btnEntregar);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
