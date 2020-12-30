package com.example.clothesmygod.ui.mycloset;

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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.clothesmygod.Model.PostData;
import com.example.clothesmygod.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class MyClosetAdapter extends BaseAdapter {
    private List<PostData> mDataList;
    private Context context;
    FirebaseDatabase database;

    StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
    //Mycloset의 context와 data를 받기 위한 생성자 // (현서 11/8 )
    public MyClosetAdapter(Context context,List<PostData> mDataList) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mycloset_card_item,parent,false);
            ImageView closetImage = convertView.findViewById(R.id.card_item_img);
            TextView closetTitle= convertView.findViewById(R.id.card_item_title);
            holder.closetImage=closetImage;
            holder.closetTitle=closetTitle;
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        //선택된 아이템에 PostData를 가져옴 // (현서 11/12 )
        PostData postdata = mDataList.get(position);
        holder.closetTitle.setText(postdata.getTitle());
        //선택된 아이템에 User의 UID의 옷 이름에 따라서  Storage의 똑같은 파일을 다운받아서 ImageView에 고정 (현서 11/12 )
        StorageReference clothesimgRef = mStorageRef.child("users").child(postdata.getUid()).child(postdata.getTitle()+".jpg");
        clothesimgRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful()){
                    //Glide 메소드를 통해 다운받은 URI를  ImageView에 고정 (현서 11/12 )
                    Glide.with(context).load(task.getResult()).override(150,150).into(holder.closetImage);
                }else{
                }
            }
        });
        return convertView;
    }
    //Mycloset의 data를 item에 고정시키기 위한 view holder // (현서 11/12 )
    public class ViewHolder{
        ImageView closetImage;
        TextView closetTitle;
    }
}
