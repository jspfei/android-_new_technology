package net.tt.twoactivitytest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by chengzuan on 2017/4/11.
 */

public class ResumeRecv extends BroadcastReceiver {
    ResumeInter act = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        if(act != null)
            act.BroadcastRecv(intent.getStringExtra("cmd"));
    }

    public void setAct(ResumeInter h) {
        act = h;
    }
}
