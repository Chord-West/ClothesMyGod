package com.example.clothesmygod.ui.mycody;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clothesmygod.Model.PostData;
import com.example.clothesmygod.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class SelectCategory extends AppCompatActivity {

    //파이어 메소드 초기화 (현서 11/ 14일)
    private FirebaseAuth mAuth; // 현재 유저정보 불러오기 위한 메소드
    FirebaseUser currentUser; // 현재 유저에 storage 저장
    FirebaseDatabase database; //User가 가지고있는 옷들 가져오기위한 작엄
    DatabaseReference userclothesRef;
    StorageReference mStorageRef;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycody_select_category);
        //파이어 메소드 인스턴스화 (현서 11/ 14일)
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        userclothesRef = database.getReference().child("users").child(currentUser.getUid());

        // PostCodyActivity로 부터 받아온 Category 받기 위한 intent   (현서 11/ 14일)
        Intent intent = getIntent();
        final Button selectbtn = findViewById(R.id.select_complete_btn);

        // PostCodyActivity로 부터 받아온 Category  (현서 11/ 14일)
        final String category = intent.getExtras().getString("category");

        // PostCodyActivity로 부터 받아온 Category TextView에 매칭  (현서 11/ 14일)
        title=findViewById(R.id.select_category);
        title.setText(category);

        // Category별 아이템 선택하기 위한 Grid View(현서 11/ 14일)
        final GridView gridView = findViewById(R.id.select_gridview);

// 파이어베이스의 데이터베이스의 실시간 데이터를 받아옴 ( 현서 11/14일 )
        ValueEventListener mValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final ArrayList<PostData> dataList = new ArrayList<>();
                dataList.clear();

                // Category별 아이템 을 불러오는 메소드  ( 현서 11/14일 )
                for (final DataSnapshot datasnapshot : snapshot.child(category).getChildren()) { ;
                    final String clothes= datasnapshot.getValue().toString();  // 옷 이름
                    PostData postData = new PostData(currentUser.getUid(),clothes);
                    dataList.add(postData);
                }

                // Category별 아이템 생성하기 위한 adpater 생성   ( 현서 11/14일 )
                SelectAdpater adapter = new SelectAdpater(getApplicationContext(),dataList);
                gridView.setAdapter(adapter);
                selectbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int count=0;

                        PostData conveyData = new PostData();
                        for(PostData postdata : dataList){
                            if(postdata.isCheck()){
                                count+=1;
                                conveyData=postdata;
                            }
                        }
                        // 선택한 아이템이 2개 이상인 경우 Toast 메세지 출력   ( 현서 11/14일 )
                        if(count>1){
                            Toast.makeText(SelectCategory.this,"제품을 한개만 선택해주세요", Toast.LENGTH_SHORT).show();
                        }else if(count==1){
                            // 선택한 아이템이 1개인 경우 선택한 옷의 이름을 PostCodyActivity로 반환  ( 현서 11/14일 )
                            Intent intent = new Intent();
                            intent.putExtra("comeback",conveyData.getTitle());
                            setResult(RESULT_OK,intent);
                            finish();
                        }else{
                            Toast.makeText(SelectCategory.this,"제품을 선택해주세요", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("error");
            }
        };
        userclothesRef.addValueEventListener(mValueEventListener);

    }
}