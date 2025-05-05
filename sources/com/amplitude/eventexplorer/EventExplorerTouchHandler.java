package com.amplitude.eventexplorer;

import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class EventExplorerTouchHandler implements View.OnTouchListener {
    private float initialTouchX;
    private float initialTouchY;
    private int initialX;
    private int initialY;
    private String instanceName;
    private WindowManager.LayoutParams layoutParams;
    private WindowManager windowManager;

    EventExplorerTouchHandler(WindowManager windowManager2, WindowManager.LayoutParams layoutParams2, String str) {
        this.layoutParams = layoutParams2;
        this.windowManager = windowManager2;
        this.instanceName = str;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.initialY = this.layoutParams.y;
            this.initialX = this.layoutParams.x;
            this.initialTouchX = motionEvent.getRawX();
            this.initialTouchY = motionEvent.getRawY();
            return true;
        } else if (action == 1) {
            if (isAClick(this.initialTouchX, motionEvent.getRawX(), this.initialTouchY, motionEvent.getRawY())) {
                view.performClick();
                Intent intent = new Intent(view.getContext(), EventExplorerInfoActivity.class);
                intent.putExtra("instanceName", this.instanceName);
                view.getContext().startActivity(intent);
            }
            return true;
        } else if (action != 2) {
            return false;
        } else {
            this.layoutParams.y = this.initialY + ((int) (motionEvent.getRawY() - this.initialTouchY));
            this.layoutParams.x = this.initialX + ((int) (motionEvent.getRawX() - this.initialTouchX));
            this.windowManager.updateViewLayout(view, this.layoutParams);
            return true;
        }
    }

    private boolean isAClick(float f, float f2, float f3, float f4) {
        return Math.abs(f - f2) <= 5.0f && Math.abs(f3 - f4) <= 5.0f;
    }
}
