package com.example.clothesmygod.ui.calendar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

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
import java.util.List;

public class MyCodyListActivity extends AppCompatActivity {

    private FirebaseAuth mAuth; // 현재 유저정보 불러오기 위한 메소드
    FirebaseUser currentUser; // 현재 유저에 storage 저장
    FirebaseDatabase database; //User가 가지고있는 옷들 가져오기위한 작엄
    DatabaseReference codylistRef;
    StorageReference mStorageRef;

    private ListView listView;
    List fileList = new ArrayList<>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycodylist);

        listView= (ListView)findViewById(R.id.listview);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
//
//        adapter = new ArrayAdapter<String>(this, R.layout.activity_mycodylist, fileList);
//        listView.setAdapter(adapter);
//
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference databaseRef = database.getReference().child("users").child(currentUser.getUid());
//
//        // Read from the database
//        databaseRef.child("codylist").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                for (DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
//                    String str = fileSnapshot.child("filename").getValue(String.class);
//                    fileList.add(str);
//                }
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.w("TAG: ", "Failed to read value", databaseError.toException());
//            }
//        });
//

    }
}
