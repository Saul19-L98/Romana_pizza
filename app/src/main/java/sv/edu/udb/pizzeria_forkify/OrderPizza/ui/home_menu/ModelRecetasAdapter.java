package sv.edu.udb.pizzeria_forkify.OrderPizza.ui.home_menu;



import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import sv.edu.udb.pizzeria_forkify.OrderPizza.Activities.RecipeDisplay;
import sv.edu.udb.pizzeria_forkify.OrderPizza.model.MenuPizzasItem;
import sv.edu.udb.pizzeria_forkify.OrderPizza.model.ModelRecetas;
import sv.edu.udb.pizzeria_forkify.R;

public class ModelRecetasAdapter extends RecyclerView.Adapter<ModelRecetasAdapter.MyViewHolder> {

    Context context;
    ArrayList<ModelRecetas> recetasList;


    public ModelRecetasAdapter(Context context, ArrayList<ModelRecetas> recetasList) {
        this.context = context;
        this.recetasList = recetasList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_menu_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.e("Pizzas", "Adapter pizza list: "+ recetasList);
        ModelRecetas modelRecetasItem =recetasList.get(position);
        holder.tv_titulo.setText(modelRecetasItem.getTitulo());
        holder.tv_personas.setText(modelRecetasItem.getNoPersonas().toString());
        holder.tv_tiempo.setText(modelRecetasItem.getTiempo());
        Glide.with(context).load(modelRecetasItem.getRefImg()).into(holder.img_receta);
        holder.view_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context,"clickeaste el item: "+modelRecetasItem.getKey(), Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(view.getContext(), RecipeDisplay.class);
                intent.putExtra("key", modelRecetasItem.getKey());
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recetasList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView img_receta;
        TextView tv_titulo,tv_personas,tv_tiempo;
        View view_item;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_receta=itemView.findViewById(R.id.img_receta);
            tv_titulo = itemView.findViewById(R.id.tv_titulo);
            tv_personas = itemView.findViewById(R.id.tv_personas);
            tv_tiempo=itemView.findViewById(R.id.tv_tiempo);
            view_item =itemView;

        }
    }

    public void filter(String sch_text, ArrayList<ModelRecetas> listOriginal){
        if (sch_text.isEmpty()){
            recetasList.clear();
            recetasList.addAll(listOriginal);
        }else {
            List<ModelRecetas> filtered = recetasList.stream().filter(i -> i.getTitulo().toLowerCase().contains(sch_text.toLowerCase())).collect(Collectors.toList());
            System.out.println(filtered);
            recetasList.clear();
            recetasList.addAll(filtered);
        }
        notifyDataSetChanged();
    }

}
