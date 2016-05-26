package com.broadgames.stealthperformancecommunication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.broadgames.stealthperformancecommunication.session.Session;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Iterator;

public class SendNotificationService extends Service {

    private ValueEventListener handler;

    public SendNotificationService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();

        handler = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot arg0) {
                Iterable iterableObject = arg0.getChildren();
                Iterator iterator = iterableObject.iterator();
                //Log.d("Iterator",iterator.next().toString());
                while(iterator.hasNext()){
                    DataSnapshot data = (DataSnapshot)iterator.next();
                    //Log.d("SendService",data.toString());
                    if(data.getKey().equalsIgnoreCase("MESSAGE")) {
                        //Session.clientMessage=data.getValue().toString();
                        postNotif(data.getValue().toString());
                        //Log.d("SendService",data.getKey().toString());
                    }
                }
                /*if(arg0.getValue()!=null) {
                    //Log.e("Iterator",iterator.next().toString());
                    Log.e("getValue", arg0.toString());
                    postNotif(arg0.getValue().toString());
                }*/
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e("FirebaseService","Event cancelled");
            }
        };

        StealthPerformanceApplication.getFirebase().addValueEventListener(handler);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void postNotif(String notifString) {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.
                NOTIFICATION_SERVICE);
        Notification notification = new Notification(R.mipmap.ic_launcher,
                "SPC Incoming Message", System.currentTimeMillis());
        Context context = getApplicationContext();
        Intent notificationIntent = new Intent(context, ClientStrategyActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
        NotificationCompat.BigTextStyle bigStyle = new NotificationCompat.BigTextStyle();
        bigStyle.bigText("SPC Incoming Message");
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Play : ")
                .setContentText(notifString)
                .setContentIntent(contentIntent)
                .setStyle(bigStyle);
                //.extend(new NotificationCompat.WearableExtender().addAction(action))
        CharSequence contentTitle = "Play : ";
        notification.setLatestEventInfo(context, contentTitle, notifString, contentIntent);
        mNotificationManager.notify(1, notification);
    }
}