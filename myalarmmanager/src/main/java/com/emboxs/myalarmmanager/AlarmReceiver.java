package com.emboxs.myalarmmanager;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.CamcorderProfile;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.util.Calendar;

public class AlarmReceiver extends BroadcastReceiver {
    public static final String TYPE_ONE_TIME ="OneTimeAlarm";
    public static final String TYPE_REPEATING ="RepeatingAlarm";
    public static final String EXTRA_MESSAGE="message";
    public static final String EXTRA_TYPE="type";
    private final int NOTIF_ID_ONETIME =100;
    private final int NOTIF_ID_REPEATING =101;

    public AlarmReceiver(){

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String type = intent.getStringExtra(EXTRA_TYPE);
        String message = intent.getStringExtra(EXTRA_MESSAGE);
        String title = type.equalsIgnoreCase(TYPE_ONE_TIME)?"One Time Alarm" : "Repeating Alarm";
        int notifId = type.equalsIgnoreCase(TYPE_ONE_TIME)?NOTIF_ID_ONETIME : NOTIF_ID_REPEATING;
        showAlarmNotification(context,title,message,notifId);
    }
    private void showAlarmNotification(Context context, String title, String message, int notifId){
        NotificationManager notificationManagerCompat =(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound =RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder =new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_alarm)
                .setContentTitle(title)
                .setContentText(message)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setVibrate(new long[]{1000,1000,1000,1000,1000})
                .setSound(alarmSound);
        notificationManagerCompat.notify(notifId,builder.build());
    }
    public void setOneTimeAlarm(Context context, String type, String date, String time, String message){
        AlarmManager alarmManager =(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(EXTRA_MESSAGE,message);
        intent.putExtra(EXTRA_TYPE, type);
        String dateAray[]=date.split("-");
        String timeAray[]=time.split(":");
        Calendar calendar =Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(dateAray[0]));
        calendar.set(Calendar.MONTH, Integer.parseInt(dateAray[1])-1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateAray[2]));
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeAray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeAray[1]));
        calendar.set(Calendar.SECOND, 0);
        int requestCode = NOTIF_ID_ONETIME;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),pendingIntent);
        Toast.makeText(context,"One Time Alarm Set Up", Toast.LENGTH_SHORT).show();
    }
    public void setRepeatingAlarm(Context context, String type, String time, String message){
        AlarmManager alarmManager= (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context,AlarmReceiver.class);
        intent.putExtra(EXTRA_TYPE,type);
        intent.putExtra(EXTRA_MESSAGE,message);
        String timeAray[]=time.split(":");
        Calendar calendar =Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeAray[1]));
        calendar.set(Calendar.MINUTE,Integer.parseInt(timeAray[1]));
        calendar.set(Calendar.SECOND,0);
        int requestCode=NOTIF_ID_REPEATING;
        PendingIntent pendingIntent =PendingIntent.getBroadcast(context,requestCode,intent,0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
        Toast.makeText(context,"Repeating alarm set up", Toast.LENGTH_SHORT).show();
    }

    public void cancelAlarm(Context context, String type){
        AlarmManager alarmManager =(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        int requestCode = type.equalsIgnoreCase(TYPE_ONE_TIME)?NOTIF_ID_ONETIME : NOTIF_ID_REPEATING;
        PendingIntent pendingIntent =PendingIntent.getBroadcast(context,requestCode, intent,0);
        alarmManager.cancel(pendingIntent);
        Toast.makeText(context, "Repeating alarm di batalkan", Toast.LENGTH_SHORT).show();
    }
}
