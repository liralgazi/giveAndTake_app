package com.example.class3demo2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.class3demo2.databinding.FragmentStudentsListBinding;
import com.example.class3demo2.model.Model;
import com.example.class3demo2.model.Student;

import java.util.LinkedList;
import java.util.List;

public class StudentsListFragment extends Fragment {
    FragmentStudentsListBinding binding;
    List<Student> data = new LinkedList<>();
    StudentRecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStudentsListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new StudentRecyclerAdapter(getLayoutInflater(),data);
        binding.recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new StudentRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Log.d("TAG", "Row was clicked " + pos);
                Student st = data.get(pos);
                StudentsListFragmentDirections.ActionStudentsListFragmentToBlueFragment action = StudentsListFragmentDirections.actionStudentsListFragmentToBlueFragment(st.name);
                Navigation.findNavController(view).navigate(action);
            }
        });

        View addButton = view.findViewById(R.id.btnAdd);
        NavDirections action = StudentsListFragmentDirections.actionGlobalAddStudentFragment();
        addButton.setOnClickListener(Navigation.createNavigateOnClickListener(action));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        reloadData();
    }

    void reloadData(){
        binding.progressBar.setVisibility(View.VISIBLE);
        Model.instance().getAllStudents((stList)->{
            data = stList;
            adapter.setData(data);
            binding.progressBar.setVisibility(View.GONE);
        });
    }
}