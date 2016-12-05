package com.classes.android.notificationapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

    Button btnShow, btnClear;
    NotificationManager manager;
    Notification myNotication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShow = (Button) findViewById(R.id.btnShowNotification);
        btnClear = (Button) findViewById(R.id.btnClearNotification);


        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        btnShow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //API level 11
                Intent intent = new Intent("MainActivity.this, NotificationView.class");

                //By giving a PendingIntent to another application, you are granting it the right to
                // perform the operation you have specified as if the other application was yourself (with the same permissions and identity)
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_ONE_SHOT);



                //public static class Notification.Builder
                //Builder class for Notification objects. Provides a convenient way to set the various fields of a Notification
                Notification.Builder builder = new Notification.Builder(MainActivity.this);

                //Setting this flag will make it so the notification is automatically canceled when the user clicks it in the panel.
                builder.setAutoCancel(false);

                //Set the text that is displayed in the status bar when the notification first arrives
                builder.setTicker("this is ticker text");


                builder.setContentTitle("WhatsApp Notification");
                builder.setContentText("You have a new message");
                builder.setSmallIcon(R.drawable.abc);
                builder.setContentIntent(pendingIntent);

                //Set whether this is an ongoing notification.
                builder.setOngoing(true);
                builder.setSubText("This is subtext...");   //API level 16
                builder.setNumber(100);

                //Combine all of the options that have been set and return a new Notification object.
                //builder.build();

                myNotication = builder.build();

                //Post a notification to be shown in the status bar
                manager.notify(11, myNotication);
               // manager.notify(12,myNotication);

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                manager.cancel(11);
            }
        });
    }


}
