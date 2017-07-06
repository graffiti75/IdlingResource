package br.android.cericatto.idling_resource;

import android.app.ActivityManager;
import android.content.Context;
import android.support.test.espresso.IdlingResource;

/**
 * IntentServiceIdlingResource.java.
 *
 * @author Rodrigo Cericatto
 * @since Oct 12, 2016
 */
public class IntentServiceIdlingResource implements IdlingResource {

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    private Context mContext;
    private ResourceCallback mResourceCallback;

    //--------------------------------------------------
    // Constructor
    //--------------------------------------------------

    public IntentServiceIdlingResource(Context context) {
        mContext = context;
    }

    //--------------------------------------------------
    // Idling Resource
    //--------------------------------------------------

    @Override
    public String getName() {
        return IntentServiceIdlingResource.class.getName();
    }

    @Override
    public boolean isIdleNow() {
        boolean idle = !isIntentServiceRunning();
        if (idle && mResourceCallback != null) {
            mResourceCallback.onTransitionToIdle();
        }
        return idle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        mResourceCallback = resourceCallback;
    }

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    private boolean isIntentServiceRunning() {
        ActivityManager manager = (ActivityManager)mContext.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo info : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (RepeatService.class.getName().equals(info.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}