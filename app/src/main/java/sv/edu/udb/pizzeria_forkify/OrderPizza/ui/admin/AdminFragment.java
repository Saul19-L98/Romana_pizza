package sv.edu.udb.pizzeria_forkify.OrderPizza.ui.admin;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sv.edu.udb.pizzeria_forkify.R;
import sv.edu.udb.pizzeria_forkify.databinding.AdminFragmentBinding;

public class AdminFragment extends Fragment {

    private AdminFragmentBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        AdminViewModel adminViewModel=
                new ViewModelProvider(this).get(AdminViewModel.class);

        binding = AdminFragmentBinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        //llenado desde firebase
        final RecyclerView recyclerView = binding.RecetasList;
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adminViewModel.getRecipes(recyclerView,getContext());

        //popup Menu
        adminViewModel.popMenu(recyclerView,getContext());


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}