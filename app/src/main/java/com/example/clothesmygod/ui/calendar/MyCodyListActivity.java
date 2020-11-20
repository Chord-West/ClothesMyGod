package com.example.clothesmygod.ui.calendar;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.clothesmygod.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

//by 최나라 (20-11-18)
public class MyCodyListActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;
    FirebaseUser currentUser;

    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycodylist);

        listView = (ListView) findViewById(R.id.codylist);
        initDatabase();
        //simple_dropdown_item_1line은 문자열들을 보여주는 리스트 모양이다.
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<String>());
        listView.setAdapter(adapter);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser(); //로그인 되어 있는 정보
        mReference = mDatabase.getReference("users").child(currentUser.getUid()).child("codylist"); //현재 유저의 코디리스트 정보
        //데이터 베이스의 실시간 정보를 가져옴
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //중복을 방지하기 위해 clear사용
                adapter.clear();
                for(DataSnapshot listData : dataSnapshot.getChildren()){
                    //cody목록을 가져와 adapter 배열에 추가한다.
                    String cody = listData.getKey();
                    adapter.add(cody);
                }
                adapter.notifyDataSetChanged();
                //listView에 adapterdp 담은 정보를 설정한다.
                listView.setSelection(adapter.getCount()-1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    //by 최나라 (20-11-19)
    private void initDatabase() {

        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("log");
        mReference.child("log").setValue("check");
        mChild = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mReference.addChildEventListener(mChild);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mReference.removeEventListener(mChild);
    }
}
