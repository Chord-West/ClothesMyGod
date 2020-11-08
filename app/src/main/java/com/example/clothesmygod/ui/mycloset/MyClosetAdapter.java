package com.example.clothesmygod.ui.mycloset;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class MyClosetAdapter extends RecyclerView.Adapter<MyClosetAdapter.ViewHolder> {
    private final List<PostData> mDataList;
    FirebaseDatabase database;
    StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();

//    private  MyRecyclerViewClickListener mListener;

    public MyClosetAdapter(List<PostData> mDataList) {
        this.mDataList = mDataList;
    }

//    // 클릭 리스너
//    public interface MyRecyclerViewClickListener{
//        void onItemClicked(int position); // 클릭한 아이템 포지션
//        void onShareButtonClicked(int position);
//        void onLearnMoreButtonClicked(int position);
//    }


//    public void setOnClickListener(MyRecyclerViewClickListener listener){
//        mListener = listener;
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mycloset_card_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        PostData item = mDataList.get(position);
        holder.closettitle.setText(item.getTitle());
        StorageReference clothesimgRef = mStorageRef.child("users").child(item.getUid()).child(item.getTitle());
        clothesimgRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful()){
                    Glide.with(holder.itemView.getContext()).load(task.getResult()).override(150,150).into(holder.closetimage);
                }else{

                }
            }
        });
        System.out.println(clothesimgRef);



    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView closetimage;
        TextView closettitle;
        public ViewHolder(View itemView){
            super(itemView);
            closetimage = itemView.findViewById(R.id.card_item_img);
            closettitle = itemView.findViewById(R.id.card_item_title);
        }
    }

}
