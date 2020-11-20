package com.example.clothesmygod.ui.board;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.clothesmygod.MainActivity;
import com.example.clothesmygod.Model.Board;
import com.example.clothesmygod.Model.Comment;
import com.example.clothesmygod.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BoardActivity extends AppCompatActivity {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance(); //현재 유저정보 가져오기         (정현구)
    FirebaseUser currentUser= mAuth.getCurrentUser();  //유저정보 저장         (정현구)
    DatabaseReference db = FirebaseDatabase.getInstance().getReference(); //Realtime database정보 가져오기         (정현구)
    DatabaseReference boardDBRef = db.child("board"); //child가 board인 정보 가져오기         (정현구)
    //layout안에 view 사용을 위한 선언         (정현구)
    TextView textTitle;
    TextView textContent;
    TextView textAuthor;
    Button edit_btn;
    EditText board_edit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        //댓글 출력을 위한 listview          (정현구)
        final ListView listView = (ListView)findViewById(R.id.board_comment_listView);
        final ArrayList<Comment> commentData = new ArrayList<>();
        //id값으로 연결         (정현구)
        textTitle = (TextView)findViewById(R.id.board_title);
        textContent = (TextView)findViewById(R.id.board_content);
        textAuthor = (TextView)findViewById(R.id.board_author);
        edit_btn = (Button)findViewById(R.id.board_edit_btn);
        board_edit = (EditText)findViewById(R.id.board_edit);
        Intent intent= getIntent(); //게시글 등록시에 넘겼던 intent를 받음         (정현구)
        final String key = intent.getExtras().getString("key");//intent에서 key값 가져오기         (정현구)
        final DatabaseReference DBRef = db.child("board").child(key);// 키값을 통해서 클릭했던 게시글의 정보 가져오기         (정현구)
        final DatabaseReference commentRef = DBRef.child("comment");// child가 comment인 정보를 가져오기         (정현구)
        //실시간 댓글 달기 버튼 클릭 리스너         (정현구)
        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //글쓴이는 현재유저의 이메일, EditText에서 받은 내용 String으로 받아와 comment객체에 저장         (정현구)
                String author = currentUser.getEmail();
                String content = board_edit.getText().toString();
                Comment comment =new Comment(author,content);
                //빈내용이 없을시에 child가 "comment"인 하위노드로 comment 형태로 저장         (정현구)
                if(!content.equals("")){
                    DBRef.child("comment").push().setValue(comment);
                    board_edit.setText(""); // EditText 부분 초기화         (정현구)
                }
                else{
                    Toast.makeText(BoardActivity.this,"내용을 입력하세요",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //intent로 받아온 key값 안에 저장되어있는 정보 가져오는 리스너         (정현구)
        ValueEventListener mValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Board board =snapshot.child(key).getValue(Board.class);//키값 아래에 Board 객체 형태로 값 가져옴         (정현구)
                //각각의 Textview에 getter를 사용하여 set해줌         (정현구)
                textAuthor.setText(board.getAuthor());
                textTitle.setText(board.getTitle());
                textContent.setText(board.getContent());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        //실시간 댓글을 위한 이벤트 리스너         (정현구)
        ValueEventListener commentValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                commentData.clear();//댓글의 중복 출력 제거         (정현구)
                for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                    Comment comment = datasnapshot.getValue(Comment.class);
                    commentData.add(comment);
                }
                CommentAdapter adapter = new CommentAdapter(commentData);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        boardDBRef.addValueEventListener(mValueEventListener);
        commentRef.addValueEventListener(commentValueEventListener);
    }
}