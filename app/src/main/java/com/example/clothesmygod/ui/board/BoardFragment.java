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
        //fragment를 위한 view     (정현구)
        view = inflater.inflate(R.layout.fragment_board,container,false);
        view.findViewById(R.id.board_post_btn).setOnClickListener(onClickListener);
        //firebase에 있는 데이터를 출력할 ListView     (정현구)
        //이벤트 리스너 안에서 사용하기위해서 final로 선언     (정현구)
        final ListView listView = (ListView)view.findViewById(R.id.board_listView);
        final ArrayList<Board> boardData = new ArrayList<>();
        //firebase 읽기를 위한 db
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        DatabaseReference boardDBRef = db.child("board");

        //firebase Realtime Database의 정보를 얻기위한 이벤트 리스너      (정현구)
       ValueEventListener mValueEventListener = new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               //중복 데이터 출력 제거를 위하여 clear     (정현구)
               boardData.clear();
               //Board의 모델 객체를 생성하여 db에서 데이터를 객체에 저장후 boardData에 add      (정현구)
               for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                   Board board = datasnapshot.getValue(Board.class);
                   board.setKey(datasnapshot.getKey());
                   boardData.add(board);
               }
               //데이터를 BoardAdapter를 사용하여 ListView에 담기      (정현구)
               BoardAdapter adapter = new BoardAdapter(boardData);
               listView.setAdapter(adapter);
               //리스트뷰 item 클릭 리스너, 클릭시 item의 키값을 intent로 넘겨서 게시판 출력      (정현구)
               listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                   @Override
                   public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                       String data=boardData.get(i).getKey();
                       Intent intent = new Intent(getActivity(),BoardActivity.class);
                       intent.putExtra("key",data);
                       startActivity(intent);
                   }
               });
           }
           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       };
       boardDBRef.addValueEventListener(mValueEventListener);
        return view;
    }
    //클릭 이벤트리스너 (정현구)
    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            //게시판 포스트 페이지로 이동
            if (v.getId() == R.id.board_post_btn) {
                Intent intent = new Intent(getActivity(), PostBoardActivity.class);
                startActivity(intent);
            }
        }
    };
}