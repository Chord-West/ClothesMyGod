package com.example.clothesmygod.ui.board;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.clothesmygod.Model.Board;
import com.example.clothesmygod.Model.PostData;
import com.example.clothesmygod.R;
import com.example.clothesmygod.ui.mycody.MyCodyAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class BoardAdapter extends BaseAdapter {
    List<Board> data;
    BoardAdapter(List<Board> data){
        this.data=data;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_list,parent,false);
            TextView boardAuthor = convertView.findViewById(R.id.board_list_author);
            TextView boardTitle = convertView.findViewById(R.id.board_list_title);
            TextView boardContent = convertView.findViewById(R.id.board_list_content);
            holder.boardAuthor = boardAuthor;
            holder.boardTitle = boardTitle;
            holder.boardContent = boardContent;

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }
        Board board = data.get(position);
        holder.boardAuthor.setText(board.getAuthor());
        holder.boardTitle.setText(board.getTitle());
        holder.boardContent.setText(board.getContent());
        return convertView;
    }
    public class ViewHolder{
        TextView boardAuthor;
        TextView boardTitle;
        TextView boardContent;
    }
}
