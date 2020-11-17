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
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.clothesmygod.Model.Board;
import com.example.clothesmygod.Model.CodyItem;
import com.example.clothesmygod.Model.PostData;
import com.example.clothesmygod.R;
import com.example.clothesmygod.ui.mycloset.MyClosetAdapter;
import com.example.clothesmygod.ui.mycloset.PostClothesActivity;
import com.example.clothesmygod.ui.mycody.MyCodyAdapter;
import com.example.clothesmygod.ui.mycody.PostCodyActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class BoardFragment extends Fragment {
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_board,container,false);
        view.findViewById(R.id.board_post_btn).setOnClickListener(onClickListener);
        final ListView listView = (ListView)view.findViewById(R.id.board_listView);
        final List<Board> board = new ArrayList<Board>();
        BoardAdapter adapter = new BoardAdapter(getActivity());
        listView.setAdapter(adapter);
        board.add(new Board("","",""));
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("board").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Board board1 = snapshot.getValue(Board.class);
                board.add(board1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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