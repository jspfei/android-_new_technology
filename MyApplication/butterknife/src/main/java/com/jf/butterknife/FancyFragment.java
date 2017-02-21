package com.jf.butterknife;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FancyFragment extends Fragment {
    //3.Fragment中的绑定
  @BindView(R.id.button1)
  Button button1;
  @BindView(R.id.button2)
  Button button2;

    @Override public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_fancy,container,false);
        ButterKnife.bind(this,view);
        return view;
    }
}
