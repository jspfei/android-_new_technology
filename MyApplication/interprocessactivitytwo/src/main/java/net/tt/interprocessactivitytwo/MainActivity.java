package net.tt.interprocessactivitytwo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
Activity 跨进程通信 ，接收方
* 接收来自 interprocessactivityone 中MainActivity 传递的参
*
*
* */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.tv_value);

        if(getIntent().getData() !=null){
            String value = getIntent().getExtras().getString("value");
            Log.i("HACK-TAG","value  "+value);
            textView.setText(value);
        }

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("zyf.demo.customAction");
                startActivity(i);
            }
        });
    }
}
