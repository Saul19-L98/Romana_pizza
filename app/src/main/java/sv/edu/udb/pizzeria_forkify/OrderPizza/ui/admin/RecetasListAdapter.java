package sv.edu.udb.pizzeria_forkify.OrderPizza.ui.admin;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import sv.edu.udb.pizzeria_forkify.OrderPizza.Activities.RecipeDisplay;
import sv.edu.udb.pizzeria_forkify.OrderPizza.Activities.RecipeModification;
import sv.edu.udb.pizzeria_forkify.OrderPizza.model.ModelRecetas;
import sv.edu.udb.pizzeria_forkify.R;

public class RecetasListAdapter extends RecyclerView.Adapter<RecetasListAdapter.MyViewHolder> {

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
            .child("RecetarioForkify")
            .child("Categoria")
            .child("Mixto");

    Context context;
    ArrayList<ModelRecetas> recetasList;

    public RecetasListAdapter(Context context, ArrayList<ModelRecetas> recetasList) {
        this.context= context;
        this.recetasList= recetasList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_menu_list_admin,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.e("Pizzas", "Adapter pizza list: "+ recetasList);
        ModelRecetas modelRecetasItem =recetasList.get(position);
        holder.tv_titulo2.setText(modelRecetasItem.getTitulo());
        holder.tv_descripcion.setText(modelRecetasItem.getDescripcion());
        Glide.with(context).load(modelRecetasItem.getRefImg()).into(holder.img_receta2);
        holder.view_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"clickeaste el item: "+modelRecetasItem.getKey(), Toast.LENGTH_SHORT).show();
                PopupMenu popupMenu = new PopupMenu(context,view);
                popupMenu.getMenuInflater().inflate(R.menu.popupmenu,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.popup_delete:
                                delete_alert(modelRecetasItem.getKey());
                                break;

                            case R.id.popup_mod:
                                Intent intent= new Intent(view.getContext(), RecipeModification.class);
                                intent.putExtra("clase",modelRecetasItem);
                                view.getContext().startActivity(intent);
                                break;
                        }

                        return true;
                    }
                });
                popupMenu.show();

//                Intent intent= new Intent(view.getContext(), RecipeDisplay.class);
//                intent.putExtra("key", modelRecetasItem.getKey());
//                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recetasList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView img_receta2;
        TextView tv_titulo2,tv_descripcion;
        View view_item;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_receta2=itemView.findViewById(R.id.img_receta2);
            tv_titulo2 = itemView.findViewById(R.id.tv_titulo2);
            tv_descripcion = itemView.findViewById(R.id.tv_descripcion);
            view_item =itemView;
        }
    }

    //Funcion para la muestra de la ventana emergener ante la peticion de eliminacion de una receta
    private void delete_alert(String recipe_code) {
        Dialog dialog = new Dialog(context);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        dialog.setContentView(R.layout.recipe_delete_alert);

        dialog.findViewById(R.id.btn_cancelar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.btn_borrar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Borrado de la receta en base de datos
                Toast.makeText(context,"Se borró el item: "+ recipe_code, Toast.LENGTH_SHORT).show();
                reference.child("" + recipe_code).removeValue();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
