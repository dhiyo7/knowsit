package com.ydhnwb.knowsit.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ydhnwb.knowsit.R;
import com.ydhnwb.knowsit.models.ScheduleModel;

public class HomeViewHolder extends RecyclerView.ViewHolder {
    private TextView time, course;

    public HomeViewHolder(@NonNull View itemView) {
        super(itemView);
        time = itemView.findViewById(R.id.list_home_time);
        course = itemView.findViewById(R.id.list_home_course);
    }

    public void binding(final ScheduleModel schedule, final Context context){
        time.setText(schedule.getTime());
        course.setText(schedule.getCourse());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, schedule.getCourse()+" tapped", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
