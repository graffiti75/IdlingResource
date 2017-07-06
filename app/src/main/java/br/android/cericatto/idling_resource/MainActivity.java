package br.android.cericatto.idling_resource;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * MainActivity.java.
 * Source: https://github.com/chiuki/espresso-samples/tree/master/idling-resource-intent-service
 *
 * @author Rodrigo Cericatto
 * @since Oct 12, 2016
 */
public class MainActivity extends AppCompatActivity {

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    private TextView mInputTextView;
    private TextView mOutputTextView;

    private LocalBroadcastManager mLocalBroadcastManager;
    private BroadcastReceiver mBroadcastReceiver;
    private IntentFilter mIntentFilter;

    //--------------------------------------------------
    // Activity Life Cycle
    //--------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setLayout();
        setBroadcastReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLocalBroadcastManager.registerReceiver(mBroadcastReceiver, mIntentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLocalBroadcastManager.unregisterReceiver(mBroadcastReceiver);
    }

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    private void setLayout() {
        mInputTextView = (TextView) findViewById(R.id.input);
        mOutputTextView = (TextView) findViewById(R.id.output);
    }

    private void setBroadcastReceiver() {
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                mOutputTextView.setText(intent.getStringExtra(RepeatService.KEY_OUTPUT));
            }
        };
        mIntentFilter = new IntentFilter(RepeatService.INTENT_REPEAT);
    }

    public void repeat(View view) {
        Intent intent = new Intent(this, RepeatService.class);
        intent.putExtra(RepeatService.KEY_INPUT, mInputTextView.getText().toString());
        startService(intent);
    }
}