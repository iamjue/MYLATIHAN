package com.emboxs.mylatihan.Intent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.emboxs.mylatihan.R;

public class MainActivityIntent extends AppCompatActivity implements View.OnClickListener {
    private Button btnDial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_intent);

        btnDial = findViewById(R.id.btn_dial);
        btnDial.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String phone="082210207773";
        if (v.getId() ==  R.id.btn_dial){
            Intent intentDial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
//            startActivities(new Intent[]{intentDial});
            startActivity(intentDial);
        }

    }
}
