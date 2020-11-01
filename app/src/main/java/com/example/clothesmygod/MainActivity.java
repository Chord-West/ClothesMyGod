package com.example.clothesmygod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.main_signup_btn).setOnClickListener(onClickListener); // 회원가입 버튼 클릭시 메소드 실행
        findViewById(R.id.main_signin_btn).setOnClickListener(onClickListener); // 로그인 버튼 클릭시 메소드 실행
    }


    //클릭 이벤트 Switch 문으로 넘겨진 id 값에 따라 메소드 처리 ( 현서 )
    View.OnClickListener onClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.main_signup_btn:
                    Intent intent = new Intent(MainActivity.this,SignUpActivitiy.class);
                    startActivity(intent);
                    break;
                case R.id.main_signin_btn:
                    break;
            }
        }
    };
}