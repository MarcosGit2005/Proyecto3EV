package com.example.proyecto3ev_cliente.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto3ev_cliente.R;

import com.example.proyecto3ev_cliente.activities.model.Contenido;
import com.example.proyecto3ev_cliente.base.ImageDownloader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase para crear el adaptador del Recycler view de peliculas.
 */
public class AdaptadorRecycleView extends RecyclerView.Adapter<AdaptadorRecycleView.ViewHolder> {
    private LayoutInflater layoutInflater;
    private java.util.List<Contenido> contenidos;
    private View.OnClickListener onClickListener;
    public AdaptadorRecycleView(Context context, List<Contenido> contenidos){
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.contenidos = contenidos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.simple_element_list,parent,false);
        view.setOnClickListener(onClickListener);
        return new ViewHolder(view);
    }
    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contenido contenido = contenidos.get(position);

        //ImageDownloader.downloadImage(null);
        holder.tituloContenido.setText(contenido.getTítulo());
        holder.precioContenido.setText(contenido.getPrecio()+"€");
        holder.idContenido = contenido.getIdContenido();
    }

    @Override
    public int getItemCount() {
        return contenidos.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView tituloContenido, precioContenido;
        private Button botonAñadir;
        private int idContenido;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewPelicula);
            tituloContenido = itemView.findViewById(R.id.tituloContenido);
            precioContenido = itemView.findViewById(R.id.precioContenido);
            botonAñadir = itemView.findViewById(R.id.añadirBoton);
        }
    }
}
