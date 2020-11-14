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
    public Button cha_Btn,del_Btn,cody_save_Btn;
    public TextView memoTextView,textView2,title;
    public EditText contextEditText;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calendar,container,false);
        calendarView=view.findViewById(R.id.calendarView);
        memoTextView=view.findViewById(R.id.memoTextView);
        cody_save_Btn=view.findViewById(R.id.cody_save_Btn);
        del_Btn=view.findViewById(R.id.del_Btn);
        textView2=view.findViewById(R.id.textView2);
        title=view.findViewById(R.id.title);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView canlendarview , int year, int month, int dayOfMonth) {
                System.out.println(year+"년"+month+"월"+dayOfMonth+"일");
                memoTextView.setText(year+"년"+month+"월"+dayOfMonth+"일");
                cody_save_Btn.setVisibility(view.VISIBLE);
            }
        });
        cody_save_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDiary(fname);
                str=contextEditText.getText().toString();
                textView2.setText(str);


            }
        });


        return view;
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
