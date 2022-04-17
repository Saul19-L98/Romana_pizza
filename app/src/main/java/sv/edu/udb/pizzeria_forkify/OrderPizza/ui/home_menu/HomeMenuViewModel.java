package sv.edu.udb.pizzeria_forkify.OrderPizza.ui.home_menu;

import android.content.Context;

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

public class HomeMenuViewModel extends ViewModel {


//    private final MutableLiveData<String> mText;
    private ArrayList<MenuPizzasItem> list;
    private ArrayList<MenuPizzasItem> listOriginal;
    MenuPizzasAdapter menuPizzasAdapter;


    DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
            .child("PizzeriaForkify")
            .child("Menu")
            .child("Catergorias")
            .child("Pizzas");



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
        menuPizzasAdapter = new MenuPizzasAdapter(context,list);
        recyclerView.setAdapter(menuPizzasAdapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                listOriginal.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    MenuPizzasItem menuPizzasItem = dataSnapshot.getValue(MenuPizzasItem.class);
                    menuPizzasItem.setKey(dataSnapshot.getKey().toString());
                    list.add(menuPizzasItem);

                }
                listOriginal.addAll(list);
                menuPizzasAdapter.notifyDataSetChanged();

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
                menuPizzasAdapter.filter(newText,listOriginal);
                return false;
            }
        });
    }
}