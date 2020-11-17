package com.example.clothesmygod.ui.mycody;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.clothesmygod.Model.CodyItem;
import com.example.clothesmygod.Model.PostData;
import com.example.clothesmygod.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class MyCodyAdapter extends BaseAdapter {
    private List<CodyItem> mDataList;
    private Context context;
    FirebaseDatabase database= FirebaseDatabase.getInstance(); // 데이터베이스 초기화
    DatabaseReference codylistRef;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance(); // 현재 유저정보 불러오기 위한 메소드
    FirebaseUser currentUser= mAuth.getCurrentUser();; // 현재 유저에 storage 저장
    StorageReference mStorageRef = FirebaseStorage.getInstance().getReference(); //firestroae 초기화

    public MyCodyAdapter(Context context,List<CodyItem> mDataList) {
        this.context = context;
        this.mDataList = mDataList;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mycody_item,parent,false);
            ImageView topImage = convertView.findViewById(R.id.cody_top_img);
            ImageView bottomImage = convertView.findViewById(R.id.cody_bottom_img);
            ImageView shoesImage = convertView.findViewById(R.id.cody_shoes_img);
            TextView topTitle= convertView.findViewById(R.id.cody_top_title);
            TextView bottomTitle= convertView.findViewById(R.id.cody_bottom_title);
            TextView shoesTitle= convertView.findViewById(R.id.cody_shoes_title);
            TextView codyTitle= convertView.findViewById(R.id.codylist_title);
            Button codydeletebtn =convertView.findViewById(R.id.codylist_delete_btn);
            holder.topImage=topImage;
            holder.bottomImage=bottomImage;
            holder.shoesImage=shoesImage;
            holder.topTitle=topTitle;
            holder.bottomTitle=bottomTitle;
            holder.shoesTitle=shoesTitle;
            holder.codyTitle=codyTitle;
            holder.codydeletebtn=codydeletebtn;

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        final CodyItem codyItem = mDataList.get(position);
        holder.topTitle.setText(codyItem.getTop());
        holder.bottomTitle.setText(codyItem.getBottom());
        holder.shoesTitle.setText(codyItem.getShoes());
        holder.codyTitle.setText(codyItem.getTitle());
        // 코디 삭제하기 위한 버튼
        holder.codydeletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("삭제").setMessage("정말 삭제하시겠습니까?").setCancelable(false);
                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        codylistRef = database.getReference().child("users").child(currentUser.getUid()).child("codylist");
                        codylistRef.child(codyItem.getTitle()).removeValue();
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
            }
        });

        StorageReference codytopRef = mStorageRef.child("users").child(currentUser.getUid()).child(codyItem.getTop()+".jpg");
        codytopRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful()){
                    Glide.with(context).load(task.getResult()).override(100,100).into(holder.topImage);
                }else{

                }
            }
        });
        StorageReference codybottomRef = mStorageRef.child("users").child(currentUser.getUid()).child(codyItem.getBottom()+".jpg");
        codybottomRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful()){
                    Glide.with(context).load(task.getResult()).override(100,100).into(holder.bottomImage);
                }else{

                }
            }
        });
        StorageReference codyshoesRef = mStorageRef.child("users").child(currentUser.getUid()).child(codyItem.getShoes()+".jpg");
        codyshoesRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful()){
                    Glide.with(context).load(task.getResult()).override(100,100).into(holder.shoesImage);
                }else{

                }
            }
        });
        return convertView;
    }
    public class ViewHolder{
        ImageView topImage;
        ImageView bottomImage;
        ImageView shoesImage;
        TextView topTitle;
        TextView bottomTitle;
        TextView shoesTitle;
        TextView codyTitle;
        Button codydeletebtn;
    }
}
