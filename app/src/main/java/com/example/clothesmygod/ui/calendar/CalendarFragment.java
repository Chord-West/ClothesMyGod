package com.example.clothesmygod.ui.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.clothesmygod.Model.PostData;
import com.example.clothesmygod.R;
import java.util.ArrayList;


public class CalendarFragment extends Fragment {
    public CalendarView calendarView;
    public Button cha_Btn, del_Btn, get_Btn;
    public TextView memoTextView, textView2, title;
    public EditText contextEditText;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calendar, container, false);

        calendarView = v.findViewById(R.id.calendarView);
        memoTextView = v.findViewById(R.id.memoTextView);
        del_Btn = v.findViewById(R.id.del_Btn);
        cha_Btn = v.findViewById(R.id.cha_Btn);
        get_Btn = v.findViewById(R.id.get_Btn);
        textView2 = v.findViewById(R.id.textView2);
        title = v.findViewById(R.id.title);
        contextEditText = v.findViewById(R.id.contextEditText);



        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                contextEditText.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);
                cha_Btn.setVisibility(View.VISIBLE);
                del_Btn.setVisibility(View.VISIBLE);
                get_Btn.setVisibility(View.VISIBLE);
                memoTextView.setText(String.format("%d / %d / %d", year, month + 1, dayOfMonth));
                contextEditText.setText("");
                checkDay(year, month, dayOfMonth);

            }
        });



        return v;
    }

    public void checkDay(int cYear, int cMonth, int cDay) {

        try {
            cha_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    textView2.setText(contextEditText.getText());
                }
            });
            del_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    contextEditText.setText("");
                    textView2.setText(contextEditText.getText());
                }
            });
            get_Btn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Intent intent_list = new Intent(getActivity(), MyCodyListActivity.class);
                    startActivity(intent_list);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//public class CalendarFragment extends Fragment {
//    public CalendarView calendarView;
//    public Button cha_Btn,del_Btn;
//    public TextView memoTextView,textView2,title;
//    public EditText contextEditText;
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.fragment_calendar,container,false);
//
//        calendarView=v.findViewById(R.id.calendarView);
//        memoTextView=v.findViewById(R.id.memoTextView);
//        del_Btn=v.findViewById(R.id.del_Btn);
//        cha_Btn=v.findViewById(R.id.cha_Btn);
//        textView2=v.findViewById(R.id.textView2);
//        title=v.findViewById(R.id.title);
//        contextEditText=v.findViewById(R.id.contextEditText);
//
//
//        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                memoTextView.setText(String.format("%d / %d / %d",year,month+1,dayOfMonth));
//                contextEditText.setText("");
//                checkDay(year,month,dayOfMonth);
//            }
//        });
//        cha_Btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                cha_Btn.setVisibility(View.VISIBLE);
//                del_Btn.setVisibility(View.VISIBLE);
//                contextEditText.setVisibility(View.INVISIBLE);
//                textView2.setVisibility(View.VISIBLE);
//
//            }
//        });
//
//        return v;
//    }
//
//    public void checkDay(int cYear,int cMonth,int cDay){
//
//
//        try{
//
//            contextEditText.setVisibility(View.INVISIBLE);
//            textView2.setVisibility(View.VISIBLE);
//
//            cha_Btn.setVisibility(View.VISIBLE);
//            del_Btn.setVisibility(View.VISIBLE);
//
//            cha_Btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    contextEditText.setVisibility(View.VISIBLE);
//                    textView2.setVisibility(View.INVISIBLE);
//
//                    cha_Btn.setVisibility(View.INVISIBLE);
//                    del_Btn.setVisibility(View.INVISIBLE);
//                    textView2.setText(contextEditText.getText());
//                }
//
//            });
//            del_Btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    textView2.setVisibility(View.INVISIBLE);
//                    contextEditText.setText("");
//                    contextEditText.setVisibility(View.VISIBLE);
//                    cha_Btn.setVisibility(View.INVISIBLE);
//                    del_Btn.setVisibility(View.INVISIBLE);
//                }
//            });
//            if(textView2.getText()==null){
//                textView2.setVisibility(View.INVISIBLE);
//                memoTextView.setVisibility(View.VISIBLE);
//                cha_Btn.setVisibility(View.INVISIBLE);
//                del_Btn.setVisibility(View.INVISIBLE);
//                contextEditText.setVisibility(View.VISIBLE);
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//
//    @SuppressLint("WrongConstant")
//    public void removeDiary(String readDay){
//        FileOutputStream fos=null;
//
//        try{
////            fos=openFileOutput(readDay,MODE_NO_LOCALIZED_COLLATORS);
////            String content="";
////            fos.write((content).getBytes());
////            fos.close();
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @SuppressLint("WrongConstant")
//    public void saveDiary(String readDay){
//        FileOutputStream fos=null;
//
//        try{
////            fos=openFileOutput(readDay,MODE_NO_LOCALIZED_COLLATORS);
////            String content=contextEditText.getText().toString();
////            fos.write((content).getBytes());
////            fos.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//
//}