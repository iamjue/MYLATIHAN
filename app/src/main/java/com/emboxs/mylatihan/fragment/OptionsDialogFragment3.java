package com.emboxs.mylatihan.fragment;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.emboxs.mylatihan.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class OptionsDialogFragment3 extends DialogFragment implements View.OnClickListener{
    private Button btnClose, btnChoose;
    private RadioGroup rgOptions;
    private RadioButton rbSaf, rbMou, rbMoyes, rbLvg;
    private OnOptionDialogListener onOptionDialogListener;

    public OptionsDialogFragment3() {
        // Required empty public constructor
    }

    public OnOptionDialogListener getOnOptionDialogListener() {
        return onOptionDialogListener;
    }
    public void setOnOptionDialogListener(OnOptionDialogListener onOptionDialogListener){
        this.onOptionDialogListener = onOptionDialogListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_options_dialog3, container, false);
        btnChoose = (Button)view.findViewById(R.id.btn_choose);
        btnChoose.setOnClickListener(this);
        btnClose = (Button)view.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(this);
        rgOptions=(RadioGroup)view.findViewById(R.id.rg_options);
        rbSaf =(RadioButton)view.findViewById(R.id.rb_saf);
        rbMou =(RadioButton)view.findViewById(R.id.rb_mou);
        rbMoyes =(RadioButton)view.findViewById(R.id.rb_moyes);
        rbLvg =(RadioButton)view.findViewById(R.id.rb_lvg);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_close:
                getDialog().cancel();
                break;
            case R.id.btn_choose:
                int checkedRadioButtonId = rgOptions.getCheckedRadioButtonId();
                if (checkedRadioButtonId !=-1 ){
                    String coach = null;
                    switch (checkedRadioButtonId){
                        case R.id.rb_saf:
                            coach = rbSaf.getText().toString().trim();
                            break;
                        case R.id.rb_mou:
                            coach = rbMou.getText().toString().trim();
                            break;
                        case R.id.rb_moyes:
                            coach = rbMoyes.getText().toString().trim();
                            break;
                        case R.id.rb_lvg:
                            coach = rbLvg.getText().toString().trim();
                            break;
                    }
                    getOnOptionDialogListener().onOptionChoosen(coach);
                    getDialog().cancel();
                }
                break;
        }
    }
    public interface OnOptionDialogListener{
        void onOptionChoosen(String text);
    }
}
