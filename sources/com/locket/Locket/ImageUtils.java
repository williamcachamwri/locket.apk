package com.locket.Locket;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.concurrent.ExecutionException;

public final class ImageUtils {
    public static final int CORNER_RADIUS_DP = 24;
    public static final int WIDTH_BUFFER_PADDING_DP = 76;

    static Bitmap getRoundedBitmap(Context context, Bitmap bitmap, int i) {
        int dpToPx = (int) Util.dpToPx(context.getResources(), 24);
        int dpToPx2 = (int) Util.dpToPx(context.getResources(), i);
        try {
            return (Bitmap) ((RequestBuilder) Glide.with(context).asBitmap().load(bitmap).transform((Transformation<Bitmap>[]) new Transformation[]{new FitCenter(), new RoundedCorners(dpToPx)})).submit(dpToPx2, dpToPx2).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    static Bitmap getRoundedBitmap(Context context, int i, int i2) {
        int dpToPx = (int) Util.dpToPx(context.getResources(), 24);
        int dpToPx2 = (int) Util.dpToPx(context.getResources(), i2);
        try {
            return (Bitmap) ((RequestBuilder) Glide.with(context).asBitmap().load(Integer.valueOf(i)).transform((Transformation<Bitmap>[]) new Transformation[]{new FitCenter(), new RoundedCorners(dpToPx)})).submit(dpToPx2, dpToPx2).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    static Bitmap getCircularBitmap(Context context, Bitmap bitmap, int i) {
        try {
            return (Bitmap) ((RequestBuilder) Glide.with(context).asBitmap().load(bitmap).transform((Transformation<Bitmap>) new CircleCrop())).submit(i, i).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    static Bitmap getCircularBitmap(Context context, String str, int i) {
        try {
            return (Bitmap) ((RequestBuilder) Glide.with(context).asBitmap().load(str).transform((Transformation<Bitmap>) new CircleCrop())).submit(i, i).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    static Bitmap getTextBitmap(Context context, String str, int i, float f, int i2, int i3, int i4, int i5) {
        TextView textView = new TextView(context);
        textView.setDrawingCacheEnabled(true);
        textView.setTypeface(ResourcesCompat.getFont(context, i));
        textView.setTextColor(i3);
        textView.setTextSize(i2, f);
        textView.setText(str);
        textView.setMaxLines(i5);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        if (i4 >= 0) {
            textView.setMaxWidth((int) Util.dpToPx(context.getResources(), i4 - 76));
        }
        textView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        textView.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        textView.layout(0, 0, textView.getMeasuredWidth(), textView.getMeasuredHeight());
        return textView.getDrawingCache();
    }

    static Bitmap getProfilePlaceholderBitmap(Context context, String str, int i) {
        TextView textView = new TextView(context);
        textView.setDrawingCacheEnabled(true);
        textView.setTypeface(ResourcesCompat.getFont(context, R.font.proxima_soft_bold));
        textView.setTextColor(context.getResources().getColor(R.color.white));
        textView.setTextSize(12.0f);
        textView.setText(str);
        textView.setMaxLines(1);
        textView.setMaxWidth((int) Util.dpToPx(context.getResources(), i));
        textView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        textView.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        textView.layout(0, 0, textView.getMeasuredWidth(), textView.getMeasuredHeight());
        return textView.getDrawingCache();
    }

    static Bitmap getGradientBitmap(int i, int i2, int[] iArr, float f) {
        if (iArr.length >= 2) {
            GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, iArr);
            gradientDrawable.setCornerRadius(f);
            Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            gradientDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            gradientDrawable.draw(canvas);
            return createBitmap;
        }
        SentryLogcatAdapter.e("ImageUtils", "Gradient must have at least two colors");
        throw new IllegalArgumentException("Gradient must have at least two colors");
    }

    static Bitmap getSolidColorBitmap(int i, int i2, int i3, float f) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setColor(i3);
        paint.setAntiAlias(true);
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, (float) i, (float) i2), f, f, paint);
        return createBitmap;
    }

    static int parseRGBAColor(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Color string cannot be null");
        } else if (str.length() != 9) {
            return Color.parseColor(str);
        } else {
            String substring = str.substring(7, 9);
            return Color.parseColor("#" + substring + str.substring(1, 7));
        }
    }

    public static Bitmap createGradientBorder(int i, int i2, float f, int i3, String[] strArr) {
        int i4 = i;
        int i5 = i2;
        float f2 = f;
        int i6 = i3;
        String[] strArr2 = strArr;
        if (strArr2 == null || strArr2.length == 0) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(i4, i5, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        int[] iArr = new int[strArr2.length];
        for (int i7 = 0; i7 < strArr2.length; i7++) {
            iArr[i7] = Color.parseColor(strArr2[i7]);
        }
        Paint paint = new Paint(1);
        float f3 = (float) i4;
        float f4 = (float) i5;
        LinearGradient linearGradient = r8;
        Bitmap bitmap = createBitmap;
        LinearGradient linearGradient2 = new LinearGradient(0.0f, 0.0f, f3, f4, iArr, (float[]) null, Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, f3, f4), f2, f2, paint);
        Paint paint2 = new Paint(1);
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        int i8 = i4 - i6;
        int i9 = i5 - i6;
        float f5 = (float) i6;
        int max = Math.max(0, (int) (f2 - (f5 / 2.0f)));
        RectF rectF = new RectF(f5, f5, (float) i8, (float) i9);
        float f6 = (float) max;
        canvas.drawRoundRect(rectF, f6, f6, paint2);
        return bitmap;
    }
}
