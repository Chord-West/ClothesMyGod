package com.example.clothesmygod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.clothesmygod.ui.board.BoardFragment;
import com.example.clothesmygod.ui.mycloset.MyClosetFragment;
import com.example.clothesmygod.ui.mycody.MyCodyFragment;
import com.example.clothesmygod.ui.calendar.CalendarFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //프래그먼트 이동 버튼 (현서 11/6일 )
        findViewById(R.id.main_mycody_btn).setOnClickListener(onClickListener);
        findViewById(R.id.main_mycloset_btn).setOnClickListener(onClickListener);
        findViewById(R.id.main_calendar_btn).setOnClickListener(onClickListener);
        findViewById(R.id.main_board_btn).setOnClickListener(onClickListener);
        setDefaultFragment(); // 첫번쩨 프래그먼트는 mycloset (현서 11/6일 )
        Button logout_btn = (Button)findViewById(R.id.logout_btn);


        logout_btn.setOnClickListener(new View.OnClickListener() { //로그아웃을 위한 함수 (현구)
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });

    }
    //클릭 이벤트 Switch 문으로 넘겨진 id 값에 따라 메소드 처리 (현서 11/6일 )
    View.OnClickListener onClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            //프래그먼트 객체 생성후  버튼 클릭시 id 값에 따라 각각 프래그먼트로 이동 (현서 11/6일 )
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            switch (v.getId()){
                case R.id.main_mycody_btn:
                    MyCodyFragment myCodyFragment = new MyCodyFragment();
                    transaction.replace(R.id.frame, myCodyFragment); //프래그먼트 이동 (현서 11/6일 )
                    transaction.addToBackStack(null);
                    transaction.commit();
                    break;
                case R.id.main_mycloset_btn:
                    MyClosetFragment myclosetFragment = new MyClosetFragment();
                    transaction.replace(R.id.frame,myclosetFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    break;
                case R.id.main_calendar_btn:
                    CalendarFragment calendarFragment = new CalendarFragment();
                    transaction.replace(R.id.frame, calendarFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    break;
                case R.id.main_board_btn:
                    BoardFragment boardFragment = new BoardFragment();
                    transaction.replace(R.id.frame, boardFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    break;
            }
        }
    };

    public void setDefaultFragment(){
        // 첫번쩨 프래그먼트는 mycloset 로 하기 위한 작업 (현서 11/6일 )
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        MyClosetFragment myclosetFragment = new MyClosetFragment();
        transaction.add(R.id.frame,myclosetFragment);
        transaction.commit();
    }
}