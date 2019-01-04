package com.emboxs.mylatihan.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver6 extends BroadcastReceiver {
    final SmsManager sms = SmsManager.getDefault();

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null){
                final Object[] pdusObj = (Object[])bundle.get("pdus");
                for (int i=0; i<pdusObj.length;i++){
                    SmsMessage currentMessage = getIncomingMassage(pdusObj[i],bundle);
                    String phoneNumber =currentMessage.getDisplayOriginatingAddress();
                    String senderNum =phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();
                    Log.i("SmsReceiver","senderNum : "+senderNum +"message : "+message);
                    Intent showSmsIntent = new Intent(context,ActivitySmsListenerApp6.class);
                    showSmsIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    showSmsIntent.putExtra(ActivitySmsListenerApp6.EXTRA_SMS_NO, phoneNumber);
                    showSmsIntent.putExtra(ActivitySmsListenerApp6.EXTRA_SMS_MESSAGE, message);
                    context.startActivities(new Intent[]{showSmsIntent});
                }
            }
        }catch (Exception e){
            Log.e("SmsReceiver", "Exception smsReceiver"+e);
        }
    }
    private SmsMessage getIncomingMassage(Object aObject, Bundle bundle){
        SmsMessage currentSMS;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            String format =bundle.getString("format");
            currentSMS = SmsMessage.createFromPdu((byte[])aObject,format);
        }else {
            currentSMS = SmsMessage.createFromPdu((byte[])aObject);
        }
        return currentSMS;
    }
}
