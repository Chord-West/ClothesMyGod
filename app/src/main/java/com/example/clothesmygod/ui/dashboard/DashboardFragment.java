package com.example.clothesmygod.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothesmygod.LoginActivity;
import com.example.clothesmygod.Model.PostData;
import com.example.clothesmygod.R;
import com.example.clothesmygod.SignUpActivitiy;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment{
    private View view;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dashboard,container,false);
        ArrayList<PostData> postData = new ArrayList<>();

        postData.add(new PostData("id1","test1","test1"));
        postData.add(new PostData("id2","test2","test2"));
        postData.add(new PostData("id3","test3","test3"));

        DashboardAdapter adapter = new DashboardAdapter(postData);

        ListView listView = view.findViewById(R.id.dashboard_listview);
        listView.setAdapter(adapter);

        return view;
    }

}