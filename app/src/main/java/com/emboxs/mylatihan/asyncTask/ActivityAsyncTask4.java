package com.emboxs.mylatihan.asyncTask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.emboxs.mylatihan.R;

public class ActivityAsyncTask4 extends AppCompatActivity {
    public static final String DEMO_ASYNC ="DemoAsync";
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task4);
        tvStatus = (TextView)findViewById(R.id.tv_status);
        DemoAsync demoAsync = new DemoAsync();
        demoAsync.execute("Halo ini Demo AsyncTask");
    }
    private class DemoAsync extends AsyncTask<String, Void, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvStatus.setText("Status : onPreExecute");
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d(DEMO_ASYNC, "Status : doInBacground");
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                Log.d(DEMO_ASYNC, e.getMessage());
            }
            return params[0];
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tvStatus.setText("Status : onPostExecute"+s);
        }
    }
}
