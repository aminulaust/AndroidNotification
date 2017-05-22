package aminulaust.com.androidnotification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_TEXT_REPLY = "key_text_reply";
    int mRequestCode = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                // Creates an explicit intent for an Activity in your app
                Intent resultIntent = new Intent(MainActivity.this, ResultActivity.class);

                TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);

                stackBuilder.addParentStack(ResultActivity.class);

                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );

                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(MainActivity.this)
                                .setSmallIcon(R.drawable.notification_icon)
                                .setContentTitle("Android Notification");

                Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.sales);
                NotificationCompat.BigPictureStyle s = new NotificationCompat.BigPictureStyle().bigPicture(largeIcon);
                s.setSummaryText("Summary text appears on expanding the notification");
                mBuilder.setStyle(s);

                mBuilder.setContentIntent(resultPendingIntent);
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(1, mBuilder.build());
            }
        });

        Button b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                // Creates an explicit intent for an Activity in your app
                Intent resultIntent = new Intent(MainActivity.this, ResultActivity.class);

                TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);

                stackBuilder.addParentStack(ResultActivity.class);

                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                // Create the reply action and add the remote input.

                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(MainActivity.this)
                                .setSmallIcon(R.drawable.notification_icon)
                                .setContentTitle("Downloading Resource");

                mBuilder.setContentIntent(resultPendingIntent);
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mBuilder.setProgress(0, 0, true);
                mNotificationManager.notify(1, mBuilder.build());
            }
        });

        Button b3 = (Button) findViewById(R.id.button3);
        b3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                // Key for the string that's delivered in the action's intent.
                String replyLabel = "Reply Here";
                RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                        .setLabel(replyLabel)
                        .build();

                // Creates an explicit intent for an Activity in your app
                Intent resultIntent = new Intent(MainActivity.this, ResultActivity.class);

                TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);

                stackBuilder.addParentStack(ResultActivity.class);

                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                // Create the reply action and add the remote input.

                // Add to your action, enabling Direct Reply for it
                NotificationCompat.Action action =
                        new NotificationCompat.Action.Builder(R.drawable.notification_icon, replyLabel, resultPendingIntent)
                                .addRemoteInput(remoteInput)
                                .setAllowGeneratedReplies(true)
                                .build();

                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(MainActivity.this)
                                .addAction(action)
                                .setAutoCancel(true)
                                .setSmallIcon(R.drawable.notification_icon)
                                .setContentTitle("Android Tutorial")
                                .setContentText("Do you like my tutorials ?");

                mBuilder.setContentIntent(resultPendingIntent);
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(mRequestCode, mBuilder.build());
            }
        });
    }
}
