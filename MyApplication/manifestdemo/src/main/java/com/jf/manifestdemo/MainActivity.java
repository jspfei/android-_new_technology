package com.jf.manifestdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jf.manifestdemo.activity.ManifestManagerActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button_read_Manifest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_read_Manifest = (Button) findViewById(R.id.button_read_Manifest_metaData);
        button_read_Manifest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button_read_Manifest_metaData:
                openManifestActivity();
                break;
            default:
                break;
        }

    }

    private void openManifestActivity() {
        Intent intent = new Intent();
        intent.setClass(this, ManifestManagerActivity.class);
        startActivity(intent);
    }
}
