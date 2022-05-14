package sv.edu.udb.pizzeria_forkify.OrderPizza.ui.bookmarks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import sv.edu.udb.pizzeria_forkify.databinding.FragmentBookmarksBinding;


public class BookmarksFragment extends Fragment {

    private FragmentBookmarksBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BookmarksViewModel bookmarksViewModel =
                new ViewModelProvider(this).get(BookmarksViewModel.class);

        binding = FragmentBookmarksBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final RecyclerView  recyclerView = binding.recipeList;
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        bookmarksViewModel.getMyBookmarks(recyclerView,getContext());


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}