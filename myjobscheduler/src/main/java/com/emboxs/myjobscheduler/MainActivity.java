package com.emboxs.myjobscheduler;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import java.text.DecimalFormat;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener {
    Button btnStart, btnCancel;
    private int jobId =10;
    TextView show;
    public static  final String TAG= GetCurrentWeatherJobService.class.getSimpleName();
    final String APP_ID ="ae40e7733b10dfa6af24107304650ebb";
    private final String CITY ="Tigaraksa";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart=findViewById(R.id.btn_start);
            btnStart.setOnClickListener(this);
        btnCancel = findViewById(R.id.btn_cancel);
            btnCancel.setOnClickListener(this);
        show =findViewById(R.id.tv_tampil);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:
                startJob();
                break;
            case R.id.btn_cancel:
                cancelJob();
                break;
        }
    }

    private void startJob() {
        ComponentName mServiceComponent = new ComponentName(this, GetCurrentWeatherJobService.class);

        JobInfo.Builder builder = new JobInfo.Builder(jobId, mServiceComponent);

        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        builder.setRequiresDeviceIdle(false);
        builder.setRequiresCharging(true);

        //1000 ms =1 detik
        builder.setPeriodic(5000);

        JobScheduler jobScheduler =(JobScheduler)getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(builder.build());
        getCurrentWeather();

        Toast.makeText(this, "job service started", Toast.LENGTH_SHORT).show();
    }
    private void cancelJob() {
        JobScheduler tm =(JobScheduler)getSystemService(Context.JOB_SCHEDULER_SERVICE);
        tm.cancel(jobId);
        Toast.makeText(this,"job service canceled", Toast.LENGTH_SHORT).show();
        finish();
    }


    private void getCurrentWeather() {
        Log.d(TAG,"Running");
        AsyncHttpClient client = new AsyncHttpClient();
        String  url = "http://api.openweathermap.org/data/2.5/weather?q="+CITY+"&appid="+APP_ID;
        Log.e(TAG, "getCurrentWeather : "+url);
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                Log.d(TAG, result);
                try {
                    JSONObject responseObject = new JSONObject(result);
                    String currentWeather = responseObject.getJSONArray("weather").getJSONObject(0).getString("main");
                    String description =responseObject.getJSONArray("weather").getJSONObject(0).getString("description");
                    double tempInKelvin = responseObject.getJSONObject("main").getDouble("temp");

                    double tempInCelcius = tempInKelvin - 273;
                    String temprature = new DecimalFormat("##.##").format(tempInCelcius);

                    String title = "Current Weather";
                    String message = currentWeather +", "+description+"with "+temprature+" celcius";
                    int notifId =100;
                    show.setText(message);

                }catch (Exception e){

                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                //ketika proses gagal, maka jobFinished diset dengan parameter true. yang artinya job perlu di reschedule


            }
        });
    }
}
