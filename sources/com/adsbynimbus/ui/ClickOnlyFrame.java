package com.adsbynimbus.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import androidx.core.view.GestureDetectorCompat;

@Deprecated
public class ClickOnlyFrame extends FrameLayout {
    protected static final GestureDetector.SimpleOnGestureListener ClickGestureListener = new GestureDetector.SimpleOnGestureListener() {
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return true;
        }
    };
    protected final GestureDetectorCompat clickDetector;
    protected MotionEvent downEvent;

    public ClickOnlyFrame(Context context) {
        this(context, (AttributeSet) null);
    }

    public ClickOnlyFrame(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ClickOnlyFrame(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.clickDetector = new GestureDetectorCompat(context, ClickGestureListener);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        MotionEvent motionEvent2;
        if (this.clickDetector.onTouchEvent(motionEvent)) {
            super.dispatchTouchEvent(this.downEvent);
            super.dispatchTouchEvent(motionEvent);
            MotionEvent motionEvent3 = this.downEvent;
            if (motionEvent3 == null) {
                return true;
            }
            motionEvent3.recycle();
            this.downEvent = null;
            return true;
        } else if (motionEvent.getActionMasked() == 0) {
            this.downEvent = MotionEvent.obtain(motionEvent);
            return true;
        } else if (motionEvent.getActionMasked() != 3 || (motionEvent2 = this.downEvent) == null) {
            return true;
        } else {
            motionEvent2.recycle();
            this.downEvent = null;
            return true;
        }
    }
}
