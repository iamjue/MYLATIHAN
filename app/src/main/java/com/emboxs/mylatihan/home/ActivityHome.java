package com.emboxs.mylatihan.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.emboxs.mylatihan.Intent.MainActivityIntent;
import com.emboxs.mylatihan.asyncTask.ActivityAsyncTask4;
import com.emboxs.mylatihan.activity.ActivityBarVolume1;
import com.emboxs.mylatihan.broadcastReceiver.ActivityBroadcastHome6;
import com.emboxs.mylatihan.fragment.ActivityMyFlexibleFragment3;
import com.emboxs.mylatihan.service.ActivityMyService5;
import com.emboxs.mylatihan.R;

public class ActivityHome extends AppCompatActivity implements View.OnClickListener {
    Button bActivity,bFragment,bAsynTask,bService,bReceiver,bIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bActivity = (Button)findViewById(R.id.bActivity);
        bActivity.setOnClickListener(this);
        bFragment =(Button)findViewById(R.id.bFragment);
        bFragment.setOnClickListener(this);
        bAsynTask =(Button)findViewById(R.id.bAsyncTask);
        bAsynTask.setOnClickListener(this);
        bService =(Button)findViewById(R.id.bService);
        bService.setOnClickListener(this);
        bReceiver =(Button)findViewById(R.id.bReceiver);
        bReceiver.setOnClickListener(this);
        bIntent = findViewById(R.id.bIntent);
        bIntent.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bActivity:
                Intent iActivity =new Intent(ActivityHome.this , ActivityBarVolume1.class);
                startActivities(new Intent[]{iActivity});
                break;
            case R.id.bIntent:
                Intent iIntent = new Intent(ActivityHome.this, MainActivityIntent.class);
                startActivities(new Intent[]{iIntent});
                break;
            case R.id.bFragment:
                Intent iFragment =new Intent(ActivityHome.this , ActivityMyFlexibleFragment3.class);
                startActivities(new Intent[]{iFragment});
                break;
            case R.id.bAsyncTask:
                Intent iAsynTask =new Intent(ActivityHome.this , ActivityAsyncTask4.class);
                startActivities(new Intent[]{iAsynTask});
                break;
            case R.id.bService:
                Intent iService =new Intent(ActivityHome.this , ActivityMyService5.class);
                startActivities(new Intent[]{iService});
                break;
            case R.id.bReceiver:
                Intent iReceiver =new Intent(ActivityHome.this , ActivityBroadcastHome6.class);
                startActivities(new Intent[]{iReceiver});
                break;
        }
    }
}
