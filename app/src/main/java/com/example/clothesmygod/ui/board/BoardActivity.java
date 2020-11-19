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
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    TextView textTitle;
    TextView textContent;
    TextView textAuthor;
    Button edit_btn;
    EditText board_edit;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    DatabaseReference boardDBRef = db.child("board");
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        textAuthor = (TextView)findViewById(R.id.board_author);
        textTitle = (TextView)findViewById(R.id.board_title);
        textContent = (TextView)findViewById(R.id.board_content);
        edit_btn = (Button)findViewById(R.id.board_edit_btn);
        board_edit = (EditText)findViewById(R.id.board_edit);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        final ListView listView = (ListView)findViewById(R.id.board_comment_listView);
        final ArrayList<Comment> commentData = new ArrayList<>();
        Intent intent= getIntent();
        final String key = intent.getExtras().getString("key");
        final DatabaseReference DBRef = db.child("board").child(key);
        final DatabaseReference commentRef = DBRef.child("comment");
        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String author = currentUser.getEmail();
                String content = board_edit.getText().toString();
                Comment comment =new Comment(author,content);
                if(!content.equals("")){
                    DBRef.child("comment").push().setValue(comment);
                    board_edit.setText("");
                }
                else{
                    Toast.makeText(BoardActivity.this,"내용을 입력하세요",Toast.LENGTH_SHORT).show();
                }
            }
        });

        ValueEventListener mValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Board board =snapshot.child(key).getValue(Board.class);
                textAuthor.setText(board.getAuthor());
                textTitle.setText(board.getTitle());
                textContent.setText(board.getContent());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        ValueEventListener commentValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                commentData.clear();
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