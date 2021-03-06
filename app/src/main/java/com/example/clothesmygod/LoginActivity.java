package com.example.clothesmygod;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.login_btn).setOnClickListener(onClickListener); // 로그인 버튼 클릭시 메소드 실행 ( 현서 11/3일 )
        findViewById(R.id.login_signup_btn).setOnClickListener(onClickListener); // 회원가입 버튼 클릭시 메소드 실행( 현서 11/3일 )

    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){ // 자동 로그인 ( 현서 11/3일 )
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }


    //클릭 이벤트 Switch 문으로 넘겨진 id 값에 따라 메소드 처리 ( 현서 11/3일 )
    View.OnClickListener onClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.login_signup_btn:
                    Intent intent = new Intent(LoginActivity.this,SignUpActivitiy.class);
                    startActivity(intent);
                    break;
                case R.id.login_btn:
                    login();
                    break;
            }
        }
    };
    private void login(){
        // 사용자의 아이디와 이메일  ( 현서 11/3일 )
        String email =  ((EditText)findViewById(R.id.login_email)).getText().toString();
        String password = ((EditText)findViewById(R.id.login_password)).getText().toString();
        if(email.length()>0&&password.length()>0) //null 값 체크
        {
            // 파이어베이스 Auth 로그인 메소드 ( 현서 11/3일 )
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // 로그인 성공시 ( 현서 11/3일 )
                            if (task.isSuccessful()) {
                                FirebaseUser user =mAuth.getCurrentUser();
                                String currentUserEmail=user.getEmail();

                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                            } else {
                                //로그인 실패시  ( 현서 11/3일 )
                                Toast.makeText(LoginActivity.this, "아이디와 비밀번호가 정확하지 않습니다.",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }else{
            Toast.makeText(LoginActivity.this, "이메일또는 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
        }
    }

}