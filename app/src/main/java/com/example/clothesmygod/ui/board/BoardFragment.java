package com.example.clothesmygod.ui.board;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.clothesmygod.Model.CodyItem;
import com.example.clothesmygod.Model.PostData;
import com.example.clothesmygod.R;
import com.example.clothesmygod.ui.mycloset.MyClosetAdapter;
import com.example.clothesmygod.ui.mycloset.PostClothesActivity;
import com.example.clothesmygod.ui.mycody.MyCodyAdapter;
import com.example.clothesmygod.ui.mycody.PostCodyActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class BoardFragment extends Fragment {
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_board,container,false);
        view.findViewById(R.id.board_post_btn).setOnClickListener(onClickListener);
        return view;
    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.board_post_btn:
                    Intent intent = new Intent(getActivity(), PostBoardActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}