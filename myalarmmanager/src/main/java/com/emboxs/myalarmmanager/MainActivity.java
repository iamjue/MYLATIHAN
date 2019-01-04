package com.emboxs.myalarmmanager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvOneTimeDate, tvOneTimeTime;
    private TextView tvRepeatingTime;
    private EditText edtOneTimeMessage, edtRepeatingMessage;
    private Button btnOneTimeDate, btnOneTimeTime, btnOneTime,btnRepeatingTime,btnRepeating,btnCancalRepeatingAlarm;
    private Calendar calOneTimeDate, calOneTimeTime, calRepeatTimeTime;
    private AlarmReceiver alarmReceiver;
    private AlarmPreference alarmPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvOneTimeDate =findViewById(R.id.tv_one_time_alarm_date);
        tvOneTimeTime=findViewById(R.id.tv_one_time_alarm_time);
        edtOneTimeMessage =findViewById(R.id.edt_one_time_alarm_message);
        btnOneTimeDate =findViewById(R.id.btn_one_time_alarm_date);
            btnOneTimeDate.setOnClickListener(this);
        btnOneTimeTime=findViewById(R.id.btn_one_time_alarm_time);
            btnOneTimeTime.setOnClickListener(this);
        btnOneTime =findViewById(R.id.btn_set_one_time_alarm);
            btnOneTime.setOnClickListener(this);
        tvRepeatingTime =findViewById(R.id.tv_repeating_alarm_time);
        edtRepeatingMessage =findViewById(R.id.edt_repeating_alarm_message);
        btnRepeatingTime=findViewById(R.id.btn_repeating_time_alarm_time);
            btnRepeatingTime.setOnClickListener(this);
        btnRepeating=findViewById(R.id.btn_repeating_time_alarm);
            btnRepeating.setOnClickListener(this);
        btnCancalRepeatingAlarm =findViewById(R.id.btn_cancel_repeating_alarm);
            btnCancalRepeatingAlarm.setOnClickListener(this);

        calOneTimeDate =Calendar.getInstance();
        calOneTimeTime=Calendar.getInstance();
        calRepeatTimeTime =Calendar.getInstance();

        alarmPreference = new AlarmPreference(this);
        alarmReceiver = new AlarmReceiver();

        if(!TextUtils.isEmpty(alarmPreference.getOneTimeDate())){
            setOneTimeText();
        }
        if (!TextUtils.isEmpty(alarmPreference.getRepeatingTime())){
            setRepeatingText();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_one_time_alarm_date){
            final Calendar currentDate = Calendar.getInstance();
            new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    calOneTimeDate.set(year,monthOfYear,dayOfMonth);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    tvOneTimeDate.setText(dateFormat.format(calOneTimeDate.getTime()));
                }
            },currentDate.get(Calendar.YEAR),currentDate.get(Calendar.MONTH),currentDate.get(Calendar.DATE)).show();
        }
        if (v.getId()==R.id.btn_one_time_alarm_time){
            final Calendar currentDate =Calendar.getInstance();
            new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    calOneTimeTime.set(Calendar.HOUR_OF_DAY,hourOfDay);
                    calOneTimeTime.set(Calendar.MINUTE, minute);
                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH : mm");
                    tvOneTimeTime.setText(timeFormat.format(calOneTimeTime.getTime()));
                    //Log.v(TAG,"the choosen one "+ date.getTime());
                }
            },currentDate.get(Calendar.HOUR_OF_DAY),currentDate.get(Calendar.MINUTE),true).show();
        }
        else if (v.getId()==R.id.btn_repeating_time_alarm_time){
            final Calendar currentDate =Calendar.getInstance();
            new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    calRepeatTimeTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    calRepeatTimeTime.set(Calendar.MINUTE, minute);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
                    tvRepeatingTime.setText(dateFormat.format(calRepeatTimeTime.getTime()));
                    //Log.v(TAG,"the choosen one "+ date.getTime());
                }
            },currentDate.get(Calendar.HOUR_OF_DAY),currentDate.get(Calendar.MINUTE),true).show();
        }
        else if (v.getId()==R.id.btn_set_one_time_alarm){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String oneTimeDate = dateFormat.format(calOneTimeDate.getTime());

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            String oneTimeTime =timeFormat.format(calOneTimeTime.getTime());
            String oneTimeMessage = edtOneTimeMessage.getText().toString();

            alarmPreference.setOneTimeDate(oneTimeDate);
            alarmPreference.setOneTimeMessage(oneTimeMessage);
            alarmPreference.setOneTimeTime(oneTimeTime);

            alarmReceiver.setOneTimeAlarm(this, AlarmReceiver.TYPE_ONE_TIME,
                    alarmPreference.getOneTimeDate(),
                    alarmPreference.getOneTimeTime(),
                    alarmPreference.getOneTimeMessage());
        }
        else if (v.getId()==R.id.btn_repeating_time_alarm){
            SimpleDateFormat timeFormat =new SimpleDateFormat("HH:mm");
            String repeatTimeTime = timeFormat.format(calRepeatTimeTime.getTime());
            String reapeatTimeMessage =edtRepeatingMessage.getText().toString();
            alarmPreference.setRepeatingTime(repeatTimeTime);
            alarmPreference.setRepeatingMessage(reapeatTimeMessage);

            alarmReceiver.setRepeatingAlarm(this, AlarmReceiver.TYPE_REPEATING,
                    alarmPreference.getRepeatingTime(),
                    alarmPreference.getRepeatingMessage());
        }
        else if (v.getId()==R.id.btn_cancel_repeating_alarm){
            alarmReceiver.cancelAlarm(this, AlarmReceiver.TYPE_REPEATING);
        }
    }
    private void setOneTimeText(){
        tvOneTimeTime.setText(alarmPreference.getOneTimeTime());
        tvOneTimeDate.setText(alarmPreference.getOneTimeDate());
        edtOneTimeMessage.setText(alarmPreference.getOneTimeMessage());
    }
    private void  setRepeatingText(){
        tvRepeatingTime.setText(alarmPreference.getRepeatingTime());
        edtRepeatingMessage.setText(alarmPreference.getRepeatingMessage());
    }
}
