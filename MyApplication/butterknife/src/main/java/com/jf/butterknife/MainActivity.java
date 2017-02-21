package com.jf.butterknife;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

import static android.util.Log.DEBUG;

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

    @OnFocusChange({R.id.first_name,R.id.middle_name,R.id.last_name})
    public void onFocusChange(EditText editText){
        editText.addTextChangedListener(new EditChangedListener(editText));
    }


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

    //EditView 输入监听
    class EditChangedListener implements TextWatcher {
        private static final String TAG = "EditChangedListener";
        private boolean DEBUG = true;
        private CharSequence temp;//监听前的文本
        private int editStart;//光标开始位置
        private int editEnd;//光标结束位置
        private final int charMaxNum = 10;
        private EditText editText;

        public EditChangedListener(EditText editText){
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if (DEBUG)
                Log.i(TAG, "输入文本之前的状态");
            temp = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (DEBUG)
                Log.i(TAG, "输入文字中的状态，count是一次性输入字符数");
         //  mTvAvailableCharNum.setText("还能输入" + (charMaxNum - s.length()) + "字符");

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (DEBUG)
                Log.i(TAG, "输入文字后的状态");
            /** 得到光标开始和结束位置 ,超过最大数后记录刚超出的数字索引进行控制 */
            editStart = editText.getSelectionStart();
            editEnd = editText.getSelectionEnd();
            if (temp.length() > charMaxNum) {
                Toast.makeText(getApplicationContext(), "你输入的字数已经超过了限制！", Toast.LENGTH_LONG).show();
                s.delete(editStart - 1, editEnd);
                int tempSelection = editStart;
                editText.setText(s);
                editText.setSelection(tempSelection);
            }

        }
    }

}
