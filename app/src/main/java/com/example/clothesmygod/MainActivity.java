package com.example.clothesmygod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.clothesmygod.ui.dashboard.DashboardFragment;
import com.example.clothesmygod.ui.home.HomeFragment;
import com.example.clothesmygod.ui.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //프래그먼트 이동 버튼
        findViewById(R.id.main_home_btn).setOnClickListener(onClickListener);
        findViewById(R.id.main_dashbord_btn).setOnClickListener(onClickListener);
        findViewById(R.id.main_profile_btn).setOnClickListener(onClickListener);

    }
    //클릭 이벤트 Switch 문으로 넘겨진 id 값에 따라 메소드 처리 ( 현서 )
    View.OnClickListener onClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            switch (v.getId()){
                case R.id.main_home_btn:
                    HomeFragment homeFragment = new HomeFragment();
                    transaction.replace(R.id.frame,homeFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    break;
                case R.id.main_dashbord_btn:
                    DashboardFragment dashboardFragment = new DashboardFragment();
                    transaction.replace(R.id.frame,dashboardFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    break;
                case R.id.main_profile_btn:
                    ProfileFragment profileFragment = new ProfileFragment();
                    transaction.replace(R.id.frame,profileFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    break;
            }
        }
    };
}