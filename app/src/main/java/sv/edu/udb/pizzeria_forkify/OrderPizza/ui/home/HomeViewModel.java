package sv.edu.udb.pizzeria_forkify.OrderPizza.ui.home;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import sv.edu.udb.pizzeria_forkify.OrderPizza.model.MenuPizzasItem;

public class HomeViewModel extends ViewModel {


    private final MutableLiveData<String> mText;
    private ArrayList<MenuPizzasItem> list;
    MenuPizzasAdapter menuPizzasAdapter;


    DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
            .child("PizzeriaForkify")
            .child("Menu")
            .child("Catergorias")
            .child("Pizzas");



    public HomeViewModel() {
        this.list = list;
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }


    public void getMenu(RecyclerView recyclerView, Context context) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        list = new ArrayList<>();
        menuPizzasAdapter = new MenuPizzasAdapter(context,list);
        recyclerView.setAdapter(menuPizzasAdapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    MenuPizzasItem menuPizzasItem = dataSnapshot.getValue(MenuPizzasItem.class);
                    list.add(menuPizzasItem);
                }
                menuPizzasAdapter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return;
    }
}