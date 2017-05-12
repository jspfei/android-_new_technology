package net.tt.twoactivitytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import ss.jj.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private final static String LOG_TAG = "net.tt.twoactivitytest.MainActivity";

    private Button startButton = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        startButton  = (Button) findViewById(R.id.start_btn);
        startButton.setOnClickListener(this);

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
}
