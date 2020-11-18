package com.example.clothesmygod;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivitiy extends AppCompatActivity {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance(); //파이어베이스 인증 연동 ( 현서 11/1일 )
    private static final String TAG = "SignUpActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        findViewById(R.id.signup_btn).setOnClickListener(onClickListener); // 회원가입 버튼 클릭시 메소드 실행 ( 현서 11/1일 )
    }


    //클릭 이벤트 Switch 문으로 넘겨진 id 값에 따라 메소드 처리 ( 현서 11/1일 )
    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.signup_btn:
                    singUp();
                    break;
            }
        }
    };
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    private void singUp(){
        String email =  ((EditText)findViewById(R.id.signup_email)).getText().toString();
        String password = ((EditText)findViewById(R.id.signup_password)).getText().toString();
        String passwordCheck = ((EditText)findViewById(R.id.signup_passconfirm)).getText().toString();

        if(email.length()>0&&password.length()>0&&passwordCheck.length()>0) //null 값 체크 ( 현서 11/1일 )
        {
            // 패스워드 같을 때만 회원가입 완료되게 ( 현서 11/1일 )
            if (password.equals(passwordCheck)) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // 회원가입 성공시 ( 현서 11/1일 )
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(SignUpActivitiy.this, "회원가입이 성공했습니다.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignUpActivitiy.this,LoginActivity.class);
                                    startActivity(intent);
                                } else {
                                    //회원가입 실패시 ( 현서 11/1일 )
                                    Toast.makeText(SignUpActivitiy.this, "회원가입이 실패했습니다.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } else {
                Toast.makeText(SignUpActivitiy.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(SignUpActivitiy.this, "이메일또는 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
        }

    }
}