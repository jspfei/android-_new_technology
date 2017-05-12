package net.tt.twoactivitytest;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ss.jj.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ResumeInter{
    private final static String LOG_TAG = "net.tt.twoactivitytest.MainActivity";
    private ResumeRecv m_retReceiver;
    private Button startButton = null;
    private  TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        m_retReceiver = new ResumeRecv();
        m_retReceiver.setAct(this);
        IntentFilter inRFilter = new IntentFilter("net.tt.jf.broadcast");
        inRFilter.setPriority(0x7fffffff);
        this.registerReceiver(m_retReceiver, inRFilter);
        startButton  = (Button) findViewById(R.id.start_btn);
        startButton.setOnClickListener(this);

        textView = (TextView) findViewById(R.id.textView);

        Button show_btn = (Button) findViewById(R.id.show_btn);
        show_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("重新获得控制权");
            }
        });

        Log.i("HACK-TAG","Main Activity created");
    }

    @Override
    public void onClick(View v) {
        if(v.equals(startButton)){
            SubActivity.setMainActivity(this);

            Intent intent = new Intent("shy.luo.task.subactivity");
            startActivity(intent);
            onWindowFocusChanged(true);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                Log.i("HACK-TAG","MainActivity  ");
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void BroadcastRecv(String cmd) {
        Log.i("HACK-TAG", "in app BroadcastRecv cmd = " + cmd);
        switch(cmd){
            case "create":
            case "resume":
            case "pause":
                onWindowFocusChanged(true);
                //远驭 类 有 Activity启动的支付Sdk ，在onResume时直接让cocos获得焦点执行cocos代码
                onResume();
                break;
            default:
                break;
        }
    }
    @Override
    public void onResume(){
        super.onResume();
    }
}
