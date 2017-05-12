package net.tt.interproceeactivityone;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/*
* Activity 跨进程通信 ，发送方
*
* Intent  发送info 数据到 另一个进程 interprocessactivitytwo MainActivity中显示value数据
* */
public class MainActivity extends AppCompatActivity {
    private static final String ACTION = "com.jf.test.MYACTION";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //URI定义了通信协议
                Uri uri = Uri.parse("info://test");
                //通过Action和URI调用第二个进程中的Activity，并传递数据
                Intent invokeIntent = new Intent(ACTION,uri);
                invokeIntent.putExtra("value", "Data from project one");
                startActivity(invokeIntent);
            }
        });
    }
}
