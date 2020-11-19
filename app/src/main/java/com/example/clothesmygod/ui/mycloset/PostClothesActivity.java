package com.example.clothesmygod.ui.mycloset;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.clothesmygod.MainActivity;
import com.example.clothesmygod.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class PostClothesActivity extends AppCompatActivity {
    private StorageReference mStorageRef; // 파이어베이스 storage ( 현서 11/8일 )
    int REQUEST_IMAGE_CODE =1001; // 이미지를 성공적으로 받아왔을 때 응답코드 ( 현서 11/8일 )
    int REQUEST_EXTERNAL_STORAGE_PERMISSION = 1002; // 외부 저장소 성공적으로 연결시 응답코드 ( 현서 11/8일 )

    private RadioGroup radioGroup;  // 상의, 하의 , 신발 선택하기위한 라디오그룹 ( 현서 11/8일 )
    private String closet_catrgory; // 상의, 하의 , 신발 선택저장 ( 현서 11/8일 )

    private FirebaseAuth mAuth; // 현재 유저정보 불러오기 위한 메소드 ( 현서 11/8일 )
    FirebaseUser currentUser; // 현재 유저에 storage 저장 ( 현서 11/8일 )
    private DatabaseReference mDatabase;

    ImageView postImage; // 내 옷장에 업로드할 이미지 선택 ( 현서 11/8일 )
    Button postClothesBtn; // 마지막 등록 버튼 ( 현서 11/8일 )
    Uri image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycloset_post_clothes);
        radioGroup = (RadioGroup) findViewById(R.id.post_radio_group); // 상의, 하의 , 신발 선택하기위한 라디오그룹 ( 현서 11/8일 )
        radioGroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);  //  라디오그룹 클릭시  ( 현서 11/8일 )

        // 유저정보 인스턴스화  ( 현서 11/8일 )
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        // firebase storage 인스턴스화  ( 현서 11/8일 )
        mStorageRef = FirebaseStorage.getInstance().getReference();

 //         유저에게 갤러리에 접근할 수 있게 권한 요청하는 작업  ( 현서 11/8일 )
        if (ContextCompat.checkSelfPermission(PostClothesActivity.this, Manifest.permission.READ_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(PostClothesActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)){

            }else{
                ActivityCompat.requestPermissions(PostClothesActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_EXTERNAL_STORAGE_PERMISSION);
            }
        }else{

        }


        postImage=findViewById(R.id.post_clothes_img);//이미지 클릭했을때 받아오기  ( 현서 11/8일 )
        // 이미지 클릭 했을 때 처리  ( 현서 11/8일 )
        postImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Action 으로 저장된 이미지 URL 주소 불러오기  ( 현서 11/8일 )
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,REQUEST_IMAGE_CODE);
            }
        });

        postClothesBtn = findViewById(R.id.post_clothes_btn);// 등록버튼  ( 현서 11/8일 )
        postClothesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String postname =  ((EditText)findViewById(R.id.post_clothes_name)).getText().toString();

                if(closet_catrgory!=null&&postname!=null){
                    //         user//user.Uid(유저키) // 카테고리(상의,하의,신발) // 입력한 이름으로 저장  ( 현서 11/8일 )
                    StorageReference postRef = mStorageRef.child("users").child(currentUser.getUid()).child(postname+".jpg");

                    mDatabase.child("users").child(currentUser.getUid()).child(closet_catrgory).child(postname).setValue(postname);
                    mDatabase.child("users").child(currentUser.getUid()).child("all").child(postname).setValue(postname);

                    //(파이어베이스 메소드 )
                    postRef.putFile(image) // 이미지 파일 삽입  ( 현서 11/8일 )
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    System.out.println(taskSnapshot.toString()); //이미지 업로드 성공시 Main Activity로 이동 ( 현서 11/8일 )
                                    Intent intent = new Intent(PostClothesActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    //실패  ( 현서 11/8일 )
                                }
                            });
                }else{
                    //옷이나 카테고리가 Null 값일 경우 Toast 메세지 출력 ( 현서 11/8일 )
                    Toast.makeText(PostClothesActivity.this, "옷의 이름과 카테고리를 설정해주세요", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    //  라디오그룹 클릭시 처리메소드 ( 현서 11/8일 )
    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(checkedId==R.id.post_top_radio){
                //  파이어베이스 유저정보에 넣을 전역변수 카테고리 변경 ( 현서 11/8일 )
                closet_catrgory="top";
                Toast.makeText(PostClothesActivity.this, closet_catrgory, Toast.LENGTH_SHORT).show();
            }else if(checkedId==R.id.post_bottom_radio){
                closet_catrgory="bottom";
                Toast.makeText(PostClothesActivity.this, closet_catrgory, Toast.LENGTH_SHORT).show();
            } else if(checkedId==R.id.post_shoes_radio){
                closet_catrgory="shoes";
                Toast.makeText(PostClothesActivity.this, closet_catrgory, Toast.LENGTH_SHORT).show();
            }
        }
    };



    @Override // intent를 통해 이미지를 성공적으로 받아 왔을 떄 처리 ( 현서 11/8일 )
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_IMAGE_CODE){ //이미지를 성공적으로 받아왔을 때 ( 현서 11/8일 )
            image = data.getData(); //기기에서 받아온 이미지 데이터 ( 현서 11/8일 )
            try {
                //이미지를 비트맵으로 바꿔야 이미지 뷰에 삽입 가능 ( 현서 11/8일 )
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),image);
                postImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}