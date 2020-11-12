package com.example.clothesmygod.ui.mycody;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.clothesmygod.R;
import com.example.clothesmygod.ui.mycloset.PostClothesActivity;

public class MyCodyFragment extends Fragment {
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mycody,container,false);
        view.findViewById(R.id.mycody_post_btn).setOnClickListener(onClickListener);


        return view;
    }
    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.mycody_post_btn:
                    Intent intent = new Intent(getActivity(), PostCodyActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}