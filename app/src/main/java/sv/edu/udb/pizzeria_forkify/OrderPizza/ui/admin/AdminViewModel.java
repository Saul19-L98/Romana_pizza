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

    public void getMenu(RecyclerView recyclerView, Context context) {
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

                    Log.e("Listas", "Adapter pizza list: "+ pasosList );

                    ModelRecetas modelRecetasItem =new ModelRecetas(
                            snapshot.child(dataSnapshot.getKey()).child("titulo").getValue().toString(),
                            snapshot.child(dataSnapshot.getKey()).child("refImg").getValue().toString(),
                            snapshot.child(dataSnapshot.getKey()).child("descripcion").getValue().toString(),
                            snapshot.child(dataSnapshot.getKey()).child("tiempo").getValue().toString(),
                            (ArrayList<String>) ingredientesList.clone(), (ArrayList<String>) pasosList.clone(),
                            Integer.parseInt(snapshot.child(dataSnapshot.getKey()).child("noPersonas").getValue().toString())
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



    // TODO: Implement the ViewModel
}