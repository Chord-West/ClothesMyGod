package com.example.clothesmygod.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothesmygod.Model.PostData;
import com.example.clothesmygod.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashboardAdapter extends BaseAdapter {
    private List<PostData> mData;
    private Map<String,Integer> mdataImageMap;

    public DashboardAdapter(List<PostData> data){
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_item,parent,false);
            ImageView dashboardImage = convertView.findViewById(R.id.dashboard_item_image);
            TextView dashboardTitle= convertView.findViewById(R.id.dashboard_item_title);
            TextView dashboardContent= convertView.findViewById(R.id.dashboard_item_content);
            CheckBox checkBox = convertView.findViewById(R.id.dashboard_item_checkBox);
            holder.dashboardImage=dashboardImage;
            holder.dashboardTitle=dashboardTitle;
            holder.dashboardContent= dashboardContent;
            holder.checkBox=checkBox;
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        PostData postData = mData.get(position);
        holder.dashboardTitle.setText(postData.getTitle());
        holder.dashboardContent.setText(postData.getContents());
        holder.dashboardImage.setImageResource(mdataImageMap.get(postData.getContents()));
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
        ImageView dashboardImage;
        TextView dashboardTitle;
        TextView dashboardContent;
        CheckBox checkBox;
    }
}
