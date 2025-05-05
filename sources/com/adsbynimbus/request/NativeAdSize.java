package com.adsbynimbus.request;

import com.adsbynimbus.openrtb.request.Format;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/adsbynimbus/request/NativeAdSize;", "", "(Ljava/lang/String;I)V", "adFormat", "Lcom/adsbynimbus/openrtb/request/Format;", "getAdFormat", "()Lcom/adsbynimbus/openrtb/request/Format;", "Small", "Medium", "Large", "request_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NativeAdSize.kt */
public enum NativeAdSize {
    Small,
    Medium,
    Large;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: NativeAdSize.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                com.adsbynimbus.request.NativeAdSize[] r0 = com.adsbynimbus.request.NativeAdSize.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.adsbynimbus.request.NativeAdSize r1 = com.adsbynimbus.request.NativeAdSize.Small     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.adsbynimbus.request.NativeAdSize r1 = com.adsbynimbus.request.NativeAdSize.Medium     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.adsbynimbus.request.NativeAdSize r1 = com.adsbynimbus.request.NativeAdSize.Large     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.request.NativeAdSize.WhenMappings.<clinit>():void");
        }
    }

    public final Format getAdFormat() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return Format.BANNER_320_50;
        }
        if (i == 2) {
            Format.Companion companion = Format.Companion;
            return Format.BANNER_300_250;
        } else if (i == 3) {
            return Format.INTERSTITIAL_PORT;
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }
}
