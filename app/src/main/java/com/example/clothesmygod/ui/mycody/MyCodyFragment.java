package com.example.clothesmygod.ui.mycody;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.clothesmygod.Model.CodyItem;
import com.example.clothesmygod.R;
import com.example.clothesmygod.ui.board.PostBoardActivity;
import com.example.clothesmygod.ui.mycloset.PostClothesActivity;
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

import java.util.ArrayList;

public class MyCodyFragment extends Fragment {
    private View view;

    //파이어베이스 메소드 선언 (현서 11/13일 )
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseDatabase database;
    DatabaseReference codylistRef;
    StorageReference mStorageRef;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mycody,container,false);
        view.findViewById(R.id.mycody_post_btn).setOnClickListener(onClickListener);

        //파이어베이스 메소드  인스턴스화  (현서 11/13일 )
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser(); // 로그인 되어 있는 정보 (현서 11/13일 )
        database = FirebaseDatabase.getInstance();
        codylistRef = database.getReference().child("users").child(currentUser.getUid()).child("codylist"); //코디리스트 (현서 11/13일 )


        final ArrayList<CodyItem> codylist = new ArrayList<>();      //코디 리스트를 위한 ArrayList 초기화 (현서 11/13일 )
        final GridView gridView = view.findViewById(R.id.mycody_gridview);

        // 파이어베이스의 데이터베이스의 실시간 데이터를 받아옴 ( 현서 11/13일 )
        ValueEventListener mValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot snapshot) {
                codylist.clear(); // 중복방지 clear ( 현서 11/13일 )
                for (final DataSnapshot datasnapshot : snapshot.getChildren()) {
                    CodyItem codyItem = datasnapshot.getValue(CodyItem.class);
                    codyItem.setTitle(datasnapshot.getKey());
                    // 등록된 코디리스트 ArrayList에 추가 ( 현서 11/13일 )
                    codylist.add(codyItem);
                }

                // adapter에 적용 ( 현서 11/13일 )
                MyCodyAdapter adapter = new MyCodyAdapter(getActivity(),codylist);

                gridView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("error");
            }
        };
        codylistRef.addValueEventListener(mValueEventListener);
        return view;
    }
    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.mycody_post_btn:
                    Intent intent = new Intent(getActivity(), PostCodyActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

}