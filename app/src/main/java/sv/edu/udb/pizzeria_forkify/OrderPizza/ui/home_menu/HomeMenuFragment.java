package sv.edu.udb.pizzeria_forkify.OrderPizza.ui.home_menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import sv.edu.udb.pizzeria_forkify.databinding.FragmentHomeMenuBinding;

public class HomeMenuFragment extends Fragment {

    private FragmentHomeMenuBinding binding;
    MenuPizzasAdapter myAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeMenuViewModel homeMenuViewModel =
                new ViewModelProvider(this).get(HomeMenuViewModel.class);

        binding = FragmentHomeMenuBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        //llenado de datos desde firebase
        final RecyclerView recyclerView = binding.rvMenulist;
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeMenuViewModel.getMenu(recyclerView,getContext());


        //filtrado
        final SearchView searchView =binding.schTxt;
        homeMenuViewModel.searchTXT(searchView,getContext());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}