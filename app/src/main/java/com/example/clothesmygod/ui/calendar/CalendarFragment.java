package com.example.clothesmygod.ui.calendar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.clothesmygod.R;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CalendarFragment extends Fragment {

    private View view;
    public String fname=null;
    public String str=null;
    public CalendarView calendarView;
    public Button cha_Btn,del_Btn,save_Btn;
    public TextView memoTextView,textView2,title;
    public EditText contextEditText;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calendar,container,false);
        calendarView=view.findViewById(R.id.calendarView);
        memoTextView=view.findViewById(R.id.memoTextView);
        save_Btn=view.findViewById(R.id.save_Btn);
        del_Btn=view.findViewById(R.id.del_Btn);
        cha_Btn=view.findViewById(R.id.cha_Btn);
        textView2=view.findViewById(R.id.textView2);
        title=view.findViewById(R.id.title);
        contextEditText=view.findViewById(R.id.contextEditText);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(@NonNull CalendarView view2, int year, int month, int dayOfMonth) {
                memoTextView.setVisibility(view2.VISIBLE);
                save_Btn.setVisibility(view2.VISIBLE);
                contextEditText.setVisibility(view2.VISIBLE);
                textView2.setVisibility(view2.INVISIBLE);
                cha_Btn.setVisibility(view2.INVISIBLE);
                del_Btn.setVisibility(view2.INVISIBLE);
                memoTextView.setText(String.format("%d / %d / %d",year,month+1,dayOfMonth));
                contextEditText.setText("");
                checkDay(year,month,dayOfMonth);

            }
        });
        save_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDiary(fname);
                str=contextEditText.getText().toString();
                textView2.setText(str);
                save_Btn.setVisibility(View.INVISIBLE);
                cha_Btn.setVisibility(View.VISIBLE);
                del_Btn.setVisibility(View.VISIBLE);
                contextEditText.setVisibility(View.INVISIBLE);
                textView2.setVisibility(View.VISIBLE);

            }
        });

        return view;
    }

    public void checkDay(int cYear,int cMonth,int cDay){
//        fname=""+cYear+"-"+(cMonth+1)+""+"-"+cDay+".txt";//저장할 파일 이름설정
//        FileInputStream fis=null;//FileStream fis 변수

        try{
//
//            fis=openFileInput(fname);
//
//            byte[] fileData=new byte[fis.available()];
//            fis.read(fileData);
//            fis.close();
//
//            str=new String(fileData);

            contextEditText.setVisibility(View.INVISIBLE);
            textView2.setVisibility(View.VISIBLE);
            textView2.setText(str);

            save_Btn.setVisibility(View.INVISIBLE);
            cha_Btn.setVisibility(View.VISIBLE);
            del_Btn.setVisibility(View.VISIBLE);

            cha_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    contextEditText.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.INVISIBLE);
                    contextEditText.setText(str);

                    save_Btn.setVisibility(View.VISIBLE);
                    cha_Btn.setVisibility(View.INVISIBLE);
                    del_Btn.setVisibility(View.INVISIBLE);
                    textView2.setText(contextEditText.getText());
                }

            });
            del_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    textView2.setVisibility(View.INVISIBLE);
                    contextEditText.setText("");
                    contextEditText.setVisibility(View.VISIBLE);
                    save_Btn.setVisibility(View.VISIBLE);
                    cha_Btn.setVisibility(View.INVISIBLE);
                    del_Btn.setVisibility(View.INVISIBLE);
                    removeDiary(fname);
                }
            });
            if(textView2.getText()==null){
                textView2.setVisibility(View.INVISIBLE);
                memoTextView.setVisibility(View.VISIBLE);
                save_Btn.setVisibility(View.VISIBLE);
                cha_Btn.setVisibility(View.INVISIBLE);
                del_Btn.setVisibility(View.INVISIBLE);
                contextEditText.setVisibility(View.VISIBLE);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @SuppressLint("WrongConstant")
    public void removeDiary(String readDay){
        FileOutputStream fos=null;

        try{
//            fos=openFileOutput(readDay,MODE_NO_LOCALIZED_COLLATORS);
//            String content="";
//            fos.write((content).getBytes());
//            fos.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @SuppressLint("WrongConstant")
    public void saveDiary(String readDay){
        FileOutputStream fos=null;

        try{
//            fos=openFileOutput(readDay,MODE_NO_LOCALIZED_COLLATORS);
//            String content=contextEditText.getText().toString();
//            fos.write((content).getBytes());
//            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
