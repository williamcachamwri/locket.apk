package com.google.android.tv.ads;

import android.os.Parcelable;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public abstract class IconClickFallbackImage implements Parcelable {

    /* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
    public static abstract class Builder {
        public abstract IconClickFallbackImage build();

        public abstract Builder setAltText(String str);

        public abstract Builder setCreativeType(String str);

        public abstract Builder setHeight(int i);

        public abstract Builder setStaticResourceUri(String str);

        public abstract Builder setWidth(int i);
    }

    public static Builder builder() {
        zza zza = new zza();
        zza.setWidth(0);
        zza.setHeight(0);
        zza.setAltText("");
        zza.setCreativeType("");
        zza.setStaticResourceUri("");
        return zza;
    }

    public abstract String getAltText();

    public abstract String getCreativeType();

    public abstract int getHeight();

    public abstract String getStaticResourceUri();

    public abstract int getWidth();
}
