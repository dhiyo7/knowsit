package com.ydhnwb.knowsit.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ydhnwb.knowsit.MainActivity;
import com.ydhnwb.knowsit.R;
import com.ydhnwb.knowsit.models.ScheduleModel;
import com.ydhnwb.knowsit.utilities.Constants;
import com.ydhnwb.knowsit.viewholders.HomeViewHolder;

public class FragmentHome extends Fragment {
    private RecyclerView recyclerView;
    private FirebaseRecyclerAdapter<ScheduleModel, HomeViewHolder> firebaseRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.main_recyclerview_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(Constants.REF_SCHEDULE);
        FirebaseRecyclerOptions fo = new FirebaseRecyclerOptions.Builder<ScheduleModel>().setQuery(databaseReference, ScheduleModel.class).build();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ScheduleModel, HomeViewHolder>(fo){
            @NonNull
            @Override
            public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(getContext()).inflate(R.layout.list_item_home, parent, false);
                return new HomeViewHolder(v);
            }

            @Override
            protected void onBindViewHolder(@NonNull HomeViewHolder homeViewHolder, int i, @NonNull ScheduleModel scheduleModel) {
                homeViewHolder.binding(scheduleModel, getContext());
            }
        };
        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}
