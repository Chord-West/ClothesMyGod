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

  

- ####  개발 환경 

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

  

### 기능

### 

### 