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
    //firebase 사용을 위한 선언        (정현구)
    private FirebaseAuth mAuth= FirebaseAuth.getInstance(); //유저 정보를 사용        (정현구)
    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();//Realtime Database 사용        (정현구)
    FirebaseUser currentUser= mAuth.getCurrentUser(); //현재 유저 정보 저장        (정현구)
    Button postBoardBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_post);

        postBoardBtn = findViewById(R.id.post_board_btn); //게시물 등록 버튼         (정현구)
        // 버튼 클릭 리스너         (정현구)
        postBoardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String author = currentUser.getEmail(); //현재 유저의 이메일 정보를 글쓴이에 저장         (정현구)
                //글 제목과 내용은 각각의 EditText의 내용을 String 형태로 저장         (정현구)
                String post_name = ((EditText) findViewById(R.id.post_board_name)).getText().toString();
                String post_content = ((EditText) findViewById(R.id.post_board_content)).getText().toString();
                Board board =new Board(author,post_name,post_content); // board 객체를 위에 내용으로 생성
                //게시글의 내용중 빈내용이 있을시에 Toast메세지 출력        (정현구)
                //없을시에는 RealtimeDatabase의 "board"하위에 board객체형태로 push        (정현구)
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