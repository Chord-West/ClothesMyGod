package com.example.clothesmygod;


import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    //스플래시 화면을 위한 activity      (정현구) 11월3일
    //splash.xml을 만들어 styles.xml을통해 theme으로 보여주고 화면넘김       (정현구) 11월3일
    //기존에는 handler방식으로 작성했지만 자원낭비를 줄이기 위해서 개선       (정현구) 11월3일
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
