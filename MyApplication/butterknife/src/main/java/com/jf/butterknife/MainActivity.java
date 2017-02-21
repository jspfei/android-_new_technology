package com.jf.butterknife;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //1.Activity中的绑定
    @BindView(R.id.sub_text_view)
    TextView sub_text_view;
    @BindView(R.id.title_text_view)
    TextView title_text_view;

    //2.资源绑定
    @BindString(R.string.title) String title;
    @BindDrawable(R.drawable.panda)Drawable panda;
    @BindColor(R.color.red) int red;
    @BindDimen(R.dimen.space) float space;


    //5.View集合中的使用
    @BindViews({R.id.first_name,R.id.middle_name,R.id.last_name})
    List<EditText> nameViews;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        listView();
    }

    private void listView() {


        //Android的Property（配置信息）也可以被用在apply方法中
        ButterKnife.apply(nameViews,View.ALPHA,0.7f);
    }
    static final ButterKnife.Action<View> DISABLE = new ButterKnife.Action<View>() {
        @Override
        public void apply(@NonNull View view, int index) {
            view.setEnabled(false);
        }
    };
    static  final ButterKnife.Setter<View,Boolean> ENABLED = new ButterKnife.Setter<View, Boolean>() {
        @Override
        public void set(@NonNull View view, Boolean value, int index) {
            view.setEnabled(value);
        }
    };

    //6监听器绑定
    @OnClick(R.id.submit_button)
    public void submit(Button button){
        button.setText("Hello!");
        ButterKnife.apply(nameViews,DISABLE);

    }
    @OnClick(R.id.enable_button)
    public void enable(Button button){
        ButterKnife.apply(nameViews,ENABLED,true);
    }


    //可以选注解 ，没有找到不会报错
    @Nullable
    @OnClick({R.id.cb_1,R.id.cb_2,R.id.cb_3})
    public void checkOnClick(CheckBox checkBox){
        if(checkBox.isChecked()){
            title =title + "  "+checkBox.getText();
        }else{

        }
        title_text_view.setText(title);

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

}
