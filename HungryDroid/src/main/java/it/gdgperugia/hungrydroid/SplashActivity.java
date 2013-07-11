package it.gdgperugia.hungrydroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

public class SplashActivity extends Activity {
    // Splash screen state
    private boolean activated = true;

    // Timeout of splash screen visualization
    private int splashTimeout = 5000;
    private Thread splashTread = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        // Thread to manage splash screen
        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int actualTime = 0;
                    while (activated && (actualTime < splashTimeout)) {
                        sleep(100);
                        actualTime += 100;
                    }
                } catch (InterruptedException e) {
                    // TODO: Use Log Class to catch and log exception
                } finally {
                    finish();
                    // Start the main activity
                    Intent mainIntent = new Intent(SplashActivity.this, CoursesActivity.class);
                    startActivity(mainIntent);
                }
            }
        };

        splashTread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Close splash screen after a screen touch event
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            activated = false;
        }
        return super.onTouchEvent(event);
    }
}