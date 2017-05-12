package net.tt.msgreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by admin on 2017/5/12.
 */

public class MsgReceiver extends BroadcastReceiver {

    public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    private GetMesInfo getMesInfo;
    public MsgReceiver(GetMesInfo getMesInfo) {
        this.getMesInfo = getMesInfo;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String action = intent.getAction();
        if(action.equals(SMS_RECEIVED_ACTION)){
            Bundle bundle = intent.getExtras();
            if(bundle != null){
                //pdu为承载着一条短信的所有短信。
                //一条短信为140个英文字符长度，在这个长度范围内，即需一个pdu即可。超出这个范围，即要分割成多个pdu数组。
                Object[] pdus =  (Object[]) bundle.get("pdus");
                String msg = "";
                for(Object pdu : pdus){
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[])pdu);
                    String address = smsMessage.getOriginatingAddress();
                    String body = smsMessage.getMessageBody();
                    Log.i("KwokTag", address + " " + body);
                    msg = msg+"\\n  address = "+address+"\\n  body = "+body;
                }

                getMesInfo.getMsg(msg);
            }
        }
    }

    public interface  GetMesInfo{
        void getMsg(String msg);
    }
}
