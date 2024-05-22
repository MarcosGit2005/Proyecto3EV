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
import com.example.proyecto3ev_cliente.activities.model.Factura;

import java.util.List;

public class AdaptadorRecycleViewFactura extends RecyclerView.Adapter<AdaptadorRecycleViewFactura.ViewHolder> {
    private LayoutInflater layoutInflater;
    private List<Factura> facturas;
    private View.OnClickListener onClickListener;
    public AdaptadorRecycleViewFactura(Context context, List<Factura> facturas){
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.facturas = facturas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.simple_element_list_factura,parent,false);
        view.setOnClickListener(onClickListener);
        return new ViewHolder(view);
    }
    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Factura factura = facturas.get(position);

        holder.idFactura.setText(factura.getIdFactura()+"");
        holder.fecha.setText(factura.getFecha());
        holder.importeBase.setText(factura.getImporteBase()+"€");
        holder.importeIVA.setText(factura.getImporteIVA()+"€");
        holder.totalFactura.setText(factura.getTotalFactura()+"€");
    }

    @Override
    public int getItemCount() {
        return facturas.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView idFactura, fecha, importeBase, importeIVA, totalFactura;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idFactura = itemView.findViewById(R.id.textViewIdFactura);
            fecha = itemView.findViewById(R.id.textViewFecha);
            importeBase = itemView.findViewById(R.id.importeBase);
            importeIVA = itemView.findViewById(R.id.importeIVA);
            totalFactura = itemView.findViewById(R.id.importeTotal);
        }
    }
}
