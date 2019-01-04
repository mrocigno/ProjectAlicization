package br.com.mrocigno.projectalicization.Helpers;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.RelativeLayout;

public class CustomRelativeLayout extends RelativeLayout implements ScaleGestureDetector.OnScaleGestureListener {

    public RelativeLayoutOnTouch relativeLayoutOnTouch;
    public GestureDetectorCompat detector;
    public ScaleGestureDetector scaleDetector;

    public void setRelativeLayoutOnTouch(RelativeLayoutOnTouch relativeLayoutOnTouch) {
        this.relativeLayoutOnTouch = relativeLayoutOnTouch;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP)
            relativeLayoutOnTouch.onFrameTouchUp();

        super.dispatchTouchEvent(event);
        return detector.onTouchEvent(event);
    }

    public CustomRelativeLayout(Context context) {
        super(context);
        detector = new GestureDetectorCompat(context, new MyGesture());
        scaleDetector = new ScaleGestureDetector(context, this);
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        detector = new GestureDetectorCompat(context, new MyGesture());
        scaleDetector = new ScaleGestureDetector(context, this);
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        detector = new GestureDetectorCompat(context, new MyGesture());
        scaleDetector = new ScaleGestureDetector(context, this);
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        detector = new GestureDetectorCompat(context, new MyGesture());
        scaleDetector = new ScaleGestureDetector(context, this);
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        return false;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return false;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }

    private class MyGesture extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return super.onSingleTapUp(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            relativeLayoutOnTouch.onScrollMovie(distanceX, distanceY);
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return super.onDown(e);
        }
    }

    public interface RelativeLayoutOnTouch {
        void onFrameTouchUp();
        void onScrollMovie(float x, float y);
    }
}
