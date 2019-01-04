package com.emboxs.mylatihan.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.emboxs.mylatihan.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment3 extends Fragment implements View.OnClickListener {

    private Button btnDetailCategory;
    public CategoryFragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category3, container, false);
        btnDetailCategory = (Button)view.findViewById(R.id.btn_detail_category);
        btnDetailCategory.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_detail_category){
            DetailCategoryFragment3 mDetailCategoryFragment3 = new DetailCategoryFragment3();
            Bundle mBundle = new Bundle();
            mBundle.putString(DetailCategoryFragment3.EXTRA_NAME,"Lifestyle");
            String description ="Ketegori ini akan berisi produk-produk Lifestyle";
            mDetailCategoryFragment3.setArguments(mBundle);
            mDetailCategoryFragment3.setDescription(description);
            FragmentManager mFragmentManager = getFragmentManager();
            FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.frame_container, mDetailCategoryFragment3, DetailCategoryFragment3.class.getSimpleName());
            mFragmentTransaction.addToBackStack(null);
            mFragmentTransaction.commit();

        }
    }
}
