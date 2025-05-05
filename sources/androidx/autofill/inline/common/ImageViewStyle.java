package androidx.autofill.inline.common;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.autofill.inline.common.ViewStyle;
import androidx.core.util.Preconditions;
import io.sentry.android.core.SentryLogcatAdapter;

public final class ImageViewStyle extends ViewStyle {
    private static final String KEY_IMAGE_MAX_HEIGHT = "image_max_height";
    private static final String KEY_IMAGE_MAX_WIDTH = "image_max_width";
    private static final String KEY_IMAGE_SCALE_TYPE = "image_scale_type";
    private static final String KEY_IMAGE_TINT_LIST = "image_tint_list";
    private static final String KEY_IMAGE_VIEW_STYLE = "image_view_style";
    private static final String TAG = "ImageViewStyle";

    /* access modifiers changed from: protected */
    public String getStyleKey() {
        return KEY_IMAGE_VIEW_STYLE;
    }

    public ImageViewStyle(Bundle bundle) {
        super(bundle);
    }

    public void applyStyleOnImageViewIfValid(ImageView imageView) {
        String string;
        ColorStateList colorStateList;
        if (isValid()) {
            super.applyStyleOnViewIfValid(imageView);
            if (this.mBundle.containsKey(KEY_IMAGE_MAX_WIDTH)) {
                imageView.setMaxWidth(this.mBundle.getInt(KEY_IMAGE_MAX_WIDTH));
                imageView.setAdjustViewBounds(true);
            }
            if (this.mBundle.containsKey(KEY_IMAGE_MAX_HEIGHT)) {
                imageView.setMaxHeight(this.mBundle.getInt(KEY_IMAGE_MAX_HEIGHT));
                imageView.setAdjustViewBounds(true);
            }
            if (this.mBundle.containsKey(KEY_IMAGE_TINT_LIST) && (colorStateList = (ColorStateList) this.mBundle.getParcelable(KEY_IMAGE_TINT_LIST)) != null) {
                imageView.setImageTintList(colorStateList);
            }
            if (this.mBundle.containsKey(KEY_IMAGE_SCALE_TYPE) && (string = this.mBundle.getString(KEY_IMAGE_SCALE_TYPE)) != null) {
                try {
                    imageView.setScaleType(ImageView.ScaleType.valueOf(string));
                } catch (IllegalArgumentException unused) {
                    SentryLogcatAdapter.w(TAG, "Cannot recognize the scale type: " + string);
                }
            }
        }
    }

    public static final class Builder extends ViewStyle.BaseBuilder<ImageViewStyle, Builder> {
        /* access modifiers changed from: protected */
        public Builder getThis() {
            return this;
        }

        public Builder() {
            super(ImageViewStyle.KEY_IMAGE_VIEW_STYLE);
        }

        public Builder setScaleType(ImageView.ScaleType scaleType) {
            Preconditions.checkNotNull(scaleType, "scaleType should not be null");
            this.mBundle.putString(ImageViewStyle.KEY_IMAGE_SCALE_TYPE, scaleType.name());
            return this;
        }

        public Builder setMaxWidth(int i) {
            this.mBundle.putInt(ImageViewStyle.KEY_IMAGE_MAX_WIDTH, i);
            return this;
        }

        public Builder setMaxHeight(int i) {
            this.mBundle.putInt(ImageViewStyle.KEY_IMAGE_MAX_HEIGHT, i);
            return this;
        }

        public Builder setTintList(ColorStateList colorStateList) {
            Preconditions.checkNotNull(colorStateList, "imageTintList should not be null");
            this.mBundle.putParcelable(ImageViewStyle.KEY_IMAGE_TINT_LIST, colorStateList);
            return this;
        }

        public ImageViewStyle build() {
            return new ImageViewStyle(this.mBundle);
        }
    }
}
