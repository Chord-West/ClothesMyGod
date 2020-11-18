package com.example.clothesmygod.ui.mycody;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.clothesmygod.Model.PostData;
import com.example.clothesmygod.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class SelectAdpater extends BaseAdapter {

    private List<PostData> mDataList;
    private Context context;
    StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
    //SelectCategory 의 context와 data를 받기 위한 생성자 // (현서 11/14일 )
    public SelectAdpater(Context context,List<PostData> mDataList) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mycody_select_item,parent,false);
            ImageView closetImage = convertView.findViewById(R.id.select_item_img);
            TextView closetTitle= convertView.findViewById(R.id.select_item_title);
            CheckBox checkBox = convertView.findViewById(R.id.checkBox);
            holder.closetImage=closetImage;
            holder.closetTitle=closetTitle;
            holder.checkBox=checkBox;

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        PostData postdata = mDataList.get(position);
        holder.closetTitle.setText(postdata.getTitle());
        holder.checkBox.setChecked(postdata.isCheck());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean newState = !mDataList.get(position).isCheck();
                mDataList.get(position).setCheck(newState);

                System.out.println(newState);
            }
        });
        //선택된 아이템에 User의 UID의 옷 이름에 따라서  Storage의 똑같은 파일을 다운받아서 ImageView에 고정 (현서 11/14 )
        StorageReference clothesimgRef = mStorageRef.child("users").child(postdata.getUid()).child(postdata.getTitle()+".jpg");
        clothesimgRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful()){
                    Glide.with(context).load(task.getResult()).override(150,150).into(holder.closetImage);
                }else{

                }
            }
        });
        return convertView;
    }
    public class ViewHolder{
        ImageView closetImage;
        TextView closetTitle;
        CheckBox checkBox;
    }
}