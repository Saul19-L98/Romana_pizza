package sv.edu.udb.pizzeria_forkify.OrderPizza.ui.admin;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import sv.edu.udb.pizzeria_forkify.OrderPizza.model.ModelRecetas;
import sv.edu.udb.pizzeria_forkify.OrderPizza.ui.home_menu.ModelRecetasAdapter;


public class AdminViewModel extends ViewModel {

    private ArrayList<ModelRecetas> list;
    private ArrayList<ModelRecetas> listOriginal;
    private ArrayList<String> ingredientesList;
    private ArrayList<String> pasosList;
    RecetasListAdapter recetasListAdapter;

    //referencia de la base de datos

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
            .child("RecetarioForkify")
            .child("Categoria")
            .child("Mixto");

    public void getRecipes(RecyclerView recyclerView, Context context) {
        listOriginal =new ArrayList<>();
        list = new ArrayList<>();
        ingredientesList = new ArrayList<>();
        pasosList = new ArrayList<>();

        recetasListAdapter = new RecetasListAdapter(context,list);
        recyclerView.setAdapter(recetasListAdapter);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                listOriginal.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    pasosList.clear();
                    ingredientesList.clear();
                    Log.e("Datasnapshot", "Adapter pizza list: "+ dataSnapshot);

                    for (DataSnapshot dataSnapshot1:snapshot.child(dataSnapshot.getKey()).child("Ingredientes").getChildren()){
                        //Log.e("Ingredientes", "Adapter pizza list: "+ dataSnapshot1);
                        ingredientesList.add(dataSnapshot1.getValue().toString());
                    }

                    for (DataSnapshot dataSnapshot2:snapshot.child(dataSnapshot.getKey()).child("Pasos").getChildren()){
                        //Log.e("Pasos", "Adapter pizza list: "+ dataSnapshot2);
                        pasosList.add(dataSnapshot2.getValue().toString());
                    }

                    Log.e("Listas", "Adapter pizza list: "+ pasosList.get(1) );

                    ModelRecetas modelRecetasItem =new ModelRecetas(
                            snapshot.child(dataSnapshot.getKey()).child("Titulo").getValue().toString(),
                            snapshot.child(dataSnapshot.getKey()).child("RefImg").getValue().toString(),
                            snapshot.child(dataSnapshot.getKey()).child("Descripcion").getValue().toString(),
                            snapshot.child(dataSnapshot.getKey()).child("Tiempo").getValue().toString(),
                            ingredientesList,pasosList,
                            Integer.parseInt(snapshot.child(dataSnapshot.getKey()).child("NoPersonas").getValue().toString())
                    );
                    modelRecetasItem.setKey(dataSnapshot.getKey());
                    list.add(modelRecetasItem);
                }

                listOriginal.addAll(list);
                recetasListAdapter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return;

    }

    public void popMenu(RecyclerView recyclerView, Context context) {

    }


    // TODO: Implement the ViewModel
}