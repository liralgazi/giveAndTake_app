package com.example.giveAndTake_app;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.giveAndTake_app.R;
import com.example.giveAndTake_app.StudentRecyclerAdapter;
import com.example.giveAndTake_app.StudentsListFragmentDirections;
import com.example.giveAndTake_app.StudentsListFragmentViewModel;
import com.example.giveAndTake_app.databinding.FragmentStudentsListBinding;
import com.example.giveAndTake_app.model.Model;
import com.example.giveAndTake_app.model.Movie;
import com.example.giveAndTake_app.model.MovieModel;
import com.example.giveAndTake_app.model.Student;

import java.util.List;

public class StudentsListFragment extends Fragment {
    FragmentStudentsListBinding binding;
    StudentRecyclerAdapter adapter;
    StudentsListFragmentViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStudentsListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new StudentRecyclerAdapter(getLayoutInflater(),viewModel.getData().getValue());
        binding.recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new StudentRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Log.d("TAG", "Row was clicked " + pos);
                Student st = viewModel.getData().getValue().get(pos);
                StudentsListFragmentDirections.ActionStudentsListFragmentToBlueFragment action = StudentsListFragmentDirections.actionStudentsListFragmentToBlueFragment(st.name);
                Navigation.findNavController(view).navigate(action);
            }
        });

        View addButton = view.findViewById(R.id.btnAdd);
        NavDirections action = StudentsListFragmentDirections.actionGlobalAddStudentFragment();
        addButton.setOnClickListener(Navigation.createNavigateOnClickListener(action));

        binding.progressBar.setVisibility(View.GONE);

        viewModel.getData().observe(getViewLifecycleOwner(),list->{
            adapter.setData(list);
        });

        Model.instance().EventStudentsListLoadingState.observe(getViewLifecycleOwner(), status->{
            binding.swipeRefresh.setRefreshing(status == Model.LoadingState.LOADING);
        });

        binding.swipeRefresh.setOnRefreshListener(()->{
            reloadData();
        });


        LiveData<List<Movie>> data = MovieModel.instance.searchMoviesByTitle("avatar");
        data.observe(getViewLifecycleOwner(),list->{
            list.forEach(item->{
                Log.d("TAG","got movie: " + item.getTitle() + " " + item.getPoster());
            });
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        viewModel = new ViewModelProvider(this).get(StudentsListFragmentViewModel.class);
    }

    void reloadData(){
//        binding.progressBar.setVisibility(View.VISIBLE);
        Model.instance().refreshAllStudents();
    }
}