package com.emboxs.mvpvolume;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MainView{
    private EditText editWidth, editHeight,editLength;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editWidth = findViewById(R.id.edt_width);
        editHeight = findViewById(R.id.edt_height);
        editLength = findViewById(R.id.edt_length);

        Button btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);

        final MainPresenter presenter = new MainPresenter(this);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lenght = editLength.getText().toString().trim();
                String width = editWidth.getText().toString().trim();
                String height = editHeight.getText().toString().trim();
                boolean isEmptyFields = false;
                if (TextUtils.isEmpty(lenght)){
                    isEmptyFields = true;
                    editLength.setError("Fields tidak boleh kosong");
                }
                if (TextUtils.isEmpty(width)){
                    isEmptyFields = true;
                    editWidth.setError("Fields tidak boleh kosong");
                }
                if (TextUtils.isEmpty(height)){
                    isEmptyFields = true;
                    editHeight.setError("Fields tidak boleh kosong");
                }
                if (!isEmptyFields){
                    double l = Double.parseDouble(lenght);
                    double w = Double.parseDouble(width);
                    double h = Double.parseDouble(height);

                presenter.hitungVolume(l,w,h);
                }
            }
        });
    }

    @Override
    public void tampilVolume(MainModel model) {
        tvResult.setText(String.valueOf(model.getVolume()));
    }
}
