package com.example.clothesmygod.ui.profile;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

public class ProfileFragment extends Fragment {
    int REQUEST_IMAGE_CODE =1001; // 이미지를 성공적으로 받아왔을 때 응답코드
    int REQUEST_EXTERNAL_STORAGE_PERMISSION = 1002; // 외부 저장소 성공적으로 연결시 응답코드
    String currentUserEmail;
    private StorageReference mStorageRef; // 파이어베이스 storage
    File localFile; //이미지 저장되는 곳


    ImageView userProfileImg;  // 유저 이미지

    private View view;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile,container,false);

        // 공유환경에서 데이터 읽기
        SharedPreferences sharedPref = getActivity().getSharedPreferences("shared",Context.MODE_PRIVATE);
        currentUserEmail=sharedPref.getString("email","");
        System.out.println(currentUserEmail);

        mStorageRef = FirebaseStorage.getInstance().getReference(); //storage 인스턴스
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

        //Storage의 업로드했던파일 이미지뷰에 고정시키기 위해서 이미지 다운로드 (파이어베이스 메소드 )
        try {
            localFile = File.createTempFile("images", "jpg");
            StorageReference profileRef = mStorageRef.child("users").child(currentUserEmail).child("profile.jpg");

            profileRef.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            //이미지 뷰에 넣기위해 bitmap으로 형변환
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            userProfileImg.setImageBitmap(bitmap); //유저 이미지 변경
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle failed download
                    // ...
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

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


            StorageReference profileRef = mStorageRef.child("users").child(currentUserEmail).child("profile.jpg");

            //(파이어베이스 메소드 )
            profileRef.putFile(image) // 이미지 파일 삽입
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            System.out.println(taskSnapshot.toString()); //이미지 업로드 성공
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            //실패
                        }
                    });
        }

    }
}