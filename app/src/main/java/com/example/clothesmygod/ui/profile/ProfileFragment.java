package com.example.clothesmygod.ui.profile;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.clothesmygod.R;

import java.io.IOException;

public class ProfileFragment extends Fragment {
    int REQUEST_IMAGE_CODE =1001; // 이미지를 성공적으로 받아왔을 때 응답코드
    int REQUEST_EXTERNAL_STORAGE_PERMISSION = 1002; // 외부 저장소 성공적으로 연결시 응답코드
    ImageView userProfileImg;  // 유저 이미지
    private View view;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile,container,false);

        //  유저에게 갤러이에 접근할 수 있게 권한 요청하는 작업
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.READ_EXTERNAL_STORAGE)){

            }else{
                ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_EXTERNAL_STORAGE_PERMISSION);
            }

        }else{

        }
        userProfileImg=view.findViewById(R.id.profile_userimage);
        // 유저 이미지 클릭 했을 때 처리
        userProfileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Action 으로 저장된 이미지 URL 주소 불러오기
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,REQUEST_IMAGE_CODE);
            }
        });
        return view;
    }

    @Override
    // intent로 성공적으로 이미지를 받아왔을 때
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_IMAGE_CODE){ //이미지를 성공적으로 받아왔을 때
            Uri image = data.getData();
            try {
                //이미지를 비트맵으로 바꿔야 이미지 뷰에 삽입 가능
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),image);
                userProfileImg.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}