package com.example.clothesmygod.ui.mycody;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clothesmygod.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

public class PostCodyActivity extends AppCompatActivity {
    private ImageView topimg;
    private ImageView bottomimg;
    private ImageView shoesimg;
    private int REQUEST_CODE = 1001;
    private TextView comeback;
    private FirebaseAuth mAuth; // 현재 유저정보 불러오기 위한 메소드
    FirebaseUser currentUser; // 현재 유저에 storage 저장
    FirebaseDatabase database; //User가 가지고있는 옷들 가져오기위한 작엄
    DatabaseReference userclothesRef;
    StorageReference mStorageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_cody);
        topimg = findViewById(R.id.codypost_top_img);
        topimg.setOnClickListener(onClickListener);
        bottomimg = findViewById(R.id.codypost_bottom_img);
        bottomimg.setOnClickListener(onClickListener);
        shoesimg=findViewById(R.id.codypost_shoes_img);
        shoesimg.setOnClickListener(onClickListener);

    }
    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.codypost_top_img:
                    Intent intent = new Intent(getApplicationContext(), SelectCategory.class);
                    intent.putExtra("category","top");
                    startActivityForResult(intent,REQUEST_CODE);
                    break;
                case R.id.codypost_bottom_img:
                    Intent intent1 = new Intent(getApplicationContext(), SelectCategory.class);
                    intent1.putExtra("category","bottom");
                    startActivityForResult(intent1,REQUEST_CODE);
                    break;
                case R.id.codypost_shoes_img:
                    Intent intent2 = new Intent(getApplicationContext(), SelectCategory.class);
                    intent2.putExtra("category","shoes");
                    startActivityForResult(intent2,REQUEST_CODE);
                    break;
            }
        }
    };
    @Override // intent를 통해 이미지를 성공적으로 받아 왔을 떄 처리
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Toast.makeText(getApplicationContext(),"수신 성공", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"수신 실패", Toast.LENGTH_SHORT).show();
        }
        if(requestCode==REQUEST_CODE){ //이미지를 성공적으로 받아왔을 때
            String comeback = data.getStringExtra("comeback");
            System.out.println(comeback);
        }

    }
}