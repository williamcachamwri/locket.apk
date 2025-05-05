package androidx.media3.effect;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.text.Layout;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.TextPaint;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;

public abstract class TextOverlay extends BitmapOverlay {
    public static final int TEXT_SIZE_PIXELS = 100;
    private Bitmap lastBitmap;
    private SpannableString lastText;

    public abstract SpannableString getText(long j);

    public static TextOverlay createStaticTextOverlay(final SpannableString spannableString) {
        return new TextOverlay() {
            public SpannableString getText(long j) {
                return spannableString;
            }
        };
    }

    public static TextOverlay createStaticTextOverlay(final SpannableString spannableString, final OverlaySettings overlaySettings) {
        return new TextOverlay() {
            public SpannableString getText(long j) {
                return spannableString;
            }

            public OverlaySettings getOverlaySettings(long j) {
                return overlaySettings;
            }
        };
    }

    public Bitmap getBitmap(long j) {
        SpannableString text = getText(j);
        if (!text.equals(this.lastText)) {
            this.lastText = text;
            TextPaint textPaint = new TextPaint();
            textPaint.setTextSize(100.0f);
            StaticLayout createStaticLayout = createStaticLayout(text, textPaint, getSpannedTextWidth(text, textPaint));
            Bitmap bitmap = this.lastBitmap;
            if (!(bitmap != null && bitmap.getWidth() == createStaticLayout.getWidth() && this.lastBitmap.getHeight() == createStaticLayout.getHeight())) {
                this.lastBitmap = Bitmap.createBitmap(createStaticLayout.getWidth(), createStaticLayout.getHeight(), Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas((Bitmap) Assertions.checkNotNull(this.lastBitmap));
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            createStaticLayout.draw(canvas);
        }
        return (Bitmap) Assertions.checkNotNull(this.lastBitmap);
    }

    private int getSpannedTextWidth(SpannableString spannableString, TextPaint textPaint) {
        StaticLayout createStaticLayout = createStaticLayout(spannableString, textPaint, (int) textPaint.measureText(spannableString, 0, spannableString.length()));
        int lineCount = createStaticLayout.getLineCount();
        float f = 0.0f;
        for (int i = 0; i < lineCount; i++) {
            f += createStaticLayout.getLineWidth(i);
        }
        return (int) Math.ceil((double) f);
    }

    private StaticLayout createStaticLayout(SpannableString spannableString, TextPaint textPaint, int i) {
        if (Util.SDK_INT >= 23) {
            return Api23.getStaticLayout(spannableString, textPaint, i);
        }
        return new StaticLayout(spannableString, textPaint, i, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
    }

    private static final class Api23 {
        private Api23() {
        }

        public static StaticLayout getStaticLayout(SpannableString spannableString, TextPaint textPaint, int i) {
            return StaticLayout.Builder.obtain(spannableString, 0, spannableString.length(), textPaint, i).build();
        }
    }
}
