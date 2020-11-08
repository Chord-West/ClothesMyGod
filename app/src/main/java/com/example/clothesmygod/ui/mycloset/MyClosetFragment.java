package com.example.clothesmygod.ui.mycloset;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.clothesmygod.Model.PostData;
import com.example.clothesmygod.R;
import com.example.clothesmygod.ui.home.HomeFragment;
import com.example.clothesmygod.ui.profile.ProfileFragment;

import java.util.ArrayList;

public class MyClosetFragment extends Fragment{
    private View view;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mycloset,container,false);

        view.findViewById(R.id.mycloset_post_btn).setOnClickListener(onClickListener); // 옷등록 버튼
        ArrayList<PostData> postData = new ArrayList<>();
        // 등록된 옷을 나열하기 위한 recycler view
        RecyclerView recyclerView = view.findViewById(R.id.mycloset_recyclerView);
        // 가로로 나오게 하기위한 설정
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager); // 레이아웃 메니저를 선언해야 recyclerview 작동

        MyClosetAdapter adapter = new MyClosetAdapter(dataList);
        recyclerView.setAdapter(adapter);
        return view;
    }
    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.mycloset_post_btn:
                    Intent intent = new Intent(getActivity(),PostClothesActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}