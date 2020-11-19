package com.example.clothesmygod.ui.board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.clothesmygod.Model.Board;
import com.example.clothesmygod.Model.Comment;
import com.example.clothesmygod.R;

import java.util.List;

public class CommentAdapter extends BaseAdapter {
    List<Comment> data;
    CommentAdapter(List<Comment> data){
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_list,parent,false);
            TextView CommentAuthor = convertView.findViewById(R.id.comment_list_author);
            TextView CommentContent = convertView.findViewById(R.id.comment_list_content);
            holder.commentAuthor = CommentAuthor;
            holder.commentContent = CommentContent;

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }
        Comment comment = data.get(position);
        holder.commentAuthor.setText(comment.getAuthor());
        holder.commentContent.setText(comment.getContent());
        return convertView;
    }
    public class ViewHolder{
        TextView commentAuthor;
        TextView commentContent;
    }
}
