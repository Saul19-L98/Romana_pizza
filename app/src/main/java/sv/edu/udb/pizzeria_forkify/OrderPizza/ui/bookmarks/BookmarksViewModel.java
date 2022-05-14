package sv.edu.udb.pizzeria_forkify.OrderPizza.ui.bookmarks;

import static sv.edu.udb.pizzeria_forkify.OrderPizza.LandingMenuActivity.database;
import static sv.edu.udb.pizzeria_forkify.OrderPizza.LandingMenuActivity.myAuth;
import static sv.edu.udb.pizzeria_forkify.OrderPizza.LandingMenuActivity.refUser;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import sv.edu.udb.pizzeria_forkify.OrderPizza.model.ModelRecetas;
import sv.edu.udb.pizzeria_forkify.OrderPizza.ui.home_menu.ModelRecetasAdapter;

public class BookmarksViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    //    private final MutableLiveData<String> mText;
    private ArrayList<ModelRecetas> list;
    private ArrayList<ModelRecetas> listOriginal;
    private ArrayList<String> ingredientesList;
    private ArrayList<String> pasosList;
    ModelRecetasAdapter modelRecetasAdapter;

    ArrayList<String>  keyList = new ArrayList<>();

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
            .child("RecetarioForkify")
            .child("Categoria")
            .child("Mixto");


    public BookmarksViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }



    public LiveData<String> getText() {
        return mText;
    }

    public void getMyBookmarks(RecyclerView recyclerView, Context context) {
        //Metodo de obtencion del Nombre usuario a travez del nickname del correo
        String userKey=myAuth.getCurrentUser().getEmail();
        int index = userKey.indexOf('@');
        userKey = userKey.substring(0,index);
//        return userEmail;

        listOriginal = new ArrayList<>();
        list = new ArrayList<>();
        ingredientesList = new ArrayList<>();
        pasosList = new ArrayList<>();

        modelRecetasAdapter = new ModelRecetasAdapter(context,list);
        recyclerView.setAdapter(modelRecetasAdapter);

        refUser.child(userKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    keyList.add(dataSnapshot.getValue().toString());
                }

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

                            if (keyList.contains(dataSnapshot.getKey())){
                                list.add(modelRecetasItem);
                            }

                        }

                        listOriginal.addAll(list);
                        modelRecetasAdapter.notifyDataSetChanged();

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });

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