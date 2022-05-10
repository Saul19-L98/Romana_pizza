package sv.edu.udb.pizzeria_forkify.OrderPizza.ui.home_menu;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import sv.edu.udb.pizzeria_forkify.OrderPizza.model.MenuPizzasItem;
import sv.edu.udb.pizzeria_forkify.OrderPizza.model.ModelRecetas;

public class HomeMenuViewModel extends ViewModel {


//    private final MutableLiveData<String> mText;
    private ArrayList<ModelRecetas> list;
    private ArrayList<ModelRecetas> listOriginal;
    private ArrayList<String> ingredientesList;
    private ArrayList<String> pasosList;
    ModelRecetasAdapter modelRecetasAdapter;


//    DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
//            .child("PizzeriaForkify")
//            .child("Menu")
//            .child("Catergorias")
//            .child("Pizzas");
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
            .child("RecetarioForkify")
            .child("Categoria")
            .child("Mixto");



    public HomeMenuViewModel() {

//        mText = new MutableLiveData<>();
//        mText.setValue("This is home fragment");
    }

//    public LiveData<String> getText() {
//        return mText;
//    }


    public void getMenu(RecyclerView recyclerView, Context context) {
        listOriginal =new ArrayList<>();
        list = new ArrayList<>();
        ingredientesList = new ArrayList<>();
        pasosList = new ArrayList<>();

        modelRecetasAdapter = new ModelRecetasAdapter(context,list);
        recyclerView.setAdapter(modelRecetasAdapter);

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
                modelRecetasAdapter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return;
    }

    public void searchTXT(SearchView searchView, Context context) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                modelRecetasAdapter.filter(newText,listOriginal);
                return false;
            }
        });
    }
}