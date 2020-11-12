package com.example.clothesmygod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.clothesmygod.ui.mycloset.MyClosetFragment;
import com.example.clothesmygod.ui.mycody.MyCodyFragment;
import com.example.clothesmygod.ui.calendar.CalendarFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //프래그먼트 이동 버튼
        findViewById(R.id.main_mycody_btn).setOnClickListener(onClickListener);

        findViewById(R.id.main_mycloset_btn).setOnClickListener(onClickListener);
        findViewById(R.id.main_calendar_btn).setOnClickListener(onClickListener);
        setDefaultFragment(); // 첫번쩨 프래그먼트는 mycloset


    }
    //클릭 이벤트 Switch 문으로 넘겨진 id 값에 따라 메소드 처리 ( 현서 )
    View.OnClickListener onClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            switch (v.getId()){
                case R.id.main_mycody_btn:
                    MyCodyFragment myCodyFragment = new MyCodyFragment();
                    transaction.replace(R.id.frame, myCodyFragment);
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
            }
        }
    };
    public void setDefaultFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        MyClosetFragment myclosetFragment = new MyClosetFragment();
        transaction.add(R.id.frame,myclosetFragment);
        transaction.commit();
    }
}