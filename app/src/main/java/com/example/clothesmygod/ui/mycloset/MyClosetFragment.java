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
        ArrayList<PostData> postData = new ArrayList<>();
        view.findViewById(R.id.mycloset_post_btn).setOnClickListener(onClickListener);
        postData.add(new PostData("id1","test1","test1"));
        postData.add(new PostData("id2","test2","test2"));
        postData.add(new PostData("id3","test3","test3"));

        MyClosetAdapter adapter = new MyClosetAdapter(postData);

        ListView listView = view.findViewById(R.id.mycloset_listview);
        listView.setAdapter(adapter);

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