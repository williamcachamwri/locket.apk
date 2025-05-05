package androidx.emoji2.text;

import android.os.Build;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import androidx.core.text.PrecomputedTextCompat;
import java.util.stream.IntStream;

class UnprecomputeTextOnModificationSpannable implements Spannable {
    private Spannable mDelegate;
    private boolean mSafeToWrite = false;

    UnprecomputeTextOnModificationSpannable(Spannable spannable) {
        this.mDelegate = spannable;
    }

    UnprecomputeTextOnModificationSpannable(Spanned spanned) {
        this.mDelegate = new SpannableString(spanned);
    }

    UnprecomputeTextOnModificationSpannable(CharSequence charSequence) {
        this.mDelegate = new SpannableString(charSequence);
    }

    private void ensureSafeWrites() {
        Spannable spannable = this.mDelegate;
        if (!this.mSafeToWrite && precomputedTextDetector().isPrecomputedText(spannable)) {
            this.mDelegate = new SpannableString(spannable);
        }
        this.mSafeToWrite = true;
    }

    /* access modifiers changed from: package-private */
    public Spannable getUnwrappedSpannable() {
        return this.mDelegate;
    }

    public void setSpan(Object obj, int i, int i2, int i3) {
        ensureSafeWrites();
        this.mDelegate.setSpan(obj, i, i2, i3);
    }

    public void removeSpan(Object obj) {
        ensureSafeWrites();
        this.mDelegate.removeSpan(obj);
    }

    public <T> T[] getSpans(int i, int i2, Class<T> cls) {
        return this.mDelegate.getSpans(i, i2, cls);
    }

    public int getSpanStart(Object obj) {
        return this.mDelegate.getSpanStart(obj);
    }

    public int getSpanEnd(Object obj) {
        return this.mDelegate.getSpanEnd(obj);
    }

    public int getSpanFlags(Object obj) {
        return this.mDelegate.getSpanFlags(obj);
    }

    public int nextSpanTransition(int i, int i2, Class cls) {
        return this.mDelegate.nextSpanTransition(i, i2, cls);
    }

    public int length() {
        return this.mDelegate.length();
    }

    public char charAt(int i) {
        return this.mDelegate.charAt(i);
    }

    public CharSequence subSequence(int i, int i2) {
        return this.mDelegate.subSequence(i, i2);
    }

    public String toString() {
        return this.mDelegate.toString();
    }

    public IntStream chars() {
        return CharSequenceHelper_API24.chars(this.mDelegate);
    }

    public IntStream codePoints() {
        return CharSequenceHelper_API24.codePoints(this.mDelegate);
    }

    private static class CharSequenceHelper_API24 {
        private CharSequenceHelper_API24() {
        }

        static IntStream codePoints(CharSequence charSequence) {
            return charSequence.codePoints();
        }

        static IntStream chars(CharSequence charSequence) {
            return charSequence.chars();
        }
    }

    static PrecomputedTextDetector precomputedTextDetector() {
        return Build.VERSION.SDK_INT < 28 ? new PrecomputedTextDetector() : new PrecomputedTextDetector_28();
    }

    static class PrecomputedTextDetector {
        PrecomputedTextDetector() {
        }

        /* access modifiers changed from: package-private */
        public boolean isPrecomputedText(CharSequence charSequence) {
            return charSequence instanceof PrecomputedTextCompat;
        }
    }

    static class PrecomputedTextDetector_28 extends PrecomputedTextDetector {
        PrecomputedTextDetector_28() {
        }

        /* access modifiers changed from: package-private */
        public boolean isPrecomputedText(CharSequence charSequence) {
            return (charSequence instanceof PrecomputedText) || (charSequence instanceof PrecomputedTextCompat);
        }
    }
}
