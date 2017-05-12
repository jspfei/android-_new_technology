package net.tt.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by admin on 2017/4/26.
 */

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if(bundle!= null){
            Object[] objArray = (Object[]) bundle.get("pdus");
            SmsMessage[] messages = new SmsMessage[objArray.length];
            for(int i=0;i<objArray.length;i++){
                messages[i] = SmsMessage.createFromPdu((byte[]) objArray[i]);
                StringBuilder str = new StringBuilder("from: ");
                str.append(messages[i].getDisplayOriginatingAddress());
                str.append("\n message: \n");
                str.append(messages[i].getDisplayMessageBody());

                Toast.makeText(context,str.toString(),Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
}
