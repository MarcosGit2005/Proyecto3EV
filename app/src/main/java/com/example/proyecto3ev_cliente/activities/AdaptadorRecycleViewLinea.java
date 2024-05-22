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
import com.example.proyecto3ev_cliente.activities.model.Linea;

import java.util.List;

public class AdaptadorRecycleViewLinea extends RecyclerView.Adapter<AdaptadorRecycleViewLinea.ViewHolder> {
    private LayoutInflater layoutInflater;
    private List<Linea> lineas;
    private View.OnClickListener onClickListener;
    private Contenido contenido;
    public AdaptadorRecycleViewLinea(Context context, List<Linea> lineas){
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.lineas = lineas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.simple_element_list_linea,parent,false);
        view.setOnClickListener(onClickListener);
        return new ViewHolder(view);
    }
    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Linea linea = lineas.get(position);

        holder.idLinea.setText(linea.getIdLinea()+"");
        holder.idFactura.setText(linea.getIdFactura()+"");
        holder.idContenido.setText(linea.getIdContenido()+"");

    }

    @Override
    public int getItemCount() {
        return lineas.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView idLinea,idFactura,idContenido;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idLinea = itemView.findViewById(R.id.textViewIdLinea);
            idFactura = itemView.findViewById(R.id.idFacturaPadre);
            idContenido = itemView.findViewById(R.id.idContenidoLinea);

        }
    }
}
