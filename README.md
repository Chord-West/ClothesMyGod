## Mobile Programming Team Project 

**: Clothes My Got (옷마이갓 )**



#### 20175161 소프트웨어학과 김현서 @Chord.West

> - 데이터베이스 ( Firebase 연동 )
> - 로그인, 회원가입, 내 옷장, 나의 코디 



### 목차

| 내용          |
| ------------- |
| 프로젝트 소개 |
| 코드 구현     |
| 코드 실행     |



### 프로젝트 소개

---

- #### 개발 동기 및 목적

  > 사람들은 매일 아침마다 스타일 코디를 위해 착의하고 탈의하는 과정을 수없이 반복한다. 또한 어떤 사람들은 옷이 많아서 자신의 옷장에 어떤 옷들이 있는지 알 수 없고, 어떤 사람들은 일주일에 세네번 같은 옷을 입기도 한다. 우리는 사용자 개인 맞춤에 중점을 두어 사용자가 가지고 있는 옷들을 바탕으로 "오늘 뭐 입지?”, “저번에 이 친구 만날 때 뭐 입었지?” 라는 고민을 덜어주고자 한다.

  

- ####  개요

  > 옷장에 있는 옷들을 카메라로 촬영한뒤 핸드폰 어플 속에 저장해서  현재 보유하고 있는 옷들을 한눈에 
  >
  > 살펴볼 수 있고, 옷들의 조합을 코디리스트로 저장해서 관리할 수 있다.
  >
  > 그리고 캘린더를 통해 언제 옷을 입을지 정할 수 있고, 게시판을 통해 코디에 대한 질문도 할 수 있다. 
  
  
  
- #### 개발 환경

  > Compile Sdk Vesrion : 30 ( API 30 : Android 10.0 + (R))
  >
  > 언어 : Java 사용
  >
  > Build Tools Version : 30.0.2
  >
  > Android Gradle Plugin Vesrion : 4.0.1
  >
  > Gradle Version : 6.1.1
  >
  > AVD : Pixel 2 API 30
  >
  > 데이터 베이스 : Firebase 

  

- #### 프로젝트 구조

  ```
  📦main
   ┣ 📂java
   ┃ ┗ 📂com
   ┃ ┃ ┗ 📂example
   ┃ ┃ ┃ ┗ 📂clothesmygod
   ┃ ┃ ┃ ┃ ┣ 📂Model
   ┃ ┃ ┃ ┃ ┃ ┣ 📜Board.java
   ┃ ┃ ┃ ┃ ┃ ┣ 📜CodyItem.java
   ┃ ┃ ┃ ┃ ┃ ┣ 📜Comment.java
   ┃ ┃ ┃ ┃ ┃ ┗ 📜PostData.java
   ┃ ┃ ┃ ┃ ┣ 📂ui
   ┃ ┃ ┃ ┃ ┃ ┣ 📂board
   ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardActivity.java
   ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardAdapter.java
   ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardFragment.java
   ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentAdapter.java
   ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PostBoardActivity.java
   ┃ ┃ ┃ ┃ ┃ ┣ 📂calendar
   ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CalendarActivity.java
   ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CalendarFragment.java
   ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MyCodyListActivity.java
   ┃ ┃ ┃ ┃ ┃ ┣ 📂mycloset
   ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MyClosetAdapter.java
   ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MyClosetFragment.java
   ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PostClothesActivity.java
   ┃ ┃ ┃ ┃ ┃ ┗ 📂mycody
   ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MyCodyAdapter.java
   ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MyCodyFragment.java
   ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PostCodyActivity.java
   ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SelectAdpater.java
   ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SelectCategory.java
   ┃ ┃ ┃ ┃ ┣ 📜LoginActivity.java
   ┃ ┃ ┃ ┃ ┣ 📜MainActivity.java
   ┃ ┃ ┃ ┃ ┣ 📜MyFirebaseMessagingService.java
   ┃ ┃ ┃ ┃ ┣ 📜SignUpActivitiy.java
   ┃ ┃ ┃ ┃ ┗ 📜SplashActivity.java
   ┣ 📂res
   ┃ ┣ 📂layout
   ┃ ┃ ┣ 📜activity_board.xml
   ┃ ┃ ┣ 📜activity_calendar.xml
   ┃ ┃ ┣ 📜activity_login.xml
   ┃ ┃ ┣ 📜activity_main.xml
   ┃ ┃ ┣ 📜activity_mycodylist.xml
   ┃ ┃ ┣ 📜activity_signup.xml
   ┃ ┃ ┣ 📜board_list.xml
   ┃ ┃ ┣ 📜board_post.xml
   ┃ ┃ ┣ 📜comment_list.xml
   ┃ ┃ ┣ 📜fragment_board.xml
   ┃ ┃ ┣ 📜fragment_calendar.xml
   ┃ ┃ ┣ 📜fragment_mycloset.xml
   ┃ ┃ ┣ 📜fragment_mycody.xml
   ┃ ┃ ┣ 📜mycloset_card_item.xml
   ┃ ┃ ┣ 📜mycloset_post_clothes.xml
   ┃ ┃ ┣ 📜mycody_item.xml
   ┃ ┃ ┣ 📜mycody_postcody.xml
   ┃ ┃ ┣ 📜mycody_select_category.xml
   ┃ ┃ ┗ 📜mycody_select_item.xml
   ┗ 📜AndroidManifest.xml
   
  
  ```
  



### 코드구현

---



> 중요 코드 
>
> 1. LoginActivity.class
>
> 2. SignUpActivity.class
>
> 3. Main
>
>    1. mycloset
>
>       - MyClosetFragment.class
>       - PostClothesActivity.class
>
>    2. mycody
>
>       - MyCodyFragment.class
>
>       - PostCodyActivity.class
>
>       - SelectCategory.class
>
>         



#### 1. LoginActivity.class

``LoginActivity.class``에서는  EditText에서 받아온 데이터를 String 값으로 파이어베이스 Auth를 메소드를 통해 넘겨서 일치하는 아이디와 비밀번호이면   

```MainActivity.class``` 로 Intent를 통해 화면 이동, 일치하지 않으면 ```LoginActivity.class``` 에 "아이디와 비밀번호가 정확하지 않습니다." 메세지 출력. EditText

의 두개의 값이 모두 Null 값인 경우 "이메일또는 비밀번호를 입력해주세요" Toast 메세지 출력.

로그인 성공시 FirebaseUser 메소드를 통해 CurrentUser의 UID를 통해 자동 로그인이 활성화 되고,  User의 정보를 불러옴.

```java
 private void login(){
        // 사용자의 아이디와 이메일  ( 현서 11/3일 )
        String email =  ((EditText)findViewById(R.id.login_email)).getText().toString();
        String password = ((EditText)findViewById(R.id.login_password)).getText().toString();
        if(email.length()>0&&password.length()>0) //null 값 체크
        {// 파이어베이스 Auth 로그인 메소드 ( 현서 11/3일 )
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // 로그인 성공시 ( 현서 11/3일 )
                            if (task.isSuccessful()) {
                                FirebaseUser user =mAuth.getCurrentUser();
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
```



#### 2. SignUpActivity.class

`SignUpActivity.class`에서는 `sinUp()` 메소드를 통해서 회원가입 Firebase Auth 메소드를 통해 회원가입, 회원가입 성공시 FirebaseAuth에 등록.

비밀번호 불일치, null 값인 경우 Toast 메세지 출력하고, 일치할 경우 Firebase Auth에 uid 와 함께 email 형식으로 사용자 등록 및 

Intent를 통해 `LoginActivity.class`로 이동

```java
private void singUp(){
        String email =  ((EditText)findViewById(R.id.signup_email)).getText().toString();
        String password = ((EditText)findViewById(R.id.signup_password)).getText().toString();
        String passwordCheck = ((EditText)findViewById(R.id.signup_passconfirm)).getText().toString();
        if(email.length()>0&&password.length()>0&&passwordCheck.length()>0) //null 값 체크 ( 현서 11/1일 )
        { // 패스워드 같을 때만 회원가입 완료되게 ( 현서 11/1일 )
            if (password.equals(passwordCheck)) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) { // 회원가입 성공시 ( 현서 11/1일 )
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(SignUpActivitiy.this, "회원가입이 성공했습니다.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignUpActivitiy.this,LoginActivity.class);
                                    startActivity(intent);
                                } else {//회원가입 실패시 ( 현서 11/1일 )
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
```

<div>
    <div>
        <h3>
            FirebaseAuth에 등록
        </h3>
        <img src="https://user-images.githubusercontent.com/50789483/99766216-ff3e2c80-2b43-11eb-91f2-ade81ed06332.png" style="width:900px; height:300px;" />
    </div>
</div>





#### 3. Main

`Board` ,  `Calendar` ,   `mycloset` ,  `mycody` 4개의 Fragment로 분기해서 패키지별로  각각의 기능 구현 

- #### mycloset

  - ##### MyClosetFragment.class

    - User의 UID의  realtime database의 onDataChange 메소드를 통해 저장된 카테고리 별로 저장된 옷의 이름을 Storage와 연동시켜 화면에 출력 .

    - 삭제하고 싶은 경우 GridView 메소드인 setOnItemLongClickListener를 통해서  길게 클릭시 AlertDialogBuilder 객체를 생성해서 삭제할 것인지 

      물어보고,  삭제 클릭 시 StorageReference와 DatabaseReference 메소드를 통해서 유저의 정보와 파일을 동시에 삭제한다.

      ```java
      // 파이어베이스의 데이터베이스의 실시간 데이터를 받아옴 ( 현서 11/8일 )
              ValueEventListener mValueEventListener = new ValueEventListener() {
                  @Override
                  public void onDataChange(@NonNull DataSnapshot snapshot) {
                      final ArrayList<PostData> dataList = new ArrayList<>();
                      dataList.clear(); // 중복 출력을 막기위한 Data Clear  ( 현서 11/8일 )
                      for (final DataSnapshot datasnapshot : snapshot.child(category).getChildren()) { ;
                          final String clothes= datasnapshot.getValue().toString(); 
                          PostData postData = new PostData(currentUser.getUid(),clothes); 
                          dataList.add(postData);
                      }
                      MyClosetAdapter adapter = new MyClosetAdapter(getActivity(),dataList); 
                      gridView.setAdapter(adapter); 
                      gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                          @Override
                          public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                              final String delete =dataList.get(position).getTitle(); //길게 아이템 클릭시 아이템 정보 저장 ( 현서 11/16일 )
                              AlertDialog.Builder builder = new AlertDialog.Builder(getContext());  // Dialog 생성 ( 현서 11/16일 )
                              builder.setTitle("삭제").setMessage("정말 삭제하시겠습니까?").setCancelable(false);
                              // 삭제 클릭 시  선택된 아이템 정보 데이터베이스에서 삭제 ( 현서 11/16일 )
                              builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                                  @Override
                                  public void onClick(DialogInterface dialog, int which) {
                                      StorageReference closetRef = mStorageRef.child("users").child(currentUser.getUid()).child(delete+".jpg");
                                      closetRef.delete();
                                      userclothesRef.child("all").child(delete).removeValue();
                                      userclothesRef.child("top").child(delete).removeValue();
                                      userclothesRef.child("bottom").child(delete).removeValue();
                                      userclothesRef.child("shoes").child(delete).removeValue();
                                  }
                              }); // 취소 ( 현서 11/16일 )
                              builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                  @Override
                                  public void onClick(DialogInterface dialog, int which) {
                                      System.out.println("he");
                                      dialog.cancel();
                                  }
                              });
                              AlertDialog alertDialog = builder.create();
                              alertDialog.show();
                              return true;
                          }
                      });
      ```

    

    - `MyClosetAdapter.class` 의 Glide API 사용  ,GridView 의 Adapter에서 Glide API를 통해서 Firebase Storage의 저장된 파일들을 URI로 

      불러와서 User가 가진  카테고리의 옷이름과 일치하면 ViewHolder를 통해 ImageView에 고정시켜 화면에 출력시킴.  

      ```java
      //선택된 아이템에 User의 UID의 옷 이름에 따라서  Storage의 똑같은 파일을 다운받아서 ImageView에 고정 (현서 11/12 )
              StorageReference clothesimgRef = mStorageRef.child("users").child(postdata.getUid()).child(postdata.getTitle()+".jpg");
              clothesimgRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                  @Override
                  public void onComplete(@NonNull Task<Uri> task) {
                      if(task.isSuccessful()){
                          //Glide 메소드를 통해 다운받은 URI를  ImageView에 고정 (현서 11/12 )
                          Glide.with(context).load(task.getResult()).override(150,150).into(holder.closetImage);
                      }else{
                      }
                  }
              });
      ```

      <div>
          <div>
              <h5>유저 데이터</h5>
              <img src="https://i.imgur.com/t2uSs1v.png" style="width:600px; height:300px; margin-bottom : 50px" />
          </div>
          <div>
          	<h5>유저별 Storage</h5>
          	<img src="https://i.imgur.com/6nUutXo.png" style="width:600px; height:200px;" />
      	</div>
      </div>

    

  - ##### PostClothesActivity.class

    - 옷등록을 하기전에  ImageView에 핸드폰 갤러리에서 가져온 이미지를 고정 하기 위해서  먼저  유저에게 갤러리에 접근할 수 있게하는 권한 

      요청하는 READ_EXTERNAL_STORAGE을 실시.

      ```java
      //         유저에게 갤러리에 접근할 수 있게 권한 요청하는 작업  ( 현서 11/8일 )
      //(중략)
      if (ContextCompat.checkSelfPermission(PostClothesActivity.this, Manifest.permission.READ_CONTACTS) !=
                      PackageManager.PERMISSION_GRANTED) 
      {
       if(ActivityCompat.shouldShowRequestPermissionRationale(PostClothesActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)){
      }else{
           ActivityCompat.requestPermissions(PostClothesActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_EXTERNAL_STORAGE_PERMISSION);
       }
          }else{
              }
      //(중략)
      ```
      

      
- Allow 클릭시 갤러리에 접근 권한을 얻게 되고. 사진 클릭시 onActivityResult 메소드를 통해서 이미지를 성공적으로 받아 왔을 때 
    
  image를 bitmap으로 변환해서 `PostClothesActivity.class`에 있는 imageView에 선택한 이미지 삽입 
    
  <div>
          <img src="https://user-images.githubusercontent.com/50789483/99638031-03f1da80-2a89-11eb-8e58-bbbbe8d66280.PNG" style="width:400px; height:200px;" />	
          <img src="https://user-images.githubusercontent.com/50789483/99637980-ee7cb080-2a88-11eb-8dc5-23866fae9c14.PNG" style="width:400px; height:200px;" />
      </div>
    
  ```java
      @Override // intent를 통해 이미지를 성공적으로 받아 왔을 떄 처리 ( 현서 11/8일 )
          protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
              super.onActivityResult(requestCode, resultCode, data);
              if(requestCode==REQUEST_IMAGE_CODE){ //이미지를 성공적으로 받아왔을 때 ( 현서 11/8일 )
                  image = data.getData(); //기기에서 받아온 이미지 데이터 ( 현서 11/8일 )
                  try {
                      //이미지를 비트맵으로 바꿔야 이미지 뷰에 삽입 가능 ( 현서 11/8일 )
                      Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),image);
                      postImage.setImageBitmap(bitmap);
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
          }
      ```
  

  
- #### mycody

  - ##### MyCodyFragment.class

    - Firebase Realtime Database User의 UID에서 Child("codylist")에 CodyItem들을 객체를 불러와` MyCodyadapter.class`로 넘긴 뒤 

      Glide 객체를 통해서 카테고리별로 일치하는 이름을 Storage와 일치하면 Imageview와 TextView에 각각 고정 후  gridview를 

      통해 출력 

    - 삭제 버튼 클릭시  AlertDialogBuilder 객체를 생성해서 삭제할 것인지 물어보고, 삭제 클릭 시  User의 "codylist"에서 코디명과 일치하는 데이터 

      삭제후 파이어베이스 onDataChange 메소드에 적용.

      

      ```java
      //파이어베이스 메소드  인스턴스화  (현서 11/13일 )
              mStorageRef = FirebaseStorage.getInstance().getReference();
              mAuth = FirebaseAuth.getInstance();
              currentUser = mAuth.getCurrentUser(); // 로그인 되어 있는 정보 (현서 11/13일 )
              database = FirebaseDatabase.getInstance();
              codylistRef = database.getReference().child("users").child(currentUser.getUid()).child("codylist"); //코디리스트 (현서 11/13일 )
              final ArrayList<CodyItem> codylist = new ArrayList<>();      //코디 리스트를 위한 ArrayList 초기화 (현서 11/13일 )
              final GridView gridView = view.findViewById(R.id.mycody_gridview);
              // 파이어베이스의 데이터베이스의 실시간 데이터를 받아옴 ( 현서 11/13일 )
              ValueEventListener mValueEventListener = new ValueEventListener() {
                  @Override
                  public void onDataChange(@NonNull final DataSnapshot snapshot) {
                      codylist.clear(); // 중복방지 clear ( 현서 11/13일 )
                      for (final DataSnapshot datasnapshot : snapshot.getChildren()) {
                          CodyItem codyItem = datasnapshot.getValue(CodyItem.class);
                          codyItem.setTitle(datasnapshot.getKey());
                          // 등록된 코디리스트 ArrayList에 추가 ( 현서 11/13일 )
                          codylist.add(codyItem);
                      }
                      // adapter에 적용 ( 현서 11/13일 )
                      MyCodyAdapter adapter = new MyCodyAdapter(getActivity(),codylist);
                      gridView.setAdapter(adapter);
                  }
                  @Override
                  public void onCancelled(@NonNull DatabaseError error) {
                      System.out.println("error");
                  }
              };
              codylistRef.addValueEventListener(mValueEventListener);
              return view;
          }
      ```

      

  - ##### PostCodyActivity.class

    - 카테고리별로 ImageView를 클릭하면  `SelecActivity.class`로 넘어가서  User 데이터의  가진 옷들을 카테고리 별로 선택할 수 있게 

      하고 , `PostCodyActivity.class`에 카테고리 별로 적용

    - 코디 등록시 코디명과 함게 Firebase의 User의 UID에서 Child("codylist")에 Object형식으로 저장.

      ```java
      case R.id.codypost_complete_btn:
                          // 동록버튼 클릭시  (  현서 11/15 일 )
                          if(topimg!=null&&bottomimg!=null&&shoesimg!=null&&!codytitle.getText().toString().isEmpty()){
                              String key=codytitle.getText().toString(); // 코디 이름을 Key 값으로 저장 (  현서 11/ 15일 )
                              // 코디객체 생성 (  현서 11/ 15일 )
                              CodyItem codyItem = new CodyItem(toptitle.getText().toString(),bottomtitle.getText().toString(),shoestitle.getText().toString());
                              Map<String, Object> postValues = codyItem.toMap();
                              Map<String, Object> childUpdates = new HashMap<>();
                              childUpdates.put(key, postValues);
                              userclothesRef.child("codylist").updateChildren(childUpdates); // user / codylist 에  {top,bottom, shoes } 오브젝트 형식으로 저장  (  현서 11/15 일 )
                              userclothesRef.child("tmp_data").removeValue(); //일시적으로 생성했던 오브젝트 데이터 삭제 (  현서 11/15 일 )
                              // MyCodyFragment로 이동  (  현서 11/15 일 )
                              FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                              MyCodyFragment myCodyFragment = new MyCodyFragment();
                              transaction.replace(R.id.frame, myCodyFragment);
                              transaction.addToBackStack(null);
                              transaction.commit();
                          }else{
                              Toast.makeText(getApplicationContext(),"이미지를 모두 등록 해주세요", Toast.LENGTH_SHORT).show();
                          }
                          break;
      ```

      <div>
              <h5>저장된 코디리스트</h5>
              <img src="https://user-images.githubusercontent.com/50789483/99770028-94442400-2b4a-11eb-9b98-2ff19d7887ba.png" style="width:200px; height:100px;" />
          </div>

      

  - ##### SelectCategory.class

    - `PostCodyActivity.class`에서 click한 카테고리를 getExtra로 불러와서 Firebase `onDataChange()` 메소드를 통해 불러옴





### 코드 실행

---

> 1. 로그인, 회원가입
> 2. 내 옷장
> 3. 나의 코디



#### 1. 로그인, 회원가입 

- 파이어베이스 메소드를 통한 로그인, 회원가입

<div style="display:flex; justify-content : space-evenly;">
    <div style="display:felx; justify-content : center;">
        <h3>LoginActivity.java</h3>
        <img src="https://user-images.githubusercontent.com/50789483/99502579-9b3f2b00-29c0-11eb-8097-9f9f60bda919.png" style="width:300px; height:400px;" />
    </div>
    <div>
        <h3>SignUpActivity.java</h3>
        <img src="https://user-images.githubusercontent.com/50789483/99502215-1ce28900-29c0-11eb-8bbd-8069ebe2a83a.png" style="width:300px; height:400px;" />
    </div>
</div>





#### 2. 내 옷장


- Firebase RealTimeDatabase, Storage 사용


<div style="display:flex; justify-content : space-evenly;">
    <div>
        <h4>
            MyClosetFragment.java
        </h4>
        <img src="https://user-images.githubusercontent.com/50789483/99527109-9b9aee80-29df-11eb-905c-6af0be7e40dd.PNG" style="width:300px; height:400px;" />
    </div>
    <div>
        <h4>
            PostClothesActivity.java
        </h4>
        <img src="https://user-images.githubusercontent.com/50789483/99527159-af465500-29df-11eb-9628-58396ac78243.PNG" style="width:300px; height:400px;" />
    </div>
    <div>
        <h4>
            ItemLongClick
        </h4>
        <img src="https://user-images.githubusercontent.com/50789483/99527203-c5ecac00-29df-11eb-8193-dd4d985bc2c3.PNG" style="width:300px; height:400px;" />
    </div>
</div>



#### 3. 나의 코디

<div style="display:flex; justify-content : space-evenly;">
    <div>
        <h4>MyCodyFragment.java</h4>
        <img src="https://user-images.githubusercontent.com/50789483/99635942-11599580-2a86-11eb-9b2f-fef07b2a4681.PNG" style="width:300px; height:400px;" />
    </div>
    <div>
        <h4>PostCodyActivity.java</h4>
        <img src="https://user-images.githubusercontent.com/50789483/99635968-1f0f1b00-2a86-11eb-99c2-070e41d0eee6.PNG" style="width:300px; height:400px;" />
    </div>
    <div>
        <h4>삭제 버튼 클릭 시</h4>
        <img src="https://user-images.githubusercontent.com/50789483/99636011-2df5cd80-2a86-11eb-910f-f210f1f06dc0.PNG" style="width:300px; height:400px;" />
    </div>






### 차별성 및 발전할 점 