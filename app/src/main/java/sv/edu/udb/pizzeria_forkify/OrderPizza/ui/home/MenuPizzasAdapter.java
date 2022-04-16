package sv.edu.udb.pizzeria_forkify.OrderPizza.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        MenuPizzasItem menuPizzasItem =pizzasList.get(position);
        holder.tv_name.setText(menuPizzasItem.getNombre());
        holder.tv_ingredients.setText(menuPizzasItem.getIngredientes());
        holder.tv_price.setText(menuPizzasItem.getPrecio());

    }

    @Override
    public int getItemCount() {
        return pizzasList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView img_pizza;
        TextView tv_name,tv_ingredients,tv_price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_ingredients = itemView.findViewById(R.id.tv_ingredients);
            tv_price=itemView.findViewById(R.id.tv_price);

        }
    }

}
