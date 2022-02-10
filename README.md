# Mobile Programmin Team Project

## ClothesMyGot (옷마이 갓)

### 목차
| 내용             |
| ---------------- |
| 프로젝트 소개    |
| 주요 기능        |
| 내가 개발한 기능 |

### 프로젝트 소개

---
* #### 개발 동기 및 목적


> 사람들은 매일 아침마다 스타일 코디를 위해 착의하고 탈의하는 과정을 수없이 반복한다. 또한 어떤 사람들은 옷이 많아서 자신의 옷장에 어떤 옷들이 있는지 알 수 없고, 어떤 사람들은 일주일에 세네번 같은 옷을 입기도 한다. 우리는 사용자 개인 맞춤에 중점을 두어 사용자가 가지고 있는 옷들을 바탕으로 "오늘 뭐 입지?”, “저번에 이 친구 만날 때 뭐 입었지?” 라는 고민을 덜어주고자 한다.

* #### 개요


> 옷장에 있는 옷들을 카메라로 촬영한뒤 핸드폰 어플 속에 저장해서  현재 보유하고 있는 옷들을 한눈에살펴볼 수 있고, 옷들의 조합을 코디리스트로 저장해서 관리할 수 있다.그리고 캘린더를 통해 언제 옷을 입을지 정할 수 있고, 게시판을 통해 코디에 대한 질문도 할 수 있다.* #### 개발 환경


> Compile Sdk Vesrion : 30 ( API 30 : Android 10.0 + (R))언어 : Java 사용Build Tools Version : 30.0.2Android Gradle Plugin Vesrion : 4.0.1Gradle Version : 6.1.1AVD : Pixel 2 API 30데이터 베이스 : Firebase

* #### 프로젝트 구조


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

### 주요 기능

---
* 로그인 및 회원 가입

* 카테고리별 옷 등록하기

* 등록한 옷을 바탕으로 코디 등록하기

* 캘린더에 저장하기

* 질문게시판

    

### 내가 개발한 주요 기능 ( 캘린더, 푸시 알림)

---
* 캘린더
	* CalendarFragment
	* CalendarActivity
	* MyCodyListActivity

* 
푸쉬 알림

	* MyFirebaseMessagingService

1. CalendarFragmet

-CalendarFragment에서는 간단한 이미지와 버튼을 통해 사용자가 직접 달력에 코디를 기록할 수 있다는 것을 알려준다.

```Java
    //calendarActivity로 이동하기 위한 함수
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.cal_Btn:
                    Intent intent = new Intent(getActivity(), CalendarActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
```

-OnClickLister함수를 통해 cal_Btn을 클릭하면 새로운 Intent를 생성하고 이 intent를 통해 CalendarActivity로 이동한다.

2. CalendarActivity

   

-CarlendarActivity에서는 CalendarView를 통해 달력을 보여준다. 달력에 임의의 날짜를 클릭하면 해당 날짜에 메모를 적을 수 있고 이 메모를 수정하거나 삭제할 수 있다. 이 기능을 통해 누구를 만났을 때, 어느 옷을 입었는 지 기록을 할 수 있다.

```java
public void checkDay(int cYear,int cMonth,int cDay){
    fname=""+cYear+"-"+(cMonth+1)+""+"-"+cDay+".txt";//저장할 파일 이름설정한다. 날짜마다 다른 이름을 가진다.
    FileInputStream fis=null;

    try{
        //fname으로 file을 만들고, 이를 byte로 str에 저장한다.
        fis=openFileInput(fname);
        byte[] fileData=new byte[fis.available()];
        fis.read(fileData);
        fis.close();
        str=new String(fileData);

        contextEditText.setVisibility(View.INVISIBLE);
        textView2.setVisibility(View.VISIBLE);
        //textView2에 str을 보여준다.
        textView2.setText(str);

        save_Btn.setVisibility(View.INVISIBLE);
        cha_Btn.setVisibility(View.VISIBLE);
        del_Btn.setVisibility(View.VISIBLE);
        //cha.Btn을 눌렀을 때 진행
        cha_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //layout의 visibility 설정
                contextEditText.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.INVISIBLE);
                save_Btn.setVisibility(View.VISIBLE);
                cha_Btn.setVisibility(View.INVISIBLE);
                del_Btn.setVisibility(View.INVISIBLE);
                //수정한 내역으로 다시 설정한다.
                contextEditText.setText(str);
                textView2.setText(contextEditText.getText());
            });            
          //del_Btn을 눌렀을 때 진행
            del_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //layout의 visibility 설정
                    textView2.setVisibility(View.INVISIBLE);
                    cha_Btn.setVisibility(View.INVISIBLE);
                    del_Btn.setVisibility(View.INVISIBLE);
                    contextEditText.setVisibility(View.VISIBLE);
                    save_Btn.setVisibility(View.VISIBLE);
                    contextEditText.setText(str);
                    //removeDiary함수 실행
                    removeDiary(fname);
                }
            });
            //만약 textView2에 아무 값이 없다면 초기의 화면과 같게 layout의 visibility를 설정한다.
            if(textView2.getText()==null){
                textView2.setVisibility(View.INVISIBLE);
                cha_Btn.setVisibility(View.INVISIBLE);
                del_Btn.setVisibility(View.INVISIBLE);
                dayTextView.setVisibility(View.VISIBLE);
                save_Btn.setVisibility(View.VISIBLE);
                contextEditText.setVisibility(View.VISIBLE);
            }
```

-calendarView의 날짜를 클릭했을 때 실행되도록 구현한 checkDay 함수다.  클릭한 날짜를 String형태로 fname에 저장한다. FileInputStream을 통해 fname이 가르키는 파일을 만들고 fileData에 저장하여 read한 후 String변수 str에 저장한다.   -cha_Btn을 눌렀을 때 수정된 내역을 contextEditText에 현재 str이 보여지며 수정을 할 수 있다.
-del_Btn을 눌렀을 때는 removeDiary(fname) 함수를 실행한다.
-만약 textView2에 입력값이 없다면 초기의 화면과 같게 layout의 visibility를 설정한다.

```java
@SuppressLint("WrongConstant")
//content를 빈 스트링으로 설정하여 fos에 저장한다.
public void removeDiary(String readDay){
    FileOutputStream fos=null;
    try{
        fos=openFileOutput(readDay,MODE_NO_LOCALIZED_COLLATORS);
        String content="";
        fos.write((content).getBytes());
        fos.close();

    }catch (Exception e){
        e.printStackTrace();
    }
}
//content에 contextEditText에 저장되어 있는 글을 저장하여 fos에 저장한다.
@SuppressLint("WrongConstant")
public void saveDiary(String readDay){
    FileOutputStream fos=null;
    try{
        fos=openFileOutput(readDay,MODE_NO_LOCALIZED_COLLATORS);
        String content=contextEditText.getText().toString();
        fos.write((content).getBytes());
        fos.close();
    }catch (Exception e){
        e.printStackTrace();
    }
}
```

-saveDiary는 save_Btn을 눌렀을 때 실행되는 함수이다. 날짜 정보를 가진 String인 fname을 인자로 가지며 Fileoutputstream을 통해 날짜별로 내부 저장장치에 파일을 생성하여 쓴다.

-removeDiary는 del_Btn을 눌렀을 때 실행되는 함수이다. 날짜 정보를 가진 String인 fname을 인자로 가지며 fos에 빈 스트링 ""을 작성한다. 즉 기존에 작성했던 것을 지운다고 볼 수 있다.

```java
get_Btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        //나의 저장된 코디 목록을 보여주기 위해 MyCodyListActivity로 화면 전환
        Intent intent_list = new Intent(getApplicationContext(), MyCodyListActivity.class);
        startActivity(intent_list);
    }
});
```

-get_Btn을 누르면 MyCodyListActivity로 이동하기 위한 Intent를 생성하고 실행한다.

3. MyCodyListActivity

-MyCodyListActivity에서는 사용자가 저장한 코디 목록을 ListView형태로 보여준다. 사용자가 그 날 입은 코디를 등록하려고 할 때 한 눈에 코디 목록을 확인할 수 있다.

```java
        //simple_dropdown_item_1line은 문자열들을 보여주는 리스트 모양이다.
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<String>());
        listView.setAdapter(adapter);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser(); //로그인 되어 있는 정보
        mReference = mDatabase.getReference("users").child(currentUser.getUid()).child("codylist"); //현재 유저의 코디리스트 정보
        //데이터 베이스의 실시간 정보를 가져옴
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //중복을 방지하기 위해 clear사용
                adapter.clear();
                for(DataSnapshot listData : dataSnapshot.getChildren()){
                    //cody목록을 가져와 adapter 배열에 추가한다.
                    String cody = listData.getKey();
                    adapter.add(cody);
                }
                adapter.notifyDataSetChanged();
                //listView에 adapter에 담은 정보를 설정한다.
                listView.setSelection(adapter.getCount()-1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
```

-currentUser는 현재 유저 정보를 담고 있으며 mReference는 현재 유저의 코디 리스트 정보를 담고 있다. mReference에는 현재 유저가 가지고 있는 "codylist"의 child를 가지고 있다.  데이터 베이스의 실시간 정보를 가져오기 위해 addValueEventListener를 사용하고 adapter 배열에 중복을 방지하기 위해 adapter.clear()를 통해 초기화를 시켜준다. Cody 목록을 가져와 adapter.add(cody)해주고 ListView에 설정해준다.

```java
private void initDatabase() {

    mDatabase = FirebaseDatabase.getInstance();
    mReference = mDatabase.getReference("log");
    mReference.child("log").setValue("check");
    mChild = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        }
        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        }
        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
        }
        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {
        }
    };
    mReference.addChildEventListener(mChild);
}
@Override
protected void onDestroy() {
    super.onDestroy();
    mReference.removeEventListener(mChild);
}
```

-목록을 다루는 애플리케이션은 단일 객체에 사용되는 값 이벤트가 아닌 하위 이벤트를 수신 대기해야 하는데 이를 위해서 ChileEventListtner를 연결한다.

4. MyFirebaseMessagingService



-Firebase의 cloud Messaging 기능을 이용하여 푸시 알람을 만든다. 알람을 통해 사용자가 잊지 않고 달력에 다음 날 입을 코디를 생각하고 기록할 수 있게 한다.
