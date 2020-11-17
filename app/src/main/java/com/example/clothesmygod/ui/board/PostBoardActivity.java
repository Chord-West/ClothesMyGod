package com.example.clothesmygod.ui.board;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.clothesmygod.MainActivity;
import com.example.clothesmygod.Model.Board;
import com.example.clothesmygod.Model.CodyItem;
import com.example.clothesmygod.R;
import com.example.clothesmygod.ui.mycloset.PostClothesActivity;
import com.example.clothesmygod.ui.mycody.MyCodyFragment;
import com.example.clothesmygod.ui.mycody.SelectCategory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class PostBoardActivity extends AppCompatActivity {
    private FirebaseAuth mAuth; // 현재 유저정보 불러오기 위한 메소드
    FirebaseUser currentUser; // 현재 유저에 storage 저장
    private DatabaseReference mDatabase;
    Button postBoardBtn; // 마지막 등록 버튼
    Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_post);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        postBoardBtn = findViewById(R.id.post_board_btn);// 등록버튼
        postBoardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String author = currentUser.getEmail();
                String post_name = ((EditText) findViewById(R.id.post_board_name)).getText().toString();
                String post_content = ((EditText) findViewById(R.id.post_board_content)).getText().toString();
                Board board =new Board(author,post_name,post_content);
                if(!post_content.equals("") && !post_name.equals("")) {
                    mDatabase.child("board").push().setValue(board);
                    Intent intent = new Intent(PostBoardActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(PostBoardActivity.this,"제목과 내용을 모두 입력해주세요",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}