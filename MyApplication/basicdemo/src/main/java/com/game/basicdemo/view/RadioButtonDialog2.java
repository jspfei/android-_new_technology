package com.game.basicdemo.view;

import android.content.Context;
import android.widget.RadioGroup;
import com.game.basicdemo.R;

/**
 * Created by admin on 2017/5/23.
 */

public class RadioButtonDialog2 extends BaseDialog{
    private Context context;
    private String title;
    private OnItemCheckListener onItemCheckListener;

    public OnItemCheckListener getOnItemCheckListener() {
        return onItemCheckListener;
    }

    @Override
    public void setOnItemCheckListener(OnItemCheckListener onItemCheckListener) {
        this.onItemCheckListener = onItemCheckListener;
    }

    public RadioButtonDialog2(Context context){
        super(context);
        this.context = context;
    }

    public RadioButtonDialog2(Context context,int theme){
        super(context,theme);
    }
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    protected void init(){
        super.init();

        RadioGroup group = (RadioGroup) view.findViewById(R.id.radioGroup);
        group.setOnCheckedChangeListener(listener);
    }

    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            onItemCheckListener.onItemCheck(checkedId);
        }
    };

}
