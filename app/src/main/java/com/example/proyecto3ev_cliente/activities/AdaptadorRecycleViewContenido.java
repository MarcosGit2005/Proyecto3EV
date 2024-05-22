package com.example.proyecto3ev_cliente.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto3ev_cliente.R;

import com.example.proyecto3ev_cliente.activities.model.Contenido;
import com.example.proyecto3ev_cliente.base.ImageDownloader;

import java.util.List;

public class AdaptadorRecycleViewContenido extends RecyclerView.Adapter<AdaptadorRecycleViewContenido.ViewHolder> {
    private LayoutInflater layoutInflater;
    private java.util.List<Contenido> contenidos;
    private View.OnClickListener onClickListener;
    public AdaptadorRecycleViewContenido(Context context, List<Contenido> contenidos){
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.contenidos = contenidos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.simple_element_list_content,parent,false);
        view.setOnClickListener(onClickListener);
        return new ViewHolder(view);
    }
    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contenido contenido = contenidos.get(position);

        ImageDownloader.downloadImage("https://hips.hearstapps.com/hmg-prod/images/gerard-butler-300-entrenamiento-dieta-mens-health-1605801533.jpg?crop=0.526xw:1.00xh;0.241xw,0&resize=1200:*",holder.imageView);
        if (!contenido.getTipoContenido().equals("capítulo"))
            holder.tituloContenido.setText(contenido.getTítulo());
        else {
            holder.tituloContenido.setText(contenido.getNombreSerie());
        }
        holder.precioContenido.setText(contenido.getPrecio()+"€");
        holder.tipoContenido.setText(contenido.getTipoContenido());
        holder.idContenido = contenido.getIdContenido();
    }

    @Override
    public int getItemCount() {
        return contenidos.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView tituloContenido, precioContenido, tipoContenido;
        private int idContenido;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewImagen);
            tituloContenido = itemView.findViewById(R.id.tituloContenido);
            precioContenido = itemView.findViewById(R.id.precioContenido);
            tipoContenido = itemView.findViewById(R.id.textViewTipo);
        }
    }
}
