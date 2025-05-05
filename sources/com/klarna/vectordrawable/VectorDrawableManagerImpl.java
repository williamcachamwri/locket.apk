package com.klarna.vectordrawable;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.common.JavascriptException;
import com.facebook.react.uimanager.ThemedReactContext;
import javax.annotation.Nullable;

public class VectorDrawableManagerImpl {
    public static final String NAME = "RNVectorDrawable";
    private static final String RESIZE_MODE_CENTER = "center";
    private static final String RESIZE_MODE_CONTAIN = "contain";
    private static final String RESIZE_MODE_COVER = "cover";
    private static final String RESIZE_MODE_STRETCH = "stretch";

    public static ImageView createViewInstance(ThemedReactContext themedReactContext) {
        ImageView imageView = new ImageView(themedReactContext);
        imageView.setCropToPadding(true);
        imageView.setScaleType(toScaleType((String) null));
        return imageView;
    }

    public static void setResourceName(ImageView imageView, @Nullable String str) {
        imageView.setImageDrawable(createVectorDrawable(imageView.getContext(), str));
    }

    public static void setTintColor(ImageView imageView, @Nullable Integer num) {
        if (num == null) {
            imageView.clearColorFilter();
        } else {
            imageView.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
        }
    }

    public static void setResizeMode(ImageView imageView, @Nullable String str) {
        imageView.setScaleType(toScaleType(str));
    }

    private static ImageView.ScaleType toScaleType(@Nullable String str) {
        if (str == null || RESIZE_MODE_COVER.equals(str)) {
            return ImageView.ScaleType.CENTER_CROP;
        }
        if (RESIZE_MODE_CONTAIN.equals(str)) {
            return ImageView.ScaleType.FIT_CENTER;
        }
        if (RESIZE_MODE_STRETCH.equals(str)) {
            return ImageView.ScaleType.FIT_XY;
        }
        if ("center".equals(str)) {
            return ImageView.ScaleType.CENTER_INSIDE;
        }
        throw new JSApplicationIllegalArgumentException("Invalid resize mode: '" + str + "'");
    }

    private static Drawable createVectorDrawable(Context context, String str) throws JavascriptException {
        String packageName = context.getPackageName();
        int identifier = context.getResources().getIdentifier(str, "drawable", packageName);
        if (identifier == 0) {
            identifier = context.getResources().getIdentifier(str, "drawable-hdpi", packageName);
        }
        if (identifier != 0) {
            return ContextCompat.getDrawable(context, identifier);
        }
        throw new JavascriptException("Unable to find resource " + str + " in " + packageName);
    }
}
