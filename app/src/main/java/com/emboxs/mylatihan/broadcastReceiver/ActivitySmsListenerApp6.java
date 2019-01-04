package com.emboxs.mylatihan.broadcastReceiver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.emboxs.mylatihan.R;

public class ActivitySmsListenerApp6 extends AppCompatActivity implements View.OnClickListener {
    private TextView tvSmsFrom,tvSmsMessage;
    private Button btnCloseSms;
    public static final String EXTRA_SMS_NO ="extra_sms_no";
    public static final String EXTRA_SMS_MESSAGE ="extra_sms_message";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_listener_app6);
        tvSmsFrom = (TextView)findViewById(R.id.tv_no);
        tvSmsMessage = (TextView)findViewById(R.id.tv_message);
        btnCloseSms = (Button)findViewById(R.id.btn_closeSms);
        btnCloseSms.setOnClickListener(this);
        String senderNo = getIntent().getStringExtra(EXTRA_SMS_NO);
        String senderMessage = getIntent().getStringExtra(EXTRA_SMS_MESSAGE);
        tvSmsFrom.setText("from : "+senderNo);
        tvSmsMessage.setText(senderMessage);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_closeSms){
            finish();
        }
    }
}
