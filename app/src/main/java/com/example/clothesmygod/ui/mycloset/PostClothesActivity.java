package com.example.clothesmygod.ui.mycloset;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.clothesmygod.R;
import com.google.firebase.storage.StorageReference;

public class PostClothesActivity extends AppCompatActivity {
    private StorageReference mStorageRef; // 파이어베이스 storage
    private RadioGroup radioGroup;
    private String closet_catrgory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_clothes);
        radioGroup = (RadioGroup) findViewById(R.id.post_radio_group);
        radioGroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);
    }

    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(checkedId==R.id.post_top_radio){
                closet_catrgory="top";
                Toast.makeText(PostClothesActivity.this, closet_catrgory, Toast.LENGTH_SHORT).show();
            }else if(checkedId==R.id.post_bottom_radio){
                closet_catrgory="bottom";
                Toast.makeText(PostClothesActivity.this, closet_catrgory, Toast.LENGTH_SHORT).show();
            } else if(checkedId==R.id.post_shoes_radio){
                closet_catrgory="shoes";
                Toast.makeText(PostClothesActivity.this, closet_catrgory, Toast.LENGTH_SHORT).show();
            }
        }
    };
}