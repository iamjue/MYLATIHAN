package com.emboxs.mylatihan.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.emboxs.mylatihan.R;

public class ActivityMyService5 extends AppCompatActivity implements View.OnClickListener {
    private Button btnStartService,btnStartIntentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service5);
        btnStartService = findViewById(R.id.btn_start_service);
        btnStartService.setOnClickListener(this);
        btnStartIntentService = findViewById(R.id.btn_start_intent_service);
        btnStartIntentService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start_service:
                Intent mStartServiceInten=new Intent(ActivityMyService5.this, OriginService5.class);
                startActivities(new Intent[]{mStartServiceInten});
                break;
            case R.id.btn_start_intent_service:
                Intent mStartIntentService=new Intent(ActivityMyService5.this, DicodingIntentService5.class);
                mStartIntentService.putExtra(DicodingIntentService5.EXTRA_DURATION, 5000);
                startActivities(new Intent[]{mStartIntentService});
                break;
        }
    }
}
