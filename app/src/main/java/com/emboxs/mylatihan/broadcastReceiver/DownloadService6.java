package com.emboxs.mylatihan.broadcastReceiver;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


public class DownloadService6 extends IntentService {
    public static final String TAG ="DownloadService";

    public DownloadService6() {
        super("DownloadService6");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "Download Service Di jalankan");
        if (intent !=null){
            try {
                Thread.sleep(5000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            Intent notifyFinishIntent = new Intent(ActivityBroadcastEvent6.ACTION_DOWNLOAD_STATUS);
            sendBroadcast(notifyFinishIntent);
        }
    }


    }
