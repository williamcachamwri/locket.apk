package com.google.android.material.internal;

final class FadeThroughUtils {
    static final float THRESHOLD_ALPHA = 0.5f;

    static void calculateFadeOutAndInAlphas(float f, float[] fArr) {
        if (f <= 0.5f) {
            fArr[0] = 1.0f - (f * 2.0f);
            fArr[1] = 0.0f;
            return;
        }
        fArr[0] = 0.0f;
        fArr[1] = (f * 2.0f) - 1.0f;
    }

    private FadeThroughUtils() {
    }
}
