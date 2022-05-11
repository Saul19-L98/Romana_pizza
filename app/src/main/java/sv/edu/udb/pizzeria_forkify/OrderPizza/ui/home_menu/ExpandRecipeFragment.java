package sv.edu.udb.pizzeria_forkify.OrderPizza.ui.home_menu;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sv.edu.udb.pizzeria_forkify.R;

public class ExpandRecipeFragment extends Fragment {

    private ExpandRecipeViewModel mViewModel;

    public static ExpandRecipeFragment newInstance() {
        return new ExpandRecipeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.expand_recipe_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ExpandRecipeViewModel.class);
        // TODO: Use the ViewModel
    }

}