package com.brentvatne.common.api;

import android.net.Uri;
import android.text.TextUtils;
import com.brentvatne.common.toolbox.ReactBridgeUtils;
import com.facebook.react.bridge.ReadableMap;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Lcom/brentvatne/common/api/AdsProps;", "", "()V", "adLanguage", "", "getAdLanguage", "()Ljava/lang/String;", "setAdLanguage", "(Ljava/lang/String;)V", "adTagUrl", "Landroid/net/Uri;", "getAdTagUrl", "()Landroid/net/Uri;", "setAdTagUrl", "(Landroid/net/Uri;)V", "equals", "", "other", "Companion", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AdsProps.kt */
public final class AdsProps {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String PROP_AD_LANGUAGE = "adLanguage";
    private static final String PROP_AD_TAG_URL = "adTagUrl";
    private String adLanguage;
    private Uri adTagUrl;

    @JvmStatic
    public static final AdsProps parse(ReadableMap readableMap) {
        return Companion.parse(readableMap);
    }

    public final Uri getAdTagUrl() {
        return this.adTagUrl;
    }

    public final void setAdTagUrl(Uri uri) {
        this.adTagUrl = uri;
    }

    public final String getAdLanguage() {
        return this.adLanguage;
    }

    public final void setAdLanguage(String str) {
        this.adLanguage = str;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof AdsProps)) {
            return false;
        }
        AdsProps adsProps = (AdsProps) obj;
        if (!Intrinsics.areEqual((Object) this.adTagUrl, (Object) adsProps.adTagUrl) || !Intrinsics.areEqual((Object) this.adLanguage, (Object) adsProps.adLanguage)) {
            return false;
        }
        return true;
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/brentvatne/common/api/AdsProps$Companion;", "", "()V", "PROP_AD_LANGUAGE", "", "PROP_AD_TAG_URL", "parse", "Lcom/brentvatne/common/api/AdsProps;", "src", "Lcom/facebook/react/bridge/ReadableMap;", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: AdsProps.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final AdsProps parse(ReadableMap readableMap) {
            AdsProps adsProps = new AdsProps();
            if (readableMap != null) {
                String safeGetString = ReactBridgeUtils.safeGetString(readableMap, AdsProps.PROP_AD_TAG_URL);
                if (TextUtils.isEmpty(safeGetString)) {
                    adsProps.setAdTagUrl((Uri) null);
                } else {
                    adsProps.setAdTagUrl(Uri.parse(safeGetString));
                }
                String safeGetString2 = ReactBridgeUtils.safeGetString(readableMap, AdsProps.PROP_AD_LANGUAGE);
                if (!TextUtils.isEmpty(safeGetString2)) {
                    adsProps.setAdLanguage(safeGetString2);
                }
            }
            return adsProps;
        }
    }
}
