package sv.edu.udb.pizzeria_forkify.OrderPizza.ui.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import sv.edu.udb.pizzeria_forkify.OrderPizza.model.MenuPizzasItem;
import sv.edu.udb.pizzeria_forkify.R;

public class MenuPizzasAdapter extends RecyclerView.Adapter<MenuPizzasAdapter.MyViewHolder> {

    Context context;
    ArrayList<MenuPizzasItem> pizzasList;


    public MenuPizzasAdapter(Context context, ArrayList<MenuPizzasItem> pizzasList) {
        this.context = context;
        this.pizzasList = pizzasList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_menu_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.e("Pizzas", "Adapter pizza list: "+ pizzasList);
        MenuPizzasItem menuPizzasItem =pizzasList.get(position);
        holder.tv_name.setText(menuPizzasItem.getNombre());
        holder.tv_ingredients.setText(menuPizzasItem.getIngredientes());
        holder.tv_price.setText(menuPizzasItem.getPrecio());
        Glide.with(context).load(menuPizzasItem.getRefImg()).into(holder.img_pizza);
        holder.view_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"clickeaste el item: "+menuPizzasItem.getKey(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return pizzasList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView img_pizza;
        TextView tv_name,tv_ingredients,tv_price;
        View view_item;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_pizza=itemView.findViewById(R.id.img_pizza);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_ingredients = itemView.findViewById(R.id.tv_ingredients);
            tv_price=itemView.findViewById(R.id.tv_price);
            view_item =itemView;

        }
    }

}
