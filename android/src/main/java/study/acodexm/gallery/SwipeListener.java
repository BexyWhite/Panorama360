package study.acodexm.gallery;


import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class SwipeListener implements View.OnTouchListener {
    private static final String TAG = SwipeListener.class.getSimpleName();
    private static final int SWIPE_LENGTH = 30;
    float startX;
    float startY;
    private boolean isTouched = false;
    private Runnable onSwipeLeft;
    private Runnable onSwipeRight;

    public SwipeListener(Runnable onSwipeRight, Runnable onSwipeLeft) {
        super();
        this.onSwipeLeft = onSwipeLeft;
        this.onSwipeRight = onSwipeRight;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            Log.d(TAG, "onTouch [ACTION DOWN]");
            isTouched = true;

            startX = event.getX();
            startY = event.getY();

        } else if (event.getAction() == MotionEvent.ACTION_UP) {

            Log.d(TAG, "onTouch [ACTION UP]");
            float x = event.getX();

            if (isTouched) {
                if (x > startX && Math.abs(x - startX) > SWIPE_LENGTH)
                    onSwipeLeft.run();

                if (x < startX && Math.abs(x - startX) > SWIPE_LENGTH)
                    onSwipeRight.run();
                isTouched = false;
            }

        }
        return true;
    }
}
