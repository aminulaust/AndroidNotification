package aminulaust.com.androidnotification;

import android.app.NotificationManager;
import android.app.RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.widget.TextView;

/**
 * Created by Raju on 5/22/17.
 */
public class ResultActivity extends AppCompatActivity {

    // Key for the string that's delivered in the action's intent.
    private static final String KEY_TEXT_REPLY = "key_text_reply";

    // mRequestCode allows you to update the notification.
    int mRequestCode = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        TextView textView = (TextView) findViewById(R.id.replyMessage);
        textView.setText(getMessageText(getIntent()));

        String returnMessage = "Thank you";

        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_stat_toggle_check_box)
                        .setContentText(returnMessage);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //update notification
        mNotificationManager.notify(mRequestCode, mBuilder.build());
    }

    private CharSequence getMessageText(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            return remoteInput.getCharSequence(KEY_TEXT_REPLY);
        }
        return null;
    }
}
