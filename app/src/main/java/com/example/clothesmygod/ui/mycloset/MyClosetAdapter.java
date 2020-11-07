package com.example.clothesmygod.ui.mycloset;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clothesmygod.Model.PostData;
import com.example.clothesmygod.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyClosetAdapter extends BaseAdapter {
    private List<PostData> mData;
    private Map<String,Integer> mdataImageMap;

    public MyClosetAdapter(List<PostData> data){
        this.mData=data;
        mdataImageMap = new HashMap<>();
        mdataImageMap.put("test1", R.drawable.ic_dashboard_black_24dp);
        mdataImageMap.put("test2",R.drawable.ic_home_black_24dp);
        mdataImageMap.put("test3",R.drawable.ic_notifications_black_24dp);
    }

    @Override
    public int getCount() { return mData.size(); }

    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mycloset_item,parent,false);
            ImageView myclosetImage = convertView.findViewById(R.id.mycloset_item_image);
            TextView myclosetTitle= convertView.findViewById(R.id.mycloset_item_title);
            TextView myclosetContent= convertView.findViewById(R.id.mycloset_item_content);
            CheckBox checkBox = convertView.findViewById(R.id.mycloset_item_checkBox);
            holder.myclosetImage=myclosetImage;
            holder.myclosetTitle=myclosetTitle;
            holder.myclosetContent= myclosetContent;
            holder.checkBox=checkBox;
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        PostData postData = mData.get(position);
        holder.myclosetTitle.setText(postData.getTitle());
        holder.myclosetContent.setText(postData.getContents());
        holder.myclosetImage.setImageResource(mdataImageMap.get(postData.getContents()));
        holder.checkBox.setChecked(postData.isCheck());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean newState = !mData.get(position).isCheck();
                mData.get(position).setCheck(newState);
                System.out.println(newState);
            }
        });
        return convertView;
    }
    static class ViewHolder{
        ImageView myclosetImage;
        TextView myclosetTitle;
        TextView myclosetContent;
        CheckBox checkBox;
    }
}
