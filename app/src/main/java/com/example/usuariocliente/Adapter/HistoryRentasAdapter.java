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

import com.bumptech.glide.Glide;
import com.example.usuariocliente.Driver.History.DriverHistorySelected;
import com.example.usuariocliente.Driver.Home.RentaSelected;
import com.example.usuariocliente.Models.Auto;
import com.example.usuariocliente.Models.Cliente;
import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.Models.Renta;
import com.example.usuariocliente.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryRentasAdapter extends RecyclerView.Adapter<HistoryRentasAdapter.RentaViewHolder> {
    List<Renta> rentas;

    List<Renta> rentasList;
    ArrayList<Renta> arrayList;
    Context c;

    public HistoryRentasAdapter(List<Renta> rentas) {
        this.rentas = rentas;
        this.arrayList = new ArrayList<>();
        this.arrayList.addAll(rentas);
    }

    @NonNull
    @Override
    public HistoryRentasAdapter.RentaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_rent_card, parent, false);
        HistoryRentasAdapter.RentaViewHolder rvh = new HistoryRentasAdapter.RentaViewHolder(v);
        c = v.getContext();
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RentaViewHolder holder, int position) {
        holder.txtUsuario.setText("Fecha: ");
        Auto auto = Globals.getAuto(c, rentas.get(position));
        Cliente cliente = Globals.getCliente(c, rentas.get(position));
        //List<Cliente> cliente = Globals.getCliente(c, rentasList.get(position));
        Glide.with(c).load("https://cinderellaride.000webhostapp.com/assets/img/autos/" + auto.getId_vehiculo() + ".png").asBitmap().into(holder.foto);
        holder.nombre.setText(auto.getModelo());
        holder.placas.setText(auto.getPlacas());
        holder.usuario.setText(cliente.getNombres());

        //poner foto

        holder.cv.setOnClickListener(v -> {
            Renta renta = rentas.get(position);
            //Toast.makeText(c, renta.getId_usuario()+"", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(c, DriverHistorySelected.class);
            i.putExtra("renta", renta);
            c.startActivity(i);
        });
        holder.entregar.setOnClickListener(v -> {
            Renta renta = rentas.get(position);
            //Toast.makeText(c, renta.getId_usuario()+"", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(c, DriverHistorySelected.class);
            i.putExtra("renta", renta);
            c.startActivity(i);
        });

    }



    @Override
    public int getItemCount() {
        return rentas.size();
    }

    public static class RentaViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView nombre, usuario, placas, txtUsuario;
        ImageView foto;
        Button entregar;

        public RentaViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cvRenta);
            nombre = itemView.findViewById(R.id.tvNombre_auto);
            foto = itemView.findViewById(R.id.ivAuto);
            usuario = itemView.findViewById(R.id.tvNombre_usuario);
            txtUsuario = itemView.findViewById(R.id.txtUsuario);
            placas = itemView.findViewById(R.id.tvPlacas);
            entregar = itemView.findViewById(R.id.btnEntregar);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
