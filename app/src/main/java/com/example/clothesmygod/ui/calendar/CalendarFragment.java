package com.example.clothesmygod.ui.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.clothesmygod.R;

public class CalendarFragment extends Fragment {
    private View v;

    private ImageView imageView;
    private Button cal_Btn;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_calendar, container, false);
        imageView = v.findViewById(R.id.imageView);
        cal_Btn = v.findViewById(R.id.cal_Btn);

        v.findViewById(R.id.cal_Btn).setOnClickListener(onClickListener);
        return v;

    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.cal_Btn:
                    Intent intent = new Intent(getActivity(), CalendarActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}