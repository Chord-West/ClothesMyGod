# Mobile Programmin Team Project



## ClothesMyGod (옷마이 갓)



### 목차

| 내용          |
| ------------- |
| 프로젝트 소개 |
| 기능          |
| 역할분담      |



### 프로젝트 소개

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
   ┃ ┃ ┃ ┃ ┃ ┣ 📜CodyItem.java
   ┃ ┃ ┃ ┃ ┃ ┣ 📜PostData.java
   ┃ ┃ ┃ ┃ ┃ ┗ 📜User.java
   ┃ ┃ ┃ ┃ ┣ 📂ui
   ┃ ┃ ┃ ┃ ┃ ┣ 📂board
   ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BoardFragment.java
   ┃ ┃ ┃ ┃ ┃ ┣ 📂calendar
   ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CalendarFragment.java
   ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MyCodyListActivity.java
   ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MyCodyListAdpater.java
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
   ┃ ┃ ┃ ┃ ┣ 📜SignUpActivitiy.java
   ┃ ┃ ┃ ┃ ┗ 📜SplashActivity.java
   ┣ 📂res
   ┃ ┣ 📂layout
   ┃ ┃ ┣ 📜activity_login.xml
   ┃ ┃ ┣ 📜activity_main.xml
   ┃ ┃ ┣ 📜activity_mycodylist.xml
   ┃ ┃ ┣ 📜activity_signup.xml
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
   ┃ ┣ 📂mipmap-anydpi-v26
   ┗ 📜AndroidManifest.xml
  
  ```

  

### 주요 기능

1. #### 로그인, 회원가입 ( Firebase Auth 사용 )

   

   - ##### 회원가입

     <div style="display:flex; justify-content:space-around;">
     	<img src="https://i.imgur.com/Bf97WeF.png" style="width:800px; height:400px;" />
     </div>

     

     - 회원가입 버튼 클릭시 Edittext가 null 값인 경우 "아이디와 비밀번호를 모두 입력해주세요." 라는 Toast 메세지 출력.

     - 회원가입 완료후 Firebase Authenctication에 UID와 함께 e-mail 형식으로 저장됨.

       

   - ##### 로그인

     - 로그인 성공시 FirebaseUser 메소드를 통해 CurrentUser의 UID를 통해 자동 로그인이 활성화 되고,  User의 정보를 불러옵니다.

     

2. #### 내 옷장

   - 메인
   - Firebase RealTimeDatabase, Storage 사용
     - User의 데이터베이스에 저장된 카테고리 별로 저장된 옷의 이름을 Storage와 연동시켜 화면에 출력
   - 옷 등록
     - Radix Group을 통해 카테고리 선택가능
     - 옷의 이름과 카테고리가 User 정보의 저장됨.

3. #### 나의 코디

   

4. #### 캘린더

   

5. #### 질문게시판

   

