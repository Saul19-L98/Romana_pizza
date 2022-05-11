package sv.edu.udb.pizzeria_forkify.OrderPizza.ui.admin;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import sv.edu.udb.pizzeria_forkify.OrderPizza.Activities.RecipeModification;
import sv.edu.udb.pizzeria_forkify.databinding.FragmentAdminBinding;

public class AdminFragment extends Fragment {

    private FragmentAdminBinding binding;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        AdminViewModel adminViewModel=
                new ViewModelProvider(this).get(AdminViewModel.class);

        binding = FragmentAdminBinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        //llenado desde firebase
        final RecyclerView recyclerView = binding.RecetasList;
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adminViewModel.getMenu(recyclerView,getContext());


        //create recipe
        final TextView tv_btn_crear = binding.tvBtnCrear;
        tv_btn_crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(), RecipeModification.class);
                intent.putExtra("mod","f");
                startActivity(intent);
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}