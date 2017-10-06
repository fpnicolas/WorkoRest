package com.example.test1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ExpandedMenuView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.TextView;
import java.util.*;
import java.text.*;
import java.lang.*;

public class MainActivity extends AppCompatActivity {
//    private long everything;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final NumberPicker numPicker = (NumberPicker) this.findViewById(R.id.days);
        numPicker.setMaxValue(3);
        numPicker.setMinValue(1);
        final TextView tvResult = (TextView) this.findViewById(R.id.result);
        final DatePicker dpDate = (DatePicker) this.findViewById(R.id.date);
        final RadioButton work = (RadioButton) this.findViewById(R.id.work);
        final RadioButton rest = (RadioButton) this.findViewById(R.id.rest);
        rest.setChecked(true);
        Button btn = (Button) this.findViewById(R.id.enter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取NumberPicker的值，检查RadioButton的选择情况
                int base = numPicker.getValue();
                if (work.isChecked()) {
                    base += 3;
                }
                //使用calender类，将时、分、秒、毫秒设为零
                Calendar today = Calendar.getInstance();
                today.set(Calendar.HOUR_OF_DAY, 0);
                today.set(Calendar.MINUTE, 0);
                today.set(Calendar.SECOND, 0);
                today.set(Calendar.MILLISECOND, 0);

                //接收DatePicker的值为年月日，通过format转为Date类的实例
                int year = dpDate.getYear();
                int month = dpDate.getMonth() + 1;
                int day = dpDate.getDayOfMonth();
                Date thatDay;
                SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                String s = String.valueOf(year) + "- " + String.valueOf(month)+ "-" + String.valueOf(day);

                //用于记录结果
                String temp;

                try {
                    //转换，必须用到try
                    thatDay = fd.parse(s);

                    long time = thatDay.getTime() - today.getTime().getTime();
//                    everything = today.getTime().getTime();
                    switch ((int)((time/(24*3600*1000) + base-1)%6)) {
                        case 0:
                            temp = "休息第一天";
                            break;
                        case 1:
                            temp = "休息第二天";
                            break;
                        case 2:
                            temp = "休息第三天";
                            break;
                        case 3:
                            temp = "上班第一天";
                            break;
                        case 4:
                            temp = "上班第二天";
                            break;
                        case 5:
                            temp = "上班第三天";
                            break;
                        default:
                            temp = "请正确输入";
                    }
                    tvResult.setText(temp);
                }catch (Exception e){
                    tvResult.setText("请正确输入");

                }
//                finally {
//                    tvResult.setText(String.valueOf(everything));
//                }
            }
        });
    }
}
