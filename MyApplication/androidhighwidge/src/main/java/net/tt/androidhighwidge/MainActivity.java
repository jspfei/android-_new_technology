package net.tt.androidhighwidge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import net.tt.androidhighwidge.view.ProgressDialogManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void show(View view){
        ProgressDialogManager.showSpinner(this);
    }
    public void show1(View view){
        ProgressDialogManager.showHorizontal(this);
    }
}
