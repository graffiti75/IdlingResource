package br.android.cericatto.idling_resource;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.content.LocalBroadcastManager;

/**
 * RepeatService.java.
 *
 * @author Rodrigo Cericatto
 * @since Oct 12, 2016
 */
public class RepeatService extends IntentService {

    //--------------------------------------------------
    // Constants
    //--------------------------------------------------

    public static final String INTENT_REPEAT = "br.android.cericatto.idling_resource.intent_service.REPEAT";
    public static final String KEY_INPUT = "input";
    public static final String KEY_OUTPUT = "output";

    //--------------------------------------------------
    // Constructor
    //--------------------------------------------------

    public RepeatService() {
        super(RepeatService.class.getName());
    }

    //--------------------------------------------------
    // Intent Service
    //--------------------------------------------------

    @Override
    protected void onHandleIntent(Intent intent) {
        // Pretend it runs for a long time.
        SystemClock.sleep(1000);

        String query = intent.getStringExtra(KEY_INPUT);

        Intent replyIntent = new Intent(INTENT_REPEAT);
        replyIntent.putExtra(KEY_OUTPUT, query + "\n" + query);

        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
        manager.sendBroadcast(replyIntent);
    }
}