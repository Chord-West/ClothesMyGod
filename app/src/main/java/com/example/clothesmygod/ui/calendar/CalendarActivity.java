package com.example.clothesmygod.ui.calendar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.clothesmygod.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
//by 최나라 (20-11-15)
public class CalendarActivity extends AppCompatActivity {

    public String fname=null;
    public String str=null;

    public CalendarView calendarView;
    public Button cha_Btn,del_Btn,save_Btn, get_Btn;
    public TextView dayTextView,textView2,title;
    public EditText contextEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView=findViewById(R.id.calendarView);
        dayTextView=findViewById(R.id.dayTextView);
        save_Btn=findViewById(R.id.save_Btn);
        del_Btn=findViewById(R.id.del_Btn);
        cha_Btn=findViewById(R.id.cha_Btn);
        get_Btn=findViewById(R.id.get_Btn);
        textView2=findViewById(R.id.textView2);
        title=findViewById(R.id.title);
        contextEditText=findViewById(R.id.contextEditText);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @SuppressLint("DefaultLocale")
            @Override
            //CalendarView의 임의의 날짜를 클릭했을 때 진행되는 함수
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //layout의 visibility를 설정
                dayTextView.setVisibility(View.VISIBLE);
                save_Btn.setVisibility(View.VISIBLE);
                contextEditText.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.INVISIBLE);
                cha_Btn.setVisibility(View.INVISIBLE);
                del_Btn.setVisibility(View.INVISIBLE);

                dayTextView.setText(String.format("%d / %d / %d",year,month+1,dayOfMonth)); //dayTextView에 현재 클릭한 날짜를 보여준다.
                contextEditText.setText("");
                checkDay(year,month,dayOfMonth);    //checkDay함수 실행
            }
        });
        //save_Btn을 눌렀을 때 진행
        save_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDiary(fname);   //saveDiary함수 실행
                str=contextEditText.getText().toString();   //contextEditText에 저장되어 있는 글을 str에 저장
                textView2.setText(str);                     //str을 textView2에 보여줌
                //layout의 visibility 설정
                save_Btn.setVisibility(View.INVISIBLE);
                cha_Btn.setVisibility(View.VISIBLE);
                del_Btn.setVisibility(View.VISIBLE);
                contextEditText.setVisibility(View.INVISIBLE);
                textView2.setVisibility(View.VISIBLE);

            }
        });
        //get_Btn을 눌렀을 때 진행
        get_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //나의 저장된 코디 목록을 보여주기 위해 MyCodyListActivity로 화면 전환
                Intent intent_list = new Intent(getApplicationContext(), MyCodyListActivity.class);
                startActivity(intent_list);
            }
        });
    }

    public void checkDay(int cYear,int cMonth,int cDay){
        fname=""+cYear+"-"+(cMonth+1)+""+"-"+cDay+".txt";//저장할 파일 이름설정한다. 날짜마다 다른 이름을 가진다.
        FileInputStream fis=null;

        try{
            //fname으로 file을 만들고, 이를 이진화하여 str에 저장한다.
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
                }

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

        }catch (Exception e){
            e.printStackTrace();
        }
    }

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

}