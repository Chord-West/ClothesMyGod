package com.example.clothesmygod.ui.mycody;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.clothesmygod.Model.CodyItem;
import com.example.clothesmygod.R;
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

public class PostCodyActivity extends AppCompatActivity {
    private ImageView topimg;
    private ImageView bottomimg;
    private ImageView shoesimg;
    private EditText codytitle;
    private TextView toptitle;
    private TextView bottomtitle;
    private TextView shoestitle;
    private int REQUEST_CODE = 1001;
    private String category ;
    private FirebaseAuth mAuth; // 현재 유저정보 불러오기 위한 메소드
    FirebaseUser currentUser; // 현재 유저에 storage 저장
    FirebaseDatabase database; //User가 가지고있는 옷들 가져오기위한 작엄
    DatabaseReference userclothesRef;
    StorageReference mStorageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycody_postcody);
        topimg = findViewById(R.id.codypost_top_img);
        topimg.setOnClickListener(onClickListener);
        bottomimg = findViewById(R.id.codypost_bottom_img);
        bottomimg.setOnClickListener(onClickListener);
        shoesimg=findViewById(R.id.codypost_shoes_img);
        shoesimg.setOnClickListener(onClickListener);

        toptitle=findViewById(R.id.codypost_top_title);
        bottomtitle=findViewById(R.id.codypost_bottom_title);
        shoestitle=findViewById(R.id.codypost_shoes_title);
        codytitle=findViewById(R.id.codypost_edittitle);

        findViewById(R.id.codypost_complete_btn).setOnClickListener(onClickListener);
        findViewById(R.id.codypost_cancle_btn).setOnClickListener(onClickListener);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser(); // 로그인 되어 있는 정보

        database = FirebaseDatabase.getInstance();
        userclothesRef = database.getReference().child("users").child(currentUser.getUid());


    }
    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.codypost_top_img:
                    Intent intent = new Intent(getApplicationContext(), SelectCategory.class);
                    category="top";
                    intent.putExtra("category",category);
                    startActivityForResult(intent,REQUEST_CODE);
                    break;
                case R.id.codypost_bottom_img:
                    category="bottom";
                    Intent intent1 = new Intent(getApplicationContext(), SelectCategory.class);
                    intent1.putExtra("category",category);
                    startActivityForResult(intent1,REQUEST_CODE);
                    break;
                case R.id.codypost_shoes_img:
                    category="shoes";
                    Intent intent2 = new Intent(getApplicationContext(), SelectCategory.class);
                    intent2.putExtra("category",category);
                    startActivityForResult(intent2,REQUEST_CODE);
                    break;
                case R.id.codypost_complete_btn:
                    if(topimg!=null&&bottomimg!=null&&shoesimg!=null&&!codytitle.getText().toString().isEmpty()){
                        String key=codytitle.getText().toString();
                        CodyItem codyItem = new CodyItem(toptitle.getText().toString(),bottomtitle.getText().toString(),shoestitle.getText().toString());
                        Map<String, Object> postValues = codyItem.toMap();
                        Map<String, Object> childUpdates = new HashMap<>();
                        childUpdates.put(key, postValues);
                        userclothesRef.child("codylist").updateChildren(childUpdates);
                        userclothesRef.child("tmp_data").removeValue();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        MyCodyFragment myCodyFragment = new MyCodyFragment();
                        transaction.replace(R.id.frame, myCodyFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }else{
                        Toast.makeText(getApplicationContext(),"이미지를 모두 등록 해주세요", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.codypost_cancle_btn:
                    userclothesRef.child("tmp_data").removeValue();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    MyCodyFragment myCodyFragment = new MyCodyFragment();
                    transaction.replace(R.id.frame, myCodyFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
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
            String comeback = data.getStringExtra("comeback"); // 가져온 상의, 하의, 신발 중의 아이템 이름( 현서 11/12 )

            userclothesRef.child("tmp_data").child(category).setValue(comeback);
            ValueEventListener mValueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull final DataSnapshot snapshot) {
                    for (final DataSnapshot datasnapshot : snapshot.child("tmp_data").getChildren()) {
                        String clothes= datasnapshot.getValue().toString();  // 옷 이름
                        String key = datasnapshot.getKey();

                        StorageReference clothesimgRef = mStorageRef.child("users").child(currentUser.getUid()).child(clothes+".jpg");
                        if(key.equals("top")){
                            toptitle.setText(clothes);
                            clothesimgRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if(task.isSuccessful()){
                                        Glide.with(getApplicationContext()).load(task.getResult()).override(120,120).into(topimg);
                                    }else{

                                    }
                                }
                            });
                        }
                        if(key.equals("bottom")){
                            bottomtitle.setText(clothes);
                            clothesimgRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if(task.isSuccessful()){
                                        Glide.with(getApplicationContext()).load(task.getResult()).override(120,120).into(bottomimg);
                                    }else{

                                    }
                                }
                            });
                        }
                        if(key.equals("shoes")){
                            shoestitle.setText(clothes);
                            clothesimgRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if(task.isSuccessful()){
                                        Glide.with(getApplicationContext()).load(task.getResult()).override(120,120).into(shoesimg);
                                    }else{

                                    }
                                }
                            });
                        }


                    }



                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    System.out.println("error");
                }
            };
            userclothesRef.addValueEventListener(mValueEventListener);
        }



    }
}