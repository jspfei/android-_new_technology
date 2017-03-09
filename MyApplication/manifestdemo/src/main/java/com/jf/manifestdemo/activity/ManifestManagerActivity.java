package com.jf.manifestdemo.activity;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jf.manifestdemo.R;

public class ManifestManagerActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button_read_metaData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainfest_manager);
        button_read_metaData = (Button) findViewById(R.id.button_read_metaData);
        button_read_metaData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_read_metaData:
                readManifestMetaData();
                break;
            default:
                break;
        }
    }

    private void readManifestMetaData() {
        String umeng_appkey = "";
        try {
            ApplicationInfo ai = this.getPackageManager().getApplicationInfo(this.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            if (bundle != null) {
                umeng_appkey = bundle.getString("UMENG_APPKEY");
            }
        } catch (Exception e) {
            Log.e("HACK-TAG", e.getMessage());
        }
        toastNotice(umeng_appkey);
    }
    private void toastNotice(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
