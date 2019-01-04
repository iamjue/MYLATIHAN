package com.emboxs.mylatihan.fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.emboxs.mylatihan.R;
import com.emboxs.mylatihan.fragment.homeFragment3;

public class ActivityMyFlexibleFragment3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_flexible_fragment3);

        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        homeFragment3 mHomeFragment3 = new homeFragment3(); //pembuat obyek homeFragment3
        mFragmentTransaction.add(R.id.frame_container, mHomeFragment3, homeFragment3.class.getSimpleName());

        Log.d("MyFlexibleFragment","Fragment name : "+homeFragment3.class.getSimpleName());
        mFragmentTransaction.commit();
    }
}
