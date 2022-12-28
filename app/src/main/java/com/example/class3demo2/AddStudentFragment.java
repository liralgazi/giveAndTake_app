package com.example.class3demo2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.class3demo2.databinding.FragmentAddStudentBinding;
import com.example.class3demo2.model.Model;
import com.example.class3demo2.model.Student;

public class AddStudentFragment extends Fragment {
    FragmentAddStudentBinding binding;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentActivity parentActivity = getActivity();
        parentActivity.addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menu.removeItem(R.id.addStudentFragment);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                return false;
            }
        },this, Lifecycle.State.RESUMED);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddStudentBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        binding.saveBtn.setOnClickListener(view1 -> {
            String name = binding.nameEt.getText().toString();
            String stId = binding.idEt.getText().toString();
            Student st = new Student(stId,name,"",false);
            Model.instance().addStudent(st,()->{
                Navigation.findNavController(view1).popBackStack();
            });
        });

        binding.cancellBtn.setOnClickListener(view1 -> Navigation.findNavController(view1).popBackStack(R.id.studentsListFragment,false));
        return view;
    }

}