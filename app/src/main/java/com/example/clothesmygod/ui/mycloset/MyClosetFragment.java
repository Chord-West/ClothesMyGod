package com.example.clothesmygod.ui.mycloset;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

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

public class MyClosetFragment extends Fragment{
    private View view;
    StorageReference mStorageRef;
    private String category = "all"; // 기본 default

    private FirebaseAuth mAuth; // 현재 유저정보 불러오기 위한 메소드
    FirebaseUser currentUser; // 현재 유저에 storage 저장
    FirebaseDatabase database; //User가 가지고있는 옷들 가져오기위한 작엄
    DatabaseReference userclothesRef;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mycloset,container,false);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();

        userclothesRef = database.getReference().child("users").child(currentUser.getUid());


        view.findViewById(R.id.mycloset_post_btn).setOnClickListener(onClickListener); // 옷등록 버튼




        // 등록된 옷을 나열하기 위한 grid view
        final GridView gridView = view.findViewById(R.id.mycloset_gridview);


        ValueEventListener mValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final ArrayList<PostData> dataList = new ArrayList<>();
                dataList.clear();
                for (final DataSnapshot datasnapshot : snapshot.child(category).getChildren()) { ;
                    final String clothes= datasnapshot.getValue().toString();
                    PostData postData = new PostData(currentUser.getUid(),clothes);
                    dataList.add(postData);
                }
                MyClosetAdapter adapter = new MyClosetAdapter(getActivity(),dataList);
                gridView.setAdapter(adapter);
                gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        final String delete =dataList.get(position).getTitle();
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("삭제").setMessage("정말 삭제하시겠습니까?").setCancelable(false);
                        builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                StorageReference closetRef = mStorageRef.child("users").child(currentUser.getUid()).child(delete);
                                closetRef.delete();
                                userclothesRef.child("all").child(delete).removeValue();
                                userclothesRef.child("top").child(delete).removeValue();
                                userclothesRef.child("bottom").child(delete).removeValue();
                                userclothesRef.child("shoes").child(delete).removeValue();
                            }
                        });
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.out.println("he");
                                dialog.cancel();
                            }
                        });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        return true;
                    }
                });


                view.findViewById(R.id.mycloset_all_btn).setOnClickListener(onClickListener);
                view.findViewById(R.id.mycloset_top_btn).setOnClickListener(onClickListener);
                view.findViewById(R.id.mycloset_bottom_btn).setOnClickListener(onClickListener);
                view.findViewById(R.id.mycloset_shoes_btn).setOnClickListener(onClickListener);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("error");
            }
        };
        userclothesRef.addValueEventListener(mValueEventListener);

        return view;
    }
    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.mycloset_post_btn:
                    Intent intent = new Intent(getActivity(),PostClothesActivity.class);
                    startActivity(intent);
                    break;
                case R.id.mycloset_all_btn:
                    category="all";
                    userclothesRef.child("change").setValue(category);
                    break;
                case R.id.mycloset_top_btn:
                    category="top";
                    userclothesRef.child("change").setValue(category);
                    break;
                case R.id.mycloset_bottom_btn:
                    category="bottom";
                    userclothesRef.child("change").setValue(category);
                    break;
                case R.id.mycloset_shoes_btn:
                    category="shoes";
                    userclothesRef.child("change").setValue(category);
                    break;
            }
        }
    };
}