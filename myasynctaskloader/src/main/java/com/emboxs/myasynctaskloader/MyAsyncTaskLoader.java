package com.emboxs.myasynctaskloader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MyAsyncTaskLoader extends AsyncTaskLoader<ArrayList<WeatherItems>> {
    private ArrayList<WeatherItems> mData;
    private Boolean mHasResult = false;
    private String mKumpulanKota;

    public MyAsyncTaskLoader(final Context context, String kumpulanKota) {
        super(context);
        onContentChanged();
        this.mKumpulanKota = kumpulanKota;
    }

    @Override
    protected void onStartLoading() {
       if (takeContentChanged())
           forceLoad();
       else if (mHasResult)
           deliverResult(mData);
    }

    @Override
    public void deliverResult(final ArrayList<WeatherItems> data) {
        mData = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (mHasResult){
            onReleaseResource(mData);
            mData = null;
            mHasResult = false;
        }
    }
    private static final  String API_KEY = "ae40e7733b10dfa6af24107304650ebb";

    //format search kota url JAKARTA = 1642911, BANDUNG = 1650357, SEMARANG = 162796
    //http://api.openweathermap.org/data/2.5/group?id=1642911,1650357,162796&units=metric&appid=API_KEY


    @Override
    public ArrayList<WeatherItems> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();

        final ArrayList<WeatherItems> weatherItemses = new ArrayList<>();
        String url = "http://api.openweathermap.org/data/2.5/group?id="+
                mKumpulanKota+ "&units=metric&appid="+API_KEY;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("list");
                    for (int i = 0; i< list.length();i++){
                        JSONObject weather = list.getJSONObject(i);
                        WeatherItems weatherItems = new WeatherItems(weather);
                        weatherItemses.add(weatherItems);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                //jika response gagal maka do notthing
            }
        });
        return weatherItemses;
    }
    protected void onReleaseResource(ArrayList<WeatherItems> data){
        //nothing to do
    }
}
