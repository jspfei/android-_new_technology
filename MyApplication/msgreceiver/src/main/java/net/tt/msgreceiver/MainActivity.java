package net.tt.msgreceiver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements MsgReceiver.GetMesInfo {

    private TextView test_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         new MsgReceiver(this);
        test_data = (TextView) findViewById(R.id.test_data);
    }

    @Override
    public void getMsg(String msg) {
        test_data.setText(msg);
    }
}
