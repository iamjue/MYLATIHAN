package com.emboxs.mylatihan.broadcastReceiver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.emboxs.mylatihan.R;

public class ActivityBroadcastHome6 extends AppCompatActivity implements View.OnClickListener {
    private Button btnSms, btnEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_home6);
        btnSms =(Button)findViewById(R.id.btn_sms);
        btnSms.setOnClickListener(this);
        btnEvent =(Button)findViewById(R.id.btn_event);
        btnEvent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sms:
                Intent intentSms = new Intent(this, ActivitySmsListenerApp6.class);
                startActivities(new Intent[]{intentSms});
                break;
            case R.id.btn_event:
                Intent intentEvent = new Intent(this, ActivityBroadcastEvent6.class);
                startActivities(new Intent[]{intentEvent});
                break;
        }
    }
}
