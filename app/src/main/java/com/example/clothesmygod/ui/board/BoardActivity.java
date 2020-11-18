package com.example.clothesmygod.ui.board;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.clothesmygod.MainActivity;
import com.example.clothesmygod.Model.Board;
import com.example.clothesmygod.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BoardActivity extends AppCompatActivity {
    TextView textTitle;
    TextView textContent;
    TextView textAuthor;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    DatabaseReference boardDBRef = db.child("board");
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        textAuthor = (TextView)findViewById(R.id.board_author);
        textTitle = (TextView)findViewById(R.id.board_title);
        textContent = (TextView)findViewById(R.id.board_content);
        Intent intent= getIntent();
        final String key = intent.getExtras().getString("key");
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
        boardDBRef.addValueEventListener(mValueEventListener);
    }
}