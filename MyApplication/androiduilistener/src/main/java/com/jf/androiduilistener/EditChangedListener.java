package com.jf.androiduilistener;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import static android.util.Log.DEBUG;

/**
 * Created by admin on 2017/2/21.
 */

//EditView 输入监听
public class EditChangedListener implements TextWatcher {
    private static final String TAG = "EditChangedListener";
    private boolean DEBUG = true;
    private CharSequence temp;//监听前的文本
    private int editStart;//光标开始位置
    private int editEnd;//光标结束位置
    private final int charMaxNum = 10;
    private EditText editText;
    private Context context;

    public EditChangedListener(Context c,EditText editText){
        this.editText = editText;
        this.context = c;
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
            Toast.makeText(context, "你输入的字数已经超过了限制！", Toast.LENGTH_LONG).show();
            s.delete(editStart - 1, editEnd);
            int tempSelection = editStart;
            editText.setText(s);
            editText.setSelection(tempSelection);
        }

    }
}

